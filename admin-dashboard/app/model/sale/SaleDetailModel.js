Ext.define('Admin.model.sale.SaleDetailModel', {
    extend: 'Admin.model.Base',
    requires: [
        'Ext.data.proxy.Rest',
    ],
    fields: [
        { type: 'int', name: 'id' },
        { type: 'int', name: 'productNum', mapping: 'productId' },
        // { type: 'string', name: 'saleNum', mapping: 'sale.saleNum' },
        { type: 'int', name: 'saleProductNum', mapping: 'saleNum'},
    ]
    
});
