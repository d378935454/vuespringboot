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
                	name: {
                        required: true,
                    },
                    method: {
                        required: true
                    },
                    sortid: {
                        required: true
                    },
                    remark: {
                    	required: true
                    },
                    url: {
                    	required: true
                    },
                    outerAddr: {
                    	required: true
                    }
                },
                submitHandler: function(form){
                	add_alert_success.hide();
                	add_alert_error.hide();
                	$("#sub").button('loading');
            		$(form).ajaxSubmit({
                        type:"post",
                        url:$context.ctx+"/system/admin/save_menu",
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
                        			 window.location.reload();
							    }, 2000); 
							   
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
                	name: {
                        required: true,
                    },
                    method: {
                        required: true
                    },
                    sortid: {
                        required: true
                    },
                    remark: {
                    	required: true
                    },
                    url: {
                    	required: true
                    },
                    outerAddr: {
                    	required: true
                    }
                },
                submitHandler: function(form){
                	edit_alert_success.hide();
                	edit_alert_error.hide();
                	$("#editSave").button('loading');
            		$(form).ajaxSubmit({
                        type:"post",
                        url:$context.ctx+"/system/admin/edit_menu",
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
                        			$("#selectMenuList").show();
                            		$("#editMenu").hide();
                            		$("#topMenuRedio").html('');
                            		window.location.reload();
							    }, 2000);   
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

            
            getMenus();
            
            function getMenus(){
        		$.post($context.ctx+"/system/admin/getFuncList",function(data){
        			var obj = jQuery.parseJSON(data);
                 	if(obj.resp_code == "success"){
                		var funcList = jQuery.parseJSON(obj.resp_body);
                		var addFuncHtml = "";
                		//var fundHtml = "";
                		
                		var addRoot = "<label class='radio-inline'> <input type='radio' id='rootAddMenu' class='uniform' name='parentid' value='' checked> 根目录 </label>";
                		addFuncHtml = addFuncHtml + "<ul style='list-style-type:none'>"+ addRoot + "<li><ul style='list-style-type:none'>";
                		for(var i=0; i<funcList.length; i++){
                			var func = funcList[i];
                			 if (func.parentid == null || func.parentid == "" && func.level < 2) {
                	            	var ss = "<label class='radio-inline'> <input type='radio' id='add"+func.id+"' class='uniform' name='parentid' value='"+func.id+"' >"+func.name+"</label>";
                	            	addFuncHtml = addFuncHtml + "<li>"+ss;
                	              var shtml =  build(func,funcList);
                	              addFuncHtml = addFuncHtml + shtml+"</li>";
                	         }
                		}
                		addFuncList = addFuncList + "</ul></li></ul>";
                		 $("#addFuncList").append(addFuncHtml);
                		 
                		 var editFuncHtml = "";
                 		//var fundHtml = "";
                  		var editRoot = "<label class='radio-inline'> <input type='radio' id='rootEditMenu' class='uniform' name='parentid' value='' checked> 根目录 </label>";
                  		editFuncHtml = editFuncHtml + "<ul style='list-style-type:none'>"+ editRoot + "<li><ul style='list-style-type:none'>";
                 		for(var i=0; i<funcList.length; i++){
                 			var func = funcList[i];
                 			 if (func.parentid == null || func.parentid == "" && func.level < 2) {
                 				 	var ss = "<label class='radio-inline'> <input type='radio' class='uniform' id='edit"+func.id+"' name='parentid' value='"+func.id+"' >"+func.name+"</label>";
                 	            	editFuncHtml = editFuncHtml + "<li>"+ss;
                 	              var shtml =  editBuild(func,funcList);
                 	             editFuncHtml = editFuncHtml + shtml+"</li>";
                 	         }
                 		}
                 		editFuncHtml = editFuncHtml + "</ul>";
                 		 $("#editFuncList").append(editFuncHtml);
                	};
        		});
        	}
            
            function build(func,funcList){
        		var addFuncHtml = "";
        		  var children = getChildren(func,funcList);  
        	        if (children.length > 0) {
        	        	if(func.level < 2){
        	        		addFuncHtml = addFuncHtml + "<ul style='list-style-type:none'>";
        	        		for(var i=0; i<children.length; i++){
        	        			var  child = children[i];
        	        			var ss = "<label class='radio-inline'> <input type='radio' id='add"+child.id+"' class='uniform' name='parentid' value='"+child.id+"' >"+child.name+"</label>";
    	                		if(child.level < 2){
    	                			addFuncHtml = addFuncHtml + "<li>"+ss;
	        	                      var shtml =  build(child,funcList);
	        	                      addFuncHtml = addFuncHtml + shtml+"</li>";
    	                		}
        	                 }  
        	        		addFuncHtml = addFuncHtml + "</ul>";
        	        	}
        	          
        	        } 
        	   return addFuncHtml;
        	}
        	
        	function editBuild(func,funcList){
        		var editFuncHtml = "";
        		  var children = getChildren(func,funcList);  
        	        if (children.length > 0) {
        	        	if(func.level < 2){
        	        		editFuncHtml = editFuncHtml + "<ul style='list-style-type:none'>";
        	        		for(var i=0; i<children.length; i++){
        	        			var  child = children[i];
        	        			var ss = "<label class='radio-inline'> <input type='radio' id='edit"+child.id+"' class='uniform' name='parentid' value='"+child.id+"' >"+child.name+"</label>";
    	                		if(child.level < 2){
    	                			editFuncHtml = editFuncHtml + "<li>"+ss;
	        	                      var shtml =  editBuild(child,funcList);
	        	                      editFuncHtml = editFuncHtml + shtml+"</li>";
    	                		}
        	                 }  
        	        		editFuncHtml = editFuncHtml + "</ul>";
        	        	}
        	          
        	        } 
        	   return editFuncHtml;
        	}
        	
        	function getChildren(func,funcList){  
        		var children = new Array(); 
                var id = func.id;  
                for(var i=0; i<funcList.length; i++){
                	var child = funcList[i];
                    if (id == child.parentid) {  
                    	children.push(child);
                    }
                } 
                return children;  
            }
            /*-----------------------------------------------------------------------------------*/
			/*	Initialize Bootstrap Wizard
			/*-----------------------------------------------------------------------------------*/
        	 //删除
        	$(".btn-delete").click(function(){
        		var userId = $(this).val();
        		$.confirm("确定删除该菜单?", function (s) {
        			if (s) {
                		$.post($context.ctx+"/system/admin/menu_delete?userId="+userId,function(data){
                			var obj = jQuery.parseJSON(data);
                        	if(obj.resp_code == "success"){
                        		window.location.reload();
                        	};
                		});
        			} else {
        			}
        		});
        	});
        	
        	
            //编辑
        	$(".btn-edit").click(function(){
        		var menuId = $(this).val();
        		edit_alert_success.hide();
            	edit_alert_error.hide();
        		$.post($context.ctx+"/system/admin/select_menu?menuId="+menuId,function(data){
        			var obj = jQuery.parseJSON(data);
                	if(obj.resp_code == "success"){
                		var topMenus = jQuery.parseJSON(obj.resp_body).topMenu;
                		var resFunc =  jQuery.parseJSON(obj.resp_body).resFunc;
                		$("#menuName").val(resFunc.name);
                		$("#editMenuId").val(resFunc.id);
                		$("#menuMethod").find('option[value="'+resFunc.method+'"]').attr("selected",true);
                		$("#menuNumber").val(resFunc.sortid);
                		$("#menuRemark").val(resFunc.remark);
                		$("#menuURL").val(resFunc.url);
                		$("#menuRoute").val(resFunc.outerAddr);
                		$('input[name=menuIcon][value="'+resFunc.icon+'"]').attr("checked",true);
                		var parentId= resFunc.parentid;
                		if(parentId == null || parentId == ""){
                			$("#rootEditMenu").attr("checked","checked");
                		}
                		else{
                			$("#edit"+parentId).attr("checked","checked");
                		}
                		$("#selectMenuList").hide();
                		$("#editMenu").show();
                	};
        		});
        	});
        	
        	$("#cancelEdit").click(function(){
        		$("#selectMenuList").show();
        		$("#editMenu").hide();
        		$("#topMenuRedio").html('');
        		window.location.reload();
        	});
        	
        }
    };
}();