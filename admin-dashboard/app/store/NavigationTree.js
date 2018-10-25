Ext.define('Admin.store.NavigationTree', {
    extend: 'Ext.data.TreeStore',
	alias: 'store.navigationTree',
    storeId: 'NavigationTree',

    
	proxy:{
		type:'ajax',
		url:'/findNode',
		reader:{
			type:'json'
		}
	},
    root: {
        expanded: true
        
    }
});