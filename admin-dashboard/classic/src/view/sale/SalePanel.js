Ext.define('Admin.view.sale.SalePanel', {
    extend: 'Ext.grid.Panel',
    xtype: 'salePanel',
	id:'salePanel',
	
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
	title: '<b>销售清单</b>',
    bind: '{saleLists}',
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
        { text: '零售价', dataIndex: 'productPrice', width: 150 },
        {
            text: '销售数量（请输入）',
            dataIndex: 'saleProductNum',
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
                var productPrice = e.record.get("productPrice");
				
                var saleProductNum = e.record.get("saleProductNum");
                var sum = 0;
                if ((e.field == "productPrice")) {
                    e.record.set("sum", saleProductNum * productPrice);
                }
                if ((e.field == "saleProductNum")) {
                    e.record.set("sum", saleProductNum * productPrice);
                }

                for (var i = 0; i < Ext.getCmp('salePanel').getStore().getData().length; i++) {
                    sum += Ext.getCmp('salePanel').getStore().getData().getAt(i).data.saleProductNum * Ext.getCmp('salePanel').getStore().getData().getAt(i).data.productPrice;
                }

                var satisfied = Ext.getCmp('saleForm').items.items[3].items.items;
                satisfied[1].setValue(sum);
                e.record.commit();
            }
        }
    })
});
