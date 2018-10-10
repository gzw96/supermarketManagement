Ext.define('Admin.view.stockDetail.StockDetailSearchWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.stockDetailSearchWindow',
    height: 300,
    minHeight: 300,
    minWidth: 300,
    width: 500,
    scrollable: true,
    title: 'Search More Window',
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
			xtype: 'combobox',
            reference:'searchWindowField',
            hideLabel: true,
            store:Ext.create("Ext.data.Store", {
                fields: ["name", "value"],
                data: [{ name: '序号', value: 'id' },
                       { name: '商品名', value: 'stockDetailName' },
                       { name: '所属品牌', value: 'getBrandName'},
                       { name: '商品编号', value: 'stockDetailNum' }]
            }),
            displayField: 'name',
            valueField:'value',
            editable: false,
            queryMode: 'local',
            triggerAction: 'all',
            emptyText: '请选择',
            width: 150,
            listeners:{
                select: 'searchComboboxWindows'
            }
		},{
            xtype: 'textfield',
            hideLabel: true,
            name:'id',
            hidden: true,
            reference:'searchTextValue1'
        },{
            xtype: 'textfield',
            hideLabel: true,
            name:'stockDetailName',
            hidden: true,
            reference:'searchTextValue2'
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
                allowBlank : false,
                hidden: true,
                reference:'searchTextValue3'
        },{
            xtype: 'textfield',
            hideLabel: true,
            name:'stockDetailNum',
            hidden: true,
            reference:'searchTextValue4'
        }]
    }],
	buttons: ['->',{
        xtype: 'button',
        text: 'Submit',
        handler: 'submitSearchForm'
    },{
        xtype: 'button',
        text: 'Close',
        handler: function(btn) {
            btn.up('window').close();
        }
    },'->']
});