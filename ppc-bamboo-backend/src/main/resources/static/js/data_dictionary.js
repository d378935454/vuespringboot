var datatables;
$(function() {
	datatables = $('#myTable').DataTable({
        "bPaginate": true, //翻页功能  
        "bLengthChange": false, //改变每页显示数据数量  
        "bFilter": false, //过滤功能  
        "bStateSave": false,       
        "bInfo": true,//页脚信息  
        "bDestroy":true,
        "sPaginationType": "full_numbers",   /*默认翻页样式设置*/
        "bRetrieve": true,
        "bAutoWidth": true,//自动宽度  
        "bProcessing": false,//加载数据时候是否显示进度条                 
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
        "sAjaxSource": $context.ctx + "/system/admin/datadictionary_query",//如果从服务器端加载数据，这个属性用语指定加载的路径 
       "aoColumns" : [ {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "dbName",
            "aTargets" : [ 0 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "dbType",
            "aTargets" : [ 1 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "tbName",
            "aTargets" : [ 2 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "tbDescribe",
            "aTargets" : [ 3 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "createTime",
            "aTargets" : [ 4 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "updateTime",
            "aTargets" : [ 5 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "fieldNum",
            "aTargets" : [ 6 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "fieldName",
            "aTargets" : [ 7 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "fieldDescribe",
            "aTargets" : [ 8 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "fieldType",
            "aTargets" : [ 9 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "length",
            "aTargets" : [ 10 ]
        }, {
            "bVisible" : true,
            "bSortable": false,
            "mData" : "astrict",
            "aTargets" : [ 11 ]
        },{
            "bVisible" : true,
            "bSortable": false,
            "mData" : "note",
            "aTargets" : [ 12 ]
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
    	   return sPre;
       }  
    });
});