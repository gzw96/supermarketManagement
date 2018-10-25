Ext.define('Aria.view.purchase.history.HistoryDetailWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.historyDetailWindow',
    viewModel: { type: 'historyDetailViewModel' },
    height: 650,
    minHeight: 100,
    minWidth: 300,
    width: 800,
    scrollable: true,
    title: 'Detail History Window',
    closable: true,
    constrain: true,
    modal: true,
    layout: 'fit',
    items: [
        {
            xtype: 'gridpanel',
            cls: 'history-detail-grid',
            title: '订单详细信息',
            bind: '{historyDetailLists}',
            scrollable: false,
            columns: [
                { xtype: 'gridcolumn', width: 40, dataIndex: 'id', text: 'Key', hidden: true },
                { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'purchaseNum', hidden: true, text: '进货单号' },
                { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'productNum', text: '产品编号', flex: 1 },
                { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'productName', text: '产品名称', flex: 1 },
                { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'purchasePrice', text: '采购价', flex: 1 },
                { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'purchaseProductNum', text: '采购数量', flex: 1 },
                { xtype: 'gridcolumn', cls: 'content-column', dataIndex: 'purchaseStatus', text: '采购状态', flex: 1 },
                {
                    xtype: 'actioncolumn', cls: 'content-column', width: 160, text: '操作', tooltip: 'edit ',
                    items: [
                        { xtype: 'button', iconCls: 'x-fa fa-pencil', text: '编辑', handler: 'openEditDetailWindow' },
                        { xtype: 'button', iconCls: 'x-fa fa-close', text: '删除', handler: 'deleteOneDetailRow' },
                    ]
                }
            ],
            dockedItems: [{
                xtype: 'pagingtoolbar',
                dock: 'bottom',
                //itemId: 'userPaginationToolbar',
                displayInfo: true,
                bind: '{historyDetailLists}'
            }],
        }
    ],
    buttons: ['->', {
        xtype: 'button',
        text: 'Close',
        handler: function (btn) {
            btn.up('window').close();
        }
    }, '->']
});