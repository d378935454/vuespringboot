$(function() {
	$("#search").click(function() {
		Load('正在运行，请稍后...');
		var start = $("#start").val();
		var end = $("#end").val();
		var appkey = $("#e1").val();
		var phoneType = $("#e2").val();
		var province = $("#e3").val();
		var judge = validateDate(start,end);
		if(judge != "Y"){
			alert(judge);
			dispalyLoad(); 
			return; 
		}
		setTimeout(function () {
			$("#showException").show();
			chartSet(start, end, appkey, phoneType, province);
			dispalyLoad(); 
	    }, 5000);
	});
});

function chartSet(start, end, appkey, phoneType, province){
	var myChart_1 = echarts.init(document.getElementById('chart_1'));
	var myChart_2 = echarts.init(document.getElementById('chart_2'));
	var myChart_3 = echarts.init(document.getElementById('chart_3'));
	var myChart_4 = echarts.init(document.getElementById('chart_4'));
	var myChart_5 = echarts.init(document.getElementById('chart_5'));
	var myChart_6 = echarts.init(document.getElementById('chart_6'));
	var myChart_7 = echarts.init(document.getElementById('chart_7'));
	var option;
	var obj={
			start:start,
			end:end,
			appkey:appkey,
			phoneType:phoneType,
			province:province
	};
	option = ajaxPost(obj, $context.ctx + '/system/report/phone_exception_monitor_query');
	myChart_1.setOption(jQuery.parseJSON(option.lostRealNameOption));
	myChart_2.setOption(jQuery.parseJSON(option.lostIdNumberOption));
	myChart_3.setOption(jQuery.parseJSON(option.lostNetworkTimeOption));
	myChart_4.setOption(jQuery.parseJSON(option.lostMonthOption));
	myChart_5.setOption(jQuery.parseJSON(option.countSameMonthsOption));
	myChart_6.setOption(jQuery.parseJSON(option.sameOtherCallOption));
	myChart_7.setOption(jQuery.parseJSON(option.sameStartTimeOption));
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
			}
		}
	});
	return option;
}

function validateDate(start, end){
	var s1 = new Date(start.substring(0,10).replace(/-/g, "/"));
	var s2 = new Date(end.substring(0,10).replace(/-/g, "/"));
	var days = s2.getTime() - s1.getTime();
	var times = parseInt(days / (1000 * 60 * 60 * 24));
	if(times > 31){
		return "查询日期区间不能大于31天！"
	}else{
		return "Y";
	}
}