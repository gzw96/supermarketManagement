Ext.define('Admin.view.dashboard.repo.Repo',{
    extend:'Ext.container.Container',
    xtype:'repo',//供应商管理
    
    controller:'repoViewController',
    viewModel:{type:'repoViewModel'},

    layout:'fit',
    items:[{xtype:'repoPanel'}]
});