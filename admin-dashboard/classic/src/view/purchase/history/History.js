Ext.define('Admin.view.purchase.history.History', {
    extend: 'Ext.container.Container',
    xtype: 'history',//供应商管理

    controller: 'historyViewController',
    viewModel: { type: 'historyViewModel' },

    layout: 'fit',
    items: [{ xtype: 'historyPanel' }]
});