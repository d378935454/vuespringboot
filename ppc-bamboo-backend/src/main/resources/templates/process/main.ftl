<!DOCTYPE html>
<html>
<head>
<#include "common\\loginheader.ftl">
    <!-- JQUERY UI-->

    <link rel="stylesheet" type="text/css"
          href="${ctx }/resources/js/jquery-ui-1.10.3.custom/css/custom-theme/jquery-ui-1.10.3.custom.min.css"/>
    <!-- DATE RANGE PICKER -->
    <link rel="stylesheet" type="text/css"
          href="${ctx }/resources/js/bootstrap-daterangepicker/daterangepicker-bs3.css"/>
    <!-- DATA TABLES -->
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/js/datatables/media/css/jquery.dataTables.min.css"/>
    <!-- <link rel="stylesheet" type="text/css" href="${ctx }/resources/js/datatables/media/assets/css/datatables.min.css" /> -->
    <link rel="stylesheet" type="text/css"
          href="${ctx }/resources/js/datatables/extras/TableTools/media/css/TableTools.min.css"/>
    <!-- BOOTSTRAP SWITCH -->
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/js/bootstrap-switch/bootstrap-switch.min.css"/>
    <!-- DATE PICKER -->
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/js/datepicker/themes/default.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/js/datepicker/themes/default.date.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/js/datepicker/themes/default.time.min.css"/>
    <!-- SELECT2 -->
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/js/select2/select2.min.css"/>
    <!-- WIZARD -->
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/js/bootstrap-wizard/wizard.css"/>
    <!-- <link rel="stylesheet" type="text/css" href="${ctx }/resources/bootstrap-dist/css/bootstrap.min.css" /> -->
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/bootstrap/fonts/jquery.alerts.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/css/load.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/css/html/api/basics_manage.css"/>

    <link rel="stylesheet" type="text/css" href="../../demo_tab/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="../../demo_tab/css/demo.css"/>
    <link rel="stylesheet" type="text/css" href="../../demo_tab/css/tabs.css"/>
    <link rel="stylesheet" type="text/css" href="../../demo_tab/css/tabstyles.css"/>
    <link rel="stylesheet" type="text/css" href="../../bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../../bootstrap/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" type="text/css" href="../../toastr/toastr.min.css"/>
    <style>
        .margin-top {
            margin-top: 20px;
        }
    </style>
</head>
<body id="page">
<!-- HEADER -->
<#include "common\\header.ftl">
<!--/HEADER -->

<!-- PAGE -->
<section style="height: 100%">
    <!-- SIDEBAR -->
<#include "common\\sidebar.ftl">
    <!-- /SIDEBAR -->
    <div id="main-content">
        <div style="display: none" id="dataField">
            <span id="methodListData">${methodList}</span>
        </div>
        <!-- SAMPLE BOX CONFIGURATION MODAL FORM-->
        <div class="modal fade" id="box-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Box Settings</h4>
                    </div>
                    <div class="modal-body">
                        Here goes box setting content.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- /SAMPLE BOX CONFIGURATION MODAL FORM-->
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <!-- PAGE HEADER-->
                <#--<div class="row">-->
                <#--<div class="col-sm-12" style="margin-top: 20px;">-->
                <#--</div>-->
                <#--</div>-->
                    <!-- /PAGE HEADER -->
                    <!-- EXPORT TABLES -->
                <#--<div class="row">-->
                <#--<div class="tabs tabs-style-bar">-->
                <#--<nav>-->
                <#--<ul id="tittle">-->
                <#--<div style="z-index: 2;width: 100%;height: 100%;position: absolute"></div>-->
                <#--<li id="1" data-url="../../admin/process/appKey"><a href="#section-bar-1"-->
                <#--class="icon icon-home"><span>选择appKey</span></a>-->
                <#--</li>-->
                <#--<li id="2" data-url="../../admin/process/stepList"><a id="b" href="#section-bar-2"-->
                <#--cistlass="icon icon-box"><span>节点列表</span></a>-->
                <#--</li>-->
                <#--<li id="3" data-url="../../admin/process/newStep"><a href="#section-bar-3"-->
                <#--class="icon icon-display"><span>新建节点</span></a>-->
                <#--</li>-->
                <#--<li id="4" data-url="../../admin/process/paramConfig"><a href="#section-bar-4"-->
                <#--class="icon icon-upload"><span>出入参别名配置</span></a>-->
                <#--</li>-->
                <#--<li id="5" data-url="../../admin/process/inputConfig"><a href="#section-bar-5"-->
                <#--class="icon icon-tools"><span>用户输入</span></a>-->
                <#--</li>-->
                <#--<li id="5" data-url="../../admin/process/logic"><a href="#section-bar-5"-->
                <#--class="icon icon-tools"><span>新增出入参显示模板</span></a>-->
                <#--</li>-->
                <#--<li id="5" data-url="../../admin/process/end"><a href="#section-bar-5"-->
                <#--class="icon icon-tools"><span>出入参界面预览</span></a>-->
                <#--</li>-->
                <#--<li id="5" data-url="../../admin/process/callBack"><a href="#section-bar-5"-->
                <#--class="icon icon-tools"><span>回调显示配置</span></a>-->
                <#--</li>-->
                <#--</ul>-->
                <#--</nav>-->
                <#--</div><!-- /tabs &ndash;&gt;-->

                <#--</div>-->
                    <div class="row margin-top" style="background: white">
                        <div class="content-wrap" id="load">

                        </div><!-- /content -->
                    <#--<div class="center margin-top">-->
                    <#--<button class="btn btn-primary" type="button" id="previous">上一步</button>-->
                    <#--<button class="btn btn-primary" type="button" id="next">下一步</button>-->
                    <#--</div>-->
                        <div class="footer-tools">
							<span class="go-top">
								<i class="fa fa-chevron-up"></i> Top
							</span>
                        </div>
                    </div>
                </div>
                <!-- /EXPORT TABLES -->

            </div><!-- /CONTENT-->
        </div>
    </div>
