/**
 * 
 */
 
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
	

$(function() { 	
	//查询
	$("#sub").click(function() {
		Load('正在努力加载中...');
		var start = $("#start").val();
		var end = $("#end").val();
		var isReTime=validateDate(start, end);
		if(isReTime!="Y"){
			alert(isReTime);
			return;
		}
		var queryType=$("#e3").val();
		var appKey = $("#e2").val();
		var phoneType=encodeURIComponent($("#e4").val());
		var area=encodeURIComponent($("#e6").val());		
		
		chartSet(start,end,queryType,appKey,phoneType,area);  
		setTimeout(function () { 
			dispalyLoad(); 
	    }, 1000);
	});	 
	
	//导出
	$("#export").click(function() {	
		var start = $("#start").val();
		var end = $("#end").val();
		var isReTime=validateDate(start, end);
		if(isReTime!="Y"){
			alert(isReTime);
			return;
		}
		var queryType=$("#e3").val();//做下编码,1表示token，2表示手机号
		var appKey = $("#e2").val();
		var phoneType=encodeURIComponent($("#e4").val());
		var area=encodeURIComponent($("#e6").val());		
		window.location.href = $context.ctx + "/system/requestmonitor/exportRequestMonitor?start="+start+"&end="+end+"&queryType="+queryType
		+"&company="+appKey+"&phoneType="+phoneType+"&area="+area;
	});
});

function chartSet(start,end,queryType,appKey,phoneType,area){
	var myChart = echarts.init(document.getElementById('chart_1'));
	var option;
	// 过渡---------------------
	myChart.showLoading({
		text : '正在努力的读取数据中...' //loading话术
	});

	var obj={
			start:start,
			end:end,
			queryType:queryType,
			appKey:appKey,
			phoneType:phoneType,
			area:area
	};	
	option = ajaxPost(obj, $context.ctx + '/system/requestmonitor/requestMonitorChart');	
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
				//console.log(option);
				dispalyLoad(); 
			}
		}
	});
	return option;
}