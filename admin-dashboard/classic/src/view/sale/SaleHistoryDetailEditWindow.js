Ext.define('Aria.view.sale.SaleHistoryDetailEditWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.saleHistoryDetailEditWindow',
    height: 450,
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
            xtype: "combo",
            name: 'productNum',
            store: Ext.create("Ext.data.Store", {
                fields: ["productNum", "productNum"],
                proxy: {
                    type: "ajax",
                    url: '/purchasedetail/getproductnums',
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
            displayField: "productNum",
            valueField: "productNum",
            triggerAction: "all",            //  mode : "remote",            
            queryMode: "local",  //低版本使用mode属性            
            editable: true,
            allowBlank: false,

            fieldLabel: "产品编号",
            typeAhead: true,//设置为true，当开始输入字符时，在指定的延迟之后会自动匹配剩下的内容，如果找到了匹配的 内容则自动选中它 (typeAheadDelay) (默认值为 false)
            listeners: {
                select: function (combo, records) {
                    Ext.getCmp('empProductName').setValue(records.get("productName"));
                    Ext.getCmp('format').setValue(records.get("format"));
                    Ext.getCmp('retailPrice').setValue(records.get("retailPrice"));
                }
            }
        }, {
            xtype: 'textfield',
            fieldLabel: '产品名称',
            name: 'productName',
            emptyText: '此处不能为空',
            allowBlank: false,
            blankText: '此处不能为空',
            id: 'empProductName'
        }, {
            xtype: 'textfield',
            fieldLabel: '产品规格',
            name: 'format',
            emptyText: '此处不能为空',
            allowBlank: false,
            blankText: '此处不能为空',
            id: 'format'
        }, {
            xtype: 'textfield',
            fieldLabel: '产品零售价',
            name: 'retailPrice',
            emptyText: '此处不能为空',
            allowBlank: false,
            blankText: '此处不能为空',
            id: 'retailPrice'
        }, {
            xtype: 'textfield',
            fieldLabel: '进货价格',
            name: 'purchasePrice',
            emptyText: '此处不能为空',
            allowBlank: false,
            blankText: '此处不能为空'
        }, {
            xtype: 'textfield',
            fieldLabel: '进货数量',
            name: 'purchaseProductNum',
        }, {
            xtype: 'combo',
            fieldLabel: '进货状态',
            emptyText: '此处不能为空',
            allowBlank: false,
            blankText: '此处不能为空',
            name: 'purchaseStatus',
            store: Ext.create("Ext.data.Store", {
                fields: ["name", "value"],
                data: [
                    { name: '已完成', value: '已完成' },
                    { name: '已退回', value: '已退回' },
                    { name: '采购中', value: '采购中' },
                ]
            }),
            displayField: 'name',
            valueField: 'value',
            editable: false,
            queryMode: 'local',
            triggerAction: 'all',
        }]
    }],
    buttons: ['->', {
        xtype: 'button',
        text: 'Submit',
        handler: 'submitDetailEditForm'
    }, {
            xtype: 'button',
            text: 'Close',
            handler: function (btn) {
                btn.up('window').close();
            }
        }, '->']
});