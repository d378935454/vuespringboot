var myChart;
var dataTotalChart;
var datatables;
//var bbsSiteList;
//var phoneLibSitelistData;
var queryTable = false;
var queryChart = false;

$(function() {
	var isExistInterface = false;
	var start = $("#start").val();
	var end = $("#end").val();
	var bussinessId = $("#e2").val();
	initInterfaceList();
	//initLoadData();
	//initSiteList();
	/*
	function initLoadData(){
		var start = $("#start").val();
		var end = $("#end").val();
		var statisticalMethods = $("#e1").val();
		var bussinessId = $("#e2").val();
		if(bussinessId == null || bussinessId == ""){
			alert("请选择产品名！");
			return;
		}
		
		var interfaceId = "all";
		if(isExistInterface){
			interfaceId = $("#e3").val();
		}
		Load('正在运行，请稍后...');
		totalCountLoadEnd = false;
		tableLoadEnd = false;
		ChartLoadEnd = false;
		initDataTable();
		initChartSet(start,end,statisticalMethods,bussinessId,interfaceId);
		totalDataCount(bussinessId,interfaceId);
	}
	*/
	/*
	function initSiteList(){
		$.ajax({
			type : "post",
			url : $context.ctx + '/system/api/loadSiteList',
			data : "",
			async : false,
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				if (obj.resp_code == "success") {
					obj = jQuery.parseJSON(obj.resp_msg);
					bbsSiteList = obj.bbsSiteList;
					phoneLibSitelistData = obj.phoneLibSitelistData;
				}
			}
		});
	}
	*/
	function loadData(){
		var start = $("#start").val();
		var end = $("#end").val();
		var statisticalMethods = $("#e1").val();
		var bussinessId = $("#e2").val();
		if(bussinessId == null || bussinessId == ""){
			alert("请选择产品名！");
			return;
		}
		
		var interfaceId = "all";
		if(isExistInterface){
			interfaceId = $("#e3").val();
		}
		if($("#showChart").is(":hidden")){
			$("#showTable").show();
			if(!datatables){
				initDataTable();
			}
			else{
				showTable();
			}
			queryTable = true;
		}
		else{
			$("#showTable").hide();
			if(!myChart){
				myChart = echarts.init(document.getElementById('chart_1'));
			}
			if(!dataTotalChart){
				dataTotalChart = echarts.init(document.getElementById('chart_totalData'));
			}
			chartSet(start,end,statisticalMethods,bussinessId,interfaceId);
			queryChart = true;
		}
		//totalDataCount(bussinessId,interfaceId);
	}
	function initChartSet(start,end,statisticalMethods,bussinessId,interfaceId){
		chartSet(start,end,statisticalMethods,bussinessId,interfaceId);
		$("#showChart").hide();
	}
	$("#sub").click(function() {
		queryTable = false;
		queryChart = false;
		if($("#showData").is(":hidden")){
			$("#showData").show();
			$("#initPromptMsg").hide();
		}
		loadData();
	});
	/*
	$("#updateLib").click(function() {
		var start = $("#start").val();
		var end = $("#end").val();
		var bussinessId = $("#e2").val();
		if(bussinessId == null || bussinessId == ""){
			alert("请选择产品名！");
			return;
		}
		
		var interfaceId = "all";
		if(isExistInterface){
			interfaceId = $("#e3").val();
		}
		var obj={
				start:start,
				end:end,
				bussinessId:bussinessId,
				interfaceId:interfaceId
		};
		 $("#updateLib").attr("disabled", "disabled");
		 $("#updateLib").html("更新中...");
		setTimeout(function () {
			 $("#updateLib").html("更新库");
			 $("#updateLib").removeAttr("disabled");
	    }, 1*60*1000);
		Load('正在运行，请稍后...');
		$.ajax({
			type : "post",
			url : $context.ctx + '/system/api/updateLibData',
			data : obj,
			async : false,
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				if (obj.resp_code == "success") {
					dispalyLoad(); 
				}
			}
		});
	});
	*/
	function totalDataCount(bussinessId,interfaceId){
		var obj={
				bussinessId:bussinessId,
				interfaceId:interfaceId
		};
		totalCountLoadEnd = false;
		$.ajax({
			type : "post",
			url : $context.ctx + '/system/api/totalDataCount',
			data : obj,
			async : false,
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				dispalyLoad();
				if (obj.resp_code == "success") {
					obj = jQuery.parseJSON(obj.resp_msg);
					$("#totalDate").html(obj.date);
					$("#totalCount").html(obj.totalCount);
				}
			}
		});
	}
	$("#showType").click(function(){
		var start = $("#start").val();
		var end = $("#end").val();
		var statisticalMethods = $("#e1").val();
		var bussinessId = $("#e2").val();
		if(bussinessId == null || bussinessId == ""){
			alert("请选择产品名！");
			return;
		}
		
		var interfaceId = "all";
		if(isExistInterface){
			interfaceId = $("#e3").val();
		}
		var showType =  $("#showType").html();
		if("图表" == showType){
			$("#showChart").show();
			$("#showTable").hide();
			$("#showType").html("表格");
			if(!queryChart){
				if(!myChart){
					myChart = echarts.init(document.getElementById('chart_1'));
				}
				if(!dataTotalChart){
					dataTotalChart = echarts.init(document.getElementById('chart_totalData'));
				}
				chartSet(start,end,statisticalMethods,bussinessId,interfaceId);
				queryTable = true;
			}
		}
		else{
			$("#showChart").hide();
			$("#showTable").show();
			$("#showType").html("图表");
			if(!queryTable){
				if(!datatables){
					initDataTable();
				}
				else{
					showTable();
				}
				queryChart = true;
			}
		}
	});
	$("#e1").change(function(){ 
		var statisticalMethods = $("#e1").val();
		var date=new Date();
		var dat="";
		if("1" == statisticalMethods){
			dat=new Date(date.getTime()-90*24*3600*1000);
		}else{
			dat=new Date(date.getTime()-7*24*3600*1000);
		}
		$("#end").val(date.getFullYear()+"-"+ (date.getMonth()+1)+"-"+ date.getDate());
		$("#start").val(dat.getFullYear()+"-"+ (dat.getMonth()+1)+"-"+ dat.getDate());
	});
	$("#export").click(function() {	
		var start = $("#start").val();
		var end = $("#end").val();
		var statisticalMethods = $("#e1").val();
		var bussinessId = $("#e2").val();
		var bussinessName =  encodeURIComponent(encodeURIComponent($("#e2 option:selected").text()));
		if(bussinessId == null || bussinessId == ""){
			alert("请选择产品名！");
			return;
		}
		var interfaceName = "";
		var interfaceId= "all";
		if(isExistInterface){
			interfaceName =  encodeURIComponent(encodeURIComponent($("#e3 option:selected").text()));
			interfaceId = $("#e3").val();
		}
		var oSettings = datatables.fnSettings();
		var iSortCol_0  =  oSettings.aaSorting[0][0];
		var sSortDir_0  =  oSettings.aaSorting[0][1];
		window.location.href = $context.ctx + "/system/api/exportDataStatistics?start="+start+"&end="+end+"&statisticalMethods="
							+statisticalMethods+"&bussinessId="+bussinessId+"&bussinessName="+bussinessName+"&interfaceName="+interfaceName+"&interfaceId="+interfaceId+"&iSortCol_0="+iSortCol_0+"&sSortDir_0="+sSortDir_0;
	});
	
	function initInterfaceList(){
		isExistInterface = false;
		var subSum = 0;
		var interfaceListData = $("#interfaceListData").html();
		var interfaceList = eval("("+interfaceListData+")");
		var bussinessId = $("#e2").val();
		if("" ==  bussinessId){
			$("#interfaceDiv").hide();
			return false;
		}
		$("#e3").html("");
		var itemAll = null;
		$.each(interfaceList,function(i,item){ 
			if(bussinessId == item.bussinessId){
				subSum++;
				if(item.interfaceUrl != "all"){
					$("#e3").append("<option value="+item.interfaceUrl+">"+item.interfaceName+"("+item.totalData+")</option>"); 
				}
				else{
					itemAll = item;
					$("#e3").prepend("<option value="+item.interfaceUrl+">"+item.interfaceName+"("+item.totalData+")</option>"); 
				}
				
			}
		});
		if(subSum > 1){
			isExistInterface = true;
		}
		var ss  = $("#e3").find("option:selected").text();
		$("#s2id_e3 .select2-chosen").first().text(ss);
		if(isExistInterface){
			$("#interfaceDiv").show();
		}
		else{
			$("#interfaceDiv").hide();
		}
		
	}
	$("#e2").change(function(){
		initInterfaceList();
	});
});

