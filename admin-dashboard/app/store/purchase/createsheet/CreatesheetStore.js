Ext.define('Admin.store.purchase.createsheet.CreatesheetStore', {
	extend: 'Ext.data.Store',
	alias: 'store.createsheetStore',			  //1.Store取别名（reference）
	model: 'Admin.model.purchase.createsheet.CreatesheetModel',
});