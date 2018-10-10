Ext.define('Admin.view.stock.StockViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.stockViewModel',
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
        stockLists: {type: 'stockStroe'}
    }
});
