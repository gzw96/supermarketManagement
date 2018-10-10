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
		title: '仓库列表',
		bind: '{stockLists}',
		scrollable: false,
		selModel: {type: 'checkboxmodel'},
		columns: [
			 {header: '序号',dataIndex:'id',flex : 0.5,sortable: true,align:'center'}
			,{header: '创建时间',dataIndex:'createTime',flex : 1.3,sortable: true,align:'center',renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
			,{header: '最后修改时间',dataIndex:'updateTime',flex : 1.3,sortable: true,align:'center',renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
			,{header: '商品图片',dataIndex: 'productImg',flex : 1.2,sortable: true,renderer: function(value) {
                        return "<img src='resources/images/"+value+"' alt='' height='40px' width='40px'>";
             },align:'center'}
             ,{header: '商品名称',dataIndex: 'productName',flex : 1.2,sortable: true,align:'center'}
             ,{header: '商品编号',dataIndex: 'productNum',flex : 1,sortable: true,align:'center'}
             ,{header: '商品价格',dataIndex: 'productPrice',flex : 1,sortable: true,align:'center'}
			,{header: '状态',dataIndex: 'status',flex : 0.6,sortable: true,align:'center',renderer: function(value) {
                        if(value=='0'){
                        	return '下架';
                        }else{
                        	return '在售';
                        }
             }}
			,{header: '商品品牌',dataIndex: 'brand',flex : 1,align:'center',renderer: function(value) {return value.brandName;}}
			,{header: '商品品牌值',dataIndex: 'brandName1',align:'center',hidden:true}
			,{xtype: 'actioncolumn',cls: 'content-column', flex : 0.8,text: 'Actions',align:'center',tooltip: 'tool ',
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
					   { name: '按最后修改时间', value: 'updateTime' },
					   { name: '按价格区间', value: 'productPrice' }]
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
			editable:false,
			format: 'Y-m-d',
			reference:'searchDataFieldValue1'
		}, {
			xtype: 'datefield',
			editable:false,
			hideLabel: true,
			format: 'Y-m-d',
			reference:'searchDataFieldValue2'
		},{
			xtype: 'textfield',
			hideLabel: true,
			reference:'searchTextFieldValue1',
			hidden:true
		}, {
			xtype: 'textfield',
			hideLabel: true,
			reference:'searchTextFieldValue2',
			hidden:true
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
			bind: '{productLists}'
		}],
		listeners: {
			selectionchange: function(selModel, selections){
				this.down('#brandGridPanelRemove').setDisabled(selections.length === 0);
			}
		}		
	}]
});