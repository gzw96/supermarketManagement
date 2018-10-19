Ext.define('Admin.view.purchase.createsheet.Createsheet', {		//1.修改文件路径
    extend: 'Ext.container.Container',	//2.继承的组件类型
    //3.重写继承组件的属性：
    xtype: 'createsheet',

    requires: [
        'Ext.layout.container.Border',
        'Ext.button.Button'
    ],
    controller: 'createsheetViewController',		//视图绑定viewController
    viewModel: { type: 'createsheetViewModel' },	//视图绑定viewModel

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
            xtype: 'createsheetGrid',
            region: 'center'
            // createsheetGrid:'createsheetGrid'
        },
        {
            bodyPadding: 10,
            xtype: 'createsheetGridForm',
            region: 'east',
            width: 375
        }
    ]
});