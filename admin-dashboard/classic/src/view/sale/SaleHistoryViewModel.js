Ext.define('Admin.view.sale.SaleHistoryViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.saleHistoryViewModel',

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
        saleHistoryLists: { type: 'saleHistoryGridStore' }
    }
});
