Ext.define('Admin.view.menu.Menu', {		//1.修改文件路径
      extend: 'Ext.container.Container',	//2.继承的组件类型
	//3.重写继承组件的属性：
    xtype: 'menu',
	height:Ext.Element.getViewportHeight()-200,//必须设置高，否则无法使用border布局
    //controller: 'orderViewController',		
    viewModel : {type: 'menuViewModel'},	
	requires: [
        'Ext.layout.container.Border'
    ],
    layout:'border',
    margin: '20 20 20 20',
    items: [{
		title: 'Navigation',
		region:'west',
		width: 280,
		collapsible: true,
		margins: '5 0 0 0',
		cmargins: '5 5 0 0',
		split: true,
		xtype: 'menuTree'
	}]
});