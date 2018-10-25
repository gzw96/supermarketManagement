Ext.define('Admin.view.stockDetail.StockDetailGridPanel', {
	extend: 'Ext.panel.Panel',
	xtype: 'stockDetailGridPanel',
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
		title: '库存列表',
		bind: '{stockDetailLists}',
		scrollable: false,
		columns: [
			 {header: '序号',dataIndex:'id',flex : 0.5,sortable: true,align:'center'}
			,{header: '创建时间',dataIndex:'purchaseTime',flex : 1.3,sortable: true,align:'center',renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
			 ,{header: '存入仓库',dataIndex: 'repoName',flex : 1,sortable: true,align:'center'}
 			,{xtype: 'actioncolumn',cls: 'content-column', flex : 1.5,text: 'Actions',align:'center',tooltip: 'tool ',
				items: [
					{xtype: 'button', iconCls: 'x-fa fa-pencil',handler: 'openEditWindow'}
				]
			}
		],
		forceFit : true,
		tbar: [ '->',{
			text: '库存调拨',
			tooltip: '库存调拨',
			iconCls: 'fa fa-plus',
			handler: 'openAddWindow'	
		}],			
		dockedItems: [{
			xtype: 'pagingtoolbar',
			dock: 'bottom',
			displayInfo: true,
			bind: '{productLists}'
		}],
		listeners: {
			selectionchange: function(selModel, selections){
				this.down('#brandGridPanelRemove').setDisabled(selections.length === 0);
			}
		}		
	}]
});