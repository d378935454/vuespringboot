<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="ppd"  uri="/WEB-INF/tld/ppd.tld" %>
var FormWizard = function () {
    return {
        init: function () {
            
			/*-----------------------------------------------------------------------------------*/
			/*	Show country list in Uniform style
			/*-----------------------------------------------------------------------------------*/

            var wizform = $('#wizForm');
            var add_alert_error = $('.alert-danger', wizform);
            var add_alert_success = $('.alert-success', wizform);
			/*-----------------------------------------------------------------------------------*/
			/*	Validate the form elements
			/*-----------------------------------------------------------------------------------*/
            wizform.validate({
                doNotHideMessage: true,
				errorClass: 'error-span',
                errorElement: 'span',
                rules: {
                    /* Create Account */
                	userLoginName: {
                        required: true,
                        email: true
                    },
                    userLoginPassword: {
                        required: true,
                        minlength: 6
                    },
                    role: {
                        required: true
                    },
                    userRealName: {
                    	required: true
                    },
                    userMobile: {
                    	required: true,
                    	minlength: 11
                    },
                    userSex: {
                    	required: true
                    },
                    userDesc: {
                    	required: true,
                    	minlength: 16
                    }
                },
                submitHandler: function(form){
                	add_alert_success.hide();
                	add_alert_error.hide();
                	$("#sub").button('loading');
                	$(form).ajaxSubmit({           			
                        type:"post",
                        url:$context.ctx+"/system/admin/save_user",
                        success: function(data,status){
                        	$("#sub").button('reset');
                        	var obj = jQuery.parseJSON(data);
                        	if(obj.resp_code == "error"){
                        		$(wizform).find(".tip_message").html(obj.resp_msg);
                        		add_alert_error.show();
                        	}
                        	if(obj.resp_code == "success"){
                        		$(wizform).find(".tip_success_message").html(obj.resp_msg);      
                        		add_alert_success.show();
                        		document.getElementById("wizForm").reset(); 
                        		$("select").val('');
                        		setTimeout(function () { 
        							search();
							    }, 3000); 
							   window.location.reload();							                      		                        	
                        	}
                        }
                      });         		
                },
                invalidHandler: function (event, validator) { 
                	add_alert_success.hide();
                	add_alert_error.show();
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

            
            var editForm = $('#editForm');
            var edit_alert_error = $('.alert-danger', editForm);
            var edit_alert_success = $('.alert-success', editForm);
			/*-----------------------------------------------------------------------------------*/
			/*	Validate the form elements
			/*-----------------------------------------------------------------------------------*/
            editForm.validate({
                doNotHideMessage: true,
				errorClass: 'error-span',
                errorElement: 'span',
                rules: {
                    /* Create Account */
                	userLoginName: {
                        required: true,
                        email: true
                    },
                    userLoginPassword: {
                        required: true,
                        minlength: 6
                    },
                    role: {
                        required: true
                    },
                    userRealName: {
                    	required: true
                    },
                    userMobile: {
                    	required: true,
                    	minlength: 11
                    },
                    userSex: {
                    	required: true
                    },
                    userDesc: {
                    	required: true,
                    	minlength: 16
                    }
                },
                submitHandler: function(form){
                	edit_alert_success.hide();
                	edit_alert_error.hide();
                	$("#editSave").button('loading');
            		$(form).ajaxSubmit({
                        type:"post",
                        url:$context.ctx+"/system/admin/edit_user",
                        success: function(data,status){
                        	$("#editSave").button('reset');
                        	var obj = jQuery.parseJSON(data);
                        	if(obj.resp_code == "error"){
                        		$(editForm).find(".tip_message").html(obj.resp_msg);
                        		edit_alert_error.show();
                        	}
                        	if(obj.resp_code == "success"){
                        		$(editForm).find(".tip_success_message").html(obj.resp_msg);
                        		edit_alert_success.show();
                        		setTimeout(function () { 
									$("#selectUserList").show();
        							$("#editUser").hide();
        							search();
							    }, 2000); 
							    window.location.reload();
                        	}
                        }
                      });
                },
                invalidHandler: function (event, validator) { 
                	edit_alert_success.hide();
                	edit_alert_error.show();
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
            
             $("#lookUser").click(function(){
        		window.location.reload();
        	});
        	
        	
            //编辑
        	$(".btn-edit").click(function(){
        		var userId = $(this).val();
        		editUser(userId);
        	});
        	
      	  function editUser(userId){
      	  		$.post($context.ctx+"/system/admin/select_user?userId="+userId,function(data){
        			var obj = jQuery.parseJSON(data);
        			$("#selectLEdit").empty();
        			$("#selectREdit").empty();        			
                	if(obj.resp_code == "success"){
                	    var body = jQuery.parseJSON(obj.resp_body);
                	    var userRole = body.userRole;
                	    var userInList = body.userInList;
                	    var userNotInList = body.userNotInList;
                		var userId = userRole.id;
                		var user = userRole.user;
                		var role = userRole.role;
                		$("#userRoleSel").find('option[value="'+role.id+'"]').attr("selected",true);
                		$("#userSexSel").find('option[value="'+user.userSex+'"]').attr("selected",true);
                		$("#userId").val(user.id);
                		$("#userName").val(user.userLoginName);
                		$("#realName").val(user.userRealName);
                		$("#userMobile").val(user.userMobile);
                		$("#userEmail").val(user.userEmail);
                		$("#userDesc").val(user.userDesc);
                		$("#selectUserList").hide();
                		$("#editUser").show();
                		if (user.isAllcustomer == '1') {
                			$("#allSelect").parent().attr("class","checked");
                			$("#allSelect").attr("checked",true);
                			$("#singleSelect").attr("checked",false);
                			$("#allSelect").val("1");
                			$("#selectLEdit").attr("disabled",true);
							$("#selectREdit").attr("disabled",true);
							$("#torightEdit").removeAttr("href");
							$("#toleftEdit").removeAttr("href");
							$("#selectLEdit").find("option:selected").each(function(){
								$(this).attr("selected",false);
							}); 
							$("#selectREdit").find("option:selected").each(function(){
								$(this).attr("selected",false);
							});
                		} else {
                			$("#singleSelect").parent().attr("class","checked");
                			$("#allSelect").attr("checked",false);
                			$("#singleSelect").attr("checked",true);
                			$("#singleSelect").val("0");
                			$("#selectLEdit").attr("disabled",false);
							$("#selectREdit").attr("disabled",false);
							$("#torightEdit").attr("href","javascript:void(0)");
							$("#toleftEdit").attr("href","javascript:void(0)");
                		}
                		var editSelects = "";
                		for (var i = 0 ; i < userInList.length ; i++){
                			$("#selectREdit").append("<option value="+userInList[i].id+">"+userInList[i].company+"("+userInList[i].email+")"+"</option>");
                			editSelects += userInList[i].id +",";
                		}
                		if (editSelects.length > 0) {
                			editSelects = editSelects.substring(0, editSelects.length-1);
                		}
                		$("#editSelects").val(editSelects);
                		for (var i = 0 ; i < userNotInList.length ; i++) {
                			$("#selectLEdit").append("<option value="+userNotInList[i].id+">"+userNotInList[i].company+"("+userNotInList[i].email+")"+"</option>");
                		}             		
                	};
        		});
       	    }
        	 //冻结
        	$(".btn-freeze").click(function(){
        		var userId = $(this).val();
        		freezeUser(userId);
        	});
        	
        	function freezeUser(userId){
	        	$.confirm("确定冻结该用户?", function (s) {
	        			if (s) {
	                		$.post($context.ctx+"/system/admin/user_freeze?userId="+userId+"&status=F",function(data){
	                			var obj = jQuery.parseJSON(data);
	                        	if(obj.resp_code == "success"){
	                        		alert("冻结用户成功！");
	                        		search();
	                        	};
	                		});
	        			} else {
	        			}
	        		});
        	}
        	
        	 //解冻
        	$(".btn-unfreeze").click(function(){
        		var userId = $(this).val();
        		unfreezeUser(userId);
        	});
        	
        	function unfreezeUser(userId){
        			$.confirm("确定解冻该用户?", function (s) {
        			if (s) {
                		$.post($context.ctx+"/system/admin/user_freeze?userId="+userId+"&status=S",function(data){
                			var obj = jQuery.parseJSON(data);
                        	if(obj.resp_code == "success"){
                        		alert("解冻用户成功！");
                        		search();
                        	};
                		});
        			} else {
        			}
        		});
        	}
        	
        	//重置密码
        	$(".btn-resetPassword").click(function(){
        		var userId = $(this).val();
        		resetPass(userId);
        	});
        	
        	function resetPass(userId){
        		$.confirm("确定重置该用户密码?", function (s) {
        			if (s) {
        				$.post($context.ctx+"/system/admin/resetPass?userId="+userId,function(data){
                			var obj = jQuery.parseJSON(data);
                        	if(obj.resp_code == "success"){
                        		alert("重置密码成功！");
                        	}else{
                        	}
                		});
        			} else {
        			}
        		});
        	}
        	
        	//查询
        	$("#search").click(function(){
        		Load('正在运行，请稍后...');
        		search();
        		setTimeout(function () { 
					dispalyLoad(); 
			    }, 1000); 
        	});
        	
        	function search(){
        		var roleId = $("#e1").val();
        		var status = $("#e2").val();
        		var searchCondition =  encodeURIComponent(encodeURIComponent($("#searchCondition").val()));
        		$.post($context.ctx+"/system/admin/queryUserList?roleId="+roleId+"&status="+status+"&searchCondition="+searchCondition,function(data){
        			var obj = jQuery.parseJSON(data);
                	if(obj.resp_code == "success"){
                		var userList = jQuery.parseJSON(obj.resp_body);
                		if(userList){
                			var appendHtml = "";
                			for(var i = 0; i < userList.length; i++){
                    			var userInfo  = userList[i];
                    			var innerHtml = " <tr><td>"+userInfo.user.userLoginName+"</td><td>"+userInfo.user.userRealName+"</td><td>"+userInfo.role.name+"</td><td>"+userInfo.user.userMobile+"</td>";
                    			if(userInfo.user.userStatus == 'S'){
                    				innerHtml = innerHtml +"<td><font>正常</font></td>";
                    			}
                    			if(userInfo.user.userStatus == 'F'){
                    				innerHtml = innerHtml +"<td><font color='red'>冻结</font></td>";
                    			}
                    			innerHtml = innerHtml +"<td>";
                    			innerHtml = innerHtml +"<ppd:auth ctype='edit' menuid='23' parentid='${parentId}'><button value='"+userInfo.user.id+"' class='btn btn-xs btn-info btn-edit'>编辑</button>&nbsp;</ppd:auth>";
                    			if(userInfo.user.userStatus == 'S'){
                    				innerHtml = innerHtml +"<ppd:auth ctype='edit' menuid='25' parentid='${parentId}'><button value='"+userInfo.user.id+"' class='btn btn-xs btn-info btn-freeze'>冻结</button>&nbsp;</ppd:auth>";
                    			}
                    			if(userInfo.user.userStatus == 'F'){
                    				innerHtml = innerHtml +"<ppd:auth ctype='edit' menuid='25' parentid='${parentId}'><button value='"+userInfo.user.id+"' class='btn btn-xs btn-info btn-unfreeze'><font color='red'>解冻</font></button>&nbsp;</ppd:auth>";
                    			}
                    			innerHtml = innerHtml +"<ppd:auth ctype='edit' menuid='26' parentid='${parentId}'><button value='"+userInfo.user.id+"' class='btn btn-xs btn-info btn-resetPassword' data-toggle='modal'>重置密码</button></ppd:auth>";
                    			innerHtml = innerHtml +"</td></tr>";
                    		appendHtml = appendHtml + innerHtml;
                    		
                    		}
                			$("#userList").html('');
                			$("#userList").append(appendHtml);
                			$("#userList").find(".btn-edit").click(function(){
                				var userId = $(this).val();
        						editUser(userId);
                			 });
                			 $("#userList").find(".btn-freeze").click(function(){
                				var userId = $(this).val();
        						freezeUser(userId);
                			 });
                			  $("#userList").find(".btn-unfreeze").click(function(){
                				var userId = $(this).val();
        						unfreezeUser(userId);
                			 });
                			  $("#userList").find(".btn-resetPassword").click(function(){
                				var userId = $(this).val();
        						resetPass(userId);
                			 });
                			 
                		}
                	}else{
                	}
        		});
        	}
        	
			$("#cancelEdit").click(function(){
			    $('#editForm .alert-danger').hide();
                $('#editForm .alert-success').hide();
        		$("#selectUserList").show();
        		$("#editUser").hide();
        		search();
        	});
        	$("#selectL").dblclick(function(){ 
        	    var selVal = [];
        		var rightSel = $("#selectR");
			    $(this).find("option:selected").each(function(){ 
			        $(this).remove().appendTo(rightSel); 
			    }); 
			    rightSel.find("option").each(function(){ 
			        selVal.push(this.value); 
			    });
			    $("#addSelects").val(selVal.join(","));
			}); 
			$("#selectR").dblclick(function(){
			    var selVal = []; 
				var leftSel = $("#selectL");
				var rightSel = $("#selectR");
			    $(this).find("option:selected").each(function(){ 
			        $(this).remove().appendTo(leftSel); 
			    });
			    rightSel.find("option").each(function(){ 
			        selVal.push(this.value); 
			    });
			    $("#addSelects").val(selVal.join(","));
			});
			$("#selectLEdit").dblclick(function(){ 
        	    var selVal = [];
        		var rightSel = $("#selectREdit");
			    $(this).find("option:selected").each(function(){ 
			        $(this).remove().appendTo(rightSel); 
			    }); 
			    rightSel.find("option").each(function(){ 
			        selVal.push(this.value); 
			    });
			    $("#editSelects").val(selVal.join(","));
			}); 
			$("#selectREdit").dblclick(function(){
			    var selVal = []; 
				var leftSel = $("#selectLEdit");
				var rightSel = $("#selectREdit");
			    $(this).find("option:selected").each(function(){ 
			        $(this).remove().appendTo(leftSel); 
			    });
			    rightSel.find("option").each(function(){ 
			        selVal.push(this.value); 
			    });
			    $("#editSelects").val(selVal.join(","));
			});
			$("#addCancel").click(function(){
			    window.location.href = $context.ctx + "/system/admin/user_manage";
        	});
        }
    };
}();

function selectCustom(radio) {
	var value = radio.val();
	if (value == '1') {
		$("#selectL").attr("disabled",true);
		$("#selectR").attr("disabled",true);
		$("#toright").removeAttr("href");
		$("#toleft").removeAttr("href");
		$("#selectL").find("option:selected").each(function(){
			$(this).attr("selected",false);
		}); 
		$("#selectR").find("option:selected").each(function(){
			$(this).attr("selected",false);
		}); 		
	} else {
		$("#selectL").attr("disabled",false);
		$("#selectR").attr("disabled",false);
		$("#toright").attr("href","javascript:void(0)");
		$("#toleft").attr("href","javascript:void(0)");		
	}
}

function toRight() {
	var selVal = [];
	var leftSel = $("#selectL"); 
	var rightSel = $("#selectR");
	leftSel.find("option:selected").each(function(){ 
        $(this).remove().appendTo(rightSel); 
    }); 
    rightSel.find("option").each(function(){ 
        selVal.push(this.value); 
    });
    $("#addSelects").val(selVal.join(","));
}

function toLeft() {
	var selVal = [];
	var leftSel = $("#selectL"); 
	var rightSel = $("#selectR");
	rightSel.find("option:selected").each(function(){ 
        $(this).remove().appendTo(leftSel); 
    });
    rightSel.find("option").each(function(){ 
        selVal.push(this.value); 
    });
    $("#addSelects").val(selVal.join(","));
}


function selectCustomEdit(radio) {
	var value = radio.val();
	if (value == '1') {
		$("#selectLEdit").attr("disabled",true);
		$("#selectREdit").attr("disabled",true);
		$("#torightEdit").removeAttr("href");
		$("#toleftEdit").removeAttr("href");
		$("#selectLEdit").find("option:selected").each(function(){
			$(this).attr("selected",false);
		}); 
		$("#selectREdit").find("option:selected").each(function(){
			$(this).attr("selected",false);
		}); 		
	} else {
		$("#selectLEdit").attr("disabled",false);
		$("#selectREdit").attr("disabled",false);
		$("#torightEdit").attr("href","javascript:void(0)");
		$("#toleftEdit").attr("href","javascript:void(0)");		
	}
}

function toRightEdit() {
	var selVal = [];
	var leftSel = $("#selectLEdit"); 
	var rightSel = $("#selectREdit");
	leftSel.find("option:selected").each(function(){ 
        $(this).remove().appendTo(rightSel); 
    }); 
    rightSel.find("option").each(function(){ 
        selVal.push(this.value); 
    });
    $("#editSelects").val(selVal.join(","));
}

function toLeftEdit() {
	var selVal = [];
	var leftSel = $("#selectLEdit"); 
	var rightSel = $("#selectREdit");
	rightSel.find("option:selected").each(function(){ 
        $(this).remove().appendTo(leftSel); 
    });
    rightSel.find("option").each(function(){ 
        selVal.push(this.value); 
    });
    $("#editSelects").val(selVal.join(","));
}