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
	        "sAjaxSource": $context.ctx + "/system/nine/conf/queryUserList",//如果从服务器端加载数据，这个属性用语指定加载的路径 
	       "aoColumns" : [ {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "apiRealname",
	            "aTargets" : [ 0 ]
	        },	{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "apiCompany",
	            "aTargets" : [ 1 ]
	        },	{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "apiAppkey",
	            "aTargets" : [ 2 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "isTiebaSpider",
	            "aTargets" : [ 3 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "isQQSpider",
	            "aTargets" : [ 4 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "isCheckBlack",
	            "aTargets" : [ 5 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "isDerivePhone",
	            "aTargets" : [ 6 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "isDataResult",
	            "aTargets" : [ 7 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "insertTime",
	            "aTargets" : [ 8 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "updateTime",
	            "aTargets" : [ 9 ]
	        },  {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "action",
	            "fnRender": function (obj) {
	            	var id = obj.aData["id"];
	            	var apiUid = obj.aData["apiUid"];
	            	var apiAppkey = obj.aData["apiAppkey"];
	            	return '<ppd:auth ctype="edit" menuid="79" parentid="">' +
	            				'<a target="_blank" class="btn btn-xs btn-info btn-detail" onclick="updateUser(this,\'' + id + '\')">保存</a>' + ' ' +
	            				'<a href="#user_add" data-toggle="tab" class="btn btn-xs btn-info btn-detail" onclick="updateDetail(this,\'' + apiUid + '\',\'' + apiAppkey + '\')"><i class="fa fa-edit"></i> <span class="hidden-inline-mobile">修改</a>' + ' ' +
	            				'<a target="_blank" class="btn btn-xs btn-info btn-detail" onclick="deleteById(this,\'' + id + '\')">删除</a>' + 
	            			'</ppd:auth>';
	             }
	        },],
	        "aoColumnDefs" : [ {
	            sDefaultContent : '',
	            aTargets : [ '_all' ]
	        } ],
	       "fnRowCallback": function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 
	    	   if (aData.isTiebaSpider == '1') {
	    		   $('td:eq(3)', nRow).html("<input type='checkbox' onclick='checkIsTiebaSpider($(this))' name='isTiebaSpider' value='1' checked/>");
	    	   } else {
	    		   $('td:eq(3)', nRow).html("<input type='checkbox' onclick='checkIsTiebaSpider($(this))' name='isTiebaSpider' value='0'/>");
	    	   }
	    	   if (aData.isQQSpider == '1') {
	    		   $('td:eq(4)', nRow).html("<input type='checkbox' onclick='checkIsQQSpider($(this))' name='isQQSpider' value='1' checked/>");
	    	   } else {
	    		   $('td:eq(4)', nRow).html("<input type='checkbox' onclick='checkIsQQSpider($(this))' name='isQQSpider' value='0'/>");
	    	   }
	    	   if (aData.isCheckBlack == '1') {
	    		   $('td:eq(5)', nRow).html("<input type='checkbox' onclick='checkIsCheckBlack($(this))' name='isCheckBlack' value='1' checked/>");
	    	   } else {
	    		   $('td:eq(5)', nRow).html("<input type='checkbox' onclick='checkIsCheckBlack($(this))' name='isCheckBlack' value='0'/>");
	    	   }
	    	   if (aData.isDerivePhone == '1') {
	    		   $('td:eq(6)', nRow).html("<input type='checkbox' onclick='checkIsDerivePhone($(this))' name='isDerivePhone' value='1' checked/>");
	    	   } else {
	    		   $('td:eq(6)', nRow).html("<input type='checkbox' onclick='checkIsDerivePhone($(this))' name='isDerivePhone' value='0'/>");
	    	   }
	    	   if (aData.isDataResult == '1') {
	    		   $('td:eq(7)', nRow).html("<input type='checkbox' onclick='checkIsDataResult($(this))' name='isDataResult' value='1' checked/>");
	    	   } else {
	    		   $('td:eq(7)', nRow).html("<input type='checkbox' onclick='checkIsDataResult($(this))' name='isDataResult' value='0'/>");
	    	   }
	    	   $('td:eq(8)', nRow).html(new Date(aData.insertTime).Format("yyyy-MM-dd hh:mm:ss"));
	    	   $('td:eq(9)', nRow).html(new Date(aData.updateTime).Format("yyyy-MM-dd hh:mm:ss"));
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
		var company = $("#company").val();
		var username = $("#username").val();
		var appkey = $("#appkey").val(); 
		var oSettings = datatables.fnSettings();
		oSettings.sAjaxSource = $context.ctx + "/system/nine/conf/queryUserList?company="+encodeURIComponent(encodeURIComponent(company))+"&username="
							+encodeURIComponent(encodeURIComponent(username))+"&appkey="+appkey;//如果从服务器端加载数据，这个属性用语指定加载的路径
		datatables.fnClearTable(0);
		datatables.fnDraw();
	});
	
});

/**
 * 新增配置提交
 */
$("#sub").click(function(){ 
	var apiRealname_ = $("#e1").val();
	if(apiRealname_=='all'){
		alert("姓名和APPKEY不能为空！");
		return false;
	}
	var array = apiRealname_.split(",");
	var apiUid = array[0];
	var company = array[1];
	var realName = array[2]; 
	var apiAppkey = array[3];     		
	//后台交互
	if(confirm("确定保存吗?")){
		var isFormal_ = $("#e4").val();
		var start = $("#start").val();
		var end = $("#end").val();
		var id = $("#id").val();
		var isTiebaSpider0=$('input:radio[id="isTiebaSpider0"]:checked').val();
		var isTiebaSpider1=$('input:radio[id="isTiebaSpider1"]:checked').val();
		var isTiebaSpider2=$('input:radio[id="isTiebaSpider2"]:checked').val();
		var isQQSpider=$('input:checkbox[name="isQQSpider_"]:checked').val();
		var isPpdBlack=$('input:checkbox[name="isPpdBlack"]:checked').val();
		var isRrcBlack=$('input:checkbox[name="isRrcBlack"]:checked').val();
		var isZzcBlack=$('input:checkbox[name="isZzcBlack"]:checked').val();
		var isYixinBlack=$('input:checkbox[name="isYixinBlack"]:checked').val();
		var isFaHaiBlack=$('input:checkbox[name="isFaHaiBlack"]:checked').val();
		var isDerivePhone=$('input:checkbox[name="isDerivePhone_"]:checked').val();
		//var isDataResult=$('input:checkbox[name="isDataResult"]:checked').val();
		var obj = {
			id: id,
			apiUid: apiUid,
			company: company,
			realName: realName,
			apiAppkey: apiAppkey,
			isFormal_: isFormal_,
			start: start,
			end: end,
			isTiebaSpider0:isTiebaSpider0,
			isTiebaSpider1:isTiebaSpider1,
			isTiebaSpider2:isTiebaSpider2,
			isQQSpider:isQQSpider,
			isPpdBlack:isPpdBlack,
			isRrcBlack:isRrcBlack,
			isZzcBlack:isZzcBlack,
			isYixinBlack:isYixinBlack,
			isFaHaiBlack:isFaHaiBlack,
			isDerivePhone:isDerivePhone/*,
			isDataResult:isDataResult*/
		};
		$.ajax({
			type : "post",
			url : $context.ctx + '/system/nine/conf/save',
			data : obj,
			async : false,
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				if (obj.resp_code == "success") {  
					//$('#addUserModal').modal('hide');//关闭模态框       					
					jAlert("保存成功","提示");//弹出对话框      					
					window.location.href = $context.ctx + "/system/nine/conf/nine_api_user_conf";//跳转到列表页面
				} else {
					jAlert(obj.resp_msg,"提示");//弹出对话框
				}  
			}
		});     	        
	}
});



function returnLookUser(){
	window.location.href = $context.ctx + "/system/nine/conf/nine_api_user_conf";//跳转到列表页面
}

function checkIsTiebaSpider(isTiebaSpider) {
	if(isTiebaSpider.is(":checked")) {
		isTiebaSpider.attr("checked",true);
		isTiebaSpider.val("1");
	} else {
		isTiebaSpider.attr("checked",false);
		isTiebaSpider.val("0");
	}
}

function checkIsQQSpider(isQQSpider) {
	if(isQQSpider.is(":checked")) {
		isQQSpider.attr("checked",true);
		isQQSpider.val("1");
	} else {
		isQQSpider.attr("checked",false);
		isQQSpider.val("0");
	}
}

function checkIsCheckBlack(isCheckBlack) {
	if(isCheckBlack.is(":checked")) {
		isCheckBlack.attr("checked",true);
		isCheckBlack.val("1");
	} else {
		isCheckBlack.attr("checked",false);
		isCheckBlack.val("0");
	}
}

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



function checkIsDerivePhone(isDerivePhone) {
	if(isDerivePhone.is(":checked")) {
		isDerivePhone.attr("checked",true);
		isDerivePhone.val("1");
	} else {
		isDerivePhone.attr("checked",false);
		isDerivePhone.val("0");
	}
}

function checkIsDataResult(isDataResult) {
	if(isDataResult.is(":checked")) {
		isDataResult.attr("checked",true);
		isDataResult.val("1");
	} else {
		isDataResult.attr("checked",false);
		isDataResult.val("0");
	}
}


/**
 * 保存
 * @param _this
 * @param id
 */
function updateUser(_this, id) {
	var isTiebaSpider = $(_this).parents("tr:first").find("input[name='isTiebaSpider']").val();
	var isQQSpider = $(_this).parents("tr:first").find("input[name='isQQSpider']").val();
	var isCheckBlack = $(_this).parents("tr:first").find("input[name='isCheckBlack']").val();
	var isDerivePhone = $(_this).parents("tr:first").find("input[name='isDerivePhone']").val();
	var isDataResult = $(_this).parents("tr:first").find("input[name='isDataResult']").val();
	if(confirm("确定保存吗?")){
		$.post($context.ctx+"/system/nine/conf/updateConf",{id:id, isTiebaSpider:isTiebaSpider, isQQSpider:isQQSpider, isCheckBlack:isCheckBlack, isDerivePhone:isDerivePhone, isDataResult:isDataResult},function(data){
			var obj = jQuery.parseJSON(data);
	    	if(obj.resp_code == "success"){
	    		datatables.fnReloadAjax(datatables.fnSettings());
	    		jAlert("操作成功","提示");
	    	} else {
	    		jAlert("操作失败","提示");
	    	}
	    	$(_this).parents("tr:first").removeClass("focusTr");
		});
	}
}

/**
 * 修改
 * @param _this
 * @param apiUid
 * @param apiAppkey
 */
function updateDetail(_this,apiUid, apiAppkey){
	detail(apiUid, apiAppkey, "0"); // 0 修改
}

/**
 * 删除
 * @param _this
 * @param id
 */
function deleteById(_this, id) {
	if (confirm("确定删除吗?")) {
		$.post($context.ctx+"/system/nine/conf/deleteById",{id:id},function(data){
			var obj = jQuery.parseJSON(data);
	    	if(obj.resp_code == "success"){
	    		datatables.fnReloadAjax(datatables.fnSettings());
	    		jAlert("操作成功","提示");
	    	} else {
	    		jAlert("操作失败","提示");
	    	}
	    	//$(_this).parents("tr:first").removeClass("focusTr");
		});
	}
}

/**
 * 姓名选择
 * @param data
 */
function change(data){
	var array = data.split(",");
	var apiUid = array[0];
	var apiAppkey = array[3];
	$("#apiAppkey").val(apiAppkey);
	detail(apiUid, apiAppkey, "1"); // 1新增
}

/**
 * 客户类型选择
 * @param id
 */
function sel(id){
	if(id == 1){ // 1: 正式
		$("#hid").hide();
	}else if(id == 0){ // 0: 试用
		$('#hid').show();
	}
}

/**
 * 查询明细
 * @param apiUid
 * @param apiAppkey
 */
function detail(apiUid, apiAppkey, flag){
	$.ajax({
		type : "post",
		url : $context.ctx + '/system/nine/conf/queryDetail',
		data : {apiUid:apiUid, apiAppkey:apiAppkey},
		async : false,
		success : function(data) {
			var obj = jQuery.parseJSON(data);
			if (obj.resp_code == "Data") {  
				$("#id").val(obj.resp_body.id);
				if(flag == "0"){
					var fa = obj.resp_body.apiUid+","+obj.resp_body.apiCompany+","+obj.resp_body.apiRealname+","+obj.resp_body.apiAppkey;
					$("#e1").find('option[value="'+fa+'"]').attr("selected","selected");
					$("#s2id_e1 .select2-chosen").first().text(obj.resp_body.apiRealname);
					$("#e1").attr("readonly","readonly");
					//$("#e4").attr("readonly","readonly");
					var apiAppkey = obj.resp_body.apiAppkey;
					$("#apiAppkey").val(apiAppkey);
				}
				
				if(obj.resp_body.isFormal == 1){
					$("#e4").find('option[value="'+obj.resp_body.isFormal+'"]').attr("selected","selected");
					$("#s2id_e4 .select2-chosen").first().text("正式");
					$("#hid").hide();
				}else if(obj.resp_body.isFormal == 0){
					$("#e4").find('option[value="'+obj.resp_body.isFormal+'"]').attr("selected","selected");
					$("#s2id_e4 .select2-chosen").first().text("试用");
					$('#hid').show();
					var trialDays = obj.resp_body.trialDays; // 天数
					var trialStartTime = obj.resp_body.trialStartTime.time; // 开始日期
					var start = new Date(trialStartTime).Format("yyyy-MM-dd"); // 开始日期
					$("#start").val(start);
					var myDate=new Date();
					myDate.setDate(new Date(trialStartTime).getDate()+trialDays); // 开始日期 + 30天
					var end = myDate.Format("yyyy-MM-dd");
					$("#end").val(end); // 30天后的日期
					//if(flag == "0"){
					//	$("#start").attr("disabled","disabled");
					//	$("#end").attr("disabled","disabled");
					//}
				}
				
				var search = obj.resp_body.searchEngine;
				if(search==1){
					$("input[id='isTiebaSpider1']").attr('checked',true);
				}else if(search==2){
					$("input[id='isTiebaSpider2']").attr('checked',true);
				}else if(search==0){
					$("input[id='isTiebaSpider0']").attr('checked',true);
				}
				
				if(obj.resp_body.isQQSpider == 1){
					$("#isQQSpider_").attr("checked",true);
				}else{
					$("#isQQSpider_").attr("checked",false);
				}
				
				if(obj.resp_body.isPpdBlack == 1){
					$("#isPpdBlack").attr("checked",true);
				}else{
					$("#isPpdBlack").attr("checked",false);
				}
				
				if(obj.resp_body.isRrcBlack == 1){
					$("#isRrcBlack").attr("checked",true);
				}else{
					$("#isRrcBlack").attr("checked",false);
				}
				
				if(obj.resp_body.isZzcBlack == 1){
					$("#isZzcBlack").attr("checked",true);
				}else{
					$("#isZzcBlack").attr("checked",false);
				}
				
				if(obj.resp_body.isYixinBlack == 1){
					$("#isYixinBlack").attr("checked",true);
				}else{
					$("#isYixinBlack").attr("checked",false);
				}
				
				if(obj.resp_body.isFaHaiBlack == 1){
					$("#isFaHaiBlack").attr("checked",true);
				}else{
					$("#isFaHaiBlack").attr("checked",false);
				}
				
				if(obj.resp_body.isDerivePhone == 1){
					$("#isDerivePhone_").attr("checked",true);
				}else{
					$("#isDerivePhone_").attr("checked",false);
				}
			}else{
				$("#id").val("");
				$("#e4").find('option[value="1"]').attr("selected","selected");
				$("#s2id_e4 .select2-chosen").first().text("正式");
				$("#hid").hide();
				$("input[type=radio]").attr("checked",false);
				$("input[type=checkbox]").attr("checked",false);
				$("input[id='isTiebaSpider0']").attr('checked',true);
			} 
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