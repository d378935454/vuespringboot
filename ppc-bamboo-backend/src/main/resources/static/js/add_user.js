/*$(function(){
	//全选按钮
	$("#checkAll").click(function() {
		if ($("#checkAll").is(":checked")){
			$("[name='checkbox']").prop("checked",true); 
		} else{
			$("[name='checkbox']").prop("checked",false);   
		}        
    });  
});
function usermodal(){
	var email = $("#email").val();
	var username = $("#username").val();
	var tel = $("#tel").val();
	$("#modalUserName").html(username);
	$("#modalEmail").html(email);
	$("#modalTel").html(tel);
	var isc = "";
	var checkName = "";
	$("input[name='checkbox']").each(function(){ //遍历table里的全部checkbox		
        if($(this).is(":checked")) {//如果被选中
        	isc += $(this).val() + ","; //获取被选中的值
        	checkName += $(this).siblings().text()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        }	
	});
	if(isc.length > 0) {//如果获取到
        isc = isc.substring(0, isc.length - 1); //把最后一个逗号去掉
	}
	$("#productConfiguration").html(checkName);	
}*/
var FormWizard = function () {
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
                    }
                },
                submitHandler: function(form){
                	$("#modalOpen").click();
                	var company = $("#company").val();
                	var email = $("#email").val();
                	var username = $("#username").val();
                	var tel = $("#tel").val();
                	$("#modalCompany").html(company);
                	$("#modalUserName").html(username);
                	$("#modalEmail").html(email);
                	$("#modalTel").html(tel);
                	var isc = "";
                	var checkName = $("#productConfiguration").html("");
                	$("input[name='checkbox']").each(function(){ //遍历table里的全部checkbox		
                        if($(this).is(":checked")) {//如果被选中
                        	isc += $(this).val() + ","; //获取被选中的值
                        	checkName.append('<div class="col-md-6"><span>'+$(this).siblings().text()+'</span></div>');
                        }	
                	});
                	if(isc.length > 0) {//如果获取到
                        isc = isc.substring(0, isc.length - 1); //把最后一个逗号去掉
                	}
                	$("#ids").val(isc);
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
            //全选按钮
        	$("#checkAll").click(function() {
        		if ($("#checkAll").is(":checked")){
        			$("[name='checkbox']").prop("checked",true); 
        		} else{
        			$("[name='checkbox']").prop("checked",false);   
        		}        
            });
        	$("#saveButton").click(function(){       		
        			//后台交互
        			var modalCompany = $("#modalCompany").html();
            		var modalUserName = $("#modalUserName").html();
            		var modalEmail = $("#modalEmail").html();
            		var modalTel = $("#modalTel").html();
            		var ids = $("#ids").val();
            		var obj = {
            			modalCompany: modalCompany,
            			modalUserName: modalUserName,
            			modalEmail: modalEmail,
            			modalTel: modalTel,
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
            					$('#table-modal').modal('hide');//关闭模态框       					
            					jAlert("用户新增成功","提示");//弹出对话框      					
            					window.location.href = $context.ctx + "/system/api/basics_manage";//跳转到列表页面
            				} else {
            					jAlert(obj.resp_msg,"提示");//弹出对话框
            				}      				
            			}
            		});        	        
        	    });            		
        }
    };
}();
