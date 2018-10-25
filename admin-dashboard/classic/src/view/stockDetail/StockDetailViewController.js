Ext.define('Admin.view.stockDetail.StockDetailViewController', {
	extend: 'Ext.app.ViewController',
	alias: 'controller.stockDetailViewController',
	/*Add*/
	openAddWindow:function(toolbar, rowIndex, colIndex){
		toolbar.up('panel').up('container').add(Ext.widget('stockDetailAddWindow')).show();
	},
	/*Edit*/
	openEditWindow:function(grid, rowIndex, colIndex){
		var all=0;
		var record = grid.getStore().getAt(rowIndex);
		var win = grid.up('container').add(Ext.widget('stockDetailEditWindow'));
		Ext.Ajax.request({ 
			url : '/stockDetail/purchasepro', 
			method : 'post', 
			params : { 
				purid :record.id
			}, 
			success: function(response) {
				var result=Ext.decode(response.responseText);
				for(var i=0;i<result.length;i++){
					var checkboxObj = {
                        boxLabel: result[i].name,
                        checked: false,
                        inputValue:result[i].value,
                        listeners: {
						    change: function (field) {
						    	var patt = new RegExp(/.*\([^\)\(\d]*(\d+)[^\)\(\d]*\).*/,"g");//正则
		            			var num = patt.exec(field.boxLabel);//提取字符串中的数字
						    	if(field.checked){
						    		all=parseInt(num[1])+all;
						    	}else{
						    		all=all-parseInt(num[1]);
						    	}
						    	Ext.get('exp1').dom.innerHTML = '<p style="font-size:20px;color:red;text-align:center;padding:20px;background-color: #D9EDF7;">已选数量：'+all+'</p>';
						    }
						}
	                };
					var checkbox=new Ext.form.Checkbox(checkboxObj);
					Ext.getCmp('productlist').items.add(checkbox);
				}
				Ext.getCmp('parm').setValue(record.id);
				Ext.getCmp('parm1').setValue(record.data.repo.id);
				Ext.getCmp('parm2').setValue(record.data.repo.maxSize);
				Ext.Ajax.request({ 
					url : '/stock/getStock', 
					method : 'Get', 
					params : { 
						reoid :record.data.repo.id
					}, 
					success: function(response) {
						var json = Ext.util.JSON.decode(response.responseText);
						stockNum=json.stockNum;	
						Ext.get('exp').dom.innerHTML = '<p style="font-size:20px;color:red;text-align:center;padding:20px;background-color: #D9EDF7;">仓库剩余容量：'+(record.data.repo.maxSize-stockNum)+'</p>';
					}
				});	
				win.show();
			}
		});
	},

	submitAddForm:function(btn){
		var count=0;
		var bothFlag=0;
		var nullFlag=0;
		var Flagplus=0;
		var stockflag=0;
		var textItem=new Array();
		var comItem=new Array();
		var repoid1=Ext.getCmp('getRepo').getValue();
		var repoid2=Ext.getCmp('setRepo').getValue();
		if((repoid1&&repoid2)==null){
			Ext.Msg.alert('Error', '必须选择仓库');
		}else{
			if(repoid1==repoid2){
				Ext.Msg.alert('Error', '调出仓库和调入仓库不能是同一个');
			}else{
		        while(true){
		            if(Ext.getCmp('com'+count)!=null){
		            	if(Ext.getCmp('com'+count).getValue()!=null&&Ext.getCmp('text'+count).getValue()!=''){
		            		comItem[count]=Ext.getCmp('com'+count).getValue();
		            		if((/(^[1-9]\d*$)/.test(Ext.getCmp('text'+count).getValue()))){
		            			var string=Ext.getCmp('com'+count).getRawValue();
		            			var patt = new RegExp(/.*\([^\)\(\d]*(\d+)[^\)\(\d]*\).*/,"g");//正则
		            			var num = patt.exec(string);//提取字符串中的数字
		            			if(num[1]>=parseInt(Ext.getCmp('text'+count).getValue())){
		            				textItem[count]=Ext.getCmp('text'+count).getValue();
		            			}else{
		            				stockflag=1;
		            			}
		            			
		            		}else{
		            			Flagplus=1;
		            		}
		            	}else if(Ext.getCmp('com'+count).getValue()==null&&Ext.getCmp('text'+count).getValue()==''){
		            	}else{
		            	    nullFlag=1;
		            		break;
		            	}
		                count++;
		            }else if(Ext.getCmp('com'+count)==null){
		                break;
		            }
		        }
		        for(var i=0;i<comItem.length-1;i++){
		        	for(var j=i+1;j<comItem.length;j++){
		        		if(comItem[i]==comItem[j]){
		        			bothFlag=1;
		        			break;
		        		}
		        	}
		        }
		        if(nullFlag==1){
					Ext.Msg.alert('Error', '商品与数量必须同时填写');
				}else{
					if(Flagplus==1){
						Ext.Msg.alert('Error', '商品数量必须是正整数');
					}else{
						if(stockflag==1){
							Ext.Msg.alert('Error', '调拨商品数量过多，库存不足');
						}else{
							if(bothFlag==0){
								if((Ext.getCmp('com0')&&Ext.getCmp('text0'))==null){
									Ext.Msg.alert('Error', '至少要提交一个商品');
								}else{
									var win = btn.up('window');
		  							var store =	Ext.data.StoreManager.lookup('stockDetailStore');
									store.proxy.url='/stockDetail/save';
									Ext.apply(store.proxy.extraParams,{
										toSubmit:{textItem:textItem,comItem:comItem,repoid1:repoid1,repoid2:repoid2}
									});
									store.load({params:{start:0, limit:10, page:1}});
									win.close();
								}
							}else if(bothFlag==1){
								Ext.Msg.alert('Error', '不能有两个相同的商品选项');
							}
						}
					}
				}
			}
        }
	},
	submitEditForm:function(btn){
		var array=Ext.getCmp('productlist').getChecked();
		var subitem=new Array();
		var stockNum=0;
		subitem[0]=Ext.getCmp('parm').getValue();
		for(var i=0;i<array.length;i++){
			subitem[i+1]=array[i].inputValue;
		}

		var reoid=Ext.getCmp('parm1').getValue();
		Ext.Ajax.request({ 
			url : '/stock/getStock', 
			method : 'Get', 
			params : { 
				reoid :reoid
			}, 
			success: function(response) {
				var json = Ext.util.JSON.decode(response.responseText);
				stockNum=json.stockNum;	
				var count=0;
				for(var i=0;i<array.length;i++){
					var patt = new RegExp(/.*\([^\)\(\d]*(\d+)[^\)\(\d]*\).*/,"g");//正则
				    var num = patt.exec(array[i].boxLabel);//提取字符串中的数字
				    count=count+num[1];
				}
				if(count>(Ext.getCmp('parm2').getValue()-stockNum)){
					Ext.Msg.alert('操作失败', '所选商品总量大于剩余库存');
				}else{
					Ext.Ajax.request({ 
					url : '/stockDetail/tran', 
					method : 'post', 
					params : { 
						subitem :subitem
					}, 
					success: function(response) {
						var json = Ext.util.JSON.decode(response.responseText);
						if(json.success){
							Ext.Msg.alert('操作成功', json.msg);
							btn.up('window').close();
							Ext.data.StoreManager.lookup('stockDetailStore').load();
						}else{
							Ext.Msg.alert('操作失败', json.msg);
							
						}
						win.close();
					}
					});
				}
			}
		});
	},
	onResetFormClick:function(){
		var array=Ext.getCmp('productlist').getChecked();
		for(var i=0;i<array.length;i++){
			array[i].setValue(false);
		}

	},
	onAllFormClick:function(){
		var array=Ext.getCmp('productlist').items;
		array.each(function(item){  
            item.setValue(true);

        });  
	}
});