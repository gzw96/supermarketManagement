Ext.define('Admin.view.dashboard.repo.RepoViewController', {
	extend: 'Ext.app.ViewController',
	alias: 'controller.repoViewController',
	/********************************************** Controller View *****************************************************/
	/*Add*/
	openAddWindow: function (grid, rowIndex, colIndex) {
		grid.up('container').add(Ext.widget('repoAddWindow')).show();
	},
	/*Edit*/
	openEditWindow: function (grid, rowIndex, colIndex) {
		var record = grid.getStore().getAt(rowIndex);
		//获取选中数据的字段值：console.log(record.get('id')); 或者 console.log(record.data.id);
		if (record) {
			var win = grid.up('container').add(Ext.widget('repoEditWindow'));
			win.show();
			win.down('form').getForm().loadRecord(record);
		}
	},
	/*Search More*/
	openSearchWindow: function (toolbar, rowIndex, colIndex) {
		toolbar.up('grid').up('container').add(Ext.widget('repoSearchWindow')).show();
	},
	/*combobox选中后控制对应输入（文本框和日期框）框显示隐藏*/
	searchComboboxSelectChuang: function (combo, record, index) {
		//alert(record.data.name);
		var searchField = this.lookupReference('searchFieldName').getValue();
		if (searchField === 'status') {
			this.lookupReference('searchFieldValue').hide();
			this.lookupReference('searchDataFieldValue').show();
		} else {
			this.lookupReference('searchFieldValue').show();
			this.lookupReference('searchDataFieldValue').hide();
		}

	},
	/********************************************** Submit / Ajax / Rest *****************************************************/
	/*Add Submit*/
	submitAddForm: function (btn) {
		var win = btn.up('window');
		var form = win.down('form');
		var record = Ext.create('Admin.model.dashboard.repo.RepoModel');
		var values = form.getValues();//获取form数据
		if (form.isValid()) {
			record.set(values);
			record.save();
			Ext.data.StoreManager.lookup('repoGridStore').load();
			win.close();
		}
		else {
			Ext.Msg.alert("错误", "填写格式有误！");
		}
	},
	/*Edit Submit*/
	submitEditForm: function (btn) {
		var win = btn.up('window');
		var store = Ext.data.StoreManager.lookup('repoGridStore');
		var form = win.down('form');
		var values = win.down('form').getValues();//获取form数据
		var record = store.getById(values.id);//获取id获取store中的数据
		if (form.isValid()) {
			record.set(values);//rest put 
			//store.load();
			win.close();
		}
		else {
			Ext.Msg.alert("错误", "填写格式有误！");
		}
	},

	/*Quick Search*/
	quickSearch: function (btn) {
		var searchField = this.lookupReference('searchFieldName').getValue();
		var searchValue = this.lookupReference('searchFieldValue').getValue();
		var searchDataFieldValue = this.lookupReference('searchDataFieldValue').getValue();
		var store = btn.up('gridpanel').getStore();
		//var store = Ext.getCmp('userGridPanel').getStore();// Ext.getCmp(）需要在OrderPanel设置id属性
		Ext.apply(store.proxy.extraParams, { repoName: "", status: "" });

		if (searchField === 'repoName') {
			Ext.apply(store.proxy.extraParams, { repoName: searchValue });
		}
		
		if (searchField === 'status') {
			Ext.apply(store.proxy.extraParams, { status: searchDataFieldValue });
		}
		store.load({ params: { start: 0, limit: 20, page: 1 } });
	},
	submitSearchForm: function (btn) {
		var store = Ext.data.StoreManager.lookup('repoGridStore');
		var win = btn.up('window');
		var form = win.down('form');
		var values = form.getValues();
		Ext.apply(store.proxy.extraParams, { repoName: "", status: "" });
		Ext.apply(store.proxy.extraParams, {
			repoName: values.repoName,
			status: values.status,
		});
		store.load({ params: { start: 0, limit: 20, page: 1 } });
		win.close();
	},
	/*Delete One Row*/
	deleteOneRow: function (grid, rowIndex, colIndex) {
		Ext.MessageBox.confirm('提示', '确定要进行删除操作吗？数据将无法还原！',
			function (btn, text) {
				if (btn == 'yes') {
					var store = grid.getStore();
					var record = store.getAt(rowIndex);
					store.remove(record);//DELETE //http://localhost:8081/order/112
					//store.sync();
				}
			}
			, this);
	},
	/*Delete More Rows*/
	deleteMoreRows: function (btn, rowIndex, colIndex) {
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
						url: '/repo/deletes',
						method: 'post',
						params: {
							//ids[] :selectIds
							ids: selectIds
						},
						success: function (response, options) {
							var json = Ext.util.JSON.decode(response.responseText);
							if (json.success) {
								Ext.Msg.alert('操作成功', json.msg, function () {
									grid.getStore().reload();
								});
							} else {
								Ext.Msg.alert('操作失败', json.msg);
							}
						}
					});
				}
			});
		} else {
			Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
		}
	}

	
});