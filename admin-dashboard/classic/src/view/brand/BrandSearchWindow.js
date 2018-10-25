Ext.define('Admin.view.brand.BrandSearchWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.brandSearchWindow',
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
                data: [{ name: '按品牌名', value: 'brandName' },
                       { name: '按序号', value: 'id' }]
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
            name:'brandName',
            hidden: true,
            reference:'searchTextValue2'
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