var standardForm = function () {
    return {
        init: function () {
            
			/*-----------------------------------------------------------------------------------*/
			/*	Show country list in Uniform style
			/*-----------------------------------------------------------------------------------*/

            var wizform = $('#wizForm');
            var alert_error = $('.alert-danger', wizform);
			/*-----------------------------------------------------------------------------------*/
			/*	Validate the form elements
			/*-----------------------------------------------------------------------------------*/
            wizform.validate({
                doNotHideMessage: true,
				errorClass: 'error-span',
                errorElement: 'span',
                rules: {
                    /* Create Account */
					email: {
                        required: true,
                        email: true
                    },
                    password: {
                        minlength: 3,
                        required: true
                    },
                    captcha: {
                    	required: true
                    }
                },
                submitHandler: function(form){
                	$("#sub").button('loading');
                	var _c = $("input[type='checkbox']").is(':checked');
                	if(_c){
                		$.cookie('free_login_7', '1', {expires: 7,path:'/'});
                	}
            		$(form).ajaxSubmit({
                        type:"post",
                        url:$context.ctx+"/login/submit?m="+ Math.random(),
                        success: function(data,status){
                        	$("#sub").button('reset');
                        	var obj = jQuery.parseJSON(data);
                        	if(obj.resp_code == "error"){
                        		$("#tip_message").html(obj.resp_msg);
                        		alert_error.show(); 
                        		$("#randCodeImage").attr("src", $context.ctx +"/default.gif?" + Math.floor(Math.random() * 100));
//                        		if (obj.resp_msg == "验证码错误"||obj.resp_msg == "验证码已过期"){
//                        			$("#randCodeImage").attr("src", $context.ctx +"/default.gif?" + Math.floor(Math.random() * 100));
//                        		}
                        	}
                        	if(obj.resp_code == "success"){
                        		location.href=obj.resp_msg;
                        	}
                        }
                      });
                },
                invalidHandler: function (event, validator) { 
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

            /*-----------------------------------------------------------------------------------*/
			/*	Initialize Bootstrap Wizard
			/*-----------------------------------------------------------------------------------*/
        }
    };
}();

var ldapForm=function () {
    return {
        init: function () {
            
			/*-----------------------------------------------------------------------------------
				Show country list in Uniform style
			/*-----------------------------------------------------------------------------------*/

            var wizform = $('#ldapForm');
            var alert_error = $('.alert-danger', wizform);
			/*-----------------------------------------------------------------------------------
				Validate the form elements
			/*-----------------------------------------------------------------------------------*/
            wizform.validate({
                doNotHideMessage: true,
				errorClass: 'error-span',
                errorElement: 'span',
                rules: {
//                     Create Account 
//					email: {
//                        required: true,
//                        email: true
//                    },
                    password: {
                        minlength: 3,
                        required: true
                    },
                    captcha: {
                    	required: true
                    }
                },
                submitHandler: function(form){
                	$("#ldap_sub").button('loading');
                	var _c = $("input[type='checkbox']").is(':checked');
                	if(_c){
                		$.cookie('free_login_7', '1', {expires: 7,path:'/'});
                	}
            		$(form).ajaxSubmit({
                        type:"post",
                        url:$context.ctx+"/login/ldapsubmit?m="+ Math.random(),
                        success: function(data,status){
                        	$("#ldap_sub").button('reset');
                        	var obj = jQuery.parseJSON(data);
                        	if(obj.resp_code == "error"){
                        		$("#ldap_tip_message").html(obj.resp_msg);
                        		alert_error.show(); 
                        		$("#ldap_randCodeImage").attr("src", $context.ctx +"/default.gif?" + Math.floor(Math.random() * 100));
//                        		if (obj.resp_msg == "验证码错误"||obj.resp_msg == "验证码已过期"){
//                        			$("#randCodeImage").attr("src", $context.ctx +"/default.gif?" + Math.floor(Math.random() * 100));
//                        		}
                        	}
                        	if(obj.resp_code == "success"){
                        		location.href=obj.resp_msg;
                        	}
                        }
                      });
                },
                invalidHandler: function (event, validator) { 
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

            /*-----------------------------------------------------------------------------------
				Initialize Bootstrap Wizard
			/*-----------------------------------------------------------------------------------*/
        }
    };
}();

 
