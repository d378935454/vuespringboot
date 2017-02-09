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
	        "sAjaxSource": $context.ctx + "/system/report/phone_data_query?start="+$("#start").val()+"&end="+$("#end").val()+"&appkey="+$("#e1").val()
									+"&token="+$("#token").val()+"&name="+$("#name").val()+"&idNumber="+$("#idNumber").val()+"&phonenumber="
									+$("#phonenumber").val()+"&collectionStatus="+$("#e2").val()+"&analysisStatus="+$("#e3").val()
									+"&pushStatus="+$("#e4").val()+"&userno="+$("#userno").val()+"&billtype="+$("#e6").val(),
	       "aoColumns" : [ {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "userNo",
	            "aTargets" : [ 0 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "token",
	            "aTargets" : [ 1 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "name",
	            "aTargets" : [ 2 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "idNumber",
	            "aTargets" : [ 3 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "phonenumber",
	            "aTargets" : [ 4 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "billType",
	            "aTargets" : [ 5 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "company",
	            "aTargets" : [ 6 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "certificationTime",
	            "aTargets" : [ 7 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "preCollectionStatus",
	            "aTargets" : [ 8 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "collectionStatus",
	            "aTargets" : [ 9 ]
	        },{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "analysisStatus",
	            "aTargets" : [ 10 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "pushStatus",
	            "aTargets" : [ 11 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "action",
	            "fnRender": function (obj) {
	            	var token = obj.aData["token"];                            //token
	            	var preCollectionStatus = obj.aData["preCollectionStatus"];//预采集状态
	            	var analysisStatus = obj.aData["analysisStatus"];          //解析状态
	            	var pushStatus = obj.aData["pushStatus"];                  //推送状态
	            	var errorStatus = obj.aData["errorStatus"];
	            	var result = '';
	            	result +='<a target="_blank" class="btn btn-xs btn-info btn-detail" style="margin-left:6px;" href="'+$context.ctx+'/system/report/phone_data_detail?token='+token+'">详情</a>';
	            	if (preCollectionStatus==1&&analysisStatus==0) {
	            		result +='<a class="btn btn-xs btn-info btn-detail" style="margin-left:6px;" onclick="repairOrder(\''+token+'\')">补单</a>';
	            	} else {
	            		result +='<a class="btn btn-xs btn-info btn-detail" disabled="true" style="margin-left:6px;">补单</a>';
	            	}
	            	if (errorStatus==1&&pushStatus==0) {
	            		result +='<a class="btn btn-xs btn-info btn-detail" style="margin-left:6px;" onclick="pushOrder(\''+token+'\')">推送</a>';
	            	} else {
	            		result +='<a class="btn btn-xs btn-info btn-detail" disabled="true" style="margin-left:6px;">推送</a>';
	            	}
	            	if (preCollectionStatus==1||analysisStatus==1||pushStatus==1) {
	            		result +='<a target="_blank" class="btn btn-xs btn-info btn-detail" style="margin-left:6px;" href="'+$context.ctx+'/system/report/phone_data_export_txt?token='+token+'">下载</a>';
	            	} else {
	            		result +='<a class="btn btn-xs btn-info btn-detail" disabled="true" style="margin-left:6px;">下载</a>';
	            	}
	            	
	            	return result;
	             }
	        },],
	        "aoColumnDefs" : [ {
	            sDefaultContent : '',
	            aTargets : [ '_all' ]
	        } ],
	       "fnRowCallback": function(nRow, aData, iDisplayIndex) {
	    	   if (aData.preCollectionStatus=='1'){
	    		   $('td:eq(8)', nRow).html("<span>成功</span>");
	    	   } else if (aData.preCollectionStatus=='3'){
	    		   $('td:eq(8)', nRow).html("<span>-</span>");
	    	   } else {
	    		   $('td:eq(8)', nRow).html("<span>失败</span>");
	    	   }
	    	   if (aData.analysisStatus == '1'){
	    		   $('td:eq(9)', nRow).html("<span>成功</span>");
	    		   $('td:eq(10)', nRow).html("<span>成功</span>");
	    	   } else if (aData.analysisStatus == '3'){
	    		   $('td:eq(9)', nRow).html("<span>-</span>");
	    		   $('td:eq(10)', nRow).html("<span>-</span>");
	    	   } else {
	    		   $('td:eq(9)', nRow).html("<span>失败</span>");
	    		   $('td:eq(10)', nRow).html("<span>失败</span>");
	    	   }
	    	   if (aData.pushStatus == '1'){
	    		   $('td:eq(11)', nRow).html("<span>成功</span>");
	    	   } else if (aData.errorStatus=='1'&&aData.pushStatus == '0'){
	    		   $('td:eq(11)', nRow).html("<span>失败</span>");
	    	   } else {
	    		   $('td:eq(11)', nRow).html("<span>无推送</span>");
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
		if(!datatables){
			initTable();
			return;
		}
		var start = $("#start").val();
		var end = $("#end").val(); 
		var appkey = $("#e1").val();
		var token = $("#token").val();
		var name = $("#name").val();
		var idNumber = $("#idNumber").val();
		var phonenumber = $("#phonenumber").val();
		var collectionStatus = $("#e2").val();
		var analysisStatus = $("#e3").val();
		var pushStatus =  $("#e4").val();
		var userno = $("#userno").val();
		var billtype = $("#e6").val();
		var oSettings = datatables.fnSettings();
		oSettings.sAjaxSource = $context.ctx + "/system/report/phone_data_query?start="+start+"&end="+end+"&appkey="
								+appkey+"&token="+token+"&name="+name+"&idNumber="+idNumber+"&phonenumber="+phonenumber+"&collectionStatus="
								+collectionStatus+"&analysisStatus="+analysisStatus+"&pushStatus="+pushStatus+"&userno="+userno+"&billtype="+billtype;//如果从服务器端加载数据，这个属性用语指定加载的路径
		datatables.fnClearTable(0);
		datatables.fnDraw();
	});
});

function repairOrder(token) {
	$.confirm("确定提交补单?", function (s) {
		if (s) {
    		$.post($context.ctx+"/system/report/repair_bill?token="+token,function(data){
    			var obj = jQuery.parseJSON(data);
            	if(obj.resp_code == "success"){
            		alert(obj.resp_body);
            	};
    		});
		}
	});
}

function pushOrder(token) {
	$.confirm("确定提交推送?", function (s) {
		if (s) {
    		$.post($context.ctx+"/system/report/push_bill?token="+token,function(data){
    			var obj = jQuery.parseJSON(data);
            	if(obj.resp_code == "success"){
            		alert(obj.resp_body);
            	};
    		});
		}
	});
}