</section>

<!--/PAGE -->
<#include "common\\include_js.ftl">

<!-- DATE RANGE PICKER -->
<script src="${ctx }/resources/js/bootstrap-daterangepicker/moment.min.js"></script>
<script src="${ctx }/resources/js/bootstrap-daterangepicker/daterangepicker.min.js"></script>
<!-- SLIMSCROLL -->
<script type="text/javascript" src="${ctx }/resources/js/jQuery-slimScroll-1.3.0/jquery.slimscroll.min.js"></script>
<!-- SLIMSCROLL -->
<script type="text/javascript" src="${ctx }/resources/js/jQuery-slimScroll-1.3.0/slimScrollHorizontal.min.js"></script>
<!-- DATE PICKER -->
<script type="text/javascript" src="${ctx }/resources/js/datepicker/picker.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/datepicker/picker.date.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/datepicker/picker.time.js"></script>
<!-- BLOCK UI -->
<script type="text/javascript" src="${ctx }/resources/js/jQuery-BlockUI/jquery.blockUI.min.js"></script>
<!-- DATA TABLES -->
<script type="text/javascript" src="${ctx }/resources/js/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/datatables/media/assets/js/datatables.min.js"></script>
<script type="text/javascript"
        src="${ctx }/resources/js/datatables/extras/TableTools/media/js/TableTools.min.js"></script>
<script type="text/javascript"
        src="${ctx }/resources/js/datatables/extras/TableTools/media/js/ZeroClipboard.min.js"></script>
