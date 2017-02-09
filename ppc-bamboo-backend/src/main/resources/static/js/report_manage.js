var datatables;
$(function() {
	datatables = $('#myTable').DataTable({
		/*基本参数设置，以下参数设置和默认效果一致*/  
        "bPaginate": true, //翻页功能  
        "bLengthChange": false, //改变每页显示数据数量  
        "bFilter": false, //过滤功能  
        "bStateSave": false,       
        "bInfo": true,//页脚信息  
        "bDestroy":true,
         //"bJQueryUI" : true,
        //"bSort": true, //排序功能  
        //"sScrollY" : 375, //DataTables的高  
        //"sScrollX" : 1595, //DataTables的宽 
        "aaSorting": [[ 0, "desc" ]],
        "sPaginationType": "full_numbers",   /*默认翻页样式设置*/
        "bRetrieve": true,
        "bAutoWidth": true,//自动宽度  
        "bProcessing": false,//加载数据时候是否显示进度条                 
        "oLanguage": {      	
        	"sLengthMenu": "每页显示 _MENU_条",  
        	"sZeroRecords": "没有找到符合条件的数据",
        	//"sProcessing" : "正在处理数据，请稍候……",
        	//"sProcessing" : "<img src='../../resources/img/loaders/4.gif'/>", //这里是给服务器发请求后到等待时间显示的 加载gif
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
        "sAjaxSource": $context.ctx + "/system/api/report_data?start="+$("#start").val()+"&end="+$("#end").val()+"&accessSystem="
								+$("#e1").val()+"&companyName="+$("#e2").val()+"&companyStatus="+$("#e3").val()+"&sourceId="+$("#e4").val(),//如果从服务器端加载数据，这个属性用语指定加载的路径 
        /*"aaSorting": [[ 0, "desc" ]],//设置第3个元素为默认排序  */ 
       "aoColumns" : [ {
            "bVisible" : true,
            "bSortable": true,
            "mData" : "productName",
            "aTargets" : [ 0 ]
        }, {
            "bVisible" : true,
            "bSortable": true,
            "mData" : "methodDesc",
            "aTargets" : [ 1 ]
        }, {
            "bVisible" : true,
            "bSortable": true,
            "mData" : "totalCount",
            "aTargets" : [ 2 ]
        }, {
            "bVisible" : true,
            "bSortable": true,
            "mData" : "successYesData",
            "aTargets" : [ 3 ]
        }, {
            "bVisible" : true,
            "bSortable": true,
            "mData" : "successNoData",
            "aTargets" : [ 4 ]
        }, {
    	   "bVisible" : true,
           "bSortable": true,
           "mData" : "errorCount",
           "aTargets" : [ 5 ]
        }, {
            "bVisible" : false,
            "bSortable": true,
            "mData" : "callErrorCount",
            "aTargets" : [ 6 ]
        },],
        "aoColumnDefs" : [ {
            sDefaultContent : '',
            aTargets : [ '_all' ]
        } ],
       "fnRowCallback": function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 
       },
       "fnPreDrawCallback": function(){
    	   Load('正在运行，请稍后...');
       },
       "fnInfoCallback": function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {
    	   dispalyLoad();
    	   return "当前第  "+iStart+" - "+ iEnd+" 条　共计 "+iTotal+" 条";
       }  
    });
	setCompanyName();
	$("#e3").change(function(){
		setCompanyName();
	});
	
	$("#e1").change(function(){
		var accessSystem = $("#e1").val();
		if("0" == accessSystem){
			$(".customerSel").show();
			$(".sourceNameSel").hide();
		}else if("1" == accessSystem){
			$(".customerSel").hide();
			$(".sourceNameSel").show();
			$("#sourceSelType").html("魔镜选择:");
			$("#e4").html("");
			$("#e4").append("<option value='all'>全部</option>"); 
			$("#e4").append("<option value='00'>本地库</option>"); 
			$("#e4").append("<option value='200'>爬虫</option>"); 
			$("#s2id_e4 .select2-chosen").first().text("全部");
		}else if("2" == accessSystem){
			$(".customerSel").hide();
			$(".sourceNameSel").show();
			$("#e4").html("");
			$("#sourceSelType").html("供应商选择:");
			$("#e4").append("<option value='all'>全部</option>"); 
			$("#e4").append("<option value='01'>银联</option>"); 
			$("#e4").append("<option value='02'>爰金</option>"); 
			$("#e4").append("<option value='03'>国政通</option>"); 
			$("#s2id_e4 .select2-chosen").first().text("全部");
		}
			
		
	});
	
	function setCompanyName(){
		var companyLista = $("#companyListData").html();
		var companyList = eval("("+companyLista+")");
		var companyStatus = $("#e3").val();
		$("#e2").html("");
		$("#e2").append("<option value='all'>全部</option>"); 
		$.each(companyList,function(i,item){ 
			var companyNameTemp = item.companyName;
			var companyStatusTemp = item.companyStatus;
			if("all" == companyStatus){
				$("#e2").append("<option value="+companyNameTemp+">"+companyNameTemp+"</option>"); 
			}
			else if(companyStatusTemp != null && companyStatus.toLowerCase() == companyStatusTemp.toLowerCase()){
				$("#e2").append("<option value="+companyNameTemp+">"+companyNameTemp+"</option>"); 
			}
		});
		$("#s2id_e2 .select2-chosen").first().text("全部");
	}
	$("#search").click(function() {	
		var oSettings = datatables.fnSettings();
		var accessSystem = $("#e1").val();
		
		oSettings.sAjaxSource = $context.ctx + "/system/api/report_data?start="+$("#start").val()+"&end="+$("#end").val()+"&accessSystem="
		+accessSystem+"&companyName="+$("#e2").val()+"&companyStatus="+$("#e3").val()+"&sourceId="+$("#e4").val();//如果从服务器端加载数据，这个属性用语指定加载的路径 
		datatables.fnClearTable(0);
		if("2" == accessSystem){
			datatables.fnSetColumnVis(6, true ,false);
		}
		else{
			datatables.fnSetColumnVis(6, false ,false);
		}
		datatables.fnDraw();
	});
	$("#export").click(function() {	
		var oSettings = datatables.fnSettings();
		var iSortCol_0  =  oSettings.aaSorting[0][0];
		var sSortDir_0  =  oSettings.aaSorting[0][1];
		window.location.href = $context.ctx + "/system/api/export_report?start="+$("#start").val()+"&end="+$("#end").val()+"&accessSystem="
		+$("#e1").val()+"&accessSystemText="+$("#e1").find("option:selected").text()+"&companyStatus="+$("#e3").val()+"&companyStatusText="
		+$("#e3").find("option:selected").text()+"&companyName="+$("#e2").val()+"&iSortCol_0="+iSortCol_0+"&sSortDir_0="+sSortDir_0
		+"&sourceId="+$("#e4").val()+"&sourceName="+$("#e4").find("option:selected").text();
	});
});
