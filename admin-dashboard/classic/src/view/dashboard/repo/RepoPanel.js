Ext.define('Admin.view.dashboard.repo.RepoPanel', {
    extend: 'Ext.panel.Panel',
    xtype: 'repoPanel',

    requires: [
        'Ext.grid.Panel',
        'Ext.toolbar.Paging',
        'Ext.form.field.ComboBox',
        'Ext.selection.CheckboxModel',
        'Ext.form.field.Date',
        'Ext.grid.column.Date'
    ],

    layout: 'fit',
    items: [{
        xtype: 'gridpanel',
        cls: 'repo-grid',
        title: '仓库/门店管理',
        bind: '{repoLists}',
        scrollable: false,
        selModel: { type: 'checkboxmodel' },
        columns: [
            { xtype: 'gridcolumn', width: 40, dataIndex: 'id', text: 'Key', hidden: true },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'repoName', text: '名称', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'type', text: '类型', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'repoPhone', text: '电话', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'address', text: '地址', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'buildDate', text: '建立日期', flex: 1, formatter: 'date("Y/m/d")' },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'maxSize', text: '最大库存', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'minSize', text: '最小库存', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'workNum', text: '负责人工号', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'userRealName', text: '负责人姓名', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'status', text: '当前状态', flex: 1 },

            {
                xtype: 'actioncolumn', cls: 'content-column', width: 120, text: '操作', tooltip: 'edit ',
                items: [
                    { xtype: 'button', iconCls: 'x-fa fa-pencil', text: '编辑', handler: 'openEditWindow' },
                    { xtype: 'button', iconCls: 'x-fa fa-close', text: '删除', handler: 'deleteOneRow' },
                ]
            }
        ],
        tbar: [{
            xtype: 'combobox',
            reference: 'searchFieldName',
            hideLabel: true,
            store: Ext.create("Ext.data.Store", {
                fields: ["name", "value"],
                data: [
                    { name: '名称', value: 'repoName' },
                    { name: '当前状态', value: 'status' }
                ]
            }),
            displayField: 'name',
            valueField: 'value',

            editable: false,
            queryMode: 'local',
            triggerAction: 'all',
            emptyText: '选择查询方式',
            width: 135,
            listeners: {
                select: 'searchComboboxSelectChuang'
            }
        }, '-', {
            xtype: 'textfield',
            reference: 'searchFieldValue',
            name: 'repoPanelSearchField'
        }, '-', {
            xtype: 'combo',
            store: Ext.create("Ext.data.Store", {
                fields: ["name", "value"],
                data: [
                    { name: '可用', value: '可用' },
                    { name: '不可用', value: '不可用' }
                ]
            }),
            displayField: 'name',
            valueField: 'value',
            editable: false,
            queryMode: 'local',
            triggerAction: 'all',
            //
            reference: 'searchDataFieldValue',
            hidden: true,
            name: 'repoPanelSearchDataField'
        }, '-', {
            text: 'Search',
            iconCls: 'fa fa-search',
            handler: 'quickSearch'
        }, '-', {
            text: 'Search More',
            iconCls: 'fa fa-search-plus',
            handler: 'openSearchWindow'
        }, '->', {
            text: 'Add',
            tooltip: 'Add a new row',
            iconCls: 'fa fa-plus',
            handler: 'openAddWindow'
        }, '-', {
            text: 'Removes',
            tooltip: 'Remove the selected item',
            iconCls: 'fa fa-trash',
            itemId: 'repoGridPanelRemove',
            disabled: true,
            handler: 'deleteMoreRows'
        }],
        dockedItems: [{
            xtype: 'pagingtoolbar',
            dock: 'bottom',
            //itemId: 'userPaginationToolbar',
            displayInfo: true,
            bind: '{repoLists}'
        }],
        listeners: {
            selectionchange: function (selModel, selections) {
                this.down('#repoGridPanelRemove').setDisabled(selections.length === 0);
            }
        }
    }]
});