function initDataTable(){
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
        "aaSorting": [[ 0, "desc" ]],
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
        "sAjaxSource":  $context.ctx + "/system/api/showTabledata?start="+$("#start").val()+"&end="+$("#end").val()+"&bussinessId="
    	+$("#e2").val()+"&interfaceId="+$("#e3").val(),//如果从服务器端加载数据，这个属性用语指定加载的路径 
        /*"aaSorting": [[ 0, "desc" ]],//设置第3个元素为默认排序  */ 
       "aoColumns" : [ {
            "bVisible" : true,
            "bSortable": true,
            "mData" : "statisticsDate",
            "aTargets" : [ 0 ]
        }, {
           "bVisible" : true,
           "bSortable": true,
           "mData" : "totalData",
           "aTargets" : [ 1 ]
        }, {
            "bVisible" : true,
            "bSortable": true,
            "mData" : "dayIncrement",
            "aTargets" : [ 2 ]
        }, {
            "bVisible" : true,
            "bSortable": true,
            "mData" : "dayGrowthRate",
            "aTargets" : [ 3 ]
        }, ],
        "aoColumnDefs" : [ {
            sDefaultContent : '',
            aTargets : [ '_all' ]
        } ],
       "fnRowCallback": function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 
       },
       "fnPreDrawCallback": function(){
    	   Load('正在运行，请稍后...');
       },
       "fnInfoCallback": function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {
    		   dispalyLoad();
    	   return "当前第  "+iStart+" - "+ iEnd+" 条　共计 "+iTotal+" 条";
       }  
    });
}