<!-- WIZARD -->
<script src="${ctx }/resources/js/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="${ctx }/resources/js/jquery-validate/jquery.validate.min.js"></script>
<script src="${ctx }/resources/js/jquery-validate/additional-methods.min.js"></script>
<script src="${ctx }/resources/js/jquery-validate/validate_zh_CN.js"></script>
<script src="${ctx }/resources/js/jquery.form.js"></script>
<!-- UI -->
<script src="${ctx }/js/jquery.ui.core.js"></script>
<script src="${ctx }/js/jquery.ui.widget.js"></script>
<script src="${ctx }/js/jquery.ui.autocomplete.js"></script>
<!-- SELECT2 -->
<script type="text/javascript" src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/echarts/build/dist/echarts-all.js"></script>
<!-- CUSTOM SCRIPT -->
<script src="${ctx }/resources/js/script.js"></script>
<script src="${ctx }/js/validate_methods.js"></script>
<script src="${ctx }/resources/js/jquery/jquery-migrate-1.2.1.min.js"></script>
<script src="${ctx }/js/jquery.alerts.js"></script>
<script src="${ctx }/js/jquery.confirm.js"></script>
<script src="${ctx }/js/load.js"></script>
<script src="${ctx }/js/basics_manage.js"></script>
<script src="${ctx }/js/adminTree.js"></script>
<script src="../../demo_tab/js/cbpFWTabs.js"></script>
<script src="../../demo_tab/js/modernizr.custom.js"></script>
<script src="../../js/vue/vue.js"></script>
<script src="../../js/page/page.js"></script>
<script src="../../toastr/toastr.min.js"></script>
<script src="../../jquery-hashchange-master/jquery.ba-hashchange.min.js"></script>
<script src="../../jquery-hashchange-master/pageload.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function () {
        App.setPage("dynamic_table");  //Set current page
        App.init(); //Initialise plugins and elements
        AddFormWizard.init();
    });
    initTree("${treeName}");
    $(document).ajaxComplete(function (event, jqXHR, options) {
        sessionOut(jqXHR);
    });
    $(function () {

        var $json = {
            aa: "123",
            aaa: {
                bb: "22",
                aa: "asd",
                ccc: {a: "asd"}
            },
            ccc: "asd"
        }
//        var $html = $(".go-top");
//        var $ul = eachJson($json, $html,$("#inputparam"));
        init();
    });

    /**
     * 格式化JSON路劲
     * $json:传入json
     * $ul:展示的div（盒子）
     * $input:点击展示结果的input（value）
     */
    function eachJson($json, $ul, $input) {
        if (typeof($json) == "object") {
            var cacheUl = $("<ul>");
            for (var key in $json) {
                var $li = $("<li>");
                cacheUl.append($li
                        .data("key", key).text(key).bind("click", function (e) {
                            $input.val(checkParam($(this)));
                            e.stopPropagation();
                        }));
                eachJson($json[key], $li, $input);
                $ul.append(cacheUl);
            }
        }
    }
    //    /**
    //     *  根据url页面load
    //     * */
    //    function configUrl(url) {
    //        $("#load").load(
    //                url
    //                , {}
    //                , function (responseText, textStatus, jqXHR) {
    //                    sessionOut(jqXHR);
    //                })
    //    }
        /**
         *选择参数
         */
        function checkParam(u) {

            var param = '';
            param = installParam(u, param);
            return param.substring(1);
        }

        function installParam(u, param) {
            param = "." + u.data("key");
            var parent = u.parent().parent('li');
            if (parent.length > 0) {
                param = installParam(parent, param) + param;
            }

            return param;
        }

    /**
     * 初始化第一步页面
     */
    function init() {
        if (location.hash=='') {
            location.hash = "/admin/process/appKey.htm";
        }
//        var step = $("#tittle").children("li").eq(0);
//        $("#load").load(step.data("url"), {aa: "123"})
//        step.addClass("tab-current");
//
//
//        $("#previous").attr("disabled", true);
//        $("#previous").bind("click", function () {
//            var $this = $(this);
//            $this.attr("disabled", true);
//            var prev = $(".tab-current").prev("li");
//            if (prev.length != 0) {//没有上一步
//                $("#next").removeAttr("disabled");
//                ;
//                $(".tab-current").removeClass("tab-current");
//                prev.addClass("tab-current");
//                $("#load").load(prev.data("url")
//                        , {aa: "123"}
//                        , function (responseText, textStatus, jqXHR) {
//                            sessionOut(jqXHR);
//                            $this.removeAttr("disabled");
//                            prev.prev("li").length == 0 ? $("#previous").attr("disabled", true) : '';
//                        });
//            }
//        })
//
//        $("#next").bind("click", function () {
//            var $this = $(this);
//            $this.attr("disabled", true);
//            var next = $(".tab-current").next("li");//得到下一步的li
//            if (next.length != 0) {//有下一步
//                $("#previous").removeAttr("disabled");
//                $(".tab-current").removeClass("tab-current");
//                next.addClass("tab-current");
//                $("#load").load(
//                        next.data("url")
//                        , {aa: "123"}
//                        , function (responseText, textStatus, jqXHR) {
//                            sessionOut(jqXHR);
//                            $this.removeAttr("disabled");
//                            next.next("li").length == 0 ? $("#next").attr("disabled", true) : '';
//                        })
//            }
//        })
    }
</script>
</body>
</html>