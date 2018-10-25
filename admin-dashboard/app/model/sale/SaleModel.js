Ext.define('Admin.model.sale.SaleModel', {
    extend: 'Admin.model.Base',
    
    fields: [
		{type: 'string',name: 'id'},
	    {type: 'string',name: 'productName'},
	    {type: 'string',name: 'productNum'},
		{type: 'string',name: 'format'},
	    {type: 'string',name: 'retailPrice'},
		{type: 'int',name: 'saleProductNum'},
		{type: 'float' ,name: 'sum'}
	]/*,
	proxy: {
		type: 'rest',
		url: '/sale',
	}*/
});
