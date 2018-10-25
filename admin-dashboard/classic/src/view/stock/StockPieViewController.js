Ext.define('Admin.view.stock.StockPieViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.pie-basic',

    onAxisLabelRender: function (axis, label, layoutContext) {
        // Custom renderer overrides the native axis label renderer.
        // Since we don't want to do anything fancy with the value
        // ourselves except adding a thousands separator, but at the same time
        // don't want to loose the formatting done by the native renderer,
        // we let the native renderer process the value first.
        var value = layoutContext.renderer(label);
        return value ;
    },

    onSeriesLabelRender: function (value) {
       // return Ext.util.Format.number(value / 1000, '0K');
    },

    onGridColumnRender: function (v) {
        return Ext.util.Format.number(v, '0,000');
    },

    onPreview: function () {
        if (Ext.isIE8) {
            Ext.Msg.alert('Unsupported Operation', 'This operation requires a newer version of Internet Explorer.');
            return;
        }
        var chart = this.lookup('chart');
        chart.preview();
    },

    onDataRender: function (v) {
        return v + '%';
    },

    onSeriesTooltipRender: function (tooltip, record, item) {
        var total=0;
         Ext.getCmp('myPie').store.each(function(record) {
                total += record.get('value');
         });
        thispercent=record.get('value')*100/total;
        thispercent = thispercent.toFixed(2);
        tooltip.setHtml(record.get('name') + ': ' + thispercent + '%');
    },

    searchComboboxSelectChuang:function(combo,record,index){
        var searchField = this.lookupReference('searchFieldName').getValue();
        if(searchField===('selectrepo')){
            this.lookupReference('searchCom').show();
        }
    },

    quickSearch:function(btn){
        var store=new Ext.data.Store( {
                    fields: ['name', 'value' ],
                    proxy : new Ext.data.HttpProxy( {
                        type: 'rest',
                        url : '/stock/getRepoPie',//提交到某action的某方法
                        reader:{
                            type:'json'
                        },
                        writer: {
                            type: 'json'
                        }
                    }),
                    autoLoad : true
                });
        var myPie= Ext.getCmp('myPie');
        myPie.setStore(store);
        var stor=myPie.getStore();
        var searchField = this.lookupReference('searchFieldName').getValue();
        var searchTextFieldValue = Ext.getCmp('searchCom').getValue();
        if(searchField=='selectrepo'){
            if(searchTextFieldValue!=null){ 
                var reoid=searchTextFieldValue;
                Ext.apply(stor.proxy.extraParams,{
                    reoid:reoid
                });     
                stor.load();
                myPie.show();
                Ext.getCmp('repoCloumn').hide();
            }else{
                Ext.Msg.alert('Error', '仓库不能为空');
            }
        }else{
             Ext.Msg.alert('Error', '请先选择条件');
        }
    },

    back:function(btn){
        
        Ext.getCmp('myPie').hide();
        Ext.getCmp('repoCloumn').show();
    }


});