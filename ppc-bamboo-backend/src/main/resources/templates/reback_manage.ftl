<!DOCTYPE html>
<html>
<head>
<#include "common/loginheader.ftl">
<c:set var="win" value="返回值管理" ></c:set>
<!-- JQUERY UI-->
<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<!-- BOOTSTRAP SWITCH -->
<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/bootstrap-switch/bootstrap-switch.min.css" />
	<!-- DATE PICKER -->
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/datepicker/themes/default.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/datepicker/themes/default.date.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/datepicker/themes/default.time.min.css" />
		<!-- SELECT2 -->
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/select2/select2.min.css" />
	<!-- WIZARD -->
<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/bootstrap-wizard/wizard.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/jquery-ui-1.10.3.custom/css/custom-theme/jquery-ui-1.10.3.custom.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/datatables/media/css/jquery.dataTables.min.css" />
	<!-- <link rel="stylesheet" type="text/css" href="${ctx }/resources/js/datatables/media/assets/css/datatables.min.css" /> -->
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/datatables/extras/TableTools/media/css/TableTools.min.css" />	
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/bootstrap/fonts/jquery.alerts.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/load.css" />
<style type="text/css">
.jquery_confirm____{
   margin-top:130px
}
</style>
</head>
<body>
	<!-- HEADER -->
	<#include "common/header.ftl">
	<!--/HEADER -->
	
	<!-- PAGE -->
	<section id="page">
				<!-- SIDEBAR -->
				<#include "common/sidebar.ftl">
				<!-- /SIDEBAR -->
		<div id="main-content">
			<!-- SAMPLE BOX CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="box-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
					<div id="content" class="col-lg-12">
						<!-- PAGE HEADER-->
						<div class="row">
							<div class="col-sm-12" style="margin-top: 20px;">
							</div>
						</div>
						<!-- /PAGE HEADER -->
						<!-- USER PROFILE -->											
						<div class="row" id="dataBox" >
							<div class="col-md-12 ">
								<!-- BOX -->
								<div class="box border primary">
									<div class="box-title">
										<h4><i class="fa fa-user"></i><span class="hidden-inline-mobile"><font style="font-size:16px;">系统管理</font><font style="font-size:20px;">>返回参数/回调</font></span></h4>
									</div>
									<div class="box-body">
										<div class="tabbable header-tabs ">
											<ul class="nav nav-tabs"> 		     
												<!--<ppd:auth ctype="add" menuid="1003" parentid="1001">-->
											        <li><a href="#pro_edit" data-toggle="tab"><i class="fa fa-edit"></i> <span class="hidden-inline-mobile"> 回调配置</span></a></li>
											     <!--</ppd:auth>-->
											     <!--<ppd:auth ctype="add" menuid="1003" parentid="1001">-->
											        <li class="active"><a href="#pro_overview" data-toggle="tab"><i class="fa fa-dot-circle-o"></i> <span class="hidden-inline-mobile">查看</span></a></li>
											    <!-- </ppd:auth>-->
											</ul>
											
											<div class="tab-content">
											   <!-- 列表 -->
											  <!-- <ppd:auth ctype="add" menuid="1003" parentid="1001">-->
											  	<div class="tab-pane fade in active" id="pro_overview">
											   		<div class="row" id="searchBox">
														<div class="col-md-12">
															<!-- BASIC -->
																	<div class="box border purple">
																		<div class="box-title">
																			<h4><i class="fa fa-bars"></i>查询条件</h4>
																			<div class="tools hidden-xs">
																				<a href="javascript:;" class="collapse">
																					<i class="fa fa-chevron-up"></i>
																				</a>
																			</div>
																		</div>
																		<div class="box-body big">
																			<div class="row">
														                          <div class="col-xs-2" >
																					类型：<select name="isActive" id="e3" class="col-md-12">
																							<option value="">全部</option>
																							<option value="0">接口</option>
																							<option value="1">认证</option>
																						</select>
																				  </div>																								  
																				  <div class="col-xs-3" >
																					接口名称：<select name="name" id="e6" class="col-md-12">
																							<option value="">全部</option>
																							<#list apiList as l><option value="${l.api}">${l.api}</option></#list>
																						</select>
																				  </div>
																			  <div class="col-xs-2">
																				&nbsp;<button id="search" class="btn btn-warning tip-bottom form-control" title="" data-original-title="查询">查询</button>
																			  </div>
																			  
																			</div>
																		</div>
																	</div>
																	<!-- /BASIC -->
														</div>
													</div>
												  <div class="row" id="selectUserList">
													<!-- TABLE -->
													<div class="col-md-12">
														<table id="datatableTable" class="datatable table table-striped table-bordered table-hover">
															<thead>
															  <tr>
																<th>服务类型</th>
																<th>appKey</th>
																<th>流程号</th>
																<th>执行顺序</th>
																<th>服务名称</th>
																<th>参数</th>
																<th>参数类型</th>
																<th>标准别名</th>
															</thead>
															<tbody>
															</tbody>
														  </table>
													</div>
													 </div>
													<!-- /TABLE -->
												  </div>
												  <!--</ppd:auth>-->
												   <!-- 编辑 -->
												  <div id="pro_edit" class="tab-pane fade">
													  <form id="editForm" class="form-horizontal" action="#">
														<div class="row">
															 <div class="col-md-12">
																<div class="box border green">
																	<div class="box-title">
																		<h4><i class="fa fa-bars"></i>回调参数配置</h4>
																	</div>
																	<div class="box-body big">
																	   <div class="row">
																		   <div class="box-body">
																		       <div class="col-md-3">
																		       </div>
																				<div class="row">
																				<input type="hidden" name="callback_id" id="callback_id" value="${callbackInfo.id }"/>
																				  	<div class="col-xs-2">
																				       <h3 id="">回调类型</h3>
																				       <input type="text" name="callback_type" id ="callback_type" class="form-control" value="${callbackInfo.callback_type }"/>
																			       </div>
																			       <div class="col-xs-2">
																				      <h3 id="">回调url</h3>
																				      <input type="text" name="callback_url" id ="callback_url" class="form-control" style="width:500px;"  value="${callbackInfo.callback_url }"/>
																			       </div>
																			    </div>
																		       <div class="col-md-3">
																		       </div>
																			    <div class="row">
																			    	<div class="col-xs-2">
																				       <h3 id="">通知类型</h3>
																				       <input type="text" name="notify_type" id ="notify_type" class="form-control" value="${callbackInfo.notify_type }"/>
																			        </div>
																			    	<div class="col-xs-2">
																				       <h3 id="">通知url</h3>
																				       <p><input type="text" name="notify_url" id ="notify_url" class="form-control" style="width:500px;" value="${callbackInfo.notify_url }"/>
																			        </div>
																			    </div>
																			</div>
																		    <div>
																		    	<div class="col-md-4">
													  							</div>
																				<div class="col-xs-2">
																					<input type="submit" id="saveCallbackButton" value="保存"  class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; 
																					<input type="button" value="返回" class="btn btn-primary" onclick="returnBack()" />
																				</div>
																		    </div>
																	  </div>
																	</div>
																</div>
															 </div>
														 </div>
													  </form>
												   </div>
												   <!-- /编辑 -->
											  
											   <!-- /列表 -->
											</div>
										</div>
										<!-- /USER PROFILE -->
									</div>
								</div>
							</div>
						</div>
						<div class="footer-tools">
							<span class="go-top">
								<i class="fa fa-chevron-up"></i> Top
							</span>
						</div>
					</div><!-- /CONTENT-->
				</div>
			</div>
		</div>
	</section>
	<!--/PAGE -->
	<#include "common/include_js.ftl">
	
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
<!-- WIZARD -->
<script src="${ctx }/resources/js/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="${ctx }/resources/js/jquery-validate/jquery.validate.min.js"></script>
<script src="${ctx }/resources/js/jquery-validate/additional-methods.min.js"></script>
<script src="${ctx }/resources/js/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/datatables/extras/TableTools/media/js/TableTools.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/datatables/extras/TableTools/media/js/ZeroClipboard.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/datatables/media/assets/js/datatables.min.js"></script>
<script src="${ctx }/resources/js/jquery-validate/validate_zh_CN.js"></script>
<script src="${ctx }/resources/js/jquery.form.js"></script>
<script src="${ctx }/js/jquery.confirm.js"></script>
	<script src="${ctx }/resources/js/jquery/jquery-migrate-1.2.1.min.js"></script>
	<!-- SELECT2 -->
	<script type="text/javascript" src="${ctx }/resources/js/select2/select2.min.js"></script>
	<!-- CUSTOM SCRIPT -->
	<script src="${ctx }/js/jquery.alerts.js"></script>
<script src="${ctx }/resources/js/script.js"></script>
<script src="${ctx }/js/load.js"></script>
<script src="${ctx }/js/JSONFormat.js"></script>
<script src="${ctx }/js/response_manage.js"></script>
<script src="${ctx }/js/adminTree.js"></script>

	<script>
		jQuery(document).ready(function() {		
			App.setPage("dynamic_table");  //Set current page
			App.init(); //Initialise plugins and elements
	        FormWizard.init();
		});
		initTree("返回参数/回调");
	</script>
</body>
</html>