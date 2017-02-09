/**
 * Created by l on 2016/4/26.
 */
/**
 * 创建分页
 * @param pageInfo 分页的对象
 * @param divId 分页div的id
 */
function createPageText(pageInfo,divId) {
    var $ul = $("<ul/>").addClass("pagination");
    //首页
    if (pageInfo.isFirstPage) {
        var $a = $("<a/>").text("首页").prop("href", "javascript:void(0);").addClass("lastNone");
        var $firstLi = $("<li/>");
        $firstLi.append($a);
        $ul.append($firstLi);
    } else {
        var $a = $("<a onclick='loadPage(1)'>首页</a>").prop("href", "javascript:void(0);");
        var $firstLi = $("<li/>").addClass("first");
        $firstLi.append($a);
        $ul.append($firstLi);
    }

    //上一页
    //如果当前页为1，那么上一页还是1
    var $prevLi = null;
    if (!pageInfo.hasPreviousPage) {
        var $a = $("<a>上一页</a>").prop("href", "javascript:void(0);").addClass("lastNone");;
        var $prevLi = $("<li/>");
        $prevLi.append($a);
    } else {
        var $a = $("<a onclick='loadPage(" + pageInfo.prePage + ")'>上一页</a>").prop("href", "javascript:void(0);");
        var $prevLi = $("<li/>").addClass("prev");
        $prevLi.append($a);
    }
    $ul.append($prevLi);
    //循环迭代中间分页部分
    $.each(pageInfo.navigatepageNums, function (i, n) {
        if (pageInfo.pageNum == n) {
            /*console.log("当前页面" + n);*/
            var $currentLi = $("<li/>").addClass("page active");
            var $a = $("<a onclick='loadPage(" + n + ")'>" + n + "</a>").addClass("current").prop("href", "javascript:void(0);");
            $currentLi.append($a);
            $ul.append($currentLi);
        } else {
            var $currentLi = $("<li/>").addClass("page");
            var $a = $("<a onclick='loadPage(" + n + ")'>" + n + "</a>").prop("href", "javascript:void(0);");
            $currentLi.append($a);
            $ul.append($currentLi);
        }
    });
    //下一页
    null;
    if (!pageInfo.hasNextPage) {
        var $a = $("<a>下一页</a>").prop("href", "javascript:void(0);").addClass("lastNone");
        var $prevLi = $("<li/>");
        var $nextLi = $prevLi.append($a);
        $ul.append($nextLi);
    } else {
        var $a = $("<a onclick='loadPage(" + pageInfo.nextPage + ")'>下一页</a>").prop("href", "javascript:void(0);");
        var $prevLi = $("<li/>").addClass("prev");
        var $nextLi = $prevLi.append($a);
        $ul.append($nextLi);
    }

    //末页
    if (pageInfo.isLastPage) {
        var $a = $("<a>末页</a>").prop("href", "javascript:void(0);").addClass("lastNone");
        var $lastLi = $("<li/>");
        $lastLi.append($a);
        $ul.append($lastLi);
    } else {
        var $a = $("<a onclick='loadPage(" + pageInfo.pages + ")'>末页</a>").prop("href", "javascript:void(0);");
        var $lastLi = $("<li/>").addClass("last");
        $lastLi.append($a);
        $ul.append($lastLi);
    }
    //总页数
    var $cntLi = $("<li/>");
    $cntLi.append($("<span/>").addClass("cnt").text("共" + pageInfo.pages + "页"));
    $ul.append($cntLi);
    //总记录数
    var $numLi = $("<li/>");
    $numLi.append($("<span/>").text(pageInfo.total + "条记录")).addClass("num");
    $ul.append($numLi);


    $("#"+divId).empty().append($ul);
}