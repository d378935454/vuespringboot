$(function(){
	$("#search").click(function() {		 
		var start = $("#start").val();
		var end = $("#end").val(); 
		var appkey = $("#e2").val();
		var methodName=$("#e3").val();
		var dataSourceID=$("#e4").val();
		
		var list;
		//客户数据查询
		if($("#e2").css("display")=="block"){
			var dataObj={
					startTime:start,
					endTime:end,
					company:appkey,
					method:methodName					
			};
			list=ajaxPost(dataObj,$context.ctx+"/system/bill/statis/customSearch");
			//展现客户查询数据结果。
			showCustom(list)
		}
		//供应商数据查询
		else  if ($("#e4").css("display")=="block"){
			var dataObj={
					startTime:start,
					endTime:end,
					company:dataSourceID,
					method:methodName					
			};
			list=ajaxPost(dataObj,$context.ctx+"/system/bill/statis/supplierSearch");
			//展现供应商查询数据结果。
			showSupplier(list);
		}
	}); 

	$("#export").click(function(){
		var start = $("#start").val();
		var end = $("#end").val(); 
		var appkey = $("#e2").val();
		var methodName=$("#e3").val();
		var dataSourceID=$("#e4").val();
		if($("#e2").css("display")=="block"){
			window.location.href = $context.ctx + "/system/bill/statis/customExport?startTime="+start+"&endTime="+end+"&company="+appkey
			+"&method="+methodName;
		}else  if ($("#e4").css("display")=="block"){
			window.location.href = $context.ctx + "/system/bill/statis/supplierExport?startTime="+start+"&endTime="+end+"&company="+dataSourceID
			+"&method="+methodName;
		}
	});	
	$("#exportByDay").click(function(){
		var start = $("#start").val();
		var end = $("#end").val(); 
		var appkey = $("#e2").val();
		var methodName=$("#e3").val();
		var dataSourceID=$("#e4").val();
		if($("#e2").css("display")=="block"){
			window.location.href = $context.ctx + "/system/bill/statis/customExportByDay?startTime="+start+"&endTime="+end+"&company="+appkey
			+"&method="+methodName;
		}else  if ($("#e4").css("display")=="block"){
			window.location.href = $context.ctx + "/system/bill/statis/supplierExportByDay?startTime="+start+"&endTime="+end+"&company="+dataSourceID
			+"&method="+methodName;
		}
	});	
});
 
function showSupplier(list){
	$("#supplierTable").empty();	
	for(var i=0;i<list.length;i++){
		var item=list[i];
		var inner="<tr><td>"+item.dATA_SOURCE_VALUE+"</td><td>"+
		item.bILL_CHANNEL_SOURCE_NAME+"</td><td>"+item.mETHOD_NAME+
		"</td><td>"+item.tOTAL_COUNT+"</td><td>"+item.sUCCESS_YES_COUNT+
		"</td><td>"+item.sUCCESS_NO_COUNT+"</td><td>"+item.eRROR_COUNT+"</td></tr>";
		$("#supplierTable").append(inner);
	}
}

function showCustom(list){
	$("#customTable").empty();
	for(var i=0;i<list.length;i++){
		var item=list[i];
		console.info(item.rowSpan);
		var inner="";
		if(item.rowSpan==0){
			inner="<tr><td>"+item.dATA_SOURCE_VALUE+"</td><td>"+
			item.tOTAL_COUNT+"</td><td>"+item.sUCCESS_YES_COUNT+
			"</td><td>"+item.sUCCESS_NO_COUNT+"</td><td>"+
			item.eRROR_COUNT+"</td></tr>";
		}else if (item.rowSpan==1){
			inner="<tr><td>"+item.cOMPANY+"</td><td>"+item.mETHOD_NAME+"</td><td>"+
			item.aLL_TOTAL_COUNT+"</td><td>"+item.aLL_SUCCESS_YES_COUNT+
			"</td><td>"+item.aLL_SUCCESS_NO_COUNT+"</td><td>"+item.aLL_ERROR_COUNT+
			"</td><td>"+item.dATA_SOURCE_VALUE+
			"</td><td>"+item.tOTAL_COUNT+"</td><td>"+item.sUCCESS_YES_COUNT+
			"</td><td>"+item.sUCCESS_NO_COUNT+"</td><td>"+item.eRROR_COUNT+"</td></tr>";
		}else { 			
			inner="<tr><td rowspan='"+item.rowSpan+"'>"+
			item.cOMPANY+"</td><td rowspan='"+item.rowSpan+"'>"+item.mETHOD_NAME+"</td><td rowspan='"+item.rowSpan+"'>"+
			item.aLL_TOTAL_COUNT+"</td><td rowspan='"+item.rowSpan+"'>"+item.aLL_SUCCESS_YES_COUNT+
			"</td><td rowspan='"+item.rowSpan+"'>"+item.aLL_SUCCESS_NO_COUNT+"</td><td rowspan='"+item.rowSpan+"'>"+item.aLL_ERROR_COUNT+
			"</td><td>"+item.dATA_SOURCE_VALUE+
			"</td><td>"+item.tOTAL_COUNT+"</td><td>"+item.sUCCESS_YES_COUNT+
			"</td><td>"+item.sUCCESS_NO_COUNT+"</td><td>"+item.eRROR_COUNT+"</td></tr>";
		}
		$("#customTable").append(inner);
	}
}

	function ajaxPost(dataObj,url){
		var list;
		$.ajax({
			type:"post",
			url:url,
			async:false,
			data:dataObj,
			dataType:"json",
			success:function(data){
				list=data.searchResult;
			} 
		}); 
		return list;
	}

 