function showTable(){
	var oSettings = datatables.fnSettings();
	oSettings.sAjaxSource = $context.ctx + "/system/api/showTabledata?start="+$("#start").val()+"&end="+$("#end").val()+"&bussinessId="
	+$("#e2").val()+"&interfaceId="+$("#e3").val(),//如果从服务器端加载数据，这个属性用语指定加载的路径 
	datatables.fnClearTable(0);
	datatables.fnDraw();
}
function chartSet(start,end,statisticalMethods,bussinessId,interfaceId){
	var option;
	// 过渡---------------------
	Load('正在运行，请稍后...');

	var obj={
			start:start,
			end:end,
			statisticalMethods:statisticalMethods,
			bussinessId:bussinessId,
			interfaceId:interfaceId
	};
	$("#sub").html('查询中...');
	$("#sub").attr("disabled", true);
	option = ajaxPost(obj, $context.ctx + '/system/api/showdata');
	if(option){
		var dataIncrementOption = option.dataIncrementOption;
		var dataTotalOption = option.dataTotalOption;
	}
	dataIncrementOption = jQuery.parseJSON(dataIncrementOption);
	dataTotalOption = jQuery.parseJSON(dataTotalOption);
	dataTotalChart.hideLoading();
	myChart.clear();
	dataTotalChart.clear();
	myChart.setOption(dataIncrementOption);
	dataTotalChart.setOption(dataTotalOption);
	dispalyLoad();
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


