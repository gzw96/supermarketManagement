Ext.define('Admin.view.sale.SaleHistory', {
    extend: 'Ext.container.Container',
    xtype: 'salehistory',//供应商管理

    controller: 'saleHistoryViewController',
    viewModel: { type: 'saleHistoryViewModel' },

    layout: 'fit',
    items: [{ xtype: 'saleHistoryPanel' }]
});