Ext.define('Admin.view.dashboard.supplier.SupplierPanel', {
    extend: 'Ext.panel.Panel',
    xtype: 'supplierPanel',

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
        cls: 'supplier-grid',
        title: '供应商管理',
        bind: '{supplierLists}',
        scrollable: false,
        selModel: { type: 'checkboxmodel' },
        columns: [
            { xtype: 'gridcolumn', width: 40, dataIndex: 'id', text: 'Key', hidden: true },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'supplierName', text: '供应商名称', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'supplierPeople', text: '供应商负责人', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'supplierPhone', text: '供应商电话', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'bankAccount', text: '银行账户', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'bankName', text: '开户银行', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'address', text: '地址', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'remark', text: '备注', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'status', text: '当前状态', flex: 1 },

            {
                xtype: 'actioncolumn', cls: 'content-column', width: 120, text: 'Actions', tooltip: 'edit ',
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
                    { name: '供货商名称', value: 'supplierName' },
                    { name: '供货商负责人', value: 'supplierPeople' },
                    { name: '开户银行', value: 'bankName' },
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
            name: 'orderPanelSearchField'
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
            name: 'orderPanelSearchDataField'
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
            itemId: 'supplierGridPanelRemove',
            disabled: true,
            handler: 'deleteMoreRows'
        }],
        dockedItems: [{
            xtype: 'pagingtoolbar',
            dock: 'bottom',
            //itemId: 'userPaginationToolbar',
            displayInfo: true,
            bind: '{supplierLists}'
        }],
        listeners: {
            selectionchange: function (selModel, selections) {
                this.down('#supplierGridPanelRemove').setDisabled(selections.length === 0);
            }
        }
    }]
});