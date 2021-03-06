Ext.define('Admin.view.product.ProductViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.productViewModel',
    requires: [
        'Ext.data.Store',
        'Ext.data.proxy.Memory',
        'Ext.data.field.Integer',
        'Ext.data.field.String',
        'Ext.data.field.Date',
        'Ext.data.field.Boolean',
        'Ext.data.reader.Json'
    ],
    stores: {
        productLists: {type: 'productStore'}
    }
});
