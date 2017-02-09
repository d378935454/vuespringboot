$(function() {
	$("#search").click(function() {
		Load('正在运行，请稍后...');
		var start = $("#start").val();
		var end = $("#end").val();
		var appkey = $("#e1").val();
		var rate = $("#e2").val();
		var judge = validateDate(start,end);
		if(judge != "Y"){
			alert(judge);
			dispalyLoad(); 
			return; 
		}
		setTimeout(function () { 
			chartSet(start,end,appkey,rate);
			dispalyLoad(); 
	    }, 5000);
	});
	$("#export").click(function() {	
		var start = $("#start").val();
		var end = $("#end").val();
		var appkey = $("#e1").val();
		var judge = validateDate(start,end);
		if(judge != "Y"){
			alert(judge);
			return; 
		}
		window.location.href = $context.ctx + "/system/report/exportphoneTookMonitor?start="+start+"&end="+end+"&appkey="+appkey;
	});
});

function chartSet(start,end,appkey,rate){
	var myChart = echarts.init(document.getElementById('chart_1'));
	var option;
	// 过渡---------------------
	myChart.showLoading({
		text : '正在努力的读取数据中...'
	});
	var obj={
			start:start,
			end:end,
			appkey:appkey,
			rate:rate,
	};
	option = ajaxPost(obj, $context.ctx + '/system/report/jd_request_usetime_query');
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