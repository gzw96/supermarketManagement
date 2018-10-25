Ext.define('Aria.view.user.UserAddWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.userAddWindow',
	requires: [
        'Ext.grid.column.Date',
        'Ext.form.field.Date',
    ],
    height: 610,
    minHeight: 100,
    minWidth: 300,
    width: 400,
    scrollable: true,
    title: 'Add User Window',
    closable: true,
    constrain: true,
    
    defaultFocus: 'textfield',
    modal:true,
    layout: 'fit',
    items: [{
        xtype: 'form',
        layout: 'form',
        padding: '10px',
        ariaLabel: 'Enter new information',
        items: [{
            xtype: 'textfield',
            fieldLabel: '用户账号',
            name:'id',
            //hidden: true,
            //readOnly: true
        }, {
            xtype: 'textfield',
            fieldLabel: '用户昵称',
            name:'userName'
        }, {
            xtype: 'textfield',
            fieldLabel: '用户真实姓名',
            name:'userRealName'
        }, {
            xtype: 'textfield',
            fieldLabel: '密码',
            name:'password'
        }, {
            xtype: 'textfield',
            fieldLabel: '电话',
            name:'phone'
        }, {
            xtype: 'combo',
            fieldLabel: '性别',
            allowBlank: false,
            blankText: '此处不能为空',
            name: 'sex',
            store: Ext.create("Ext.data.Store", {
                fields: ["name", "value"],
                data: [
                    { name: '男', value: 'female' },
                    { name: '女', value: 'male' }
                ]
            }),
            displayField: 'name',
            valueField: 'value',
            editable: false,
            queryMode: 'local',
            triggerAction: 'all',
        },{
            xtype: 'datefield',
            fieldLabel: '生日',
            name:'birthday',
            format: 'Y/m/d '
			
        }, {
            xtype: 'textfield',
            fieldLabel: '工号',
            name:'workNum'
        }, {
            xtype: 'textfield',
            fieldLabel: '备注',
            name:'remark'
        }, {
            xtype: 'combo',
            fieldLabel: '状态',
            allowBlank: false,
            blankText: '此处不能为空',
            name: 'status',
            store: Ext.create("Ext.data.Store", {
                fields: ["name", "value"],
                data: [
                    { name: '有效', value: 'yes' },
                    { name: '无效', value: 'no' }
                ]
            }),
            displayField: 'name',
            valueField: 'value',
            editable: false,
            queryMode: 'local',
            triggerAction: 'all',
        }, {
            xtype: 'datefield',
            fieldLabel: '加入时间',
            name:'enterDate',
            format: 'Y/m/d '
			 
        }, {
            xtype: 'combo',
            fieldLabel: '身份',
            allowBlank: false,
            blankText: '此处不能为空',
            name: 'groupName',
            store: Ext.create("Ext.data.Store", {
                fields: ["name", "value"],
                data: [
                    { name: '管理员', value: 'Admin' },
                    { name: '采购员', value: 'Purchase' },
					{ name: '销售员', value: 'Sale' },
                    { name: '仓库管理员', value: 'Stock' },
					{ name: '超级管理员', value: 'SuperAdmin' }
                ]
            }),
            displayField: 'name',
            valueField: 'value',
            editable: false,
            queryMode: 'local',
            triggerAction: 'all',
        }]
    }],
	buttons: ['->',{
        xtype: 'button',
        text: 'Submit',
        handler: 'submitAddForm'
    },{
        xtype: 'button',
        text: 'Close',
        handler: function(btn) {
            btn.up('window').close();
        }
    },'->']
});
