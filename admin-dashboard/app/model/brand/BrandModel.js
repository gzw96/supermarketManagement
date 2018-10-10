Ext.define('Admin.model.brand.BrandModel', {
	extend: 'Admin.model.Base',
	requires: [
		'Ext.data.proxy.Rest'
	],
	fields: [
		{type:'int',name:'id'}
		,{type:'string',name:'brandName'}	
		,{type:'string',name:'brandDesc'}
		,{type:'string',name:'brandImg'}
		,{type:'date',name:'createTime',dateFormat:'Y-m-d H:i:s'}
		,{type:'date',name:'updateTime',dateFormat:'Y-m-d H:i:s'}
	],
	proxy: {
		type: 'rest',
		url: '/brand',
	}
});
