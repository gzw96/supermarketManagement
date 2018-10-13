Ext.define('Admin.model.stockDetail.StockDetailModel', {
	extend: 'Admin.model.Base',
	requires: [
		'Ext.data.proxy.Rest'
	],
	fields: [
		{type:'int',name:'id'}
		,{type:'string',name:'stockName'}	
		,{type:'string',name:'stockNum'}
		,{type:'string',name:'stockImg'}
		,{type:'float',name:'stockPrice'}
		,{type:'string',name:'status'}
		,{type:'string',name:'getBrandName'}
		,{type:'date',name:'createTime',dateFormat:'Y-m-d H:i:s'}
		,{type:'date',name:'updateTime',dateFormat:'Y-m-d H:i:s'}
	],
	proxy: {
		type: 'rest',
		url: '/stockDetail',
	}
});
