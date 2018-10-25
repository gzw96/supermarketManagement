Ext.define('Admin.view.tran.TranPanel', {
    extend: 'Ext.container.Container',
    xtype: 'tranPanel',
    controller: 'tranViewController',
    viewModel: {type: 'tranViewModel'},
    layout: 'fit',
    items: [{xtype:'tranGridPanel'}]
});
