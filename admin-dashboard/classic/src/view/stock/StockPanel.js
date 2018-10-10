Ext.define('Admin.view.stock.StockPanel', {
    extend: 'Ext.container.Container',
    xtype: 'stockPanel',
    controller: 'stockViewController',
    //viewModel: {type: 'productViewModel'},
    layout: 'fit',
    items: [{xtype:'stockGridPanel'}]
});
