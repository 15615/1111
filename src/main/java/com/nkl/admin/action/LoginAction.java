package com.nkl.admin.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nkl.admin.domain.User;
import com.nkl.admin.manager.LoginManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.Param;

@Controller
@Scope("prototype")
public class LoginAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Resource
	transient LoginManager loginManager;
	
	User params;
	String tip;	
	/**
	 * 
	 * **
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @Title: InSystem
	 * @Description: 用户登录
	 * @return String
	 */
	//public String InSystem(){
		public String inSystem(){
		try {
			//验证码验证
			String random = (String)Param.getSession("random");
			if (!random.equals(params.getRandom())) {
				tip="验证码错误";
				return "error";
			}
			
			//用户登录查询
			//params.setUser_type(2);
			User admin = loginManager.getUser(params);
			if (admin!=null) {
				Param.setSession("admin", admin);
			}else {
				tip="用户名或密码错误";
				return "error";
			}
			
		} catch (Exception e) {
			tip="登录异常，请稍后重试";
			return "error";
		}
		
		return "success";
	}
	
	/**
	 * @Title: OutSystem
	 * @Description: 退出登录
	 * @return String
	 */
	//public String OutSystem(){
		public String outSystem(){
		try {
			//用户查询
			User user = (User)Param.getSession("admin");
			if (user!=null) {
				//退出登录
				Param.removeSession("admin");
			}
			
		} catch (Exception e) {
			return "logout";
		}
		
		return "logout";
	}
	

	public User getParams() {
		return params;
	}

	public void setParams(User params) {
		this.params = params;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

}
