Ext.define('Admin.view.product.ProductEditWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.productEditWindow',
    height: 480,
    minHeight: 480,
    minWidth: 500,
    width: 500,
    scrollable: false,
    title: 'Edit product Window',
    closable: true,
    constrain: true,
    defaultFocus: 'textfield',
    modal:true,
	items: [{
        layout:'absolute',
        x: 150,
        items:[{
            xtype: 'image',
            id: 'imageId',
            width: 143,
            height: 162
            
        }]
    },{
        xtype: 'form',
        layout: 'form',
       
        ariaLabel: 'Enter your name',
        items: [{
            xtype: 'textfield',       
            fieldLabel: 'id',
            name:'id',
            hidden: true,
            readOnly: true
        },{
            xtype: 'filefield',
            id: 'upload',
            fieldLabel: '品牌商标',
            name:'productImg',
            allowBlank: false,
            listeners : {
                'render' : function() {
                    Ext.getCmp('upload').on('change',function(field, newValue, oldValue) {
                        var file = field.fileInputEl.dom.files.item(0);
                        var fileReader = new FileReader('file://'+newValue);
                        fileReader.readAsDataURL(file);
                        fileReader.onload=function(e){
                           var a= Ext.getCmp('imageId').setSrc(e.target.result);
                        }
                    });
                }
            }
        },{
            xtype: 'textfield',       
            fieldLabel: 'id',
            name:'id',
            hidden: true,
            readOnly: true
        },{
            xtype: 'textfield',
            fieldLabel: '品牌名',
            name:'productName',
            allowBlank: false
        },{
            xtype: 'textfield',
            name: 'productPrice',
            fieldLabel: '商品单价',
            allowBlank: false
        },{
            xtype: 'radiogroup',
            fieldLabel: '商品状态',
            items: [{
                name: 'status',
                boxLabel:'在售',
                inputValue: '1',
                checked:true
            },{
                name: 'status',
                boxLabel:'下架',
                inputValue: '0',
            }]
        },{
            xtype: 'combobox',
            name: 'getBrandName',
            id:'getBrandName',
            fieldLabel: '商品品牌',
            store: new Ext.data.Store( {
                    proxy : new Ext.data.HttpProxy( {
                        url : 'brand/getBrand'
                    }),
                    reader : new Ext.data.JsonReader( {rootProperty:""}, []),
                    autoLoad : true
                }),
                displayField : 'name',
                hiddenName : 'getBrandName',
                valueField : 'value', 
                triggerAction : 'all',
                editable : false,
                allowBlank : false
        }]
    }],buttons: ['->',{
        xtype: 'button',
        text: '提交',
        handler: 'submitEditForm'
    },{
        xtype: 'button',
        text: 'Close',
        handler: function(btn) {
            btn.up('window').close();
        }
    },'->']
  
});
