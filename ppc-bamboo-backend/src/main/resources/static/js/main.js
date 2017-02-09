$(function(){
	$("#log_out").click(function(){
		$.post($context.ctx+"/login/login_out?m="+ Math.random(),function(data){
			var obj = jQuery.parseJSON(data);
        	if(obj.resp_code == "success"){
        		location.href=obj.resp_msg;
        	}
		});
	});
});