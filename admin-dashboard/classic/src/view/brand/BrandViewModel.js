Ext.define('Admin.view.brand.BrandViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.brandViewModel',
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
        brandLists: {type: 'brandStroe'}
    }
});
