//显示遮罩  
function Load(title) {  
    $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
    $("<div class=\"datagrid-mask-msg\"></div>").html(title).appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });  
}  
  
//隐藏遮罩 
function dispalyLoad() {  
    $(".datagrid-mask").remove();  
    $(".datagrid-mask-msg").remove();  
} 