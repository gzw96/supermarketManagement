Ext.define('Admin.store.user.UserGridStore', {
	extend: 'Ext.data.Store',
	storeId:'userGridStore',
	alias: 'store.userGridStore',
	model:'Admin.model.user.UserModel',
	proxy: {
		type: 'rest',
		url: '/user',
		reader:{
			type:'json',
			rootProperty:'content',
			totalProperty: 'totalElements'
		},
		writer: {
			type: 'json'
		},
		simpleSortMode: true	
	},
	autoLoad: true,
	autoSync: true,
	remoteSort: true,
	pageSize: 15,
	sorters: {
		direction: 'DESC',property: 'id'
	}
});
