Ext.define('Admin.view.stockDetail.StockDetailAddWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.stockDetailAddWindow',
    height: 600,
    minHeight: 350,
    minWidth: 300,
    width: 500,
    title: '库存调拨',
    closable: true,
    constrain: true,
    defaultFocus: 'textfield',
    modal:true,
    layout: 'fit',
    items: [{
        scrollable: true,
        id:'form',
        xtype: 'form',
        layout: 'form',
        padding: '10px',
        ariaLabel: 'Enter your name',
		items: [{
            xtype: 'textfield',
            fieldLabel: 'id',
            name:'id',
            hidden: true,
            readOnly: true
        },{
            id:'getRepo',
            xtype: 'combobox',
            name: 'getRepo',
            fieldLabel: '调出仓库',
            store: new Ext.data.Store( {
                    proxy : new Ext.data.HttpProxy( {
                        url : 'repo/setRepo'//提交到某action的某方法
                    }),
                    reader : new Ext.data.JsonReader( {rootProperty:""}, []),//需要显示的数据实体字段
                    autoLoad : true
                }),
            displayField : 'name',
            hiddenName : 'getRepo',
            valueField : 'value', 
            triggerAction : 'all',
            editable : false,
            allowBlank : false,
            listeners:{
                select:function(combo){
                    var count=0;
                    var form = Ext.getCmp('form');
                    while(true){
                        if(Ext.getCmp('com'+count)!=null){
                            form.remove(Ext.getCmp('com'+count));
                            form.remove(Ext.getCmp('text'+count));
                            count++;
                        }else if(Ext.getCmp('com'+count)==null){
                            break;
                        }
                    }
                }
            }
              
            
        },{
            id:'setRepo',
            xtype: 'combobox',
            name: 'setRepo',
            fieldLabel: '调入仓库/门店',
            store: new Ext.data.Store( {
                    proxy : new Ext.data.HttpProxy( {
                        url : 'repo/getRepo'//提交到某action的某方法
                    }),
                    reader : new Ext.data.JsonReader( {rootProperty:""}, []),//需要显示的数据实体字段
                    autoLoad : true
                }),
                displayField : 'name',
                hiddenName : 'setRepo',
                valueField : 'value', 
                triggerAction : 'all',
                editable : false,
                allowBlank : false
        }]
    }],
	buttons: ['->',{
        xtype: 'button',
        text: '提交',
        handler: 'submitAddForm'
    },'->',{
        xtype: 'button',
        text: '添加商品栏',
        handler: function(btn) {
            var url=Ext.getCmp('getRepo').getValue();
            //alert(url);
            var count=0;
            while(true){
                if(Ext.getCmp('com'+count)!=null){
                    count++;
                }else if(Ext.getCmp('com'+count)==null){
                    break;
                }
            }
            var com =new Ext.form.ComboBox ({
                id:'com'+count,
                name: 'getProductName',
                fieldLabel: '商品名',
                store: new Ext.data.Store( {
                        proxy : new Ext.data.HttpProxy( {
                            url : 'stockDetail/getProduct?repoid='+url//提交到某action的某方法
                        }),
                        reader : new Ext.data.JsonReader( {rootProperty:""}, []),//需要显示的数据实体字段
                        autoLoad : true
                    }),
                    displayField : 'name',
                    hiddenName : 'getBrandName',
                    valueField : 'value', 
                    triggerAction : 'all',
                    editable : false,
                    allowBlank : false
            });
           
            var textfield= new Ext.form.TextField({
                id:'text'+count,
                fieldLabel: '数量'
            });
            var win = btn.up('window');
            var form = win.down('form');
            form.add(com);
            form.add(textfield);
        }
    }]
});