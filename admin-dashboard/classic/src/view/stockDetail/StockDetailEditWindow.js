Ext.define('Admin.view.stockDetail.StockDetailEditWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.stockDetailEditWindow',

    
    title: '进货详单',
    frame: true,
    width: 650,
    height: 430,
    bodyPadding: 10,
    scrollable: true,
    items: [{
        // Use the default, automatic layout to distribute the controls evenly
        // across a single row
        scrollable: true,
        xtype: 'checkboxgroup',
        fieldLabel: '商品集合',
        id:'productlist',
        cls: 'x-check-group-alt',
        columns: 3,
        items: []
    },{
        id:'parm',
        xtype: 'textfield',       
        fieldLabel: 'id',
        name:'repoId',
        hidden: true,
        readOnly: true
    }],

    buttons: [{
        text: '提交',
        handler: 'submitEditForm'
    },{
        text: '重置',
        handler: 'onResetFormClick'
    },{
        text: '全选',
        handler: 'onAllFormClick'
    }]
});