$(function () {

    $(window).hashchange(function () {
        var hash = location.hash;
        processHash(hash);
    });

    $(window).hashchange();

    $(window).bind("load resize", function () {
        resizeWindow();//resizeWindow();
    });
});
/*var count = 0;*/
function resizeWindow() {
    var $nav = $("nav.navbar");
    var height = ($(window).height() - $nav.outerHeight(true) ) + "px";
    $(".body").css("min-height", height);
}
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var rs = window.location.href.split("?");
    var r = rs[rs.length - 1];
    if (!r) {
        return null;
    }
    r = r.match(reg);
    if (r != null) {
        return decodeURI(r[2])
    }
    return null;
}
function processHash(hash) {
    var s = hash.match(".*/(.*?)\.htm");
    if (s != null ) {

        $("#load").load(
            "../.." + hash.replace("#\/", "/"),
            function (responseText, textStatus, jqXHR) {
                sessionOut(jqXHR);
                resizeWindow();
                $(".navbar-collapse").collapse('hide');
            });
    }
}
function sessionOut(jqXHR) {
    if (jqXHR.responseJSON && jqXHR.responseJSON.code === 3) {//返回时json对象时，判断是否session超时
        window.location.href = jqXHR.responseJSON.body;
    }
    if (jqXHR.status === 302) {//ajaxload 异步刷新页面是判断是否session超时
        var data = $.parseJSON(jqXHR.responseText);
        window.location.href = data.body;
    }
}