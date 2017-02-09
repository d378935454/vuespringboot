<!DOCTYPE html>
<html>
<head>
	<#include "common\\loginheader.ftl">
	<c:set var="win" value="用户管理" ></c:set>
	<!-- JQUERY UI-->
	
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/jquery-ui-1.10.3.custom/css/custom-theme/jquery-ui-1.10.3.custom.min.css" />
	<!-- DATE RANGE PICKER -->
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/bootstrap-daterangepicker/daterangepicker-bs3.css" />
	<!-- DATA TABLES -->
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/datatables/media/css/jquery.dataTables.min.css" />
	<!-- <link rel="stylesheet" type="text/css" href="${ctx }/resources/js/datatables/media/assets/css/datatables.min.css" /> -->
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/datatables/extras/TableTools/media/css/TableTools.min.css" />	
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
	<!-- <link rel="stylesheet" type="text/css" href="${ctx }/resources/bootstrap-dist/css/bootstrap.min.css" /> -->
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/bootstrap/fonts/jquery.alerts.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/load.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/html/api/basics_manage.css" />
</head>
<body>
	<!-- HEADER -->
	<#include "common\\header.ftl">
	<!--/HEADER -->
	
	<!-- PAGE -->
	<section id="page">
		<!-- SIDEBAR -->
		<#include "common\\sidebar.ftl">
		<!-- /SIDEBAR -->		
		<div id="main-content">
			<div style="display: none" id="dataField">
				<span id="methodListData">${methodList}</span>
			</div>
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
						<!-- EXPORT TABLES -->
						<div class="row">
							<div class="col-md-12">
								<!-- BOX -->
								<div class="box border primary">
									<div class="box-title">
										<h4><i class="fa fa-user"></i><span class="hidden-inline-mobile"><font style="font-size:16px;">流程管理</font><font style="font-size:20px;">>流程appkey关联管理</font></span></h4>
									</div>
									<div class="box-body">
										<div class="tabbable header-tabs ">
											<ul class="nav nav-tabs">
											   <!--<li><a href="#user_add" data-toggle="tab"><i class="fa fa-edit"></i> <span class="hidden-inline-mobile">新增用户</span></a></li>-->
											   <li class="active"><a href="#user_overview" data-toggle="tab"  id="lookUser"><i class="fa fa-dot-circle-o"></i> <span class="hidden-inline-mobile">查看</span></a></li>
											</ul>	
											<div class="tab-content">
											   <!-- 列表 -->
											   <div class="tab-pane fade in active" id="user_overview">
												   	<div class="row">
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
																		<div class="box-body">
																			<div class="row">													 
																			    <div class="col-xs-2">
																				    状态：<select name="status" id="e1" class="col-md-12">
																								<option value="">全部</option>
																								<option value="S">正常</option>
																								<option value="F">冻结</option>
																					     </select>
																			  	</div>
																			   	<div class="col-xs-3">
																			  			关键字：<input  class="form-control"  type="text" id="searchCondition" name="searchCondition" size="10" placeholder="公司名(产品名)/邮箱 /姓名/手机号 /APPKEY">
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
													<div class="row">
														<!-- TABLE -->
														<div class="col-md-12" id="showData" >
															<table id="datatableTable" class="datatable table table-striped table-bordered table-hover">
																<thead>
																	<tr>
																		<th style="width:15%">公司产品名称</th>
																		<th style="width:15%">邮箱</th>
																		<th style="width:15%">姓名</th>
																		<th style="width:20%">APPKEY</th>
																		<th style="width:10%">状态</th>
																		<th style="width:25%">操作</th>
																	</tr>
																</thead>
																<tbody>											
																</tbody>											
															</table>
														</div>
														<div id="initPromptMsg">
															<span>&nbsp;&nbsp;&nbsp;&nbsp;请输入您要查询的条件，点击查询按钮。</span>
															<br/><br/>
														</div>
													</div>		
											   </div>
											   <div class="tab-pane fade" id="user_add">
											   		<form id="wizForm" class="form-horizontal" action="#">
											   			<div class="row">
											   				<div class="col-md-12">
																<h4>基本信息</h4><hr/>
															</div>
															<div class="col-md-12 userInfoBody">
																<div class="alert alert-danger display-none">
																	<a class="close" aria-hidden="true" href="#" data-dismiss="alert">×</a>
																	<span id="tip_message">信息填写有误</span>
																</div>
																<div class="alert alert-success display-none">
																	<a class="close" aria-hidden="true" href="#" data-dismiss="alert">×</a>
																	<span id="tip_success_message">填写正确,正在提交信息,请稍后...</span>
																</div>
																<div class="form-group col-md-6">
																   <label class="col-md-4 control-label">公司名(产品名)</label> 
																   <div class="col-md-8"><input type="text" id="company" name="company" class="form-control" value=""></div>
																</div>	
																<div class="form-group col-md-6">
																   <label class="col-md-4 control-label">姓名</label> 
																   <div class="col-md-8"><input type="text" id="username" name="username" class="form-control" value=""></div>
																</div>
																<div class="form-group col-md-6">
																   <label class="col-md-4 control-label">邮箱</label> 
																   <div class="col-md-8"><input type="email" id="email" name="email" class="form-control" value=""></div>
																</div>
																<div class="form-group col-md-6">
																   <label class="col-md-4 control-label">手机号码</label> 
																   <div class="col-md-8"><input type="text" id="tel" name="tel" class="form-control" value=""></div>
																</div>
																<div class="form-group col-md-6">
																   <label class="col-md-4 control-label">状态</label> 
																  	<select name="modal8Status" id="e4">
																		<option value="S">正常</option>
																	   	<option value="F">冻结</option>
																	</select>
																</div>
																<div class="form-group col-md-6">
																	 <label class="col-md-4 control-label">系统地址(URL)</label> 
																	<div class="col-md-8"><input type="text" id="url" name="url" class="form-control" value=""></div>
																</div>
															</div>		
															<div class="col-md-12">
																<div id="addProductMothodBody">
																	<div class="col-md-12">
																 		<span class="col-md-1 fontWeight">产品配置</span>
																 		<div class="col-md-3">
																 			<span class="floatLeft">统一设置接口使用次数&nbsp;</span>
																 			<div id="addUnite" class="addUniteOn"></div>
																 		</div>
																 		<div id="addUniteSet" class="col-md-7">
																 			<div class="col-md-6">
																 				<label class="floatLeft">总调用次数&nbsp;</label>
																 				<input type="text" class="form-control inputPositiveInteger" id="addTotalCount" name="addTotalCount"> 
																 			</div>
																 			<div class="col-md-6">
																 				<label class="floatLeft">单日最高调用次数&nbsp;</label>
																 				<input type="text" class="form-control inputPositiveInteger" id="addDayCount" name="addDayCount"> 
																 			</div>
																 		</div>
															 		</div>
																	<div class="col-md-6 fontWeight">
																		<div class="col-md-6">
																			<input type="checkbox" name="addCheckAll"  id="addCheckAll">&nbsp;全选
																		</div>
																			<div class="col-md-3">总次数</div>
																			<div class="col-md-3">单日最高</div>
																	</div>
																	<div class="col-md-6 fontWeight">
																		<div class="col-md-6"></div>
																		<div class="col-md-3">总次数</div>
																		<div class="col-md-3">单日最高</div>
																	</div>
																	<div id="addMthod"></div>
																</div>
															 </div>
															 <!-- <div class="col-md-12" style="display:none">
															 	<a href="#addUserModal" data-toggle="modal" class="btn btn-primary" id="modalOpen">确定</a>
															 </div> -->
															 <div class="col-md-12">
															 	<div class="col-sm-offset-5 col-md-6">										 		
															 		<input type="submit" class="btn btn-primary" id="sub" value="保存">
																	<input type="button" value="返回" class="btn btn-primary" onclick="returnLookUser()">
																</div>
															 </div>
											   			</div>
											   		</form>
											   </div>
											</div>
										</div>
									</div>
								</div>
								<!-- /BOX -->
							</div>

							<!-- Modal -->
							<div id="myModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true" >
								<div class="modal-dialog" style="width:800px; margin-top: 200px;">	
								 	<div class="modal-content modal-table">
									    <div class="modal-header">
									        <button type="button" class="close" aria-hidden="true" id="close1">×
									        </button>
									        <h3 id="myModalLabel2">基本信息</h3>
									    </div>									    
									    <div class="box-body">
										    <form class="form-horizontal" id="resForm" action="#">
										    	<input type="hidden" name="id" id="objectId"/>	
											    <div class="col-md-12">	
											       <div class="form-group">
										                <label class="control-label col-md-3">公司名称：</label> 
										                <div class="col-md-9"><input class="form-control" type="text" id="inputCompany" name="company"/></div>
										            </div>								        										            						 
										            <div class="form-group">
										                <label class="control-label col-md-3">邮箱：</label> 
										                <div class="col-md-9"><input class="form-control" type="email" id="inputEmail" name="email"/></div>
										            </div>
										            <div class="form-group">
										                <label class="control-label col-md-3">姓名：</label> 
										                <div class="col-md-9"><input class="form-control" type="text" id="inputRealName" name="realName"/></div>
										            </div>
										            <div class="form-group">
										                <label class="control-label col-md-3">手机号码：</label>
										                <div class="col-md-9"><input class="form-control" type="number" id="inputTel" name="tel"/></div>
										            </div>	
										            <div class="form-group">
										                <label class="control-label col-md-3">采集系统URL：</label>
										                <div class="col-md-9">
										                	<div class="input-group">
														  	   	<span class="input-group-addon">http://info.mj.ppdai.com/ppcredit_collection/collection/index?value=</span>
														   	   	<input type="text" id="inputUrl" name="url" class="form-control" value="">
														   	  </div>
										                </div>
										            </div>									      
										    	</div>
										    	<button class="btn btn-primary" id="btnSave" style="margin-left:350px;" data-loading-text="loading...">确定</button>	
											</form>	
											<div style="margin-left: 450px;">												 									     
											     <button type="button" class="btn btn-danger" style="margin-top: -52px;" aria-hidden="true" id="close2">返回</button>
										    </div>
									   	</div>										    	 								    							    									    
									 </div>									  
								</div>
							</div>
							<!-- /Modal -->
							<!-- Modal -->
							<div id="productConfigurationModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="true" >
								<div class="modal-dialog" style="width:900px; margin-top: 200px;">
									<input type="hidden" name="productConfigurationId"	id="productConfigurationId"/>
								 	<div class="modal-content modal-table">
									    <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
									        </button>
									        <h3 id="myModalLabel3">流程配置</h3>
									    </div>									    
									    <div class="box-body">
										   	<div class="col-md-12" id="productConfigurationBody">
												
											 </div>
											 <div class="col-md-12">
											 	<input type="checkbox" id="checkAllProduct"> 全选
											 </div>
											 <div class="modal-footer">
											        <button class="btn btn-primary" id="productButtonSave">确定</button>
											        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">返回</button>
										    	</div>
									   	</div>										    	 								    							    									    
									 </div>									  
								</div>
							</div>
							<!-- /Modal -->
							<!-- Modal -->
							<div id="checkboxModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel4" aria-hidden="true" >
								<div class="modal-dialog" style="width:500px; margin-top: 200px;">	
								 	<div class="modal-content modal-table">
									    <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
									        </button>
									        <h4 id="myModalLabel4" style="text-align:center">流程配置确认</h4>
									    </div>									    
									    <div class="box-body">
									        <input type="hidden" name="userId" id="userId"/>
									    	<input type="hidden" name="ids" id="checkboxIds"/>
										   	<div class="col-md-12" id="checkboxProduct">
												
											</div>
											<div class="modal-footer">
											        <button class="btn btn-primary" id="checkboxBtnSave" onclick="checkboxBtnSave()">提交</button>
											        <button class="btn btn-primary" onclick="continueUpdateBtn()">继续修改</button>
										    </div>
									   	</div>										    	 								    							    									    
									 </div>									  
								</div>
							</div>
							<!-- /Modal -->
							<!-- Modal -->
							<div id="showAppModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel5" aria-hidden="true" >
								<div class="modal-dialog" style="width:800px; margin-top: 200px;">	
								 	<div class="modal-content modal-table">
									    <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
									        </button>
									        <h4 id="myModalLabel5" style="text-align:center">用户密钥详情</h4>
									    </div>									    
									    <div class="box-body">
										   	<div class="col-md-12">
											  	  <label class="control-label col-md-3">appKey:</label> 
									              <div class="col-md-9" id="appKeyValue"></div>
											</div>
											<div class="col-md-12">
											  	  <label class="control-label col-md-3">appSecret:</label> 
									              <div class="col-md-9" id="appSecretValue"></div>
											</div>
											<div class="modal-footer">
											       <button data-dismiss="modal" aria-hidden="true">确定</button>
										    </div>
									   	</div>										    	 								    							    									    
									 </div>									  
								</div>
							</div>
							<!-- /Modal -->
							<!-- Modal -->
							<div id="addUserModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel6" aria-hidden="true" >
								<div class="modal-dialog" style="width:1283px;margin-top:200px;">								
								  	<div class="modal-content modal-table">								  
										<div class="modal-header">
										  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										  <h4 class="modal-title">确认用户信息</h4>
										</div>
										  <div class="box-body" id="modalAddBody">
									    	 <div class="col-md-12 userInfoBody" id="modalAddUserInfoBody">
									    	 	<div class="form-group col-md-6">
													<label class="control-label modalAddleftTitle">公司名(产品名)</label> 
													<div class="modalAddRightInput">
														<input class="form-control modalAddInput" disabled="disabled" type="text" id="modalAddCompanyName" name="modalAddCompanyName"/>
													</div>
											</div>
											<div class="form-group col-md-6">
												<label class="control-label modalAddleftTitle">姓名</label> 
												<div class="modalAddRightInput">
													<input class="form-control modalAddInput" disabled="disabled" type="text" id="modalAddUsername" name="modalAddUsername"/>
												</div>
											</div>
											<div class="form-group col-md-6">
												<label class="control-label modalAddleftTitle">邮箱</label>
												<div class="modalAddRightInput">
													<input class="form-control modalAddInput" disabled="disabled" type="email" id="modalAddEmail" name="modalAddEmail"/>
												</div>
											</div>
											<div class="form-group col-md-6">
												<label class="control-label modalAddleftTitle">手机号码</label>
												<div class="modalAddRightInput">
													<input class="form-control modalAddInput" disabled="disabled" type="text" id="modalAddTel" name="modalAddTel"/>
												</div>
											</div>
									    	 <div class="form-group col-md-6">
												<label class="control-label modalAddleftTitle">状态</label>
												<div class="modalAddRightInput">
													<input class="form-control modalAddInput" disabled="disabled" type="text" id="modalAddStatus" name="modalAddStatus"/>
												</div>
											</div>
											<div class="form-group col-md-6">
													<label class="control-label modalAddleftTitle">系统地址(URL)</label>
													<div class="modalAddRightInput">
														<input class="form-control modalAddInput" disabled="disabled"  type="text" id="modalAddURL" name="modalAddURL"/>
													</div>
											</div>	
										 </div>
										   	<div class="col-md-12" id="modalAddProductMothodBody">
											 </div>
											<div class="modal-footer" style="margin-top:10px;">
											<button type="button" id="saveButton" class="btn btn-primary" data-loading-text="loading...">保存</button>
										    <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
											</div>
									   	</div>	
								  	</div>
								</div>
							</div>
							<!-- /Modal -->
							<!-- Modal -->
							<div id="productDetailsModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel7" aria-hidden="true" >
								<div class="modal-dialog" style="width:1283px; margin-top: 200px;">
								 	<div class="modal-content modal-table">
									    <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
									        </button>
									        <h3>查看用户</h3>
									        <input type="hidden" name="modal7UserId"	id="modal7UserId"/>
									    </div>									    
									    <div class="box-body" id="modal7Body">
									    	 <div class="col-md-12 userInfoBody" id="modal7UserInfoBody">
									    	 	<div class="col-md-6"><label class="control-label modal7leftTitle">公司名(产品名)</label><span id="modal7companyName"></span></div>
									    	 	<div class="col-md-6"><label class="control-label modal7leftTitle">姓名</label><span id="modal7Username"></span></div>
									    	 	<div class="col-md-6"><label class="control-label modal7leftTitle">邮箱</label><span id="modal7Email"></span></div>
									    	 	<div class="col-md-6"><label class="control-label modal7leftTitle">手机号码</label><span id="modal7Tel"></span></div>
												<div class="col-md-6"><label class="control-label modal7leftTitle">状态</label><span id="modal7Status"></span></div>
												<div class="col-md-6"><label class="control-label modal7leftTitle">系统地址(URL)</label><span id="modal7URL"></span></div>			
												<div class="col-md-12"><label class="control-label modal7leftTitle">AppKey</label><span id="modal7AppKey"></span></div>
												<div class="col-md-12"><label class="control-label modal7leftTitle">AppSecret</label><span id="modal7AppSecret"></span></div>
											 </div>
										   	<div class="col-md-12" id="modal7ProductMothodBody">
												
											 </div>
											 <div class="modal-footer">
											        <button class="btn btn-primary" id="productDetailsInput">修改</button>
											        <button class="btn btn-danger closeModal" data-dismiss="modal" aria-hidden="true">关闭</button>
										    	</div>
									   	</div>										    	 								    							    									    
									 </div>									  
								</div>
							</div>
							<!-- /Modal -->
							<!-- Modal -->
							<div id="updateDetailsModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel8" aria-hidden="true" >
								<div class="modal-dialog" style="width:1283px; margin-top: 200px;">
								 	<div class="modal-content modal-table">
								 	 <form class="form-horizontal" id="modal8Form" action="#">
										    <div class="modal-header">
										        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
										        </button>
										        <div>
										        	<input type="hidden" name="modal8UserId"	id="modal8UserId"/>
										        	  <h3>编辑用户</h3>
										        </div>
										    </div>									    
										    <div class="box-body" id="modal8Body">
										    	 <div class="col-md-12 userInfoBody" id="mpdal8UserInfoBody">
										    	 	<div class="form-group col-md-6">
														<label class="control-label modal8leftTitle">公司名(产品名)</label> 
														<div class="modal8RightInput">
															<input class="form-control modal8Input" type="text" id="modal8companyName" name="modal8companyName"/>
														</div>
													</div>
													<div class="form-group col-md-6">
														<label class="control-label modal8leftTitle">姓名</label> 
														<div class="modal8RightInput">
															<input class="form-control modal8Input" type="text" id="modal8Username" name="modal8Username"/>
														</div>
													</div>
													<div class="form-group col-md-6">
														<label class="control-label modal8leftTitle">邮箱</label>
														<div class="modal8RightInput">
															<input class="form-control modal8Input" type="email" id="modal8Email" name="modal8Email"/>
														</div>
													</div>
													<div class="form-group col-md-6">
														<label class="control-label modal8leftTitle">手机号码</label>
														<div class="modal8RightInput">
															<input class="form-control modal8Input" type="text" id="modal8Tel" name="modal8Tel"/>
														</div>
													</div>
											    	 <div class="form-group col-md-6">
														<label class="control-label modal8leftTitle">状态</label>
															<select name="modal8Status" id="e3">
																<option value="S">正常</option>
															   	<option value="F">冻结</option>
															</select>
													</div>
													<div class="form-group col-md-6">
														<label class="control-label modal8leftTitle">系统地址(URL)</label>
														<div class="modal8RightInput">
															<input class="form-control modal8Input" type="text" id="modal8URL" name="modal8URL"/>
														</div>
													</div>	
													<div class="form-group col-md-12">
															<label class="control-label modal8leftTitle">AppKey</label>
															<span id="modal8AppKey"></span>
													</div>
													<div class="form-group col-md-12">
															<label class="control-label modal8leftTitle">AppSecret</label>
															<span id="modal8AppSecret"></span>
													</div>
													</div>		
												 </div>
												 <div class="col-md-12" id="modal8ProductMothodBody">
												 	<div class="col-md-12">
												 		<span class="col-md-3 fontWeight">产品配置</span>
												 		<div class="col-md-3">
												 			<span class="floatLeft">统一设置接口使用次数&nbsp;</span>
												 			<div id="modal8Unite" class="modal8UniteOn"></div>
												 		</div>
												 		<div id="modal8UniteSet" class="col-md-6">
												 			<div class="col-md-6">
												 				<label class="floatLeft">总调用次数&nbsp;</label>
												 				<input type="text" class="form-control inputPositiveInteger" id="modal8TotalCount" name="modal8TotalCount"> 
												 			</div>
												 			<div class="col-md-6">
												 				<label class="floatLeft">单日最高调用次数&nbsp;</label>
												 				<input type="text" class="form-control inputPositiveInteger" id="modal8DayCount" name="modal8DayCount"> 
												 			</div>
												 		</div>
												 	</div>
												 	<div id="modal8ProductMothod">
													
												 	</div>
												 </div>
												 <div class="modal-footer">
												     <!--    <button class="btn btn-primary" id="productDetailsSubmit">提交</button>
												        <button class="btn btn-danger" id="productDetailsReturn" aria-hidden="true">返回</button>--> 
												      <input type="submit" class="btn btn-primary" id="productDetailsSubmit" value="提交"/> 
												     <input type="button" class="btn btn-danger" id="modal8Return" value="返回"/> 
												     <input type="button"  class="btn btn-danger closeModal" id="modal8Close" data-dismiss="modal" aria-hidden="true" value="关闭"/> 
											    </div>
											</form>	
										</div>	
									</div>									  
								</div>
							</div>
							<!-- Modal -->
							<div id="confirmDetailsModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel9" aria-hidden="true" >
								<div class="modal-dialog" style="width:1283px; margin-top: 200px;">
								 	<div class="modal-content modal-table">
									    <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
									        </button>
									        <input type="hidden" name="modal9UserId"	id="modal9UserId"/>
									        <h3>确认用户信息</h3>
									    </div>									    
									    <div class="box-body" id="modal9Body">
									    	 <div class="col-md-12 userInfoBody" id="modal9UserInfoBody">
									    	 	<div class="form-group col-md-6">
													<label class="control-label modal9leftTitle">公司名(产品名)</label> 
													<div class="modal9RightInput">
														<input class="form-control modal9Input" disabled="disabled" type="text" id="modal9companyName" name="modal9companyName"/>
													</div>
											</div>
											<div class="form-group col-md-6">
												<label class="control-label modal9leftTitle">姓名</label> 
												<div class="modal9RightInput">
													<input class="form-control modal9Input" disabled="disabled" type="text" id="modal9Username" name="modal9Username"/>
												</div>
											</div>
											<div class="form-group col-md-6">
												<label class="control-label modal9leftTitle">邮箱</label>
												<div class="modal9RightInput">
													<input class="form-control modal9Input" disabled="disabled" type="email" id="modal9Email" name="modal9Email"/>
												</div>
											</div>
											<div class="form-group col-md-6">
												<label class="control-label modal9leftTitle">手机号码</label>
												<div class="modal9RightInput">
													<input class="form-control modal9Input" disabled="disabled" type="text" id="modal9Tel" name="modal9Tel"/>
												</div>
											</div>
									    	 <div class="form-group col-md-6">
												<label class="control-label modal9leftTitle">状态</label>
												<div class="modal9RightInput">
													<input class="form-control modal9Input" disabled="disabled" type="text" id="modal9Status" name="modal9Status"/>
												</div>
											</div>
											<div class="form-group col-md-6">
													<label class="control-label modal9leftTitle">系统地址(URL)</label>
													<div class="modal9RightInput">
														<input class="form-control modal9Input" disabled="disabled"  type="text" id="modal9URL" name="modal9URL"/>
													</div>
											</div>	
											<div class="form-group col-md-12">
												<label class="control-label modal9leftTitle">AppKey</label>
												<span id="modal9AppKey"></span>
											</div>
											<div class="form-group col-md-12">
												<label class="control-label modal9leftTitle">AppSecret</label>
												<span id="modal9AppSecret"></span>
											</div>
										 </div>
										   	<div class="col-md-12" id="modal9ProductMothodBody">
											 </div>
											 <div class="modal-footer">
											        <button class="btn btn-primary" id="productDetailsConfirm">确认</button>
											        <button class="btn btn-danger" id="modal9Return" aria-hidden="true">返回</button>
										    	</div>
									   	</div>										    	 								    							    									    
									 </div>									  
								</div>
							</div>
							<!-- /Modal -->
						</div>						
						<!-- /EXPORT TABLES -->
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
	<!-- BLOCK UI -->
	<script type="text/javascript" src="${ctx }/resources/js/jQuery-BlockUI/jquery.blockUI.min.js"></script>
	<!-- DATA TABLES -->
	<script type="text/javascript" src="${ctx }/resources/js/datatables/media/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${ctx }/resources/js/datatables/media/assets/js/datatables.min.js"></script>
	<script type="text/javascript" src="${ctx }/resources/js/datatables/extras/TableTools/media/js/TableTools.min.js"></script>
	<script type="text/javascript" src="${ctx }/resources/js/datatables/extras/TableTools/media/js/ZeroClipboard.min.js"></script>
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
	<script src="${ctx }/js/process_appkey_manage.js"></script>
	<script src="${ctx }/js/adminTree.js"></script>
	<script>
		jQuery(document).ready(function() {		
			App.setPage("dynamic_table");  //Set current page
			App.init(); //Initialise plugins and elements	
			AddFormWizard.init();		
		});
		initTree("流程appkey关联管理");
	</script>	
</body>
</html>