Ext.define('Admin.view.changePassword.Change', {
    extend: 'Ext.container.Container',
    xtype: 'change',
    //requires: [],
    //controller: 'order',				//viewController:代码与视图分离。声明视图绑定的事件，可以多个视图共享。
    //viewModel: {type: 'orderlist'},	//viewModel：配置Stote数据源。多个视图共享Store。
    
	 requires: [
        'Ext.layout.container.Border',
        'Ext.button.Button'
    ],
    controller: 'changeViewController',		//视图绑定viewController

    layout: 'border',
    bodyBorder: true,
    defaults: {
        split: true,
        bodyPadding: 0
    },
    height: 220,
    width: 500,
    margin: '200 200 200 200',
    items: [
        {
            bodyPadding: 10,
            xtype: 'changeForm',
            region: 'center',
            width: 500,
			height:200
        }
    ]
});