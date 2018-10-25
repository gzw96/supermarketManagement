Ext.define('Admin.view.product.ProductAddWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.productAddWindow',
    height: 350,
    minHeight: 350,
    minWidth: 300,
    width: 500,
    scrollable: true,
    title: '新增商品',
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
            name: 'productName',
            fieldLabel: '商品名称',
            allowBlank: false
        },{
            xtype: 'textfield',
            name: 'productPrice',
            fieldLabel: '商品单价',
            allowBlank: false
        },{
            xtype: 'radiogroup',
            fieldLabel: '商品状态',
            items: [{
                name: 'status',
                boxLabel:'在售',
                inputValue: '1',
                checked:true
            },{
                name: 'status',
                boxLabel:'下架',
                inputValue: '0',
            }]
        },{
            xtype: 'combobox',
            name: 'getBrandName',
            fieldLabel: '商品品牌',
            store: new Ext.data.Store( {
                    proxy : new Ext.data.HttpProxy( {
                        url : 'brand/getBrand'//提交到某action的某方法
                    }),
                    reader : new Ext.data.JsonReader( {rootProperty:""}, []),//需要显示的数据实体字段
                    autoLoad : true
                }),
                displayField : 'name',
                hiddenName : 'getBrandName',
                valueField : 'value', 
                triggerAction : 'all',
                editable : false,
                allowBlank : false
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