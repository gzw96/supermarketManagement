Ext.define('Admin.view.stockDetail.StockDetailEditWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.stockDetailEditWindow',

    
    title: '进货详单',
    frame: true,
    width: 650,
    height: 430,
    
    scrollable: true,
    items: [{
        id:'exp',   
        html: '<p style="padding:20px;background-color: #D9EDF7;"></p>'},

    {
        id:'exp1',    
        html: '<p style="padding:20px;background-color: #D9EDF7;"></p>'
    },{
        
        scrollable: true,
        xtype: 'checkboxgroup',
        fieldLabel: '商品集合',
        id:'productlist',
        cls: 'x-check-group-alt',
        columns: 3
    },{
        id:'parm',
        xtype: 'textfield',       
        fieldLabel: 'id',
        name:'purchaseId',
        hidden: true,
        readOnly: true
    },{
        id:'parm1',
        xtype: 'textfield',       
        fieldLabel: 'id',
        name:'repoId',
        hidden: true,
        readOnly: true
    },{
        id:'parm2',
        xtype: 'textfield',       
        fieldLabel: 'id',
        name:'size',
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