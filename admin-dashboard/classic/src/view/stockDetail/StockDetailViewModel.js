Ext.define('Admin.view.stockDetail.StockDetailViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.stockDetailViewModel',
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
        stockDetailLists: {type: 'stockDetailStroe'}
    }
});