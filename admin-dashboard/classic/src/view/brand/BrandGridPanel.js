Ext.define('Admin.view.brand.BrandGridPanel', {
	extend: 'Ext.panel.Panel',
	xtype: 'brandGridPanel',
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
		title: '品牌列表',
		bind: '{brandLists}',
		scrollable: false,
		selModel: {type: 'checkboxmodel'},
		columns: [
			 {header: '序号',dataIndex:'id',flex : 0.5,sortable: true,align:'center'}
			,{header: '创建时间',dataIndex:'createTime',flex : 1.3,sortable: true,align:'center',renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
			,{header: '最后修改时间',dataIndex:'updateTime',flex : 1.3,sortable: true,align:'center',renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
			,{header: '品牌商标',dataIndex: 'brandImg',flex : 1.2,sortable: true,renderer: function(value) {
                        return "<img src='resources/images/"+value+"' alt='' height='40px' width='40px'>";
             },align:'center'}
             ,{header: '品牌名称',dataIndex: 'brandName',flex : 1.2,sortable: true,align:'center'}
			,{header: '品牌注释',dataIndex: 'brandDesc',flex : 3,sortable: true,align:'center'}
			,{xtype: 'actioncolumn',cls: 'content-column', flex : 1.5,text: 'Actions',align:'center',tooltip: 'tool ',
				items: [
					{xtype: 'button', iconCls: 'x-fa fa-pencil',handler: 'openEditWindow'},
					{xtype: 'button',iconCls: 'x-fa fa-close',handler: 'deleteOneRow'}
					
				]
			}
		],
		forceFit : true,
		tbar: [{
			xtype: 'combobox',
			reference:'searchFieldName',
			hideLabel: true,
			store:Ext.create("Ext.data.Store", {
				fields: ["name", "value"],
				data: [{ name: '按创建时间', value: 'createTime' },
					   { name: '按最后修改时间', value: 'updateTime' }]
			}),
			displayField: 'name',
			valueField:'value',
			value:'createTime',
			editable: false,
			queryMode: 'local',
			triggerAction: 'all',
			emptyText: 'Select a state...',
			width: 150,
			listeners:{
				select: 'searchComboboxSelectChuang'
			}
		}, '-',{
			xtype: 'datefield',
			hideLabel: true,
			format: 'Y-m-d',
			reference:'searchDataFieldValue1'
		}, {
			xtype: 'datefield',
			hideLabel: true,
			format: 'Y-m-d',
			reference:'searchDataFieldValue2'
		},'-',{
			text: 'Search',
			iconCls: 'fa fa-search',
			handler: 'quickSearch',
			itemId: 'searchButton'
		}, '-',{
			text: 'Search More',
			iconCls: 'fa fa-search-plus',
			handler: 'openSearchWindow'	
		}, '->',{
			text: 'Add',
			tooltip: 'Add a new row',
			iconCls: 'fa fa-plus',
			handler: 'openAddWindow'	
		},'-',{
			text: 'Removes',
			tooltip: 'Remove the selected item',
			iconCls:'fa fa-trash',
			itemId: 'brandGridPanelRemove',
			disabled: true,
			handler: 'deleteMoreRows'	
		}],			
		dockedItems: [{
			xtype: 'pagingtoolbar',
			dock: 'bottom',
			displayInfo: true,
			bind: '{brandLists}'
		}],
		listeners: {
			selectionchange: function(selModel, selections){
				this.down('#brandGridPanelRemove').setDisabled(selections.length === 0);
			}
		}		
	}]
});