Ext.define('Admin.view.changePassword.ChangeViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.changeViewController',
    
	// 下拉框-选择事件
	
	clearChange: function () {
		var form = Ext.getCmp('changeForm');
		form.reset();
	},
	saveChange: function () {
		var form = Ext.getCmp('changeForm');
		var formvalue =form.getValues();
		if(formvalue.newpassword == formvalue.confirmpassword)
		{
			Ext.Ajax.request({ 
						url : '/user/changePassword', 
						method : 'post', 
						params : {
							userName:userName,
							oldpassword :formvalue.password,
							newpassword:formvalue.newpassword,
							confirmpassword:formvalue.confirmpassword
						}, 
						success: function(response, options) {
			                var json = Ext.util.JSON.decode(response.responseText);
				            if(json.success){
								form.reset();
				            	Ext.Msg.alert('操作成功', json.msg);
					        }else{
					        	 Ext.Msg.alert('操作失败', json.msg);
					        }
			            }
					});
		}else{
			Ext.Msg.alert('警告','两次输入密码不一致');
		}
	}
	
	
});
