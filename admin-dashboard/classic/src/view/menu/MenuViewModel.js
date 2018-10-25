Ext.define('Admin.view.menu.MenuViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.menuViewModel',
	stores: {
        menuLists: {
            type: 'navigationTree',
            autoLoad: true
        }
    }
});