Ext.define('Admin.view.dashboard.supplier.Supplier', {
    extend: 'Ext.container.Container',
    xtype: 'supplier',//供应商管理

    controller: 'supplierViewController',
    viewModel: { type: 'supplierViewModel' },

    layout: 'fit',
    items: [{ xtype: 'supplierPanel' }]
});