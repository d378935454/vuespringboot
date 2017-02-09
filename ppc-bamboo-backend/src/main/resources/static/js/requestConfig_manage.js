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
			"sAjaxSource": $context.ctx + "/admin/request/queryConfig?type="+$("#e3").val()+"&api="+$("#e6").val(),//如果从服务器端加载数据，这个属性用语指定加载的路径
			"aoColumns" : [ {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "apiType",
	            "aTargets" : [ 0 ]
			},{
				"bVisible" : true,
				"bSortable": false,
				"mData" : "appKey",
				"aTargets" : [ 1 ]
			},{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "process",
	            "aTargets" : [ 2 ]
	        },{
	        	"bVisible" : true,
	        	"bSortable": false,
	        	"mData" : "orders",
	        	"aTargets" : [ 3 ]
	        },{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "api",
	            "aTargets" : [ 4 ]
	        },{
	        	"bVisible" : true,
	        	"bSortable": false,
	        	"mData" : "argument",
	        	"aTargets" : [ 5 ]
	        },{
	        	"bVisible" : true,
	        	"bSortable": false,
	        	"mData" : "argType",
	        	"aTargets" : [ 6 ]
	        },{
	        	"bVisible" : true,
	        	"bSortable": false,
	        	"mData" : "alias",
	        	"aTargets" : [ 7 ]
	        },{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "action",
	            "fnRender": function (obj) {
	                var id = obj.aData["id"];
	                var apiType = obj.aData["apiType"];
	                var api = obj.aData["api"];
	                return '<ppd:auth ctype="edit" menuid="1007" parentid="1001">'+
				                '<a href="#config_edit" data-toggle="tab" class="btn btn-xs btn-info btn-detail" onclick="update(\'' + id + '\',\'' + apiType + '\',\'' + api + '\')"><i class="fa fa-edit"></i> <span class="hidden-inline-mobile">修改</a>' + ' ' +
			    				'<a target="_blank" class="btn btn-xs btn-info btn-detail" onclick="del(\'' + id + '\',\'' + apiType + '\',\'' + api + '\')">删除</a>' + 
	                	   '</ppd:auth>';
	             }
	        }],
	        "aoColumnDefs" : [ {
	            sDefaultContent : '',
	            aTargets : [ '_all' ]
	        } ],
	        "fnRowCallback": function(nRow, aData, iDisplayIndex) {
	        	if (aData.apiType == '0') {
	        		$('td:eq(0)', nRow).html("<span>接口</span>");
	        	} else{
	        		$('td:eq(0)', nRow).html("<span>认证</span>");
	        	}
	        	if (aData.argType == '0') {
	        		$('td:eq(6)', nRow).html("<span>入参</span>");
	        	} else{
	        		$('td:eq(6)', nRow).html("<span>出参</span>");
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
		var oSettings = datatables.fnSettings();
		var type = $("#e3").val();
		var api = $("#e6").val();
//		var searchAppkey = $("#searchAppkey").val();
		oSettings.sAjaxSource = $context.ctx + "/admin/request/queryConfig?type="+type+"&api="+api;
		datatables.fnClearTable(0);
		datatables.fnDraw();
	});
	$("#saveButton").click(function(){       		
		//后台交互
		var apiType = $("#e1").val();
		var api = $("#e2").val();
		var obj = {
				apiType: apiType,
				api: api
		};
		$.ajax({
			type : "post",
			url : $context.ctx + '/admin/request/save',
			data : obj,
			async : false,
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				if (obj.resp_code == "success") {  
					alert("保存成功");
					window.location.href = $context.ctx + "/admin/request/requestConfig_manage";//跳转到列表页面
				} else {
					jAlert(obj.resp_msg,"提示");//弹出对话框
				}      				
			}
		});        	        
    }); 
	
	$("#e3").click(function(){
		var type = $(this).val();
		$("#e6").empty();
		$.ajax({
			type : "post",
			url : $context.ctx + "/admin/request/queryConfig_apiList",
			data : {type: type},
			dataType: "json",		
			success : function(data) {
				var apiList = data.apiList;
				$("#e6").empty();
				$("#e6").append("<option value=''>全部</option>"); 
				for(var i = 0 ; i < apiList.length ; i++){
					$("#e6").append("<option value="+apiList[i].api+">"+apiList[i].api+"-"+apiList[i].apiName+"</option>"); 
				}
			}
		});
	});
	
	$("#e1").click(function(){
		var type = $(this).val();
		$("#e2").empty();
		$.ajax({
			type : "post",
			url : $context.ctx + "/admin/request/queryConfig_list",
			data : {type: type},
			dataType: "json",		
			success : function(data) {
				var list = data.list;
				$("#e2").empty();
				$("#e2").append("<option value=''>全部</option>"); 
				for(var i = 0 ; i < list.length ; i++){
					$("#e2").append("<option value="+list[i].api+">"+list[i].api+"-"+list[i].apiName+"</option>"); 
				}
			}
		});
	});
	
	//初始化
	$("#init").click(function(){
		$.confirm("确定初始化?", function (s) {
			if (s) {$.post($context.ctx+"/admin/request/init",function(data){
					var obj = jQuery.parseJSON(data);
			    	if(obj.resp_code == "success"){
			    		//window.location.reload();
			    		datatables.fnReloadAjax(datatables.fnSettings());
			    		jAlert("初始化成功","提示");
			    	};
				});	
			};
		});	
	});
	
	//重置
	$("#reset").click(function(){
		$.confirm("确定重置?", function (s) {
			if (s) {$.post($context.ctx+"/admin/request/reset",function(data){
					var obj = jQuery.parseJSON(data);
			    	if(obj.resp_code == "success"){
			    		//window.location.reload();
			    		datatables.fnReloadAjax(datatables.fnSettings());
			    		jAlert("重置成功","提示");
			    	};
				});	
			};
		});
	});
	
	//提交
	$("#submit").click(function(){
		$.confirm("确定提交?", function (s) {
			if (s) {$.post($context.ctx+"/admin/request/submit",function(data){
					var obj = jQuery.parseJSON(data);
			    	if(obj.resp_code == "success"){
			    		//window.location.reload();
			    		datatables.fnReloadAjax(datatables.fnSettings());
			    		jAlert("提交成功","提示");
			    	};
				});	
			};
		});	
	});
	
});

