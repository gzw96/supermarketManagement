Ext.define('Admin.view.purchase.createsheet.CreatesheetViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.createsheetViewModel',
    stores: {
        createsheetLists: {
            type: 'createsheetStore',
            autoLoad: true
        }
    }
});