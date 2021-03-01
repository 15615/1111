package com.nkl.common.util.jpdl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.nkl.common.util.jpdl.util.CreateJbpmZip;
import com.nkl.common.util.jpdl.util.XmlUtil;

public class Test {
	public static void main(String[] args) throws Exception {
		//json数据
		String json = "{\"states\":{\"rect1\":{\"id\":\"rect1\",\"type\":\"start\",\"text\":{\"text\":\"开始\"}, \"attr\":{ \"x\":247, \"y\":14, \"width\":50, \"height\":50}, \"props\":{\"text\":{\"value\":\"开始\"},\"temp1\":{\"value\":\"\"},\"temp2\":{\"value\":\"\"}}},\"rect8\":{\"id\":\"rect8\",\"type\":\"end\",\"text\":{\"text\":\"结束\"}, \"attr\":{ \"x\":607, \"y\":16, \"width\":50, \"height\":50}, \"props\":{\"text\":{\"value\":\"结束\"},\"temp1\":{\"value\":\"\"},\"temp2\":{\"value\":\"\"}}},\"rect21\":{\"id\":\"rect21\",\"type\":\"task\",\"text\":{\"text\":\"提交申请\"}, \"attr\":{ \"x\":219, \"y\":121, \"width\":100, \"height\":50}, \"props\":{\"text\":{\"value\":\"提交申请\"},\"assignee\":{\"value\":\"\"}}},\"rect22\":{\"id\":\"rect22\",\"type\":\"task\",\"text\":{\"text\":\"审批任务1\"}, \"attr\":{ \"x\":114, \"y\":302, \"width\":100, \"height\":50}, \"props\":{\"text\":{\"value\":\"审批任务1\"},\"assignee\":{\"value\":\"\"}}},\"rect23\":{\"id\":\"rect23\",\"type\":\"task\",\"text\":{\"text\":\"审批任务2\"}, \"attr\":{ \"x\":344, \"y\":300, \"width\":100, \"height\":50}, \"props\":{\"text\":{\"value\":\"审批任务2\"},\"assignee\":{\"value\":\"\"}}},\"rect24\":{\"id\":\"rect24\",\"type\":\"fork\",\"text\":{\"text\":\"分支\"}, \"attr\":{ \"x\":243, \"y\":207, \"width\":50, \"height\":50}, \"props\":{\"text\":{\"value\":\"分支\"}}},\"rect29\":{\"id\":\"rect29\",\"type\":\"join\",\"text\":{\"text\":\"合并\"}, \"attr\":{ \"x\":244, \"y\":399, \"width\":50, \"height\":50}, \"props\":{\"text\":{\"value\":\"合并\"}}},\"rect32\":{\"id\":\"rect32\",\"type\":\"task\",\"text\":{\"text\":\"审批任务3\"}, \"attr\":{ \"x\":407, \"y\":400, \"width\":100, \"height\":50}, \"props\":{\"text\":{\"value\":\"审批任务3\"},\"assignee\":{\"value\":\"\"}}},\"rect33\":{\"id\":\"rect33\",\"type\":\"task\",\"text\":{\"text\":\"审批任务4\"}, \"attr\":{ \"x\":583, \"y\":403, \"width\":100, \"height\":50}, \"props\":{\"text\":{\"value\":\"审批任务4\"},\"assignee\":{\"value\":\"\"}}},\"rect34\":{\"id\":\"rect34\",\"type\":\"task\",\"text\":{\"text\":\"审批任务5\"}, \"attr\":{ \"x\":582, \"y\":201, \"width\":100, \"height\":50}, \"props\":{\"text\":{\"value\":\"审批任务5\"},\"assignee\":{\"value\":\"\"}}}},\"paths\":{\"path25\":{\"from\":\"rect1\",\"to\":\"rect21\", \"dots\":[],\"text\":{\"text\":\"TO 提交申请\"},\"textPos\":{\"x\":0,\"y\":-10}, \"props\":{\"text\":{\"value\":\"\"}}},\"path26\":{\"from\":\"rect21\",\"to\":\"rect24\", \"dots\":[],\"text\":{\"text\":\"TO 分支\"},\"textPos\":{\"x\":0,\"y\":-10}, \"props\":{\"text\":{\"value\":\"\"}}},\"path27\":{\"from\":\"rect24\",\"to\":\"rect22\", \"dots\":[{\"x\":164,\"y\":231}],\"text\":{\"text\":\"TO 审批任务1\"},\"textPos\":{\"x\":18,\"y\":-15}, \"props\":{\"text\":{\"value\":\"TO 审批任务1\"}}},\"path28\":{\"from\":\"rect24\",\"to\":\"rect23\", \"dots\":[{\"x\":393,\"y\":231}],\"text\":{\"text\":\"TO 审批任务2\"},\"textPos\":{\"x\":4,\"y\":-15}, \"props\":{\"text\":{\"value\":\"TO 审批任务2\"}}},\"path30\":{\"from\":\"rect22\",\"to\":\"rect29\", \"dots\":[{\"x\":162,\"y\":426}],\"text\":{\"text\":\"TO 合并\"},\"textPos\":{\"x\":5,\"y\":-16}, \"props\":{\"text\":{\"value\":\"TO 合并\"}}},\"path31\":{\"from\":\"rect23\",\"to\":\"rect29\", \"dots\":[],\"text\":{\"text\":\"TO 合并\"},\"textPos\":{\"x\":0,\"y\":-10}, \"props\":{\"text\":{\"value\":\"\"}}},\"path35\":{\"from\":\"rect29\",\"to\":\"rect32\", \"dots\":[],\"text\":{\"text\":\"TO 审批任务3\"},\"textPos\":{\"x\":0,\"y\":-10}, \"props\":{\"text\":{\"value\":\"\"}}},\"path36\":{\"from\":\"rect32\",\"to\":\"rect33\", \"dots\":[],\"text\":{\"text\":\"TO 审批任务4\"},\"textPos\":{\"x\":0,\"y\":-10}, \"props\":{\"text\":{\"value\":\"\"}}},\"path37\":{\"from\":\"rect33\",\"to\":\"rect34\", \"dots\":[],\"text\":{\"text\":\"TO 审批任务5\"},\"textPos\":{\"x\":0,\"y\":-10}, \"props\":{\"text\":{\"value\":\"\"}}},\"path38\":{\"from\":\"rect34\",\"to\":\"rect8\", \"dots\":[],\"text\":{\"text\":\"TO 结束\"},\"textPos\":{\"x\":0,\"y\":-10}, \"props\":{\"text\":{\"value\":\"\"}}},\"path39\":{\"from\":\"rect32\",\"to\":\"rect34\", \"dots\":[],\"text\":{\"text\":\"TO 审批任务5\"},\"textPos\":{\"x\":0,\"y\":-10}, \"props\":{\"text\":{\"value\":\"\"}}}},\"props\":{\"props\":{\"name\":{\"value\":\"流程模板\"}}}}";
		
		//打包zip
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String time = sdf.format(new Date());
		String saveDir = "D:\\temp\\loginName\\"+time;
		//先将json格式转换成xml并写入一个xml文件，再读取这个xml进行解析生成png图片，最后打包zip
		//创建xml文件
		String xmlSavePath = XmlUtil.jsonChange2Jpdl(json, saveDir, time+".xml");
		String zipPath[] = CreateJbpmZip.getJbpmZipByXmlResource(xmlSavePath, saveDir, "test");
		for(String ss :zipPath){
			System.out.println(ss);
		}
	}
	
}
