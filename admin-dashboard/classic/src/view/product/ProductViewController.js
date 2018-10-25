Ext.define('Admin.view.product.ProductViewController', {
	extend: 'Ext.app.ViewController',
	alias: 'controller.productViewController',
	/*Add*/
	openAddWindow:function(toolbar, rowIndex, colIndex){
		toolbar.up('panel').up('container').add(Ext.widget('productAddWindow')).show();
	},
	/*Edit*/
	openEditWindow:function(grid, rowIndex, colIndex){
		var record = grid.getStore().getAt(rowIndex);
		//获取选中数据的字段值：console.log(record.get('id')); 或者 console.log(record.data.id);
		var win = grid.up('container').add(Ext.widget('productEditWindow'));
		//Ext.get('imageId').dom.src="'resources/images/"+record.get('productImg')+"'"; 
		win.show();
		win.down('form').getForm().loadRecord(record);
		Ext.getCmp('imageId').setSrc("resources/images/"+record.get('productImg'));
		Ext.getCmp('getBrandName').setRawValue(record.get('brand').brandName);    //name
		Ext.getCmp('getBrandName').setValue(record.get('brand').id);			//value
	},
	/*Search More*/	
	openSearchWindow:function(toolbar, rowIndex, colIndex){
		toolbar.up('panel').up('container').add(Ext.widget('productSearchWindow')).show();
	},
	/*combobox选中后控制对应输入（文本框和日期框）框显示隐藏*/
	searchComboboxSelectChuang:function(combo,record,index){
		var searchField = this.lookupReference('searchFieldName').getValue();
		if(searchField===('createTime'||'updateTime')){
			this.lookupReference('searchDataFieldValue1').show();
			this.lookupReference('searchDataFieldValue2').show();
			this.lookupReference('searchTextFieldValue1').hide();
			this.lookupReference('searchTextFieldValue2').hide();
		}else if(searchField==='productPrice'){
			this.lookupReference('searchTextFieldValue1').show();
			this.lookupReference('searchTextFieldValue2').show();
			this.lookupReference('searchDataFieldValue1').hide();
			this.lookupReference('searchDataFieldValue2').hide();
		}
	},

	searchComboboxWindows:function(combo,record,index){
		
		var searchField = this.lookupReference('searchWindowField').getValue();
		if(searchField==='id'){
			this.lookupReference('searchTextValue1').show();
			this.lookupReference('searchTextValue2').hide();
			this.lookupReference('searchTextValue3').hide();
			this.lookupReference('searchTextValue4').hide();
		}else if(searchField==='productName'){
			this.lookupReference('searchTextValue2').show();
			this.lookupReference('searchTextValue1').hide();
			this.lookupReference('searchTextValue3').hide();
			this.lookupReference('searchTextValue4').hide();
		}else if(searchField==='productNum'){
			this.lookupReference('searchTextValue4').show();
			this.lookupReference('searchTextValue1').hide();
			this.lookupReference('searchTextValue2').hide();
			this.lookupReference('searchTextValue3').hide();
		}else if(searchField==='getBrandName'){
			this.lookupReference('searchTextValue3').show();
			this.lookupReference('searchTextValue1').hide();
			this.lookupReference('searchTextValue2').hide();
			this.lookupReference('searchTextValue4').hide();
		}
	},
	/********************************************** Submit / Ajax / Rest *****************************************************/
	/*Add Submit*/	
	submitAddForm:function(btn){
		var win = btn.up('window');
		var form = win.down('form');
		var record = Ext.create('Admin.model.product.ProductModel');
		var values  =form.getValues();//获取form数据
		record.set(values);
		record.save();
		Ext.data.StoreManager.lookup('productStore').load();
		win.close();
	},
	submitEditForm:function(btn){
		var form = btn.up('window').down('form');
		form.getForm().submit({       
			url:'product/upload',
			method : 'POST',
			waitMsg: '正在上传，请耐心等待....',
			success: function(form, action){    
					Ext.Msg.alert('Success', action.result.msg,function(){
					btn.up('window').close();
					Ext.data.StoreManager.lookup('productStore').load();
				});       
			}, 
			failure: function(form, action){
				Ext.Msg.alert('Error', action.result.msg);
			}
		});
	},
	/*Quick Search*/	
	quickSearch:function(btn){
		var store =	btn.up('gridpanel').getStore();
		var searchField = this.lookupReference('searchFieldName').getValue();
		var searchDataFieldValue1 = this.lookupReference('searchDataFieldValue1').getValue();
		var searchDataFieldValue2 = this.lookupReference('searchDataFieldValue2').getValue();
		var searchTextFieldValue1 = this.lookupReference('searchTextFieldValue1').getValue();
		var searchTextFieldValue2 = this.lookupReference('searchTextFieldValue2').getValue();
		var myArray=new Array();
		if(searchField=='productPrice'){
			if((searchTextFieldValue1&&searchTextFieldValue2)!=null){
				myArray[0]=searchTextFieldValue1;
				myArray[1]=searchTextFieldValue2;
				myArray[2]=searchField;
				store.proxy.url='/product/quicksearch';
				Ext.apply(store.proxy.extraParams,{
					myArray:myArray
				});		
				store.load({params:{start:0, limit:10, page:1}});
			}else{
				Ext.Msg.alert('Error', '两个条件不能为空');
			}
		}else{
			if((searchDataFieldValue1&&searchDataFieldValue2)!=null){
				searchDataFieldValue1=Ext.util.Format.date(searchDataFieldValue1, 'Y-m-d');
				searchDataFieldValue2=Ext.util.Format.date(searchDataFieldValue2, 'Y-m-d');
				myArray[0]=searchDataFieldValue1;
				myArray[1]=searchDataFieldValue2;
				myArray[2]=searchField;
				store.proxy.url='/product/quicksearch';
				Ext.apply(store.proxy.extraParams,{
					myArray:myArray
				});		
				store.load({params:{start:0, limit:10, page:1}});
			}else{
				Ext.Msg.alert('Error', '两个条件不能为空');
			}
		}
	},


	submitSearchForm:function(btn){
		var win = btn.up('window');
		var store =	Ext.data.StoreManager.lookup('productStore');
		var searchWindowField = this.lookupReference('searchWindowField').getValue();
		var searchTextValue1 = this.lookupReference('searchTextValue1').getValue();
		var searchTextValue2 = this.lookupReference('searchTextValue2').getValue();
		var searchTextValue3 = this.lookupReference('searchTextValue3').getValue();
		var searchTextValue4 = this.lookupReference('searchTextValue4').getValue();
		var toSubmit=new Array();
		if(searchWindowField==='id'){
			toSubmit[0]='id';
			toSubmit[1]=searchTextValue1;
		}else if(searchWindowField==='productName'){
			toSubmit[0]='productName';
			toSubmit[1]=searchTextValue2;
		}else if(searchWindowField==='productNum'){
			toSubmit[0]='productNum';
			toSubmit[1]=searchTextValue4;
		}else if(searchWindowField==='getBrandName'){
			toSubmit[0]='getBrandName';
			toSubmit[1]=searchTextValue3;
		}
		store.proxy.url='/product/moresearch';
		Ext.apply(store.proxy.extraParams,{
			toSubmit:toSubmit
		});
		store.load({params:{start:0, limit:10, page:1}});
		win.close();
	},
	/*Delete One Row*/	
	deleteOneRow:function(grid, rowIndex, colIndex){
		var store = grid.getStore();
		var record = store.getAt(rowIndex);
		Ext.MessageBox.confirm('提示', '确定要进行删除操作吗？数据将无法还原！',function(btn, text){
			if(btn=='yes'){
				store.remove(record);
			}
		}, this);
		
	},
	/*Delete More Rows*/	
	deleteMoreRows:function(btn, rowIndex, colIndex){
		var grid = btn.up('gridpanel');
		var selModel = grid.getSelectionModel();
		if (selModel.hasSelection()) {
			Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
				if (button == "yes") {
					var rows = selModel.getSelection();
					var selectIds = []; //要删除的id
					Ext.each(rows, function (row) {
						selectIds.push(row.data.id);
					});
					Ext.Ajax.request({ 
						url : '/product/deletes', 
						method : 'post', 
						params : { 
							ids :selectIds
						}, 
						success: function(response, options) {
							var json = Ext.util.JSON.decode(response.responseText);
							if(json.success){
								Ext.Msg.alert('操作成功', json.msg, function() {
								grid.getStore().reload();
							});
							}else{
								Ext.Msg.alert('操作失败', json.msg);
							}
						}
					});
				}
			});
		}else {
			Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
		}
	},
	/*Star Leave Process*/	
	starLeaveProcess:function(grid, rowIndex, colIndex){
		var record = grid.getStore().getAt(rowIndex);
		Ext.Ajax.request({ 
			url : '/leave/start', 
			method : 'post', 
			params : {
				id :record.get("id")
			}, 
			success: function(response, options) {
				var json = Ext.util.JSON.decode(response.responseText);
				if(json.success){
					Ext.Msg.alert('操作成功', json.msg, function() {
					grid.getStore().reload();
				});
				}else{
					Ext.Msg.alert('操作失败', json.msg);
				}
			}
		});
	},	
	/*Cancel Leave Process*/	
	cancelLeaveProcess:function(grid, rowIndex, colIndex){
		Ext.Msg.alert("Title","Cancel Leave Process");
	}
});
