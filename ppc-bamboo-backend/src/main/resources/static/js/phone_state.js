function validateDate(start, end){
	var s1 = new Date(start.substring(0,10).replace(/-/g, "/"));
	var s2 = new Date(end.substring(0,10).replace(/-/g, "/"));
	var days = s2.getTime() - s1.getTime();
	var times = parseInt(days / (1000 * 60 * 60 * 24));
	if(times > 31){
		return "查询日期区间不能大于31天！"
	}else{
		return "Y";
	}
}

$(function() { 	
	//查询
	$("#sub").click(function() {
		queryTable();
	});	 
	
	//导入
	$("#upload").click(function() {	
		var filepath = $("#fileToUpload").val();
		var extStart = filepath.lastIndexOf(".");
		var ext = filepath.substring(extStart, filepath.length).toUpperCase();
		if (ext != ".TXT") {
			alert("只支持TXT文件上传。");
			return;
		}
		
	    $.ajaxFileUpload ({
	    	url : $context.ctx + "/system/phone_state/fileUpload",
	    	secureuri :false,
	    	fileElementId : 'fileToUpload',
	    	dataType : 'json',
//	    	async : false,
	    	success : function (data){
            	if(data.resp_code == "success"){
            		queryTable();
            	}else{
            		alert(data.resp_msg);
            		queryTable();
            	};
	    	}
	    });
	});
	
	//实例下载
	$("#demoDownload").click(function() {	
		var url = $context.ctx + "/system/phone_state/demoDownload";
	    window.location.href = url;
	});
});

function queryTable(){
	Load('正在努力加载中...');
	var start = $("#start").val();
	var end = $("#end").val();
	var isReTime=validateDate(start, end);
	if(isReTime!="Y"){
		alert(isReTime);
		return;
	}
	setTimeout(function () { 
		dispalyLoad(); 
    }, 1000);
	
	var url = $context.ctx + "/system/phone_state/main?startTime=" + start + "&endTime=" + end;
	
	window.location.href = url;
}

function download(id){
	var url = $context.ctx + "/system/phone_state/download?id="+id;
    window.location.href = url;
}

function authDetail(mainID){
	var url = $context.ctx + "/system/phone_state/detail?mainID=" + mainID;
    window.location.href = url;
}