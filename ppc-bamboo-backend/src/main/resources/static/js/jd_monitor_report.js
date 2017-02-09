var datatables;
$(function() {
	function initTable(){
		datatables = $('#myTable').DataTable({
	        "bPaginate": true,
	        "bLengthChange": false,
	        "iDisplayLength": 10,
	        "bFilter": false, 
	        "bStateSave": false,       
	        "bInfo": true, 
	        "bDestroy":true,
	        "sPaginationType": "full_numbers",
	        "bRetrieve": true,
	        "bAutoWidth": true,
	        "bProcessing": false,              
	        "oLanguage": {      	
	        	"sLengthMenu": "每页显示 _MENU_条",  
	        	"sZeroRecords": "没有找到符合条件的数据",
	        	"sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",  
	        	"sInfoEmpty": "没有记录",  
	        	"sInfoFiltered": "(从 _MAX_ 条记录中过滤)",  
	        	"sSearch": "搜索：",  
	        	"oPaginate": {  
		        	"sFirst": "首页",  
		        	"sPrevious": "前一页",  
		        	"sNext": "后一页",  
		        	"sLast": "尾页" 
	        	}
	        },     
	        "bServerSide": true,//是否从服务器加载数据
	        "sAjaxSource": $context.ctx + "/system/jdmonitor/dataSearch?start="+$("#start").val()+"&end="+$("#end").val()+"&appkey="+$("#e2").val()
									+"&serial_number="+$("#serial_number").val()+"&name="+encodeURIComponent($("#name").val())+"&idNumber="+$("#idNumber").val()+"&jdAccount="
									+encodeURIComponent($("#jdAccount").val())+"&crawlerStatus="+$("#e4").val()+"&creditResult="+$("#e3").val()+"&pushStatus="+$("#e6").val(),
	       "aoColumns" : [ {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "company",
	            "aTargets" : [ 0 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "serial_number",
	            "aTargets" : [ 1 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            //"mData" : "querytimestamp",
	            "mData" : "querytime",
	            "aTargets" : [ 2 ]
	        }, {
	            "bVisible" : true,
	            "mData" : "name",
	            "aTargets" : [ 3 ]
	        },{
	            "bVisible" : true,
	            "mData" : "idnumber",
	            "aTargets" : [ 4 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "username",
	            "aTargets" : [ 5 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "authresult",
	            "aTargets" : [ 6 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "resultdetail",
	            "aTargets" : [ 7 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "collectstatus",
	            "aTargets" : [ 8 ]
	        },{
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "pushstatus",
	            "aTargets" : [ 9 ]
	        }, {
	            "bVisible" : true,
	            "bSortable": false,
	            "mData" : "action",
	            "fnRender": function (obj) {
	            	var serial_number = obj.aData["serial_number"];//ID 
	            	//var querytimestamp = obj.aData["querytimestamp"];
	            	var querytimestamp = obj.aData["querytime"];
	            	var name = obj.aData["name"];	            	
	            	var idnumber = obj.aData["idnumber"];
	            	var username = obj.aData["username"];	            	
	            	var authresult = obj.aData["authresult"];	            	
	            	var resultdetail = obj.aData["resultdetail"];
	            	var collectstatus = obj.aData["collectstatus"];
	            	var pushstatus = obj.aData["pushstatus"];
	            	var company=obj.aData["company"];
	            	var result = '<a target="_blank" class="btn btn-xs btn-info btn-detail" style="margin-left:6px;" href="'+$context.ctx+'/system/jdmonitor/jd_monitor_detail?serial_number='+serial_number+
	            	'&company='+company+'&querytimestamp='+querytimestamp+'&name='+name+'&idnumber='+idnumber+'&username='+username+
	            	'&authresult='+authresult+'&resultdetail='+resultdetail+'&collectstatus='+collectstatus+'&pushstatus='+pushstatus+'">详情</a>'; 
	            	return result;
	             }
	        },],
	        "aoColumnDefs" : [ {
	            sDefaultContent : '',
	            aTargets : [ '_all' ]
	        } ],
	       "fnRowCallback": function(nRow, aData, iDisplayIndex) {
	    	   console.info(nRow);
	    	   if (aData.authresult=='1'){
	    		   $('td:eq(6)', nRow).html("<span>成功</span>");
	    	   } else if (aData.authresult=='2') {
	    		   $('td:eq(6)', nRow).html("<span>失败</span>");
	    	   }else {
	    		   $('td:eq(6)', nRow).html("<span>未认证</span>");
	    	   }
	    	   
	    	   if (aData.resultdetail=='100'){
	    		   $('td:eq(7)', nRow).html("<span>-</span>");
	    	   } else if (aData.resultdetail=='501') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，账户名不存在，请重新输入</span>");
	    	   }else if (aData.resultdetail=='502') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，账户名与密码不匹配，请重新输入</span>");
	    	   }else if (aData.resultdetail=='503') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，帐号因安全原因暂时被封锁</span>");
	    	   }else if (aData.resultdetail=='511') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，验证不通过，帐号请重置密码,您的账号存在被盗风险，为保障您的账户安全，请根据以下提示将密码重置后再登录。</span>");
	    	   }else if (aData.resultdetail=='512') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，帐号存在风险，因您的账户存在风险，需进一步校验您的信息以提升您的安全等级</span>");
	    	   }else if (aData.resultdetail=='601') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，连接代理服务器异常</span>");
	    	   }else if (aData.resultdetail=='602') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，SSL证书报错</span>");
	    	   }else if (aData.resultdetail=='603') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，京东登陆页面访问异常</span>");
	    	   }else if (aData.resultdetail=='604') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，请刷新重新提交</span>");
	    	   }else if (aData.resultdetail=='611') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，验证码获取失败</span>");
	    	   }else if (aData.resultdetail=='612') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，验证码解析失败</span>");
	    	   }else if (aData.resultdetail=='613') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，验证码错误</span>");
	    	   }else if (aData.resultdetail=='408') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，请在京东绑定手机号再验证</span>");
	    	   }else if (aData.resultdetail=='412') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，请重置密码</span>");
	    	   }else if (aData.resultdetail=='700') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，登陆失败，未知错误</span>");
	    	   }else if (aData.resultdetail=='403') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，其他错误异常</span>");
	    	   }else if (aData.resultdetail=='401') {
	    		   $('td:eq(7)', nRow).html("<span>验证不通过，超时异常</span>");
	    	   }else{
	    		   $('td:eq(7)', nRow).html("<span>未知错误</span>");
	    	   }
	    	   
	    	   if (aData.collectstatus == '1'){
	    		   $('td:eq(8)', nRow).html("<span>成功</span>");
	    	   } else  if (aData.collectstatus == '2'){
	    		   $('td:eq(8)', nRow).html("<span>失败</span>");
	    	   } else {
	    		   $('td:eq(8)', nRow).html("<span>未采集</span>");
	    	   }
	    	   
	    	   if (aData.pushstatus == '1'){
	    		   $('td:eq(9)', nRow).html("<span>成功</span>");
	    	   } else if (aData.pushstatus == '2'){
	    		   $('td:eq(9)', nRow).html("<span>失败</span>");
	    	   } else {
	    		   $('td:eq(9)', nRow).html("<span>未推送</span>");
	    	   }
	       },
	       "fnPreDrawCallback": function(){
	    	   Load('正在运行，请稍后...');
	       },
	       "fnInfoCallback": function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {
	    	   if(iStart == 1 && iEnd == 0){
	    		   iStart = 0;
	    	   }
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
		var start = $("#start").val();
		var end = $("#end").val(); 
		var appkey = $("#e2").val();
		var token = $("#serial_number").val();
		var name =encodeURIComponent( $("#name").val());
		var idNumber = $("#idNumber").val();
		var jdAccount = encodeURIComponent($("#jdAccount").val());
		var crawlerStatus = $("#e4").val();
		var creditResult = $("#e3").val();
		var pushStatus =  $("#e6").val();
		var oSettings = datatables.fnSettings();
		oSettings.sAjaxSource = $context.ctx + "/system/jdmonitor/dataSearch?start="+start+"&end="+end+"&appkey="+appkey
								+"&serial_number="+token+"&name="+name+"&idNumber="+idNumber+"&jdAccount="
								+jdAccount+"&crawlerStatus="+crawlerStatus+"&creditResult="+creditResult+"&pushStatus="+pushStatus;//如果从服务器端加载数据，这个属性用语指定加载的路径
		datatables.fnClearTable(0);
		datatables.fnDraw();
	});
});



 