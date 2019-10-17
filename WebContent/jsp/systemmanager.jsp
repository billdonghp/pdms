<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="css.jsp"></jsp:include>
	<jsp:include page="js.jsp"></jsp:include>
	<script type = "text/javascript">
		Ext.onReady(function(){
			//菜单管理
			var cm = new Ext.grid.ColumnModel([
			new Ext.grid.RowNumberer(),{
				header:"<span style = 'color:#555'>ID</span>",dataIndex:"id",renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>名称</span>",dataIndex:"text",renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>提示</span>",dataIndex:"qtip",renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>链接地址</span>",dataIndex:"url",renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>子菜单</span>",dataIndex:"isLeaf",renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>父菜单名称</span>",dataIndex:"pmName",renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>在用</span>",dataIndex:"beUsed",renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				}
			}]);
			var ds = new Ext.data.Store({
				proxy:new Ext.data.HttpProxy({url:"<%=basePath%>systemmgt/gam.action"}),
				reader:new Ext.data.JsonReader({
					root:"root"
				},[
					{name:"id"},
					{name:"text"},
					{name:"qtip"},
					{name:"url"},
					{name:"isLeaf"},
					{name:"pmName"},
					{name:"beUsed"}
				])
			});
			ds.load();
			var grid = new Ext.grid.GridPanel({
				ds:ds,
				cm:cm,
				title:"系统功能菜单管理",
				autoHeight:true,
				stripeRows:true,
				viewConfig:{
					forceFit:true
				},
				bbar:[{xtype:"tbfill"},{
					text:"添加",
					cls:"x-btn-text-icon",
					icon:"<%=basePath%>images/drop-add.gif",
					handler:function(){//新增菜单
						var leafStore = new Ext.data.Store({
							proxy:new Ext.data.MemoryProxy([[
								0,"是"
							],[
								1,"否"
							]]),
							reader:new Ext.data.ArrayReader({},[
								{name:"lval"},
								{name:"ldisp"}
							])
						});
						leafStore.load();
						var enStore = new Ext.data.Store({
							proxy:new Ext.data.MemoryProxy([[
								0,"否"
							],[
								1,"是"
							]]),
							reader:new Ext.data.ArrayReader({},[
								{name:"val"},
								{name:"disp"}
							])
						});
						enStore.load();
						var pmStore = new Ext.data.Store({
							proxy:new Ext.data.HttpProxy({url:"<%=basePath%>systemmgt/gap.action"}),
							reader:new Ext.data.JsonReader({
								root:"root"
							},[
								{name:"id"},
								{name:"name"}
							])
						});
						pmStore.load();
						var pM = new Ext.form.ComboBox({
							name:"parentId",
							hiddenName:"parentId",//提交表单必须设置此项
							triggerAction:"all",
							store:pmStore,
							displayField:"name",
							valueField:"id",
							mode:"remote",
							width:120,
							editable:false,
							disabled:true,
							fieldLabel:"<span style = 'color:#555'>父菜单</span>"
						}); 
						var f = new Ext.form.FormPanel({
							//layout:"table",
							frame:true,
							url:$("#serverpath").val()+"systemmgt/optmenu-add.action",
							method:"POST",
							width:530,
							bodyStyle:"padding:5px;",
							labelAlign:"right",
						    defaultType:'textfield',
							autoHeight:true,
							items: [{
						    		name:"text",
									allowBlank:false,
									fieldLabel:"<span style = 'color:#555'>名称</span>"	
						    	},{
						    		name:"qtip",
									allowBlank:false,
									fieldLabel:"<span style = 'color:#555'>提示</span>"	
						    	},{
						    		name:"url",
									allowBlank:false,
									value:"null",
									fieldLabel:"<span style = 'color:#555'>链接地址</span>"		
						    	},{
						    		xtype:"combo",
						    		name:"leaf",
									hiddenName:"leaf",
									triggerAction:"all",
									store:leafStore,
									displayField:"ldisp",
									valueField:"lval",
									mode:"local",
									width:80,
									editable:false,
									fieldLabel:"<span style = 'color:#555'>子菜单</span>",
									listeners:{"select":function(c){
										if(c.getValue() == 0)
											pM.enable();
										else
											pM.disable();
									}}
						    	},pM,{
						    		xtype:"combo",
						    		name:"enabled",
									hiddenName:"enabled",
									triggerAction:"all",
									store:enStore,
									displayField:"disp",
									valueField:"val",
									mode:"local",
									width:80,
									editable:false,
									fieldLabel:"<span style = 'color:#555'>在用</span>"
						    	}
					    	]
						});
						var w = new Ext.Window({
							title:"添加菜单",
							width:556,
							height:276,
							items:[
								f
							],
							autoScroll:true,
							modal:true,
							resizable:false,
							bodyStyle:"padding:5px",
					    	buttons:[{
								text:"添加",
								handler:function(){
									f.getForm().submit({
										success:function(f,a){
												alertMsg("添加成功！",200,100,Ext.Msg.OK,Ext.Msg.INFO,function(){
												w.close();
												grid.getStore().reload();
											});
										},
										failure:function(){
											alertMsg("添加失败！",200,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
										}
									});
								}
							},{
								text:"取消",
								handler:function(){
									w.close();
								}
							}],
							buttonAlign:"center"
						});
						w.show();
					}
				},{
					text:"删除",
					cls:"x-btn-text-icon",
					icon:"<%=basePath%>images/drop-no.gif",
					handler:function(){//删除菜单
						var systemmgt = grid.getSelectionModel();
						var len = grid.getView().getRows().length;
						var ids = [];
						for(var i = 0; i < len; i++)
							if(systemmgt.isSelected(i))
								ids.push(grid.getStore().getAt(i).get("id"));
						if(ids.join() == ""){
							alertMsg("请选择要删除的项！",230,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
							return;
						}else{
							alertMsg("确定要删除吗？",260,100,Ext.Msg.YESNO,Ext.Msg.INFO,function(btn){
								if(btn == "yes"){
									//执行删除
									$.ajax({
										url:"<%=basePath%>systemmgt/optmenu-delete.action",
										type:"post",
										dataType:"json",
										data:{ids:ids.join()},	
										beforeSend:function(){
											Dialog.show("正在删除...",true);
										},				
										success:function(r){
											Dialog.close();
											alertMsg("成功删除！",230,100,Ext.Msg.OK,Ext.Msg.INFO,function(){
												grid.getStore().reload();
											});
										},
										error:function(){
											Dialog.close();
											alertMsg("删除失败,请联系管理员！",230,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
										}
									});
								}					
							});
						}
					}
				}],
				listeners:{"rowdblclick":function(grid, rowIndex, e){//双击修改菜单
					var record = grid.getStore().getAt(rowIndex);
					var b = record.get("isLeaf")=="是"?true:false;//是否是子菜单
					var leafStore = new Ext.data.Store({
							proxy:new Ext.data.MemoryProxy([[
								0,"是"
							],[
								1,"否"
							]]),
							reader:new Ext.data.ArrayReader({},[
								{name:"lval"},
								{name:"ldisp"}
							])
						});
						leafStore.load();
						var enStore = new Ext.data.Store({
							proxy:new Ext.data.MemoryProxy([[
								0,"否"
							],[
								1,"是"
							]]),
							reader:new Ext.data.ArrayReader({},[
								{name:"val"},
								{name:"disp"}
							])
						});
						enStore.load();
						var pmStore = new Ext.data.Store({
							proxy:new Ext.data.HttpProxy({url:"<%=basePath%>systemmgt/gap.action"}),
							reader:new Ext.data.JsonReader({
								root:"root"
							},[
								{name:"id"},
								{name:"name"}
							])
						});
						pmStore.load();
						var pM = new Ext.form.ComboBox({
							name:"parentId",
							hiddenName:"parentId",//提交表单必须设置此项
							triggerAction:"all",
							store:pmStore,
							displayField:"name",
							valueField:"id",
							mode:"remote",
							width:120,
							editable:false,
							disabled:true,
							disabled:b?false:true,//当前是子菜单时可用
							fieldLabel:"<span style = 'color:#555'>父菜单</span>"
						}); 
						var f = new Ext.form.FormPanel({
							//layout:"table",
							frame:true,
							url:$("#serverpath").val()+"systemmgt/optmenu-update.action",
							method:"post",
							width:530,
							bodyStyle:"padding:5px;",
							labelAlign:"right",
						    defaultType:'textfield',
							autoHeight:true,
							items: [{
						    		name:"text",
									fieldLabel:"<span style = 'color:#555'>名称</span>"	
						    	},{
						    		name:"qtip",
									fieldLabel:"<span style = 'color:#555'>提示</span>"	
						    	},{
						    		name:"url",
									allowBlank:false,
									value:"null",
									disabled:b?false:true,//当前是子菜单时可用
									value:record.get("url"),
									fieldLabel:"<span style = 'color:#555'>链接地址</span>"		
						    	},{
						    		xtype:"combo",
						    		name:"leaf",
									hiddenName:"leaf",
									triggerAction:"all",
									store:leafStore,
									displayField:"ldisp",
									valueField:"lval",
									mode:"local",
									width:80,
									editable:false,
									disabled:b?false:true,//当前是子菜单时可用
									fieldLabel:"<span style = 'color:#555'>子菜单</span>",
									listeners:{"select":function(c){
										if(c.getValue() == 0)
											pM.enable();
										else
											pM.disable();
									}}
						    	},pM,{
						    		xtype:"combo",
						    		name:"enabled",
									hiddenName:"enabled",
									triggerAction:"all",
									store:enStore,
									displayField:"disp",
									valueField:"val",
									mode:"local",
									width:80,
									editable:false,
									value:1,
									fieldLabel:"<span style = 'color:#555'>在用</span>"
						    	},{
						    		xtype:"hidden",
						    		name:"id",
						    		value:record.get("id")
						    	}
					    	]
						});
						var w = new Ext.Window({
							title:"修改菜单",
							width:556,
							height:276,
							items:[
								f
							],
							autoScroll:true,
							modal:true,
							bodyStyle:"padding:5px",
							resizable:false,
					    	buttons:[{
								text:"修改",
								handler:function(){
									f.getForm().submit({
										success:function(f,a){
												alertMsg("修改成功！",200,100,Ext.Msg.OK,Ext.Msg.INFO,function(){
												w.close();
												grid.getStore().reload();
											});
										},
										failure:function(){
											alertMsg("修改失败！",200,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
										}
									});
								}
							},{
								text:"取消",
								handler:function(){
									w.close();
								}
							}],
							buttonAlign:"center"
						});
						w.show();
				}}
			});
			grid.render("menudiv");
			//系统下拉表维护
			var comcm = new Ext.grid.ColumnModel([
  			new Ext.grid.RowNumberer(),{
  				header:"<span style = 'color:#555'>ID</span>",dataIndex:"id",renderer:function(v){
  					return "<span style = 'color:#555'>"+v+"</span>";
  				}
  			},{
  				header:"<span style = 'color:#555'>COMBOX代码</span>",dataIndex:"code",renderer:function(v){
  					return "<span style = 'color:#555'>"+v+"</span>";
  				}
  			},{
  				header:"<span style = 'color:#555'>COMBOX值</span>",dataIndex:"comvalue",renderer:function(v){
  					return "<span style = 'color:#555'>"+v+"</span>";
  				}
  			},{
  				header:"<span style = 'color:#555'>COMBOX显示值</span>",dataIndex:"comview",renderer:function(v){
  					return "<span style = 'color:#555'>"+v+"</span>";
  				}
  			}]);
  			var comds = new Ext.data.Store({
  				proxy:new Ext.data.HttpProxy({url:"<%=basePath%>combox/gacombox.action"}),
  				reader:new Ext.data.JsonReader({
  					root:"root"
  				},[
  					{name:"id"},
  					{name:"code"},
  					{name:"comvalue"},
  					{name:"comview"}
  				])
  			});
  			comds.load();
  			var comgrid = new Ext.grid.GridPanel({
  				ds:comds,
  				cm:comcm,
  				title:"系统下拉列表信息管理",
  				autoHeight:true,
  				stripeRows:true,
  				viewConfig:{
  					forceFit:true
  				},
  				bbar:[{xtype:"tbfill"},{
  					text:"添加",
  					cls:"x-btn-text-icon",
  					icon:"<%=basePath%>images/drop-add.gif",
  					handler:function(){//新增下拉列表
  						var f = new Ext.form.FormPanel({
  							frame:true,
  							url:$("#serverpath").val()+"combox/comboxopt-add.action",
  							method:"post",
  							width:530,
  							bodyStyle:"padding:5px;",
  							labelAlign:"right",
  						    defaultType:'textfield',
  							autoHeight:true,
  							items: [{
  						    		name:"code",
  									allowBlank:false,
  									fieldLabel:"<span style = 'color:#555'>CODE</span>"	
  						    	},{
  						    		name:"comvalue",
  									allowBlank:false,
  									fieldLabel:"<span style = 'color:#555'>COMBOX值</span>"	
  						    	},{
  						    		name:"comview",
  									allowBlank:false,
  									fieldLabel:"<span style = 'color:#555'>COMBOX显示值</span>"		
  						    	}
  					    	]
  						});
  						var w = new Ext.Window({
  							title:"添加下拉列表值",
  							width:556,
  							height:190,
  							items:[
  								f
  							],
  							autoScroll:true,
  							modal:true,
  							bodyStyle:"padding:5px",
  							resizable:false,
  					    	buttons:[{
  								text:"添加",
  								handler:function(){
  									f.getForm().submit({
  										success:function(f,a){
  											if(a.result.rlt == "ok"){
												alertMsg("添加成功",200,100,Ext.Msg.OK,Ext.Msg.INFO,function(){
													w.close();
	  												comgrid.getStore().reload();
  	  											});
  											}else{
  												alertMsg(a.result.rlt,200,100,Ext.Msg.OK,Ext.Msg.INFO,function(){
  													
  	  											});
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
  									w.close();
  								}
  							}],
  							buttonAlign:"center"
  						});
  						w.show();
  					}}],
  					listeners:{"rowdblclick":function(grid, rowIndex, e){//双击修改下拉列表信息
  						var record = grid.getStore().getAt(rowIndex);
  						var f = new Ext.form.FormPanel({
  							//layout:"table",
  							frame:true,
  							url:$("#serverpath").val()+"combox/comboxopt-update.action",
  							method:"post",
  							width:530,
  							bodyStyle:"padding:5px;",
  							labelAlign:"right",
  						    defaultType:'textfield',
  							autoHeight:true,
  							items: [{
  									xtype:"hidden",
  									name:"id",
  									value:record.get("id")
  								},{
  						    		name:"code",
  									allowBlank:false,
  									fieldLabel:"<span style = 'color:#555'>CODE</span>",
  									value:record.get("code")
  						    	},{
  						    		name:"comvalue",
  									allowBlank:false,
  									fieldLabel:"<span style = 'color:#555'>COMBOX值</span>"	,
  									value:record.get("comvalue")
  						    	},{
  						    		name:"comview",
  									allowBlank:false,
  									fieldLabel:"<span style = 'color:#555'>COMBOX显示值</span>"	,
  									value:record.get("comview")	
  						    	}
  					    	]
  						});
  						var w = new Ext.Window({
  							title:"修改下拉列表",
  							width:556,
  							height:190,
  							items:[
  								f
  							],
  							autoScroll:true,
  							modal:true,
  							bodyStyle:"padding:5px",
  							resizable:false,
  					    	buttons:[{
  								text:"修改",
  								handler:function(){
  									f.getForm().submit({
  										success:function(f,a){
  												alertMsg("修改成功！",200,100,Ext.Msg.OK,Ext.Msg.INFO,function(){
  												w.close();
  												comgrid.getStore().reload();
  											});
  										},
  										failure:function(){
  											alertMsg("修改失败！",200,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
  										}
  									});
  								}
  							},{
  								text:"取消",
  								handler:function(){
  									w.close();
  								}
  							}],
  							buttonAlign:"center"
  						});
  						w.show();
  					}}
  			});
  			comgrid.render("comdiv");
		});
	</script>
  </head>
  <body>
    <div id = "menudiv" style = "padding:10px"></div>
    <div id = "comdiv" style = "padding:10px"></div>
  </body>
</html>
