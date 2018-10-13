Ext.define('Admin.view.stock.StockViewController', {
	extend: 'Ext.app.ViewController',
	alias: 'controller.stockViewController',
	openAddWindow:function(btn, rowIndex, colIndex){
		var grid = btn.up('gridpanel');
		var selModel = grid.getSelectionModel();
		if (selModel.hasSelection()) {
			Ext.Msg.confirm("提示", "确定要进行初始化操作吗？", function (button) {
				if (button == "yes") {
					var rows = selModel.getSelection();
					var selectIds = []; //要删除的id
					Ext.each(rows, function (row) {
						selectIds.push(row.data.id);
					});
					Ext.Ajax.request({ 
						url : '/stock/initRepo', 
						method : 'post', 
						params : { 
							ids :selectIds
						}, 
						success: function(response, options) {
							var json = Ext.util.JSON.decode(response.responseText);
							if(json.success){
								Ext.Msg.alert('操作成功', json.msg, function() {
								grid.reload();
							});
							}else{
								Ext.Msg.alert('操作失败', json.msg);
							}
						}
					});
				}
			});
		}else {
			Ext.Msg.alert("错误", "没有任何行被选中，无法进行初始化操作！");
		}
	}
});
