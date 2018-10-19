Ext.define('Admin.view.purchase.createsheet.CreatesheetGrid', {		//1.修改文件路径
    extend: 'Ext.grid.Panel',					//2.继承的组件类型
    //3.重写继承组件的属性：
    id: 'createsheetGrid',
    xtype: 'createsheetGrid',
    requires: [
        'Ext.button.Button',
        'Ext.form.field.Text',
        'Ext.form.field.File',
        'Ext.form.field.HtmlEditor',
        'Ext.form.field.TextArea',
        'Ext.form.field.Time',
        'Ext.form.field.ComboBox',
        'Ext.form.field.Date',
        'Ext.form.field.Radio',
        'Ext.form.field.Hidden',
        'Ext.tip.QuickTipManager',
        'Ext.grid.plugin.CellEditing'
    ],
    title: '<b>商品清单</b>',
    bind: '{createsheetLists}',
    frame: true,
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    plugins: {
        cellediting: {
            clicksToEdit: 1
        }
    },
    columns: [
        { text: 'ID', dataIndex: 'id', hidden: true },
        { text: '商品名', dataIndex: 'productName', width: 150 },
        { text: '商品编号', dataIndex: 'productNum', width: 150 },
        { text: '商品规格', dataIndex: 'format', width: 150 },
        { text: '零售价', dataIndex: 'retailPrice', width: 150 },
        {
            text: '进货价（请输入）',
            dataIndex: 'purchasePrice',
            width: 150,
            editor: new Ext.form.field.Number({
                allowBlank: false,
                minValue: 0,
                step: 0.01,
            })
        },
        {
            text: '进货数量（请输入）',
            dataIndex: 'purchaseProductNum',
            width: 150,
            editor: new Ext.form.field.Number({
                allowBlank: false,
                minValue: 1
            })
        },
        {
            text: '小结',
            dataIndex: 'sum',
            width: 150,
        },
        {
            header: '操作',
            menuDisabled: true,
            xtype: 'actioncolumn',
            flex: 1,
            items: [{
                iconCls: 'x-fa fa-trash',
                handler: function (grid, rowIndex, colIndex) {
                    grid.getStore().removeAt(rowIndex);
                }
            }]
        }

    ],
    plugins: Ext.create("Ext.grid.plugin.CellEditing", {//设置sum的值
        clicksToEdit: 1,
        listeners: {
            edit: function (edit, e) {
                var purchasePrice = e.record.get("purchasePrice");
                var purchaseProductNum = e.record.get("purchaseProductNum");
                var sum = 0;
                if ((e.field == "purchasePrice")) {
                    e.record.set("sum", purchaseProductNum * purchasePrice);
                }
                if ((e.field == "purchaseProductNum")) {
                    e.record.set("sum", purchaseProductNum * purchasePrice);

                }


                for (var i = 0; i < Ext.getCmp('createsheetGrid').getStore().getData().length; i++) {
                    sum += Ext.getCmp('createsheetGrid').getStore().getData().getAt(i).data.purchaseProductNum * Ext.getCmp('createsheetGrid').getStore().getData().getAt(i).data.purchasePrice;
                }

                var satisfied = Ext.getCmp('createsheetGridForm').items.items[3].items.items;
                satisfied[1].setValue(sum);
                e.record.commit();
            }
        }
    })
});