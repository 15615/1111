<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理页面</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">  
<script src="js/prototype.lite.js" type="text/javascript"></script>
<script src="js/moo.fx.js" type="text/javascript"></script>
<script src="js/moo.fx.pack.js" type="text/javascript"></script>
<style type="text/css">
.left{width:390px; height:280px; background-color:#EEF2FB}
table tr td{ font-size:12px; font-family:Arial, Helvetica, sans-serif;}
body {
	font:12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #EEF2FB;
	margin: 0px;
}
#container {
	width: 390px;
}
H1 {
	font-size: 12px;
	margin: 0px;
	width: 390px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;	
}
H1 a {
	display: block;
	width: 390px;
	color: #000;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}
.content{
	width: 390px;
	height: 26px;
	
}
.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}
.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 26px;
	width: 390px;
	padding-left: 0px;
}
.MM {
	width: 390px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px,0px,0px,0px);
}
.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-repeat: no-repeat;
	height: 26px;
	width: 390px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-repeat: no-repeat;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width:390px;
	text-decoration: none;
}
.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-repeat: no-repeat;
	height: 26px;
	width: 390px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #006600;
	background-repeat: no-repeat;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 390px;
	text-decoration: none;
}
</style>
</head>

<body>
<table width="390" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
  	<td height="23" style="text-align:center; line-height:23px; color:#BEDFF1; background:url(images/left_title.gif) no-repeat left bottom;font-weight:bold">功能模块管理</td>
  </tr>
  <tr>
    <td width="390" valign="top" style="background:#fff;">
    	<div class="left">
			 <table width="100%" height="280" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
			  <tr>
				<td width="390" valign="top">
				<div id="container">
				  <h1 class="type"><a href="javascript:void(0)">--------------------* 个人中心   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="modifyInfo.jsp" target="MainFrame">用户个人信息</a></li>
					  <li><a href="modifyPwd.jsp" target="MainFrame">用户修改密码</a></li>
					</ul>
				  </div>
				  <c:if test="${admin.user_type==3}">
				  <h1 class="type"><a href="javascript:void(0)">--------------------* 学院班级管理   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listClazzs.action" target="MainFrame">学院班级查询</a></li>
					  <li><a href="Admin_addClazzShow.action" target="MainFrame">学院新增班级</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">--------------------* 学院课程管理   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listCourses.action" target="MainFrame">学院课程查询</a></li>
					  <li><a href="Admin_addCourseShow.action" target="MainFrame">学院新增课程</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">--------------------* 任课教师管理   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listTeachers.action" target="MainFrame">任课教师信息查询</a></li>
					   <li><a href="Admin_xxx.action" target="MainFrame">wuwuw</a></li>
					  <li><a href="Admin_addTeacherShow.action" target="MainFrame">新增教师信息</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">--------------------* 学年教学计划   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listPlans.action" target="MainFrame">教学计划查询</a></li>
					  <li><a href="Admin_addPlanShow.action" target="MainFrame">新增教学计划</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">--------------------* 班级课程安排表   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listCplans.action" target="MainFrame">班级课表查询</a></li>
					  <li><a href="Admin_addCplanShow.action" target="MainFrame">新增班级课表</a></li>
					  <li><a href="Admin_listCplansByClazzShow.action" target="MainFrame">课表一周视图</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">--------------------* 院系学生管理   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listUsers.action" target="MainFrame">学生查询</a></li>
					  <li><a href="Admin_addUserShow.action" target="MainFrame">新增学生</a></li>
					  <li><a href="importUserP.jsp" target="MainFrame">导入学生</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">--------------------* 智能模块管理   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <%--<li><a href="Admin_listEvaluates.action" target="MainFrame">智能模块1（教师发链接）</a></li>
					  <li><a href="Admin_listEvaluatesSum.action" target="MainFrame">智能模块2（学生查链接）</a></li>
					  --%><li><a href="Admin_listStuCourse.action" target="MainFrame">学生选课记录</a></li>
					  <li><a href="Admin_BPMN.action" target="MainFrame">BPMN建模子系统</a></li>
					</ul>
				  </div>
				  </c:if>
				  <c:if test="${admin.user_type==2}">
				  <h1 class="type"><a href="javascript:void(0)">--------------------* 学生成绩管理   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listScores.action" target="MainFrame">成绩查询</a></li>
					  <li><a href="Admin_addScoreShow.action" target="MainFrame">新增成绩</a></li>
					  <li><a href="Admin_addScoreSelectShow.action" target="MainFrame">新增选修</a></li>
					  <li><a href="Admin_listScoresSum.action" target="MainFrame">总分成绩</a></li>
					  <li><a href="importScoreP.jsp" target="MainFrame">导入成绩</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">--------------------* 学科班级课表   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listCplansByClazzShow.action" target="MainFrame">课表一周视图</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">--------------------* 智能模块管理   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listSScores.action" target="MainFrame">成绩审核</a></li>
					  <li><a href="znmk.jsp" target="MainFrame">智能监测</a></li>
					</ul>
				  </div>
				   <h1 class="type"><a href="javascript:void(0)">--------------------* 请假模块管理   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					<li><a href="Admin_listLeave.action" target="MainFrame">请假审批</a></li>
					</ul>
				  </div>
				  </c:if>
				  <c:if test="${admin.user_type==1}">
				  <h1 class="type"><a href="javascript:void(0)">--------------------* 学生选课管理   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listScources.action" target="MainFrame">已选课程查询</a></li>
					  <li><a href="Admin_addScourceShow.action" target="MainFrame">新增选修课程</a></li>
					</ul>
				  </div>
				 <h1 class="type"><a href="javascript:void(0)">--------------------* 学生请假管理   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listLeave.action?paramsLeave.stuname=${admin.real_name}" target="MainFrame">请假查询</a></li>
					  <li><a href="Admin_addLeaveShow.action" target="MainFrame">新增请假</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">--------------------* 学生成绩查询   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listScores.action" target="MainFrame">成绩查询</a></li>
					  <li><a href="Admin_listScoresSum.action" target="MainFrame">总分成绩</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">--------------------* 学科班级课表   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listCplansByClazzShow.action" target="MainFrame">课表一周视图</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">--------------------* 智能模块管理   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM"><%--
					  <li><a href="Admin_listEvaluates.action" target="MainFrame">智能模块1（科研链接）</a></li>
					  <li><a href="Admin_addEvaluateShow.action" target="MainFrame">智能模块2（下载链接）</a></li>
					  --%><li><a href="Admin_listSScores.action" target="MainFrame">填评测成绩</a></li>
					</ul>
				  </div>
			<!-- 	  <h1 class="type"><a href="javascript:void(0)">--------------------* 请假模块管理   *--------------------</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="390" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					<li><a href="Admin_listSScores.action" target="MainFrame">请假</a></li>
					</ul>
				  </div>-->
				  </c:if>
				   
					<script type="text/javascript">
						var contents = document.getElementsByClassName('content');
						var toggles = document.getElementsByClassName('type');
					
						var myAccordion = new fx.Accordion(
							toggles, contents, {opacity: true, duration: 400}
						);
						myAccordion.showThisHideOpen(contents[0]);
					</script>
				</div>
				</td>
			  </tr>
			</table>       	
        </div>
    </td>
  </tr>
</table>
</body>
</html>
