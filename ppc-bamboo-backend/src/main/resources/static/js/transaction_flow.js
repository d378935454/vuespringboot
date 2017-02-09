var datatables;
$(function() {
	function initTable(){
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
	        "aaSorting": [[ 1, "desc" ]],
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
	        "sAjaxSource": $context.ctx + "/system/api/transaction_flowdata?start="+$("#start").val()+"&end="+$("#end").val()+"&serialNumber="
									+$("#serialNumber").val()+"&methodName="+$("#e1").val()+"&userID="+$("#e2").val()+"&isSuccess="
									+$("#e3").val()+"&result="+$("#e4").val()+"&productId="+$("#e11").val()+"&dataSourceId="+$("#e12").val(),//如果从服务器端加载数据，这个属性用语指定加载的路径 
	        /*"aaSorting": [[ 0, "desc" ]],//设置第3个元素为默认排序  */ 
	       "aoColumns" : [ {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "serialNumber",
	            "aTargets" : [ 0 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "formatInserttime",
	            "aTargets" : [ 1 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "userName",
	            "aTargets" : [ 2 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "interfaceName",
	            "aTargets" : [ 3 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "useTime",
	            "aTargets" : [ 4 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "callStartTime",
	            "aTargets" : [ 5 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "startTime",
	            "aTargets" : [ 6 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "endTime",
	            "aTargets" : [ 7 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "callEndTime",
	            "aTargets" : [ 8 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "isSuccess",
	            "aTargets" : [ 9 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "supplier",
	            "aTargets" : [ 10 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "result",
	            "aTargets" : [ 11 ]
	        },],
	        "aoColumnDefs" : [ {
	            sDefaultContent : '',
	            aTargets : [ '_all' ]
	        } ],
	       "fnRowCallback": function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 
	    	   if (aData.supplier=='00'){
	    		   $('td:eq(10)', nRow).html("<span>本地库</span>");
	    	   } else if (aData.supplier=='01'){
	    		   $('td:eq(10)', nRow).html("<span>银联</span>");
	    	   } else if (aData.supplier=='02'){
	    		   $('td:eq(10)', nRow).html("<span>爰金</span>");
	    	   } else if (aData.supplier=='03'){
	    		   $('td:eq(10)', nRow).html("<span>国政通</span>");
	    	   } else if (aData.supplier=='04'){
	    		   $('td:eq(10)', nRow).html("<span>鹏元</span>");
	    	   } else if (aData.supplier=='07'){
	    		   $('td:eq(10)', nRow).html("<span>人人催</span>");
	    	   } else if (aData.supplier=='08'){
	    		   $('td:eq(10)', nRow).html("<span>神州融</span>");
	    	   } else {
	    		   $('td:eq(10)', nRow).html("<span>爬虫</span>"); 
	    	   }
	    	   if (aData.isSuccess == 'S') {
	    		   $('td:eq(9)', nRow).html("<span>成功</span>");
	    	   } else if (aData.isSuccess == 'F') {
	               $('td:eq(9)', nRow).html("<span style='color:red'>失败</span>");
	           } else{
	        	   $('td:eq(9)', nRow).html("<span></span>");
	           }
	    	   if (aData.result == "查询失败") {
	    		   $('td:eq(11)', nRow).html("<span style='color:red'>"+aData.result+"</span>");
	    	   } else{
	    		   $('td:eq(11)', nRow).html("<span>"+aData.result+"</span>");
	    	   }
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
	$("#search").click(function() {
		var start = $("#start").val();
		var end = $("#end").val();
		var serialNumber = $("#serialNumber").val(); 
		var methodName = $("#e1").val();
		var userID = $("#e2").val();
		var isSuccess = $("#e3").val();
		var result = $("#e4").val();
		var productId = $("#e11").val();
		var dataSourceId = $("#e12").val();
		showStatistics(start, end, serialNumber, methodName, userID, isSuccess, result, productId, dataSourceId);
		if(!datatables){
			initTable();
			return;
		}
		var oSettings = datatables.fnSettings();
		oSettings.sAjaxSource = $context.ctx + "/system/api/transaction_flowdata?start="+start+"&end="+end+"&serialNumber="
								+serialNumber+"&methodName="+methodName+"&userID="+userID+"&isSuccess="
								+isSuccess+"&result="+result+"&productId="+productId+"&dataSourceId="+dataSourceId;//如果从服务器端加载数据，这个属性用语指定加载的路径
		datatables.fnClearTable(0);
		datatables.fnDraw();
	});
	$("#export").click(function() {	
		var start = $("#start").val();
		var end = $("#end").val();
		var serialNumber = $("#serialNumber").val(); 
		var methodName = $("#e1").val();
		var userID = $("#e2").val();
		var isSuccess = $("#e3").val();
		var result = $("#e4").val();
		var productId = $("#e11").val();
		var dataSourceId = $("#e12").val();
		window.location.href = $context.ctx + "/system/api/export?start="+start+"&end="+end+"&serialNumber="
							+serialNumber+"&methodName="+methodName+"&userID="+userID+"&isSuccess="
							+isSuccess+"&result="+result+"&productId="+productId+"&dataSourceId="+dataSourceId;
	});
	
	$("#e11").click(function(){
		var productId = $(this).val();
		$("#e1").empty();
		$.ajax({
			type : "post",
			url : $context.ctx + "/system/api/product_method",
			data : {productId: productId},
			dataType: "json",		
			success : function(data) {
				var methodList = data.methodList;
				$("#e1").empty();
				$("#e1").append("<option value=''>全部</option>"); 
				for(var i = 0 ; i < methodList.length ; i++){
					$("#e1").append("<option value="+methodList[i].name+">"+methodList[i].descName+"</option>"); 
				}
				$("#s2id_e1").children().children().first().text("全部");
			}
		});
	});
});

function showStatistics(start, end, serialNumber, methodName, userID, isSuccess, result, productId, dataSourceId) {
	var obj = {
			start: start,
			end: end,
			serialNumber: serialNumber,
			methodName: methodName,
			userID: userID,
			isSuccess: isSuccess,
			result: result,
			productId: productId,
			dataSourceId: dataSourceId
	}; 
	$.ajax({
		type : "post",
		url : $context.ctx + "/system/api/statistics_flow",
		data : obj,
		dataType: "json",		
		success : function(data) {
			$("#queryCount").html(data.queryStatisticalBean.queryCount);
			$("#successCount").html(data.queryStatisticalBean.successCount);
			$("#failureCount").html(data.queryStatisticalBean.failureCount);
			$("#successRate").html(data.queryStatisticalBean.successRate);
			$("#averageLength").html(data.queryStatisticalBean.averageLength/1000);
		},
		 error: function() {
			 $("#queryCount").html();
			 $("#successCount").html();
			 $("#failureCount").html();
			 $("#successRate").html();
			 $("#averageLength").html();
		 }
	});
}