package com.nkl.admin.action;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nkl.admin.domain.Clazz;
import com.nkl.admin.domain.Score;
import com.nkl.admin.domain.User;
import com.nkl.admin.manager.AdminManager;
import com.nkl.common.excel.pojo.RowData;
import com.nkl.common.jxls.ExcelReader;
import com.nkl.common.jxls.impl.ExcelReaderImpl;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.Param;
import com.nkl.common.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

//@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ImportExcelAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -774181327892328274L;
	//封装文件标题请求参数的属性
	private String title;
	//封装导入文件域的属性
	private File upload;
	//封装导入文件类型的属性
	private String uploadContentType;
	//封装导入文件名的属性
	private String uploadFileName;
	//封装导入文件名的验证模板
	private String xmlFileName;
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return this.uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public File getUpload() {
		return this.upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return this.uploadContentType;
	}
	public String getXmlFileName() {
		return xmlFileName;
	}
	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}
	
	@Resource
	transient AdminManager adminManager;
	
	/**
	 * @Title: importScore
	 * @Description: 导入成绩
	 * @return String
	 */
	public String importScore() {
		try {
			//读取导入成绩Excel信息
			ExcelReader excelReader = new ExcelReaderImpl();
			excelReader.readConfig(ImportExcelAction.class.getClassLoader().getResourceAsStream(getXmlFileName()));
			List<RowData>  rowDatas =  excelReader.getSheetRows(getUpload().getAbsolutePath(), 0);
	
			//设置提示信息
			String tipInfo = null;
			
			//导入成绩
			List<Score> scores = new ArrayList<Score>();
			if (rowDatas!=null && rowDatas.size()>0) {
				for (RowData rowData : rowDatas) {
					if (!rowData.isRowError()) {//如果没有错误信息
						Score score = new Score();
						score.setUser_name(rowData.getField("user_name"));
						score.setCourse_name(rowData.getField("course_name"));
						score.setScore_year(Integer.parseInt(rowData.getField("score_year")));
						String score_year_half = rowData.getField("score_year_half");
						if ("上半年".equals(score_year_half)) {
							score.setScore_year_half(1);
						}else {
							score.setScore_year_half(2);
						}
						score.setScore_value(Integer.parseInt(rowData.getField("score_value")));
						scores.add(score);
					}else {//如果有错误信息
						tipInfo = "学号为："+rowData.getField("user_name")+" 的信息有误！";
						break;
					}
				}
			}
			
			//判断Excel错误信息结果
			if(!StringUtil.isEmptyString(tipInfo)){
				Param.setAttribute("tip", "no");
				Param.setAttribute("tipInfo", tipInfo);
				return INPUT;
			}
			
			//导入成绩入库
			adminManager.addScoreBatch(scores);
		} catch (Exception e) {
			Param.setAttribute("tip", "no");
			Param.setAttribute("tipInfo", "后台服务器繁忙！");
			return INPUT;
		}
		Param.setAttribute("tip", "ok");
		return SUCCESS;
	}
	
	/**
	 * @Title: importUser
	 * @Description: 导入学生
	 * @return String
	 */
	public String importUser() {
		try {
			//读取导入成绩Excel信息
			ExcelReader excelReader = new ExcelReaderImpl();
			excelReader.readConfig(ImportExcelAction.class.getClassLoader().getResourceAsStream(getXmlFileName()));
			List<RowData>  rowDatas =  excelReader.getSheetRows(getUpload().getAbsolutePath(), 0);
	
			//设置提示信息
			String tipInfo = null;
			
			//导入成绩
			List<User> users = new ArrayList<User>();
			if (rowDatas!=null && rowDatas.size()>0) {
				for (RowData rowData : rowDatas) {
					if (!rowData.isRowError()) {//如果没有错误信息
						User user = new User();
						user.setUser_name(rowData.getField("user_name"));
						user.setUser_pass(Md5.makeMd5(rowData.getField("user_pass")));
						user.setReal_name(rowData.getField("real_name"));
						String user_sex = rowData.getField("user_sex");
						if ("男".equals(user_sex)) {
							user.setUser_sex(1);
						}else {
							user.setUser_sex(2);
						}
						user.setUser_age(Integer.parseInt(rowData.getField("user_age")));
						//查询班级ID
						Clazz clazz = new Clazz();
						clazz.setClazz_name(rowData.getField("clazz_name"));
						clazz = adminManager.queryClazz(clazz);
						user.setClazz_id(clazz.getClazz_id());
						//设置用户类型
						user.setUser_type(1);
						user.setReg_date(DateUtil.getDate(DateUtil.getCurDateTime()));
						users.add(user);
					}else {//如果有错误信息
						tipInfo = "学号为："+rowData.getField("user_name")+" 的信息有误！";
						break;
					}
				}
			}
			
			//判断Excel错误信息结果
			if(!StringUtil.isEmptyString(tipInfo)){
				Param.setAttribute("tip", "no");
				Param.setAttribute("tipInfo", tipInfo);
				return INPUT;
			}
			
			//导入学生入库
			adminManager.addUserBatch(users);
		} catch(RuntimeException e){
			e.printStackTrace();
			Param.setAttribute("tip", "no");
			Param.setAttribute("tipInfo", "后台服务器繁忙！");
			return INPUT;
		} catch (SQLException e) {
			e.printStackTrace();
			Param.setAttribute("tip", "no");
			Param.setAttribute("tipInfo", "后台服务器繁忙！");
			return INPUT;
		} catch (Exception e) {
			e.printStackTrace();
			Param.setAttribute("tip", "no");
			Param.setAttribute("tipInfo", "后台服务器繁忙！");
			return INPUT;
		}
		Param.setAttribute("tip", "ok");
		return SUCCESS;
	}
}
