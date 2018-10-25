Ext.define('Admin.view.brand.BrandEditWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.brandEditWindow',
    height: 430,
    minHeight: 430,
    minWidth: 500,
    width: 500,
    scrollable: false,
    title: 'Edit brand Window',
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
            name:'brandImg',
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
            name:'brandName',
            allowBlank: false
        },{
            xtype: 'textfield',
            name: 'brandDesc',
            fieldLabel: '详情',
            allowBlank: false
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
