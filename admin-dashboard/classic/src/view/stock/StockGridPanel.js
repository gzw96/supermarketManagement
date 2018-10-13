Ext.define('Admin.view.stock.StockGridPanel', {
	extend: 'Ext.panel.Panel',
	xtype: 'stockGridPanel',
	requires: [
		'Ext.data.*',
	    'Ext.util.*',
	    'Ext.view.View',
	    'Ext.ux.DataView.Animated',
	    'Ext.XTemplate',
	    'Ext.panel.Panel',
	    'Ext.layout.container.Fit',
	    'Ext.toolbar.*',
	    'Ext.slider.Multi'
	],
	layout: 'fit',
	items: [{
		xtype: 'gridpanel',
		title: '商品列表',
		bind: '{stockLists}',
		scrollable: false,
		selModel: {type: 'checkboxmodel'},
		columns: [
			 {header: '序号',dataIndex:'id',flex : 0.5,sortable: true,align:'center'}
			,{header: '创建时间',dataIndex:'buildDate',flex : 1.3,sortable: true,align:'center',renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
             ,{header: '名称',dataIndex: 'repoName',flex : 1.2,sortable: true,align:'center'}
             ,{header: '类型',dataIndex: 'type',flex : 1,sortable: true,align:'center'}
		],
		forceFit : true,
		tbar: [ '->',{
			text: '仓库初始化',
			tooltip: 'init repository',
			iconCls: 'fa fa-plus',
			handler: 'openAddWindow'	
		}],			
		dockedItems: [{
			xtype: 'pagingtoolbar',
			dock: 'bottom',
			displayInfo: true,
			bind: '{stockLists}'
		}]	
	}]
});