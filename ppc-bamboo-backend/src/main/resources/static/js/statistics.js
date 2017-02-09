$(function() {

	$("#sub").click(function() {
		Load('正在运行，请稍后...');
		var start = $("#start").val();
		var end = $("#end").val();
		var methodName = $("#e2").val();
		chartSet(start,end,methodName);
		setTimeout(function () { 
			dispalyLoad(); 
	    }, 1000);
	});
	
	$("#export").click(function() {	
		var start = $("#start").val();
		var end = $("#end").val();
		var methodName = $("#e2").val();
		var interfaceName =  encodeURIComponent(encodeURIComponent($("#e2 option:selected").text()));
		window.location.href = $context.ctx + "/system/api/exportInterfaceStatistics?start="+start+"&end="+end+"&methodName="+methodName+"&interfaceName="+interfaceName;
	});
});

function chartSet(start,end,methodName){
	var myChart = echarts.init(document.getElementById('chart_1'));
	var option;
	// 过渡---------------------
	myChart.showLoading({
		text : '正在努力的读取数据中...' //loading话术
	});

	var obj={
			start:start,
			end:end,
			methodName:methodName
	};
	
	option = ajaxPost(obj, $context.ctx + '/system/api/data');
	
	myChart.hideLoading();
	
	myChart.setOption(option);
	
}
function ajaxPost(obj, url) {
	var option;
	$.ajax({
		type : "post",
		url : url,
		data : obj,
		async : false,
		success : function(data) {
			
			var obj = jQuery.parseJSON(data);
			if (obj.resp_code == "success") {
				option = jQuery.parseJSON(obj.resp_msg);
				console.log(option);
			}
		}
	});
	return option;
}