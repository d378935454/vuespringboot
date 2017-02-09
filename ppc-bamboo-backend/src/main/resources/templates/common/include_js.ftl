<!-- JAVASCRIPTS -->
<!-- Placed at the end of the document so the pages load faster -->
<!-- JQUERY -->
<script src="${ctx }/resources/js/jquery/jquery-2.0.3.min.js"></script>
<!-- JQUERY UI-->
<script src="${ctx }/resources/js/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
<!-- BOOTSTRAP -->
<script src="${ctx }/resources/bootstrap-dist/js/bootstrap.min.js"></script>
<!-- COOKIE -->
<script type="text/javascript" src="${ctx }/resources/js/jQuery-Cookie/jquery.cookie.min.js"></script>
<!-- UNIFORM -->
<script type="text/javascript" src="${ctx }/resources/js/uniform/jquery.uniform.min.js"></script>
<!-- /JAVASCRIPTS -->
	<script type="text/javascript">
	$(function(){
		$("#log_out").click(function(){
			$.post($context.ctx+"/login/login_out?m="+ Math.random(),function(data){
				var obj = jQuery.parseJSON(data);
	        	if(obj.resp_code == "success"){
	        		location.href=obj.resp_msg;
	        	}
			});
		});

		/* 个人信息修 改  begin   */
        var wizform = $("#wizInfoForm");
        var alert_error = $('.alert-danger', wizform);
        var alert_success = $('.alert-success', wizform);
        wizform.validate({
                doNotHideMessage: true,
				errorClass: 'error-span',
                errorElement: 'span',
                rules: {
                	userRealName: {
                        required: true,
                        maxlength: 50
                    },
                    userPhone: {
                    	required: true,
                    	minlength: 11,
                    	maxlength:11
                    },userEmail: {
                    	required: true,
                    	email: true
                    }
                },
                submitHandler: function(form){
                	alert_success.hide();
                    alert_error.hide();
        
            		$(form).ajaxSubmit({
                        type:"post",
                        url:$context.ctx+"/system/admin/edit_personal",
                        success: function(data,status){
                        	$("#editSave").button('reset');
                        	var obj = jQuery.parseJSON(data);
                        	if(obj.resp_code == "error"){
                        		$("#tip_message").html(obj.resp_msg);
                        		alert_error.show();
                        	}
                        	if(obj.resp_code == "success"){
                        		$("#tip_success_message").html(obj.resp_msg);
                        		alert_success.show();
                        	}
                        }
                      });
                },
                invalidHandler: function (event, validator) { 
                	alert_success.hide();
                    alert_error.show();
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
            
			$("#editInfo").click(function(){
					$('#piModel').modal({
					      keyboard: true
					 });
					alert_success.hide();
                    alert_error.hide();
			});
			
			/* ** 个人信息修 改  end  ** */
			
			
			/* 个人密码修 改  begin   */
        var wizPwdform = $("#wizPwdForm");
        var alert_error_pwd = $('.alert-danger', wizPwdform);
        var alert_success_pwd = $('.alert-success', wizPwdform);
            wizPwdform.validate({
                doNotHideMessage: true,
				errorClass: 'error-span',
                errorElement: 'span',
                rules: {
                	oldPassword: {
                        required: true
                    },
                    password: {
                    	required: true,
                    	minlength: 6
                    },
                    confPassword: {
                    	required: true,
                    	equalTo: "#password"
                    }
                },
                submitHandler: function(form){
                	alert_success_pwd.hide();
                    alert_error_pwd.hide();
    
            		$(form).ajaxSubmit({
                        type:"post",
                        url:$context.ctx+"/system/admin/edit_password",
                        success: function(data,status){
                        	//$("#editSave").button('reset');
                        	var obj = jQuery.parseJSON(data);
                        	if(obj.resp_code == "error"){
                        		$("#pwd_message").html(obj.resp_msg);
                        		alert_error_pwd.show();
                        	}
                        	if(obj.resp_code == "success"){
                        		$("#pwd_success_message").html(obj.resp_msg);
                        		alert_success_pwd.show();
                        	}
                        }
                      });
                },
                invalidHandler: function (event, validator) { 
                	alert_success_pwd.hide();
                    alert_error_pwd.show();
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
            
			$("#editPwd").click(function(){
					$("#oldPassword").closest('.form-group').removeClass('has-error');
					$("#oldPassword").closest('.form-group').removeClass('has-success');
					$("span[for='oldPassword']").addClass('valid').html("");
					$("#oldPassword").val("");
					$("#password").closest('.form-group').removeClass('has-error');
					$("#password").closest('.form-group').removeClass('has-success');
					$("span[for='password']").addClass('valid').html(""); 
					$("#password").val("");
					$("#confPassword").closest('.form-group').removeClass('has-error');
					$("#confPassword").closest('.form-group').removeClass('has-success');
					$("span[for='confPassword']").addClass('valid').html(""); 
					$("#confPassword").val("");
					$('#pwModel').modal({
					      keyboard: true
					 });
					 alert_success_pwd.hide();
					 alert_error_pwd.hide();
			});     
			
			/* 个人密码修 改  end   */       

	});
	</script>