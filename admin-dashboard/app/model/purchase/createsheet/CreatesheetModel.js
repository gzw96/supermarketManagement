Ext.define('Admin.model.purchase.createsheet.CreatesheetModel', {
    extend: 'Admin.model.Base',
    fields: [
        { name: 'id', type: 'int' },
        { name: 'productName', type: 'string' },
        { name: 'productNum', type: 'string' },
        { name: 'format', type: 'string' },
        { name: 'retailPrice', type: 'float' },
        { name: 'purchasePrice', type: 'float' },
        { name: 'purchaseProductNum', type: 'int' },
        { name: 'sum', type: 'float' }
    ]
});