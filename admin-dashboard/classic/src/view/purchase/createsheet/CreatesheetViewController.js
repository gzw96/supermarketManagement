Ext.define('Admin.view.purchase.createsheet.CreatesheetViewController', {
	extend: 'Ext.app.ViewController',
	alias: 'controller.createsheetViewController',

	// 下拉框-选择事件
	onSelectProductColumn: function (record) {
		var data = record.getSelection().data;
		var grid = Ext.getCmp('createsheetGrid');
		var dataCollection = grid.getStore().getData();
		var flag = true;

		// 防止重复添加相同的产品
		if (dataCollection) {
			for (var i = 0; i < dataCollection.length; i++) {
				if (dataCollection.getAt(i).data.prouctNum == data.productNum) {
					flag = false;
					break;
				}

			}
		}

		if (flag) {
			grid.getStore().add({
				id: data.id,
				productName: data.productName,
				productNum: data.productNum,
				format: data.format,
				retailPrice: data.retailPrice,
			});
		}
		//将文本框清空
		record.setValue("");
		//将下拉列表收回
		record.collapse();
	},

	clearCreatesheet: function () {
		var form = Ext.getCmp('createsheetGridForm');
		var grid = Ext.getCmp('createsheetGrid');
		form.reset();
		grid.getStore().removeAll();
	},


	saveCreatesheet: function () {
		var storeItems = Ext.getCmp('createsheetGrid').getStore().data.items;
		var satisfied = Ext.getCmp('createsheetGridForm').items.items[3].items.items;
		//获取grid和form的数据
		var mydata = {};
		var list = [];

		if (storeItems.length <= 0) {
			Ext.Msg.alert('提示', '销售商品不能为空');
			return;
		}

		mydata['purchaseNum'] = satisfied[0].getValue();
		mydata['statement'] = satisfied[1].getValue();
		mydata['payment'] = satisfied[2].getValue();
		mydata['method'] = satisfied[3].getValue();
		mydata['userRealName'] = satisfied[4].getValue();
		mydata['workNum'] = satisfied[5].getValue();
		mydata['supplierName'] = satisfied[6].getValue();
		mydata['repoName'] = satisfied[7].getValue();
		mydata['remark'] = satisfied[8].getValue();

		for (var i = 0; i < storeItems.length; i++) {
			list[i] = {
				'id': storeItems[i].data.id,
				'purchasePrice': storeItems[i].data.purchasePrice,
				'purchaseProductNum': storeItems[i].data.purchaseProductNum,
				'purchaseNum': satisfied[0].getValue()
			};
		}

		mydata['list'] = list;

		//先判断表中必选项是否为空再传值
		if ((satisfied[0].getValue() != '') & (satisfied[1].getValue() != '') & (satisfied[2].getValue() != '') & (satisfied[3].getValue() != '') & (satisfied[4].getValue() != '') & (satisfied[5].getValue() != '') & (satisfied[6].getValue() != '') & (satisfied[7].getValue() != '')) {

			Ext.Ajax.request({
				url: 'createsheet/saveDetail',
				method: 'post',
				'headers': { 'Content-Type': 'application/json' },
				'params': Ext.encode(mydata),
				success: function (response, options) {
					Ext.Msg.alert('提示', '保存成功!');
				},
				failure: function () {
					Ext.Msg.alert('提示', '连接失败!');
				}
			});
		} else {
			Ext.Msg.alert("错误", "填写格式有误！");
		}

	}
});