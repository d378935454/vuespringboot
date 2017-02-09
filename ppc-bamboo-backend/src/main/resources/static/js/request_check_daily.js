var datatables;
$(function(){
	function initTable(){
		datatables = $('#datatableTable').dataTable({
			"bPaginate": true, //翻页功能  
	        "bLengthChange": false, //改变每页显示数据数量  
	        "bFilter": false, //过滤功能  
	        "bInfo": true,//页脚信息  
	        "bDestroy":true,
	        "aaSorting": [[ 0, "desc" ]],
	        'bStateSave': false,
	        "bRetrieve": true,
	        "bAutoWidth": true,//自动宽度  
	        "sPaginationType": "full_numbers",
	        "bProcessing": false,//加载数据时候是否显示进度条
			"oLanguage": {
				"sLengthMenu": "每页显示 _MENU_条",  
	        	"sZeroRecords": "没有找到符合条件的数据",
	        	"sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",  
	        	"sInfoEmpty": "没有记录",  
	        	"sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
			    "oPaginate": {
			    	"sFirst": "首页",  
		        	"sPrevious": "前一页",  
		        	"sNext": "后一页",  
		        	"sLast": "尾页"
			    }
			},
			"bServerSide": true,//是否从服务器加载数据
			"sAjaxSource": $context.ctx + "/system/bill/statis/requestCheck_query?start="+$("#start").val()+"&end="+$("#end").val()+"&dataSourceID="+$("#e1").val()+"&methodName="+$("#e2").val(),//如果从服务器端加载数据，这个属性用语指定加载的路径
			"aoColumns" : [ {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "formatLOG_DATE",
	            "aTargets" : [ 0 ]
	        },{
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "dATA_SOURCE_VALUE",
	            "aTargets" : [ 1 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "mETHOD_NAME",
	            "aTargets" : [ 2 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "cUSTOM_TOTAL_COUNT",
	            "aTargets" : [ 3 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "sUPPLIER_TOTAL_COUNT",
	            "aTargets" : [ 4 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "lOCAL_TOTAL_COUNT",
	            "aTargets" : [ 5 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "balance",
	            "aTargets" : [ 6 ]
	        }, ],
	        "aoColumnDefs" : [ {
	            sDefaultContent : '',
	            aTargets : [ '_all' ]
	        } ],
	        "fnRowCallback": function(nRow, aData, iDisplayIndex) {
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
		doQuery();
	});
	
	function doQuery() {
		if($("#showData").is(":hidden")){
			$("#showData").show();
			$("#initPromptMsg").hide();
		}
		if(!datatables){
			initTable();
		}
		else{
			var oSettings = datatables.fnSettings();
			var start = $("#start").val();
			var end = $("#end").val();
			var dataSourceID = $("#e1").val();
			var methodName = $("#e2").val();
			oSettings.sAjaxSource = $context.ctx + "/system/bill/statis/requestCheck_query?start="+start+"&end="+end+"&dataSourceID="+dataSourceID+"&methodName="+methodName;
			datatables.fnClearTable(0);
			datatables.fnDraw();
		}
	}
	
	$("#export").click(function() {	
		var start = $("#start").val();
		var end = $("#end").val();
		var dataSourceID = $("#e1").val();
		var methodName = $("#e2").val();
		window.location.href = $context.ctx + "/system/bill/statis/requestCheck_export?start="+start+"&end="+end+"&dataSourceID="+dataSourceID+"&methodName="+methodName;
	});
	
	//加载即执行将查询条件设置好并查询
	$("#start").val($("#initStart").val());
	$("#end").val($("#initEnd").val());
	$("#e1").val($("#initDataSourceID").val());
	$("#e2").val($("#initMethodName").val());
	
	doQuery();
});