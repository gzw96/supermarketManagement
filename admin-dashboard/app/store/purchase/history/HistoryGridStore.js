Ext.define('Admin.store.purchase.history.HistoryGridStore', {
	extend: 'Ext.data.Store',
	storeId: 'historyGridStore',
	alias: 'store.historyGridStore',
	model: 'Admin.model.purchase.history.HistoryModel',
	proxy: {
		type: 'rest',
		url: '/purchasehistory',
		reader: {
			type: 'json',
			rootProperty: 'content',//对应后台返回的结果集名称
			totalProperty: 'totalElements'//分页需要知道总记录数
		},
		writer: {
			type: 'json'
		},
		simpleSortMode: true	//简单排序模式
	},
	autoLoad: true,
	autoSync: true,
	remoteSort: true,//全局(远程)排序
	pageSize: 16,
	sorters: {
		direction: 'DESC', property: 'id'
	}
});
