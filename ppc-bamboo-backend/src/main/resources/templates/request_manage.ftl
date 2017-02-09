<!DOCTYPE html>
<html>
<head>
<#include "common/loginheader.ftl">
<c:set var="win" value="接口管理" ></c:set>
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
										<h4><i class="fa fa-user"></i><span class="hidden-inline-mobile"><font style="font-size:16px;">系统管理</font><font style="font-size:20px;">>请求参数</font></span></h4>
									</div>
									<div class="box-body">
										<div class="tabbable header-tabs ">
											<ul class="nav nav-tabs">
											     <!-- <ppd:auth ctype="add" menuid="1003" parentid="1001">
											        <li><a href="#pro_edit" data-toggle="tab"><i class="fa fa-edit"></i> <span class="hidden-inline-mobile"> 新增</span></a></li>
											     </ppd:auth> -->
											     <!--<ppd:auth ctype="add" menuid="1003" parentid="1001">-->
											        <li class="active"><a href="#pro_overview" data-toggle="tab"><i class="fa fa-dot-circle-o"></i> <span class="hidden-inline-mobile">查看</span></a></li>
											     <!--</ppd:auth>-->
											</ul>
											
											<div class="tab-content">
											   <!-- 列表 -->
											   <!--<ppd:auth ctype="add" menuid="1003" parentid="1001">-->
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
														                          <div class="col-xs-2">
																					类型：<select name="isActive" id="e3" class="col-md-12">
																							<option value="">全部</option>
																							<option value="0">接口</option>
																							<option value="1">认证</option>
																						</select>
																				  </div>																								  
																				  <div class="col-xs-3">
																					名称：<select name="name" id="e6" class="col-md-12">
																							<option value="">全部</option>
																							<#list apiList as l><option value="${l.api}">${l.api}-${l.apiName}</option></#list>
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
																		<h4><i class="fa fa-bars"></i>用户 信息</h4>
																	</div>
																	<div class="box-body big">
																		<div class="row">
																		 <div class="col-md-12">
																			 <div class="alert alert-danger display-none">
																				<span id="api_message">信息填写有误</span>
																			 </div>
																			 <div class="alert alert-success display-none">
																				<span id="api_success_message"></span>
																			 </div>
																			  <div class="form-group" style="display:none">
																			   <label class="col-md-4 control-label">接口ID</label> 
																			   <div class="col-md-8"><input type="text" name="id" id="id" class="form-control" value=""></div>
																			</div>
																			<div class="form-group">
																			   <label class="col-md-4 control-label">类型</label> 
																			   <div class="col-md-8">
																					<select name="apiType" id="e1" class="col-md-3">
																						<option value="">全部</option>
																				   		<option value="0">接口</option>
																				   		<option value="1">模型</option>
																					</select>
																			   </div>
																			</div>
																			<h4>接口信息</h4>
																			<div class="form-group">
																			   <label class="col-md-4 control-label">接口名称</label> 
																			   <div class="col-md-8">
																					<select name="api" id="e2" class="col-md-3" >
																						<option value="">全部</option>
																						<#list list as l><option value="${l.api}">${l.api}</option></#list>
																					</select>
																			   </div>
																			</div>
																		 </div>
																	  </div>
																	</div>
																</div>
															 </div>
														 </div>
													  <div class="form-actions clearfix" > 
													  	<input type="submit" id="saveButton" value="保存" class="btn pull-center"> 
													  	<input type="button" id="cancelEdit" value="返回" class="btn pull-center">
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
                         <div class="row" id="apiBox" style="display:none">
							<div class="col-md-12">
								<!-- BOX -->
								<div class="box border ">
									<div class="box-title">
										<h4><i class="fa fa-user"></i><span class="hidden-inline-mobile">接口明细</span></h4>
									</div>
									<div class="box-body">
									    <p class="text-right"><button type="button" class="btn btn-primary" id="returnBtn">返回列表</button></p>
									    <p class="text-right"><a id="preApi" value="" onclick="javascipt:getApiDetail($(this));">上一个</a>  &nbsp;&nbsp; <a id="nextApi" value="" onclick="javascipt:getApiDetail($(this));">下一个</a></p>
									    <p class="text-left">
									       <h3 id="">接口名称</h3>
									       <p><strong>英文名称 : </strong><code><span id="curName">mj.jd.account.check</span></code> <strong>中文名称 : </strong><span id="curDesc">京东认证接口</span></p>
									    </p>
									    <h3 id="">接口地址</h3>
									    <address>
                                             <a href="javascript:return;"><span id="curRequestParams" style="word-break:break-all;">http://域名/ppdai_api/router/rest?timestamp=1439973411459&sign_method=SHA&appkey=keybe86656dc0a242a6a54b37b70b90718c
                                             &method=api.resource.jd.check&sign=ba4ac2cdede03210ec309604732925148c911047&loginname=fancytad%40126.com&loginpwd=ODQ3MXjk2MTANh</span></a>
                                        </address>
                                        
                                        <h3 id="">接口描述</h3>
                                            <span id="curMark"> 验证请求方提供的京东账号和密码的有效性。</span>                                
							            <h3>输入参数</h3>
							            <div id="inputParam">
                                               <!-- input param table -->
                                               <!-- input param table /-->
										 </div>
							            <h3>输出参数</h3>
							            <div id="outputParam">
                                               <!-- output param table -->
                                               <!-- output param table /-->
										 </div>								 									
							            <h3>系统响应码</h3>
							            <div id="respcodeParam">
                                               <!-- output param table -->
                                               <!-- output param table /-->
										 </div>
										  <h3>示例</h3>
								 		<p>
									 		<strong>请求报文 : </strong>
									 		<address>
	                                             <span id="curRequest" style="word-break:break-all;">http://域名/ppdai_api/router/rest?timestamp=1439973411459&sign_method=SHA&appkey=keybe86656dc0a242a6a54b37b70b90718c
	                                             &method=api.resource.jd.check&sign=ba4ac2cdede03210ec309604732925148c911047&loginname=fancytad%40126.com&loginpwd=ODQ3MXjk2MTANh</span>
	                                        </address>
								 		</p>
								 		<p>
									 		<strong>返回报文 : </strong>
									 		<address>
	                                             <span id="curResponseParams" style="word-break:break-all;">http://域名/ppdai_api/router/rest?timestamp=1439973411459&sign_method=SHA&appkey=keybe86656dc0a242a6a54b37b70b90718c
	                                             &method=api.resource.jd.check&sign=ba4ac2cdede03210ec309604732925148c911047&loginname=fancytad%40126.com&loginpwd=ODQ3MXjk2MTANh</span>
	                                        </address>
								 		</p>
										 <h3>返回报文</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr>
									               <th style="width: 100px;">名称</th>
									               <th style="width: 150px;">code</th>
									               <th style="width: 450px;">描述</th>
									               <th>描述</th>
									             </tr>
									            </thead>
									            <tbody>
									             <tr>
									               <td>响应编码</td>
									               <td><code>resp_code</code></td>
									               <td>Y</td>
									               <td>开放平台返回的响应编码，是判断接口调用成功或失败原因的唯一编码</td>
									             </tr>
			                                    <tr>
									               <td>响应描述</td>
									               <td><code>resp_msg</code></td>
									               <td>Y</td>
									               <td>例如：成功、服务不可用、未授权等</td>
									             </tr>		
			                                    <tr>
									               <td>响应包体</td>
									               <td><code>resp_body</code></td>
									               <td>Y</td>
									               <td>json格式封装，具体业务返回包体，详见各接口的【输出参数】当且仅当调用成功时，响应包头才有值</td>
									             </tr>											             								             
									            </tbody>
										  </table>	
										 <h3>签名SIGN机制</h3>	
										 <p>   调用接口时需要对请求包头参数进行签名验证，服务器也会对该请求参数进行验证是否合法的。方法如下：</p>
										 <p>根据包头参数名称将所有请求参数按照字母先后顺序排序:key + value .... key + value</p>
										 <p>例如：将foo=1,bar=2,baz=3 排序为bar=2,baz=3,foo=1，参数名和参数值链接后，得到拼装字符串bar2baz3foo1</p>
										 <p>   系统同时支持MD5和SHA两种加密方式:</p>
										 <p>MD5：将appsecret 拼接到参数字符串头、尾进行MD5加密，格式是：MD5(appsecretkey1value1key2value2... appsecret)</p>
										 <p>SHA：与MD5的拼接方式一致，将appsecret 拼接到参数字符串头、尾进行SHA加密。</p>
										 <p>注意：</p>
										 <p>【包头】全部是加密的输入参数，包括加密方式（sign_method）；</p>
										 <p>加密格式（默认MD5）</p>
										 <p>编码格式（默认UTF-8）</p>
										 <p> 提供JAVA的签名工具类DigestUtil.jar，可在网站下载。	</p>
										 <h3> 生成签名步骤</h3>	
										 <ul>
										 	<li>
										 		<strong>拼装请求</strong>
										 		<div>
										 			<p>method=xx.xx.xx</p>
										 			<p>timestamp=1422520027150</p>
										 			<p>appkey=9381eb1a9faf4238a91d872931892283</p>
										 			<p>sign_method=MD5</p>
										 		</div>									 	
										 	</li>
										 	<li>
										 		<strong>按首字母升序排列</strong>
										 		<div>
											 		<p>appkey=9381eb1a9faf4238a91d872931892283</p>
										 			<p>method=xx.xx.xx</p>
										 			<p>sign_method=MD5</p>
										 			<p>appkey=9381eb1a9faf4238a91d872931892283</p>
									 			</div>
										 	</li>
										 	<li>
										 		<strong>连接字符串</strong>
										 		<div>
										 			appkey9381eb1a9faf4238a91d872931892283methodxx.xx.xxsign_methodMD5timestamp1422520027150
										 		</div>
										 	</li>
										 	<li>
										 		<strong>生成签名</strong>
										 		<div>
										 		<p>MD5(<span style="color:red;">appsecret</span> appkey9381eb1a9faf4238a91d872931892283methodxx.xx.xxsign_methodMD5timestamp1422520027150<span style="color:red;">appsecret</span>)</p>
												<p><span style="color:red;">appsecret</span>替换成具体的值</p>
										 		</div>
										 	</li>
										 </ul>
										 <h3>注意事项</h3>		
										 <p>1、所有的请求和响应数据编码皆为UTF-8格式</p>
										 <p>2、查询类接口建议用get请求，交易等隐私信息查询和修改类接口建议用post请求；</p>
										 <div id="jdAccount" style="display:none">	
										 <h3 id="1">地址信息列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>phone</td><td><code>String</code></td><td>固定电话</td><td></td></tr>
			                                      <tr><td>area</td><td><code>String</code></td><td>地区</td><td></td></tr>
			                                      <tr><td>receiver</td><td><code>String</code></td><td>收件人</td><td></td></tr>
			                                      <tr><td>mobilePhone</td><td><code>String</code></td><td>手机</td><td></td></tr>
			                                      <tr><td>detailAddress</td><td><code>String</code></td><td>固定电话</td><td></td></tr>
			                                      <tr><td>phone</td><td><code>String</code></td><td>固定电话</td><td></td></tr> 											             								             
									            </tbody>
										  </table>
										 <h3 id="2">订单信息列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>expressCompanyName</td><td><code>String</code></td><td>快递公司</td><td></td></tr>
			                                      <tr><td>postFee</td><td><code>String</code></td><td>邮费</td><td></td></tr>
			                                      <tr><td>submitDate</td><td><code>String</code></td><td>订单提交时间</td><td></td></tr>
			                                      <tr><td>sureReceiveMoneyDate</td><td><code>String</code></td><td>收款时间</td><td></td></tr>
			                                      <tr><td>orderDetailUrl</td><td><code>String</code></td><td>订单详情链接</td><td></td></tr>
			                                      <tr><td>actualFee</td><td><code>String</code></td><td>实际付款金额</td><td></td></tr>
			                                      <tr><td>orderType</td><td><code>String</code></td><td>订单类型</td><td></td></tr> 
			                                      <tr><td>orderStatusText</td><td><code>String</code></td><td>订单状态</td><td></td></tr> 
			                                      <tr><td>sumQuantity</td><td><code>String</code></td><td>总数量</td><td></td></tr> 
			                                      <tr><td>orderNo</td><td><code>String</code></td><td>订单编号</td><td></td></tr> 
			                                      <tr><td>price</td><td><code>String</code></td><td>宝贝价格</td><td></td></tr> 
			                                      <tr><td>shopName</td><td><code>String</code></td><td>店铺名称</td><td></td></tr> 
			                                      <tr><td>expressNo</td><td><code>String</code></td><td>快递号</td><td></td></tr> 
			                                      <tr><td>titles</td><td><code>String</code></td><td>宝贝标题</td><td></td></tr> 
			                                      <tr><td>receiveAddress</td><td><code>String</code></td><td>收货地址信息</td><td></td></tr> 
			                                      <tr><td>receiver</td><td><code>String</code></td><td>收件人</td><td></td></tr> 
			                                      <tr><td>receivePhoneNo</td><td><code>String</code></td><td>收件人电话</td><td></td></tr>  	
			                                      <tr><td>invoiceContent</td><td><code>String</code></td><td>发票内容</td><td></td></tr>  	
			                                      <tr><td>invoiceTitle</td><td><code>String</code></td><td>收件人</td><td></td></tr>  	
			                                      <tr><td>receiver</td><td><code>String</code></td><td>发票抬头</td><td></td></tr>  	
			                                      <tr><td>invoiceType</td><td><code>String</code></td><td>发票类型</td><td></td></tr>  	 											             								             
									            </tbody>
										  </table>
										  </div>
										  <div id="billData" style="display:none">										  
										  <h3 id="3">号码基本信息</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">名称</th><th style="width: 150px;">code</th>
									             </tr>
									            </thead>
									            <tbody>
									              <tr><td>数据获取时间</td><td><code>update_time</code></td></tr>
			                                      <tr><td>本机号码</td><td><code>cell_phone</code></td></tr>
			                                      <tr><td>登记身份证号</td><td><code>idcard</code></td></tr>
			                                      <tr><td>登记姓名</td><td><code>real_name</code></td></tr>
			                                      <tr><td>入网时间</td><td><code>reg_time</code></td></tr>	 											             								             
									            </tbody>
										  </table>	
										  <h3 id="4">流量信息</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">名称</th><th style="width: 150px;">code</th>
									             </tr>
									            </thead>
									            <tbody>
									              <tr><td>数据获取时间</td><td><code>update_time</code></td></tr>
			                                      <tr><td>本机号码</td><td><code>cell_phone</code></td></tr>
			                                      <tr><td>通话地点</td><td><code>place</code></td></tr>
			                                      <tr><td>本次使用花费</td><td><code>subtotal</code></td></tr>
			                                      <tr><td>流量使用量</td><td><code>subflow</code></td></tr>	
			                                      <tr><td>使用时间</td><td><code>start_time</code></td></tr>
			                                      <tr><td>流量类型</td><td><code>net_type</code></td></tr>
			                                      <tr><td>使用时长</td><td><code>use_time</code></td></tr>											             								             
									            </tbody>
										  </table>	
										  <h3 id="5">通话信息</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">名称</th><th style="width: 150px;">code</th>
									             </tr>
									            </thead>
									            <tbody>
									              <tr><td>数据获取时间</td><td><code>update_time</code></td></tr>
			                                      <tr><td>本机号码</td><td><code>cell_phone</code></td></tr>
			                                      <tr><td>通话地点</td><td><code>place</code></td></tr>
			                                      <tr><td>本次使用花费</td><td><code>subtotal</code></td></tr>
			                                      <tr><td>对方号码</td><td><code>other_cell_phone</code></td></tr>	
			                                      <tr><td>使用时间</td><td><code>start_time</code></td></tr>
			                                      <tr><td>呼叫类型</td><td><code>init_type</code></td></tr>
			                                      <tr><td>通话类型</td><td><code>call_type</code></td></tr>	
			                                      <tr><td>使用时长</td><td><code>use_time</code></td></tr>											             								             
									            </tbody>
										  </table>	
										  <h3 id="6">短信信息</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">名称</th><th style="width: 150px;">code</th>
									             </tr>
									            </thead>
									            <tbody>
									              <tr><td>数据获取时间</td><td><code>update_time</code></td></tr>
			                                      <tr><td>本机号码</td><td><code>cell_phone</code></td></tr>
			                                      <tr><td>通话地点</td><td><code>place</code></td></tr>
			                                      <tr><td>本次使用花费</td><td><code>subtotal</code></td></tr>
			                                      <tr><td>对方号码</td><td><code>other_cell_phone</code></td></tr>	
			                                      <tr><td>发送时间</td><td><code>start_time</code></td></tr>
			                                      <tr><td>发送类型</td><td><code>init_type</code></td></tr>											             								             
									            </tbody>
										  </table>
										  <h3 id="7">账单信息</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">名称</th><th style="width: 150px;">code</th>
									             </tr>
									            </thead>
									            <tbody>
									              <tr><td>数据获取时间</td><td><code>update_time</code></td></tr>
			                                      <tr><td>本机号码</td><td><code>cell_phone</code></td></tr>
			                                      <tr><td>账单月份</td><td><code>bill_cycle</code></td></tr>
			                                      <tr><td>计划花费</td><td><code>plan_amt</code></td></tr>
			                                      <tr><td>月付费</td><td><code>pay_amt</code></td></tr>											             								             
									            </tbody>
										  </table>
										  </div>
										  <div id="company" style="display:none">		
										  <h3 id="8">公司基本信息</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>name</td><td><code>String</code></td><td>公司名称</td><td></td></tr>
			                                      <tr><td>regNumber</td><td><code>String</code></td><td>工商注册号</td><td></td></tr>
			                                      <tr><td>keyMan</td><td><code>String</code></td><td>企业为法定代表人姓名,个体户为负责人姓名</td><td></td></tr>
			                                      <tr><td>regCap</td><td><code>decimal</code></td><td>注册资本</td><td>单位:万元</td></tr>
			                                      <tr><td>currency</td><td><code>String</code></td><td>币种</td><td>例如人民币</td></tr>
			                                      <tr><td>entStatus</td><td><code>String</code></td><td>营业状态</td><td>在营、吊销等</td></tr>
			                                      <tr><td>entType</td><td><code>String</code></td><td>企业类型</td><td>有限责任公司等</td></tr> 
			                                      <tr><td>esDate</td><td><code>String</code></td><td>开业日期</td><td></td></tr> 
			                                      <tr><td>opFrom</td><td><code>String</code></td><td>经营期限起</td><td></td></tr> 
			                                      <tr><td>opTo</td><td><code>String</code></td><td>经营期限止</td><td></td></tr> 
			                                      <tr><td>address</td><td><code>String</code></td><td>注册地址</td><td></td></tr> 
			                                      <tr><td>regOrg</td><td><code>String</code></td><td>登记机关</td><td></td></tr> 
			                                      <tr><td>abuItem</td><td><code>String</code></td><td>许可经营项目</td><td></td></tr> 
			                                      <tr><td>cbuItem</td><td><code>String</code></td><td>一般经营项目</td><td></td></tr> 
			                                      <tr><td>opScopeAndForm</td><td><code>String</code></td><td>经营(业务)范围及方式</td><td></td></tr> 
			                                      <tr><td>lastCheckYear</td><td><code>int</code></td><td>最后年检年度</td><td></td></tr> 
			                                      <tr><td>canDate</td><td><code>datetime</code></td><td>注销日期</td><td></td></tr>  	
			                                      <tr><td>revDate</td><td><code>datetime</code></td><td>吊销日期</td><td></td></tr>  	
			                                      <tr><td>industryPhyCode</td><td><code>String</code></td><td>行业门类代码</td><td></td></tr>  	
			                                      <tr><td>industryCoCode</td><td><code>String</code></td><td>国民经济行业代码</td><td></td></tr>  	  	 											             								             
									            </tbody>
										  </table>	
										  <h3 id="9">公司股东信息列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>name</td><td><code>String</code></td><td>股东名称</td><td></td></tr>
			                                      <tr><td>subContribution</td><td><code>decimal</code></td><td>认缴出资额</td><td>单位:万元</td></tr>
			                                      <tr><td>conPercent</td><td><code>String</code></td><td>出资比例</td><td></td></tr>
			                                      <tr><td>currency</td><td><code>String</code></td><td>币种</td><td>例如人民币</td></tr>
			                                      <tr><td>conDate</td><td><code>datetime</code></td><td>出资日期</td><td></td></tr> 											             								             
									            </tbody>
										  </table>
										  <h3 id="10">公司高管信息列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>name</td><td><code>String</code></td><td>人员姓名</td><td></td></tr>
			                                      <tr><td>position</td><td><code>String</code></td><td>职务</td><td></td></tr>										             								             
									            </tbody>
										  </table>
										  <h3 id="11">失信被执行人信息列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>caseCode</td><td><code>String</code></td><td>案号</td><td></td></tr>
			                                      <tr><td>name</td><td><code>String</code></td><td>被执行人姓名</td><td></td></tr>
			                                      <tr><td>idNumber</td><td><code>String</code></td><td>身份证号码/企业注册号</td><td></td></tr>	
			                                      <tr><td>sex</td><td><code>String</code></td><td>性别</td><td></td></tr>
			                                      <tr><td>age</td><td><code>int</code></td><td>年龄</td><td></td></tr>
			                                      <tr><td>areaName</td><td><code>String</code></td><td>省份</td><td></td></tr>
			                                      <tr><td>issuingAuthority</td><td><code>String</code></td><td>身份证签发地</td><td></td></tr>
			                                      <tr><td>courtName</td><td><code>String</code></td><td>执行法院</td><td></td></tr>
			                                      <tr><td>regDate</td><td><code>datetime</code></td><td>立案时间</td><td></td></tr>
			                                      <tr><td>caseStatus</td><td><code>datetime</code></td><td>案件状态</td><td></td></tr>
			                                      <tr><td>executeMoney</td><td><code>decimal</code></td><td>执行标的</td><td></td></tr>
			                                      <tr><td>focusCount</td><td><code>int</code></td><td>关注次数</td><td></td></tr>														             								             
									            </tbody>
										  </table>
										  </div>
										  <div id="taobaoItem"  style="display:none">
										  <h3 id="12">商品详情</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>name</td><td><code>String</code></td><td>宝贝名称</td><td></td></tr>
			                                      <tr><td>quanity</td><td><code>int</code></td><td>已售出数量</td><td></td></tr>
			                                      <tr><td>itemLink</td><td><code>String</code></td><td>商品地址</td><td></td></tr>	
			                                      <tr><td>shopLink</td><td><code>String</code></td><td>店铺地址</td><td></td></tr>
			                                      <tr><td>confirmGoods</td><td><code>int</code></td><td>交易成功数量</td><td></td></tr>
			                                      <tr><td>price</td><td><code>int</code></td><td>价格</td><td></td></tr>
			                                      <tr><td>insertTime</td><td><code>datetime</code></td><td>入库时间</td><td></td></tr>													             								             
									            </tbody>
										  </table>
										  </div>
										  <div id="personinvestment" style="display:none">
										  <h3 id="13">对外投资的企业信息列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>name</td><td><code>String</code></td><td>投资人</td><td></td></tr>
			                                      <tr><td>entName</td><td><code>String</code></td><td>身份证号</td><td></td></tr>
			                                      <tr><td>regNumber</td><td><code>String</code></td><td>企业工商注册号</td><td></td></tr>	
			                                      <tr><td>regCap</td><td><code>Decimal</code></td><td>注册资本</td><td></td></tr>
			                                      <tr><td>currency</td><td><code>String</code></td><td>币种</td><td></td></tr>
			                                      <tr><td>entStatus</td><td><code>String</code></td><td>企业经营状态</td><td></td></tr>
			                                      <tr><td>entType</td><td><code>String</code></td><td>企业类型</td><td></td></tr>
			                                      <tr><td>isLegalPerson</td><td><code>Bool</code></td><td>是否为法定代表人</td><td></td></tr>	
			                                      <tr><td>isShareHolder</td><td><code>Bool</code></td><td>是否为股东</td><td></td></tr>	
			                                      <tr><td>subContribution</td><td><code>Decimal</code></td><td>认缴出资币种</td><td></td></tr>	
			                                      <tr><td>contributionCurrency</td><td><code>String</code></td><td>企业类型</td><td></td></tr>	
			                                      <tr><td>isManager</td><td><code>Bool</code></td><td>是否为高管</td><td></td></tr>	
			                                      <tr><td>position</td><td><code>Bool</code></td><td>担任职位</td><td></td></tr>														             								             
									            </tbody>
										  </table>	
										  <h3 id="14">失信被执行人信息列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>caseCode</td><td><code>String</code></td><td>案号</td><td></td></tr>
			                                      <tr><td>punishedName</td><td><code>String</code></td><td>被执行人姓名</td><td></td></tr>
			                                      <tr><td>idNumber</td><td><code>String</code></td><td>身份证号码/企业注册号</td><td></td></tr>	
			                                      <tr><td>sex</td><td><code>String</code></td><td>性别</td><td></td></tr>
			                                      <tr><td>age</td><td><code>int</code></td><td>年龄</td><td></td></tr>
			                                      <tr><td>areaName</td><td><code>String</code></td><td>省份</td><td></td></tr>
			                                      <tr><td>issuingAuthority</td><td><code>String</code></td><td>身份证签发地</td><td></td></tr>
			                                      <tr><td>courtName</td><td><code>String</code></td><td>执行法院</td><td></td></tr>
			                                      <tr><td>regDate</td><td><code>datetime</code></td><td>立案时间</td><td></td></tr>
			                                      <tr><td>caseStatus</td><td><code>String</code></td><td>案件状态</td><td></td></tr>													             								             
									            </tbody>
										  </table>
										  </div>
										  <div id="court" style="display:none">
										  <h3 id="15">法院被执行人信息列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>name</td><td><code>String</code></td><td>姓名</td><td></td></tr>
			                                      <tr><td>idNumber</td><td><code>String</code></td><td>被执行人姓名</td><td></td></tr>
			                                      <tr><td>id</td><td><code>String</code></td><td>身份证号码/企业注册号</td><td></td></tr>	
			                                      <tr><td>caseCode</td><td><code>String</code></td><td>案号</td><td></td></tr>
			                                      <tr><td>caseState</td><td><code>int</code></td><td>被执行人的履行情况</td><td></td></tr>
			                                      <tr><td>execCourtName</td><td><code>String</code></td><td>执行法院</td><td></td></tr>
			                                      <tr><td>execMoney</td><td><code>String</code></td><td>执行标的</td><td></td></tr>
			                                      <tr><td>caseCreateTime</td><td><code>String</code></td><td>立案时间</td><td></td></tr>
			                                      <tr><td>sex</td><td><code>String</code></td><td>性别</td><td></td></tr>
			                                      <tr><td>age</td><td><code>String</code></td><td>年龄</td><td></td></tr>	
			                                      <tr><td>age</td><td><code>String</code></td><td>年龄</td><td></td></tr>	
			                                      <tr><td>province</td><td><code>String</code></td><td>省份</td><td></td></tr>
			                                      <tr><td>execBasis</td><td><code>String</code></td><td>执行依据文号</td><td></td></tr>
			                                      <tr><td>execBasisCompany</td><td><code>String</code></td><td>做出执行依据单位</td><td></td></tr>
			                                      <tr><td>obligation</td><td><code>String</code></td><td>生效法律文书确定的义务</td><td></td></tr>
			                                      <tr><td>concreteSituation</td><td><code>String</code></td><td>失信被执行人行为具体情形</td><td></td></tr>	
			                                      <tr><td>releaseTime</td><td><code>String</code></td><td>发布时间</td><td></td></tr>	
			                                      <tr><td>collectionInfo</td><td><code>String</code></td><td>采集信息地址</td><td></td></tr>	
			                                      <tr><td>writeTime</td><td><code>String</code></td><td>写入时间</td><td></td></tr>
			                                      <tr><td>updateTime</td><td><code>String</code></td><td>更新时间</td><td></td></tr>														             								             
									            </tbody>
										  </table>
										  </div>
										  <div id="personalreport" style="display:none">
										  <h3 id="16">资产指标信息列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>hasHouse</td><td><code>boolean</code></td><td>是否有房产</td><td></td></tr>
			                                      <tr><td>hasCar</td><td><code>boolean</code></td><td>是否有车</td><td></td></tr>
			                                      <tr><td>housePurTime</td><td><code>String</code></td><td>房产购买日期</td><td></td></tr>	
			                                      <tr><td>carPurTime</td><td><code>String</code></td><td>汽车购买日期</td><td></td></tr>
			                                      <tr><td>houseValue</td><td><code>Double</code></td><td>房产预估价值</td><td></td></tr>
			                                      <tr><td>carValue</td><td><code>Double</code></td><td>汽车预估价值</td><td></td></tr>
			                                      <tr><td>RepaymentAbility</td><td><code>String</code></td><td>还贷能力区间</td><td></td></tr>													             								             
									            </tbody>
										  </table>
										  <h3 id="17">交易行为特征列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>businessTrip</td><td><code>boolean</code></td><td>有无出差消费</td><td></td></tr>
			                                      <tr><td>marriageConsume</td><td><code>boolean</code></td><td>有无婚庆消费</td><td></td></tr>
			                                      <tr><td>employed</td><td><code>boolean</code></td><td>是否就业</td><td></td></tr>	
			                                      <tr><td>childInvest</td><td><code>boolean</code></td><td>有无母婴/教育投资</td><td></td></tr>
			                                      <tr><td>nightConsume</td><td><code>boolean</code></td><td>有无夜消费</td><td></td></tr>
			                                      <tr><td>city</td><td><code>String</code></td><td>常住城市</td><td></td></tr>
			                                      <tr><td>workRegion</td><td><code>String</code></td><td>工作时间消费区域</td><td></td></tr>
			                                      <tr><td>freeRegion</td><td><code>String</code></td><td>非工作时间消费区域</td><td></td></tr>													             								             
									            </tbody>
										  </table>	
										  <h3 id="18">每月消费状况列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>month</td><td><code>String</code></td><td>月份</td><td></td></tr>
			                                      <tr><td>amount</td><td><code>Double</code></td><td>总消费金额</td><td></td></tr>
			                                      <tr><td>count</td><td><code>int</code></td><td>总消费笔数</td><td></td></tr>	
			                                      <tr><td>amountRanking</td><td><code>Double</code></td><td>消费金额在本市的排名</td><td></td></tr>
			                                      <tr><td>countRanking</td><td><code>Double</code></td><td>消费笔数在本市的排名</td><td></td></tr>												             								             
									            </tbody>
										  </table>
										  <h3 id="19">消费地域分布列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>name</td><td><code>String</code></td><td>城市</td><td></td></tr>
			                                      <tr><td>amount</td><td><code>Double</code></td><td>总消费金额</td><td></td></tr>
			                                      <tr><td>count</td><td><code>int</code></td><td>总消费笔数</td><td></td></tr>											             								             
									            </tbody>
										  </table>
										  <h3 id="20">消费大类分布列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>name</td><td><code>String</code></td><td>类别名称</td><td></td></tr>
			                                      <tr><td>amount</td><td><code>Double</code></td><td>总消费金额</td><td></td></tr>
			                                      <tr><td>count</td><td><code>int</code></td><td>总消费笔数</td><td></td></tr>											             								             
									            </tbody>
										  </table>
										  <h3 id="21">信用相关交易统计列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>name</td><td><code>String</code></td><td>类别名称</td><td></td></tr>
			                                      <tr><td>amount</td><td><code>Double</code></td><td>总消费金额</td><td></td></tr>
			                                      <tr><td>count</td><td><code>int</code></td><td>总消费笔数</td><td></td></tr>											             								             
									            </tbody>
										  </table>
										  </div>
										  <div id="chsi" style="display:none">
										  <h3 id="22">学历信息列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>name</td><td><code>String</code></td><td>姓名</td><td></td></tr>
			                                      <tr><td>sex</td><td><code>int</code></td><td>性别</td><td>0 女  1 男</td></tr>
			                                      <tr><td>educationallevel</td><td><code>String</code></td><td>学历层次</td><td></td></tr>
			                                      <tr><td>certificatenumber</td><td><code>String</code></td><td>证书编号</td><td></td></tr>	
			                                      <tr><td>birthday</td><td><code>Date</code></td><td>出生日期</td><td></td></tr>
			                                      <tr><td>category</td><td><code>String</code></td><td>学历类别</td><td></td></tr>
			                                      <tr><td>learningform</td><td><code>String</code></td><td>学习形式</td><td></td></tr>
			                                      <tr><td>schoolname</td><td><code>String</code></td><td>学校名称</td><td></td></tr>
			                                      <tr><td>professionalname</td><td><code>String</code></td><td>专业名称</td><td></td></tr>
			                                      <tr><td>Instituteaddress</td><td><code>String</code></td><td>院校所在地</td><td></td></tr>
			                                      <tr><td>entrancetime</td><td><code>Date</code></td><td>入学时间</td><td></td></tr>
			                                      <tr><td>graduationtime</td><td><code>Date</code></td><td>毕业时间</td><td></td></tr>
			                                      <tr><td>conclusion</td><td><code>String</code></td><td>毕结业结论</td><td></td></tr>												             								             
									            </tbody>
										  </table>
										  <h3 id="23">学籍信息列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>name</td><td><code>String</code></td><td>姓名</td><td></td></tr>
			                                      <tr><td>sex</td><td><code>int</code></td><td>性别</td><td>0 女  1 男</td></tr>
			                                      <tr><td>idnumber</td><td><code>String</code></td><td>身份证号</td><td></td></tr>
			                                      <tr><td>educationallevel</td><td><code>String</code></td><td>学历层次</td><td></td></tr>	
			                                      <tr><td>studyno</td><td><code>String</code></td><td>学号</td><td></td></tr>
			                                      <tr><td>category</td><td><code>String</code></td><td>学历类别</td><td></td></tr>
			                                      <tr><td>birthday</td><td><code>Date</code></td><td>出生日期</td><td></td></tr>
			                                      <tr><td>category</td><td><code>String</code></td><td>学历类别</td><td></td></tr>
			                                      <tr><td>learningform</td><td><code>String</code></td><td>学校名称</td><td></td></tr>
			                                      <tr><td>schoolname</td><td><code>String</code></td><td>院校所在地</td><td></td></tr>
			                                      <tr><td>professionalname</td><td><code>String</code></td><td>专业名称</td><td></td></tr>
			                                      <tr><td>entrancetime</td><td><code>Date</code></td><td>入学时间</td><td></td></tr>
			                                      <tr><td>departuredate</td><td><code>Date</code></td><td>离校时间</td><td></td></tr>
			                                      <tr><td>department</td><td><code>String</code></td><td>系(所、函授站)</td><td></td></tr>
			                                      <tr><td>class</td><td><code>String</code></td><td>班级</td><td></td></tr>
			                                      <tr><td>nation</td><td><code>String</code></td><td>民族</td><td></td></tr>
			                                      <tr><td>examineenumber</td><td><code>String</code></td><td>考生号</td><td></td></tr>
			                                      <tr><td>educationalsystem</td><td><code>int</code></td><td>学制</td><td></td></tr>
			                                      <tr><td>branch</td><td><code>String</code></td><td>分院</td><td></td></tr>
			                                      <tr><td>status</td><td><code>String</code></td><td>学籍状态</td><td></td></tr>												             								             
									            </tbody>
										  </table>
										  </div>
										  <div id="taobaoAccount" style="display:none">
										  <h3 id="24">地址信息列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>phone</td><td><code>String</code></td><td>电话号码</td><td></td></tr>
			                                      <tr><td>area</td><td><code>String</code></td><td>地区</td><td></td></tr>
			                                      <tr><td>receiver</td><td><code>String</code></td><td>收件人</td><td></td></tr>
			                                      <tr><td>zipCode</td><td><code>String</code></td><td>邮编</td><td></td></tr>	
			                                      <tr><td>detailAddress</td><td><code>String</code></td><td>详细地址</td><td></td></tr>												             								             
									            </tbody>
										  </table>
										  <h3 id="25">订单信息列表</h3>	
										 <table class="table table-bordered table-striped">
									            <thead>
									             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
									            </thead>
									            <tbody>
									              <tr><td>expressCompanyName</td><td><code>String</code></td><td>快递公司</td><td></td></tr>
			                                      <tr><td>postFee</td><td><code>String</code></td><td>邮费</td><td></td></tr>
			                                      <tr><td>createTime</td><td><code>String</code></td><td>订单创建时间</td><td></td></tr>
			                                      <tr><td>orderDetailUrl</td><td><code>String</code></td><td>订单详情链接</td><td></td></tr>	
			                                      <tr><td>actualFee</td><td><code>String</code></td><td>实际付款金额</td><td></td></tr>
			                                      <tr><td>orderType</td><td><code>String</code></td><td>订单类型</td><td></td></tr>
			                                      <tr><td>orderStatusText</td><td><code>String</code></td><td>订单状态</td><td></td></tr>
			                                      <tr><td>buyerMessage</td><td><code>String</code></td><td>买家留言</td><td></td></tr>
			                                      <tr><td>sumQuantity</td><td><code>String</code></td><td>总数量</td><td></td></tr>
			                                      <tr><td>orderNo</td><td><code>String</code></td><td>订单编号</td><td></td></tr>
			                                      <tr><td>price</td><td><code>String</code></td><td>宝贝价格</td><td></td></tr>
			                                      <tr><td>sellerId</td><td><code>String</code></td><td>店铺编号</td><td></td></tr>
			                                      <tr><td>shopName</td><td><code>String</code></td><td>店铺名称</td><td></td></tr>
			                                      <tr><td>expressNo</td><td><code>String</code></td><td>快递号</td><td></td></tr>
			                                      <tr><td>titles</td><td><code>String</code></td><td>宝贝标题</td><td></td></tr>
			                                      <tr><td>receiveAddress</td><td><code>String</code></td><td>收货地址信息</td><td></td></tr>												             								             
									            </tbody>
										  </table>
										  </div>
										  <div id="nineDetail" style="display:none">
											  <h3 id="26">检查结果列表</h3>	
											 <table class="table table-bordered table-striped">
										            <thead>
										             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
										            </thead>
										            <tbody>
										              <tr><td>serialNumber</td><td><code>String</code></td><td>序号</td><td></td></tr>
				                                      <tr><td>checkItem</td><td><code>String</code></td><td>检查项</td><td></td></tr>
				                                      <tr><td>checkResult</td><td><code>String</code></td><td>检查结果</td><td></td></tr>
				                                      <tr><td>checkNote</td><td><code>String</code></td><td>检查备注</td><td></td></tr>											             								             
										            </tbody>
											  </table>
										  </div>
										  <div id="reportDetail" style="display:none">
										  	<h3 id="27">基本信息列表</h3>	
											 <table class="table table-bordered table-striped">
										            <thead>
										             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
										            </thead>
										            <tbody>
										              <tr><td>idNumber</td><td><code>String</code></td><td>身份证号码</td><td></td></tr>
				                                      <tr><td>gender</td><td><code>String</code></td><td>性别</td><td></td></tr>
				                                      <tr><td>phoneNumber</td><td><code>String</code></td><td>注册手机号</td><td></td></tr>
				                                      <tr><td>name</td><td><code>String</code></td><td>真实姓名</td><td></td></tr>
				                                      <tr><td>nativeProvince</td><td><code>String</code></td><td>籍贯省份</td><td></td></tr>	
				                                      <tr><td>birthPlace</td><td><code>String</code></td><td>出生地，包含省份,城市,区县</td><td></td></tr>	
				                                      <tr><td>age</td><td><code>int</code></td><td>年龄</td><td></td></tr>	
				                                      <tr><td>address</td><td><code>String</code></td><td>居住地</td><td></td></tr>	
				                                      <tr><td>iDCardValid</td><td><code>String</code></td><td>身份证解析状态</td><td></td></tr>	
				                                      <tr><td>emergencyContacts</td><td><code>List</code></td><td>紧急联系人</td><td>详见附录<a href="#34">紧急联系人列表</a></td></tr>											             								             
										            </tbody>
											  </table>
											  <h3 id="34">紧急联系人列表</h3>	
											 <table class="table table-bordered table-striped">
										            <thead>
										             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
										            </thead>
										            <tbody>
										              <tr><td>userId</td><td><code>String</code></td><td>用户id</td><td></td></tr>
				                                      <tr><td>realName</td><td><code>String</code></td><td>姓名</td><td></td></tr>
				                                      <tr><td>relationship</td><td><code>String</code></td><td>关系</td><td></td></tr>
				                                      <tr><td>phoneNumber</td><td><code>String</code></td><td>联系电话</td><td></td></tr>										             								             
										            </tbody>
											  </table>
											  <h3 id="28">数据源信息列表</h3>	
											 <table class="table table-bordered table-striped">
										            <thead>
										             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
										            </thead>
										            <tbody>
										              <tr><td>dataSourceIdentification</td><td><code>String</code></td><td>数据源标识</td><td></td></tr>
				                                      <tr><td>dataSourceName</td><td><code>String</code></td><td>数据源名称</td><td></td></tr>
				                                      <tr><td>accountName</td><td><code>String</code></td><td>账号名称</td><td></td></tr>
				                                      <tr><td>datatype</td><td><code>String</code></td><td>数据类型</td><td></td></tr>	
				                                      <tr><td>datatypeName</td><td><code>String</code></td><td>数据类型名称</td><td></td></tr>
				                                      <tr><td>dataValidity</td><td><code>String</code></td><td>数据有效性</td><td></td></tr>
				                                      <tr><td>dataReliability</td><td><code>String</code></td><td>数据可靠性</td><td></td></tr>
				                                      <tr><td>bindingTime</td><td><code>String</code></td><td>绑定时间</td><td></td></tr>									             								             
										            </tbody>
											  </table>
											  <h3 id="29">信息核对列表</h3>	
											 <table class="table table-bordered table-striped">
										            <thead>
										             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
										            </thead>
										            <tbody>
										              <tr><td>checkpoint</td><td><code>String</code></td><td>分析点</td><td></td></tr>
				                                      <tr><td>checkResult</td><td><code>String</code></td><td>检查结果</td><td></td></tr>
				                                      <tr><td>proof</td><td><code>String</code></td><td>证据</td><td></td></tr>
				                                      <tr><td>mark</td><td><code>int</code></td><td></td><td></td></tr>									             								             
										            </tbody>
											  </table>
											  <h3 id="30">推断分析列表</h3>	
											 <table class="table table-bordered table-striped">
										            <thead>
										             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
										            </thead>
										            <tbody>
										              <tr><td>checkpoint</td><td><code>String</code></td><td>分析点</td><td></td></tr>
				                                      <tr><td>checkResult</td><td><code>String</code></td><td>检查结果</td><td></td></tr>
				                                      <tr><td>proof</td><td><code>String</code></td><td>证据</td><td></td></tr>
				                                      <tr><td>mark</td><td><code>int</code></td><td></td><td></td></tr>									             								             
										            </tbody>
											  </table>
											  <h3 id="31">联系人信息列表</h3>	
											 <table class="table table-bordered table-striped">
										            <thead>
										             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
										            </thead>
										            <tbody>
										              <tr><td>name</td><td><code>String</code></td><td>联系人信息</td><td></td></tr>
				                                      <tr><td>firstCallTime</td><td><code>String</code></td><td>最早联系时间</td><td></td></tr>
				                                      <tr><td>lastCallTime</td><td><code>String</code></td><td>最晚联系时间</td><td></td></tr>
				                                      <tr><td>cellNumber</td><td><code>String</code></td><td>联系电话</td><td></td></tr>
				                                      <tr><td>recentCommunications</td><td><code>String</code></td><td>近半年通话次数、时长、短信次数</td><td></td></tr>										             								             
										            </tbody>
											  </table>
											  <h3 id="32">话单数据分析列表</h3>	
											 <table class="table table-bordered table-striped">
										            <thead>
										             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
										            </thead>
										            <tbody>
										              <tr><td>phoneCallDataObjects</td><td><code>List</code></td><td>通话时段分析</td><td>详见附录<a href="#35">通话时段分析列表</a></td></tr>
				                                      <tr><td>industryPhoneCallDataObject</td><td><code>List</code></td><td>行业通话数据分析</td><td>详见附录<a href="#36">行业通话数据分析列表</a></td></tr>
				                                      <tr><td>regionPhoneCallDataObject</td><td><code>List</code></td><td>联系人所在地区</td><td>详见附录<a href="#37">联系人所在地区列表</a></td></tr>
				                                      <tr><td>phoneMonthlyUsageObject</td><td><code>List</code></td><td>月使用情况</td><td>详见附录<a href="#38">月使用情况列表</a></td></tr>
				                                      <tr><td>recentDemandsObject</td><td><code>List</code></td><td>近期需求</td><td>详见附录<a href="#39">近期需求列表</a></td></tr>										             								             
										            </tbody>
											  </table>
											  <h3 id="35">通话时段分析列表</h3>	
											 <table class="table table-bordered table-striped">
										            <thead>
										             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
										            </thead>
										            <tbody>
										              <tr><td>phoneCallTimeSlot</td><td><code>String</code></td><td>时间</td><td></td></tr>
				                                      <tr><td>numberOfCalling</td><td><code>int</code></td><td>主叫次数</td><td></td></tr>
				                                      <tr><td>callingTotalLength</td><td><code>long</code></td><td>主叫时长（秒）</td><td></td></tr>
				                                      <tr><td>numberOfCalled</td><td><code>int</code></td><td>被叫次数</td><td></td></tr>
				                                      <tr><td>calledTotalLength</td><td><code>long</code></td><td>被叫时长（秒）</td><td></td></tr>										             								             
										            </tbody>
											  </table>
											  <h3 id="36">行业通话数据分析列表</h3>	
											 <table class="table table-bordered table-striped">
										            <thead>
										             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
										            </thead>
										            <tbody>
										              <tr><td>industryType</td><td><code>String</code></td><td>行业类别</td><td></td></tr>
				                                      <tr><td>numberOfCalls</td><td><code>int</code></td><td>通话次数</td><td></td></tr>
				                                      <tr><td>callsTotalLength</td><td><code>long</code></td><td>通话时长（分）</td><td></td></tr>
				                                      <tr><td>numberOfCalling</td><td><code>int</code></td><td>主叫次数</td><td></td></tr>
				                                      <tr><td>numberOfCalled</td><td><code>int</code></td><td>被叫次数</td><td></td></tr>										             								             
										            </tbody>
											  </table>
											  <h3 id="37">联系人所在地区列表</h3>	
											 <table class="table table-bordered table-striped">
										            <thead>
										             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
										            </thead>
										            <tbody>
										              <tr><td>region</td><td><code>String</code></td><td>地区</td><td></td></tr>
				                                      <tr><td>numberOfContacts</td><td><code>int</code></td><td>号码数量</td><td></td></tr>
				                                      <tr><td>numberOfCalled</td><td><code>int</code></td><td>电话呼入次数</td><td></td></tr>
				                                      <tr><td>calledTotalLength</td><td><code>long</code></td><td>电话呼入时长（分）</td><td></td></tr>
				                                      <tr><td>numberOfCalling</td><td><code>int</code></td><td>电话呼出次数</td><td></td></tr>
				                                      <tr><td>callingTotalLength</td><td><code>long</code></td><td>电话呼出时长（分）</td><td></td></tr>										             								             
										            </tbody>
											  </table>
											  <h3 id="38">月使用情况列表</h3>	
											 <table class="table table-bordered table-striped">
										            <thead>
										             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
										            </thead>
										            <tbody>
										              <tr><td>operator</td><td><code>String</code></td><td>运营商</td><td></td></tr>
				                                      <tr><td>number</td><td><code>String</code></td><td>号码</td><td></td></tr>
				                                      <tr><td>region</td><td><code>String</code></td><td>归属地</td><td></td></tr>
				                                      <tr><td>month</td><td><code>String</code></td><td>月份</td><td></td></tr>
				                                      <tr><td>callingTotalLength</td><td><code>long</code></td><td>主叫时间（分）</td><td></td></tr>
				                                      <tr><td>calledTotalLength</td><td><code>long</code></td><td>被叫时间（分）</td><td></td></tr>
				                                      <tr><td>numberOfSMS</td><td><code>int</code></td><td>短信数量</td><td></td></tr>
				                                      <tr><td>dataTraffic</td><td><code>double</code></td><td>数据流量</td><td></td></tr>										             								             
										            </tbody>
											  </table>
											  <h3 id="39">近期需求列表</h3>	
											 <table class="table table-bordered table-striped">
										            <thead>
										             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
										            </thead>
										            <tbody>
										              <tr><td>yearAndMonth</td><td><code>String</code></td><td>年月份，yyyy年MM月</td><td></td></tr>
				                                      <tr><td>requirement</td><td><code>String</code></td><td>需求</td><td></td></tr>
				                                      <tr><td>numberOfCalling</td><td><code>int</code></td><td>主叫次数</td><td></td></tr>
				                                      <tr><td>callingTotalLength</td><td><code>long</code></td><td>主叫时间（秒）</td><td></td></tr>
				                                      <tr><td>numberOfCalled</td><td><code>int</code></td><td>被叫次数</td><td></td></tr>
				                                      <tr><td>calledTotalLength</td><td><code>int</code></td><td>被叫时间（秒）</td><td></td></tr>								             								             
										            </tbody>
											  </table>
											  <h3 id="33">出行情况分析列表</h3>	
											 <table class="table table-bordered table-striped">
										            <thead>
										             <tr><th style="width: 100px;">参数</th><th style="width: 150px;">类型</th><th style="width: 450px;">释义</th><th>说明</th></tr>
										            </thead>
										            <tbody>
										              <tr><td>travelDayType</td><td><code>String</code></td><td>时间段</td><td></td></tr>
				                                      <tr><td>startDate</td><td><code>String</code></td><td>出发时间</td><td></td></tr>
				                                      <tr><td>endDate</td><td><code>String</code></td><td>回程时间</td><td></td></tr>
				                                      <tr><td>startArea</td><td><code>String</code></td><td>出发地</td><td></td></tr>
				                                      <tr><td>destination</td><td><code>String</code></td><td>目标</td><td></td></tr>								             								             
										            </tbody>
											  </table>
										  </div>					
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
<script src="${ctx }/js/request_manage.js"></script>
<script src="${ctx }/js/adminTree.js"></script>

	<script>
		jQuery(document).ready(function() {		
			App.setPage("dynamic_table");  //Set current page
			App.init(); //Initialise plugins and elements
	        FormWizard.init();
		});
		initTree("请求参数");
	</script>
</body>
</html>