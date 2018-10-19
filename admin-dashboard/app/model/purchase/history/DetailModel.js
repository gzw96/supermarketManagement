Ext.define('Admin.model.purchase.history.DetailModel', {
    extend: 'Admin.model.Base',
    requires: [
        'Ext.data.proxy.Rest',
    ],
    fields: [
        { type: 'int', name: 'id' },
        { type: 'int', name: 'productNum', mapping: 'product.productNum' },
        { type: 'string', name: 'productName', mapping: 'product.productName' },
        { type: 'string', name: 'format', mapping: 'product.format' },
        { type: 'float', name: 'retailPrice', mapping: 'product.retailPrice' },
        { type: 'float', name: 'purchasePrice', mapping: 'purchasePrice' },
        { type: 'string', name: 'purchaseNum', mapping: 'purchase.purchaseNum' },
        { type: 'int', name: 'purchaseProductNum' },
        { type: 'string', name: 'purchaseStatus' },
    ],

    proxy: {
        type: 'rest',
        url: '/purchasedetail',
    }
});
