var tabPanel;
Ext.onReady(function(){
	tabPanel = new Ext.TabPanel({
		bodyBorder:false,
		border:false,
		frame:true,
		defaults:{autoScroll:true},
		enableTabScroll:true,
		items:[{
				title:"WORKPLACE",
				autoLoad:{url:"jsp/worklist.jsp",scripts:true},
				id:"0",
				autoHeight:true
			}
		]
	});
	tabPanel.activate(tabPanel.getItem("0"));
	//树菜单
	var tree = new Ext.tree.TreePanel({
		width:220,
		height:260,
		loader:new Ext.tree.TreeLoader({dataUrl:$("#basepath").val()+"systemmgt/tm.action"}),
		rootVisible:false,
		margins:'5 5 5 5',
		frame:true,
		autoScroll:true,
		viewConfig:{
			forceFit:true
		}				
	});	
	var root = new Ext.tree.AsyncTreeNode({expanded:true});
	tree.setRootNode(root);
	//注册单击事件
	tree.on("click",function(node){
		if(node.attributes.url != ""){
			addTab(node.text,node.attributes.url,node.id);
		}
	});
	//提案现况
	var icm = new Ext.grid.ColumnModel([{
		header:"<span style = 'color:#555'>类别</span>",dataIndex:"ptype",menuDisabled:true,renderer:function(v){
			return "<span style = 'color:#555'>"+v+"</span>";
		}
	},{
		header:"<span style = 'color:#555'>现况</span>",dataIndex:"pstatus",align:"center",menuDisabled:true,renderer:function(v){
			return "<span style = 'color:#555'>"+v+"</span>";
		}
	}]);
	var ids = new Ext.data.Store({
		proxy:new Ext.data.HttpProxy({url:$("#basepath").val()+"workplace/getProCurSts.action"}),
		reader:new Ext.data.JsonReader({
			root:"list"
		},[
			{name:"ptype"},
			{name:"pstatus"}
		])
	});
	ids.load();
	var igrid = new Ext.grid.GridPanel({
		ds:ids,
		cm:icm,
		title:"人员信息管理现况",
		width:220,
		height:220,
		viewConfig:{
			forceFit:true
		}
	});
	var issueState = new Ext.Panel({
		frame:true,
		width:220,
		height:220,
		items:[igrid],
		bodyStyle:"padding:0px;margin:0px"
	});
	//主画面
	new Ext.Viewport({
		enableTabScroll:true,
		layout:"border",
		items:[{
				region:"north",
				height:90,
				bodyStyle:"padding-top:10px;",
				autoLoad:{url:$("#basepath").val()+"jsp/head.jsp",scripts:true,nocache:true}
			},{
				title:"系统功能导航",
				region:"west",
				width:220,
				autoHeight:true,
			    //collapsible: true,
				items:[
					tree,issueState
				],
				bbar:new Ext.Toolbar({
				buttons:[{
						text:"系统管理",
						handler:function(){
							window.open($("#basepath").val()+"jsp/systemmanager.jsp","blank","width:800,height:500;scrolls:yes");
						},
						disabled:$("#currentuser").val()=="administrator"?false:true	//只有超级管理员才可以管理菜单
					}]
				})    
			},{
				region:"center",
				layout:'fit',
				items:[
					tabPanel
				]
			}
		]    		
	});
});
/**添加选项面板*/
function addTab(title,url,tid){
	var ct = tabPanel.getItem("id"+tid);
	/*
		检查该选项卡是否已经存在
		1、若存在则不添加直接激活该选项卡即可
		2、不存在则添加后激活	
	*/
	if(ct == null){
		var tab = tabPanel.add({
			title:title,
			//autoLoad:{url:url,scripts:true,nocache:true},//动态加载
			html:"<iframe src='"+url+"' frameborder='0' scrolling='yes' style='width:100%;height:"+($(document).height()-20)+"'></iframe>",
			closable:true,
			autoHeight:true,
			//height:600,
			enableTabScroll:true,
			autoScroll:true,
			loadMask:"Loading...",
			id:"id"+tid//指定id,便于查找
		}).show();
		//选中刚刚添加的选项卡
		tabPanel.activate(tab);
	}else{
		tabPanel.activate(ct);
	}
}