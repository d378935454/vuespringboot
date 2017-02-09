var datatables;
$(function() {
	function initTable(){
		datatables = $('#myTable').DataTable({
	        "bPaginate": true,
	        "bLengthChange": false,
	        "iDisplayLength": 10,
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
	        "sAjaxSource": $context.ctx + "/system/api/getSkyNetDataManagerQueryReport?start="+$("#start").val()+"&end="+$("#end").val()+"&appkey="+$("#e2").val()
									+"&serial_number="+$("#serial_number").val()+"&name="+encodeURIComponent($("#name").val())+"&idNumber="+$("#idNumber").val()+"&phoneNumber="
									+$("#phoneNumber").val()+"&tokenBatch="+$("#tokenBatch").val()+"&result="+$("#e4").val()+"&resource="+$("#e3").val(),
	       "aoColumns" : [ {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "serialNumber",
	            "aTargets" : [ 1 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "queryBatchNumber",
	            "aTargets" : [ 2 ]
	        },{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "queryTime",
	            "aTargets" : [ 3 ]
	        }, {
	            "bVisible" : true,
	            "mData" : "name",
	            "aTargets" : [ 4 ]
	        },{
	            "bVisible" : true,
	            "mData" : "idnumber",
	            "aTargets" : [ 5 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "phone",
	            "aTargets" : [ 6 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "queryResultUseTime",
	            "aTargets" : [ 7 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "queryStatus",
	            "aTargets" : [ 8 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "recordCount",
	            "aTargets" : [ 9 ]
	        },{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "verification",
	            "aTargets" : [ 10 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "action",
	            "fnRender": function (obj) {
	            	var serial_number = obj.aData["serialNumber"];//ID 
	            	var queryTime = obj.aData["queryTime"];
	            	var name = obj.aData["name"];	            	
	            	var idnumber = obj.aData["idnumber"];
	            	var queryBatchNumber = obj.aData["queryBatchNumber"];	            	
	            	var queryStatus = obj.aData["queryStatus"];	            	
	            	var queryTime=obj.aData["queryTime"];
	            	var phone=obj.aData["phone"];
	            	
	            	var result = '<a target="_blank" class="btn btn-xs btn-info btn-detail" style="margin-left:6px;" href="'+$context.ctx+'/system/api/getSkyNetDataManagerQueryDetailReport?serial_number='+serial_number+
	            	'&queryTime='+queryTime+'&name='+name+'&idnumber='+idnumber+'&queryStatus='+queryStatus+
	            	'&queryBatchNumber='+queryBatchNumber+'&phone='+phone+'">详情</a>'; 
	            	return result;
	             }
	        },],
	        "aoColumnDefs" : [ {
	            sDefaultContent : '',
	            aTargets : [ '_all' ]
	        } ],
	        "fnRowCallback": function(nRow, aData, iDisplayIndex) {
		    	   console.info(nRow);
		    	   if (aData.queryStatus==1){
		    		   $('td:eq(7)', nRow).html("成功有数据");
		    	   } else if (aData.queryStatus==2) {
		    		   $('td:eq(7)', nRow).html("成功无数据");
		    	   }else if (aData.queryStatus==3){
		    		   $('td:eq(7)', nRow).html("查询失败");
		    		   $('td:eq(9)', nRow).html("-");
		    	   }
		    	   
		    	   if (aData.verification==1){
		    		   $('td:eq(9)', nRow).html("是");
		    	   } else if (aData.verification==0) {
		    		   $('td:eq(9)', nRow).html("-");
		    		   if (aData.queryStatus==1){
		    			   $('td:eq(9)', nRow).html("否");
		    		   }
		    	   }
		    	   
		    	   $('td:eq(6)', nRow).html(aData.queryResultUseTime/1000);
		    	   
//		    	   $('td:eq(2)', nRow).html(aData.queryTime/60*60);
		    	   
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
		var appkey = $("#e2").val();
		var queryType =  encodeURIComponent($("#e3").val());
		
		var queryResult = encodeURIComponent($("#e4").val());
		
		var token = $("#serial_number").val();
		var tokenBatch = $("#batch_number").val();
		var name =encodeURIComponent( $("#name").val());
		var idNumber = $("#idNumber").val();
		var phoneNumber = $("#phoneNumber").val();
		var oSettings = datatables.fnSettings();
		oSettings.sAjaxSource = $context.ctx + "/system/api/getSkyNetDataManagerQueryReport?start="+start+"&end="+end+"&appkey="+appkey
								+"&tokenSerialNumber="+token+"&name="+name+"&idNumber="+idNumber+"&phoneNumber="
								+phoneNumber+"&tokenBatchNumber="+tokenBatch+"&result="+queryResult+"&resource="+queryType;//如果从服务器端加载数据，这个属性用语指定加载的路径
		datatables.fnClearTable(0);
		datatables.fnDraw();
	});
});



 