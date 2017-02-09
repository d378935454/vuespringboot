<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="ppd"  uri="/WEB-INF/tld/ppd.tld" %> 
var FormWizard = function () {
    return {
        init: function () {
            
			/*-----------------------------------------------------------------------------------*/
			/*	Show country list in Uniform style
			/*-----------------------------------------------------------------------------------*/
            var apiEditForm = $('#editForm');
            var alert_error = $('.alert-danger', apiEditForm);
            var alert_success = $('.alert-success', apiEditForm);
			/*-----------------------------------------------------------------------------------*/
			/*	Validate the form elements
			/*-----------------------------------------------------------------------------------*/
            apiEditForm.validate({
                doNotHideMessage: true,
				errorClass: 'error-span',
                errorElement: 'span',
                rules: {
                    /* Create Account */
                	descName: {
                        required: true,
                        maxlength: 200
                    },
                    mark: {
                    	required: true
                    }
                },
                submitHandler: function(form){
                	alert_success.hide();
                    alert_error.hide();
                    
                    $("#editSave").button('loading');
            		$(form).ajaxSubmit({
                        type:"post",
                        url:$context.ctx+"/system/api/upt_api",
                        success: function(data,status){
                        	$("#editSave").button('reset');
                        	var obj = jQuery.parseJSON(data);
                        	if(obj.resp_code == "error"){
                        		$("#api_message").empty().html(obj.resp_msg);
                        		alert_error.show();                       		
                        	}
                        	if(obj.resp_code == "success"){
                        		$("#api_success_message").empty().html(obj.resp_msg);
                        		alert_success.show();    
                        		setTimeout(function () { 
									$("#selectUserList").show();
    								$("#searchBox").show();
    								$("#editUser").hide();
        							$('#myTable').DataTable().fnDraw();
							    }, 2000);                     		
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
            /*-----------------------------------------------------------------------------------*/
			/*	Initialize Bootstrap Wizard
			/*-----------------------------------------------------------------------------------*/
           
            var type = $("#e3").val();
        	var name = $("#e6").val();
 
        	showTable(type,name);
        	$("#search").click(function() {
        		var type = $("#e3").val();
        		var name = $("#e6").val();
    
        		showTable(type,name);
        	});
        	
        	$("#cancelEdit").click(function(){
        		$("#selectUserList").show();
        		$("#searchBox").show();
        		$("#editUser").hide();
        		$('#myTable').DataTable().fnDraw();
        	});
        	
        	$("#returnBtn").click(function(){
        		$("#searchBox").show();
        		$("#dataBox").show();
        		$("#apiBox").hide();
        	});
        }
    };
}();



function uptFreezeStatus(obj){
	
	var name = obj.val();
	var type = obj.attr("chk");
	var msg = '';
	if(type == 'S'){
		msg = '确认启用本接口?';
	}else{
		msg = '确认停用本接口?';
	}
	$.confirm(msg, function (s) {
		if(s){
				$.post($context.ctx+"/system/api/api_freeze?name="+name+"&type="+type,function(data){
					var obj = jQuery.parseJSON(data);
			    	if(obj.resp_code == "success"){
			    		$('#myTable').DataTable().fnDraw();
			    		jAlert("操作成功","提示");
			    	}else{
			    	    jAlert("操作失败","提示");
			    	};
				});
		}
	});
}

function editApiMethod(obj){
	var name = obj.val();
	var apiEditForm = $('#editForm');
    var alert_error = $('.alert-danger', apiEditForm);
    var alert_success = $('.alert-success', apiEditForm);
    alert_success.hide();
    alert_error.hide();
    $("#descName").closest('.form-group').removeClass('has-error');
    $("#descName").closest('.form-group').removeClass('has-success');
	$("span[for='descName']").addClass('valid').html("");
	$("#mark").closest('.form-group').removeClass('has-error');
	$("#mark").closest('.form-group').removeClass('has-success');
	$("span[for='mark']").addClass('valid').html("");
	$.post($context.ctx+"/system/api/select_api?name="+name,function(data){
		var obj = jQuery.parseJSON(data);
    	if(obj.resp_code == "success"){
    		var body  = jQuery.parseJSON(obj.resp_body);

    		$("#id").val(name);
    		$("#descName").val(body.descName);
    		$("#mark").val(body.mark);
   			
    		$("#selectUserList").hide();
    		$("#searchBox").hide();
    		$("#editUser").show();
    	};
	});

	
}


function getApiDetail(obj){
    var name = obj.val();
    if(name == ''){
    	name = obj.attr("value");
    }
   // alert(name);
    //return ;
	$.post($context.ctx+"/system/api/api_detail?name="+name,function(data){
		var obj = jQuery.parseJSON(data);
    	if(obj.resp_code == "success"){
    		var body  = jQuery.parseJSON(obj.resp_body);
    		$("#preApi").attr('value',body.pre);
    		$("#nextApi").attr('value',body.next);
    		$("#curName").empty().html(body.cur);
    		$("#curDesc").empty().html(body.descName);
    		$("#curMark").empty().html(body.mark);
    		$("#curRequestParams").empty().html(body.requestParams);
    		$("#curRequest").empty().html(body.requestParams);    		
    		try{
    			var j = new JSONFormat(JSON && JSON.parse ? JSON.parse(body.responseParams) : eval('(' + body.responseParams + ')'));
        		$("#curResponseParams").empty().html(j.toString());
    		}catch (e){
    			$("#curResponseParams").empty().html(body.responseParams);
            }
    		createIOTable(body.list, body.respCodeList);
    		$("#searchBox").hide();
    		$("#dataBox").hide();
    		$("#apiBox").show();
    		if (body.name == "mj.jd.account.detail"){
    			$("#jdAccount").show();
    		} else {
    			$("#jdAccount").hide();
    		}
    		if (body.name == "mj.bill.data.get"){
    			$("#billData").show();
    		} else {
    			$("#billData").hide();
    		}
    		if (body.name == "mj.company.query"){
    			$("#company").show();
    		} else {
    			$("#company").hide();
    		}
    		if (body.name == "mj.taobao.item.query"){
    			$("#taobaoItem").show();
    		} else {
    			$("#taobaoItem").hide();
    		}
    		if (body.name == "mj.taobao.item.query"){
    			$("#taobaoItem").show();
    		} else {
    			$("#taobaoItem").hide();
    		}
    		if (body.name == "mj.personinvestment.query"){
    			$("#personinvestment").show();
    		} else {
    			$("#personinvestment").hide();
    		}
    		if (body.name == "mj.personinvestment.query"){
    			$("#personinvestment").show();
    		} else {
    			$("#personinvestment").hide();
    		}
    		if (body.name == "mj.court.query"){
    			$("#court").show();
    		} else {
    			$("#court").hide();
    		}
    		if (body.name == "mj.personalreport.query"){
    			$("#personalreport").show();
    		} else {
    			$("#personalreport").hide();
    		}
    		if (body.name == "mj.chsi.query"){
    			$("#chsi").show();
    		} else {
    			$("#chsi").hide();
    		}
    		if (body.name == "mj.taobao.account.detail"){
    			$("#taobaoAccount").show();
    		} else {
    			$("#taobaoAccount").hide();
    		}
    		if (body.name == "mj.nine.getwwwinfo"){
    			$("#nineDetail").show();
    		} else {
    			$("#nineDetail").hide();
    		}
    		if (body.name == "mj.report.query"){
    			$("#reportDetail").show();
    		} else {
    			$("#reportDetail").hide();
    		}
    	};
	});
	
}


function createIOTable(tableList, tableRespcodeList){
	 var cBegin = '<table class="table table-bordered table-striped"><thead><tr><th style="width: 100px;">名称</th><th style="width: 150px;">code</th>'+
	                    '<th style="width: 250px;">是否必须</th><th>描述</th></tr></thead><tbody>';
	 var cEnd = '</tbody></table>';
	 var respcodeBegin = '<table class="table table-bordered table-striped"><thead><tr><th style="width: 100px;">名称</th><th style="width: 150px;">code</th>'+
     '<th style="width: 250px;">描述</th><th >解决方案</th></tr></thead><tbody>';
	 var respcodeEnd = '</tbody></table>';
     var inStr = '';
     var outStr = '';
     var inputTable = '';
     var outputTable = '';
     var respcodeStr = '';
     var respcodeTable = '';
     for(i=0;i<tableList.length;i++){
    	 var item = tableList[i];
    	 if(item.paramType == 'I'){
    		 inStr  += '<tr><td>'+item.paramName+'</td><td><code>'+item.paramCode+'</code></td><td>'+item.paramIsnvl+'</td><td>'+item.paramDesc+'</td></tr>';
    	 }else{
    		 outStr  += '<tr><td>'+item.paramName+'</td><td><code>'+item.paramCode+'</code></td><td>'+item.paramIsnvl+'</td><td>'+item.paramDesc+'</td></tr>';
    	 }
     }
     for (var i=0;i<tableRespcodeList.length;i++){
    	var item = tableRespcodeList[i];
    	respcodeStr  += '<tr><td>'+item.msg+'</td><td><code>'+item.code+'</code></td><td>'+item.name+'</td><td>'+item.solution+'</td></tr>';
     }
     if(inStr != ''){
    	 inputTable += cBegin+inStr+cEnd; 
     }else{
    	 inputTable = '<p class="text-danger">暂无接口输入参数相关信息！</p>';
     }
     
     if(outStr != ''){
    	 outputTable += cBegin+outStr+cEnd; 
     }else{
    	 outputTable = '<p class="text-danger">暂无接口输出参数相关信息！</p>';
     }
     if(respcodeStr != ''){
    	respcodeTable += respcodeBegin+respcodeStr+respcodeEnd; 
     }else{
    	respcodeTable = '<p class="text-danger">暂无系统响应码相关信息！</p>';
     }
     
     $("#inputParam").empty().html(inputTable);
     $("#outputParam").empty().html(outputTable);
     $("#respcodeParam").empty().html(respcodeTable);
	
}

function showTable(type,name) {
	
	$('#myTable').DataTable().fnDestroy();
	//if (oTable != null) { oTable.fnDestroy(); };
	$('#myTable').dataTable({
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
			"sProcessing" : "正在加载数据......",
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
        "sAjaxSource": $context.ctx + "/system/api/show_api?name="+name+"&type="+type,//如果从服务器端加载数据，这个属性用语指定加载的路径 
        /*"aaSorting": [[ 0, "desc" ]],//设置第3个元素为默认排序  */ 
       "aoColumns" : [  {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "name",
            "aTargets" : [ 0 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "descName",
            "aTargets" : [ 1 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "mark",
            "aTargets" : [ 2 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "type",
            "aTargets" : [ 3 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "type",
            "aTargets" : [ 4 ]
        },],
        "aoColumnDefs" : [ {
            sDefaultContent : '',
            aTargets : [ '_all' ]
        } ],
       "fnRowCallback": function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 
    	   if (aData.type=='S'){
    		   $('td:eq(3)', nRow).html("<span>可用</span>");
    	   } else if (aData.type=='F'){
    		   $('td:eq(3)', nRow).html("<span><font color='red'>不可用</font></span>");
    	   }
    	   
    	   var temp = '<ppd:auth ctype="edit" menuid="41" parentid="${parentId }"><button value="'+aData.name+'"  class="btn btn-xs btn-info btn-edit" onclick="javascript:editApiMethod($(this));">编辑</button></ppd:auth>&nbsp;';
    	   if (aData.type == 'S'){
    		   temp += '<ppd:auth ctype="edit" menuid="42" parentid="${parentId }"><button value="'+aData.name+'" chk="F" class="btn btn-xs btn-info btn-freeze" onclick="javascript:uptFreezeStatus($(this));" >停用</button></ppd:auth>&nbsp;';
    	   }else if(aData.type == 'F'){
    		   temp +=  '<ppd:auth ctype="edit" menuid="42" parentid="${parentId }"><button value="'+aData.name+'" chk="S" class="btn btn-xs btn-info btn-unfreeze" onclick="javascript:uptFreezeStatus($(this));" >启用</button></ppd:auth>&nbsp;';
    	   }
    	   temp += '<ppd:auth ctype="edit" menuid="43" parentid="${parentId }"><button value="'+aData.name+'" class="btn btn-xs btn-info btn-detail" onclick="javascript:getApiDetail($(this));" >详情</button></ppd:auth>';
    	   $('td:eq(4)', nRow).html(temp);

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