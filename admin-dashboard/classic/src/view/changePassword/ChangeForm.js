Ext.define('Admin.view.changePassword.ChangeForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.changeForm',
    id: 'changeForm',

    requires: [
        'Ext.button.Button',
        'Ext.form.field.*',
        'Ext.form.FieldSet'
    ],
    //controller: 'createsheetViewController',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    scrollable: true,
    frame: true,
    title: '请输入以下信息',
    fieldDefaults: {
        labelAlign: 'left',
        labelWidth: 150,
        msgTarget: 'none',
        invalidCls: ''
    },

    items: [{
        html: '<br/>'
    }, 
	{
        xtype: 'fieldset',
        title: '修改密码',
        items: [{
            xtype: 'textfield',
            fieldLabel: '原密码',
            name: 'password',
			inputType: 'password',
            emptyText: '请输入原密码',
            editable: true,
            allowBlank: false,
            blankText: '此处不能为空',
        }, {
            xtype: 'textfield',
            fieldLabel: '新密码',
            name: 'newpassword',
			inputType: 'password',
			emptyText: '请输入新密码',
            editable: true,
            allowBlank: false,
            blankText: '此处不能为空',
        }, {
            xtype: 'textfield',
            fieldLabel: '确认密码',
            name: 'confirmpassword',
			inputType: 'password',
            emptyText: '请再输入新密码',
            allowBlank: false,
            blankText: '此处不能为空',
            editable: true
        },
		{
			layout: {
				type: 'hbox',
				align: 'middle ',
				pack: 'center'
			},
			buttons: [{
				xtype: 'button',
				text: '确认',
				// margin: '10 10 10 20',
				handler: 'saveChange'
			}, {
				xtype: 'button',
				text: '取消',
				// margin: '10 60 10 10',
				handler: 'clearChange'
			}]
		}
		]
    }
	]

});