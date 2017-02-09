var datatables;
$(function(){
	function initTable(){
		datatables = $('#datatableTable').dataTable({
			"bPaginate": true, //翻页功能  
	        "bLengthChange": false, //改变每页显示数据数量  
	        "bFilter": false, //过滤功能  
	        "bInfo": true,//页脚信息  
	        "bDestroy":true,
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
			"sAjaxSource": $context.ctx + "/admin/manage/query?status="+$("#e1").val()+"&searchCondition="+$("#searchCondition").val(),//如果从服务器端加载数据，这个属性用语指定加载的路径
			"aoColumns" : [ {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "user.company",
	            "aTargets" : [ 0 ]
	        },{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "user.email",
	            "aTargets" : [ 1 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "user.realName",
	            "aTargets" : [ 2 ]
	        }, /*{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "tel",
	            "aTargets" : [ 3 ]
	        },*/ {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "appKey",
	            "aTargets" : [ 3 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "user.status",
	            "aTargets" : [ 4 ]
	        }, /*{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "collectionSystemURL",
	            "aTargets" : [ 6 ]
	        }, */{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "action",
	            "fnRender": function (obj) {
	                var id = obj.aData["id"];
	                var appKey = obj.aData["appKey"];
	                var user = obj.aData["user"];
	                var realName = user.realName;
	                var status = user.status;
	                if(status == 'S'){
	                	return '<ppd:auth ctype="edit" menuid="1002" parentid="1001"><button value="'+id+'" class="btn btn-xs btn-info btn-freeze" style="margin-left:6px;" onclick="check(\''+realName+'\',\''+appKey+'\')">配置</button></ppd:auth>';
	                }
	                /*if (isActive == 'S') {
	                	 return '<ppd:auth ctype="edit" menuid="31" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-freeze" style="margin-left:6px;" onclick="freeze(\'' + sReturn + '\')">冻结</button></ppd:auth>'+
	             		'<ppd:auth ctype="edit" menuid="32" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="resetPassword(\'' + sReturn + '\')"><font>重置密码</font></button></ppd:auth>'+
	             		'<ppd:auth ctype="edit" menuid="33" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="editFun(\'' + sReturn + '\',\'' + company + '\',\'' + email + '\',\'' + realName + '\',\'' + tel + '\',\'' + url  + '\')"><font>修改信息</font></button></ppd:auth>'+
	             		'<ppd:auth ctype="edit" menuid="34" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="productConfiguration(\'' + sReturn + '\')"><font>产品配置</font></button></ppd:auth>'+
	                	 '<ppd:auth ctype="edit" menuid="35" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="showAppKey(\'' + sReturn + '\')"><font>查看密钥</font></button></ppd:auth>';
	                } else {
	                	return '<ppd:auth ctype="edit" menuid="31" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-unfreeze" style="margin-left:6px;" onclick="unfreeze(\'' + sReturn + '\')"><font color="red">解冻</font></button></ppd:auth>'+
	             		'<ppd:auth ctype="edit" menuid="32" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="resetPassword(\'' + sReturn + '\')"><font>重置密码</font></button></ppd:auth>'+
	             		'<ppd:auth ctype="edit" menuid="33" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="editFun(\'' + sReturn + '\',\'' + company + '\',\'' + email + '\',\'' + realName + '\',\'' + tel + '\',\'' + url  + '\')"><font>修改信息</font></button></ppd:auth>'+
	             		'<ppd:auth ctype="edit" menuid="34" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="productConfiguration(\'' + sReturn + '\')"><font>产品配置</font></button></ppd:auth>'+
	                	'<ppd:auth ctype="edit" menuid="35" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="showAppKey(\'' + sReturn + '\')"><font>查看密钥</font></button></ppd:auth>';
	                }*/            
	             }
	        }, ],
	        "aoColumnDefs" : [ {
	            sDefaultContent : '',
	            aTargets : [ '_all' ]
	        } ],
	        "fnRowCallback": function(nRow, aData, iDisplayIndex) {
	        	if (aData.user.status == 'S') {
	        		$('td:eq(4)', nRow).html("<span>正常</span>");
	        	} else{
	        		$('td:eq(4)', nRow).html("<span style='color:red'>冻结</span>");
	        	}
	        	$('td:eq(6)', nRow).html("http://info.mj.ppdai.com/<br/>ppcredit_collection/user/"+aData.collectionSystemURL);
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
//		alert("123");
		if(!datatables){
			initTable();
			return;
		}
		var oSettings = datatables.fnSettings();
		var status = $("#e1").val();
		var searchCondition = $("#searchCondition").val();
//		var searchAppkey = $("#searchAppkey").val();
		oSettings.sAjaxSource = $context.ctx + "/admin/manage/query?status="+status+"&searchCondition="+searchCondition;
		datatables.fnClearTable(0);
		datatables.fnDraw();
	});
	$("#close1").click(function(){	
		$("#inputCompany").closest('.form-group').removeClass('has-error');
		$("#inputCompany").closest('.form-group').removeClass('has-success');
		$("span[for='inputCompany']").addClass('valid').html("");
		$("#inputEmail").closest('.form-group').removeClass('has-error');
		$("#inputEmail").closest('.form-group').removeClass('has-success');
		$("span[for='inputEmail']").addClass('valid').html(""); 
		$("#inputRealName").closest('.form-group').removeClass('has-error');
		$("#inputRealName").closest('.form-group').removeClass('has-success');
		$("span[for='inputRealName']").addClass('valid').html(""); 
		$("#inputTel").closest('.form-group').removeClass('has-error');
		$("#inputTel").closest('.form-group').removeClass('has-success');
		$("span[for='inputTel']").addClass('valid').html(""); 
		$("#inputUrl").closest('.form-group').removeClass('has-error');
		$("#inputUrl").closest('.form-group').removeClass('has-success');
		$("span[for='inputUrl']").addClass('valid').html("");
		$('#myModal').modal('hide');//关闭模态框 			
	});
	$("#close2").click(function(){
		$("#inputCompany").closest('.form-group').removeClass('has-error');
		$("#inputCompany").closest('.form-group').removeClass('has-success');
		$("span[for='inputCompany']").addClass('valid').html("");
		$("#inputEmail").closest('.form-group').removeClass('has-error');
		$("#inputEmail").closest('.form-group').removeClass('has-success');
		$("span[for='inputEmail']").addClass('valid').html(""); 
		$("#inputRealName").closest('.form-group').removeClass('has-error');
		$("#inputRealName").closest('.form-group').removeClass('has-success');
		$("span[for='inputRealName']").addClass('valid').html(""); 
		$("#inputTel").closest('.form-group').removeClass('has-error');
		$("#inputTel").closest('.form-group').removeClass('has-success');
		$("span[for='inputTel']").addClass('valid').html(""); 
		$("#inputUrl").closest('.form-group').removeClass('has-error');
		$("#inputUrl").closest('.form-group').removeClass('has-success');
		$("span[for='inputUrl']").addClass('valid').html("");
		$('#myModal').modal('hide');//关闭模态框 	
	});
	//全选按钮
	$("#checkAllProduct").click(function() {
		if ($("#checkAllProduct").is(":checked")){
			$("[name='checkboxMethodProduct']").prop("checked",true); 
		} else{
			$("[name='checkboxMethodProduct']").prop("checked",false);   
		}        
    });
	//确定按钮
	$("#productButtonSave").click(function(){
		var isc = "";
    	var checkName = $("#checkboxProduct").html("");
    	$("input[name='checkboxMethodProduct']").each(function(){ //遍历table里的全部checkbox		
            if($(this).is(":checked")) {//如果被选中
            	isc += $(this).val() + ","; //获取被选中的值
            	checkName.append('<div class="col-md-6"><span>'+$(this).siblings().text()+'</span></div>');
            }	
    	});
    	if(isc.length > 0) {//如果获取到
            isc = isc.substring(0, isc.length - 1); //把最后一个逗号去掉
    	}
    	$("#checkboxIds").val(isc);
		$('#productConfigurationModal').modal('hide');//关闭模态框       
		$('#checkboxModal').modal('show');//开启模态框 
		$("#userId").val($("#productConfigurationId").val());
	});	
    //全选按钮
	$("#checkAll").click(function() {
		if ($("#checkAll").is(":checked")){
			$("[name='checkboxAdd']").prop("checked",true); 
		} else{
			$("[name='checkboxAdd']").prop("checked",false);   
		}        
    });
	$("#saveButton").click(function(){       		
		//后台交互
		var modalCompany = $("#modalCompany").html();
		var modalUserName = $("#modalUserName").html();
		var modalEmail = $("#modalEmail").html();
		var modalTel = $("#modalTel").html();
		var modalcollectionSystemURL = $("#modalcollectionSystemURL").html();
		var ids = $("#addUserIds").val();
		var obj = {
			modalCompany: modalCompany,
			modalUserName: modalUserName,
			modalEmail: modalEmail,
			modalTel: modalTel,
			modalcollectionSystemURL: modalcollectionSystemURL,
			ids: ids
		};
		$.ajax({
			type : "post",
			url : $context.ctx + '/system/api/add_user',
			data : obj,
			async : false,
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				if (obj.resp_code == "success") {  
					$('#addUserModal').modal('hide');//关闭模态框       					
					jAlert("用户新增成功","提示");//弹出对话框      					
					window.location.href = $context.ctx + "/system/api/basics_manage";//跳转到列表页面
				} else {
					jAlert(obj.resp_msg,"提示");//弹出对话框
				}      				
			}
		});        	        
    }); 
});

function check(realName, appKey){
	$.post($context.ctx+"/admin/process/process_appkey?appKey="+appKey,function(data){
		var obj = jQuery.parseJSON(data);
    	if(obj.resp_code == "success"){
    		var processList = obj.resp_body.processList;
    		var processCheckList = obj.resp_body.processCheckList;
    		var strHtml = $("#productConfigurationBody").html("");
    		for(var i=0; i <processList.length;i++ ) {   
    			strHtml.append('<div class="col-md-4"><input type="checkbox" name="checkboxMethodProduct" value="'+processList[i].processId+'"> <span>'+processList[i].processName+'</span></div>');
    	    }
    		for (var i = 0 ; i < processCheckList.length; i++){
    			$("[name='checkboxMethodProduct']").each(function(){
    			     if($(this).val() ==processCheckList[i].processId){
    			    	 $(this).prop("checked",true);
    			     }                        
    			}); 
    		} 
    		$("#productConfigurationId").val(appKey);
    		$("#checkAllProduct").prop("checked",false);
    		$("#productConfigurationModal").modal("show");
    	};
	});
}

//保存
function checkboxBtnSave(){
	var userId = $("#userId").val();
	var checkboxIds = $("#checkboxIds").val(); 
	$("#checkboxBtnSave").button('loading');
	$.post($context.ctx+"/admin/process/saveProcessAppkey",{userId: userId, ids: checkboxIds},function(data){
		$("#checkboxBtnSave").button('reset');
		var obj = jQuery.parseJSON(data);
		if(obj.resp_code == "success"){
			$('#checkboxModal').modal('hide');//关闭模态框   
			//alert("操作成功");
			jAlert("操作成功","提示");
		} else {
			alert(obj.resp_msg);
		}
	});
}

/*function check(realName, appKey){
	$.confirm("确定选中该用户?", function (s) {
		if (s) {
			$.post($context.ctx+"/admin/manage/save?realName="+realName+"&appKey="+appKey,function(data){
				var obj = jQuery.parseJSON(data);
		    	if(obj.resp_code == "success"){
		    		//window.location.reload();
		    		datatables.fnReloadAjax(datatables.fnSettings());
		    		jAlert("操作成功","提示");
		    	};
			});	
		};
	});
}*/

function addNewUser(){
	window.location.href = $context.ctx + "/system/api/to_add_user";
}

function freeze(userId){
	$.confirm("确定冻结该用户?", function (s) {
		if (s) {
			$.post($context.ctx+"/system/api/userFreeze?id="+userId+"&status=F",function(data){
				var obj = jQuery.parseJSON(data);
		    	if(obj.resp_code == "success"){
		    		//window.location.reload();
		    		datatables.fnReloadAjax(datatables.fnSettings());
		    		jAlert("操作成功","提示");
		    	};
			});	
		};
	});	
}

function unfreeze(userId){
	$.confirm("确定解冻该用户?", function (s) {
		if (s) {
			$.post($context.ctx+"/system/api/userFreeze?id="+userId+"&status=S",function(data){
				var obj = jQuery.parseJSON(data);
		    	if(obj.resp_code == "success"){
		    		//window.location.reload();
		    		datatables.fnReloadAjax(datatables.fnSettings());
		    		jAlert("操作成功","提示");
		    	};
			});
		};
	});
}

function resetPassword(userId){
	$.confirm("确定重置该用户密码?", function (s) {
		if (s) {
			$.post($context.ctx+"/system/api/userResetPassword?id="+userId,function(data){
				var obj = jQuery.parseJSON(data);
		    	if(obj.resp_code == "success"){
		    		//window.location.reload();
		    		datatables.fnReloadAjax(datatables.fnSettings());
		    		jAlert("操作成功","提示");
		    	};
			});
		};
	});
}

function editFun(userId, company, email, realName, tel, url){
	$("#inputCompany").val(company);
	$("#inputEmail").val(email);
	$("#inputRealName").val(realName);
	$("#inputTel").val(tel);
	$("#inputUrl").val(url);
	$("#objectId").val(userId);
	$("#myModal").modal("show");
	FormWizard.init();
}

function productConfiguration(userId){
	$.post($context.ctx+"/system/api/loadProductConfiguration?id="+userId,function(data){
		var obj = jQuery.parseJSON(data);
    	if(obj.resp_code == "success"){
    		var methodList = obj.resp_body.methodList;
    		var conditionList = obj.resp_body.conditionList;
    		var strHtml = $("#productConfigurationBody").html("");
    		for(var i=0; i <methodList.length;i++ ) {   
    			strHtml.append('<div class="col-md-4"><input type="checkbox" name="checkboxMethodProduct" value="'+methodList[i].id+'"> <span>'+methodList[i].descName+'</span></div>');
    	    }
    		for (var i = 0 ; i < conditionList.length; i++){
    			$("[name='checkboxMethodProduct']").each(function(){
    			     if($(this).val() ==conditionList[i].id){
    			    	 $(this).prop("checked",true);
    			     }                        
    			}); 
    		} 
    		$("#productConfigurationId").val(userId);
    		$("#checkAllProduct").prop("checked",false);
    		$("#productConfigurationModal").modal("show");
    	};
	});
}
//继续修改
function continueUpdateBtn(){
	var userId = $("#userId").val();
	var checkboxIds = $("#checkboxIds").val();
	var checkboxId = checkboxIds.split(",");
	console.info(checkboxId.length);
	$.post($context.ctx+"/admin/process/loadProcessConfiguration?id="+userId,function(data){
		var obj = jQuery.parseJSON(data);
    	if(obj.resp_code == "success"){
    		var processList = obj.resp_body.processList;
    		var strHtml = $("#productConfigurationBody").html("");
    		for(var i=0; i <processList.length;i++ ) {   
    			strHtml.append('<div class="col-md-4"><input type="checkbox" name="checkboxMethodProduct" value="'+processList[i].processId+'"> <span>'+processList[i].processName +'</span></div>');
    	    }
    		for (var i = 0 ; i < checkboxId.length; i++){
    			if (checkboxId[i] != "" || checkboxId[i] != null){
    				$("[name='checkboxMethodProduct']").each(function(){
		   			     if($(this).val() ==checkboxId[i]){
		   			    	 $(this).prop("checked",true);
		   			     }                        
		   			}); 
    			}   			
    		} 
    		$("#productConfigurationId").val(userId);
    		$('#checkboxModal').modal('hide');//关闭模态框   
    		$("#productConfigurationModal").modal("show");//开启模态框
    	};
	});
}

function showAppKey(userId) {	
	$.post($context.ctx+"/system/api/show_appkey",{userId: userId},function(data){
		var obj = jQuery.parseJSON(data);
		$("#showAppModal").modal("show");//开启模态框
		if (obj.resp_code == "success"){
			$("#appKeyValue").html(obj.resp_body.appKey);
			$("#appSecretValue").html(obj.resp_body.appSecret);
		}		
	});
}

var FormWizard = function () {
    return {
        init: function () {
            var wizform = $('#resForm');
            wizform.validate({
                doNotHideMessage: true,
				errorClass: 'error-span',
                errorElement: 'span',
                rules: {
                	company: {
                        required: true
                    },
                	email: {
                        required: true,
                        email: true
                    },
                    realName: {
                    	required: true,
                    	maxlength: 5
                    },
                    tel: {
                    	required: true,
                    	isBegin: true
                    },
                    url: {
                    	required: true,
                    	isSystemURL: true
                    }
                },
                submitHandler: function(form){
                	$(form).ajaxSubmit({
                        type:"post",
                        url:$context.ctx+"/system/api/updateUser",
                        success: function(data,status){
                        	var obj = jQuery.parseJSON(data);
                        	if(obj.resp_code == "error"){
                        		//alert(obj.resp_msg);
                        		jAlert(obj.resp_msg,"提示");
                        	}
                        	if(obj.resp_code == "success"){                        		
                        		document.getElementById("resForm").reset(); 
                        		$('#myModal').modal('hide');//关闭模态框                        		
                        		datatables.fnReloadAjax(datatables.fnSettings());
                        		jAlert("操作成功","提示");
                        		//window.location.reload();
                        		//jAlert('操作成功');
                        	}
                        }
                      });               	            	
                },
                invalidHandler: function (event, validator) { 
                	
                },

                highlight: function (element) {                	
                    $(element)
                        .closest('.form-group').removeClass('has-success').addClass('has-error'); 
                },

                unhighlight: function (element) {
                    $(element)
                        .closest('.form-group').removeClass('has-error'); 
                },

                success: function (label) {
                    if (label.attr("for") == "gender") { 
                        label.closest('.form-group').removeClass('has-error').addClass('has-success');
                        label.remove(); 
                    } else {
                        label.addClass('valid') 
                        .closest('.form-group').removeClass('has-error').addClass('has-success'); 
                    }
                    
                }
            });
        }
    };
}();
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

function returnLookUser(){
	$("#lookUser").click();
}


var AddFormWizard = function () {
    return {
        init: function () {
            var wizform = $('#wizForm');
            wizform.validate({
                doNotHideMessage: true,
				errorClass: 'error-span',
                errorElement: 'span',
                rules: {
                    /* Create Account */
                	company: {
                    	required: true
                    },
                	email: {
                        required: true,
                        email: true
                    },
                    username: {
                    	required: true,
                    	maxlength: 5
                    },
                    tel: {
                    	required: true,
                    	isBegin: true
                    },
                    url: {
                    	required: true,
                    	isSystemURL: true
                    }
                },
                submitHandler: function(form){
                	$("#addUserModal").modal("show");
                	var company = $("#company").val();
                	var email = $("#email").val();
                	var username = $("#username").val();
                	var tel = $("#tel").val();
                	var collectionSystemURL = $("#url").val();
                	$("#modalCompany").html(company);
                	$("#modalUserName").html(username);
                	$("#modalEmail").html(email);
                	$("#modalTel").html(tel);
                	$("#modalcollectionSystemURL").html(collectionSystemURL);
                	var isc = "";
                	var checkName = $("#productConfiguration").html("");
                	$("input[name='checkboxAdd']").each(function(){ //遍历table里的全部checkboxAdd		
                        if($(this).is(":checked")) {//如果被选中
                        	isc += $(this).val() + ","; //获取被选中的值
                        	checkName.append('<div class="col-md-6"><span>'+$(this).siblings().text()+'</span></div>');
                        }	
                	});
                	if(isc.length > 0) {//如果获取到
                        isc = isc.substring(0, isc.length - 1); //把最后一个逗号去掉
                	}
                	$("#addUserIds").val(isc);
                },
                invalidHandler: function (event, validator) { 
                	
                },

                highlight: function (element) { 
                    $(element)
                        .closest('.form-group').removeClass('has-success').addClass('has-error'); 
                },

                unhighlight: function (element) { 
                    $(element)
                        .closest('.form-group').removeClass('has-error'); 
                },

                success: function (label) {
                    if (label.attr("for") == "gender") { 
                        label.closest('.form-group').removeClass('has-error').addClass('has-success');
                        label.remove(); 
                    } else { 
                        label.addClass('valid') 
                        .closest('.form-group').removeClass('has-error').addClass('has-success'); 
                    }                    
                }
            });           		
        }
    };
}();