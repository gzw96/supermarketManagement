Ext.define('Aria.view.purchase.history.HistoryEditWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.historyEditWindow',
    height: 650,
    minHeight: 100,
    minWidth: 300,
    width: 500,
    scrollable: true,
    title: '修改信息',
    closable: true,
    constrain: true,
    defaultFocus: 'textfield',
    modal: true,
    layout: 'fit',
    items: [{
        xtype: 'form',
        layout: 'form',
        padding: '10px',
        ariaLabel: '请输入',
        items: [{
            xtype: 'textfield',
            fieldLabel: 'id',
            name: 'id',
            hidden: true,
            readOnly: true
        }, {
            xtype: 'textfield',
            fieldLabel: '结算金额',
            name: 'statement',
            emptyText: '此处不能为空',
            allowBlank: false,
            blankText: '此处不能为空'
        }, {
            xtype: 'textfield',
            fieldLabel: '实付金额',
            name: 'payment',
            emptyText: '此处不能为空',
            allowBlank: false,
            blankText: '此处不能为空'
        }, {
            xtype: 'combo',
            fieldLabel: '付款方式',
            emptyText: '此处不能为空',
            allowBlank: false,
            blankText: '此处不能为空',
            name: 'method',
            store: Ext.create("Ext.data.Store", {
                fields: ["name", "value"],
                data: [
                    { name: '现金', value: '现金' },
                    { name: '支付宝', value: '支付宝' },
                    { name: '微信支付', value: '微信支付' },
                    { name: '银联云闪付', value: '银联云闪付' },
                    { name: '支票', value: '支票' },
                    { name: '网银支付', value: '网银支付' },
                    { name: '刷卡', value: '刷卡' },
                ]
            }),
            displayField: 'name',
            valueField: 'value',
            editable: false,
            queryMode: 'local',
            triggerAction: 'all',
        }, {
            xtype: "combo",
            name: 'userRealName',
            store: Ext.create("Ext.data.Store", {
                fields: ["userRealName", "workNum"],
                proxy: {
                    type: "ajax",
                    url: '/purchasehistory/getusers',
                    actionMethods:
                    {
                        read: "POST"  //解决传中文参数乱码问题，默认为“GET”提交            
                    },
                    reader: {
                        type: "json",  //返回数据类型为json格式                
                        rootProperty: "root"  //数据            
                    },
                },
                autoLoad: true  //自动加载数据
            }),
            displayField: "userRealName",
            valueField: "userRealName",
            triggerAction: "all",            //  mode : "remote",            
            queryMode: "local",  //低版本使用mode属性            
            editable: true,
            allowBlank: false,

            fieldLabel: "进货员姓名",
            typeAhead: true,//设置为true，当开始输入字符时，在指定的延迟之后会自动匹配剩下的内容，如果找到了匹配的 内容则自动选中它 (typeAheadDelay) (默认值为 false)
            listeners: {
                select: function (combo, records) {
                    Ext.getCmp('workNumInHistory').setValue(records.get("workNum"));
                }
            }
        }, {
            xtype: 'textfield',
            fieldLabel: '进货员工号',
            name: 'workNum',
            id: 'workNumInHistory'
        }, {
            xtype: "combo",
            name: 'supplierName',
            store: Ext.create("Ext.data.Store", {
                fields: ["supplierName", "supplierName"],
                proxy: {
                    type: "ajax",
                    url: '/purchasehistory/getsuppliers',
                    actionMethods:
                    {
                        read: "POST"  //解决传中文参数乱码问题，默认为“GET”提交            
                    },
                    reader: {
                        type: "json",  //返回数据类型为json格式                
                        rootProperty: "root"  //数据            
                    },
                },
                autoLoad: true  //自动加载数据
            }),
            displayField: "supplierName",
            valueField: "supplierName",
            triggerAction: "all",            //  mode : "remote",            
            queryMode: "local",  //低版本使用mode属性            
            editable: true,
            allowBlank: false,

            fieldLabel: "供应商",
            typeAhead: true,//设置为true，当开始输入字符时，在指定的延迟之后会自动匹配剩下的内容，如果找到了匹配的 内容则自动选中它 (typeAheadDelay) (默认值为 false)
        }, {
            xtype: "combo",
            name: 'repoName',
            store: Ext.create("Ext.data.Store", {
                fields: ["repoName", "repoName"],
                proxy: {
                    type: "ajax",
                    url: '/purchasehistory/getrepos',
                    actionMethods:
                    {
                        read: "POST"  //解决传中文参数乱码问题，默认为“GET”提交            
                    },
                    reader: {
                        type: "json",  //返回数据类型为json格式                
                        rootProperty: "root"  //数据            
                    },
                },
                autoLoad: true  //自动加载数据
            }),
            displayField: "repoName",
            valueField: "repoName",
            triggerAction: "all",            //  mode : "remote",            
            queryMode: "local",  //低版本使用mode属性            
            editable: true,
            allowBlank: false,

            fieldLabel: "仓库",
            typeAhead: true,//设置为true，当开始输入字符时，在指定的延迟之后会自动匹配剩下的内容，如果找到了匹配的 内容则自动选中它 (typeAheadDelay) (默认值为 false)
        }, {
            xtype: 'textfield',
            fieldLabel: '备注',
            name: 'remark',
        }]
    }],
    buttons: ['->', {
        xtype: 'button',
        text: 'Submit',
        handler: 'submitEditForm'
    }, {
            xtype: 'button',
            text: 'Close',
            handler: function (btn) {
                btn.up('window').close();
            }
        }, '->']
});