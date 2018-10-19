Ext.define('Aria.view.dashboard.supplier.SupplierEditWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.supplierEditWindow',
    height: 600,
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
            fieldLabel: '供应商名称',
            name: 'supplierName',
            emptyText: '此处不能为空',
            allowBlank: false,
            blankText: '此处不能为空'
        }, {
            xtype: 'textfield',
            fieldLabel: '供应商负责人',
            name: 'supplierPeople'
        }, {
            xtype: 'textfield',
            fieldLabel: '供应商电话',
            name: 'supplierPhone',
            emptyText: '此处不能为空',
            allowBlank: false,
            blankText: '此处不能为空',
            regex: /^((\d{3,4}-)*\d{7,8}(-\d{3,4})*|13\d{9})$/,
            regexText: '手机或固话格式不正确'
        }, {
            xtype: 'textfield',
            fieldLabel: '银行账户',
            name: 'bankAccount',
            emptyText: '此处不能为空',
            allowBlank: false,
            blankText: '此处不能为空'
        }, {
            xtype: 'textfield',
            fieldLabel: '开户银行',
            name: 'bankName'
        }, {
            xtype: 'textfield',
            fieldLabel: '地址',
            name: 'address'
        }, {
            xtype: 'textfield',
            fieldLabel: '备注',
            name: 'remark'
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