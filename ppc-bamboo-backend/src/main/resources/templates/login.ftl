<#assign ctx=request.contextPath>

<html>
<style type="text/css">
	.noDisplayClass{display:none;};
	.displayClass{display:block;}
	.nav li.active{border-bottom:2px solid #0000ff;}	
</style>

<head>

<#include "common/loginheader.ftl">
<!-- DATE RANGE PICKER -->

<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<!-- WIZARD -->
<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/bootstrap-wizard/wizard.css" />
</head>
<body class="login">
	<!-- PAGE -->
	<section >
			<!-- HEADER -->
			<header>
				<!-- NAV-BAR -->
				<div class="container">
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<div id="logo">
								<a href=""><img src="${ctx }/resources/img/logo/logo.png" height="40" alt="logo name" /></a>
							</div>
						</div>
					</div>
				</div>
				<!--/NAV-BAR -->
			</header>
			<!--/HEADER -->
			<!-- LOGIN -->
			<section id="login_bg" class="visible">
				<div class="container">
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<div class="login-box">
								<h2 class="bigintro"></h2>
								
								<div class="divide-40">
									<ul class="nav nav-tabs" >
										<li id="ldapFormtab" class="active"><a href="#ldapForm" data-toggle="tab" style="text-decoration:none;background-color:transparent;border:none;" > <span class="hidden-inline-mobile">LDAP</span></a></li>
										<li id="wizFormtab"><a href="#wizForm" data-toggle="tab" style="text-decoration:none;background-color:transparent;border:none;" > <span class="hidden-inline-mobile">STANDARD</span></a></li>
								    </ul>
								</div>
								<div class="divide-10"></div>
								
								<form role="form" id="ldapForm" class="DisplayClass">
									<div class="alert alert-danger display-none">
										<a class="close" aria-hidden="true" href="#" data-dismiss="alert">×</a>
										<span id="ldap_tip_message">用户信息填写有误</span>
									 </div>
								  <div class="form-group">
									<label for="exampleInputEmail1">LDAP ${loginType}账 号</label>
									<i class="fa fa-user"></i>
									<input type="text" class="form-control "  name="ldap_name" id="ldap_name" >
									<span class="error-span"></span>
								  </div>	
								  <div class="form-group"> 
									<label for="exampleInputPassword1">密      码</label>
									<i class="fa fa-lock"></i>
									<input type="password" class="form-control" name="ldap_password" id="ldap_password" >
									<span class="error-span"></span>
								  </div>
								  <div class="form-group"> 
									  	<div style="float:right;margin-top:5%;" >
				                           <img id="ldap_randCodeImage" src="${ctx }/default.gif" border="0">	
				                        </div> 
								  		<label for="exampleInputPassword1">验证码</label>
										<i class="fa fa-lock"></i>									
										<input type="text" class="form-control" style="width:70%;" name="ldap_captcha" id="ldap_captcha" >																
									    <span class="error-span"></span>
								  </div>
								  <div>
									<label class="checkbox"> <input type="checkbox" class="uniform" value=""> 7天免登录</label>
									<button type="submit" class="btn btn-danger" id="ldap_sub" data-loading-text="登录中...">登录</button>
								  </div>
								  <div class="form-group display-none"> 
									<label for="exampleInputPassword1">登陆类型</label>
									<i class="fa fa-lock"></i>
									<input type="text" class="form-control" name=<#assign logintype="ldap"> id="logintype" value="ldap" >
									<span class="error-span"></span>
								  </div>
								</form>
								
								<form role="form" id="wizForm" class="noDisplayClass">
									<div class="alert alert-danger display-none">
										<a class="close" aria-hidden="true" href="#" data-dismiss="alert">×</a>
										<span id="tip_message">用户信息填写有误</span>
									 </div>
								  <div class="form-group">
									<label for="exampleInputEmail1">工号 （邮箱）</label>
									<i class="fa fa-user"></i>
									<input type="email" class="form-control "  name="name" id="name" >
									<span class="error-span"></span>
								  </div>
								  <div class="form-group"> 
									<label for="exampleInputPassword1">密      码</label>
									<i class="fa fa-lock"></i>
									<input type="password" class="form-control" name="password" id="password" >
									<span class="error-span"></span>
								  </div>
								  <div class="form-group"> 
									  	<div style="float:right;margin-top:5%;" >
				                           <img id="randCodeImage" src="${ctx }/default.gif" border="0">	
				                        </div> 
								  		<label for="exampleInputPassword1">验证码</label>
										<i class="fa fa-lock"></i>									
										<input type="text" class="form-control" style="width:70%;" name="captcha" id="captcha" >																
									    <span class="error-span"></span>
								  </div>
								  <div>
									<label class="checkbox"> <input type="checkbox" class="uniform" value=""> 7天免登录</label>
									<button type="submit" class="btn btn-danger" id="sub" data-loading-text="登录中...">登录</button>
								  </div>
								   <div class="form-group display-none"> 
									<label for="exampleInputPassword1">登陆类型</label>
									<i class="fa fa-lock"></i>
									<input type="text" class="form-control" name=<#assign logintype="standard"> id="logintype" value="standard" >
									<span class="error-span"></span>
								  </div>
								</form>
								
								<!-- SOCIAL LOGIN -->
								<div class="divide-20"></div>
								<div class="center">
									<strong>Copyright Reserved 2007-2016©拍拍贷  | 沪ICP备05063398号</strong>
								</div>
								<div class="divide-20"></div>
								
							</div>
						</div>
					</div>
				</div>
			</section>
			<!--/LOGIN -->
	</section>
	<!--/PAGE -->
	<#include "common/include_js.ftl">
	



<!-- BACKSTRETCH -->
<script type="text/javascript" src="${ctx }/resources/js/backstretch/jquery.backstretch.min.js"></script>
<!-- WIZARD -->
<script src="${ctx }/resources/js/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="${ctx }/resources/js/jquery-validate/jquery.validate.min.js"></script>
<script src="${ctx }/resources/js/jquery-validate/additional-methods.min.js"></script>
<script src="${ctx }/resources/js/jquery-validate/validate_zh_CN.js"></script>
<script src="${ctx }/resources/js/jquery.form.js"></script>
<!-- CUSTOM SCRIPT -->
<script src="${ctx }/resources/js/script.js"></script>
<script src="${ctx }/js/login.js"></script>

	<script>
		jQuery(document).ready(function() {		
			App.setPage("login");  //Set current page
			App.init(); //Initialise plugins and elements
			standardForm.init();
			ldapForm.init();
			console.info('${loginType }');
			//FormWizard.init();
		});
	</script>
	<script type="text/javascript">
		
		function swapScreen(id) {
			jQuery('.visible').removeClass('visible animated fadeInUp');
			jQuery('#'+id).addClass('visible animated fadeInUp');
		}
		$("#randCodeImage").click(function(){
		  $(this).attr("src", $context.ctx +"/default.gif?" + Math.floor(Math.random() * 100));
		});
		$("#ldap_randCodeImage").click(function(){
			  $(this).attr("src", $context.ctx +"/default.gif?" + Math.floor(Math.random() * 100));
			});	
		</script> 
	<script type="text/javascript">  
	$(function(){ 
		$("#ldapFormtab").click(function(){
			$("#ldapForm").css("display",'none');
			$("#wizForm").css("display",'none'); 
			$("#ldapForm").css("display",'block'); 
		});
		
		$("#wizFormtab").click(function(){
			$("#ldapForm").css("display",'none');
			$("#wizForm").css("display",'none');
			 
			$("#wizForm").css("display",'block');
		});  
	}); 
	</script> 
	<!-- /JAVASCRIPTS -->
	 
</body>
</html>
