var datatables;
$(function() {
	function initTable(){
		datatables = $('#myTable').DataTable({
	        "bPaginate": true,
	        "bLengthChange": false,
	        "bFilter": false, 
	        "bStateSave": false,       
	        "bInfo": true, 
	        "bDestroy":true,
	        "sPaginationType": "full_numbers",
	        "bRetrieve": true,
	        "bAutoWidth": true,
	        "bProcessing": false,              
	        "oLanguage": {      	
	        	"sLengthMenu": "每页显示 _MENU_条",  
	        	"sZeroRecords": "没有找到符合条件的数据",
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
	        "sAjaxSource": $context.ctx + "/system/report/phone_data_mom_query?start="+$("#start").val()+"&end="+$("#end").val()+"&appkey="+$("#e1").val()
									+"&phoneType="+$("#e2").val()+"&city="+$("#e3").val(),
	       "aoColumns" : [ {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "date",
	            "aTargets" : [ 0 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "totalPercent",
	            "aTargets" : [ 1 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "realNamePercent",
	            "aTargets" : [ 2 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "idNumberPercent",
	            "aTargets" : [ 3 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "networkTimePercent",
	            "aTargets" : [ 4 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "callRecordPercent",
	            "aTargets" : [ 5 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "billPercent",
	            "aTargets" : [ 6 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "netRecordPercent",
	            "aTargets" : [ 7 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "flowRecordPercent",
	            "aTargets" : [ 8 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "smsRecordPercent",
	            "aTargets" : [ 9 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "action",
	            "fnRender": function (obj) {
	            	var date = obj.aData["date"];
	            	var appkey = obj.aData["appkey"];
	            	var phoneType = obj.aData["phoneType"];
	            	var city = obj.aData["city"];
	            	return '<a target="_blank" class="btn btn-xs btn-info btn-detail" style="margin-left:6px;" href="'+$context.ctx+'/system/report/phone_data_mom_detail?date='+date+'&appkey='+appkey+'&phoneType='+phoneType+'&city='+city+'">查看详情</a>';
	             }
	        },],
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
		if(!datatables){
			initTable();
			return;
		}
		var start = $("#start").val();
		var end = $("#end").val(); 
		var appkey = $("#e1").val();
		var phoneType = $("#e2").val();
		var city = $("#e3").val();
		var oSettings = datatables.fnSettings();
		oSettings.sAjaxSource = $context.ctx + "/system/report/phone_data_mom_query?start="+start+"&end="+end+"&appkey="
								+appkey+"&phoneType="+phoneType+"&city="+city;//如果从服务器端加载数据，这个属性用语指定加载的路径
		datatables.fnClearTable(0);
		datatables.fnDraw();
	});
});