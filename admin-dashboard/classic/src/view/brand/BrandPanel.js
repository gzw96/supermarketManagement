Ext.define('Admin.view.brand.BrandPanel', {
    extend: 'Ext.container.Container',
    xtype: 'brandPanel',
    controller: 'brandViewController',
    viewModel: {type: 'brandViewModel'},
    layout: 'fit',
    items: [{xtype:'brandGridPanel'}]
});
