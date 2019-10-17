<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>档案录入</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="css.jsp"></jsp:include>
<jsp:include page="js.jsp"></jsp:include>
<script type="text/javascript"
	src="<%=basePath%>js/doopro/ComboBoxTree.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/doopro/jQuery.print.js"></script>
<script type="text/javascript">
	var cm= null, grid = null, uStore = null, userid = "", usernm = "";
	Ext.onReady(function(){
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'side';
		//表单
		var f = new Ext.form.FormPanel({
				//url:"<%=basePath%>preg/do-saveOrUpdate.action", 
				method:"post",
				tbar:[{
					xtype:"label",
	        		cls:"labelCls",
	        		text:"档案查询"
	       		},{
		        	xtype:"tbfill"
		        	},{
		        	text:"查询",
		        	icon:"<%=basePath%>images/vtb/search.png",
		        	handler:function(){
		        		ds.load({params:{start:0,limit:20,sts:"4"}});
	  
			        	}
		        },{
		        	text:"重置",
		        	icon:"<%=basePath%>images/vtb/reset.png",
		        	handler:function(){
		        		f.getForm().reset();
			        }
		        }],
				region:"center",
				autoHeight:'auto',
				autoScroll:true,
				layout:{
					columns:8,
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
					width:140
			    },{
			    	xtype:"label",
	        		cls:"labelCls",
	        		width:80,
	        		text:"姓名:"
			    },{
					name:"pname",
					id:"pname",
					width:140
			    },{
			    	xtype:"label",
	        		cls:"labelCls",
	        		width:80,
	        		text:"性别:"
			    },{
					xtype:"radiogroup",
					name: 'sex',
					id:'sex',
					width:140,
					items:[     
	                    {boxLabel:"男", name: 'sex',inputValue : '1'},
	                    {boxLabel:"女", name: 'sex',inputValue : '2'}
	                ]
			    },{
			    	xtype:"label",
	        		cls:"labelCls",
	        		width:80,
	        		text:"身份证:"
			    },{
					name:"docNum",
					id:"docNum",
					width:140
			    },{
			    	xtype:"label",
	        		cls:"labelCls",
	        		width:80,
	        		text:"原档案编号:"
			    },{
					name:"pno",
					id:"pno",
					width:140
			    },{
			    	xtype:"label",
	        		cls:"labelCls",
	        		text:"军兵种:"
			    },{
			    	xtype:"combo",
		            name:"troopsType",
		            id:"troopsType",
					hiddenName:"troopsType",//提交表单必须设置此项
					triggerAction:"all",
					store:getComboxDs("TOPT",(" and comvalue <> 0 ")),
					displayField:"comview",
					valueField:"comvalue",
					mode:"remote",
					editable:false,
					width:140
			    },{
			    	xtype:"label",
	        		cls:"labelCls",
	        		width:80,
	        		text:"入伍地:"
			    },{
					name:"jiSite",
					id:"jiSite",
					listeners:{"focus":function(){
						getJiSite();
					}},
					width:140
			    }]
		    });
			//档案列表
			cm = new Ext.grid.ColumnModel([
				new Ext.grid.RowNumberer(),
				{
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
				}
			]);
			var ds = new Ext.data.Store({
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
			ds.on("beforeload", function(){//注册基本参数
			   Ext.apply(this.baseParams, {
			       sts:"4",
			       code:$("#code").val(),
				   pname:$("#pname").val(),
				   sex:Ext.getCmp("sex").getValue() != null?Ext.getCmp("sex").getValue().inputValue:"0",
				   docNum:$("#docNum").val(),
				   pno:$("#pno").val(),
				   troopsType:Ext.getCmp("troopsType").getValue(),
				   jiSite:$("#jiSite").val()
			   });
			});
			ds.load({params:{start:0,limit:20,sts:"4"}});
			grid = new Ext.grid.GridPanel({
				ds:ds,
				cm:cm,
				height:$(document).height()-180,
				viewConfig:{    
		           forceFit:true   
		        },
				stripeRows:true,
				autoScroll:true,
				frame:true,
				loadMask:"<s:text name = 'dataloading'/>",
				bbar:new Ext.PagingToolbar({
					pageSize:20,
					cls:"color:#555",
					ctCls:"color:#555",
					store:ds,
					displayInfo:true,
					displayMsg:"<span style = 'color:#555'>显示第{0}条到第{1}条记录,总记录:{2}条</span>",
					emptyMsg:"<span style = 'color:#555'>没有记录！</span>"
				}),
				listeners:{
					"rowdblclick":function(grid, rowIndex, e){
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
			new Ext.Panel({
				items:[f,grid],
				autoHeight:'auto'
			}).render("infoquery");
		});
	/*
	上传课题图片
	pathId:表示要设置的路径值
	fileId:表示要设置的图片src属性，用于显示上传的图片
	sign:上传文件标识('I'表示上传图片;'F'表示上传附件)
	*/
	function updloadTkImage(pathId, fileId, sign){
		var imgf = new Ext.form.FormPanel({
			frame:true,
	        url:"<%=basePath%>pf/upldimg.action",
			method : "post",
			fileUpload : true,
			layout : "table",
			layoutConfig : {
				columns : 1
			},
			defaults : {
				height : 20
			},
			defaultType : 'textfield',
			items : [ {
				inputType : "file",
				name : "upload",
				width : 360,
				allowBlank : false
			} ]
		});
		var wdw = new Ext.Window(
				{
					title : sign == "I" ? "上传图片" : "上传文件",
					width : 420,
					height : 120,
					minWidth : 300,
					minHeight : 200,
					layout : 'fit',
					plain : true,
					bodyStyle : 'padding:5px;',
					buttonAlign : 'center',
					items : imgf,
					modal : true,
					resizable : false,
					buttons : [
							{
								text : "上传",
								handler : function() {
									imgf.getForm().submit({
									success : function(f, a) {
										$("#" + pathId).val("personfile/"+ a.result.uploadFileName);
										alertMsg("上传成功！",200,100,Ext.Msg.OK,Ext.Msg.INFO,function() {
												wdw.close();
												if (sign == "I")
												$("#"+ fileId).attr("src","personfile/"+ a.result.uploadFileName);
																	});
														},
									failure : function() {
										alertMsg("上传失败！",200,100,Ext.Msg.OK,Ext.Msg.WARNING,function() {
																	});
														}
													});
								}
							}, {
								text : "取消",
								handler : function() {
									wdw.close();
								}
							} ]
				});
		wdw.show();
	};

	/**
	 *获取入伍地信息
	 */

	function getJiSite() {

		var deptCm = new Ext.grid.ColumnModel([ {
			header : "<span style = 'color:#555'>代码</span>",
			dataIndex : "code",
			renderer : function(v) {
				return "<span style = 'color:#555'>" + v + "</span>";
			},
			align : "center",
			menuDisabled : true
		}, {
			header : "<span style = 'color:#555'>入伍地</span>",
			dataIndex : "jiSite",
			renderer : function(v) {
				return "<span style = 'color:#555'>" + v + "</span>";
			},
			align : "center",
			menuDisabled : true
		} ]);
		var data = [ [ "A1", "海港镇" ], [ "A2", "东港镇" ], [ "A3", "西港镇" ],
				[ "A4", "北港镇" ], [ "A5", "海阳镇" ], [ "A6", "杜庄镇" ],
				[ "A7", "石门寨镇" ], [ "A8", "驻操营镇" ], [ "A9", "经济开发区" ],
				[ "A10", "临港物流园区" ], [ "A11", "文化路街道办事处" ],
				[ "A12", "建设路街道办事处" ], [ "A13", "燕山大街街道办事处" ],
				[ "A14", "港城大街街道办事处" ], [ "A15", "西港路街道办事处" ],
				[ "A16", "海滨路街道办事处" ], [ "A17", "河东街道办事处" ],
				[ "A18", "白塔岭街道办事处" ], [ "A19", "东环路街道办事处" ],
				[ "A20", "北环路街道办事处" ]

		];
		var uStore = new Ext.data.Store({
			proxy : new Ext.data.MemoryProxy(data),

			reader : new Ext.data.ArrayReader({}, [ {
				name : "code"
			}, {
				name : "jiSite"
			} ])
		});
		uStore.load();
		var deptGrid = new Ext.grid.GridPanel({
			region : "center",
			ds : uStore,
			cm : deptCm,
			height : 260,
			stripeRows : true,
			autoScroll : true,
			frame : true,
			viewConfig : {
				forceFit : true
			},
			loadMask : "数据加载中...",
			bbar : new Ext.PagingToolbar({
				pageSize : 10,
				cls : "color:#555",
				ctCls : "color:#555",
				store : uStore,
				displayInfo : true
			}),
			listeners : {
				"rowdblclick" : function(grid, rowIndex, e) {
					var record = grid.getStore().getAt(rowIndex);
					$("#jiSite").val(record.get("jiSite"));
					dwin.close();
				}
			}
		});
		var dwin = new Ext.Window({
			closable : true,
			width : 620,
			height : 286,
			resizable : false,
			plain : true,
			modal : true,
			frame : true,
			items : [ deptGrid ]
		});
		dwin.show();
	};

	/**
	 *获取奖惩情况
	 */

	function getRp() {

		var RpCm = new Ext.grid.ColumnModel([ {
			header : "<span style = 'color:#555'>代码</span>",
			dataIndex : "code",
			renderer : function(v) {
				return "<span style = 'color:#555'>" + v + "</span>";
			},
			align : "center",
			menuDisabled : true
		}, {
			header : "<span style = 'color:#555'>类型</span>",
			dataIndex : "rp",
			renderer : function(v) {
				return "<span style = 'color:#555'>" + v + "</span>";
			},
			align : "center",
			menuDisabled : true
		} ]);
		var Rpdata = [[ "B1", "嘉奖" ], [ "B2", "优秀士兵" ], [ "B3", "三等功" ],
				[ "B4", "二等功" ], [ "B5", "其他" ]];
		var RpStore = new Ext.data.Store({
			proxy : new Ext.data.MemoryProxy(Rpdata),

			reader : new Ext.data.ArrayReader({}, [ {
				name : "code"
			}, {
				name : "rp"
			} ])
		});
		RpStore.load();
		var RpGrid = new Ext.grid.GridPanel({
			region : "center",
			ds : RpStore,
			cm : RpCm,
			height : 260,
			stripeRows : true,
			autoScroll : true,
			frame : true,
			viewConfig : {
				forceFit : true
			},
			loadMask : "数据加载中...",
			bbar : new Ext.PagingToolbar({
				pageSize : 10,
				cls : "color:#555",
				ctCls : "color:#555",
				store : RpStore,
				displayInfo : true
			}),
			listeners : {
				"rowdblclick" : function(grid, rowIndex, e) {
					var record = grid.getStore().getAt(rowIndex);
					$("#rp").val(record.get("rp"));
					if (record.get("rp") == "其他") {
						Ext.getCmp('remark1').enable();
					} else {
						Ext.getCmp('remark1').setValue("");
						Ext.getCmp('remark1').disable();
					}
					rpwin.close();
				}
			}
		});
		var rpwin = new Ext.Window({
			closable : true,
			width : 620,
			height : 286,
			resizable : false,
			plain : true,
			modal : true,
			frame : true,
			items : [ RpGrid ]
		});
		rpwin.show();
	}
</script>
</head>
<body>
	<div id="infoquery"></div>
	<input type="hidden" id="bgval"
		value="<s:property value = "%{#session.userbg}"/>" />
	<input type="hidden" id="deptnme"
		value="<s:property value = '%{#session.userdeptnm}'/>" />
</body>
</html>