function returnApi(){
	window.location.href = $context.ctx + "/admin/request/requestConfig_manage";
}

//修改查询
function update(id,apiType,api){
	$.ajax({
		type : "post",
		url : $context.ctx+"/admin/request/updateQuery?id="+id+"&apiType="+apiType+"&api="+api,
		dataType: "json",		
		success : function(data) {
			var processConfig = data.processConfig;
			$("#id").val(processConfig.id);
			$("#orders").val(processConfig.orders);
			$("#alias").val(processConfig.alias);
		}
	});
}

//修改
function updateConfig(){
	//后台交互
	var id = $("#id").val();
	var orders = $("#orders").val();
	var alias = $("#alias").val();
	var obj = {
			id: id,
			orders: orders,
			alias: alias
	};
	$.ajax({
		type : "post",
		url : $context.ctx + '/admin/request/updateConfig',
		data : obj,
		async : false,
		success : function(data) {
			var obj = jQuery.parseJSON(data);
			if (obj.resp_code == "success") {  
				alert("保存成功");
				window.location.href = $context.ctx + "/admin/request/requestConfig_manage";//跳转到列表页面
			} else {
				jAlert(obj.resp_msg,"提示");//弹出对话框
			}      				
		}
	});
}

//删除
function del(id,apiType,api){
	$.confirm("确定删除该用接口?", function (s) {
		if (s) {$.post($context.ctx+"/admin/request/delete?id="+id+"&apiType="+apiType+"&api="+api,function(data){
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