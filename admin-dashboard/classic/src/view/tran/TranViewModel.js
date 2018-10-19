Ext.define('Admin.view.tran.TranViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.tranViewModel',
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
        tranLists: {type: 'tranStore'}
    }
});
