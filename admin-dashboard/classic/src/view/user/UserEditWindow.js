Ext.define('Aria.view.user.UserEditWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.userEditWindow',
    height: 370,
	x:200,
	y:10,
    minHeight: 100,
    minWidth: 300,
    width: 680,
    scrollable: true,
    title: 'Edit User Window',
    closable: true,
    constrain: true,
    defaultFocus: 'textfield',
    modal:true,
	items: [{
        xtype: 'form',
        layout: 'column',
		width:650,
        ariaLabel: 'Alter new information',
        items: [{
            xtype: 'textfield',
            fieldLabel: '用户账号',width:300,padding:'10px',

            name:'id',
            //hidden: true,
            //readOnly: true
        }, {
            xtype: 'textfield',
            fieldLabel: '用户昵称',width:300,padding:'10px',
            name:'userName'
        }, {
            xtype: 'textfield',
            fieldLabel: '用户真实姓名',width:300,padding:'10px',
            name:'userRealName'
        }, {
            xtype: 'textfield',
            fieldLabel: '电话',width:300,padding:'10px',
            name:'phone'
        }, {
            xtype: 'combo',width:300,padding:'10px',
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
        }, {
            xtype: 'datefield',
            fieldLabel: '生日',width:300,padding:'10px',
            name:'birthday',
            format: 'Y/m/d '
        }, {
            xtype: 'textfield',
            fieldLabel: '工号',width:300,padding:'10px',
            name:'workNum'
        }, {
            xtype: 'textfield',
            fieldLabel: '备注',width:300,padding:'10px',
            name:'remark'
        }, 
		{
            xtype: 'combo',width:300,padding:'10px',
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
        },{
            xtype: 'datefield',
            fieldLabel: '加入时间',width:300,padding:'10px',
            name:'enterDate',
            format: 'Y/m/d '
        },{
            xtype: 'combo',width:300,padding:'10px',
            fieldLabel: '身份',
            allowBlank: false,
            blankText: '此处不能为空',
            name: 'groupName',
            store: Ext.create("Ext.data.Store", {
                fields: ["name", "value"],
                data: [
                    { name: '销售员', value: 'Sale' },
                    { name: '采购员', value: 'Stock' },
                    { name: '采购员', value: 'Purchase' },
                    { name: '管理员', value: 'Admin' },
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
        handler: 'submitEditForm'
    },{
        xtype: 'button',
        text: 'Close',
        handler: function(btn) {
            btn.up('window').close();
        }
    },'->']
  
});
