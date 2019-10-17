Ext.onReady(function(){
	//login main
	var i = new Ext.Panel({
		frame:true,
		width:556,
		height:280,
		bodyStyle:"padding:6px;background-image:url('images/dootk/login_main.jpg')"
	});
	i.render("imgdiv");
	var f = new Ext.form.FormPanel({
		width:556,
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
									window.location=$("#serverpath").val()+"main.jsp";
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