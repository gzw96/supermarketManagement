Ext.define('Admin.model.user.UserModel', {
    extend: 'Admin.model.Base',
    requires: [
		'Ext.data.proxy.Rest'
	],
    fields: [
		{type: 'string',name: 'id'},
		{type: 'string',name: 'password'},
	    {type: 'string',name: 'userName'},
	    {type: 'string',name: 'userRealName'},
		{type: 'string',name: 'phone'},
	    {type: 'string',name: 'sex'},
		{type: 'date',name: 'birthday',dateFormat:'Y/m/d'},
	    {type: 'string',name: 'workNum'},
		{type: 'date',name: 'enterDate',dateFormat:'Y/m/d'},
	    {type: 'string',name: 'remark'},
		{type: 'string',name: 'status'},
	    {type: 'string',name: 'groupName'},
	],
	proxy: {
		type: 'rest',
		url: '/user',
	}
});