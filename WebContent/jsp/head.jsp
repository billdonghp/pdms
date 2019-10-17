<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<jsp:include page="css.jsp"></jsp:include>
	<jsp:include page="js.jsp"></jsp:include>
	<script type = "text/javascript">
		Ext.onReady(function(){
			//Logout
			$("#logout").click(function(){
				$.get("<%=basePath%>lg/logout.action",function(){
					window.location="<%=basePath%>index.jsp";
				});
			});
			$("#usermanul").click(function(){
				var cm1 = new Ext.grid.ColumnModel([{
					header:"区分",dataIndex:"type",align:"center",menuDisabled:true
				},{
					header:"文件名",dataIndex:"filename",align:"center",renderer:function(v){
						return "<a href ='<%=basePath%>manual.action?download="+v+"' >"+v+"</a>";
					},menuDisabled:true
				}]);
				var data1 = [["PDMS系统","manual.pptx"]];
				var ds1 = new Ext.data.Store({
					proxy:new Ext.data.MemoryProxy(data1),
					reader:new Ext.data.ArrayReader({},[
					    {name:"type"},
					    {name:"filename"}
					])
				});
				ds1.load();
				
				var grid1 = new Ext.grid.GridPanel({
					ds:ds1,
					cm:cm1,
					width:650,
					height:200,
					viewConfig:{
						forceFit:true
					}
				});
				new Ext.Window({
					title:"如需帮助,请选择对应指导书",
					width:676,
					height:249,
					items:[
						grid1
					],
					autoScroll:true,
					bodyStyle:"padding:5px"
				}).show(Ext.getBody());
			});
			new Ext.Toolbar({
            	items:[
            		{
		        		xtype:"label",
		        		cls:"labelCls2",
		        		text:"退役军人档案管理系统"
		        	},{
		        		xtype:"label",
		        		cls:"labelCls2",
		        		text:"<s:property value = '%{#session.userbgnm}'/>"
		        	}
            	]
            }).render("bgdiv");
		});
	</script>
  </head>  
  <body>
	<div>
		<div style = "float:left;">
			<img src = "<%=basePath%>images/doopro/chine.png"/>
		</div>
		<div  style = "float:right;">
			<table id = "tableSty">
				<tr>
					<td align = center id = "usermanul" style = "font-size:12px;text-align:center;color:#555;text-decoration:underline;font-weight:bold;cursor:hand;">
						用户手册
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td align = "right" style = "font-size:12px;text-align:center;color:#555;font-weight:bold;">
						您好:
						<s:property value = "%{#session.username}"/>&nbsp;&nbsp;
						<s:property value = "%{#session.logininfo}"/>&nbsp;&nbsp;
					</td>
					<td align = "center">
						<img src = "<%=basePath%>images/doopro/logout.jpg" style = "cursor:hand;margin-top:5px;" id = "logout"/>
					</td>
				</tr>
			</table>
		</div>
		<div  id = "bgdiv" style = "clear:both;margin:0px;padding:0px;width:100%"></div>
	</div>
  </body>
</html>
