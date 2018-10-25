Ext.define('Admin.view.stock.StockPiePanel', {
    extend: 'Ext.panel.Panel',
    xtype: 'stockPiePanel',
    controller: 'pie-basic',

    requires: [
        'Ext.chart.*'
    ],

    items: [{
        tbar:[{
            xtype: 'combobox',
            reference:'searchFieldName',
            hideLabel: true,
            store:Ext.create("Ext.data.Store", {
                fields: ["name", "value"],
                data: [{ name: '选择仓库', value: 'selectrepo' }]
            }),
            displayField: 'name',
            valueField:'value',
            value:'select',
            editable: false,
            queryMode: 'local',
            triggerAction: 'all',
            emptyText: 'Select a state...',
            width: 150,
            listeners:{
                select: 'searchComboboxSelectChuang'
            }
        }, '-',{
             id:'searchCom',
            xtype: 'combobox',
            name: 'getRepo',
            store: new Ext.data.Store( {
                proxy : new Ext.data.HttpProxy( {
                    url : 'repo/getRepo'//提交到某action的某方法
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
            hidden:true,
            reference:'searchCom'
        },'-',{
            text: '查找',
            iconCls: 'fa fa-search',
            handler: 'quickSearch',
            itemId: 'searchButton'
        },{
            text: '返回总览',
            iconCls: 'fa fa-search',
            handler: 'back',
        }]
    },{
            html: '<p style="padding:50px;background-color: #D9EDF7;"></p>',
            border:0
    },{
        id:'repoCloumn',
        xtype: 'cartesian',
        width: '100%',
        height: 400,
        captions: {
            title: {
                text: '仓库总概况图',
                alignTo: 'chart'
            }
        },
        theme: 'Muted',
        interactions: ['itemhighlight'],
        animation: {
            duration: 200
        },
        store: {
            type: 'two-year-sales'
        },
        legend: {
            type: 'dom',
            docked: 'bottom'
        },
        axes: [{
            type: 'numeric3d',
            position: 'left',
            fields: ['value1', 'value2'],
            grid: true,
            title: '仓库容量',
            renderer: 'onAxisLabelRender'
        }, {
            type: 'category3d',
            position: 'bottom',
            fields: 'name',
            title: {
                text: '仓库名',
                translationX: -30
            },
            grid: true
        }],
        series: {
            type: 'bar3d',
            stacked: false,
            title: ['已用容量', '总容量'],
            xField: 'name',
            yField: ['value1', 'value2'],
            label: {
                field: ['value1', 'value2'],
                display: 'insideEnd',
                renderer: 'onSeriesLabelRender'
            },
            highlight: true,
            style: {
                inGroupGapWidth: -7
            }
        }
    },{
        id:'myPie',
        xtype: 'polar',
        reference: 'chart',
        theme: 'default-gradients',
        width: '100%',
        height: 500,
        insetPadding: 40,
        innerPadding: 20,
        store: {},
        hidden:true,
        legend: {
            docked: 'bottom'
        },
        interactions: ['rotate'],
        series: [{
            type: 'pie',
            angleField: 'value',
            label: {
                field: 'name',
                calloutLine: {
                    length: 60,
                    width: 3
                }
            },
            highlight: true,
            tooltip: {
                trackMouse: true,
                renderer: 'onSeriesTooltipRender'
            }
        }]
    }]  
});
