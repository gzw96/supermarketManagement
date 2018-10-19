Ext.define('Admin.model.tran.TranModel', {
	extend: 'Ext.data.Model',
	fields: [
		 {type:'int',name:'id'}
		,{type:'string',name:'repoFromName',mapping:'repoFrom.repoName'}
		,{type:'string',name:'repoToName',mapping:'repoTo.repoName'}
		,{type:'int',name:'moveNum'}
		,{type:'date',name:'moveDate'}
	]
});
