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
	        "aaSorting": [[ 1, "desc" ]],
	        "sPaginationType": "full_numbers",   /*默认翻页样式设置*/
	        "bRetrieve": true,
	        "bAutoWidth": true,//自动宽度  
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
	        "sAjaxSource": $context.ctx + "/system/api/function_userdata?company="+$("#company").val()+"&username="
							+$("#username").val()+"&appkey="+$("#appkey").val(),//如果从服务器端加载数据，这个属性用语指定加载的路径 
	       "aoColumns" : [ {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "company",
	            "aTargets" : [ 0 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "realName",
	            "aTargets" : [ 1 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "appKey",
	            "aTargets" : [ 2 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "isyixin",
	            "aTargets" : [ 3 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "isdataresult",
	            "aTargets" : [ 4 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "inserttime",
	            "aTargets" : [ 5 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "updatetime",
	            "aTargets" : [ 6 ]
	        },  {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "action",
	            "fnRender": function (obj) {
	            	var sReturn = obj.aData["id"];
	            	return '<ppd:auth ctype="edit" menuid="79" parentid=""><a target="_blank" class="btn btn-xs btn-info btn-detail" onclick="updateUser(this,\'' + sReturn + '\')">保存</a></ppd:auth>';
	             }
	        },],
	        "aoColumnDefs" : [ {
	            sDefaultContent : '',
	            aTargets : [ '_all' ]
	        } ],
	       "fnRowCallback": function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 
	    	   if (aData.isyixin == '1') {
	    		   $('td:eq(3)', nRow).html("<input type='checkbox' onclick='checkIsyixin($(this))' name='isyixin' value='1' checked/>");
	    	   } else {
	    		   $('td:eq(3)', nRow).html("<input type='checkbox' onclick='checkIsyixin($(this))' name='isyixin' value='0'/>");
	    	   }
	    	   if (aData.isdataresult == '1') {
	    		   $('td:eq(4)', nRow).html("<input type='checkbox' onclick='checkIsdataresult($(this))' name='isdataresult' value='1' checked/>");
	    	   } else {
	    		   $('td:eq(4)', nRow).html("<input type='checkbox' onclick='checkIsdataresult($(this))' name='isdataresult' value='0'/>");
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
		var company = $("#company").val();
		var username = $("#username").val();
		var appkey = $("#appkey").val(); 
		var oSettings = datatables.fnSettings();
		oSettings.sAjaxSource = $context.ctx + "/system/api/function_userdata?company="+company+"&username="
							+username+"&appkey="+appkey;//如果从服务器端加载数据，这个属性用语指定加载的路径
		datatables.fnClearTable(0);
		datatables.fnDraw();
	});
});

function checkIsyixin(isyixin) {
	if(isyixin.is(":checked")) {
		isyixin.attr("checked",true);
		isyixin.val("1");
	} else {
		isyixin.attr("checked",false);
		isyixin.val("0");
	}
}

function checkIsdataresult(isdataresult) {
	if(isdataresult.is(":checked")) {
		isdataresult.attr("checked",true);
		isdataresult.val("1");
	} else {
		isdataresult.attr("checked",false);
		isdataresult.val("0");
	}
}
function updateUser(_this, id) {
	$(_this).parents("tr:first").addClass("focusTr");
	var isyixin = $(_this).parents("tr:first").find("input[name='isyixin']").val();
	var isdataresult = $(_this).parents("tr:first").find("input[name='isdataresult']").val();
	$.confirm("确定保存吗?", function (s) {
		if (s) {
			$.post($context.ctx+"/system/api/function_update",{id:id, isyixin:isyixin, isdataresult:isdataresult},function(data){
				var obj = jQuery.parseJSON(data);
		    	if(obj.resp_code == "success"){
		    		datatables.fnReloadAjax(datatables.fnSettings());
		    		jAlert("操作成功","提示");
		    	} else {
		    		jAlert("操作失败","提示");
		    	}
		    	$(_this).parents("tr:first").removeClass("focusTr");
			});
		} else {
			datatables.fnReloadAjax(datatables.fnSettings());
			$(_this).parents("tr:first").removeClass("focusTr");
		}
	});
}

/*
Datatables刷新方法
*/
$.fn.dataTableExt.oApi.fnReloadAjax = function (oSettings) {
	this.fnClearTable(this);
	this.oApi._fnProcessingDisplay(oSettings, true);
	var that = this;	 
	$.getJSON(oSettings.sAjaxSource, {iDisplayStart:oSettings._iDisplayStart,iDisplayLength:oSettings._iDisplayLength}, function (json) {
	    /* Got the data - add it to the table */
	    for (var i = 0; i < json.aaData.length; i++) {
	        that.oApi._fnAddData(oSettings, json.aaData[i]);
	    }
	    oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
	    //that.fnDraw(that);
	    that.fnDraw(false);
	    that.oApi._fnProcessingDisplay(oSettings, false);
	});
};