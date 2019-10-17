<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel = "stylesheet" type = "text/css" href = "<%=basePath%>css/ext-all.css"/>
<link rel = "stylesheet" type = "text/css" href = "<%=basePath%>css/mainstyle.css"/>
<link rel = "stylesheet" type = "text/css" href = "<%=basePath%>css/processing.css"/>
