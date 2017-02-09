	var myVue, image,
	data_all=JSON.parse(reportChartDto);
	//数据初始化
	$(function() {
		if(navigator.userAgent.toLocaleLowerCase().indexOf('msie 8.0')>0){
			alert('请在ie8以上浏览器中打开...');
			return false;
		}
		
	
		chartInvoice(data_all);
		chartContactArea(data_all);
		//画信用评分图
		chartCreditScore(data_all.score,data_all.level)
		//信用等级，逾期率图
		chartCreditGrade(data_all)
		
		setTimeout(function(){
	        $('.scorehover').parent().css('left',($('#gauge_1').find('canvas').width()/2-20));
	        //信用评分tooltip
	        $('.scorehover').hover(function() {
	            $('.scoreshow').show();
	        }, function() {
	            $('.scoreshow').hide();
	        })
	        $('.ratehover').parent().css('right',($('#gauge_2').find('canvas').width()/2-30));
	        //逾期率tooltip
	        $('.ratehover').hover(function() {
	            $('.rateshow').show();
	        }, function() {
	            $('.rateshow').hide();
	        })
		},100);
	
	
	
	myVue = new Vue({
		el: '#zx_data',
		data: data_all,
		methods: {
			expand: function(method, event) {
				var obj = event.currentTarget;
				var name1=method+"1";
				var name2=method+"2";
				if ($(obj).find('i').hasClass('down')) {
					$(obj).find('span').html('展开');
					$(obj).find('i').removeClass('down');
					$("tr[name='" + name2 + "']").hide();
					$("tr[name='"+name1+"']").show();
				} else {
					$(obj).find('span').html('收起');
					$(obj).find('i').addClass('down');
					$("tr[name='" + name2 + "']").show();
					$("tr[name='" + name1 + "']").hide();
				}
				}
			 }
	})
});

function export_pdf(){
	 html2canvas(document.body, {  
        allowTaint: true,  
        taintTest: false,  
        onrendered: function(canvas) {  
             image = canvas.toDataURL("image/png"); 
             $.ajax({  
                 type : "POST",  
                 url : bathPath + "/system/creditreport/to_credit_report_pdf",  
                 data: {data:image,rowKey:rowKey,name:name,phoneNumber:phoneNumber},
                 success: function(fileName){  
                  window.location.href = bathPath + "/pdf/"+name+"_"+phoneNumber+".pdf";
                 },
                 error:function(data){
                 }
             });
       
        }  
    }); 
	
}	
	
	
//信用评分画图
function chartCreditScore(score,grade){
		var show_gauge= 1,val=['','H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'];
	    switch(grade){
			case '': show_gauge=0;break;
	        case 'H': show_gauge=1;break;
	        case 'G': show_gauge=2;break;
	        case 'F': show_gauge=3;break;
	        case 'E': show_gauge=4;break;
	        case 'D': show_gauge=5;break;
	        case 'C': show_gauge=6;break;
	        case 'B': show_gauge=7;break;
			case 'A': show_gauge=8;break;
	        default:show_gauge=9;break;
	    }
	
			var creditScore = {
					tooltip: {
						formatter: "{a}: {c}%"
					},
					series: [{
						startAngle: 180,
						endAngle: 0,
						min: 0,
						max: 9,
						name: '实际完成',
						type: 'gauge',
						center: ['50%', '60%'], // 默认全局居中
						radius: 120,
						splitNumber: 9,
						axisLine: { // 坐标轴线
							lineStyle: { // 属性lineStyle控制线条样式
								color: [
									[0.0, 'lime'],
									[show_gauge/9, '#65c3d6'],
									[1, 'rgb(237,238,238)']
								],
								width: 30
							}
						},
						axisTick: {
							show: false
						},
						axisLabel: { // 坐标轴小标记
							textStyle: {
								fontSize: 12,
								color: '#919191'
							},
							formatter: function(value) {
								return val[value];
							}
						},
						splitLine: { // 分隔线
							show: false
						},
						pointer: false,
						detail: {
							formatter: '{value}',
							offsetCenter: [0, '-25%'],
							textStyle: {
								fontSize: 30,
								color:'#65c3d6'
							}
						},
						data: [{
							value:score
						}]
					}]
				}
			
				var scoreChart = echarts.init(document.getElementById('gauge_1'));
				// 使用刚指定的配置项和数据显示图表。
				scoreChart.setOption(creditScore);
	};
	
