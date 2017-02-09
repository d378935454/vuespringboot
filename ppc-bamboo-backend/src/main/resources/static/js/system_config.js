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
	        "sAjaxSource": $context.ctx + "/system/conf/queryList",//如果从服务器端加载数据，这个属性用语指定加载的路径 
	       "aoColumns" : [ /*{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "parentKey",
	            "aTargets" : [ 0 ]
	        },*/	{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "configKey",
	            "aTargets" : [ 1 ]
	        },	{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "configValue",
	            "aTargets" : [ 2 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "remark",
	            "aTargets" : [ 3 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "createTime",
	            "aTargets" : [ 4 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "createUser",
	            "aTargets" : [ 5 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "updateTime",
	            "aTargets" : [ 6 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "updateUser",
	            "aTargets" : [ 7 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "isActive",
	            "aTargets" : [ 8 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "action",
	            "fnRender": function (obj) {
	            	console.log(obj);
	            	var id = obj.aData["id"];
	            	return '<ppd:auth ctype="edit" menuid="79" parentid="">' +
	            				'<a href="#user_edit" data-toggle="tab" class="btn btn-xs btn-info btn-detail" onclick="updateDetail(\'' + id + '\')"><i class="fa fa-edit"></i> <span class="hidden-inline-mobile">修改</a>' + ' ' +
	            				'<a target="_blank" class="btn btn-xs btn-info btn-detail" onclick="deleteById(this,\'' + id + '\')">删除</a>' + 
	            			'</ppd:auth>';
	             }
	        },],
	        "aoColumnDefs" : [ {
	            sDefaultContent : '',
	            aTargets : [ '_all' ]
	        } ],
	        "fnRowCallback": function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 
		    	   $('td:eq(4)', nRow).html(new Date(aData.createTime).Format("yyyy-MM-dd hh:mm:ss"));
		    	   $('td:eq(6)', nRow).html(new Date(aData.updateTime).Format("yyyy-MM-dd hh:mm:ss"));
		    	   if (aData.isActive == '1') {
		    		   $('td:eq(7)', nRow).html("有效");
		    	   } else {
		    		   $('td:eq(7)', nRow).html("无效");
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
	/**
	 * 查询列表搜索
	 */
	$("#search").click(function() {
		if(!datatables){
			initTable();
		}
		var configKey = $("#configKey").val(); 
		var oSettings = datatables.fnSettings();
		oSettings.sAjaxSource = $context.ctx + "/system/conf/queryList?configKey="+encodeURIComponent(encodeURIComponent(configKey));//如果从服务器端加载数据，这个属性用语指定加载的路径
		datatables.fnClearTable(0);
		datatables.fnDraw();
	});
	
});

/**
 * 添加
 */
$("#sub").click(function(){ 
	//后台交互
	if(confirm("确定保存吗?")){
		var remark=$("#remark_add").val();
		var configKey=$("#configKey_add").val();
		var configValue=$("#configValue_add").val();
		if(configKey=="" || configValue==""){
			alert("配置键和配置值不能为空！");
			return false;
		}
		var param = {
			configKey: configKey,
			configValue: configValue,
			remark: remark
		};
		$.ajax({
			type : "post",
			url : $context.ctx + '/system/conf/save',
			data : param,
			async : false,
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				if (obj.resp_code == "success") {  
					jAlert("保存成功","提示");//弹出对话框      					
					window.location.href = $context.ctx + "/system/conf/system_config";//跳转到列表页面
				} else {
					jAlert(obj.resp_msg,"提示");//弹出对话框
				}  
			}
		});     	        
	}
});

/**
 * 删除
 * @param _this
 * @param id
 */
function deleteById(_this, id) {
	if (confirm("确定删除吗?")) {
		$.post($context.ctx+"/system/conf/deleteById",{id:id},function(data){
			var obj = jQuery.parseJSON(data);
	    	if(obj.resp_code == "success"){
	    		jAlert("操作成功","提示");
	    		window.location.href = $context.ctx + "/system/conf/system_config";//跳转到列表页面
	    	} else {
	    		jAlert("操作失败","提示");
	    	}
		});
	}
}

/**
 * 修改
 * @param _this
 * @param id
 */
function updateDetail(id){
	$.ajax({
		type : "post",
		url : $context.ctx + '/system/conf/queryDetail',
		data : {id:id},
		async : false,
		success : function(data) {
			var obj = jQuery.parseJSON(data);
			if (obj.resp_code == "Data") { 
				if(obj.resp_body.isActive == 1){
					$("#isActive_edit").attr("checked",true);
				}else{
					$("#isActive_edit").attr("checked",false);
				}
				$("#id_edit").attr("value", obj.resp_body.id);
				$("#configKey_edit").attr("value", obj.resp_body.configKey);
				$("#configValue_edit").attr("value", obj.resp_body.configValue);
				$("#remark_edit").attr("value", obj.resp_body.remark);
			}else{
				jAlert(obj.resp_msg,"提示");//弹出对话框
			}
		}	
	});
}

/**
 * 修改
 */
$("#sub_edit").click(function(){ 
	//后台交互
	if(confirm("确定保存吗?")){
		var id=$("#id_edit").val();
		var configKey=$("#configKey_edit").val();
		var configValue=$("#configValue_edit").val();
		var remark=$("#remark_edit").val();
		var isActive=$("#isActive_edit").val();
		if(configKey=="" || configValue==""){
			alert("配置键和配置值不能为空！");
			return false;
		}
		var param = {
			id: id,
			configKey: configKey,
			configValue: configValue,
			remark: remark,
			isActive: isActive
		};
		$.ajax({
			type : "post",
			url : $context.ctx + '/system/conf/update',
			data : param,
			async : false,
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				if (obj.resp_code == "success") {  
					jAlert("保存成功","提示");//弹出对话框      					
					window.location.href = $context.ctx + "/system/conf/system_config";//跳转到列表页面
				} else {
					jAlert(obj.resp_msg,"提示");//弹出对话框
				}  
			}
		});     	        
	}
});


function returnLookUser(){
	window.location.href = $context.ctx + "/system/conf/system_config";//跳转到列表页面
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

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}