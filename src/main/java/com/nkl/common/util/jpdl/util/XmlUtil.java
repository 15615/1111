package com.nkl.common.util.jpdl.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class XmlUtil {

	/**
	 * xml转换成String
	 * 
	 * @param fileName
	 * @return
	 */
	public static String xmlChangeString(String filePath) {
		try {
			// 创建SAXReader对象
			SAXReader reader = new SAXReader();
			// 读取文件 转换成Document
			Document document = reader.read(new File(filePath));
			// document转换为String字符串
			String documentStr = document.asXML();
			return documentStr;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将字符串str的xml内容格式保存到savePathDir目录下，名称为fileName
	 * 
	 * @param str
	 * @param savePathDir
	 * @param fileName
	 * @return 保存路径
	 * @throws IOException
	 */
	public static String strChangeXML(String str, String savePathDir,
			String fileName) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(
				str.getBytes("UTF-8"));
		XMLWriter writer = null;
		String savePath = null;
		try {
			SAXReader saxReader = new SAXReader();
			Document document;
			document = saxReader.read(bais);
			OutputFormat format = OutputFormat.createPrettyPrint();
			// 判断保存目录是否存在
			File saveDir = new File(savePathDir);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			savePath = savePathDir + "\\" + fileName;
			/** 将document中的内容写入文件中 */
			writer = new XMLWriter(new FileWriter(new File(savePath)),
					format);
			writer.write(document);
			writer.close();
		} catch (DocumentException e) {
			System.out.println("转换失败！");
			e.printStackTrace();
		}finally{
			if(writer!=null){
				writer.close();
			}
			if(bais!=null){
				bais.close();
			}
		}
		return savePath;
	}
	
	/**
	 * 根据任务id从任务节点中获取节点
	 * @param states
	 * @param taskId
	 * @return
	 */
	public static JsonNode getTaskByTaskId(JsonNode states, String taskId){
		for(JsonNode item : states){
			String id = item.path("id").asText();
			if(id.equals(taskId)){
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 把String类型的json转换成jpdl字符串,注：jpdl格式未实现全部支持
	 * @param json
	 * @return xmlString
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static String jsonChange2Jpdl(String json) throws JsonProcessingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		//json字符串不加双引号的情况下
//		mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false); 
		JsonNode root = mapper.readTree(json);
		JsonNode states = root.path("states");//任务节点  
		JsonNode paths = root.path("paths");//连线路径
		JsonNode props = root.path("props"); 
		
		String processName = props.path("props").path("name").path("value").asText();

		//创建字符型xml
		Document document = DocumentHelper.createDocument();
		Element process = document.addElement("process");  
		process.addAttribute("name", processName);
		process.addAttribute("xmlns", "http://jbpm.org/4.4/jpdl");
        
		//生成process的子节点
		for(JsonNode item : states){	
			//获取json中的数据值
			String taskId = item.path("id").asText();
			String taskType = item.path("type").asText();
			String taskName = item.path("text").path("text").asText();
			String g = item.path("attr").path("x").asText()+","+item.path("attr").path("y").asText()+","+item.path("attr").path("width").asText()+","+item.path("attr").path("height").asText();
			String assignee = item.path("props").path("assignee").path("value").asText();
			//创建任务节点
			Element task = process.addElement(taskType);
			task.addAttribute("name", taskName);
			task.addAttribute("g", g);
			if(taskType.equals("task") && !assignee.trim().isEmpty()){
				task.addAttribute("assignee", assignee);
			}else if(taskType.equals("task") && assignee.trim().isEmpty()){
				task.addAttribute("assignee", "admin");//如果未指定办理人，就自动设置为管理员
			}
			//创建任务节点下的连线
			for(JsonNode tem : paths){
				String from = tem.path("from").asText();
				if(taskId.equals(from)){
					Element transition = task.addElement("transition");
					String transitionName = tem.path("text").path("text").asText();
					String toTaskId = tem.path("to").asText();
					transition.addAttribute("name", transitionName);
					JsonNode toTask = getTaskByTaskId(states,toTaskId);
					String toTaskName = toTask.path("text").path("text").asText();
					transition.addAttribute("to", toTaskName);
					String g2 = tem.path("textPos").path("x").asText()+","+tem.path("textPos").path("y").asText();
					String xy;
					if(tem.path("dots").size()>0){
						xy = tem.path("dots").get(0).get("x") + "," + tem.path("dots").get(0).get("y") + ":" + g2;
					}else{
						xy = g2;
					}
					
					transition.addAttribute("g", xy);
				}
			}
		}
        
        

        //创建字符串缓冲区 
        StringWriter stringWriter = new StringWriter();  
        //设置文件编码  
        OutputFormat xmlFormat = new OutputFormat();  
        xmlFormat.setEncoding("UTF-8"); 
        // 设置换行 
        xmlFormat.setNewlines(true); 
        // 生成缩进 
        xmlFormat.setIndent(true); 
        // 使用4个空格进行缩进, 可以兼容文本编辑器 
//        xmlFormat.setIndent("    "); 
        
        //创建写文件方法  
//        Writer fileWriter = new FileWriter(\"D:/test.xml\");
        XMLWriter xmlWriter = new XMLWriter(stringWriter,xmlFormat);  
        //写入文件  
        xmlWriter.write(document);  
        //关闭  
        xmlWriter.close(); 
        // 输出xml 
        return stringWriter.toString();
	}
	
	/**
	 * 把String类型的json转换成jpdl格式并保存,注：jpdl格式未实现全部支持
	 * @param json
	 * @param savePathDir
	 * @param fileName
	 * @return 保存的全路径
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static String jsonChange2Jpdl(String json, String savePathDir, String fileName) throws JsonProcessingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		//json字符串不加双引号的情况下
//		mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false); 
		JsonNode root = mapper.readTree(json);
		JsonNode states = root.path("states");//任务节点  
		JsonNode paths = root.path("paths");//连线路径
		JsonNode props = root.path("props"); 
		
		String processName = props.path("props").path("name").path("value").asText();

		//创建字符型xml
		Document document = DocumentHelper.createDocument();
		Element process = document.addElement("process");  
		process.addAttribute("name", processName);
		process.addAttribute("xmlns", "http://jbpm.org/4.4/jpdl");
        
		//生成process的子节点
		for(JsonNode item : states){	
			//获取json中的数据值
			String taskId = item.path("id").asText();
			String taskType = item.path("type").asText();
			String taskName = item.path("text").path("text").asText();
			String g = item.path("attr").path("x").asText()+","+item.path("attr").path("y").asText()+","+item.path("attr").path("width").asText()+","+item.path("attr").path("height").asText();
			String assignee = item.path("props").path("assignee").path("value").asText();
			//创建任务节点
			Element task = process.addElement(taskType);
			task.addAttribute("name", taskName);
			task.addAttribute("g", g);
			if(taskType.equals("task") && !assignee.trim().isEmpty()){
				task.addAttribute("assignee", assignee);
			}else if(taskType.equals("task") && assignee.trim().isEmpty()){
				task.addAttribute("assignee", "admin");//如果未指定办理人，就自动设置为管理员
			}
			//创建任务节点下的连线
			for(JsonNode tem : paths){
				String from = tem.path("from").asText();
				if(taskId.equals(from)){
					Element transition = task.addElement("transition");
					String transitionName = tem.path("text").path("text").asText();
					String toTaskId = tem.path("to").asText();
					transition.addAttribute("name", transitionName);
					JsonNode toTask = getTaskByTaskId(states,toTaskId);
					String toTaskName = toTask.path("text").path("text").asText();
					transition.addAttribute("to", toTaskName);
					String g2 = tem.path("textPos").path("x").asText()+","+tem.path("textPos").path("y").asText();
					String xy;
					if(tem.path("dots").size()>0){
						xy = tem.path("dots").get(0).get("x") + "," + tem.path("dots").get(0).get("y") + ":" + g2;
					}else{
						xy = g2;
					}
					
					transition.addAttribute("g", xy);
				}
			}
		}
        
        

        //创建字符串缓冲区 
        StringWriter stringWriter = new StringWriter();  
        //设置文件编码  
        OutputFormat xmlFormat = new OutputFormat();  
        xmlFormat.setEncoding("UTF-8"); 
        // 设置换行 
        xmlFormat.setNewlines(true); 
        // 生成缩进 
        xmlFormat.setIndent(true); 
        // 使用4个空格进行缩进, 可以兼容文本编辑器 
//        xmlFormat.setIndent("    "); 
        
        //创建写文件方法  
//        Writer fileWriter = new FileWriter(\"D:/test.xml\");
        XMLWriter xmlWriter = new XMLWriter(stringWriter,xmlFormat);  
        //写入文件  
        xmlWriter.write(document);  
        //关闭  
        xmlWriter.close(); 
        // 输出xml 
        String xmlSavePath = XmlUtil.strChangeXML(stringWriter.toString(), savePathDir, fileName);
        return xmlSavePath;
	}
}
