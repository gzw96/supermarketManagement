Ext.define('Admin.view.brand.BrandAddWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.brandAddWindow',
    height: 350,
    minHeight: 350,
    minWidth: 300,
    width: 500,
    scrollable: true,
    title: '新增品牌',
    closable: true,
    constrain: true,
    
    defaultFocus: 'textfield',
    modal:true,
    layout: 'fit',
    items: [{
        xtype: 'form',
        layout: 'form',
        padding: '10px',
        ariaLabel: 'Enter your name',
		items: [{
            xtype: 'textfield',
            fieldLabel: 'id',
            name:'id',
            hidden: true,
            readOnly: true
        },{
            xtype: 'datefield',
            fieldLabel: '创建时间',
            format: 'Y-m-d H:i:s', 
            name: 'createTime',
            editable:false,
            value:new Date(),
             readOnly: true
        },{
            xtype: 'datefield',
            fieldLabel: '最后更新时间',
            format: 'Y-m-d H:i:s', 
            name: 'updateTime',
            editable:false,
            value:new Date(),
            readOnly: true,
            hidden:true
        },{
            xtype: 'textfield',
            name: 'brandName',
            fieldLabel: '品牌名称',
            allowBlank: false
        },{
			xtype     : 'textareafield',
			grow      : true,
			name      : 'brandDesc',
			fieldLabel: '注释',
			anchor    : '100%'
		}]
    }],
	buttons: ['->',{
        xtype: 'button',
        text: '提交',
        handler: 'submitAddForm'
    },{
        xtype: 'button',
        text: '关闭',
        handler: function(btn) {
            btn.up('window').close();
        }
    },'->']
});