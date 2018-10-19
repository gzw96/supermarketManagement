Ext.define('Admin.model.dashboard.supplier.SupplierModel', {
    extend: 'Admin.model.Base',
    requires: [
        'Ext.data.proxy.Rest',
	],
    fields: [
	    {type: 'int',name: 'id'},
	    {type: 'string',name: 'supplierName'},
        {type: 'string',name: 'supplierPeople'},
        {type: 'string',name: 'supplierPhone'},
        {type: 'string',name: 'bankAccount'},
        {type: 'string',name: 'bankName'},
        {type: 'string',name: 'remark'},
        {type: 'string',name: 'address'},
        {type: 'string',name: 'status'},
    ],
    
	proxy: {
		type: 'rest',
		url: '/supplier',
	}
});
