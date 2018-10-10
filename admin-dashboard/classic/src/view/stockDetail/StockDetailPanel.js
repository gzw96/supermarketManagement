Ext.define('Admin.view.stockDetail.StockDetailPanel', {
    extend: 'Ext.container.Container',
    xtype: 'stockDetailPanel',
    controller: 'stockDetailViewController',
    viewModel: {type: 'stockDetailViewModel'},
    layout: 'fit',
    items: [{xtype:'stockDetailGridPanel'}]
});
