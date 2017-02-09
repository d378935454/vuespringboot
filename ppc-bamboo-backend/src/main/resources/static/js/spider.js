$(function(){
	$("#dg").datagrid({
		onDblClickRow : function(index,row){
			$("#cc").attr('src',ctx+'/system/jd/dataList/'+row.id);
		}
	});
	//搜索
	$("#search_button").click(function(){
		$("#dg").datagrid({
			queryParams: {
				state: $("#search_value").combobox('getValue')
			},
			pageNumber:1
		});
	});
})