Ext.define('Admin.model.product.ProductModel', {
	extend: 'Admin.model.Base',
	requires: [
		'Ext.data.proxy.Rest'
	],
	fields: [
		{type:'int',name:'id'}
		,{type:'string',name:'productName'}	
		,{type:'string',name:'productNum'}
		,{type:'string',name:'productImg'}
		,{type:'float',name:'productPrice'}
		,{type:'string',name:'status'}
		,{type:'string',name:'getBrandName'}
		,{type:'date',name:'createTime',dateFormat:'Y-m-d H:i:s'}
		,{type:'date',name:'updateTime',dateFormat:'Y-m-d H:i:s'}
	],
	proxy: {
		type: 'rest',
		url: '/product',
	}
});
