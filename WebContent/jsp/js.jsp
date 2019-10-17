<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type = "text/javascript" src = "<%=basePath%>js/jquery-1.10.2.min.js"></script>
<script type = "text/javascript" src = "<%=basePath%>js/ext-base.js"></script>
<script type = "text/javascript" src = "<%=basePath%>js/ext-all.js"></script>
<script type = "text/javascript" src = "<%=basePath%>js/ext-lang-zh_CN.js"></script>
<script type = "text/javascript" src = "<%=basePath%>js/doopro/sys-all.js"></script>
<script type = "text/javascript" src = "<%=basePath%>js/processing.js"></script>
<script type = "text/javascript">
Ext.onReady(function(){
	Ext.QuickTips.init();// 浮动信息提示
});
</script>
<!-- 服务器地址 -->
<input type = "hidden" value = "<%=basePath%>" id = "serverpath"/>
