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
			"sAjaxSource": $context.ctx + "/system/admin/operationLog_query?start="+$("#start").val()+"&end="+$("#end").val()+"&operationPerson="+$("#e1").val()+"&operationType="+$("#e2").val(),//如果从服务器端加载数据，这个属性用语指定加载的路径
			"aoColumns" : [ {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "insertTime",
	            "aTargets" : [ 0 ]
	        },{
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "userLoginName",
	            "aTargets" : [ 1 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "userRealName",
	            "aTargets" : [ 2 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "operationType",
	            "aTargets" : [ 3 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": true,
	            "mData" : "operationContent",
	            "aTargets" : [ 4 ]
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
			var operationPerson = $("#e1").val();
			var operationType = $("#e2").val();
			oSettings.sAjaxSource = $context.ctx + "/system/admin/operationLog_query?start="+start+"&end="+end+"&operationPerson="+operationPerson+"&operationType="+operationType;
			datatables.fnClearTable(0);
			datatables.fnDraw();
		}
	});
});