$(function() {
	$("#sub").click(function() {
		Load('正在运行，请稍后...');
		var start = $("#start").val();
		var end = $("#end").val();
		var statisticalMethods = $("#e2").val();
		var bussinessId = $("#e4").val();
		var interfaceId = "";
		if($("#e6").val())
		{
			interfaceId=$("#e6").val().toString();
		}
		chartSet(start,end,statisticalMethods,bussinessId,interfaceId);
		setTimeout(function () { 
			dispalyLoad(); 
	    }, 1000);
	});
});
function chartSet(start,end,statisticalMethods,bussinessId,interfaceId){
	var myChart = echarts.init(document.getElementById('chart_1'));


	// 过渡---------------------
	myChart.showLoading({
		text : '正在努力的读取数据中...' //loading话术
	});

	var obj={
			start:start,
			end:end,
			appkey:statisticalMethods,
			phoneType:bussinessId,
			area:interfaceId
	};
	
	option = ajaxPost(obj, $context.ctx + '/system/report/phone_transformation_monitor_chart');
	
	myChart.hideLoading();
	
	myChart.setOption(option);
	//var option;
	// 过渡---------------------
	//Load('正在运行，请稍后...');

	/*var obj={
			start:start,
			end:end,
			statisticalMethods:statisticalMethods,
			bussinessId:bussinessId,
			interfaceId:interfaceId
	};*/
	/*$("#sub").html('查询中...');
	$("#sub").attr("disabled", true);*/
	//option = ajaxPost(obj, $context.ctx + '/system/report/phone_transformation_monitor_chart');
	/*if(option){
		myChart.clear();*/

		//myChart.setOption();
	//}


	

	//dispalyLoad();
}
function ajaxPost(obj, url) {
	var option;
	$.ajax({
		type : "post",
		url : url,
		data : obj,
		async : false,
		success : function(data) {
			$("#sub").html('查询');
			$("#sub").removeAttr("disabled");
			var obj = jQuery.parseJSON(data);
			if (obj.resp_code == "success") {
				option = jQuery.parseJSON(obj.resp_msg);
			}
		}
	});
	return option;
}


