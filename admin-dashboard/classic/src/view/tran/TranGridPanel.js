Ext.define('Admin.view.tran.TranGridPanel', {
	extend: 'Ext.panel.Panel',
	xtype: 'tranGridPanel',
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
		bind: '{tranLists}',
		scrollable: false,
		selModel: {type: 'checkboxmodel'},
		columns: [
			 {header: '序号',dataIndex:'id',flex : 0.5,sortable: true,align:'center'}
			,{header: '执行时间',dataIndex:'moveDate',flex : 1.3,sortable: true,align:'center',renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
             ,{header: '调拨数量',dataIndex: 'moveNum',flex : 1,sortable: true,align:'center'}
             ,{header: '调出仓库',dataIndex: 'repoFromName',flex : 1,sortable: true,align:'center'}
             ,{header: '调入仓库',dataIndex: 'repoToName',flex : 1,sortable: true,align:'center'}
		],
		plugins: [{
            ptype: 'rowwidget',
            widget: {
                xtype: 'grid',
                bind: {
                    store: '{record.tranDetail}'
                },
                columns: [{
                    text: '进货详单',
                    dataIndex: 'id',
                    width: 75,
                    align:'center'
                },{
                    text: '货物名称',
                    dataIndex: 'product',
                    width: 265,
                    align:'center',
                    renderer:function(value){
                    	return value.productName;
                    }
                },{
                    text: '进货数量',
                    dataIndex: 'tranNum',
                    width: 265,
                    align:'center'
                }]
            }
        }],
		forceFit : true,
		tbar: [{
			xtype: 'combobox',
			reference:'searchFieldName',
			hideLabel: true,
			store:Ext.create("Ext.data.Store", {
				fields: ["name", "value"],
				data: [{ name: '按创建时间', value: 'createTime' },
					   { name: '按最后修改时间', value: 'updateTime' },
					   { name: '按价格区间', value: 'tranPrice' }]
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
			bind: '{tranLists}'
		}],
		listeners: {
			selectionchange: function(selModel, selections){
				this.down('#brandGridPanelRemove').setDisabled(selections.length === 0);
			}
		}		
	}]
});


