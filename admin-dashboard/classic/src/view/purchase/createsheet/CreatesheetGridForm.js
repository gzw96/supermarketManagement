Ext.define('Admin.view.purchase.createsheet.CreatesheetGridForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.createsheetGridForm',
    id: 'createsheetGridForm',

    requires: [
        'Ext.button.Button',
        'Ext.form.field.*',
        'Ext.form.FieldSet'
    ],
    //controller: 'createsheetViewController',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    bodyPadding: 10,
    scrollable: true,
    frame: true,
    title: '进货结算',
    fieldDefaults: {
        labelAlign: 'left',
        labelWidth: 90,
        msgTarget: 'none',
        invalidCls: ''
    },

    items: [{
        html: '<br/>'
    }, {
        xtype: 'fieldset',
        title: '添加商品',
        defaultType: 'textfield',
        layout: 'anchor',
        defaults: {
            anchor: '100%'
        },
        items: [{
            xtype: 'combobox',
            typeAheadDelay: 20,
            emptyText: '请输入货号',
            listeners: {
                select: 'onSelectProductColumn'    // 选择下拉行的点击函数
            },
            store: Ext.create('Ext.data.Store', {  // 下拉列表显示的数据源
                autoSync: true,
                fields: ['id', 'productName', 'productNum', 'format', 'retailPrice'],
                proxy: {
                    type: 'ajax',
                    url: 'purchasedetail/getproductnums',
                    actionMethods:
                    {
                        read: "POST"  //解决传中文参数乱码问题，默认为“GET”提交            
                    },
                    reader: {
                        type: 'json',
                        rootProperty: 'root'
                    }
                }
            }),
            displayField: 'productNum', // 要显示出来的字段名，字段名来自store
            hiddenName: 'id', // 若是表单，提交的属性值
            valueField: 'productNum',   // 要显示出来的字段值, 字段值来自store
            triggerAction: 'all',
            editable: true,
            allowBlank: true
        }]
    }, {
        layout: {
            type: 'hbox',
            align: 'middle ',
            pack: 'center'
        },
        buttons: [{
            xtype: 'button',
            text: '进货',
            // margin: '10 10 10 20',
            handler: 'saveCreatesheet'
        }, {
            xtype: 'button',
            text: '清零',
            // margin: '10 60 10 10',
            handler: 'clearCreatesheet'
        }
        ]
    }, {
        xtype: 'fieldset',
        title: '统计信息',
        defaultType: 'textfield',
        layout: 'anchor',
        defaults: {
            anchor: '100%'
        },
        items: [{
            xtype: 'textfield',
            fieldLabel: '进货单号',
            name: 'purchaseNum',
            emptyText: '请输入单号',
            editable: true,
            allowBlank: false,
            blankText: '此处不能为空',
        }, {
            xtype: 'textfield',
            fieldLabel: '结算金额',
            name: 'statement',
            editable: false,
            allowBlank: false,
            blankText: '此处不能为空',
        }, {
            xtype: 'textfield',
            fieldLabel: '实付金额',
            name: 'payment',
            emptyText: '请输入实付金额',
            allowBlank: false,
            blankText: '此处不能为空',

            editable: true
        }, {
            xtype: 'combo',
            fieldLabel: '付款方式',
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
                    Ext.getCmp('workNumInCreatesheet').setValue(records.get("workNum"));
                }
            }
        }, {
            xtype: 'textfield',
            fieldLabel: '进货员工号',
            name: 'workNum',
            id: 'workNumInCreatesheet',
            editable: false
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
            editable: true
        }],

    }]

});