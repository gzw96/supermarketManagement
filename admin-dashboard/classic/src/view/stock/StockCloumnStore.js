Ext.define('Admin.view.stock.StockCloumnStore', {
    extend: 'Ext.data.Store',
    alias: 'store.two-year-sales',

    fields: ['name', 'value1', 'value2'],
   	proxy: {
        type: 'rest',
        url: '/stock/getAllRepoCloumn',
        reader:{
            type:'json'
        },
        writer: {
            type: 'json'
        }
       
    },
    autoLoad: true

});