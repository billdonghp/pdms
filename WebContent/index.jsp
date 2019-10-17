<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//zhoufamin add session context root
	request.setAttribute("root", request.getContextPath());
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>退役军人档案管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<jsp:include page="jsp/css.jsp"></jsp:include>
	<jsp:include page="jsp/js.jsp"></jsp:include>
	<script type = "text/javascript">
	Ext.onReady(function(){
		//login main
		var i = new Ext.Panel({
			frame:true,
			width:656,
			height:379,
			bodyStyle:"padding:6px;background-image:url('images/doopro/login.png')"
		});
		i.render("imgdiv");
		var f = new Ext.form.FormPanel({
			width:656,
			layout:"table",
			frame:true,
			url:$("#serverpath").val()+"lg/login.action",
			method:"post",
			layoutConfig:{columns:6},
			defaults: {   
		        height:20   
		    },  
		    defaultType:'textfield',
			autoHeight:true,
			items: [{
	    		xtype:"label",
	    		cls:"labelCls",
	    		text:"用户名:"
	    	},{
	            name:"name",
		        id:"name",
		        allowBlank:false
	        },{
	    		xtype:"label",
	    		cls:"labelCls",
	    		text:"密码:"
	    	},{
	    		name:"password",
		        id:"password",
		        inputType:"password",
		        allowBlank:false
	    	},{
	    		xtype:"label",
	    		cls:"labelCls",
	    		text:" "
	    	},{
	    		xtype:"button",
	    		text:"登录",
	    		type:"submit",
		        id:"loginbtn",
		        listeners:{"click":function(){
						var btn = this;
		    			btn.disable();
		    			f.getForm().submit({
								success:function(f,a){
									if(a.result.info == "ok"){
										window.location=$("#serverpath").val()+"jsp/main.jsp";
									}else{
										alertMsg(a.result.info,300,100,Ext.Msg.OK,Ext.Msg.INFO,function(){});
										btn.enable();
									}
								},
								failure:function(){
									alertMsg("登录失败！",200,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
									btn.enable();
								}
							});
						}
					}
	    		}
	    	]
		});
		f.render("formdiv");
	});
	</script>
  </head>
  <body bgcolor = "#F0F8FF"> 
  	<div id = "imgdiv" style = "margin-top:100px" align = "center"></div>
  	<div id = "formdiv" style = "margin-top:0px" align = "center"></div>
  	<!-- 标识此页面无需判断用户是否登录 -->
  	<input type = "hidden" id = "loginpage" value = "yes"/>
  	<input type = "hidden" value = "<%=basePath%>" id = "serverpath"/>
  </body>
</html>
