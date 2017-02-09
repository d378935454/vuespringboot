<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="ppd"  uri="/WEB-INF/tld/ppd.tld" %>
var datatables;
$(function() {
	function initTable(){	
		datatables = $('#datatableTable').DataTable({
			/*基本参数设置，以下参数设置和默认效果一致*/  
	        "bPaginate": true, //翻页功能  
	        "bLengthChange": false, //改变每页显示数据数量  
	        "bFilter": false, //过滤功能  
	        "bStateSave": false,       
	        "bInfo": true,//页脚信息  
	        "bDestroy":true,
	        "bRetrieve": true,
	        "iDisplayLength": 20,
	        "bAutoWidth": true,//自动宽度  
	        "sPaginationType": "full_numbers",
	        "bProcessing": false,//加载数据时候是否显示进度条                 
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
	       "sAjaxSource": $context.ctx + "/system/creditreport/findSolr_credit_report?start="+$("#start").val()+"&end="+$("#end").val()
	        				+"&searchCondition="+$("#searchCondition").val()+"&status="+$("#e1").val()+"&companyName="+$("#e2").val(),//如果从服务器端加载数据，这个属性用语指定加载的路径 
	       "aoColumns" : [ {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "reportId",
	            "aTargets" : [ 0 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "name",
	            "aTargets" : [ 1 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "idNumber",
	            "aTargets" : [ 2 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "reportTime",
	            "aTargets" : [ 3 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "reportStatus",
	            "aTargets" : [ 4 ]
	        }
	        ,{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "reportUseTime",
	            "aTargets" : [ 5 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "action",
	            "fnRender": function (obj) {             
	            	var rowkey = obj.aData["rowkey"];
	            	var reportStatus = obj.aData["reportStatus"];
	            	if(reportStatus == '已生成'){
		            	return '<ppd:auth ctype="edit" menuid="52" parentid=""><a target="_blank" class="btn btn-xs btn-info btn-detail" href="'+$context.ctx+'/system/creditreport/credit_report_hbase_detail?rowkey='+rowkey+'">详情查看</a></ppd:auth>';  	
	            	}else{
	            		return '<ppd:auth ctype="edit" menuid="52" parentid=""><a target="_blank" class="btn btn-xs btn-info btn-detail">详情查看</a></ppd:auth>';
	            	}
	             }
	        },],
	        "aoColumnDefs" : [ {
	            sDefaultContent : '',
	            aTargets : [ '_all' ]
	        } ],
	       "fnRowCallback": function(nRow, aData, iDisplayIndex) {
	    	  if (aData.reportStatus=='生成中'){
	    		   $('td:eq(4)', nRow).html("<span>生成中</span>");
	    	   }  
	    	   if (aData.reportStatus=='生成失败'){
	    		   $('td:eq(4)', nRow).html("<span style='color:red;'>生成失败</span>");
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
		//avgCreditReport();
		var start = $("#start").val();
		var end = $("#end").val();
		var searchCondition = $("#searchCondition").val();
		var status = $("#e1").val();
		var companyName = $("#e2").val();
		var oSettings = datatables.fnSettings();
		oSettings.sAjaxSource = $context.ctx + "/system/creditreport/findSolr_credit_report?start="+start+"&end="+end
								+"&searchCondition="+searchCondition+"&status="+status+"&companyName="+companyName;//如果从服务器端加载数据，这个属性用语指定加载的路径
		datatables.fnClearTable(0);
		datatables.fnDraw();		
	});
	$("#companyName").autocomplete({
		minLength: 2,	
		source: function(request, response) {
		$.ajax({
			url: $context.ctx + "/system/creditreport/company_autocomplete",
			type : "post",
			data: {
				searchCondition: $("#companyName").val()
			},
			success: function(data) {
				var obj = jQuery.parseJSON(data);
				 response($.map(obj.resp_body, function(item) {
                                return { label: item.company +"   "+ item.email , value: item.company}
                 }));
			}
		});
		},
		select: function(event, ui) {
			$("#companyName").val(ui.item.value);			
		}
	});
});

function showCreditReportDetail(idnumber, phonecell){	
	window.location.href = $context.ctx + "/system/creditreport/credit_report_hbase_detail?idnumber="+idnumber+"&phonecell="+phonecell;
}

function avgCreditReport(){
	var start = $("#start").val();
	var end = $("#end").val();
	var searchCondition = $("#searchCondition").val();
	var status = $("#e1").val();
	var companyName = $("#e2").val();
	$.post($context.ctx+"/system/creditreport/avg_credit_hbase_report",{start:start, end:end, searchCondition:searchCondition, status:status, companyName: companyName},function(data){
		var data = jQuery.parseJSON(data);
		$("#avgCreditReport").html(data.average);
	});
}