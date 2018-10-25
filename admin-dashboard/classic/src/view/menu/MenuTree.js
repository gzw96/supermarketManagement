Ext.define('Admin.view.menu.MenuTree', {
      extend: 'Ext.tree.Panel',
    id:'menuTree',
	xtype: 'menuTree',
	title:'<b>组织架构</b>',
	bind:'{menuLists}'
});