var datatables;
var detailTable;

function initMainTable(){
	datatables = $('#myTable').DataTable({
		/*基本参数设置，以下参数设置和默认效果一致*/  
        "bPaginate": true, //翻页功能  
        "bLengthChange": false, //改变每页显示数据数量  
        "bFilter": false, //过滤功能  
        "bStateSave": false,       
        "bInfo": true,//页脚信息  
        "bDestroy":true,
         //"bJQueryUI" : true,
        //"bSort": true, //排序功能  
        //"sScrollY" : 375, //DataTables的高  
        //"sScrollX" : 1595, //DataTables的宽 
        //"aaSorting": [[ 1, "desc" ]],
        "sPaginationType": "full_numbers",   /*默认翻页样式设置*/
        "bRetrieve": true,
        "bAutoWidth": true,//自动宽度  
        "bProcessing": false,//加载数据时候是否显示进度条                 
        "oLanguage": {      	
        	"sLengthMenu": "每页显示 _MENU_条",  
        	"sZeroRecords": "没有找到符合条件的数据",
        	//"sProcessing" : "正在处理数据，请稍候……",
        	//"sProcessing" : "<img src='../../resources/img/loaders/4.gif'/>", //这里是给服务器发请求后到等待时间显示的 加载gif
        	"sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",  
        	"sInfoEmpty": "没有记录",  
        	"sInfoFiltered": "(从 _MAX_ 条记录中过滤)",  
        	"sSearch": "搜索：",  
        	"oPaginate": {  
	        	"sFirst": "首页",  
	        	"sPrevious": "前一页",  
	        	"sNext": "后一页",  
	        	"sLast": "尾页" 
        	}
        },
        "bServerSide": true,//是否从服务器加载数据
        "sAjaxSource": $context.ctx + "/system/nine/record/custsum?startDateStr="+$("#startDate").val()+"&endDateStr="+$("#endDate").val()+"&custAppkey="
								+$("#custAppkey").val()+"&confStatus="+$("#confStatus").val(),//如果从服务器端加载数据，这个属性用语指定加载的路径
        /*"aaSorting": [[ 0, "desc" ]],//设置第3个元素为默认排序  */
       "aoColumns" : [ {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "custCompanyName",
            "aTargets" : [ 0 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "custAppkey",
            "aTargets" : [ 1 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "queryCount",
            "aTargets" : [ 2 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "succCount",
            "aTargets" : [ 3 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "succRatioStr",
            "aTargets" : [ 4 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "highSuspiciousCount",
            "aTargets" : [ 5 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "lowSuspiciousCount",
            "aTargets" : [ 6 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "nodataCount",
            "aTargets" : [ 7 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "serviceList",
            "aTargets" : [ 8 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "avgCostTime",
            "aTargets" : [ 9 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "confStatusName",
            "aTargets" : [ 10 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "action",
            "fnRender": function (obj) {
            	var custAppkey = obj.aData["custAppkey"];
            	var startDate = $("#startDate").val();
            	var endDate = $("#endDate").val();
            	var detailPageUrl = $context.ctx + "/system/nine/record/detail?custAppkey="+custAppkey+"&startDateStr="+startDate+"&endDateStr="+endDate;
            	return '<button value="'+custAppkey+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="showCustDetailDialog(this,\'' + custAppkey + '\',\'' + startDate + '\',\'' + endDate + '\')"><font>详细</font></button>'
            	//return '<a href="' + detailPageUrl + '" class="btn btn-xs btn-info btn-detail" style="margin-left:6px;" >详细</a>'
            		+ '<button  value="'+custAppkey+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="showTimeDistDialog(this,\'' + custAppkey + '\',\'' + startDate + '\',\'' + endDate + '\')"><font>耗时分布</font></button>';
            	//return '<div><a href="' + detailPageUrl + '" class="btn btn-xs btn-info btn-detail" style="margin-left:6px;" >详细</a>'
            	//	+'<a href="' + timeDistPageUrl + '" class="btn btn-xs btn-info btn-dist" style="margin-left:6px;" >耗时分布</a></div>';
             }
        },],
        "aoColumnDefs" : [ {
            sDefaultContent : '',
            aTargets : [ '_all' ]
        } ],
       "fnPreDrawCallback": function(){
    	   Load('正在运行，请稍后...');
       },
       "fnInfoCallback": function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {
    	   dispalyLoad();
    	   return "当前第  "+iStart+" - "+ iEnd+" 条　共计 "+iTotal+" 条";
       }
    });
}

function initDetailTable() {
	detailTable = $('#detailTable').DataTable({
		/*基本参数设置，以下参数设置和默认效果一致*/  
        "bPaginate": true, //翻页功能  
        "bLengthChange": false, //改变每页显示数据数量  
        "bFilter": false, //过滤功能  
        "bStateSave": false,       
        "bInfo": true,//页脚信息  
        "bDestroy":true,
         //"bJQueryUI" : true,
        //"bSort": true, //排序功能  
        //"sScrollY" : 375, //DataTables的高  
        //"sScrollX" : 1595, //DataTables的宽 
        //"aaSorting": [[ 1, "desc" ]],
        "sPaginationType": "full_numbers",   /*默认翻页样式设置*/
        "bRetrieve": true,
        "bAutoWidth": true,//自动宽度  
        "bProcessing": false,//加载数据时候是否显示进度条                 
        "oLanguage": {      	
        	"sLengthMenu": "每页显示 _MENU_条",  
        	"sZeroRecords": "没有找到符合条件的数据",
        	//"sProcessing" : "正在处理数据，请稍候……",
        	//"sProcessing" : "<img src='../../resources/img/loaders/4.gif'/>", //这里是给服务器发请求后到等待时间显示的 加载gif
        	"sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
        	"sInfoEmpty": "没有记录",
        	"sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
        	"sSearch": "搜索：",
        	"oPaginate": {  
	        	"sFirst": "首页",  
	        	"sPrevious": "前一页",  
	        	"sNext": "后一页",  
	        	"sLast": "尾页" 
        	}
        },
        "bServerSide": true,//是否从服务器加载数据
        "sAjaxSource": $context.ctx + "/system/nine/record/detail?startDateStr="+$("#detailStartDate").val()+"&endDateStr="+$("#detailEndDate").val()+"&identity="
			+$("#detailIdentity").val()+"&identityValue="+$("#detailIdentityValue").val()+"&suspiciousDegree="+$("#detailSuspiciousDegree").val()+"&checkItem="
			+$("#detailCheckItem").val()+"&nineStatus="+$("#detailNineStatus").val()+"&custAppkey="+$("#detailCustAppkey").val(),//如果从服务器端加载数据，这个属性用语指定加载的路径
        /*"aaSorting": [[ 0, "desc" ]],//设置第3个元素为默认排序  */
       "aoColumns" : [ {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "id",
            "aTargets" : [ 0 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "apiCompany",
            "aTargets" : [ 1 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "apiSerial",
            "aTargets" : [ 2 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "applyQueryTimeStr",
            "aTargets" : [ 3 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "name",
            "aTargets" : [ 4 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "idNumber",
            "aTargets" : [ 5 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "phone",
            "aTargets" : [ 6 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "costTimeSec",
            "aTargets" : [ 7 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "statusName",
            "aTargets" : [ 8 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "suspiciousDegreeName",
            "aTargets" : [ 9 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "accordance",
            "aTargets" : [ 10 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "action",
            "fnRender": function (obj) {
            	var token = obj.aData["id"];
            	var apiSerial = obj.aData["apiSerial"];
            	var appkey = obj.aData["apiAppKey"];
            	//var detailPageUrl = $context.ctx + "/system/nine/record/apiDetail?apiSerial="+apiSerial+"&appkey="+appkey;
            	return '<button value="'+custAppkey+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="showApiDetail(this,\'' + token + '\',\'' + appkey + '\',\'' + apiSerial + '\')"><font>详细</font></button>';
             }
        },],
        "aoColumnDefs" : [ {
            sDefaultContent : '',
            aTargets : [ '_all' ]
        } ],
       "fnPreDrawCallback": function(){
    	   Load('正在运行，请稍后...');
       },
       "fnInfoCallback": function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {
    	   dispalyLoad();
    	   return "当前第  "+iStart+" - "+ iEnd+" 条　共计 "+iTotal+" 条";
       }
    });
}

$("#search").click(function() {
	if(!datatables){
		initMainTable();
	}
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var custAppkey = $("#custAppkey").val();
	var confStatus = $("#confStatus").val();
	showTotalSum(startDate, endDate, custAppkey, confStatus);
	var oSettings = datatables.fnSettings();
	oSettings.sAjaxSource = $context.ctx + "/system/nine/record/custsum?startDateStr="+startDate+"&endDateStr="+endDate+"&custAppkey="
		+custAppkey+"&confStatus="+confStatus;//如果从服务器端加载数据，这个属性用语指定加载的路径
	datatables.fnClearTable(0);
	datatables.fnDraw();
});

$("#detailSearch").click(function() {
	if(!detailTable){
		initDetailTable();
	}
	var custAppkey = $("#detailCustAppkey").val();
	var startDate = $("#detailStartDate").val();
	var endDate = $("#detailEndDate").val();
	var identity = $("#detailIdentity").val();
	var identityValue = $("#detailIdentityValue").val();
	var suspiciousDegree = $("#detailSuspiciousDegree").val();
	var checkItem = $("#detailCheckItem").val();
	var nineStatus = $("#detailNineStatus").val();
	var oSettings = detailTable.fnSettings();
	oSettings.sAjaxSource = $context.ctx + "/system/nine/record/detail?startDateStr="+startDate+"&endDateStr="+endDate+"&identity="
		+identity+"&identityValue="+identityValue+"&suspiciousDegree="+suspiciousDegree+"&checkItem="+checkItem
		+"&nineStatus="+nineStatus+"&custAppkey="+custAppkey;//如果从服务器端加载数据，这个属性用语指定加载的路径
	
	//var queryStr = $("#detailForm").serialize();
	//oSettings.sAjaxSource = $context.ctx + "/system/nine/record/detail?" + queryStr;
	
	detailTable.fnClearTable(0);
	detailTable.fnDraw();
});

function showTotalSum(startDate, endDate, custAppkey, confStatus) {
	var params = {
		startDateStr: startDate,
		endDateStr: endDate,
		custAppkey: custAppkey,
		confStatus: confStatus
	};
	$.ajax({
		type : "get",
		url : $context.ctx + "/system/nine/record/totalsum",
		data : params,
		dataType: "json",
		success : function(data) {
			$("#queryCount").html(data.queryCount);
			$("#succCount").html(data.succCount);
			$("#succRatioStr").html(data.succRatioStr);
			$("#highSuspiciousCount").html(data.highSuspiciousCount);
			$("#lowSuspiciousCount").html(data.lowSuspiciousCount);
			$("#nodataCount").html(data.nodataCount);
			$("#avgCostTime").html(data.avgCostTime);
		},
		error: function() {
			$("#queryCount").html();
			$("#succCount").html();
			$("#succRatioStr").html();
			$("#highSuspiciousCount").html();
			$("#lowSuspiciousCount").html();
			$("#nodataCount").html();
			$("#avgCostTime").html();
		}
	});
}

function showCustDetailDialog(_this, custAppkey, startDate, endDate){
	//$(_this).parents("tr:first").addClass("focusTr");
	//Load('正在运行，请稍后...');
	$("#detailCustAppkey").val(custAppkey);
	$("#detailStartDate").val(startDate);
	$("#detailEndDate").val(endDate);
	$("#detailIdentityValue").val("");
	$("#detailSuspiciousDegree").val("");
	$("#detailCheckItem").val("");
	$("#detailNineStatus").val("");
	
	$("#detailSearch").click();
	$("#showCustDetailModal").modal('show');//开启模态框
}

/**
 * 耗时分布
 * @param _this
 * @param custAppkey
 * @param startDate
 * @param endDate
 */
function showTimeDistDialog(_this, custAppkey, startDate, endDate){
	Load('正在运行，请稍后...');
	$("#user_add").modal('show');//开启模态框
	setTimeout(function (){
		chartSet(custAppkey, startDate, endDate)
	},1000); // 设置1秒后进行查询,必须要等上面的模态框加载出来，再去查询数据，否则，不能显示图表，
	setTimeout(function () { 
		dispalyLoad(); 
	}, 1000);
}

function chartSet(custAppkey,startDate,endDate){
	var myChart = echarts.init(document.getElementById('chart_1'));
	var option;
	// 过渡---------------------
	myChart.showLoading({
		text : '正在努力的读取数据中...' //loading话术
	});

	var obj={
			custAppkey:custAppkey,
			startDate:startDate,
			endDate:endDate
	};
	
	option = ajaxPost(obj, $context.ctx + '/system/nine/record/chart/timeDist');
	
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
			}
		}
	});
	return option;
}

function showApiDetail(_this, token, appkey, serial){
	var inputContentTemp = "暂无接口输入参数相关信息！";
	var outputContentTemp = "暂无接口输出参数相关信息！";
	
	if (!isBlank(token) && !isBlank(appkey) && !isBlank(serial)) {
		//Load('正在运行，请稍后...');
		$.post($context.ctx+"/system/nine/record/detail/content?token="+token+"&appkey="+appkey+"&serial="+serial,function(data){
		    //dispalyLoad();
			//var obj = jQuery.parseJSON(data);
	    	if(!isBlank(data) && data.code=="success") {
				inputContentTemp = decodeURI(data.result.textContent);
				outputContentTemp = data.result.responseContent;
	    	}
	    	
	    	$("#modal1InterfaceRequestName").empty().html(inputContentTemp);
		 	$("#modal1InterfaceResponseName").empty().html(outputContentTemp);
		    $("#detailContentModal").modal('show');//{backdrop: 'static', keyboard: false}
		});
	} else {
		$("#modal1InterfaceRequestName").empty().html(inputContentTemp);
	 	$("#modal1InterfaceResponseName").empty().html(outputContentTemp);
	    $("#detailContentModal").modal('show');
	}
}

function isBlank(value) {
	if (typeof(value)=="undefined" || !value || ""==value || value=="undefined")
		return true;
	else 
		return false;
}