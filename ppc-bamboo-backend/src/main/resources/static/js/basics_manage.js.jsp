<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="ppd"  uri="/WEB-INF/tld/ppd.tld" %>
var datatables;
$(function(){
	function initTable(){
		if($("#showData").is(":hidden")){
			$("#showData").show();
			$("#initPromptMsg").hide();
		}
		
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
			"sAjaxSource": $context.ctx + "/system/api/basics_query?status="+$("#e1").val()+"&searchCondition="+$("#searchCondition").val(),//如果从服务器端加载数据，这个属性用语指定加载的路径
			"aoColumns" : [ {
	            "bVisible" : true,
	            "bSortable": false,
	             "mData" : "company",
	            "fnRender": function (obj) {
	                var sReturn = obj.aData["id"];
	                var company = obj.aData["company"];
	                	 return '<button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="productDetails(this,\'' + sReturn + '\')"><font>'+company+'</font></button>';
	             }
	        },{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "email",
	            "aTargets" : [ 1 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "realName",
	            "aTargets" : [ 2 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "appKey",
	            "aTargets" : [ 4 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "status",
	            "aTargets" : [ 5 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "action",
	            "fnRender": function (obj) {
	                var sReturn = obj.aData["id"];
	                var company = obj.aData["company"];
	                var email = obj.aData["email"];
	                var realName = obj.aData["realName"];
	                var tel = obj.aData["tel"];
	                var isActive = obj.aData["status"];
	                var url = obj.aData["collectionSystemURL"];
	                if (isActive == 'S') {
	                	 return '<ppd:auth ctype="edit" menuid="31" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-freeze" style="margin-left:6px;" onclick="freeze(\'' + sReturn + '\')">冻结</button></ppd:auth>'+
	             		'<ppd:auth ctype="edit" menuid="32" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="resetPassword(\'' + sReturn + '\')"><font>重置密码</font></button></ppd:auth>'+
	             		'<ppd:auth ctype="edit" menuid="33" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="editFun(\'' + sReturn + '\',\'' + company + '\',\'' + email + '\',\'' + realName + '\',\'' + tel + '\',\'' + url  + '\')"><font>修改信息</font></button></ppd:auth>'+
	             		'<ppd:auth ctype="edit" menuid="34" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="productConfiguration(\'' + sReturn + '\')"><font>产品配置</font></button></ppd:auth>'+
	             		'<ppd:auth ctype="edit" menuid="62" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="editProductDetails(this,\'' + sReturn + '\')"><font>编辑</font></button></ppd:auth>'+
	                	 '<ppd:auth ctype="edit" menuid="35" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="showAppKey(\'' + sReturn + '\')"><font>查看密钥</font></button></ppd:auth>';
	                } else {
	                	return '<ppd:auth ctype="edit" menuid="31" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-unfreeze" style="margin-left:6px;" onclick="unfreeze(\'' + sReturn + '\')"><font color="red">解冻</font></button></ppd:auth>'+
	             		'<ppd:auth ctype="edit" menuid="32" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="resetPassword(\'' + sReturn + '\')"><font>重置密码</font></button></ppd:auth>'+
	             		'<ppd:auth ctype="edit" menuid="33" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="editFun(\'' + sReturn + '\',\'' + company + '\',\'' + email + '\',\'' + realName + '\',\'' + tel + '\',\'' + url  + '\')"><font>修改信息</font></button></ppd:auth>'+
	             		'<ppd:auth ctype="edit" menuid="34" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="productConfiguration(\'' + sReturn + '\')"><font>产品配置</font></button></ppd:auth>'+
	             		'<ppd:auth ctype="edit" menuid="62" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="editProductDetails(this,\'' + sReturn + '\')"><font>编辑</font></button></ppd:auth>'+
	                	'<ppd:auth ctype="edit" menuid="35" parentid=""><button value="'+sReturn+'" class="btn btn-xs btn-info btn-edit" style="margin-left:6px;" onclick="showAppKey(\'' + sReturn + '\')"><font>查看密钥</font></button></ppd:auth>';
	                }            
	             }
	        }, ],
	        "aoColumnDefs" : [ {
	            sDefaultContent : '',
	            aTargets : [ '_all' ]
	        } ],
	        "fnRowCallback": function(nRow, aData, iDisplayIndex) {
	        	if (aData.status == 'S') {
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
	initAdd();
	$("#searchCondition").autocomplete({
		minLength: 2,	
		source: function(request, response) {
		$.ajax({
			url: $context.ctx + "/system/api/basics_autocomplete",
			type : "post",
			data: {
				status: $("#e1").val(),
				searchCondition: $("#searchCondition").val()
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
			$("#searchCondition").val(ui.item.value);
			if(!datatables){
				initTable();
			}
			else{
				var oSettings = datatables.fnSettings();
				var status = $("#e1").val();
				var searchCondition = $("#searchCondition").val();
				oSettings.sAjaxSource = $context.ctx + "/system/api/basics_query?status="+status+"&searchCondition="+searchCondition;
				datatables.fnClearTable(0);
				datatables.fnDraw();
			}
		}
	});	
	
	$(".inputPositiveInteger").keyup(function(){     
	        var tmptxt=$(this).val();     
	       $(this).val(tmptxt.replace(/\D|^0\d+/g,''));     
    }).bind("paste",function(){     
        var tmptxt=$(this).val();     
        $(this).val(tmptxt.replace(/\D|^0\d+/g,''));
    }).css("ime-mode", "disabled");
    
	$("#search").click(function() {
		alert("123");
		if(!datatables){
			initTable();
		}
		else{
			var oSettings = datatables.fnSettings();
			var status = $("#e1").val();
			var searchCondition = $("#searchCondition").val();
			oSettings.sAjaxSource = $context.ctx + "/admin/manage/query?status="+status+"&searchCondition="+searchCondition;
			datatables.fnClearTable(0);
			datatables.fnDraw();
		}
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
		var status = $("#modalAddStatus").val();
		if("正常" == status){
			status = "S";
		}
		else{
			status = "F";
		}
		var userName = $("#modalAddUsername").val();
		var email = $("#modalAddEmail").val();
		var tel = $("#modalAddTel").val();
		var url = $("#modalAddURL").val();
		var companyName = $("#modalAddCompanyName").val();
		var modalAddInterfaceList = $(".modalAddInterface:checked");
		var interfaceIdListStr = "[";
		if(modalAddInterfaceList){
			for(var i=0; i < modalAddInterfaceList.length;i++ ) {
				if(i != 0){
					interfaceIdListStr = interfaceIdListStr + ",";
				}
				var interfaceIdTemp = $(modalAddInterfaceList[i]).val();
				var totalCountTmp = $("#modalAddTotalCount"+interfaceIdTemp).val();
				var dayCountTmp = $("#modalAddDayCount"+interfaceIdTemp).val();
				interfaceIdListStr = interfaceIdListStr + "{'interfaceId':'"+interfaceIdTemp+"','totalCount':'"+totalCountTmp+"','dayCount':'"+dayCountTmp+"'}";
			}
		}
		interfaceIdListStr = interfaceIdListStr + "]";
		$("#saveButton").html('处理中...');
		$("#saveButton").attr("disabled","disabled");
		var param = {
				status: status,
				userName: userName,
				email: email,
				tel: tel,
				url: url,
				companyName: companyName,
				interfaceIdListStr: interfaceIdListStr
			};
		$.post($context.ctx+"/system/api/add_user",param,function(data){
			$("#saveButton").html('确认');
			$("#saveButton").removeAttr("disabled");
			var obj = jQuery.parseJSON(data);
			if(obj.resp_code == "success"){
				$('#addUserModal').modal('hide');//关闭模态框       					
				window.location.href = $context.ctx + "/system/api/basics_manage";//跳转到列表页面
				jAlert("用户新增成功","提示");//弹出对话框      			
			} else {
				jAlert(obj.resp_msg,"提示");
			}
		});
    }); 
    
});

$("#modalAddCancel").click(function(){
	$("#saveButton").html('确认');
	$("#saveButton").removeAttr("disabled");
});

$("#productDetailsInput").click(function(){
	setModal8Info(false);
	$('#productDetailsModal').modal('hide');//关闭模态框       
	$("#updateDetailsModal").modal({backdrop: 'static', keyboard: false});//开启模态框
});
function addNewUser(){
	window.location.href = $context.ctx + "/system/api/to_add_user";
}

function initAdd(){
	var methodListData = $("#methodListData").html();
	var methodList = eval("("+methodListData+")");
	var mothodHtml = $("#addMthod").html("");
	var productId = "";
	var methodClassHtml = "";
	var l = 0;
	var r = 0;
	var leftright = true;
		for(var i=0; i < methodList.length;i++ ) {
			var productIdTemp = methodList[i].productId;
			if(productId != productIdTemp){
				if("" != productId){
					methodClassHtml = methodClassHtml + '</div></div>';
				}
				if(l > r){
				leftright = false;
			}
			else{
				leftright = true;
			}
			if(leftright){
				methodClassHtml = methodClassHtml + '<div class="addProductClass col-md-6 floatLeft">';
			}
			else{
				methodClassHtml = methodClassHtml + '<div class="addProductClass col-md-6 floatRight">';
			}
			methodClassHtml = methodClassHtml + '<div class="col-md-12 addProductName"><input type="checkbox" name="addProduct" class="addProduct" id="add'+methodList[i].productId+'" value="'+methodList[i].productId+'">&nbsp;<span>'+methodList[i].productName+'</span></div>';
			methodClassHtml = methodClassHtml +  '<div class="addMethodClass">';
			productId = productIdTemp;
			}
			if(leftright){
			l++;
		}
		else{
			r++;
		}
		
		methodClassHtml = methodClassHtml + '<div class="col-md-12">';
		methodClassHtml = methodClassHtml + '<div class="addInterfaceDiv col-md-6"><input type="checkbox" name="addInterface" class="addInterface" id="add'+methodList[i].id+'" value="'+methodList[i].id+'">&nbsp;<span>'+methodList[i].descName+'</span></div>';
		methodClassHtml = methodClassHtml + '<div class="addTotalCount col-md-3"><input type="text" class="form-control inputPositiveInteger" disabled="disabled" id="addTotalCount'+methodList[i].id+'" name="addTotalCount'+methodList[i].id+'"></div>';
		methodClassHtml = methodClassHtml + '<div class="addDayCount col-md-3"><input type="text" class="form-control inputPositiveInteger" disabled="disabled" id="addDayCount'+methodList[i].id+'" name="addDayCount'+methodList[i].id+'"></div>';
		methodClassHtml = methodClassHtml + '</div>';
		}
		
		methodClassHtml = methodClassHtml + '</div></div>';
		mothodHtml.append(methodClassHtml);

		if($("#addUnite").hasClass("addUniteOff")){
   			$("#addUnite").addClass("addUniteOn");
			$("#addUnite").removeClass("addUniteOff");
			$("#addUniteSet").show();
   		}
				
		$("#addCheckAll").click(function(){
   		 	var ischeck =  $(this).is(":checked");
   		 	if(!ischeck){
   		 		$(".addProduct").removeAttr("checked");
   		 		$(".addInterface").removeAttr("checked");
   		 		if($("#addUnite").hasClass("addUniteOff")){
   		 			$(".addTotalCount input").attr("disabled","disabled");
   		 			$(".addDayCount input").attr("disabled","disabled");
   		 		}
   		 	}
   		 	else{
   		 		$(".addProduct").prop("checked", true);
   		 		$(".addInterface").prop("checked", true);
   		 		if($("#addUnite").hasClass("addUniteOff")){
	   		 		$(".addTotalCount input").removeAttr("disabled");
	   		 		$(".addDayCount input").removeAttr("disabled");
	   		 	}
   		 	}
   		});
   		$(".addProduct").click(function(){
   			 var ischeck =  $(this).is(":checked");
      		if(!ischeck){
      			$(this).parent(".addProductName").siblings(".addMethodClass").find(".addInterface").removeAttr("checked");
      			if($("#addUnite").hasClass("addUniteOff")){
      				$(this).parent(".addProductName").siblings(".addMethodClass").find(".addTotalCount input").attr("disabled","disabled");
      				$(this).parent(".addProductName").siblings(".addMethodClass").find(".addDayCount input").attr("disabled","disabled");
      			}
      		}
      		else{
      			$(this).parent(".addProductName").siblings(".addMethodClass").find(".addInterface").prop("checked", true);
      			if($("#addUnite").hasClass("addUniteOff")){
      				$(this).parent(".addProductName").siblings(".addMethodClass").find(".addTotalCount input").removeAttr("disabled");
      				$(this).parent(".addProductName").siblings(".addMethodClass").find(".addDayCount input").removeAttr("disabled");
      			}
      			else{
      				$(this).parent(".addProductName").siblings(".addMethodClass").find(".addTotalCount input").val($("#addTotalCount").val());
      				$(this).parent(".addProductName").siblings(".addMethodClass").find(".addDayCount input").val($("#addDayCount").val());
      			}
      		}
   		});
   		$(".addInterface").click(function(){
   			 var selMethod = $(this).parents(".addMethodClass").find(".addInterface:checked").length;
   			  if(selMethod > 0){
	   		 	$(this).parents(".addMethodClass").siblings(".addProductName").children(".addProduct").prop("checked", true);
	   		 }
	   		 else{
	   		 	$(this).parents(".addMethodClass").siblings(".addProductName").children(".addProduct").removeAttr("checked");
	   		 }
			var ischeck =  $(this).is(":checked");
		 	if(ischeck){
		 		if($("#addUnite").hasClass("addUniteOff")){
			 		$(this).parent(".addInterfaceDiv").siblings(".addTotalCount").children("input").removeAttr("disabled");
					$(this).parent(".addInterfaceDiv").siblings(".addDayCount").children("input").removeAttr("disabled");
				}
				else{
					$(this).parent(".addInterfaceDiv").siblings(".addTotalCount").children("input").val($("#addTotalCount").val());
					$(this).parent(".addInterfaceDiv").siblings(".addDayCount").children("input").val($("#addDayCount").val());
				}
		 	}
		 	else{
		 		if($("#addUnite").hasClass("addUniteOff")){
			 		$(this).parent(".addInterfaceDiv").siblings(".addTotalCount").children("input").attr("disabled","disabled");
					$(this).parent(".addInterfaceDiv").siblings(".addDayCount").children("input").attr("disabled","disabled");
				}
		 	}
		});
	    
	    $(".addTotalCount input").keyup(function(){     
	        var tmptxt=$(this).val();     
	         $(this).val(tmptxt.replace(/\D|^0\d+/g,''));    
	         tmptxt=$(this).val(); 
	         var dayInput = $(this).parent(".addTotalCount").siblings(".addDayCount").children("input");
	        var dayTemp =  $(dayInput).val();
	        if(dayTemp && tmptxt){
	        	if(parseInt(tmptxt) < dayTemp){
	        		$(this).val($(dayInput).val());
	        		alert("总次数不能小于当日次数!");
	        	}
	        }
	    }).bind("paste",function(){     
	        var tmptxt=$(this).val();     
	        $(this).val(tmptxt.replace(/\D|^0\d+/g,''));  
	         tmptxt=$(this).val(); 
	         var dayInput = $(this).parent(".addTotalCount").siblings(".addDayCount").children("input");
	        var dayTemp =  $(dayInput).val();
	        if(dayTemp && tmptxt){
	        	if(parseInt(tmptxt) < parseInt(dayTemp)){
	        		$(this).val($(dayInput).val());
	        		alert("总次数不能小于单日次数!");
	        	}
	        }   
	    }).css("ime-mode", "disabled");
	    
	    $(".addDayCount input").keyup(function(){     
	        var tmptxt=$(this).val();     
	         $(this).val(tmptxt.replace(/\D|^0\d+/g,''));   
	          tmptxt=$(this).val(); 
	         var totalInput = $(this).parent(".addDayCount").siblings(".addTotalCount").children("input");
	        var totalTemp =  $(totalInput).val();
	        if(totalTemp && tmptxt){
	        	if(parseInt(tmptxt) > parseInt(totalTemp)){
	        		$(this).val($(totalInput).val());
	        		alert("单日次数不能大于总次数!");
	        	}
	        }    
	    }).bind("paste",function(){     
	        var tmptxt=$(this).val();     
	        $(this).val(tmptxt.replace(/\D|^0\d+/g,''));     
	            tmptxt=$(this).val(); 
	         var totalInput = $(this).parent(".addDayCount").siblings(".addTotalCount").children("input");
	        var totalTemp =  $(totalInput).val();
	        if(totalTemp && tmptxt){
	        	if(parseInt(tmptxt) > parseInt(totalTemp)){
	        		$(this).val($(totalInput).val());
	        		alert("单日次数不能大于总次数!");
	        	}
	        }    
	    }).css("ime-mode", "disabled");
	    
	    
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
    		for(var i=0; i < methodList.length;i++ ) {   
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

		var methodList;
		var conditionList;
		var keySet;
		var userInfo;
		var methodCountList;
    		
function productDetails(_this,userId){
	$(_this).parents("tr:first").addClass("focusTr");
	 Load('正在运行，请稍后...');
	$.post($context.ctx+"/system/api/loadProductDetails?id="+userId,function(data){
		var obj = jQuery.parseJSON(data);
		dispalyLoad();
    	if(obj.resp_code == "success"){
    		 methodList = obj.resp_body.methodList;
    		 conditionList = obj.resp_body.conditionList;
    		 methodCountList = obj.resp_body.methodCountList;
    		 keySet = obj.resp_body.keySet;
    		 if(keySet){
    		 	 userInfo = keySet.user;
    		 }
    		setModal7Info(userId);
    	};
	});
}

function editProductDetails(_this,userId){
	$(_this).parents("tr:first").addClass("focusTr");
	 Load('正在运行，请稍后...');
	$.post($context.ctx+"/system/api/loadProductDetails?id="+userId,function(data){
		var obj = jQuery.parseJSON(data);
		dispalyLoad();
    	if(obj.resp_code == "success"){
    		 methodList = obj.resp_body.methodList;
    		 conditionList = obj.resp_body.conditionList;
    		 methodCountList = obj.resp_body.methodCountList;
    		 keySet = obj.resp_body.keySet;
    		 if(keySet){
    		 	 userInfo = keySet.user;
    		 }
    		 setModal8Info(true);
			$("#updateDetailsModal").modal({backdrop: 'static', keyboard: false});//开启模态框
    	};
	});
}

function setModal7Info(userId){
		$("#modal7companyName").html(userInfo.company);
		$("#modal7UserId").val(userId);
  		if (userInfo.status == 'S') {
      		$("#modal7Status").html("正常");
      	} else{
      		$("#modal7Status").html("冻结");
      		$("#modal7Status").css("color","red")
      	}
  		$("#modal7Username").html(userInfo.username);
  		$("#modal7Email").html(userInfo.email);
  		$("#modal7Tel").html(userInfo.tel);
  		$("#modal7AppKey").html(keySet.appKey);
  		$("#modal7AppSecret").html(keySet.appSecret);
  		$("#modal7URL").html(userInfo.url);
  		var mothodHtml = $("#modal7ProductMothodBody").html("");
  		mothodHtml.append('<div class="col-md-12">已配置产品</div>');
  		var productId = "";
  		var methodClassHtml = "";
  		mothodHtml.append('<div class="col-md-6"><div class="col-md-6"></div><div class="col-md-3">总次数(已用/总计)</div><div class="col-md-3">今日次数(已用/总计)</div></div>');
  		mothodHtml.append('<div class="col-md-6"><div class="col-md-6"></div><div class="col-md-3">总次数(已用/总计)</div><div class="col-md-3">今日次数(已用/总计)</div></div>');
  		methodClassHtml = methodClassHtml + '<div id="modal7Mthod">';
  		var l = 0;
  		var r = 0;
  		var leftright = true;
  		for(var i=0; i < methodList.length;i++ ) {
  			var productIdTemp = methodList[i].productId;
  			if(productId != productIdTemp){
  				if("" != productId){
  					methodClassHtml = methodClassHtml + '</div></div>';
  				}
  				if(l > r){
  					leftright = false;
  				}
  				else{
  					leftright = true;
  				}
  				if(leftright){
  					methodClassHtml = methodClassHtml + '<div class="modal7ProductClass col-md-6 floatLeft">';
  				}
  				else{
  					methodClassHtml = methodClassHtml + '<div class="modal7ProductClass col-md-6 floatRight">';
  				}
  				methodClassHtml = methodClassHtml + '<div class="col-md-12 modal7ProductName"><input type="checkbox" name="modal7Product" disabled="disabled" class="modal7Product" id="modal7'+methodList[i].productId+'" value="'+methodList[i].productId+'">&nbsp;<span>'+methodList[i].productName+'</span></div>';
  				methodClassHtml = methodClassHtml +  '<div class="modal7MethodClass">';
  				productId = productIdTemp;
  			}
  			if(leftright){
  				l++;
  			}
  			else{
  				r++;
  			}
  			methodClassHtml = methodClassHtml + '<div class="col-md-12">';
  			methodClassHtml = methodClassHtml + '<div class="modal7InterfaceDiv col-md-6"><input type="checkbox" name="modal7Interface" class="modal7Interface" disabled="disabled" id="modal7'+methodList[i].id+'" value="'+methodList[i].id+'">&nbsp;<span>'+methodList[i].descName+'</span></div>';
  			methodClassHtml = methodClassHtml + '<div class="modal7TotalCount col-md-3"><span id="modal7TotalCount'+methodList[i].id+'"></span></div>';
  			methodClassHtml = methodClassHtml + '<div class="modal7DayCount col-md-3"><span id="modal7DayCount'+methodList[i].id+'"></span></div>';
  			methodClassHtml = methodClassHtml + '</div>';
  	    }
  	    methodClassHtml = methodClassHtml + '</div></div></div>';
  	    mothodHtml.append(methodClassHtml);
  	    for(var i=0; i < conditionList.length;i++ ) {
  	    	$('#modal7'+conditionList[i].id+'').prop("checked", true);
  	    }
  	    for(var i=0; i < methodCountList.length;i++ ) {
  	    	var myMethod = methodCountList[i].myMethod;
  	    	if(methodCountList[i].totalUseNums && methodCountList[i].totalNums){
  	    		$('#modal7TotalCount'+myMethod.id+'').html(methodCountList[i].totalUseNums+"/"+methodCountList[i].totalNums);
  	    		if(parseInt(methodCountList[i].totalUseNums) >= parseInt(methodCountList[i].totalNums)){
	    			$('#modal7TotalCount'+myMethod.id+'').addClass("redColor");
	    		}
  	    	}
  	    	var totalUseNums = "";
	    	if(!methodCountList[i].totalUseNums){
	    		totalUseNums = "0";
	    	}
	    	else{
	    		totalUseNums = methodCountList[i].totalUseNums;
	    	}
	    	
	    	var totalNums = "";
	    	if(!methodCountList[i].totalNums){
	    		totalNums = "不限";
	    	}
	    	else{
	    		totalNums = methodCountList[i].totalNums;
	    	}
	    	$('#modal7TotalCount'+myMethod.id+'').html(totalUseNums+"/"+totalNums);
	    	
	    	if(methodCountList[i].realUseNums && methodCountList[i].realNums){
  	    		$('#modal7DayCount'+myMethod.id+'').html(methodCountList[i].realUseNums+"/"+methodCountList[i].realNums);
  	    		if(parseInt(methodCountList[i].realUseNums) >= parseInt(methodCountList[i].realNums)){
	    			$('#modal7DayCount'+myMethod.id+'').addClass("redColor");
	    		}
  	    	}
  	    	var realUseNums = "";
	    	if(!methodCountList[i].realUseNums){
	    		realUseNums = "0";
	    	}
	    	else{
	    		realUseNums = methodCountList[i].realUseNums;
	    	}
	    	
	    	var realNums = "";
	    	if(!methodCountList[i].realNums){
	    		realNums = "不限";
	    	}
	    	else{
	    		realNums = methodCountList[i].realNums;
	    	}
	    	$('#modal7DayCount'+myMethod.id+'').html(realUseNums+"/"+realNums);
  	    }
  	    
  	    var thisMethodClass = $(".modal7MethodClass");
   		for(var i=0; i < thisMethodClass.length;i++ ){
	   		 var selMethod = $(thisMethodClass[i]).find(".modal7Interface:checked").length;
	   		 if(selMethod > 0){
	   		 	$(thisMethodClass[i]).siblings(".modal7ProductName").children(".modal7Product").prop("checked", true);
	   		 }
   		}
  		$("#productDetailsModal").modal({backdrop: 'static', keyboard: false});//开启模态框
}

$("#modal8Return").click(function(){
	$("#updateDetailsModal").modal("hide");
	$("#productDetailsModal").modal({backdrop: 'static', keyboard: false});//开启模态框
});
function setModal8Info(lookEdit){
	if(lookEdit){
		$("#modal8Return").hide();
		$("#modal8Close").show();
	}
	else{
		$("#modal8Return").show();
		$("#modal8Close").hide();
	}
		document.getElementById("modal8Form").reset(); 
		initUpdateDetailsModal();
		$("#modal8companyName").val(userInfo.company);
		$("#modal8UserId").val(userInfo.id);
   		if (userInfo.status == 'S') {
   			 $("#e3").find("option[value='S']").attr("selected", "selected");  
   			$("#s2id_e3 .select2-chosen").first().text("正常");
       	} else{
       		  $("#e3").find("option[value='F']").attr("selected", "selected");  
       		$("#s2id_e3 .select2-chosen").first().text("冻结");
       	}
   		$("#modal8Username").val(userInfo.username);
   		$("#modal8Email").val(userInfo.email);
   		$("#modal8Tel").val(userInfo.tel);
   		$("#modal8AppKey").html(keySet.appKey);
   		$("#modal8AppSecret").html(keySet.appSecret);
   		$("#modal8URL").val(userInfo.url);
   		var mothodHtml = $("#modal8ProductMothod").html("");
   		var productId = "";
   		var methodClassHtml = "";
   		mothodHtml.append('<div class="col-md-6 fontWeight"><div class="col-md-6"><input type="checkbox" name="modal8CheckAll"  id="modal8CheckAll">&nbsp;全选</div><div class="col-md-3">总次数</div><div class="col-md-3">单日最高</div></div>');
  		mothodHtml.append('<div class="col-md-6 fontWeight"><div class="col-md-6"></div><div class="col-md-3">总次数</div><div class="col-md-3">单日最高</div></div>');
   		methodClassHtml = methodClassHtml + '<div id="modal8Mthod">';
   		var l = 0;
  		var r = 0;
  		var leftright = true;
   		for(var i=0; i < methodList.length;i++ ) {
   			var productIdTemp = methodList[i].productId;
   			if(productId != productIdTemp){
   				if("" != productId){
   					methodClassHtml = methodClassHtml + '</div></div>';
   				}
   				if(l > r){
  					leftright = false;
  				}
  				else{
  					leftright = true;
  				}
  				if(leftright){
  					methodClassHtml = methodClassHtml + '<div class="modal8ProductClass col-md-6 floatLeft">';
  				}
  				else{
  					methodClassHtml = methodClassHtml + '<div class="modal8ProductClass col-md-6 floatRight">';
  				}
  				methodClassHtml = methodClassHtml + '<div class="col-md-12 modal8ProductName"><input type="checkbox" name="modal8Product" class="modal8Product" id="modal8'+methodList[i].productId+'" value="'+methodList[i].productId+'">&nbsp;<span>'+methodList[i].productName+'</span></div>';
  				methodClassHtml = methodClassHtml +  '<div class="modal8MethodClass">';
  				productId = productIdTemp;
   			}
   			if(leftright){
  				l++;
  			}
  			else{
  				r++;
  			}
  			
  			methodClassHtml = methodClassHtml + '<div class="col-md-12">';
  			methodClassHtml = methodClassHtml + '<div class="modal8InterfaceDiv col-md-6"><input type="checkbox" name="modal8Interface" class="modal8Interface" id="modal8'+methodList[i].id+'" value="'+methodList[i].id+'">&nbsp;<span>'+methodList[i].descName+'</span></div>';
  			methodClassHtml = methodClassHtml + '<div class="modal8TotalCount col-md-3"><input type="text" class="form-control inputPositiveInteger" disabled="disabled" id="modal8TotalCount'+methodList[i].id+'" name="modal8TotalCount'+methodList[i].id+'"></div>';
  			methodClassHtml = methodClassHtml + '<div class="modal8DayCount col-md-3"><input type="text" class="form-control inputPositiveInteger" disabled="disabled" id="modal8DayCount'+methodList[i].id+'" name="modal8DayCount'+methodList[i].id+'"></div>';
  			methodClassHtml = methodClassHtml + '</div>';
   		}
   		
   		if($("#modal8Unite").hasClass("modal8UniteOff")){
   			$("#modal8Unite").addClass("modal8UniteOn");
			$("#modal8Unite").removeClass("modal8UniteOff");
			$("#modal8UniteSet").show();
   		}
   		
   		methodClassHtml = methodClassHtml + '</div></div></div>';
   		mothodHtml.append(methodClassHtml);
   		for(var i=0; i < conditionList.length;i++ ) {
   			$('#modal8'+conditionList[i].id+'').prop("checked", true);
   		}
   		 for(var i=0; i < methodCountList.length;i++ ) {
  	    	var myMethod = methodCountList[i].myMethod;
  	    	if(methodCountList[i].totalNums){
  	    		$('#modal8TotalCount'+myMethod.id+'').val(methodCountList[i].totalNums);
  	    	}
  	    	if(methodCountList[i].realNums){
  	    		$('#modal8DayCount'+myMethod.id+'').val(methodCountList[i].realNums);
  	    	}
  	    }
   		var thisMethodClass = $(".modal8MethodClass");
   		for(var i=0; i < thisMethodClass.length;i++ ){
	   		 var selMethod = $(thisMethodClass[i]).find(".modal8Interface:checked").length;
	   		 if(selMethod > 0){
	   		 	$(thisMethodClass[i]).siblings(".modal8ProductName").children(".modal8Product").prop("checked", true);
	   		 }
   		}
   		$(".modal8TotalCount input").keyup(function(){     
	        var tmptxt=$(this).val();     
	         $(this).val(tmptxt.replace(/\D|^0\d+/g,''));    
	         tmptxt=$(this).val(); 
	         var dayInput = $(this).parent(".modal8TotalCount").siblings(".modal8DayCount").children("input");
	        var dayTemp =  $(dayInput).val();
	        if(dayTemp && tmptxt){
	        	if(parseInt(tmptxt) < dayTemp){
	        		$(this).val($(dayInput).val());
	        		alert("总次数不能小于当日次数!");
	        	}
	        }
	    }).bind("paste",function(){     
	        var tmptxt=$(this).val();     
	        $(this).val(tmptxt.replace(/\D|^0\d+/g,''));  
	         tmptxt=$(this).val(); 
	         var dayInput = $(this).parent(".modal8TotalCount").siblings(".modal8DayCount").children("input");
	        var dayTemp =  $(dayInput).val();
	        if(dayTemp && tmptxt){
	        	if(parseInt(tmptxt) < parseInt(dayTemp)){
	        		$(this).val($(dayInput).val());
	        		alert("总次数不能小于单日次数!");
	        	}
	        }   
	    }).css("ime-mode", "disabled");
	    
	    $(".modal8DayCount input").keyup(function(){     
	        var tmptxt=$(this).val();     
	         $(this).val(tmptxt.replace(/\D|^0\d+/g,''));   
	          tmptxt=$(this).val(); 
	         var totalInput = $(this).parent(".modal8DayCount").siblings(".modal8TotalCount").children("input");
	        var totalTemp =  $(totalInput).val();
	        if(totalTemp && tmptxt){
	        	if(parseInt(tmptxt) > parseInt(totalTemp)){
	        		$(this).val($(totalInput).val());
	        		alert("单日次数不能大于总次数!");
	        	}
	        }    
	    }).bind("paste",function(){     
	        var tmptxt=$(this).val();     
	        $(this).val(tmptxt.replace(/\D|^0\d+/g,''));     
	            tmptxt=$(this).val(); 
	         var totalInput = $(this).parent(".modal8DayCount").siblings(".modal8TotalCount").children("input");
	        var totalTemp =  $(totalInput).val();
	        if(totalTemp && tmptxt){
	        	if(parseInt(tmptxt) > parseInt(totalTemp)){
	        		$(this).val($(totalInput).val());
	        		alert("单日次数不能大于总次数!");
	        	}
	        }    
	    }).css("ime-mode", "disabled");
	    
	    
   		$(".modal8Product").click(function(){
   			 var ischeck =  $(this).is(":checked");
      		if(!ischeck){
      			$(this).parent(".modal8ProductName").siblings(".modal8MethodClass").find(".modal8Interface").removeAttr("checked");
      			if($("#modal8Unite").hasClass("modal8UniteOff")){
      				$(this).parent(".modal8ProductName").siblings(".modal8MethodClass").find(".modal8TotalCount input").attr("disabled","disabled");
      				$(this).parent(".modal8ProductName").siblings(".modal8MethodClass").find(".modal8DayCount input").attr("disabled","disabled");
      			}
      		}
      		else{
      			$(this).parent(".modal8ProductName").siblings(".modal8MethodClass").find(".modal8Interface").prop("checked", true);
      			if($("#modal8Unite").hasClass("modal8UniteOff")){
      				$(this).parent(".modal8ProductName").siblings(".modal8MethodClass").find(".modal8TotalCount input").removeAttr("disabled");
      				$(this).parent(".modal8ProductName").siblings(".modal8MethodClass").find(".modal8DayCount input").removeAttr("disabled");
      			}
      			else{
      				$(this).parent(".modal8ProductName").siblings(".modal8MethodClass").find(".modal8TotalCount input").val($("#modal8TotalCount").val());
      				$(this).parent(".modal8ProductName").siblings(".modal8MethodClass").find(".modal8DayCount input").val($("#modal8DayCount").val());
      			}
      		}
   		});
   		$(".modal8Interface").click(function(){
   			 var selMethod = $(this).parents(".modal8MethodClass").find(".modal8Interface:checked").length;
	   		 if(selMethod > 0){
	   		 	$(this).parents(".modal8MethodClass").siblings(".modal8ProductName").children(".modal8Product").prop("checked", true);
	   		 }
	   		 else{
	   		 	$(this).parents(".modal8MethodClass").siblings(".modal8ProductName").children(".modal8Product").removeAttr("checked");
	   		 }
	   		 var ischeck =  $(this).is(":checked");
		 	if(ischeck){
		 		if($("#modal8Unite").hasClass("modal8UniteOff")){
			 		$(this).parent(".modal8InterfaceDiv").siblings(".modal8TotalCount").children("input").removeAttr("disabled");
					$(this).parent(".modal8InterfaceDiv").siblings(".modal8DayCount").children("input").removeAttr("disabled");
				}
				else{
					$(this).parent(".modal8InterfaceDiv").siblings(".modal8TotalCount").children("input").val($("#modal8TotalCount").val());
					$(this).parent(".modal8InterfaceDiv").siblings(".modal8DayCount").children("input").val($("#modal8DayCount").val());
				}
		 	}
		 	else{
		 		if($("#modal8Unite").hasClass("modal8UniteOff")){
			 		$(this).parent(".modal8InterfaceDiv").siblings(".modal8TotalCount").children("input").attr("disabled","disabled");
					$(this).parent(".modal8InterfaceDiv").siblings(".modal8DayCount").children("input").attr("disabled","disabled");
				}
		 	}
   		});
   		$("#modal8CheckAll").click(function(){
   		 	var ischeck =  $(this).is(":checked");
   		 	if(!ischeck){
   		 		$(".modal8Product").removeAttr("checked");
   		 		$(".modal8Interface").removeAttr("checked");
   		 		if($("#modal8Unite").hasClass("modal8UniteOff")){
   		 			$(".modal8TotalCount input").attr("disabled","disabled");
   		 			$(".modal8DayCount input").attr("disabled","disabled");
   		 		}
   		 	}
   		 	else{
   		 		$(".modal8Product").prop("checked", true);
   		 		$(".modal8Interface").prop("checked", true);
   		 		if($("#modal8Unite").hasClass("modal8UniteOff")){
	   		 		$(".modal8TotalCount input").removeAttr("disabled");
	   		 		$(".modal8DayCount input").removeAttr("disabled");
	   		 	}
   		 	}
   		});
   		
   		modal8FormWizard.init();
}

$("#modal8Unite").click(function(){
	if($(this).hasClass("modal8UniteOn")){
		$(this).removeClass("modal8UniteOn");
		$(this).addClass("modal8UniteOff");
		$("#modal8UniteSet").hide();
		$(".modal8Interface:checked").parent(".modal8InterfaceDiv").siblings(".modal8TotalCount").children("input").removeAttr("disabled");
		$(".modal8Interface:checked").parent(".modal8InterfaceDiv").siblings(".modal8DayCount").children("input").removeAttr("disabled");
	}
	else{
		$(this).addClass("modal8UniteOn");
		$(this).removeClass("modal8UniteOff");
		$("#modal8UniteSet").show();
		$(".modal8TotalCount input").attr("disabled","disabled");
		$(".modal8DayCount input").attr("disabled","disabled");
	}
});
$("#addUnite").click(function(){
	if($(this).hasClass("addUniteOn")){
		$(this).removeClass("addUniteOn");
		$(this).addClass("addUniteOff");
		$("#addUniteSet").hide();
		$(".addInterface:checked").parent(".addInterfaceDiv").siblings(".addTotalCount").children("input").removeAttr("disabled");
		$(".addInterface:checked").parent(".addInterfaceDiv").siblings(".addDayCount").children("input").removeAttr("disabled");
	}
	else{
		$(this).addClass("addUniteOn");
		$(this).removeClass("addUniteOff");
		$("#addUniteSet").show();
		$(".addTotalCount input").attr("disabled","disabled");
		$(".addDayCount input").attr("disabled","disabled");
	}
});


$("#modal8TotalCount").keyup(function(){
	var modal8TotalCount = $("#modal8TotalCount").val();
	var modal8DayCount = $("#modal8DayCount").val();
	if(modal8TotalCount && modal8DayCount){
		if(parseInt(modal8TotalCount) < parseInt(modal8DayCount)){
			$("#modal8TotalCount").val(modal8DayCount);
			alert("总次数不能小于单日次数!");
		}
	}
	$(".modal8Interface:checked").parent(".modal8InterfaceDiv").siblings(".modal8TotalCount").children("input").val($("#modal8TotalCount").val());
});
$("#modal8DayCount").keyup(function(){
	var modal8TotalCount = $("#modal8TotalCount").val();
	var modal8DayCount = $("#modal8DayCount").val();
	if(modal8TotalCount && modal8DayCount){
		if(parseInt(modal8TotalCount) < parseInt(modal8DayCount)){
			$("#modal8DayCount").val(modal8TotalCount);
			alert("单日次数不能大于总次数!");
		}
	}
	$(".modal8Interface:checked").parent(".modal8InterfaceDiv").siblings(".modal8DayCount").children("input").val($("#modal8DayCount").val());
});

$("#addTotalCount").keyup(function(){
	var addTotalCount = $("#addTotalCount").val();
	var addDayCount = $("#addDayCount").val();
	if(addTotalCount && addDayCount){
		if(parseInt(addTotalCount) < parseInt(addDayCount)){
			$("#addTotalCount").val(addDayCount);
			alert("总次数不能小于单日次数!");
		}
	}
	$(".addInterface:checked").parent(".addInterfaceDiv").siblings(".addTotalCount").children("input").val($("#addTotalCount").val());
});
$("#addDayCount").keyup(function(){
	var addTotalCount = $("#addTotalCount").val();
	var addDayCount = $("#addDayCount").val();
	if(addTotalCount && addDayCount){
		if(parseInt(addTotalCount) < parseInt(addDayCount)){
			$("#addDayCount").val(addTotalCount);
			alert("单日次数不能大于总次数!");
		}
	}
	$(".addInterface:checked").parent(".addInterfaceDiv").siblings(".addDayCount").children("input").val($("#addDayCount").val());
});


function initUpdateDetailsModal()
{
	
	$("#modal8companyName").closest('.form-group').removeClass('has-error');
	$("#modal8companyName").closest('.form-group').removeClass('has-success');
	$("span[for='modal8companyName']").addClass('valid').html("");
	$("#modal8Username").closest('.form-group').removeClass('has-error');
	$("#modal8Username").closest('.form-group').removeClass('has-success');
	$("span[for='modal8Username']").addClass('valid').html(""); 
	$("#modal8Email").closest('.form-group').removeClass('has-error');
	$("#modal8Email").closest('.form-group').removeClass('has-success');
	$("span[for='modal8Email']").addClass('valid').html(""); 
	$("#modal8Tel").closest('.form-group').removeClass('has-error');
	$("#modal8Tel").closest('.form-group').removeClass('has-success');
	$("span[for='modal8Tel']").addClass('valid').html(""); 
	$("#modal8URL").closest('.form-group').removeClass('has-error');
	$("#modal8URL").closest('.form-group').removeClass('has-success');
	$("span[for='modal8URL']").addClass('valid').html("");
}
//点击提交，跳到确认页面
/*
$("#productDetailsSubmit").click(function(){
	modal8FormWizard.init();
});
*/

$("#modal9Return").click(function(){
	$("#confirmDetailsModal").modal("hide");
  	$("#updateDetailsModal").modal({backdrop: 'static', keyboard: false});//开启模态框
});

$("#productDetailsConfirm").click(function(){
	var userId = $("#modal9UserId").val();
	var status = $("#modal9Status").val();
	if("正常" == status){
		status = "S";
	}
	else{
		status = "F";
	}
	var userName = $("#modal9Username").val();
	var email = $("#modal9Email").val();
	var tel = $("#modal9Tel").val();
	var url = $("#modal9URL").val();
	var companyName = $("#modal9companyName").val();
	var modal9InterfaceList = $(".modal9Interface:checked");
	var interfaceIdListStr = "[";
	if(modal9InterfaceList){
		for(var i=0; i < modal9InterfaceList.length;i++ ) {
			if(i != 0){
				interfaceIdListStr = interfaceIdListStr + ",";
			}
			var interfaceIdTemp = $(modal9InterfaceList[i]).val();
			var totalCountTmp = $("#modal9TotalCount"+interfaceIdTemp).val();
			var dayCountTmp = $("#modal9DayCount"+interfaceIdTemp).val();
			interfaceIdListStr = interfaceIdListStr + "{'interfaceId':'"+interfaceIdTemp+"','totalCount':'"+totalCountTmp+"','dayCount':'"+dayCountTmp+"'}";
		}
	}
	interfaceIdListStr = interfaceIdListStr + "]";
	$("#productDetailsConfirm").html('处理中...');
	$("#productDetailsConfirm").attr("disabled","disabled");
	var param = {
			userId: userId,
			status: status,
			userName: userName,
			email: email,
			tel: tel,
			url: url,
			companyName: companyName,
			interfaceIdListStr: interfaceIdListStr
		};
	$.post($context.ctx+"/system/api/updateUserInfo",param,function(data){
		$("#productDetailsConfirm").html('确认');
		$("#productDetailsConfirm").removeAttr("disabled");
		var obj = jQuery.parseJSON(data);
		if(obj.resp_code == "success"){
			$('#confirmDetailsModal').modal('hide');//关闭模态框   
			$("tr").removeClass("focusTr");
				var oSettings = datatables.fnSettings();
				var status = $("#e1").val();
				var searchCondition = $("#searchCondition").val();
				oSettings.sAjaxSource = $context.ctx + "/system/api/basics_query?status="+status+"&searchCondition="+searchCondition;
				datatables.fnClearTable(0);
				datatables.fnDraw();
				jAlert("修改用户信息成功","提示");
		} else {
			jAlert(obj.resp_msg,"提示");
		}
	});
});
/*
$("#productDetailsModal,#updateDetailsModal,#confirmDetailsModal").on('hide.bs.modal', function () {
  $("tr").removeClass("focusTr");
});
*/
$(".close").click(function(){
	$("tr").removeClass("focusTr");
});
$(".closeModal").click(function(){
	$("tr").removeClass("focusTr");
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

//继续修改
function continueUpdateBtn(){
	var userId = $("#userId").val();
	var checkboxIds = $("#checkboxIds").val();
	var checkboxId = checkboxIds.split(",");
	console.info(checkboxId.length);
	$.post($context.ctx+"/system/api/loadProductConfiguration?id="+userId,function(data){
		var obj = jQuery.parseJSON(data);
    	if(obj.resp_code == "success"){
    		var methodList = obj.resp_body.methodList;
    		var strHtml = $("#productConfigurationBody").html("");
    		for(var i=0; i < methodList.length;i++ ) {   
    			strHtml.append('<div class="col-md-4"><input type="checkbox" name="checkboxMethodProduct" value="'+methodList[i].id+'"> <span>'+methodList[i].descName+'</span></div>');
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
    		$("#productConfigurationModal").modal({backdrop: 'static', keyboard: false});//开启模态框
    	};
	});
}
//保存
function checkboxBtnSave(){
	var userId = $("#userId").val();
	var checkboxIds = $("#checkboxIds").val(); 
	$("#checkboxBtnSave").button('loading');
	$.post($context.ctx+"/system/api/changeProductConfiguration",{userId: userId, ids: checkboxIds},function(data){
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
                onfocusout:function(element){
                	var validS = $(element).valid();
                	if(validS){
                		var elementId = $(element).attr('id');
                		var elementValue = $(element).val();
                		if("company" != elementId && "email" != elementId && "tel" != elementId && "url" != elementId){
                			return;
                		}
                		var obj={
                			elementId:elementId,
                			elementValue:elementValue,
                			userId:""
                		}
                		$.post($context.ctx+"/system/api/validateUserInfo",obj,function(data){
							var obj = jQuery.parseJSON(data);
							if(obj.resp_code == "success"){
								
							} else {
								var _this = $("#"+elementId);
								var errorMsg = obj.resp_msg;
								$(_this).parents(".form-group").removeClass("has-success");
								$(_this).parents(".form-group").addClass("has-error");
								var errorSpan = $(_this).next(".error-span");
								if(errorSpan){
									$(errorSpan).removeClass("valid");
									$(errorSpan).html(errorMsg);
								}
								else{
									$(_this).parent().append('<span for="'+elementId+'" class="error-span">'+errorMsg+'</span>');
								}
							}
						});
                	}
                },
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
                	//$("#addUserModal").modal("show");
                	/*
                	var company = $("#company").val();
                	var email = $("#email").val();
                	var username = $("#username").val();
                	var tel = $("#tel").val();
                	var status = $("#e4").val();
			  		if (status == 'S') {
			      		$("#modalAddStatus").val("正常");
			      	} else{
			      		$("#modalAddStatus").val("冻结");
			      		$("#modalAddStatus").css("color","red")
			      	}
                	var collectionSystemURL = $("#url").val();
                	$("#modalAddCompanyName").val(company);
                	$("#modalAddUsername").val(username);
                	$("#modalAddEmail").val(email);
                	$("#modalAddTel").val(tel);
                	$("#modalAddURL").val(collectionSystemURL);
                	var mothodHtml = $("#modalAddProductMothodBody").html("");
				  		mothodHtml.append('<div class="col-md-12">已配置产品</div>');
				  		var productId = "";
				  		var methodClassHtml = "";
				  		mothodHtml.append('<div class="col-md-6"><div class="col-md-6"></div><div class="col-md-3">总次数(已用/总计)</div><div class="col-md-3">今日次数(已用/总计)</div></div>');
  						mothodHtml.append('<div class="col-md-6"><div class="col-md-6"></div><div class="col-md-3">总次数(已用/总计)</div><div class="col-md-3">今日次数(已用/总计)</div></div>');
				  		methodClassHtml = methodClassHtml + '<div id="modalAddMethod">';
				  		var l = 0;
  						var r = 0;
  						var leftright = true;
  						var methodListData = $("#methodListData").html();
						var methodList = eval("("+methodListData+")");
  						for(var i=0; i < methodList.length;i++ ) {
				  			var productIdTemp = methodList[i].productId;
				  			if(productId != productIdTemp){
				  				if("" != productId){
				  					methodClassHtml = methodClassHtml + '</div></div>';
				  				}
				  				if(l > r){
				  					leftright = false;
				  				}
				  				else{
				  					leftright = true;
				  				}
				  				if(leftright){
				  					methodClassHtml = methodClassHtml + '<div class="modalAddProductClass col-md-6 floatLeft">';
				  				}
				  				else{
				  					methodClassHtml = methodClassHtml + '<div class="modalAddProductClass col-md-6 floatRight">';
				  				}
				  				methodClassHtml = methodClassHtml + '<div class="col-md-12 modalAddProductName"><input type="checkbox" name="modalAddProduct" disabled="disabled" class="modalAddProduct" id="modalAdd'+methodList[i].productId+'" value="'+methodList[i].productId+'">&nbsp;<span>'+methodList[i].productName+'</span></div>';
				  				methodClassHtml = methodClassHtml +  '<div class="modalAddMethodClass">';
				  				productId = productIdTemp;
				  			}
				  			if(leftright){
				  				l++;
				  			}
				  			else{
				  				r++;
				  			}
				  			methodClassHtml = methodClassHtml + '<div class="col-md-12">';
				  			methodClassHtml = methodClassHtml + '<div class="modalAddInterfaceDiv col-md-6"><input type="checkbox" name="modalAddInterface" class="modalAddInterface" disabled="disabled" id="modalAdd'+methodList[i].id+'" value="'+methodList[i].id+'">&nbsp;<span>'+methodList[i].descName+'</span></div>';
				  			methodClassHtml = methodClassHtml + '<div class="modalAddTotalCount col-md-3"><input type="text" class="form-control inputPositiveInteger" disabled="disabled" id="modalAddTotalCount'+methodList[i].id+'" name="modalAddTotalCount'+methodList[i].id+'"></div>';
				  			methodClassHtml = methodClassHtml + '<div class="modalAddDayCount col-md-3"><input type="text" class="form-control inputPositiveInteger" disabled="disabled" id="modalAddDayCount'+methodList[i].id+'" name="modalAddDayCount'+methodList[i].id+'"></div>';
				  			methodClassHtml = methodClassHtml + '</div>';
				  	    }
				  	    methodClassHtml = methodClassHtml + '</div></div></div>';
  	    				mothodHtml.append(methodClassHtml);
  	    				var addInterfaceList = $("#addMthod").find(".addInterface:checked");
  	    				if(addInterfaceList && addInterfaceList.length > 0){
  	    					for(var i=0; i < addInterfaceList.length;i++ ) {
  	    						var interfaceIdTemp = $(addInterfaceList[i]).val();
  	    						$("#modalAdd"+interfaceIdTemp).prop("checked", true);
  	    						$("#modalAddTotalCount"+interfaceIdTemp).val($("#addTotalCount"+interfaceIdTemp).val());
  	    						$("#modalAddDayCount"+interfaceIdTemp).val($("#addDayCount"+interfaceIdTemp).val());
  	    					}
  	    				}
  	    				
  	    				var addProductList = $("#addMthod").find(".addProduct:checked");
  	    				if(addProductList && addProductList.length > 0){
  	    					for(var i=0; i < addProductList.length;i++ ) {
  	    						var productIdTemp = $(addProductList[i]).val();
  	    						$("#modalAdd"+productIdTemp).prop("checked", true);
  	    					}
  	    				}
  	    				$(".inputPositiveInteger").keyup(function(){     
					        var tmptxt=$(this).val();     
					         $(this).val(tmptxt.replace(/\D|^0\d+/g,''));     
					    }).bind("paste",function(){     
					        var tmptxt=$(this).val();     
					        $(this).val(tmptxt.replace(/\D|^0\d+/g,''));          
					    }).css("ime-mode", "disabled");
				  		$("#addUserModal").modal({backdrop: 'static', keyboard: false});//开启模态框
				  		*/
				  		
				  		var status = $("#e4").val();
						var userName = $("#username").val();
						var email = $("#email").val();
						var tel = $("#tel").val();
						var url = $("#url").val();
						var companyName = $("#company").val();
						var modalAddInterfaceList = $(".addInterface:checked");
						var interfaceIdListStr = "[";
						if(modalAddInterfaceList){
							for(var i=0; i < modalAddInterfaceList.length;i++ ) {
								if(i != 0){
									interfaceIdListStr = interfaceIdListStr + ",";
								}
								var interfaceIdTemp = $(modalAddInterfaceList[i]).val();
								var totalCountTmp = $("#addTotalCount"+interfaceIdTemp).val();
								var dayCountTmp = $("#addDayCount"+interfaceIdTemp).val();
								interfaceIdListStr = interfaceIdListStr + "{'interfaceId':'"+interfaceIdTemp+"','totalCount':'"+totalCountTmp+"','dayCount':'"+dayCountTmp+"'}";
							}
						}
						interfaceIdListStr = interfaceIdListStr + "]";
						$("#sub").html('处理中...');
						$("#sub").attr("disabled","disabled");
						var param = {
								status: status,
								userName: userName,
								email: email,
								tel: tel,
								url: url,
								companyName: companyName,
								interfaceIdListStr: interfaceIdListStr
							};
						$.post($context.ctx+"/system/api/add_user",param,function(data){
							$("#sub").html('保存');
							$("#sub").removeAttr("disabled");
							var obj = jQuery.parseJSON(data);
							if(obj.resp_code == "success"){
								$('#addUserModal').modal('hide');//关闭模态框       					
								window.location.href = $context.ctx + "/system/api/basics_manage";//跳转到列表页面
								jAlert("用户新增成功","提示");//弹出对话框      			
							} else {
								jAlert(obj.resp_msg,"提示");
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
var modal8FormWizard = function () {
    return {
        init: function () {
            var wizform = $('#modal8Form');
           	 wizform.validate({
                doNotHideMessage: true,
                onfocusout:function(element){
                	var validS = $(element).valid();
                	var userId = $("#modal8UserId").val();
                	if(validS){
                		var elementId = $(element).attr('id');
                		var elementIds = elementId;
                		if("modal8companyName" == elementId){
                			elementId = "company";
                		}
                		else if("modal8Email" == elementId){
                			elementId = "email";
                		}
                		else if("modal8Tel" == elementId){
                			elementId = "tel";
                		}
                		else if("modal8URL" == elementId){
                			elementId = "url";
                		}
                		else{
                			return;
                		}
                		var elementValue = $(element).val();
                		var obj={
                			elementId:elementId,
                			elementValue:elementValue,
                			userId:userId
                		}
                		$.post($context.ctx+"/system/api/validateUserInfo",obj,function(data){
							var obj = jQuery.parseJSON(data);
							if(obj.resp_code == "success"){
								
							} else {
								var _this = $("#"+elementIds);
								var errorMsg = obj.resp_msg;
								$(_this).parents(".form-group").removeClass("has-success");
								$(_this).parents(".form-group").addClass("has-error");
								var errorSpan = $(_this).next(".error-span");
								if(errorSpan){
									$(errorSpan).removeClass("valid");
									$(errorSpan).html(errorMsg);
								}
								else{
									$(_this).parent().append('<span for="'+elementIds+'" class="error-span">'+errorMsg+'</span>');
								}
							}
						});
                	}
                },
				errorClass: 'error-span',
                errorElement: 'span',
                rules: {
                    /* Create Account */
                	modal8companyName: {
                    	required: true
                    },
                	modal8Email: {
                        required: true,
                        email: true
                    },
                    modal8Username: {
                    	required: true,
                    	maxlength: 5
                    },
                    modal8Tel: {
                    	required: true,
                    	isBegin: true
                    },
                    modal8URL: {
                    	required: true,
                    	isSystemURL: true
                    }
                },
                submitHandler: function(form){
               		 /*
                		$("#modal9UserId").val($("#modal8UserId").val());
						$("#modal9companyName").val($("#modal8companyName").val());
						var status = $("#e3").val();
				  		if (status == 'S') {
				      		$("#modal9Status").val("正常");
				      	} else{
				      		$("#modal9Status").val("冻结");
				      		$("#modal9Status").css("color","red")
				      	}
				  		$("#modal9Username").val($("#modal8Username").val());
				  		$("#modal9Email").val($("#modal8Email").val());
				  		$("#modal9Tel").val($("#modal8Tel").val());
				  		$("#modal9AppKey").html($("#modal8AppKey").html());
				  		$("#modal9AppSecret").html($("#modal8AppSecret").html());
				  		$("#modal9URL").val($("#modal8URL").val());
				  		var mothodHtml = $("#modal9ProductMothodBody").html("");
				  		mothodHtml.append('<div class="col-md-12">已配置产品</div>');
				  		var productId = "";
				  		var methodClassHtml = "";
				  		mothodHtml.append('<div class="col-md-6"><div class="col-md-6"></div><div class="col-md-3">总次数(已用/总计)</div><div class="col-md-3">今日次数(已用/总计)</div></div>');
  						mothodHtml.append('<div class="col-md-6"><div class="col-md-6"></div><div class="col-md-3">总次数(已用/总计)</div><div class="col-md-3">今日次数(已用/总计)</div></div>');
				  		methodClassHtml = methodClassHtml + '<div id="modal9Mthod">';
				  		var l = 0;
  						var r = 0;
  						var leftright = true;
  						for(var i=0; i < methodList.length;i++ ) {
				  			var productIdTemp = methodList[i].productId;
				  			if(productId != productIdTemp){
				  				if("" != productId){
				  					methodClassHtml = methodClassHtml + '</div></div>';
				  				}
				  				if(l > r){
				  					leftright = false;
				  				}
				  				else{
				  					leftright = true;
				  				}
				  				if(leftright){
				  					methodClassHtml = methodClassHtml + '<div class="modal9ProductClass col-md-6 floatLeft">';
				  				}
				  				else{
				  					methodClassHtml = methodClassHtml + '<div class="modal9ProductClass col-md-6 floatRight">';
				  				}
				  				methodClassHtml = methodClassHtml + '<div class="col-md-12 modal9ProductName"><input type="checkbox" name="modal9Product" disabled="disabled" class="modal9Product" id="modal9'+methodList[i].productId+'" value="'+methodList[i].productId+'">&nbsp;<span>'+methodList[i].productName+'</span></div>';
				  				methodClassHtml = methodClassHtml +  '<div class="modal9MethodClass">';
				  				productId = productIdTemp;
				  			}
				  			if(leftright){
				  				l++;
				  			}
				  			else{
				  				r++;
				  			}
				  			methodClassHtml = methodClassHtml + '<div class="col-md-12">';
				  			methodClassHtml = methodClassHtml + '<div class="modal9InterfaceDiv col-md-6"><input type="checkbox" name="modal9Interface" class="modal9Interface" disabled="disabled" id="modal9'+methodList[i].id+'" value="'+methodList[i].id+'">&nbsp;<span>'+methodList[i].descName+'</span></div>';
				  			methodClassHtml = methodClassHtml + '<div class="modal9TotalCount col-md-3"><input type="text" class="form-control inputPositiveInteger" disabled="disabled" id="modal9TotalCount'+methodList[i].id+'" name="modal9TotalCount'+methodList[i].id+'"></div>';
				  			methodClassHtml = methodClassHtml + '<div class="modal9DayCount col-md-3"><input type="text" class="form-control inputPositiveInteger" disabled="disabled" id="modal9DayCount'+methodList[i].id+'" name="modal9DayCount'+methodList[i].id+'"></div>';
				  			methodClassHtml = methodClassHtml + '</div>';
				  	    }
				  	    methodClassHtml = methodClassHtml + '</div></div></div>';
  	    				mothodHtml.append(methodClassHtml);
  	    				var modal8InterfaceList = $("#modal8Mthod").find(".modal8Interface:checked");
  	    				if(modal8InterfaceList && modal8InterfaceList.length > 0){
  	    					for(var i=0; i < modal8InterfaceList.length;i++ ) {
  	    						var interfaceIdTemp = $(modal8InterfaceList[i]).val();
  	    						$("#modal9"+interfaceIdTemp).prop("checked", true);
  	    						$("#modal9TotalCount"+interfaceIdTemp).val($("#modal8TotalCount"+interfaceIdTemp).val());
  	    						$("#modal9DayCount"+interfaceIdTemp).val($("#modal8DayCount"+interfaceIdTemp).val());
  	    					}
  	    				}
  	    				
  	    				$(".inputPositiveInteger").keyup(function(){     
					        var tmptxt=$(this).val();     
					         $(this).val(tmptxt.replace(/\D|^0\d+/g,''));          
					    }).bind("paste",function(){     
					        var tmptxt=$(this).val();     
					         $(this).val(tmptxt.replace(/\D|^0\d+/g,''));     
					    }).css("ime-mode", "disabled");
					    
  	    				var modal8ProductList = $("#modal8Mthod").find(".modal8Product:checked");
  	    				if(modal8ProductList && modal8ProductList.length > 0){
  	    					for(var i=0; i < modal8ProductList.length;i++ ) {
  	    						var productIdTemp = $(modal8ProductList[i]).val();
  	    						$("#modal9"+productIdTemp).prop("checked", true);
  	    					}
  	    				}
  	    				
				  		$("#updateDetailsModal").modal("hide");
				  		$("#confirmDetailsModal").modal({backdrop: 'static', keyboard: false});//开启模态框
				  		
				  		*/
				  		
				  		var userId = $("#modal8UserId").val();
						var status = $("#e3").val();
						var userName = $("#modal8Username").val();
						var email = $("#modal8Email").val();
						var tel = $("#modal8Tel").val();
						var url = $("#modal8URL").val();
						var companyName = $("#modal8companyName").val();
						var modal8InterfaceList = $(".modal8Interface:checked");
						var interfaceIdListStr = "[";
						if(modal8InterfaceList){
							for(var i=0; i < modal8InterfaceList.length;i++ ) {
								if(i != 0){
									interfaceIdListStr = interfaceIdListStr + ",";
								}
								var interfaceIdTemp = $(modal8InterfaceList[i]).val();
								var totalCountTmp = $("#modal8TotalCount"+interfaceIdTemp).val();
								var dayCountTmp = $("#modal8DayCount"+interfaceIdTemp).val();
								interfaceIdListStr = interfaceIdListStr + "{'interfaceId':'"+interfaceIdTemp+"','totalCount':'"+totalCountTmp+"','dayCount':'"+dayCountTmp+"'}";
							}
						}
						interfaceIdListStr = interfaceIdListStr + "]";
						$("#productDetailsSubmit").html('处理中...');
						$("#productDetailsSubmit").attr("disabled","disabled");
						var param = {
								userId: userId,
								status: status,
								userName: userName,
								email: email,
								tel: tel,
								url: url,
								companyName: companyName,
								interfaceIdListStr: interfaceIdListStr
							};
						$.post($context.ctx+"/system/api/updateUserInfo",param,function(data){
							$("#productDetailsSubmit").html('提交');
							$("#productDetailsSubmit").removeAttr("disabled");
							var obj = jQuery.parseJSON(data);
							if(obj.resp_code == "success"){
								$('#updateDetailsModal').modal('hide');//关闭模态框   
								$("tr").removeClass("focusTr");
									var oSettings = datatables.fnSettings();
									var status = $("#e1").val();
									var searchCondition = $("#searchCondition").val();
									oSettings.sAjaxSource = $context.ctx + "/system/api/basics_query?status="+status+"&searchCondition="+searchCondition;
									datatables.fnClearTable(0);
									datatables.fnDraw();
									jAlert("修改用户信息成功","提示");
							} else {
								jAlert(obj.resp_msg,"提示");
							}
						});
                },
                invalidHandler: function (event, validator) { 
                	
                },

                highlight: function (element) { 
                    $(element) .closest('.form-group').removeClass('has-success').addClass('has-error'); 
                },

                unhighlight: function (element) { 
                    $(element).closest('.form-group').removeClass('has-error'); 
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