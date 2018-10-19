Ext.define('Aria.view.purchase.history.HistorySearchWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.historySearchWindow',
    height: 500,
    minHeight: 100,
    minWidth: 300,
    width: 500,
    scrollable: true,
    title: '搜索',
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
            fieldLabel: '进货单号',
            name: 'purchaseNum'
        }, {
            xtype: 'combo',
            fieldLabel: '付款方式',
            emptyText: '此处不能为空',
            allowBlank: false,
            blankText: '此处不能为空',
            name: 'method',
            store: Ext.create("Ext.data.Store", {
                fields: ["name", "value"],
                data: [
                    { name: '现金', value: '现金' },
                    { name: '支付宝', value: '支付宝' },
                    { name: '微信支付', value: '微信支付' },
                    { name: '银联云闪付', value: '银联云闪付' },
                    { name: '支票', value: '支票' },
                    { name: '网银支付', value: '网银支付' },
                    { name: '刷卡', value: '刷卡' },
                ]
            }),
            displayField: 'name',
            valueField: 'value',
            editable: false,
            queryMode: 'local',
            triggerAction: 'all',
        }, {
            xtype: 'datefield',
            fieldLabel: '进货时间（起）',
            name: 'purchaseTimeStart',
            format: 'Y/m/d H:i:s'
        }, {
            xtype: 'datefield',
            fieldLabel: '进货时间（止）',
            name: 'purchaseTimeEnd',
            format: 'Y/m/d H:i:s'
        }
        ]
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
