Ext.define('Admin.model.sale.SaleHistoryModel', {
    extend: 'Admin.model.Base',
    requires: [
        'Ext.data.proxy.Rest',
    ],
    fields: [
        { type: 'int', name: 'id' },
        { type: 'string', name: 'saleNum' },
        { type: 'float', name: 'statement' },
        { type: 'float', name: 'payment' },
        { type: 'string', name: 'method' },
        { type: 'date', name: 'saleTime', dateFormat: 'Y/m/d H:i:s' },
        { type: 'string', name: 'remark' },
        { type: 'string', name: 'salerId', mapping: 'salerId' },
        { type: 'string', name: 'customerName', mapping: 'operator.customerName' },
        { type: 'string', name: 'customerPhone', mapping: 'operator.customerPhone' },
    ],

    proxy: {
        type: 'rest',
        url: '/salehistory',
    }
});
