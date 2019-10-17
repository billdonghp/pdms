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
    <title>WORK LIST</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="css.jsp"></jsp:include>
	<jsp:include page="js.jsp"></jsp:include>
	<script type = "text/javascript" src = "<%=basePath%>js/doopro/jQuery.print.js"></script>
	<script type = "text/javascript">		
		var proGrid = null, uStore = null,tStore = null, userid = "", usernm = "",tuserid = "", tusernm = "";
		Ext.onReady(function(){
			
			//录入&进行中提案
			var sm = new Ext.grid.CheckboxSelectionModel();
			var proCm = new Ext.grid.ColumnModel([
			new Ext.grid.RowNumberer(),
			sm,{
				header:"<span style = 'color:#555'>档案编号</span>",dataIndex:"code",renderer:function(v){
					return "<span style = 'color:#555'>"+v+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>姓名</span>",dataIndex:"pname",renderer:function(v){
					return "<span style = 'color:#555'>"+(v == null ? "" : v)+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>性别</span>",dataIndex:"sex",renderer:function(v){
					return "<span style = 'color:#555'>"+(v == 1 ? "男" : "女")+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>出生年月</span>",dataIndex:"birthday",renderer:function(v){
					return "<span style = 'color:#555'>"+(v == null ? "" : v)+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>民族</span>",dataIndex:"nation",renderer:function(v){
					return "<span style = 'color:#555'>"+(v == null ? "" : v)+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>政治面貌</span>",dataIndex:"politicsStatus",renderer:function(v){
					return "<span style = 'color:#555'>"+(v == null ? "" : v)+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>文化程度</span>",dataIndex:"culture",renderer:function(v){
					return "<span style = 'color:#555'>"+(v == null ? "" : v)+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>入伍年月</span>",dataIndex:"jiDt",renderer:function(v){
					return "<span style = 'color:#555'>"+(v == null ? "" : v)+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>退伍年月</span>",dataIndex:"reDt",renderer:function(v){
					return "<span style = 'color:#555'>"+(v == null ? "" : v)+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>状态</span>",dataIndex:"sts",renderer:function(v){
					switch(v)
					{
					case "1":
						return "<span style = 'color:#555'>"+("初始状态")+"</span>";
					  break;
					case "2":
						return "<span style = 'color:#555'>"+("待提交")+"</span>";
					  break;
					case "3":
						return "<span style = 'color:#555'>"+("待审批")+"</span>";
					  break;
					case "4":
						return "<span style = 'color:#555'>"+("审批完成")+"</span>";
					  break;
					default:
						return "<span style = 'color:#555'>"+("已删除")+"</span>";
					  break;
					}
					
				}
			}
			,{
				header:"<span style = 'color:#555'>创建日期</span>",dataIndex:"crDt",renderer:function(v){
					return "<span style = 'color:#555'>"+(v == null ? "" : v)+"</span>";
				}
			},{
				header:"<span style = 'color:#555'>修改日期</span>",dataIndex:"uptDt",renderer:function(v){
					return "<span style = 'color:#555'>"+(v == null ? "" : v)+"</span>";
				}
			}]);
			var proDs = new Ext.data.Store({
				proxy:new Ext.data.HttpProxy({url:"<%=basePath%>preg/query.action"}),
				reader:new Ext.data.JsonReader({
					root:"root",
					totalProperty:"totalProperty"
				},[
					{name:"code"},
					{name:"pname"},
					{name:"sex"},
					{name:"birthday"},
					{name:"nation"},
					{name:"politicsStatus"},
					{name:"culture"},
					{name:"jiDt"},
					{name:"reDt"},
					{name:"sts"},
					{name:"crDt"},
					{name:"uptDt"}
				])
			});
			proDs.load({params:{start:0,limit:20,sts:"3,4"}});
			proGrid = new Ext.grid.GridPanel({
				tbar:[{
						xtype:"label",
						cls:"labelCls2",
						text:"待审批档案"
					},{xtype:"tbfill"},{
						text:"审批",
						cls:"x-btn-text-icon",
						icon:"<%=basePath%>images/vtb/approval.png",
						handler:function(){
							var cdm = proGrid.getSelectionModel();
							var len = proGrid.getView().getRows().length;
							var codes = [];
							for(var i = 0; i < len; i++)
								if(cdm.isSelected(i))
									codes.push(proGrid.getStore().getAt(i).get("code"));
							if(codes.join() == ""){
								alertMsg("请选择要审批的项！",230,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
								return;
							}else{
								alertMsg("确定要审批吗？",260,100,Ext.Msg.YESNO,Ext.Msg.INFO,function(btn){
									if(btn == "yes"){
										$.ajax({
											url:"<%=basePath%>workplace/approval.action",
											type:"post",
											dataType:"json",
											timeout:5000,
											data:{code:codes.join(),sign:"Approval"},	
											beforeSend:function(){
												//Dialog.show("正在审批...",true);
											},				
											success:function(r){
												//Dialog.close();
												alertMsg("成功审批所选的全部项目！",280,100,Ext.Msg.OK,Ext.Msg.INFO,function(){
													proGrid.getStore().reload();
												});
											},
											error:function(){
												Dialog.close();
												alertMsg("审批失败,请联系管理员！",230,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
											}
										});
									}					
								});
							}
						}
					},{
						text:"取消审批",
						cls:"x-btn-text-icon",
						icon:"<%=basePath%>images/vtb/undo.png",
						handler:function(){
							var cdm = proGrid.getSelectionModel();
							var len = proGrid.getView().getRows().length;
							var codes = [];
							for(var i = 0; i < len; i++)
								if(cdm.isSelected(i))
									codes.push(proGrid.getStore().getAt(i).get("code"));
							if(codes.join() == ""){
								alertMsg("请选择要取消审批的项！",230,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
								return;
							}else{
								alertMsg("确定要取消审批吗？",260,100,Ext.Msg.YESNO,Ext.Msg.INFO,function(btn){
									if(btn == "yes"){
										//执行删除
										$.ajax({
											url:"<%=basePath%>workplace/approval.action",
											type:"post",
											dataType:"json",
											timeout:5000,
											data:{code:codes.join(),sign:"unApproval"},	
											beforeSend:function(){
												//Dialog.show("正在取消审批...",true);
											},				
											success:function(r){
												//Dialog.close();
												alertMsg("成功取消审批所选的全部项目！",280,100,Ext.Msg.OK,Ext.Msg.INFO,function(){
													proGrid.getStore().reload();
												});
											},
											error:function(){
												Dialog.close();
												alertMsg("取消审批失败,请联系管理员！",230,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){});
											}
										});
									}					
								});
							}
						}
					}    
				],
				ds:proDs,
				cm:proCm,
				sm:sm,
				id:"proGrid",
				height:650,
				viewConfig:{    
		           forceFit:true    
		        },
				stripeRows:true,
				autoScroll:true,
				frame:true,
				viewConfig:{
					forceFit:true
				},
				loadMask:"<s:text name = 'dataloading'/>",
				bbar:new Ext.PagingToolbar({
					pageSize:5,
					cls:"color:#555",
					ctCls:"color:#555",
					store:proDs,
					displayInfo:true,
					displayMsg:"<span style = 'color:#555'>显示第{0}条到第{1}条记录,总记录:{2}条</span>",
					emptyMsg:"<span style = 'color:#555'>没有记录！</span>"
				}),
				listeners:{
					"rowdblclick":function(grid, rowIndex, e){//修改/提交评价提案
						var record = grid.getStore().getAt(rowIndex);
						var title = "", url = "", w = 0, h = 0;
						title = "档案信息预览";
						url = "jsp/printarea.jsp?code="+record.get("code");
						w = 640;
						h = 640;
						
						var wd = new Ext.Window({
							title:title,
					        width:w,
					        height:h,
					        layout:'fit',
					        plain:true,
					        buttonAlign:'center',
					        id:"uptProWin",
					        items:[{
					        	xtype:"panel",
					        	bodyBorder:false,
					    		border:false,
					    		frame:true,
					    		defaults:{autoScroll:true},
					    		enableTabScroll:true,
					    		items:[{
					    				autoLoad:{url:url,scripts:true},
					    				autoHeight:true
					    			}
					    		]
					        }],
					        resizable:false,
							modal:true										
					    });
					    wd.show();
					}
				}
			});
			proGrid.render("prolistdiv");
			
   			
   			//控制显隐
   			$.ajax({
   				type:"post",
   				url:"<%=basePath%>workplace/userrole.action",
   				success:function(r){
   					if(r.role == "RA"){
   		   				$("#prolistdiv").hide();//隐藏评价面板
   		   			}
   				}
   			});
		});
	
	
	</script>
  </head>
  <body> 
  	<!-- 提案录入&进行清单 -->
  	<div id = "prolistdiv"  style = "margin:10px 10px 0px 10px;"></div>
  	<input type = "hidden" id = "roleid" value = "<s:property value = '%{#session.userrole}'/>"/>
  </body>
</html>