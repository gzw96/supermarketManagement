Ext.define('Admin.view.sale.SaleHistoryDetailViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.saleHistoryDetailViewModel',

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
        saleHistoryDetailLists: { type: 'saleDetailGridStore' }
    }
});
