<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>退役军人档案管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<jsp:include page="css.jsp"></jsp:include>
	<jsp:include page="js.jsp"></jsp:include>
	<script type = "text/javascript" src = "<%=basePath%>js/doopro/main.js"></script>
	<script type = "text/javascript" src = "<%=basePath%>js/doopro/jQuery.print.js"></script>
  </head>
  <body bgcolor = "#F0F8FF"> 
  	<h1>WELCOME TO PDMS</h1>
  	<div>
  		<input type = "hidden" id = "basepath" value = "<%=basePath%>"/>
  		<!-- 当前使用者 -->
  		<input type = "hidden" id = "currentuser" value = "<s:property value = '%{#session.username}'/>"/>
  	</div>
  </body>
</html>
