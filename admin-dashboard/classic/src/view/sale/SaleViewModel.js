Ext.define('Admin.view.sale.SaleViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.saleViewModel',
    stores: {
        saleLists: {
            type: 'saleGridStore',
            autoLoad: true
        }
    }
});