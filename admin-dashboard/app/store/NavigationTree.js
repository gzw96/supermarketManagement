Ext.define('Admin.store.NavigationTree', {
    extend: 'Ext.data.TreeStore',

    storeId: 'NavigationTree',

    fields: [{
        name: 'text'
    }],

    root: {
        expanded: true,
        children: [
            {
                text: '超市管理',
                iconCls: 'x-fa fa-shopping-bag',
                expanded: false,
                selectable: false,
                //leaf:true,
                children: [
                    {
                        text: '门店/仓库管理',
                        iconCls: 'x-fa fa-link',
                        viewType: 'repo',
                        leaf: true
                    },
                    {
                        text: '商品管理',
                        iconCls: 'x-fa fa-link',
                        viewType: 'admindashboard',
                        leaf: true
                    },
                    {
                        text: '品牌管理',
                        iconCls: 'x-fa fa-link',
                        viewType: 'admindashboard',//点击后要显示的页面
                        leaf: true,
                    },
                    {
                        text: '供货商管理',
                        iconCls: 'x-fa fa-link',
                        viewType: 'supplier',//点击后要显示的页面
                        leaf: true,
                    }
                ]
            }, {
                text: '进货管理',
                iconCls: 'x-fa fa-truck',
                //rowCls: 'nav-tree-badge nav-tree-badge-new',
                //viewType: 'order',
                expanded: false,
                selectable: false,
                children: [
                    {
                        text: '新增进货单',
                        iconCls: 'x-fa fa-link',
                        viewType: 'createsheet',
                        leaf: true
                    },
                    {
                        text: '进货单管理',
                        iconCls: 'x-fa fa-link',
                        viewType: 'history',
                        leaf: true,
                    }
                ]
            },
            {
                text: '销售管理',
                iconCls: 'x-fa fa-shopping-cart',
                //rowCls: 'nav-tree-badge nav-tree-badge-new',
                //viewType: 'order',
                expanded: false,
                selectable: false,
                children: [
                    {
                        text: '销售开单',
                        iconCls: 'x-fa fa-link',
                        //viewType: 'createsheet',
                        leaf: true
                    },
                    {
                        text: '销售历史管理',
                        iconCls: 'x-fa fa-link',
                        viewType: 'history',
                        leaf: true
                    }
                ]
            },
            {
                text: '库存管理',
                iconCls: 'x-fa fa-home',
                //rowCls: 'nav-tree-badge nav-tree-badge-new',
                //viewType: 'order',
                expanded: false,
                selectable: false,
                children: [
                    {
                        text: '查看库存状况',
                        iconCls: 'x-fa fa-link',
                        // viewType: 'order',
                        leaf: true
                    },
                    {
                        text: '库存流水',
                        iconCls: 'x-fa fa-link',
                        // viewType: 'order',
                        leaf: true
                    },
                    {
                        text: '库存调拨',
                        iconCls: 'x-fa fa-link',
                        //viewType: 'order',
                        leaf: true
                    }
                ]
            },
            {
                text: '财务管理',
                iconCls: 'x-fa fa-cny',
                //rowCls: 'nav-tree-badge nav-tree-badge-new',
                //viewType: 'order',
                expanded: false,
                selectable: false,
                children: [
                    {
                        text: '查看报表',
                        iconCls: 'x-fa fa-link',
                        //viewType: 'order',
                        leaf: true
                    },
                    {
                        text: '财务记账',
                        iconCls: 'x-fa fa-link',
                        // viewType: 'order',
                        leaf: true
                    },
                    {
                        text: '利润统计',
                        iconCls: 'x-fa fa-link',
                        //viewType: 'order',
                        leaf: true
                    }
                ]
            },
            {
                text: '权限管理',
                iconCls: 'x-fa fa-users',
                //rowCls: 'nav-tree-badge nav-tree-badge-new',
                //viewType: 'order',
                expanded: false,
                selectable: false,
                children: [
                    {
                        text: '人员管理',
                        iconCls: 'x-fa fa-link',
                        //viewType: 'order',
                        leaf: true
                    }
                ]
            }
        ]
    }
});
