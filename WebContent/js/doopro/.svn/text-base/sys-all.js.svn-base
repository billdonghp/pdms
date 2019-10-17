/**
	系统共用js脚本
**/
//===================================================================================//
Ext.onReady(function(){
	//检查用户是否登录,如果没有登录这转到登录页面	
	if($("#loginpage").val() != "yes")
		$.ajax({
			url:$("#serverpath").val()+"lg/cklogin.action",
			type:"post",
			dataType:"json",
			success:function(r){
				if(r.login == "N"){
						alertMsg("您还没有登录系统，请先登录！",300,100,Ext.Msg.OK,Ext.Msg.WARNING,function(){
						window.location=$("#serverpath").val()+"index.jsp";
					});
				}
			},
			error:function(){
				alert("error");
			}
		});
});
//===================================================================================//
/**
 * 提示窗体
 * t:标题
 * m:提示信息
 * w:窗体宽度
 * h:窗体高度
 * bs:窗体按钮
 * icon:窗体图标
 * fn:点击后的回调函数
 */
function alertMsg(m, w, h, bs, icon,fn){
	Ext.Msg.show({
		title:"系统提示信息",
		msg:"<span style = 'color:#555'>"+m+"</span>",
		width:w,
		height:h,
		closable:true,
		buttons:bs,
		icon:icon,
		fn:fn		
	});
}
/**
 * 获取下拉框值
 * code:区分下拉框要取的值
 * filter:下拉框的值过滤条件
 * comid:Combox id
 * v:要设置的默认值
 */
function getComboxDs(code, filter, comid, v){
	var ds = new Ext.data.Store({
		proxy:new Ext.data.HttpProxy({url:$("#serverpath").val()+"combox/getcomvls.action?code="+code+"&filter="+filter}),
		reader:new Ext.data.JsonReader({
			root:"root"
		},[
			{name:"comvalue"},
			{name:"comview"}
		]),
		autoLoad:true
	});
	ds.load({ 
	   callback:function(records) { 
		   if(comid!=null)
			   Ext.getCmp(comid).setValue(v); 
	   } 
	});
	return ds;
}

function getStruCombox(code){
	var ds = new Ext.data.Store({
		proxy:new Ext.data.HttpProxy({url:$("#serverpath").val()+"vtb/ql-getStructureCombox.action"}),
		reader:new Ext.data.JsonReader({
			root:"root"
		},[
			{name:"comvalue"},
			{name:"comview"}
		]),
		baseParams:{
			code:code
		}
	});
	
	ds.on("beforeload", function(){//注册基本参数\
		Ext.apply(this.baseParams, {
		    filter:getStruComFilter(this.baseParams.code)
		});
	});

	return ds;
}

function getStruComFilter(code){
	var filter="";
	var vtb_d_v=Ext.getCmp("vtb_d").getValue();
	var vtb_k_v=Ext.getCmp("vtb_k").getValue();
	var vtb_z_v=Ext.getCmp("vtb_z").getValue();
	if(code=="vtb_k"){
		filter=(vtb_d_v=="")?"":(" and vtb_d_cd='"+vtb_d_v+"'");
	}else if(code=="vtb_z"){
		filter=(vtb_d_v==""?"":" and vtb_d_cd='"+vtb_d_v+"'")+(vtb_k_v==""?"":" and vtb_k_cd='"+vtb_k_v+"'");
	}else if(code=="vtb_g"){
		filter=(vtb_d_v==""?"":" and vtb_d_cd='"+vtb_d_v+"'")+(vtb_k_v==""?"":" and vtb_k_cd='"+vtb_k_v+"'")+(vtb_z_v==""?"":" and vtb_z_cd='"+vtb_z_v+"'");
	}
	return filter;
}

/**
 * 放大显示上传的图片
 * @param src
 */
function showImg(hm){
	var wdw = new Ext.Window({
        width:700,
        height:450,
        plain:true,
        bodyStyle:'padding:5px;',
        buttonAlign:'center',
        html:hm,
		modal:true,
		resizable:false
    });
    wdw.show();
}
