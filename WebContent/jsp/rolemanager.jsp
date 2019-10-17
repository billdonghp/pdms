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
    <title>系统角色维护</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="css.jsp"></jsp:include>
	<jsp:include page="js.jsp"></jsp:include>
	<script type = "text/javascript">
		var grid = null;
		Ext.onReady(function(){
			//角色列表
			var cm = new Ext.grid.ColumnModel([
			new Ext.grid.RowNumberer(),{
				header:"<span style = 'color:#555'>角色ID</span>",dataIndex:"id",renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>角色名称</span>",dataIndex:"rolenm",renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>角色备注</span>",dataIndex:"rolerm",renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				}
			}]);
			var ds = new Ext.data.Store({
				proxy:new Ext.data.HttpProxy({url:"<%=basePath%>systemrole/gar.action"}),
				reader:new Ext.data.JsonReader({
					root:"root"
				},[
					{name:"id"},
					{name:"rolenm"},
					{name:"rolerm"}
				])
			});
			ds.load();
			grid = new Ext.grid.GridPanel({
				ds:ds,
				cm:cm,
				title:"系统角色列表",
				autoHeight:'auto',
				viewConfig:{    
		           forceFit:true    
		        },
				stripeRows:true,
				autoScroll:true,
				frame:true,
				tbar:[{
					text:"新增",
					cls:"x-btn-text-icon",
					icon:"<%=basePath%>images/drop-add.gif",
					handler:function(){//新增角色
						var sltNodes = new Ext.form.Hidden({
							name:"sltNodes"
						});
						var sign = new Ext.form.Hidden({
							name:"sign",	//标识是修改还是新增, 0代表新增, 1代表修改 ;默认为0
							value:0
						});
						var f = new Ext.form.FormPanel({
							autoHeight:true,
							frame:true,
							labelAlign:"right",
							url:"<%=basePath%>systemrole/ar.action",
							method:"post",
							layout:"table",
			     			layoutConfig:{columns:6},
			     			defaults: {   
						        height:20   
						    },  
						    defaultType:'textfield',
						    items:[{
				        		xtype:"label",
				        		cls:"labelCls",
				        		text:"ID:"
				        	},{
					            name:"id",
								allowBlank:false,
								id:"id"
					        },{
				        		xtype:"label",
				        		cls:"labelCls",
				        		text:"角色名称:"
				        	},{
					            name:"rolenm",
								allowBlank:false,
								id:"rolenm"
					        },{
				        		xtype:"label",
				        		cls:"labelCls",
				        		text:"备注:"
				        	},{
					            name:"rolerm",
					            id:"rolerm"
					        },sltNodes,sign]
						});
						var tree = new Ext.tree.TreePanel({
							height:350,
							title:"系统菜单权限",
							loader:new Ext.tree.TreeLoader({dataUrl:"<%=basePath%>systemrole/gatn.action"}),
							rootVisible:false,
							margins:'5 5 5 5',
							frame:true,
							autoScroll:true,
							viewConfig:{
								forceFit:true
							},
							autoScroll:true				
						});	
						var root = new Ext.tree.AsyncTreeNode({expanded:true});
						tree.setRootNode(root);
						tree.expandAll();
						//注册点选事件
						tree.on("checkchange",function(node){
							if(node.leaf){//子节点的情况，如果子节点选中，父节点一定被选中
								if(node.getUI().isChecked()){
									node.parentNode.getUI().toggleCheck(true);
								}
							}
						});
						var window = new Ext.Window({
					        title: "添加系统角色",
					        width: 540,
					        height:485,
					        minWidth: 300,
					        minHeight: 200,
					        layout: 'fit',
					        plain:true,
					        bodyStyle:'padding:5px;',
					        buttonAlign:'center',
					        items:[f,tree],
							modal:true,
							resizable:false,
					        buttons: [{
					            text:"添加",
					            handler:function(){
					            	var nodes = tree.getChecked();
									var bln = false;
									var mids = [];
									for(var i = 0; i < nodes.length; i++){
										if(nodes[i].leaf){
											bln = true;
										}
									}
									if(bln){
										for(var i = 0; i < nodes.length; i++){
											if(nodes[i].leaf){
												mids.push(nodes[i].id);
												//传入父节点
												if($.inArray(nodes[i].parentNode.id, mids) == -1)
													mids.push(nodes[i].parentNode.id);
											}
										}
										//设置菜单ID值
										//alert(mids.join());
										sltNodes.setValue(mids.join());
										f.getForm().submit({
											success:function(){
												alertMsg("添加成功！",200,100,Ext.Msg.OK,Ext.Msg.INFO,function(){
													grid.getStore().reload();
													window.close();
												});
											},failure:function(){
												alertMsg("添加失败！",230,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){b.enable();});
											}
										});
									}else{
										alertMsg("没有选择菜单,请选择！",230,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
									}
					            }
					        },{
					            text:"取消",
					            handler:function(){
					            	window.close();
					            }
					        }]
					    });
					    window.show();
					}}
				],
				listeners:{
					"rowdblclick":function(){//修改角色
						var sm = grid.getSelectionModel();
						var len = grid.getView().getRows().length;
						var ids = [];
						var idx = 0;
						for(var i = 0; i < len; i++){
							if(sm.isSelected(i)){
								ids.push(grid.getStore().getAt(i).get("id"));
								idx = i;
							}
						}						
						var sltNodes = new Ext.form.Hidden({
							name:"sltNodes"
						});
						var sysRoleId = new Ext.form.Hidden({
							name:"id",
							value:grid.getStore().getAt(idx).get("id")
						});
						var sign = new Ext.form.Hidden({
							name:"sign",	//标识是修改还是新增, 0代表新增, 1代表修改 ;默认为0
							value:0
						});
						var f = new Ext.form.FormPanel({
							autoHeight:true,
							frame:true,
							labelAlign:"right",
							url:"<%=basePath%>systemrole/ar.action",
							method:"post",
							layout:"table",
			     			layoutConfig:{columns:6},
			     			defaults: {   
						        height:20   
						    },  
						    defaultType:'textfield',
						    items:[{
				        		xtype:"label",
				        		cls:"labelCls",
				        		text:"ID:"
				        	},{
					            name:"id1",
								allowBlank:false,
								id:"id",
								disabled:true,
								value:grid.getStore().getAt(idx).get("id")
					        },{
				        		xtype:"label",
				        		cls:"labelCls",
				        		text:"角色名称:"
				        	},{
					            name:"rolenm",
								allowBlank:false,
								id:"rolenm",
								value:grid.getStore().getAt(idx).get("rolenm")
					        },{
				        		xtype:"label",
				        		cls:"labelCls",
				        		text:"备注:"
				        	},{
					            name:"rolerm",
					            id:"rolerm",
								value:grid.getStore().getAt(idx).get("rolerm")
					        },sltNodes,sign,sysRoleId]
						});
						var tree = new Ext.tree.TreePanel({
							height:350,
							title:"系统菜单权限",
							loader:new Ext.tree.TreeLoader({dataUrl:"<%=basePath%>systemrole/gatn.action?roleId="+grid.getStore().getAt(idx).get("id")}),
							rootVisible:false,
							margins:'5 5 5 5',
							frame:true,
							autoScroll:true,
							viewConfig:{
								forceFit:true
							},
							autoScroll:true				
						});	
						var root = new Ext.tree.AsyncTreeNode({expanded:true});
						tree.setRootNode(root);
						tree.expandAll();
						//注册点选事件
						tree.on("checkchange",function(node){
							if(node.leaf){//子节点的情况，如果子节点选中，父节点一定被选中
								if(node.getUI().isChecked()){
									node.parentNode.getUI().toggleCheck(true);
								}
							}
						});
						var window = new Ext.Window({
					        title: "修改系统菜单",
					        width: 540,
					        height:485,
					        minWidth: 300,
					        minHeight: 200,
					        layout: 'fit',
					        plain:true,
					        bodyStyle:'padding:5px;',
					        buttonAlign:'center',
					        items:[f,tree],
							modal:true,
							resizable:false,
					        buttons: [{
					            text:"修改",
					            handler:function(){
					            	var nodes = tree.getChecked();
									var bln = false;
									var mids = [];
									for(var i = 0; i < nodes.length; i++){
										if(nodes[i].leaf){
											bln = true;
										}
									}
									if(bln){
										for(var i = 0; i < nodes.length; i++){
											if(nodes[i].leaf){
												mids.push(nodes[i].id);
												//传入父节点
												if($.inArray(nodes[i].parentNode.id, mids) == -1)
													mids.push(nodes[i].parentNode.id);
											}
										}
										//设置菜单ID值
										//alert(mids.join());
										sltNodes.setValue(mids.join());
										sign.setValue(1);//标识修改
										f.getForm().submit({
											success:function(){
												alertMsg("修改成功！",200,100,Ext.Msg.OK,Ext.Msg.INFO,function(){
													grid.getStore().reload();
													window.close();
												});
											},failure:function(){
												alertMsg("修改失败！",230,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){b.enable();});
											}
										});
									}else{
										alertMsg("没有选择菜单,请选择！",230,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
									}
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
			grid.render("rolewholediv");
		});
	</script>
  </head>  
  <body>
  	<div id = "rolewholediv"></div>
  </body>
</html>
