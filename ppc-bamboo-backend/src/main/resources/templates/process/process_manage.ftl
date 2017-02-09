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
                    <div class="row margin-top" style="background: white">
                        <div class="content-wrap" id="load">

                        </div>
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
    $(function () {
        init();
    });


    /**
     * 初始化第一步页面
     */
    function init() {
        if (location.hash=='') {
            location.hash = "/admin/process/process_list.htm";
        }
    }
</script>
</body>
</html>