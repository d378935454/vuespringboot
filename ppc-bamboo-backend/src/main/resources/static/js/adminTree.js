function initTree(name){
	$("#ppcredit_admin_tree>li>ul>li").each(function(){
		if ($(this).text().trim() == name){			
			$(this).addClass("current");
			$(this).parent().css("display","block");
			$(this).parent().parent().addClass("has-sub open");
			$(this).parent().prev().children().eq(2).addClass("arrow open");
		} else{
			$(this).removeClass("current");
			$(this).parent().parent().addClass("has-sub");
			$(this).parent().prev().children().eq(2).addClass("arrow");
		}
	});
};