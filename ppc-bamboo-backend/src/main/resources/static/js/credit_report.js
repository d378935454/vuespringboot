<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/WEB-INF/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="UTF-8">
		<title>信用报告</title>
		<link rel="stylesheet" href="${ctx }/resources/css/reportdetail/reportDetail.css" />
	    <script type="text/javascript" src="${ctx }/resources/js/reportdetail/echarts.min.js"></script> 
		<script type="text/javascript" src="${ctx }/resources/js/flot/jquery.min.js"></script>
		<script type="text/javascript" src="${ctx }/resources/js/reportdetail/vue.js"></script>
		<script type="text/javascript">
		var bathPath='${ctx}';
		var reportChartDto='${reportChartDto}';
		var rowKey='${reportId}';
		var name='${reportObject.basicInfoObject.name }';
		var phoneNumber='${reportObject.basicInfoObject.phoneNumber }';
		</script>
		
	</head>

	<body>
		<div class="ch_main" id="zx_data">
			<input type="hidden" id="reportId" name="reportId" value="${reportId}">
			<div class="banner">
				<c:if test="${reportObject.basicInfoObject.gender=='男'}">
				<img class="fl" src="${ctx }/resources/img/reportdetail/head_icon.png"/>
				</c:if>
				<c:if test="${reportObject.basicInfoObject.gender=='女'}">
				<img class="fl" src="${ctx }/resources/img/reportdetail/girl.png"/>
				</c:if>
				<div class="banner_info">
					<span class="name" >${reportObject.basicInfoObject.name }</span><br />
					<span class="id">ID：${reportObject.reportId }</span>
				</div>
				<div class="export" >
					<img style="padding-right: 30px" src="${ctx }/resources/img/reportdetail/export_icon.png" onclick="export_pdf()"/><br/>
					<span style="padding-right: 30px">报告日期:<fmt:formatDate value="${reportObject.reportTime }" pattern="yyyy年MM月dd日"/> </span>
				</div>
			</div>
			<div class="header clearfix">
				<i class="userinfo"></i>基本信息
			</div>
			<div class="dv_spec_info wp45 fl">
				<span>性别：${reportObject.basicInfoObject.gender }</span>
				<span>年龄：${reportObject.basicInfoObject.age }</span>
				<span>手机号：${reportObject.basicInfoObject.phoneNumber }</span>
				<span>身份证：${reportObject.basicInfoObject.idNumber }
				<i class="tooltip2" v-if="${reportObject.basicInfoObject.IDCardValid}" title="${reportObject.basicInfoObject.cardValidMsg}"></i>
				</span>
				<br />
				<span>居住地址： ${reportObject.basicInfoObject.address}</span>
				<span>QQ号：${reportObject.basicInfoObject.supplementDto.qq}  </span>
				<span>邮箱：${reportObject.basicInfoObject.supplementDto.mail}</span><br/>
				<span>信用卡号 :${reportObject.basicInfoObject.supplementDto.creditCardNum}</span>
				<span>工作地址: ${reportObject.basicInfoObject.supplementDto.workAddressFst} 
				 ${reportObject.basicInfoObject.supplementDto.workAddressSec} 
				  ${reportObject.basicInfoObject.supplementDto.workAddressDistrict} 
				 ${reportObject.basicInfoObject.supplementDto.workAddressDetail}
				</span>
			</div>
			<div class=" fr relative" style="width:600px;">
				<div class="fl " style="height:200px;width:280px" id="gauge_1"></div>
				<div class="tooltip_gauge" id="creditScore">信用评分<i class="scorehover"></i></div>
				<div class="tooltip_gauge_content scoreshow">
					信用评分:<span>${reportObject.score}</span>&nbsp;等级:<span></span>
					<br/>信用评分从600到700不等，分数越高请注明信用越好。
				</div>
				<div class="fr " style="height:200px;width:280px" id="gauge_2"></div>
				<div class="tooltip_gauge gauge_right">逾期率<i class="ratehover"></i></div>
				<div class="tooltip_gauge_content tooltip_gauge_right_content rateshow">
					逾期率:<span>${reportObject.grade }</span>
					<br/>逾期率表示该类型用户逾期的可能性仅供参考
				</div>
			</div>
			<div class="item mt10 clearfix">
				<div class="item_title">数据来源</div>
				<table class="item_table">
					<tr>
						<th>名称</th>
						<th>类别</th>
						<th>是否在数据源处完成实名认证</th>
						<th>数据源使用时长</th>
						<th>数据获取时间</th>
					</tr>
					<c:if test="${reportObject.dataBindingInfoObject!=null && reportObject.dataBindingInfoObject.size()>0 }">
						<c:forEach var="f" items="${reportObject.dataBindingInfoObject }" varStatus="s">																		
							<tr>
								<td>${f.dataSourceName }</td>
								<td>${f.datatype }</td>
								<td>${f.dataReliability }</td>
								<td>${f.dataUsageLength }</td>
								<td>${f.dataFetchTime}</td>
							</tr>
						</c:forEach>
					</c:if>		
						<c:if test="${reportObject.dataBindingInfoObject.size()==0 }">
							<tr><td colspan="5" align="center">暂&nbsp;无&nbsp;数&nbsp;据</td></tr>
						</c:if>		
				</table>
			</div>
			<div class="item wp52 fl">
				<div class="item_title">信息核对</div>
				<table class="item_table">
					<tr>
						<th>核对结果</th>
						<th>主项</th>
						<th>被核对象</th>
					</tr>
					<c:if test="${reportObject.dataCheckList!=null && reportObject.dataCheckList.size()>0 }">
						<c:forEach var="d" items="${reportObject.dataCheckList }" varStatus="s">																		
							<tr>
								<td><i class="state${d.resultType}"></i>${d.checkResult}</td>
								<td>${d.checkItem }</td>
								<td>${d.dataFrom }</td>
							</tr>
						</c:forEach>
					</c:if>		
						<c:if test="${reportObject.dataCheckList.size()==0 }">
							<tr><td colspan="3" align="center">暂&nbsp;无&nbsp;数&nbsp;据</td></tr>
						</c:if>	
				</table>
			</div>
			<div class="item wp40 fr">
				<div class="item_title">风险警示</div>
				<table class="item_table">
					<tr>
						<th>核对结果</th>
						<th>主项</th>
						<th>被核对象</th>
					</tr>
					<c:if test="${reportObject.riskWarningList!=null && reportObject.riskWarningList.size()>0 }">
						<c:forEach var="d" items="${reportObject.riskWarningList}" varStatus="s">																		
							<tr>
								<td><i class="state${d.resultType}"></i>${d.checkResult}</td>
								<td>${d.checkItem }</td>
								<td>${d.dataFrom }</td>
							</tr>
						</c:forEach>
					</c:if>		
						<c:if test="${reportObject.riskWarningList.size()==0 }">
							<tr><td colspan="3" align="center">暂&nbsp;无&nbsp;数&nbsp;据</td></tr>
						</c:if>	
				</table>
			</div>
			<c:if test="${reportDetailVlidateDto.hasDeductionAnalysisList==true}">
				<div class="item clearfix">
				<div class="item_title">推断分析</div>
				<table class="item_table">
					<tr>
						<th>检查项</th>
						<th>结果</th>
						<th>原因</th>
					</tr>
					<c:if test="${reportObject.deductionAnalysisList!=null && reportObject.deductionAnalysisList.size()>0 }">
						<c:forEach var="d" items="${reportObject.deductionAnalysisList}" varStatus="s">																		
							<tr>
								<td>${d.checkpoint}</td>
								<td>${d.checkResult}</td>
								<td><span>${d.proof}</span></td>
							</tr>
						</c:forEach>
					</c:if>		
						<c:if test="${reportObject.deductionAnalysisList.size()==0 }">
							<tr><td colspan="3" align="center">暂&nbsp;无&nbsp;数&nbsp;据</td></tr>
						</c:if>
				</table>
			</div>
			</c:if>
			<c:if test="${reportDetailVlidateDto.hasContactCheckList}">
				<div class="item">
				<div class="item_title">联系人信息</div>
				<table class="item_table">
					<tr>
						<th>姓名</th>
						<th>电话号码</th>
						<th>联系时间</th>
						<th>通话次数</th>
						<th>时长</th>
						<th>短信次数</th>
					</tr>
					<c:if test="${reportObject.contactCheckList!=null && reportObject.contactCheckList.size()>0 }">
						<c:forEach var="f" items="${reportObject.contactCheckList }" varStatus="s">																		
						  <tr>
							<td>${f.name }</td>
							<td>${f.cellNumber }</td>
							<td>${f.firstCallTime }~${f.lastCallTime }</td>
							<td>${f.callsCount }</td>
							<td>${f.callsLength }</td>
							<td>${f.smsCount }</td>
						 </tr>
					 </c:forEach>
				  </c:if>
					<c:if test="${reportObject.contactCheckList.size()==0 }">
						<tr><td colspan="5" align="center">暂&nbsp;无&nbsp;数&nbsp;据</td></tr>
					</c:if>	
				</table>
			</div>
			</c:if>
			
			<div class="header">
				<i class="mobile"></i>运营商
			</div>
			<div class="dv_spec wp52 fl">
				<span>律师号码：<label>${reportObject.specialNumberCall.lawyerCallCount}</label>次</span>
				<span>银行类：<label>${reportObject.specialNumberCall.bankCallCount}</label>次</span>
				<span>贷款类：<label>${reportObject.specialNumberCall.loanCallCount}</label>次</span>
				<span>澳门：<label>${reportObject.specialNumberCall.macaoCallCount}</label>次</span>
			</div>
			
				<div class="item wp52 mt10 fl">
				<div class="item_title">手机帐单</div>
				<table class="item_table">
					<tr>
						<th></th>
						<th>月份</th>
						<th>金额(元)</th>
						<th>主叫时长(分)</th>
						<th>被叫时长(分)</th>
						<th>流量(MB)</th>
					</tr>
					<c:if test="${reportObject.CDRAnalysisObject.phoneMonthlyUsageObject!=null && reportObject.CDRAnalysisObject.phoneMonthlyUsageObject.size()>0 }">
	             		<c:forEach var="f" items="${reportObject.CDRAnalysisObject.phoneMonthlyUsageObject }" varStatus="s">																		
						  <tr>
						   <c:if test="${f.warningInfo!=null&&f.warningInfo!=''}">
						   	 <td style="width:18px;"><span class="unusual" title="${f.warningInfo}"></span></td>
						   </c:if>
						   <c:if test="${f.warningInfo==null||f.warningInfo==''}"><td></td>
						   </c:if>
						  	<td>${f.month }</td>
							<td>${f.cost }</td>
							<td>${f.callingTotalLength }</td>
							<td>${f.calledTotalLength }</td>
							<td>${f.dataTraffic }</td>
							</tr>
						</c:forEach>
					</c:if>	
					<c:if test="${reportObject.CDRAnalysisObject.phoneMonthlyUsageObject.size()==0 }">
						<tr><td colspan="6" align="center">暂&nbsp;无&nbsp;数&nbsp;据</td></tr>
					</c:if>
				</table>
			</div>
			<div class="item wp40 fr">
				<div class="item_title">通话时段</div>
				<table class="item_table">
					<tr>
						<th>时间</th>
						<th>主叫次数</th>
						<th>主叫时长(分)</th>
						<th>被叫次数</th>
						<th>被叫时长(分)</th>
					</tr>
					<c:if test="${reportObject.CDRAnalysisObject.phoneCallDataObjects!=null && reportObject.CDRAnalysisObject.phoneCallDataObjects.size()>0 }">
						<c:forEach var="f" items="${reportObject.CDRAnalysisObject.phoneCallDataObjects }" varStatus="s">																		
						 <tr>
							<td>${f.phoneCallTimeSlot }</td>
							<td>${f.numberOfCalling }</td>
							<td>${f.callingTotalLength }</td>
							<td>${f.numberOfCalled }</td>
							<td>${f.calledTotalLength }</td>
						</tr>
						</c:forEach>
					</c:if>	
							<c:if test="${reportObject.CDRAnalysisObject.phoneCallDataObjects.size()==0 }">
								<tr><td colspan="5" align="center">暂&nbsp;无&nbsp;数&nbsp;据</td></tr>
							</c:if>	
				</table>
			</div>
			
			<c:if test="${reportDetailVlidateDto.hasRegionPhoneCallDataObject}">
				<div class="item clearfix">
				<div class="item_title">联系人所在地区</div>
				<div class="wp52  fl">
					<div class="map_title">
						号码数量<br />
						<span>低</span><span class="map_legend"></span><span>高</span>
					</div>
					<div id="map_chart" style="height:350px"></div>
				</div>
				<table class="item_table wp40 fr">
					<tr>
						<th>地区</th>
						<th>号码数量</th>
						<th>通话次数</th>
					</tr>
					<c:if test="${reportObject.CDRAnalysisObject.regionPhoneCallDataObject!=null && reportObject.CDRAnalysisObject.regionPhoneCallDataObject.size()>0 }">
	             		<c:forEach var="f" items="${reportObject.CDRAnalysisObject.regionPhoneCallDataObject }" varStatus="s">																		
						  <tr>
							<td>${f.region }</td>
							<td>${f.numberOfContacts }</td>
							<td>${f.numberOfCalls }</td>
						 </tr>
						</c:forEach>
					</c:if>		
					<c:if test="${reportObject.CDRAnalysisObject.regionPhoneCallDataObject.size()==0 }">
						<tr><td colspan="6" align="center">暂&nbsp;无&nbsp;数&nbsp;据</td></tr>
					</c:if>		
				</table>
			</div>	
			</c:if>
			
			<c:if test="${reportDetailVlidateDto.hasRecentDemandsObject}">
					<div class="item">
					<div class="item_title">商户分析
						<a class="expend_btn" v-if="${reportObject.CDRAnalysisObject.recentDemandsObject.size()>10}" v-on:click="expand('merchantTr',$event)"><span>展开</span><i></i></a>
					</div>
					<table class="item_table">
						<tr>
							<th>号码</th>
							<th>商户名</th>
							<th>行业</th>
							<th>通话次数</th>
							<th>主叫</th>
							<th>被叫</th>
							<th>通话时长(秒)</th>
							<th>最近联系时间</th>
						</tr>
						<c:if test="${reportObject.CDRAnalysisObject.recentDemandsObject!=null && reportObject.CDRAnalysisObject.recentDemandsObject.size()>0 }">
		             		<c:forEach var="f" items="${reportObject.CDRAnalysisObject.recentDemandsObject }" varStatus="s">																		
							  <tr v-bind:class="{'c33b6d0':d.isexsit}" name="merchantTr1">
								<td>${f.phoneNumber }</td>
								<td>${f.organization }</td>
								<td>${f.industry }</td>
								<td>${f.totalCalls }</td>
								<td>${f.numberOfCalling }</td>
								<td>${f.numberOfCalled }</td>													
								<td>${f.callTotalLength }</td>													
								<td>${f.lastContactTime }</td>													
							 </tr>
							</c:forEach>
		             		<c:forEach var="f" items="${reportObject.CDRAnalysisObject.subRecentDemandsObject}" varStatus="s">																		
							  <tr v-bind:class="{'c33b6d0':d.isexsit}" name="merchantTr2" style="display: none">
								<td>${f.phoneNumber }</td>
								<td>${f.organization }</td>
								<td>${f.industry }</td>
								<td>${f.totalCalls }</td>
								<td>${f.numberOfCalling }</td>
								<td>${f.numberOfCalled }</td>													
								<td>${f.callTotalLength }</td>													
								<td>${f.lastContactTime }</td>													
							 </tr>
							</c:forEach>
						</c:if>		
						<c:if test="${reportObject.CDRAnalysisObject.recentDemandsObject.size()==0 }">
							<tr><td colspan="6" align="center">暂&nbsp;无&nbsp;数&nbsp;据</td></tr>
						</c:if>	
					</table>
				</div>
			</c:if>
			
			<c:if test="${reportDetailVlidateDto.hasTravelAnalysisObjects}">
				<div class="item">
				<div class="item_title">外出情况<span class="icon_loc"><i></i>${reportObject.departure}</span>
					<a class="expend_btn" v-if="${reportObject.travelAnalysisObjects.size()>10}" v-on:click="expand('traveltr',$event)"><span>展开</span><i></i></a>
				</div>
				<table id="travelTable" class="item_table">
					<tr>
						<th>时间段</th>
						<th>出发日期</th>
						<th>回程日期</th>
						<th>时长(天)</th>
						<th>目的地</th>
						<th>途经地</th>
					</tr>
					<c:if test="${reportObject.travelAnalysisObjects!= null && reportObject.travelAnalysisObjects.size()>0}">
		             	<c:forEach var="f" items="${reportObject.subTravelAnalysisObjects }" varStatus="s">																		
						  <tr name="traveltr1">
							<td>${f.travelDayType }</td>
							<td>${f.startDate }</td>
							<td>${f.endDate }</td>
							<td>${f.days }</td>
							<td>${f.destination }</td>													
							<td>${f.waypointsStr }</td>
							</tr>
						</c:forEach>
						
		             	<c:forEach var="f" items="${reportObject.travelAnalysisObjects }" varStatus="s">																		
						  <tr name="traveltr2" style="display: none">
							<td>${f.travelDayType }</td>
							<td>${f.startDate }</td>
							<td>${f.endDate }</td>
							<td>${f.days }</td>
							<td>${f.destination }</td>													
							<td>${f.waypointsStr }</td>
							</tr>
						</c:forEach>
						<tr>
							<td></td>
						    <td></td>
						    <td align="right"><span style="font-weight:bold;">平均时长：</span></td>
						    <td>
							    <c:if test="${reportObject.travelAnalysisObjects!=null}">
							   	 	${reportObject.travelDeductionObject.avgTravelDays }
							    </c:if>
					    	</td>
						    <td></td>
						    <td></td>													
						    <td></td>
					 </tr>
								</c:if>
				<c:if test="${reportObject.travelAnalysisObjects.size()==0}">
					<tr><td colspan="6" align="center">暂&nbsp;无&nbsp;数&nbsp;据</td></tr>	
				</c:if>	
				</table>
			</div>
			</c:if>
			
			<c:if test="${reportDetailVlidateDto.hasLoanNet}">
				<div class="item">
				<div class="item_title">网贷类网站访问情况
					<a class="expend_btn" v-if="siteinfo_isexpand" v-on:click="expand('siteinfo',$event)"><span>展开</span><i></i></a>
				</div>
				<table class="item_table">
					<tr>
						<th>域名</th>
						<th>网站名称</th>
						<th>访问次数</th>
						<th>最近访问时间</th>
					</tr>
					<tr v-for="d in siteinfo">
						<td>{{d.domain}}</td>
						<td>{{d.sitename}}</td>
						<td>{{d.times}}</td>
						<td>{{d.lasttime}}</td>
					</tr>
				</table>
			</div>
			</c:if>
				<c:if test="${reportDetailVlidateDto.hasConsumptionPerMonthObjects||reportDetailVlidateDto.hasJDFinancialStatusObject||reportDetailVlidateDto.hasReceiverAnalysisObjects}">
				<div class="header">
					<i class="jd"></i>京东
				</div>
				</c:if>
				
				<c:if test="${reportDetailVlidateDto.hasJDFinancialStatusObject}">
					<div class="dv_spec">
						<span>总资产：<label>¥${reportObject.ECommerceDataObject.jdFinancialStatusObject.totalAsset}</label></span>
						<span>白条当前欠款：<label>¥${reportObject.ECommerceDataObject.jdFinancialStatusObject.totalObl}</label></span>
						<span>白条总额度：<label>¥${reportObject.ECommerceDataObject.jdFinancialStatusObject.totalBtMoney}</label></span>
						<span>信用评级：<label>${reportObject.ECommerceDataObject.jdFinancialStatusObject.creditLevel}</label></span>
					</div>
				</c:if>
				<c:if test="${reportDetailVlidateDto.hasConsumptionPerMonthObjects}">
				<div class="item clearfix">
				<div class="item wp52 fl mt10" style="min-height:350px">
					<div class="item_title">消费分析</div>
					<table class="item_table" >
						<tr>
							<th>月份</th>
							<th>订单数量(笔)</th>
							<th>消费金额(元)</th>
						</tr>
						<c:if test="${reportObject.ECommerceDataObject.consumptionPerMonthObjects!=null && reportObject.ECommerceDataObject.consumptionPerMonthObjects.size()>0 }">
		             		<c:forEach var="f" items="${reportObject.ECommerceDataObject.consumptionPerMonthObjects }" varStatus="s">																		
								<td>${f.month }</td>
								<td>${f.ordersCount }</td>
								<td>${f.monthCost }</td>
							 </tr>
							</c:forEach>
						</c:if>		
						<c:if test="${reportObject.ECommerceDataObject.consumptionPerMonthObjects.size()==0 }">
							<tr><td colspan="6" align="center">暂&nbsp;无&nbsp;数&nbsp;据</td></tr>
						</c:if>	
					</table>
				</div>
				</c:if>	
				<c:if test="${reportDetailVlidateDto.hasConsumptionPerMonthObjects}">
				<div class="item item_over wp40 fr mt10" style="display: block">
				</c:if>
				<c:if test="${!reportDetailVlidateDto.hasConsumptionPerMonthObjects}">
				<div class="item item_over wp40 fr mt10" style="display: none">
				</c:if>
				<div class="item_title">发票信息</div>
				<div id="pie_chart" style="min-height:350px" class="wp40 fl">

				</div>
					<table class="item_table wp52 fr">
						<tr>
							<th>发票抬头</th>
							<th>次数</th>
							<th>占比</th>
						</tr>
						<c:if test="${reportObject.ECommerceDataObject.invoiceAnalysisObjects!=null && reportObject.ECommerceDataObject.invoiceAnalysisObjects.size()>0 }">
		             		<c:forEach var="f" items="${reportObject.ECommerceDataObject.invoiceAnalysisObjects}" varStatus="s">																		
								<td>${f.title }</td>
								<td>${f.invoiceCount }</td>
								<td>${f.invoicePercent }</td>
							 </tr>
							</c:forEach>
						</c:if>		
						<c:if test="${reportObject.ECommerceDataObject.invoiceAnalysisObjects.size()==0 }">
							<tr><td colspan="6" align="center">暂&nbsp;无&nbsp;数&nbsp;据</td></tr>
						</c:if>	
					</table>
					</div>
				</div>
				<c:if test="${reportDetailVlidateDto.hasReceiverAnalysisObjects}">
				<div class="item clearfix">
				<div class="item_title">收货人信息</div>
				<table class="item_table">
					<tr>
						<th>收货人姓名</th>
						<th>电话号码</th>
						<th>订单数量(笔)</th>
						<th>消费金额(元)</th>
						<th>占比</th>
					</tr>
					<c:if test="${reportObject.ECommerceDataObject.receiverAnalysisObjects!=null && reportObject.ECommerceDataObject.receiverAnalysisObjects.size()>0 }">
	             		<c:forEach var="f" items="${reportObject.ECommerceDataObject.receiverAnalysisObjects }" varStatus="s">																		
							<td>${f.name }</td>
							<td>${f.phoneNumber }</td>
							<td>${f.ordersCount }</td>
							<td>${f.cost }</td>
							<td>${f.orderPercent }</td>
						 </tr>
						</c:forEach>
					</c:if>		
					<c:if test="${reportObject.ECommerceDataObject.receiverAnalysisObjects.size()==0 }">
						<tr><td colspan="6" align="center">暂&nbsp;无&nbsp;数&nbsp;据</td></tr>
					</c:if>	
				</table>
			</div>
			</c:if>
		</div>
		<script type="text/javascript" src="${ctx }/resources/js/reportdetail/html2canvas.js"></script>
		<script type="text/javascript" src="${ctx }/resources/js/reportdetail/reportdetail.js"></script>
		
	</body>

</html>