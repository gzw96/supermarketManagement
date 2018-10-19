Ext.define('Admin.view.purchase.history.HistoryPanel', {
    extend: 'Ext.panel.Panel',
    xtype: 'historyPanel',

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
        cls: 'history-grid',
        title: '进货单管理',
        bind: '{historyLists}',
        scrollable: false,
        selModel: { type: 'checkboxmodel' },
        columns: [
            { xtype: 'gridcolumn', width: 40, dataIndex: 'id', text: 'Key', hidden: true },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'purchaseNum', text: '进货单号', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'statement', text: '结算金额', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'payment', text: '实付金额', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'method', text: '付款方式', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'purchaseTime', text: '进货时间', flex: 1, formatter: 'date("Y/m/d H:i:s")' },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'workNum', text: '进货员工号', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'userRealName', text: '进货员姓名', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'supplierName', text: '供货商', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'repoName', text: '仓库', flex: 1 },
            { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'remark', text: '备注', flex: 1 },

            {
                xtype: 'actioncolumn', cls: 'content-column', width: 160, text: '操作', tooltip: 'edit ',
                items: [
                    { xtype: 'button', iconCls: 'x-fa fa-pencil', text: '编辑', handler: 'openEditWindow' },
                    { xtype: 'button', iconCls: 'x-fa fa-close', text: '删除', handler: 'deleteOneRow' },
                    { xtype: 'button', iconCls: 'x-fa fa-th-list', text: '详情', handler: 'openDetailWindow' }
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
                    { name: '进货单号', value: 'purchaseNum' },
                    { name: '付款方式', value: 'method' },
                    { name: '进货时间', value: 'purchaseTime' }
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
        }, {
            xtype: 'textfield',
            reference: 'searchFieldValue',
            name: 'historyPanelSearchField'
        }, {
            xtype: 'datefield',
            hideLabel: true,
            hidden: true,
            format: 'Y/m/d H:i:s',
            reference: 'searchDateFieldValue',
            fieldLabel: 'From',
            name: 'from_date'
            //,id:'from_date',
            //vtype: 'daterange',
            //endDateField: 'to_date'
        }, {
            xtype: 'datefield',
            hideLabel: true,
            hidden: true,
            format: 'Y/m/d H:i:s',
            reference: 'searchDateFieldValue2',
            fieldLabel: 'To',
            name: 'to_date'
            //,id:'to_date',
            //vtype: 'daterange',
            //startDateField: 'from_date'
        }, {
            xtype: 'combo',
            store: Ext.create("Ext.data.Store", {
                fields: ["name", "value"],
                data: [
                    { name: '现金', value: '现金' },
                    { name: '支付宝', value: '支付宝' },
                    { name: '微信支付', value: '微信支付' },
                    { name: '银联云闪付', value: '银联云闪付' },
                    { name: '支票', value: '支票' },
                    { name: '网银支付', value: '网银支付' },
                    { name: '刷卡', value: '刷卡' },
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
            name: 'historyPanelSearchMethodField'
        }, '-', {
            text: 'Search',
            iconCls: 'fa fa-search',
            handler: 'quickSearch'
        }, '-', {
            text: 'Search More',
            iconCls: 'fa fa-search-plus',
            handler: 'openSearchWindow'
        }, '->', {
            text: 'Removes',
            tooltip: 'Remove the selected item',
            iconCls: 'fa fa-trash',
            itemId: 'historyGridPanelRemove',
            disabled: true,
            handler: 'deleteMoreRows'
        }],
        dockedItems: [{
            xtype: 'pagingtoolbar',
            dock: 'bottom',
            //itemId: 'userPaginationToolbar',
            displayInfo: true,
            bind: '{historyLists}'
        }],
        listeners: {
            selectionchange: function (selModel, selections) {
                this.down('#historyGridPanelRemove').setDisabled(selections.length === 0);
            }
        }
    }]
});