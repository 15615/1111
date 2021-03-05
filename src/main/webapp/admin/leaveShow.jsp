<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
var tempClassName=""; 
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}


/* function serch()
{
   document.info.action="Admin_listLeave.action";
   document.info.submit();
} */
function del()
{
	var checks=document.getElementsByName("chkid");
    var ids="";
	for (var i=0;i<checks.length;i++)
    {
        var e = checks[i];
		if(e.checked==true)
		{
		  if(ids=="")
		  {
		    ids=ids+e.value;
		  }
		  else
		  {
		    ids=ids+","+e.value;
		  }
		}
    }
    if(ids=="")
    {
       alert("请至少选择一个要删除的请假！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delCourses.action?paramsCourse.ids="+ids;
       document.info.submit();
    }
    
}
function delById(id){
	console.log(id)
	event.preventDefault();
	 if(confirm('确认删除吗!?'))
	    {
	       document.info.action="Admin_delLeave.action?paramsLeave.id="+id;
	       document.info.submit();
	    }
}

function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listCourses.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listCourses.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">请假管理&gt;&gt;请假查询</span>
</div>
<form name="info" id="info" action="Admin_listleave.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">请假列表</td>
    <td width="" align="right">
      <input type="button" value="增加" onclick="window.location='Admin_addLeaveShow.action';" class="btnstyle"/> &nbsp;
      <!-- <input type="button" value="删除" onclick="del();" class="btnstyle"/> --> 
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <!-- <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td> -->
     <td width="40" align="center">序号</td>
     <td width="" align="center">请假原因</td>
     <td width="" align="center">请假类型</td>
     <td width="" align="center">请假时间</td>
     <td width="" align="center">结束时间</td>
     <td width="" align="center">请假状态</td>
     <td width="" align="center">操作</td>
   </tr>
   <s:if test="#attr.leaves!=null && #attr.leaves.size()>0">
   <s:iterator value="#attr.leaves" id="leave" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
    <%--  <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#leave.id}" cssStyle="vertical-align:text-bottom;"/></td> --%>
     <td width="" align="center"><s:property value="#status.index+1"/></td>
     <td width="" align="center"><s:property value="#leave.reason"/></td>
     <td width="" align="center">
    <%--  <s:property value="#leave.flag"/> --%>
     <s:if test="#leave.flag==1">请假一</s:if>
     <s:if test="#leave.flag==2">请假二</s:if>
     </td>
     <td width="" align="center"><s:property value="#leave.b_dateDesc"/></td>
     <td width="" align="center"><s:property value="#leave.e_dateDesc"/></td>
     <td width="" align="center">
     <%-- <s:property value="#leave.state"/> --%>
     <s:if test="#leave.flag==1">
     		<s:if test="#leave.state==0">已驳回,请删除后重新申请</s:if>
     		<s:if test="#leave.state==1">等待班主任审批  </s:if>
     		<s:if test="#leave.state==2">等待学院审批  </s:if>
     		<s:if test="#leave.state==3">已同意  </s:if>
     </s:if>
      <s:if test="#leave.flag==2">
     		<s:if test="#leave.state==0">已驳回,请删除后重新申请 </s:if>
     		<s:if test="#leave.state==1">等待导师审批  </s:if>
     		<s:if test="#leave.state==2">等待学院审批  </s:if>
     		<s:if test="#leave.state==3">等待研究生处备案  </s:if>
     		<s:if test="#leave.state==4">领取通行证 </s:if>
     		<s:if test="#leave.state==5">已领取通行证</s:if>
     </s:if>
     </td>
     
     <td width="" align="center">
      
        <c:if test='${admin.user_type==1}'>
         	<s:if test="#leave.state==1">
       <img src="images/edit.png"/>&nbsp;<s:a href="Admin_editLeave.action?paramsLeave.id=%{#leave.id}">编辑</s:a>
        <img src="images/del.gif"/>&nbsp;<s:a onclick="delById(%{#leave.id})">删除</s:a> 
      		 </s:if> 
      		 <s:if test="#leave.state==0">
       <img src="images/del.gif"/>&nbsp;<s:a onclick="delById(%{#leave.id})">删除</s:a>
       		</s:if>
       		<s:if test="#leave.state==4">
       		 <img src="images/edit.png"/>&nbsp;<s:a href="Admin_passLeave.action?paramsLeave.id=%{#leave.id}&paramsLeave.state=%{#leave.state+1}&paramsLeave.piid=%{#leave.piid}&paramsLeave.flag=%{#leave.flag}">领取通行证</s:a>
       		</s:if>
       	</c:if>
        <s:if test="#leave.flag == 1 && #leave.state<3"> 
       <img src="images/down.gif"/>&nbsp;<s:a href="Admin_getPic.action?paramsLeave.piid=%{#leave.piid}">查看流程图</s:a>
       	</s:if>
       	  <s:if test="#leave.flag == 2 && #leave.state<5">
       <img src="images/down.gif"/>&nbsp;<s:a href="Admin_getPic.action?paramsLeave.piid=%{#leave.piid}">查看流程图</s:a>
       	</s:if>
      	 <!-- 张老师审核 班主任审批 导师审批-->
       <c:if test='${admin.user_name=="tea1"}'>
        <s:if test="#leave.state==1">
       <img src="images/edit.png"/>&nbsp;<s:a href="Admin_passLeave.action?paramsLeave.id=%{#leave.id}&paramsLeave.state=%{#leave.state+1}&paramsLeave.piid=%{#leave.piid}&paramsLeave.flag=%{#leave.flag}">审批通过</s:a>
        <s:if test="#leave.flag==1">
       <img src="images/edit.png"/>&nbsp;<s:a href="Admin_passLeave.action?paramsLeave.id=%{#leave.id}&paramsLeave.state=%{#leave.state-1}&paramsLeave.piid=%{#leave.piid}&paramsLeave.flag=%{#leave.flag}">审批驳回</s:a>
        </s:if>
        </s:if> 
       </c:if>
       	 <!-- 李老师审核 班主任审批 学院审批-->
       <c:if test='${admin.user_name=="tea2"}'>
        <s:if test="#leave.state==2">
       <img src="images/edit.png"/>&nbsp;<s:a href="Admin_passLeave.action?paramsLeave.id=%{#leave.id}&paramsLeave.state=%{#leave.state+1}&paramsLeave.piid=%{#leave.piid}&paramsLeave.flag=%{#leave.flag}">审批通过</s:a>
        <%--  <s:if test="#leave.flag==1">
       <img src="images/edit.png"/>&nbsp;<s:a href="Admin_passLeave.action?paramsLeave.id=%{#leave.id}&paramsLeave.state=%{#leave.state-1}&paramsLeave.piid=%{#leave.piid}&paramsLeave.flag=%{#leave.flag}">审批驳回</s:a>
        </s:if> --%>
        </s:if> 
       </c:if>
        <!-- 王老师审核 研究生处备案-->
        <c:if test='${admin.user_name=="tea3"}'>
        <s:if test="#leave.state==3 && #leave.flag==2">
       <img src="images/edit.png"/>&nbsp;<s:a href="Admin_passLeave.action?paramsLeave.id=%{#leave.id}&paramsLeave.state=%{#leave.state+1}&paramsLeave.piid=%{#leave.piid}&paramsLeave.flag=%{#leave.flag}">审批通过</s:a>
        </s:if>  
       </c:if> 
     </td>  
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="7" align="center"><b>&lt;不存在请假信息&gt;</b></td> 
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>