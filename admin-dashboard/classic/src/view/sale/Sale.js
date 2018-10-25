Ext.define('Admin.view.sale.Sale', {
    extend: 'Ext.container.Container',
    xtype: 'sale',
    //requires: [],
    //controller: 'order',				//viewController:代码与视图分离。声明视图绑定的事件，可以多个视图共享。
    //viewModel: {type: 'orderlist'},	//viewModel：配置Stote数据源。多个视图共享Store。
    
	 requires: [
        'Ext.layout.container.Border',
        'Ext.button.Button'
    ],
    controller: 'saleViewController',		//视图绑定viewController
    viewModel: { type: 'saleViewModel' },	//视图绑定viewModel

    layout: 'border',
    bodyBorder: false,
    defaults: {
        split: true,
        bodyPadding: 0
    },
    height: 500,
    width: 800,
    margin: '20 20 20 20',
    items: [
		{
            xtype: 'salePanel',
            region: 'center'
            // createsheetGrid:'createsheetGrid'
        },
        {
            bodyPadding: 10,
            xtype: 'saleForm',
            region: 'east',
            width: 375
        }
    ]
});