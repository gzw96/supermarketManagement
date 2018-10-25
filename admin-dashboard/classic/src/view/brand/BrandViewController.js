Ext.define('Admin.view.brand.BrandViewController', {
	extend: 'Ext.app.ViewController',
	alias: 'controller.brandViewController',
	/*Add*/
	openAddWindow:function(toolbar, rowIndex, colIndex){
		toolbar.up('panel').up('container').add(Ext.widget('brandAddWindow')).show();
	},
	/*Edit*/
	openEditWindow:function(grid, rowIndex, colIndex){
		var record = grid.getStore().getAt(rowIndex);
		//获取选中数据的字段值：console.log(record.get('id')); 或者 console.log(record.data.id);
		var win = grid.up('container').add(Ext.widget('brandEditWindow'));
		//Ext.get('imageId').dom.src="'resources/images/"+record.get('brandImg')+"'"; 
		win.show();
		win.down('form').getForm().loadRecord(record);
		Ext.getCmp('imageId').setSrc("resources/images/"+record.get('brandImg'));
		
	},
	/*Search More*/	
	openSearchWindow:function(toolbar, rowIndex, colIndex){
		toolbar.up('panel').up('container').add(Ext.widget('brandSearchWindow')).show();
	},
	/*combobox选中后控制对应输入（文本框和日期框）框显示隐藏*/
	searchComboboxSelectChuang:function(combo,record,index){
		//alert(record.data.name);
		/*
		var searchField = this.lookupReference('searchFieldName').getValue();
		if(searchField==='createTime'){
			this.lookupReference('searchFieldValue').hide();
			this.lookupReference('searchDataFieldValue').show();
			this.lookupReference('searchDataFieldValue2').show();
		}else{
			this.lookupReference('searchFieldValue').show();
			this.lookupReference('searchDataFieldValue').hide();
			this.lookupReference('searchDataFieldValue2').hide();
		}*/
	},

	searchComboboxWindows:function(combo,record,index){
		
		var searchField = this.lookupReference('searchWindowField').getValue();
		if(searchField==='id'){
			this.lookupReference('searchTextValue1').show();
			this.lookupReference('searchTextValue2').hide();
		}else if(searchField==='brandName'){
			this.lookupReference('searchTextValue2').show();
			this.lookupReference('searchTextValue1').hide();
		}
	},
	/********************************************** Submit / Ajax / Rest *****************************************************/
	/*Add Submit*/	
	submitAddForm:function(btn){
		var win = btn.up('window');
		var form = win.down('form');
		var record = Ext.create('Admin.model.brand.BrandModel');
		var values  =form.getValues();//获取form数据
		record.set(values);
		record.save();
		Ext.data.StoreManager.lookup('brandStroe').load();
		win.close();
	},
	submitEditForm:function(btn){
		var form = btn.up('window').down('form');
		form.getForm().submit({       
			url:'brand/upload',
			method : 'POST',
			waitMsg: '正在上传，请耐心等待....',
			success: function(form, action){    
					Ext.Msg.alert('Success', action.result.msg,function(){
					btn.up('window').close();
					Ext.data.StoreManager.lookup('processDefinitionStroe').load();
				});       
			}, 
			failure: function(form, action){
				Ext.Msg.alert('Error', action.result.msg);
			}
		});
	},
	/*Quick Search*/	
	quickSearch:function(btn){
		var searchField = this.lookupReference('searchFieldName').getValue();
		var searchDataFieldValue1 = this.lookupReference('searchDataFieldValue1').getValue();
		var searchDataFieldValue2 = this.lookupReference('searchDataFieldValue2').getValue();
		if((searchDataFieldValue1&&searchDataFieldValue2)!=null){
			searchDataFieldValue1=Ext.util.Format.date(searchDataFieldValue1, 'Y-m-d');
			searchDataFieldValue2=Ext.util.Format.date(searchDataFieldValue2, 'Y-m-d');
			var store =	btn.up('gridpanel').getStore();
			//alert(searchDataFieldValue2);
			//var store = Ext.getCmp('userGridPanel').getStore();// Ext.getCmp(）需要在LeavePanel设置id属性
			//Ext.apply(store.proxy.extraParams, {createTime:""});
			/*var a[0]=searchDataFieldValue1;
			a[1]=searchDataFieldValue2;*/
			var myArray=new Array();
			myArray[0]=searchDataFieldValue1;
			myArray[1]=searchDataFieldValue2;	
			/*Ext.Ajax.request({ 
						url : '/brand/quicksearch', 
						method : 'post', 
						//traditional: true,
						params : { 
							myArray:myArray
						}, 
						success: function(response, options) {
							store.load({params:{start:0, limit:10, page:1}});
						}
			});*/
			myArray[2]=searchField;
			store.proxy.url='/brand/quicksearch';
			Ext.apply(store.proxy.extraParams,{
				myArray:myArray
			});		
			store.load({params:{start:0, limit:10, page:1}});
		}else{
			Ext.Msg.alert('Error', '两个日期条件不能为空');
		}
	},


	submitSearchForm:function(btn){
		var win = btn.up('window');
		var store =	Ext.data.StoreManager.lookup('brandStroe');
		var searchWindowField = this.lookupReference('searchWindowField').getValue();
		var searchTextValue1 = this.lookupReference('searchTextValue1').getValue();
		var searchTextValue2 = this.lookupReference('searchTextValue2').getValue();
		var toSubmit=new Array();
		if(searchWindowField==='id'){
			toSubmit[0]='id';
			toSubmit[1]=searchTextValue1;
		}else if(searchWindowField==='brandName'){
			toSubmit[0]='brandName';
			toSubmit[1]=searchTextValue2;
		}
		store.proxy.url='/brand/moresearch';
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
				Ext.Ajax.request({ 
					url : '/brand/delete', 
					method : 'post', 
					params : { 
						id :record.id
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
						url : '/brand/deletes', 
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
