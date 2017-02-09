$(function() {

	$("#tpmButton").click(function() {
		onClickFunc("TPM", "tpmChart");
	});
	$("#tpmButton").click();

	$("#custCountButton").click(function() {
		onClickFunc("CUSTDIST", "custCountChart");
	});
	$("#custCountButton").click();

	$("#timeDistButton").click(function() {
		onClickFunc("TIMEDIST", "timeDistChart");
	});
	$("#timeDistButton").click();

	$("#failRateButton").click(function() {
		onClickFunc("FAILRATE", "failRateChart");
	});
	$("#failRateButton").click();

	$("#highSuspRateButton").click(function() {
		onClickFunc("HIGHSUSPRATE", "highSuspRateChart");
	});
	$("#highSuspRateButton").click();

	$("#highRiskRateButton").click(function() {
		onClickFunc("HIGHRISKRATE", "highRiskRateChart");
	});

	$("#sameProfRateButton").click(function() {
		onClickFunc("SAMEPROFRATE", "sameProfRateChart");
	});

	$("#turnHardRateButton").click(function() {
		onClickFunc("TURNHARDRATE", "turnHardRateChart");
	});

	$("#blackHitRateButton").click(function() {
		onClickFunc("BLACKHITRATE", "blackHitRateChart");
	});

	$("#contactRateButton").click(function() {
		onClickFunc("CONTACTRATE", "contactRateChart");
	});

	$("#export").click(
			function() {
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				var custAppkey = $("#custAppkey").val();
				// var interfaceName =
				// encodeURIComponent(encodeURIComponent($("#e2
				// option:selected").text()));
				window.location.href = $context.ctx
						+ "/system/nine/record/chart/tpm?startDate="
						+ startDate + "&endDate=" + endDate + "&custAppkey="
						+ custAppkey;
			});
});

function onClickFunc(chartType, chartName) {
	var url = null;
	if ("TPM" == chartType) {// tpm
		url = '/system/nine/record/chart/tpm';
	} else if ("CUSTDIST" == chartType) {
		url = '/system/nine/record/chart/custCount';
	} else if ("TIMEDIST" == chartType) {
		url = '/system/nine/record/chart/timeDist';
	} else {
		url = '/system/nine/record/chart/rate';
	}

	Load('正在运行，请稍后...');
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var custAppkey = $("#custAppkey").val();
	chartSet(url, startDate, endDate, custAppkey, chartType, chartName);
	setTimeout(function() {
		dispalyLoad();
	}, 1000);
}

function chartSet(url, startDate, endDate, custAppkey, chartType, chartName) {
	var myChart = echarts.init(document.getElementById(chartName));
	myChart.setOption({
		grid : { // 控制图的大小，调整下面这些值就可以，
			x : 150,// 左边距
			x2 : 10,// 右边距
			y2 : 100,// 下边距, y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
		}
	});
	var option;
	// 过渡---------------------
	myChart.showLoading({
		text : '正在努力的读取数据中...' // loading话术
	});

	var obj = {
		startDate : startDate,
		endDate : endDate,
		custAppkey : custAppkey,
		chartType : chartType
	};

	option = ajaxPost(obj, $context.ctx + url);
	if ("TIMEDIST" == chartType) {
		var obj = jQuery.parseJSON(option);
		if (obj.resp_code == "success") {
			option = jQuery.parseJSON(obj.resp_msg);
			//console.log(option);
		}
	}

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

			// option = jQuery.parseJSON(data);
			console.log(data);
			option = data;
		}
	});
	return option;
}
