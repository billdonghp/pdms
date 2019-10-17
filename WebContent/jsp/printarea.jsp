<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String code = request.getParameter("code");
	String flag = request.getParameter("flag");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>实施提案</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<jsp:include page="css.jsp"></jsp:include>
<jsp:include page="js.jsp"></jsp:include>
<script type = "text/javascript" src = "<%=basePath%>js/doopro/jQuery.print.js"></script>
<script type="text/javascript">
		$(function(){
			//var flag = "<%=flag%>";
			$.ajax({
				type:"POST",
				url:"<%=basePath%>workplace/area.action",
				data:{code:"<%=code%>"},
				beforeSend:function(){
									
				},
				success:function(r){
					var perInfo = new Ext.form.FormPanel({
						title:"档案信息",
						region:"center",
						autoHeight:'auto',
						autoScroll:true,
						layout:{
							columns:4,
							type: 'table',
							width:'auto'
						},
						defaults:{   
					        height:25,
					        bodyStyle: 'padding:20px',
					        
					    },  
					    defaultType:'textfield',
						    items:[
				           {
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"档案编号:"
						    },{
								name:"code",
								id:"code",
								width:140,
								value:r.pinfo.code
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"状态:"
						    },{
					            name:"sts",
					            id:"sts",
								width:140,
								value:r.pinfo.sts
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"姓名:"
						    },{
								name:"pname",
								id:"pname",
								width:140,
								value:r.pinfo.pname
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"参战情况:"
						    },{
								name: 'combatant',
								id:'combatant',
								width:140,
								value:r.pinfo.combatant
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"性别:"
						    },{
								name: 'sex',
								id:'sex',
								width:140,
								value:r.pinfo.sex
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		width:140,
				        		text:"参加非战争军事行动:"
						    },{
								name: 'nonWar',
								id:'nonWar',
								width:140,
								value:r.pinfo.nonWar
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"出生年月:"
						    },{
					            name:"birthday",
					            id:"birthday",
								width:140,
								value:r.pinfo.birthday
					        },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"身体状况:"
						    },{
								name: 'body',
								id:'body',
								width:140,
								value:r.pinfo.body
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"民族:"
						    },{
								name:"nation",
								id:"nation",
								width:140,
								value:r.pinfo.nation
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"原部队:"
						    },{
								name:"organization",
								id:"organization",
								width:140,
								value:r.pinfo.organization
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"政治面貌:"
						    },{
					            name:"politicsStatus",
					            id:"politicsStatus",
								width:140,
								value:r.pinfo.politicsStatus
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"原档案编号:"
						    },{
								name:"pno",
								id:"pno",
								width:140,
								value:r.pinfo.pno
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"文化程度:"
						    },{
					            name:"culture",
					            id:"culture",
								width:140,
								value:r.pinfo.culture
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"军兵种:"
						    },{
					            name:"troopsType",
					            id:"troopsType",
								width:140,
								value:r.pinfo.troopsType
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"入伍年月:"
						    },{
					            name:"jiDt",
					            id:"jiDt",
								width:140,
								value:r.pinfo.jiDt
					        },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"身份证:"
						    },{
								name:"docNum",
								id:"docNum",
								width:140,
								value:r.pinfo.docNum
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"退伍年月:"
						    },{
					            name:"reDt",
					            id:"reDt",
								width:140,
								value:r.pinfo.reDt
					        },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"联系方式:"
						    },{
								name:"address",
								id:"address",
								width:140,
								value:r.pinfo.address
						    },{
								xtype:"button",
								text:"下载附件",
								icon:"<%=basePath%>images/vtb/save.png",
								handler:function(){
									if($("#filePath").val() == ""){
										alertMsg("没有附件!",260,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
									}else{
										window.open("<%=basePath%>pf/dwldimg.action?fileName="+$("#filePath").val());
									}
								}
							},{
						    	name:"filePath",
						    	id:"filePath",
								width:410,
								colspan:3,
								value:r.pinfo.filePath
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"入伍地:"
						    },{
								name:"jiSite",
								id:"jiSite",
								width:410,
								colspan:3,
								value:r.pinfo.jiSite
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"奖惩情况:"
						    },{
								name:"rp",
								id:"rp",
								width:140,
								value:r.pinfo.rp
						    },{
								name:"remark1",
								id:"remark1",
								colspan:2,
								width:262,
								value:r.pinfo.remark1
						    },{
						    	xtype:"label",
				        		cls:"labelCls",
				        		text:"备注:"
						    },{
								name:"remark2",
								id:"remark2",
								width:410,
								height:50,
								colspan:3,
								value:r.pinfo.remark2
						    }]
					});
					new Ext.Panel({
						tbar:[{xtype:"tbfill"},{
				        	text:"打印",
				        	icon:"<%=basePath%>images/vtb/print.png",
				        	handler:function(){
				        		//$('#f').prepend('<a class="print-preview">Print this page</a>'); 
				        		 $("#printAreaDiv").print({iframe:true,prepend:'<br/>'});  
		  
				        	}
			        	}],
						items:[perInfo],
						autoHeight:'auto'
					}).render("printAreaDiv");
				},
				error:function(){
					alertMsg("数据加载异常!",230,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
				}
			});
	});
	

	</script>
</head>
<body>
	<div id="printAreaDiv"></div>
</body>
</html>