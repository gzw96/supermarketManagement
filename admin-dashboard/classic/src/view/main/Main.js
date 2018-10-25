Ext.define('Admin.view.main.Main', {
    extend: 'Ext.container.Viewport',

    requires: [
        'Ext.button.Segmented',
        'Ext.list.Tree'
    ],

    controller: 'main',
    viewModel: 'main',
	

    cls: 'sencha-dash-viewport',
    itemId: 'mainView',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    listeners: {
        render: 'onMainViewRender'
    },
	listeners: {
		afterRender:function(){
			Ext.Ajax.request({
				url:'finduser',
				method:'get',
				success: function(response,options){
					var json = Ext.util.JSON.decode(response.responseText);
					userName = json.map.userName;
					if(userName!=null){
						Ext.getCmp('loginUserName').setText(userName);
					}else{
						if(id!='login')
							window.location.href="#login";
					}
				}
			});
		}
    },
    items: [
        {
            xtype: 'toolbar',
            cls: 'sencha-dash-dash-headerbar shadow',
            height: 64,
            itemId: 'headerBar',
            items: [
                {
                    xtype: 'component',
                    reference: 'senchaLogo',
                    cls: 'sencha-logo',
                    html: '<div class="main-logo"><i class="fa fa-cogs" style="margin-left:20px;margin-right:20px;"></i>超市进销存系统</div>',
                    width: 250
                },
                {
                    margin: '0 0 0 8',
                    ui: 'header',
                    iconCls:'x-fa fa-navicon',
                    id: 'main-navigation-btn',
                    handler: 'onToggleNavigationSize'
                },
                '->',
                {
                    iconCls:'x-fa fa-power-off',
                    ui: 'header',
                    //href: '',//修改为登录页面
                    hrefTarget: '_self',
                    tooltip: 'logout',
					handler: 'logoutButton'
                },
                {
                    xtype: 'tbtext',
                    text: '用户名:',
					id:'loginUserName',
                    cls: 'top-user-name',
                },
                {
                    xtype: 'image',
                    cls: 'header-right-profile-image',
					id:'loginUserImage',
                    height: 35,
                    width: 35,
                    alt:'current user image',
                    src: 'resources/images/user-profile/2.png'
                }
            ]
        },
        {
            xtype: 'maincontainerwrap',
            id: 'main-view-detail-wrap',
            reference: 'mainContainerWrap',
            flex: 1,
            items: [
                {
                    xtype: 'treelist',
                    reference: 'navigationTreeList',
                    itemId: 'navigationTreeList',
                    ui: 'nav',
                    store: 'NavigationTree',
                    width: 250,
                    expanderFirst: false,
                    expanderOnly: false,
                    listeners: {
                        selectionchange: 'onNavigationTreeSelectionChange'
                    }
                },
                {
                    xtype: 'container',
                    flex: 1,
                    reference: 'mainCardPanel',
                    cls: 'sencha-dash-right-main-container',
                    itemId: 'contentPanel',
                    layout: {
                        type: 'card',
                        anchor: '100%'
                    }
                }
            ]
        }
    ]
	
});

