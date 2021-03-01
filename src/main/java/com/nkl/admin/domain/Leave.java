package com.nkl.admin.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.DateUtil;

public class Leave extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private String reason;
	private int flag;
	private int state;
	private Date starttime;
	private Date endtime;
	private String piid;
	private String stuname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getPiid() {
		return piid;
	}
	public void setPiid(String piid) {
		this.piid = piid;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	
	public String getB_dateDesc(){
		try {
			return DateUtil.dateToDateString(starttime, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public String getE_dateDesc(){
		try {
			return DateUtil.dateToDateString(endtime, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	

}
