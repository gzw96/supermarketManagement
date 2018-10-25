Ext.define('Admin.view.user.UserPanel', {
    extend: 'Ext.panel.Panel',
    xtype: 'userPanel',

    requires: [
        'Ext.grid.Panel',
        'Ext.toolbar.Paging',
        'Ext.grid.column.Date',
		'Ext.selection.CheckboxModel',
        'Ext.form.field.Date',
		'Ext.form.field.ComboBox'
    ],
    //controller: 'searchresults',
   // viewModel: {type: 'orderViewModel'},
    layout: 'fit',
    items: [{
            xtype: 'gridpanel',
            cls: 'user-grid',
            title: 'UserGrid Results',
            //routeId: 'user',
            bind: '{userLists}',
            scrollable: false,
			selModel: {type: 'checkboxmodel'},
            columns: [
                {xtype: 'gridcolumn',width: 50,dataIndex: 'id',text: '用户账号',hidden:true},
                {xtype: 'gridcolumn', cls: 'content-column',dataIndex: 'userName',text: '用户昵称',flex: 1},
				{xtype: 'gridcolumn', cls: 'content-column',dataIndex: 'userRealName',text: '用户真实姓名',flex: 1},
                {xtype: 'gridcolumn',cls: 'content-column',dataIndex: 'phone',text: '电话',flex: 1},
				{xtype: 'gridcolumn',cls: 'content-column',dataIndex: 'sex',text: '性别',flex: 1,renderer:function(value){if(value=='female'){return '男';} else {return '女';}}},
				{xtype: 'gridcolumn',cls: 'content-column',dataIndex: 'workNum',text: '工号',flex: 1},
                {xtype: 'datecolumn',cls: 'content-column',width: 120,dataIndex: 'birthday',text: '生日',formatter: 'date("Y/m/d")'},
				{xtype: 'gridcolumn',cls: 'content-column',dataIndex: 'remark',text: '备注',flex: 1},
				{xtype: 'gridcolumn',cls: 'content-column',dataIndex: 'status',text: '状态',flex: 1,renderer: function(value) {
                        if(value=='yes'){
                        	return '有效';
                        }else{
                        	return '无效';
                        }
             }},
				{xtype: 'gridcolumn',cls: 'content-column',dataIndex: 'groupName',text: '身份',flex: 1,renderer: function(value) {
                        if(value=='Sale'){
                        	return '销售员';
                        }else if(value == 'Purchase'){
                        	return '采购员';
                        }else if(value == 'Stock'){
                        	return '仓库管理员';
                        }else if(value == 'Purchase'){
                        	return '采购员';
                        }else if(value == 'SuperAdmin'){
                        	return '超级管理员';
                        }else if(value == 'Admin'){
                        	return '管理员';
                        }
             }},
                {xtype: 'datecolumn',cls: 'content-column',width: 120,dataIndex: 'enterDate',text: '加入时间',formatter: 'date("Y/m/d")'},
                {xtype: 'actioncolumn',cls: 'content-column', width: 120,dataIndex: 'bool',text: 'Actions',tooltip: 'edit ',
                    items: [
                        {xtype: 'button', iconCls: 'x-fa fa-pencil' ,handler: 'openEditWindow'},
                        {xtype: 'button',iconCls: 'x-fa fa-close'	,handler: 'deleteOneRow'}
                    ]
                }
            ],
            tbar: [{
	            xtype: 'combobox',
	            reference:'searchFieldName',
	            hideLabel: true,
	            store:Ext.create("Ext.data.Store", {
				    fields: ["name", "value"],
				    data: [
				      	{ name: '工号', value: 'workNum' },
						{ name: '用户真实姓名', value: 'userRealName' }
				    ]
				}),
	            displayField: 'name',
	            valueField:'value',
	            value:'workNum',
	            editable: false,
	            queryMode: 'local',
	            triggerAction: 'all',
	            emptyText: 'Select a state...',
	            width: 135,
	            listeners:{
					select: 'searchComboboxSelectChuang'
				}
	        }, '-',{
            	xtype:'textfield',
            	reference:'searchFieldValue',
            	name:'userPanelSearchField'
		    }/*, '-',{
				xtype: 'datefield',
				hideLabel: true,
				hidden:true,
				format: 'Y/m/d H:i:s',
				//reference:'searchDataFieldValue',
				fieldLabel: 'From',
				name: 'from_date'
				//,id:'from_date',
				//vtype: 'daterange',
				//endDateField: 'to_date'
			}*/,'-',{
		        text: 'Search',
		        iconCls: 'fa fa-search',
		        handler: 'quickSearch'
		    },  '->',{
		        text: 'Add',
		        tooltip: 'Add a new row',
		        iconCls: 'fa fa-plus',
		        handler: 'openAddWindow'	
		    },'-',{
		        text: 'Removes',
		        tooltip: 'Remove the selected item',
		        iconCls:'fa fa-trash',
		        itemId: 'userGridPanelRemove',
		        disabled: true,
		        handler: 'deleteMoreRows'	
		    }],
            dockedItems: [{
                xtype: 'pagingtoolbar',
                dock: 'bottom',
                itemId: 'userPaginationToolbar',
                displayInfo: true,
                bind: '{userLists}'
            }],
            listeners: {
				selectionchange: function(selModel, selections){
					this.down('#userGridPanelRemove').setDisabled(selections.length === 0);
				}
			}
			
        }]
});
