Ext.define('Admin.view.product.ProductPanel', {
    extend: 'Ext.container.Container',
    xtype: 'productPanel',
    controller: 'productViewController',
    viewModel: {type: 'productViewModel'},
    layout: 'fit',
    items: [{xtype:'productGridPanel'}]
});
