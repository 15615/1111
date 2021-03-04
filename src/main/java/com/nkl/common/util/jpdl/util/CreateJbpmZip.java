package com.nkl.common.util.jpdl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.nkl.common.util.jpdl.JpdlModelDrawer;

import com.nkl.common.util.jpdl.model.JpdlModel;

public class CreateJbpmZip {

	/**
	 * 根据xml内容生成zip文件
	 * @param xmlcontent  xml内容
	 * @param savePathDir 保存路径
	 * @param deployName  发布名称
	 * @return String[] 0:zipPath(zip路径) 1:savePathDir(zip所在目录) 2:tempDir(xml和png的临时路径)
	 * @throws Exception
	 */
	public static String[] getJbpmZipByXmlContent(String xmlcontent, String savePathDir, String deployName) throws Exception{
		String xmlName = deployName + ".jpdl.xml";
		String pngName = deployName + ".png";
		String tempDir = savePathDir+"\\temp";
		String pngPath = tempDir+"\\"+pngName;
		
		//根据内容生成xml文件保存到临时目录下
		XmlUtil.strChangeXML(xmlcontent, tempDir, xmlName);
		//根据内容生成png图片保存到与上面相同的临时目录下
		InputStream is = InputStreamUtil.StringTOInputStream(xmlcontent);
		JpdlModel jpdlModel = new JpdlModel(is);
		ImageIO.write(new JpdlModelDrawer().draw(jpdlModel), "png",new File(pngPath));
		if(is!=null){is.close();}
		
		//将临时路径下的xml和png打包为zip
		FileToZip.fileToZip(tempDir, savePathDir, deployName);
				
		String zipPath = savePathDir + "\\" + deployName + ".zip";
		return new String[]{zipPath,savePathDir,tempDir};
	}
	
	/**
	 * 根据xml文件生成zip文件
	 * @param xmlcontent  xml内容
	 * @param savePathDir 保存路径
	 * @param deployName  发布名称
	 * @return String[] 0:zipPath(zip路径) 1:savePathDir(zip所在目录) 2:tempDir(xml和png的临时路径)
	 * @throws Exception
	 */
	public static String[] getJbpmZipByXmlResource(String xmlPath, String savePathDir, String deployName) throws Exception{
		String pngName = deployName + ".png";
		String tempDir = savePathDir+"\\temp";
		String pngPath = tempDir+"\\"+pngName;
		String jpdlPath = tempDir+"\\"+deployName + ".jpdl.xml";
		InputStream is = new FileInputStream(new File(xmlPath));
		
		//复制文件到临时文件
//		FileUtils.copyFileToDirectory(new File(xmlPath), new File(tempDir));
		FileUtils.copyFile(new File(xmlPath), new File(jpdlPath));
		
		//根据内容生成png图片保存到与上面相同的临时目录下
		JpdlModel jpdlModel = new JpdlModel(is);
		ImageIO.write(new JpdlModelDrawer().draw(jpdlModel), "png",new File(pngPath));
		if(is!=null){is.close();}
		
		//将临时路径下的xml和png打包为zip
		FileToZip.fileToZip(tempDir, savePathDir, deployName);
				
		String zipPath = savePathDir + "\\" + deployName + ".zip";
		return new String[]{zipPath,savePathDir,tempDir};
	}
	
}

