Ext.define('Aria.view.dashboard.supplier.SupplierSearchWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.supplierSearchWindow',
    height: 500,
    minHeight: 100,
    minWidth: 300,
    width: 500,
    scrollable: true,
    title: 'Search Window',
    closable: true,
    constrain: true,
    defaultFocus: 'textfield',
    modal: true,
    layout: 'fit',
    items: [{
        xtype: 'form',
        layout: 'form',
        padding: '10px',
        ariaLabel: 'Enter',
        items: [{
            xtype: 'textfield',
            fieldLabel: '供应商名称',
            name: 'supplierName'
        }, {
            xtype: 'textfield',
            fieldLabel: '供应商负责人',
            name: 'supplierPeople'
        }, {
            xtype: 'textfield',
            fieldLabel: '开户银行',
            name: 'bankName'
        }, {
            xtype: 'combo',
            fieldLabel: '当前状态',
            name: 'status',
            value: '可用',
            store: Ext.create("Ext.data.Store", {
                fields: ["name", "value"],
                data: [
                    { name: '可用', value: '可用' },
                    { name: '不可用', value: '不可用' }
                ]
            }),
            displayField: 'name',
            valueField: 'value',
            editable: false,
            queryMode: 'local',
            triggerAction: 'all',
        }]
    }],
    buttons: ['->', {
        xtype: 'button',
        text: 'Submit',
        handler: 'submitSearchForm'
    }, {
            xtype: 'button',
            text: 'Close',
            handler: function (btn) {
                btn.up('window').close();
            }
        }, '->']
});
