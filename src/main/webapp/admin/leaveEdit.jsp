<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.leave!=null">编辑</s:if><s:else>增加</s:else>请假</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	
	
	 $("#addBtn").bind('click',function(){
		if($("#paramsLeave\\.flag").val()!=1&&$("#paramsLeave\\.flag").val()!=2){
			alert('请选择请假类型');
			return; 
		}
	 if(!$("#paramsLeave\\.reason").val()){
		alert('请输入请假原因');
		return;
	}
	 if(!$("#paramsLeave\\.starttime").val()){
			alert('请选择请假开始时间');
			return;
	}
	 if(!$("#paramsLeave\\.endtime").val()){
			alert('请选择请假结束时间');
			return;
	}
	 $("#btime").val($("#paramsLeave\\.starttime").val().toString())
	 
	 $("#etime").val($("#paramsLeave\\.endtime").val().toString())
	 
	 $("#info").attr('action','Admin_addLeave.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
			
		 if(!$("#paramsLeave\\.reason").val()){
			alert('请输入请假原因');
			return;
		}
		 
			$("#info").attr('action','Admin_saveLeave.action').submit();
			
	});
	 
	function dateToString(date){
		    const year = date.getFullYear();
		    let month = date.getMonth() + 1;
		    let day = date.getDate();
		    let hour = date.getHours();
		    let minute = date.getMinutes();
		    let second = date.getSeconds();
		    month = month > 9 ? month : ('0' + month);
		    day = day > 9 ? day : ('0' + day);
		    hour = hour > 9 ? hour : ('0' + hour);
		    minute = minute > 9 ? minute : ('0' + minute);
		    second = second > 9 ? second : ('0' + second);
		    const dateTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
		    return dateTime;
		  }
});


</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">请假管理&gt;&gt;<s:if test="#attr.clazz!=null">编辑</s:if><s:else>添加</s:else>请假</span>
</div>
<form id="info" name="info" action="Admin_addLeave.action" method="post">   
<s:hidden id="paramsLeave.id" name="paramsLeave.id" value="%{#attr.leave.id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.leave!=null">编辑 </s:if><s:else>增加</s:else>请假信息</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
      <s:if test="#attr.leave==null"> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 请假类型：</td>
          <td width="65%">
          	<s:select list="#{'1':'请假类型一','2':'请假类型二'}" id="paramsLeave.flag" name="paramsLeave.flag" 
          		value="%{#attr.leave.flag}"  cssStyle="width:155px;"
          		listKey="key" listValue="value" headerKey="0" headerValue="请选择">
          	</s:select>
          </td>
        </tr> 
        </s:if>
        <tr>
          <td width="35%" align="right" style="padding-right:5px">请假原因：</td>
          <td width="65%">
          	<s:textarea name="paramsLeave.reason" id="paramsLeave.reason" value="%{#attr.leave.reason}" cssStyle="width:300px;height:60px;">
          	</s:textarea>
          </td>
        </tr> 
        <s:if test="#attr.leave==null"> 
         <tr>
          <td width="35%" align="right" style="padding-right:5px">请假开始时间：</td>
          <td width="65%">
          	<input type="datetime-local" name="paramsLeave.starttime" id="paramsLeave.starttime" >
          </td>
        </tr>
         <tr>
          <td width="35%" align="right" style="padding-right:5px">请假结束时间：</td>
          <td width="65%">
          	<input type="datetime-local" name="paramsLeave.endtime" id="paramsLeave.endtime" >
          </td> 
        </tr>
        </s:if>
        	<input type="text" name="paramsLeave.stuname" style="display: none;" value="${admin.real_name}">
          	<input type="text" id="btime" name="btime" style="display: none;" >
          	<input type="text" id="etime" name="etime" style="display: none;" >
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<s:if test="#attr.leave!=null">
          	<input type="button" id="editBtn" Class="btnstyle" value="编辑请假"/> 
          	</s:if>
          	<s:else>
          	<input type="button" id="addBtn" Class="btnstyle" value="新增请假" />
          	</s:else>
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>