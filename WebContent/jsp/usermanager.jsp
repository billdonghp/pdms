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
    <title>用户信息管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="css.jsp"></jsp:include>
	<jsp:include page="js.jsp"></jsp:include>
	<script type = "text/javascript">
		var grid = null, userId = "", userNm = "", deptnm = "", teamnm = "",uStore = null, userid = "", usernm = "", adeptcd = "", adeptnm = "", dStore = null;
		Ext.onReady(function(){
			var enStore = new Ext.data.Store({
				proxy:new Ext.data.MemoryProxy([[
					0,"停用"
				],[
					1,"在用"
				]]),
				reader:new Ext.data.ArrayReader({},[
					{name:"val"},
					{name:"disp"}
				])
			});
			enStore.load();
			//角色ComboBox
			var rs = new Ext.data.Store({
				proxy:new Ext.data.HttpProxy({url:"<%=basePath%>systemrole/gr.action"}),
				reader:new Ext.data.JsonReader({
					root:"root"
				},[
					{name:"id"},
					{name:"name"}
				])
			});
			rs.load();
			
			//用户列表
			var cm = new Ext.grid.ColumnModel([
			new Ext.grid.RowNumberer(),{
				header:"<span style = 'color:#555'>用户账号</span>",dataIndex:"userId",renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				},sortable:true
			},{
				header:"<span style = 'color:#555'>用户姓名</span>",dataIndex:"userNm",renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				},sortable:true
			},{
				header:"<span style = 'color:#555'>角色名称</span>",dataIndex:"roleNm",renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>角色ID</span>",dataIndex:"roleId",hidden:true,renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				},sortable:true
			},{
				header:"<span style = 'color:#555'>电话号码</span>",dataIndex:"phoneNum",hidden:true,renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>电子邮箱</span>",dataIndex:"email",hidden:true,renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>使用状态</span>",dataIndex:"status",renderer:function(v){
					return "<span style = 'color:#555'>"+(v == 1 ? "<span style='color:green'>在用</span>" : "<span style='color:blue'>停用</span>")+"</span>";
				}
			}]);
			var ds = new Ext.data.Store({
				proxy:new Ext.data.HttpProxy({url:"<%=basePath%>systemuser/gau.action"}),
				reader:new Ext.data.JsonReader({
					root:"root",
					totalProperty:"totalProperty"
				},[
					{name:"userId"},
					{name:"userNm"},
					{name:"roleNm"},
					{name:"roleId"},
					{name:"phoneNum"},
					{name:"email"},
					{name:"status"}
				])
			});
			ds.on("beforeload", function(){//注册基本参数
			   Ext.apply(this.baseParams, {
	      			userId:userId,
			        userNm:userNm
			   });
			});
			ds.load({params:{start:0,limit:30,userId:userId,userNm:userNm}});
			grid = new Ext.grid.GridPanel({
				ds:ds,
				cm:cm,
				title:"系统用户列表",
				height:$(document).height()-100,
				//autoHeight:'auto',
				stripeRows:true,
				autoScroll:true,
				frame:true,
				viewConfig:{
					forceFit:true
				},
				loadMask:"<s:text name = 'dataloading'/>",
				bbar:new Ext.PagingToolbar({
					pageSize:30,
					cls:"color:#555",
					ctCls:"color:#555",
					store:ds,
					displayInfo:true,
					displayMsg:"<span style = 'color:#555'>显示第{0}条到第{1}条记录,总记录:{2}条</span>",
					emptyMsg:"<span style = 'color:#555'>没有记录！</span>"
				}),
				tbar:[{
					text:"新增",
					cls:"x-btn-text-icon",
					icon:"<%=basePath%>images/add16.gif",
					handler:function(){//新增用户
						var basicInfo = new Ext.form.FieldSet({
							title:"<span style='color:#555'>基本信息</span>",
        					layout:"table",
        					frame:true,
        					layoutConfig:{columns:8},
        					defaults: {   
						        height:20   
						    },  
						    defaultType:'textfield',
							items: [{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"登录ID(职号):"
					        	},{
						            name:"userId",
									allowBlank:false,
									id:"userid",
									allowBlank:false
						        },{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"用户姓名:"
					        	},{
						            name:"userNm",
									allowBlank:false,
									id:"username",
									allowBlank:false
						        },{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"登录密码:"
					        	},{
						            name:"userPwd",
									inputType:"password"
						        },{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"电子邮箱:"
					        	},{
						            name:"email",
									vtype:"email",
									allowBlank:false,
									id:"email"
						        },{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"手机号码:"
					        	},{
						            name:"phoneNum",
									regex:/^[0-9]{1,20}$/,
									regexText:"电话号码错误",
									maxLength:11,
									minLength:11
						        },{
						        	xtype:"label",
					        		cls:"labelCls",
					        		text:"系统角色:"
						        },{
						            xtype:"combo",
						            name:"roleId",
									hiddenName:"roleId",//提交表单必须设置此项
									triggerAction:"all",
									store:rs,
									displayField:"name",
									valueField:"id",
									mode:"remote",
									width:120,
									editable:false,
									allowBlank:false
						        },{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"使用状态:"
					        	},{
						            xtype:"combo",
						            name:"status",
									hiddenName:"status",
									triggerAction:"all",
									store:enStore,
									displayField:"disp",
									valueField:"val",
									mode:"local",
									width:80,
									editable:false,
									value:1,
									colspan:5
						        }
					        ]
						});
						
						var f = new Ext.form.FormPanel({
        					layout:"form",
        					frame:true,
					        url:"<%=basePath%>systemuser/ui-add.action",
							method:"post",
					        items: [basicInfo]
					    });
					     var window = new Ext.Window({
					        title: "新增用户",
					        width: 875,
					        height:170,
					        minWidth: 300,
					        minHeight: 200,
					        layout: 'fit',
					        plain:true,
					        bodyStyle:'padding:5px;',
					        buttonAlign:'center',
					        items: f,
							modal:true,
							resizable:false,
					        buttons: [{
					            text:"新增",
					            handler:function(){
					            	f.getForm().submit({
										success:function(f,a){
											if(a.result.info == "ok"){
												alertMsg("添加成功！",200,100,Ext.Msg.OK,Ext.Msg.INFO,function(){
													window.close();
													userId = "";			
													userNm = "";
													ds.load({params:{start:0,limit:30,userId:userId,userNm:userNm,deptnm:deptnm,teamnm:teamnm}});
												});
											}else{
												alertMsg(a.result.info,200,100,Ext.Msg.OK,Ext.Msg.INFO,function(){});
											}
										},
										failure:function(){
											alertMsg("添加失败！",200,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
										}
									});
					            }
					        },{
					            text:"取消",
					            handler:function(){
					            	window.close();
					            }
					        }]
					    });
					    window.show();
					}
				},{
					text:"查找",//查找用户
					cls:"x-btn-text-icon",
					icon:"<%=basePath%>images/chinaz5.png",
					handler:function(){
						var p = new Ext.Panel({
							region:"center",
        					layout:"table",
        					//frame:true,
        					layoutConfig:{columns:8},
        					defaults:{   
						        height:20   
						    },  
						    defaultType:'textfield',
							items: [{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"登录ID(职号):"
					        	},{
						            name:"suserId",
						            id:"suserId"
						        },{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"用户姓名:"
					        	},{
						            name:"suserNm",
									id:"suserNm"
						        },{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"部门名称:"
					        	},{
						            name:"sdeptNm",
									id:"sdeptNm"
						        },{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"班组名称:"
					        	},{
						            name:"steamnm",
									id:"steamnm"
						        }
					        ],
					        tbar:[{xtype:"tbfill"},{
					        	text:"查找",
					        	minWidth:60,
					        	handler:function(){
					        		userId = $.trim(Ext.get("suserId").getValue());				
									userNm = $.trim(Ext.get("suserNm").getValue());	
									ds.load({params:{start:0,limit:30,userId:userId,userNm:userNm}});
									win.close();
					        	}
					        },{
					        	text:"取消",
					            handler:function(){
					            	win.close();
					            }
					        }]
						});
						var win = new Ext.Window({
				            title:"查找用户",
				            closable:true,
				            width:790,
				            height:102,
				            resizable:false,
				            plain:true,
				            modal:true,
				            frame:true,
				            items:[p]
				        });
				        win.show();
					}	
				},{
		        	text:"导出",
		        	cls:"x-btn-text-icon",
					icon:"<%=basePath%>images/exim.png",
		            handler:function(){
		            	window.open("<%=basePath%>systemuser/excel.action?userId="+userId+"&userNm="+userNm+"&deptnm="+deptnm+"&teamnm="+teamnm);
		            }
		        }],
				listeners:{"rowdblclick":function(grid, rowIndex, e){//修改用户
					 	var record = grid.getStore().getAt(rowIndex);
					 	var basicInfo = new Ext.form.FieldSet({
							title:"<span style='color:#555'>基本信息</span>",
        					layout:"table",
        					frame:true,
        					layoutConfig:{columns:8},
        					defaults: {   
						        height:20   
						    },  
						    defaultType:'textfield',
							items: [{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"登录ID(职号):"
					        	},{
						            name:"userId1",
									allowBlank:false,
									value:record.get("userId"),
									disabled:true
						        },{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"用户姓名:"
					        	},{
						            name:"userNm",
									allowBlank:false,
									value:record.get("userNm")
						        },{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"登录密码:"
					        	},{
						            name:"userPwd",
									inputType:"password"
						        },{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"电子邮箱:"
					        	},{
						            name:"email",
									vtype:"email",
									value:record.get("email")
						        },{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"手机号码:"
					        	},{
						            name:"phoneNum",
									regex:/^[0-9]{1,20}$/,
									regexText:"电话号码错误",
									maxLength:11,
									minLength:11,
									value:record.get("phoneNum")
						        },{
						        	xtype:"label",
					        		cls:"labelCls",
					        		text:"系统角色:"
						        },{
						            xtype:"combo",
						            name:"roleId",
									hiddenName:"roleId",//提交表单必须设置此项
									triggerAction:"all",
									store:rs,
									displayField:"name",
									valueField:"id",
									mode:"remote",
									width:120,
									editable:false,
									allowBlank:false,
									value:record.get("roleId")
						        },{
					        		xtype:"label",
					        		cls:"labelCls",
					        		text:"使用状态:"
					        	},{
						            xtype:"combo",
						            name:"status",
									hiddenName:"status",
									triggerAction:"all",
									store:enStore,
									displayField:"disp",
									valueField:"val",
									mode:"local",
									width:80,
									editable:false,
									value:record.get("status"),
									colspan:5
						        },{
						        	xtype:"hidden",
						            name:"userId",
									allowBlank:false,
									value:record.get("userId")
						        }
					        ]
						});
						
						var f = new Ext.form.FormPanel({
        					layout:"form",
        					frame:true,
					        url:"<%=basePath%>systemuser/ui-update.action",
							method:"post",
					        items: [basicInfo]
					    });
					    var window = new Ext.Window({
					        title: "修改用户",
					        width: 875,
					        height:190,
					        minWidth: 300,
					        minHeight: 200,
					        layout: 'fit',
					        plain:true,
					        bodyStyle:'padding:5px;',
					        buttonAlign:'center',
					        items: [f],
							modal:true,
							resizable:false,
					        buttons: [{
					            text:"修改",
					            handler:function(){
					            	f.getForm().submit({
										success:function(f,a){
											if(a.result.info == "ok"){
												alertMsg("修改成功！",200,100,Ext.Msg.OK,Ext.Msg.INFO,function(){
													window.close();
													userId = "";			
													userNm = "";
													deptnm = "";
													teamnm = "";
													ds.load({params:{start:0,limit:30,userId:userId,userNm:userNm,deptnm:deptnm,teamnm:teamnm}});
												});
											}else{
												alertMsg(a.result.info,200,100,Ext.Msg.OK,Ext.Msg.INFO,function(){});
											}
										},
										failure:function(){
											alertMsg("修改失败！",200,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
										}
									});
					            }
					        },{
					            text:"取消",
					            handler:function(){
					            	window.close();
					            }
					        }]
					    });
					    window.show();
					 }
				}	
			});
			grid.render("userinfodiv");
		});
	
	</script>
  </head>  
  <body>
  	<div id = "userinfodiv"></div>
  	<input type ="hidden" id = "bgval" value = "<s:property value = "%{#session.userbg}"/>"/>
  </body>
</html>
