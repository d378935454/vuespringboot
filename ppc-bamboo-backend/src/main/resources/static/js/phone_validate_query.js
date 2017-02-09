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
	        "sAjaxSource": $context.ctx + "/system/report/phone_validate_data?phone="+$("#phone").val()+"&type="+$("#e4").val()+"&province="+$("#e6").val(),
	       "aoColumns" : [ {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "phoneType",
	            "aTargets" : [ 0 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "province",
	            "aTargets" : [ 1 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "fontCheck",
	            "aTargets" : [ 2 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "regexJs",
	            "aTargets" : [ 3 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "password",
	            "aTargets" : [ 4 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "keyboardType",
	            "aTargets" : [ 5 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "servicePasswordFormat",
	            "aTargets" : [ 6 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "servicePasswordRemark",
	            "aTargets" : [ 7 ]
	        },{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "smsCodeFormat",
	            "aTargets" : [ 8 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "smsCodeRemark",
	            "aTargets" : [ 9 ]
	        },{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "resetPasswordCode",
	            "aTargets" : [ 10 ]
	        },{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "resetPasswordCustomer",
	            "aTargets" : [ 11 ]
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
		var phone = $("#phone").val();
		var type = $("#e4").val(); 
		var province = $("#e6").val();
		var oSettings = datatables.fnSettings();
		oSettings.sAjaxSource = $context.ctx + "/system/report/phone_validate_data?phone="+phone+"&type="+type+"&province="+province;//如果从服务器端加载数据，这个属性用语指定加载的路径
		datatables.fnClearTable(0);
		datatables.fnDraw();
	});
});
