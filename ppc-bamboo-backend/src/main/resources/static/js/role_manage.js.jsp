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
                        url:$context.ctx+"/system/admin/save_role",
                        success: function(data,status){
                        	$("#sub").button('reset');
                        	var obj = jQuery.parseJSON(data);
                        	if(obj.resp_code == "error"){
                        		$(wizform).find(".tip_message").html(obj.resp_msg);
                        		add_alert_error.show();
                        	}
                        	if(obj.resp_code == "success"){
                        		$(wizform).find(".tip_success_message").html(obj.resp_msg);                        		
                        		document.getElementById("wizForm").reset(); 
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
                        url:$context.ctx+"/system/admin/edit_role",
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
									$("#selList").show();
        							$("#editList").hide();
        							$("#editTable").html('');
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
            
         
            /*-----------------------------------------------------------------------------------*/
			/*	Initialize Bootstrap Wizard
			/*-----------------------------------------------------------------------------------*/
            //编辑
        	$(".btn-edit").click(function(){
        		var roleId = $(this).val();
        		editRole(roleId);
        	});
        	
        	function editRole(roleId){
	        	$.post($context.ctx+"/system/admin/select_role?roleId="+roleId,function(data){
	        			var obj = jQuery.parseJSON(data);
	                	if(obj.resp_code == "success"){
	                		var role = jQuery.parseJSON(obj.resp_body).role;
               				var roleFunc =  jQuery.parseJSON(obj.resp_body).roleFunc;
	                		if(roleFunc){
	                			for(var i=0; i < roleFunc.length; i++){
	                    			var roleFuncs = roleFunc[i];
	                    			var funcId = roleFuncs.func.id;
	                    			if(roleFuncs.isActive == 1){
	                    				$("#edit"+funcId).attr("checked", true);
	                    			}
	                    		};
	                		}
	                		$("#editRoleId").val(role.id);
	                		$("#editRoleName").val(role.name);
	                		$("#editRoleRemark").val(role.remark);
	                		$("#selList").hide();
	                		$("#editList").show();
	                	};
	        		});
        	}
        	
        	$("#cancelEdit").click(function(){
        		$("#selList").show();
        		$("#editList").hide();
        		$("#editTable").html('');
        		window.location.reload();
        	});
        	
        	$("#lookRole").click(function(){
        		window.location.reload();
        	});
        	
        	getFunds();
        	 $("#editAllfunc").click(function(){
        		 $("#editFuncList").find(".func").prop("checked", true);
        		 $("#editNOfunc").removeAttr("checked");
     		 });
        	 $("#addAllfunc").click(function(){
        		 $("#addFuncList").find(".func").prop("checked", true);
        		 $("#addNOfunc").removeAttr("checked");
     		 });
        	 $("#editNOfunc").click(function(){
        		 $("#editFuncList").find(".func").removeAttr("checked");
        		 $("#editAllfunc").removeAttr("checked");
     		 });
        	 $("#addNOfunc").click(function(){
        		 $("#addFuncList").find(".func").removeAttr("checked");
        		 $("#addAllfunc").removeAttr("checked");
     		 });
        	function getFunds(){
        		$.post($context.ctx+"/system/admin/getFuncList",function(data){
        			var obj = jQuery.parseJSON(data);
                 	if(obj.resp_code == "success"){
                		var funcList = jQuery.parseJSON(obj.resp_body);
                		var addFuncHtml = "";
                		//var fundHtml = "";
                		addFuncHtml = addFuncHtml + "<ul style='list-style-type:none'>";
                		for(var i=0; i < funcList.length; i++){
                			var func = funcList[i];
                			 if (func.parentid == null || func.parentid == "") {
                	            	var ss = "<input type='checkbox' name='addfunc' class='func' id='add"+func.id+"' value='"+func.id+"'> "+func.name;
                	            	addFuncHtml = addFuncHtml + "<li>"+ss;
                	              var shtml =  build(func,funcList);
                	              addFuncHtml = addFuncHtml + shtml+"</li>";
                	         }
                		}
                		addFuncHtml = addFuncHtml + "</ul>";
                		 $("#addFuncList").append(addFuncHtml);
                		 $("#addFuncList").find(".func").click(function(){
                		 	 var ischeck =  $(this).is(":checked");
                			 if(!ischeck){
                				 $(this).next("ul").find(".func").removeAttr("checked");
                			 }
                			 else{
                			 	 $(this).next("ul").find(".func").prop("checked", true);
                			 }
                			 var selMenu = $(this).parent().children(".func:checked").length;
                			 if(selMenu >0){
                			 	 var firstChilds = $(this).parent().children(".func");
                			 	 var firstChild = firstChilds[0];
                			 	 if($(this).val() == $(firstChild).val()){
                			 	 	if($(this).is(":checked")){
                			 	 		 $(this).parents("ul").prev(".func").prop("checked", true);
                			 	 	}else{
                			 	 		$(this).parent().children(".func").removeAttr("checked");
                			 	 		cancelCheckParent($(this));
                			 	 	}
                			 	 }
                			 	 else{
                			 	 	$(firstChild).prop("checked", true);
                			 	 	 $(this).parents("ul").prev(".func").prop("checked", true);
                			 	 }
                			 }else{
                			 		cancelCheckParent($(this));
                			 }
                		 });
                		 
                		 var editFuncHtml = "";
                 		//var fundHtml = "";
                		 editFuncHtml = editFuncHtml + "<ul style='list-style-type:none'>";
                 		for(var i=0; i< funcList.length; i++){
                 			var func = funcList[i];
                 			 if (func.parentid == null || func.parentid == "") {
                 	            	var ss = "<input type='checkbox' name='editfunc' class='func' id='edit"+func.id+"' value='"+func.id+"'> "+func.name;
                 	            	editFuncHtml = editFuncHtml + "<li>"+ss;
                 	              var shtml =  editBuild(func,funcList);
                 	             editFuncHtml = editFuncHtml + shtml+"</li>";
                 	         }
                 		}
                 		editFuncHtml = editFuncHtml + "</ul>";
                 		 $("#editFuncList").append(editFuncHtml);
                 		 $("#editFuncList").find(".func").click(function(){
                 		 	 var ischeck =  $(this).is(":checked");
                			 if(!ischeck){
                				 $(this).next("ul").find(".func").removeAttr("checked");
                			 }
                			  else{
                			 	 $(this).next("ul").find(".func").prop("checked", true);
                			 }
                			 var selMenu = $(this).parent().children(".func:checked").length;
                			if(selMenu >0){
                			 	 var firstChilds = $(this).parent().children(".func");
                			 	 var firstChild = firstChilds[0];
                			 	 if($(this).val() == $(firstChild).val()){
                			 	 	if($(this).is(":checked")){
                			 	 		 $(this).parents("ul").prev(".func").prop("checked", true);
                			 	 	}else{
                			 	 		$(this).parent().children(".func").removeAttr("checked");
                			 	 		cancelCheckParent($(this));
                			 	 	}
                			 	 }
                			 	 else{
                			 	 	$(firstChild).prop("checked", true);
                			 	 	 $(this).parents("ul").prev(".func").prop("checked", true);
                			 	 }
                			 }else{
                			 		cancelCheckParent($(this));
                			 }
                			 
                		 });
                	};
        		});
        	}
        	
        	function cancelCheckParent(currnode){
        		var pareUrl =  $(currnode).parents("ul:first");
       			if($(pareUrl).prev(".func").length > 0){
       				var parentCheck = $(pareUrl).find(".func:checked").length;
	        		if(parentCheck < 1){
	        		 	 $(pareUrl).prev(".func").removeAttr("checked");
	        		 	cancelCheckParent($(pareUrl).prev(".func"));
	        		}
       			}
        	
        	}
        	function build(func,funcList){
        		var addFuncHtml = "";
        		  var children = getChildren(func,funcList);  
        	        if (children.length > 0) {
        	        	if(func.level < 5){
        	        		addFuncHtml = addFuncHtml + "<ul style='list-style-type:none'>";
        	        		for(var i=0; i< children.length; i++){
        	        			var  child = children[i];
    	                		var ss = "<input type='checkbox' name='addfunc' class='func' id='add"+child.id+"' value='"+child.id+"'> "+child.name;
    	                		if(child.level < 5){
    	                			addFuncHtml = addFuncHtml + "<li>"+ss;
	        	                      var shtml =  build(child,funcList);
	        	                      addFuncHtml = addFuncHtml + shtml+"</li>";
    	                		}
    	                		else{
    	                			addFuncHtml = addFuncHtml +ss+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
	        	                      var shtml =  build(child,funcList);
	        	                      addFuncHtml = addFuncHtml + shtml;
    	                		}
        	                 }  
        	        		addFuncHtml = addFuncHtml + "</ul>";
        	        	}
        	        	else{
        	        		for(var i=0; i< children.length; i++){
        	        			var  child = children[i];
        	        			var ss = "<input type='checkbox' name='addfunc' class='func' id='add"+child.id+"' value='"+child.id+"'> "+child.name;
        	        			addFuncHtml = addFuncHtml +ss+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
        	        			var shtml =  build(child,funcList);
        	        			addFuncHtml = addFuncHtml + shtml;
        	                }  
        	        	}
        	          
        	        } 
        	   return addFuncHtml;
        	}
        	
        	function editBuild(func,funcList){
        		var editFuncHtml = "";
        		  var children = getChildren(func,funcList);  
        	        if (children.length > 0) {
        	        	if(func.level < 5){
        	        		editFuncHtml = editFuncHtml + "<ul style='list-style-type:none'>";
        	        		for(var i=0; i< children.length; i++){
        	        			var  child = children[i];
    	                		var ss = "<input type='checkbox' name='editfunc' class='func' id='edit"+child.id+"' value='"+child.id+"'> "+child.name;
    	                		if(child.level < 5){
    	                			editFuncHtml = editFuncHtml + "<li>"+ss;
	        	                      var shtml =  editBuild(child,funcList);
	        	                      editFuncHtml = editFuncHtml + shtml+"</li>";
    	                		}
    	                		else{
    	                			editFuncHtml = editFuncHtml +ss+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
	        	                      var shtml =  editBuild(child,funcList);
	        	                      editFuncHtml = editFuncHtml + shtml;
    	                		}
        	                 }  
        	        		editFuncHtml = editFuncHtml + "</ul>";
        	        	}
        	        	else{
        	        		for(var i=0; i< children.length; i++){
        	        			var  child = children[i];
        	        			var ss = "<input type='checkbox' name='editfunc' class='func' id='edit"+child.id+"' value='"+child.id+"'> "+child.name;
        	        			editFuncHtml = editFuncHtml +ss+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
        	        			var shtml =  editBuild(child,funcList);
        	        			editFuncHtml = editFuncHtml + shtml;
        	                }  
        	        	}
        	          
        	        } 
        	   return editFuncHtml;
        	}
        	
        	function getChildren(func,funcList){  
        		var children = new Array(); 
                var id = func.id;  
                for(var i=0; i< funcList.length; i++){
                	var child = funcList[i];
                    if (id == child.parentid) {  
                    	children.push(child);
                    }
                } 
                return children;  
            }
        	
        	//查询
        	$("#search").click(function(){
        		Load('正在运行，请稍后...');
        		var searchRoleName = encodeURIComponent(encodeURIComponent($("#searchRoleName").val()));
        		$.post($context.ctx+"/system/admin/queryRoleList?roleName="+searchRoleName,function(data){
        			var obj = jQuery.parseJSON(data);
                	if(obj.resp_code == "success"){
                		var roleList = jQuery.parseJSON(obj.resp_body);
                		if(roleList){
                			var appendHtml = "";
                			for(var i = 0; i < roleList.length; i++){
                    			var roleInfo  = roleList[i];
                    			var innerHtml = " <tr><td>"+i+"</td><td>"+roleInfo.name+"</td><td>"+roleInfo.remark+"</td><td>";
                    			innerHtml = innerHtml +"<ppd:auth ctype='edit' menuid='20' parentid='${parentId}'><button value='"+roleInfo.id+"' class='btn btn-xs btn-info btn-edit'>编辑</button>&nbsp;</ppd:auth>";
                    			innerHtml = innerHtml +"&nbsp;&nbsp;&nbsp;&nbsp;<button value='"+roleInfo.id+"' class='btn btn-xs btn-info func-down roleFuncA'>展开</button>";
                    			innerHtml = innerHtml +"</td></tr>";
                    			innerHtml = innerHtml +"<tr><td style='display:none' class='roleFuncTd' colspan='5'><div class='roleFuncDiv'></div></td></tr>";
                    		appendHtml = appendHtml + innerHtml;
                    		}
                			$("#roleList").html('');
                			$("#roleList").append(appendHtml);
                			$("#roleList").find(".btn-edit").click(function(){
                				var roleId = $(this).val();
        						editRole(roleId);
                			 });
                			$(".roleFuncA").click(function(){
                				roleFuncA(this);
                			});
                		}
                		setTimeout(function () { 
							dispalyLoad(); 
					    }, 1000);
                	}else{
                		setTimeout(function () { 
							dispalyLoad(); 
					    }, 1000);
                	}
        		});
        	});
        	
        	function showAppendFunc(roleId){
        		$.post($context.ctx+"/system/admin/select_role?roleId="+roleId,function(data){
        			var obj = jQuery.parseJSON(data);
                	if(obj.resp_code == "success"){
                			var role = jQuery.parseJSON(obj.resp_body).role;
	                		var funcList =  jQuery.parseJSON(obj.resp_body).roleFunc;
	                			var addFuncHtml = "";
	                		if(funcList){
		                		addFuncHtml = addFuncHtml + "<ul style='list-style-type:none'>";
		                		for(var i=0; i < funcList.length; i++){
		                			var func = funcList[i].func;
		                			 if (func.parentid == null || func.parentid == "") {
		                	            	addFuncHtml = addFuncHtml + "<li>"+func.name;
		                	              var shtml =  buildRole(func,funcList);
		                	              addFuncHtml = addFuncHtml + shtml+"</li>";
		                	         }
		                		}
		                		addFuncHtml = addFuncHtml + "</ul>";
		                		appendNode.append(addFuncHtml);
	                		}else{
		                		addFuncHtml = addFuncHtml + "<ul style='list-style-type:none'>";
		                			addFuncHtml = addFuncHtml + "<li>无菜单功能权限</li>"
	                		}
	                		return addFuncHtml;
                	}else{
                	}
        	});
        	}
        	
        	function roleFuncA(ss){
        		var isclass = $(ss).hasClass("func-up");
        		var appendNode =  $(ss).parents("tr:first").next("tr").find(".roleFuncDiv");
        		var appendNodeTd =  $(ss).parents("tr:first").next("tr").find(".roleFuncTd");
        		if(isclass){
        			$(appendNodeTd).hide();
        			$(ss).html('展开');
        			$(ss).removeClass("func-up");
        			$(ss).addClass("func-dowm");
        			return;
        		}
        		var thisn = $(ss);
        		if($(appendNode).html() != ''){
        			$(".roleFuncTd").hide();	
        			$(".roleFuncA").removeClass("func-up");
        			$(".roleFuncA").addClass("func-dowm");
        			$(".roleFuncA").html('展开');
        			$(appendNodeTd).show();
        			thisn.removeClass("func-dowm");
        			thisn.addClass("func-up");
        			thisn.html('隐藏');
        		}
        		var roleId = $(ss).val();
        		$.post($context.ctx+"/system/admin/select_role?roleId="+roleId,function(data){
        			var obj = jQuery.parseJSON(data);
                	if(obj.resp_code == "success"){
                			var role = jQuery.parseJSON(obj.resp_body).role;
	                		var funcList =  jQuery.parseJSON(obj.resp_body).roleFunc;
	                		if(funcList){
	                				var addFuncHtml = "";
		                		addFuncHtml = addFuncHtml + "<ul style='list-style-type:none'>";
		                		for(var i=0; i < funcList.length; i++){
		                			var func = funcList[i].func;
		                			 if (func.parentid == null || func.parentid == "") {
		                	            	addFuncHtml = addFuncHtml + "<li>"+func.name;
		                	              var shtml =  buildRole(func,funcList);
		                	              addFuncHtml = addFuncHtml + shtml+"</li>";
		                	         }
		                		}
		                		addFuncHtml = addFuncHtml + "</ul>";
		                		appendNode.html('');
		                		$(".roleFuncTd").hide();	
        						$(".roleFuncA").removeClass("func-up");
        						$(".roleFuncA").addClass("func-dowm");
        						$(".roleFuncA").html('展开');
        						thisn.removeClass("func-dowm");
        						thisn.addClass("func-up");
        						thisn.html('隐藏');
		                		appendNode.append(addFuncHtml);
		                		$(appendNodeTd).show();
	                		}else{
	                			appendNode.html('');
		                		$(".roleFuncTd").hide();	
        						$(".roleFuncA").removeClass("func-up");
        						$(".roleFuncA").addClass("func-dowm");
        						$(".roleFuncA").html('展开');
        						thisn.removeClass("func-dowm");
        						thisn.addClass("func-up");
        						thisn.html('隐藏');
        						var addFuncHtml = "";
		                		addFuncHtml = addFuncHtml + "<ul style='list-style-type:none'>";
		                			addFuncHtml = addFuncHtml + "<li>无菜单功能权限</li>"
		                		appendNode.append(addFuncHtml);
		                		$(appendNodeTd).show();
	                		}
	                		
                	}else{
                	}
        	});
        	}
        	//查询角色菜单
        	$(".roleFuncA").click(function(){
        		roleFuncA(this);
        	});
        		
        		function buildRole(func,funcList){
        			var addFuncHtml = "";
        		 	 var children = getChildrenForRoleFunc(func,funcList);  
        	        if (children.length > 0) {
        	        	if(func.level < 5){
        	        		addFuncHtml = addFuncHtml + "<ul style='list-style-type:none'>";
        	        		for(var i=0; i< children.length; i++){
        	        			var  child = children[i];
    	                		if(child.level < 5){
    	                			addFuncHtml = addFuncHtml + "<li>"+child.name;
	        	                      var shtml =  buildRole(child,funcList);
	        	                      addFuncHtml = addFuncHtml + shtml+"</li>";
    	                		}
    	                		else{
    	                			addFuncHtml = addFuncHtml +child.name+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
	        	                      var shtml =  buildRole(child,funcList);
	        	                      addFuncHtml = addFuncHtml + shtml;
    	                		}
        	                 }  
        	        		addFuncHtml = addFuncHtml + "</ul>";
        	        	}
        	        	else{
        	        		for(var i=0; i< children.length; i++){
        	        			var  child = children[i];
        	        			addFuncHtml = addFuncHtml +child.name+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
        	        			var shtml =  buildRole(child,funcList);
        	        			addFuncHtml = addFuncHtml + shtml;
        	                }  
        	        	}
        	          
        	        } 
        	  	 return addFuncHtml;
        		}
        		
        	function getChildrenForRoleFunc(func,funcList){  
        		var children = new Array(); 
                var id = func.id;  
                for(var i=0; i< funcList.length; i++){
                	var child = funcList[i].func;
                	if(child){
	                	  if (id == child.parentid) {  
	                    	children.push(child);
	                    }
                	}
                } 
                return children;  
            }
        }
    
    };
}();