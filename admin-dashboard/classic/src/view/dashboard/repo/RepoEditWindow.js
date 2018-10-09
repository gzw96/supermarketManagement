Ext.define('Aria.view.dashboard.repo.RepoEditWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.repoEditWindow',
    height: 650,
    minHeight: 100,
    minWidth: 300,
    width: 500,
    scrollable: true,
    title: 'Edit Repo Window',
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
            fieldLabel: '名称',
            name: 'repoName',
            emptyText: '此处不能为空',
            allowBlank: false,
            blankText: '此处不能为空'
        }, {
            xtype: 'combo',
            fieldLabel: '类型',
            emptyText: '此处不能为空',
            allowBlank: false,
            blankText: '此处不能为空',
            name: 'type',
            store: Ext.create("Ext.data.Store", {
                fields: ["name", "value"],
                data: [
                    { name: '仓库', value: '仓库' },
                    { name: '门店', value: '门店' }
                ]
            }),
            displayField: 'name',
            valueField: 'value',
            editable: false,
            queryMode: 'local',
            triggerAction: 'all',
        }, {
            xtype: 'textfield',
            fieldLabel: '电话',
            name: 'repoPhone',
            emptyText: '固话格式：区号-固话号码',
            allowBlank: false,
            blankText: '此处不能为空',
            regex: /^((\d{3,4}-)*\d{7,8}(-\d{3,4})*|13\d{9})$/,
            regexText: '手机或固话格式不正确'
        }, {
            xtype: 'textfield',
            fieldLabel: '地址',
            name: 'address'
        }, {
            xtype: 'datefield',
            fieldLabel: '建立日期',
            name: 'buildDate',
            format: 'Y/m/d'
        }, , {
            xtype: 'textfield',
            fieldLabel: '最大库存',
            name: 'maxSize'
        }, {
            xtype: 'textfield',
            fieldLabel: '最小库存',
            name: 'minSize'
        }, {
            xtype: "combo", 
            name:'userRealName',        
            store: Ext.create("Ext.data.Store", {
                fields: ["userRealName", "workNum"],
                proxy: {
                    type: "ajax",
                    url: '/repo/getusers',
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

            fieldLabel: "负责人姓名",
            typeAhead: true,//设置为true，当开始输入字符时，在指定的延迟之后会自动匹配剩下的内容，如果找到了匹配的 内容则自动选中它 (typeAheadDelay) (默认值为 false)
            listeners:{
                select:function(combo,records){
                    Ext.getCmp('empGroupName').setValue(records.get("workNum"));
                }
            }
        }, {
            xtype: 'textfield',
            fieldLabel: '负责人工号',
            name: 'workNum',
            id : 'empGroupName'
        }, {
            xtype: 'combo',
            fieldLabel: '当前状态',
            emptyText: '此处不能为空',
            allowBlank: false,
            blankText: '此处不能为空',
            name: 'status',
            store: Ext.create("Ext.data.Store", {
                fields: ["name", "value"],
                data: [
                    { name: '可用', value: '可用' },
                    { name: '不可用', value: '不可用' }
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
        handler: 'submitEditForm'
    }, {
            xtype: 'button',
            text: 'Close',
            handler: function (btn) {
                btn.up('window').close();
            }
        }, '->']
});