//逾期率图	
function chartCreditGrade(data_all){
	var laterateMin=data_all.laterateMin;
	var laterateMax=data_all.laterateMax;
	if(!laterateMin&&!laterateMax){
		laterateMin=0;
		laterateMax=0;
	}else if(!laterateMin){
		laterateMin=0;
	}else if(!laterateMax){
		laterateMax=0.25;
	}
	var option_grade = {
			tooltip: {
				formatter: "{a}: {c}%"
			},
			series: [{
				startAngle: 0,
				endAngle: 180,
				clockwise: false,
				min: 0,
				max: 0.25,
				name: '实际完成',
				type: 'gauge',
				center: ['50%', '60%'], // 默认全局居中
				radius: 120,
				splitNumber: 5,
				axisLine: { // 坐标轴线
					lineStyle: { // 属性lineStyle控制线条样式
						color: [
							[0.0, 'lime'],
							[laterateMin / 0.25, 'rgb(237,238,238)'],
							[laterateMax / 0.25, 'rgb(248,170,33)'],
							[1, 'rgb(237,238,238)']
						],
						width: 30
					}
				},
				axisTick: {
					show: false
				},
				axisLabel: { // 坐标轴小标记
					textStyle: {
						fontSize: 12,
						color: '#919191'
					},
					formatter: function(value) {
						return (25 - value * 100) + '%';
					}
				},
				splitLine: { // 分隔线
					show: false
				},
				pointer: false,
				detail: {
					formatter: function(value) {
						if(!data_all.laterateMax&&!data_all.laterateMin){
							return "" ;
						}
						if(!data_all.laterateMax){
							return ">"+data_all.laterateMin * 100+"%" ;
						}
						if(!data_all.laterateMin){
							return "<" + data_all.laterateMax * 100 + '%';	
						}
						return data_all.laterateMin * 100 + '%~' + data_all.laterateMax * 100 + '%';
					},
					offsetCenter: [0, '-20%'],
					textStyle: {
						fontSize: 20,
						color: 'rgb(248,170,33)'
					}
				},
				data: [{
					value: data_all.laterateMin * 100
				}]
			}]
		}
		var gradeChart = echarts.init(document.getElementById('gauge_2'));
		// 使用刚指定的配置项和数据显示图表。
		gradeChart.setOption(option_grade);
}

//联系人分布图
function chartContactArea(data_all){
	//map地图配置
	$.get(bathPath+'/resources/js/reportdetail/china.json', function(chinaJson) {
		echarts.registerMap('china', chinaJson);
		var data_map = [],
		colors_map = ['#3bb3cb', '#82cfde', '#b1e1ea', '#e0f3f7', '#fff'];
		$.each(data_all.contactAreas, function(i, e) {
			var color = '#fff';
			switch (i) {
				case 0:
					color = colors_map[0];
					break;
				case 1:
				case 2:
					color = colors_map[1];
					break;
				case 3:
				case 4:
					color = colors_map[2];
					break;
				default:
					if (data_all.contactAreas[i].numberOfContacts > 0)
						color = colors_map[3];
					break;
			}
			data_map.push({
				name: data_all.contactAreas[i].region,
				value: data_all.contactAreas[i].numberOfContacts,
				itemStyle: {
					normal: {
						areaColor: color

					},
					emphasis: {
						opacity: 1,
						areaColor: color
					}
				}
			})
		})
		var option_map = {
			tooltip: {
				trigger: 'item',
				formatter: function(params, ticket, callback) {
					return params.name + '<br/>号码数量：' + (params.value ? params.value : 0) + '个';
				}
			},
			series: [{
				name: '中国',
				type: 'map',
				mapType: 'china',
				selectedMode: false,
				label: {
					normal: {
						show: false
					},
					emphasis: {
						show: false
					}
				},
				itemStyle: {
					normal: {
						borderWidth: 1,
						borderColor: '#fff',
						areaColor: '#edeeee'
					},
					emphasis: {
						opacity: 1,
                        areaColor: '#edeeee'
					}
				},
				data: data_map
			}]
		};
		var myChart_map = echarts.init(document.getElementById('map_chart'));
		// 使用刚指定的配置项和数据显示图表。
		myChart_map.setOption(option_map);
	});
}

//发票信息图
function chartInvoice(data_all){
	var title = [],
	invoice_data = [],
	colors = ['#65c3d6', '#f8aa21', '#e45e5c', '#a08bcd', '#388190', '#579737'];
	$.each(data_all.invoices, function(i, e) {
		title.push(data_all.invoices[i].title);
		invoice_data.push({
			name: data_all.invoices[i].title,
			value: data_all.invoices[i].invoiceCount,
			itemStyle: {
				normal: {
					color: colors[i]
				}
			}
		})
	});
	var option = {
		title: {
			show: true,
			text: '占比',
			left: 'center',
			top: '40%',
			textStyle: {
				fontSize: '20',
				fontWeight: 'bold'
			}
		},
		tooltip: {
			trigger: 'item',
			formatter: "{b}<br/>{a}:{d}%"
		},
		legend: {
			orient: 'vertical',
			x: 'left',
			y: 'bottom',
			data: title
		},
		series: [{
			name: '占比',
			type: 'pie',
            		center:['50%','45%'],
			radius: ['50%', '80%'],
			avoidLabelOverlap: false,
			label: {
				normal: {
					show: false,
					position: 'center'
				},
				emphasis: {
					show: false
				}
			},
			labelLine: {
				normal: {
					show: false
				}
			},
			data: invoice_data
		}]
	};
	var invoiceChart=document.getElementById('pie_chart');
	var myChart = echarts.init(invoiceChart);
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}
