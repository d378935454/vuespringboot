<#include "common\\taglibs.ftl">
<style type="text/css">
.navbar-nav > li {
    float: right;
}

.modal-dialog {
    width: 600px;
    margin: 190px auto;
}

.modal-body {
    position: relative;
    padding: 15px 75px 2px 5px;
}

</style>
<header class="navbar clearfix" id="header">
	<div class="container">
		<div class="navbar-brand">
			<!-- COMPANY LOGO -->
			<a href="#"> <img
				src="${ctx }/resources/img/logo/logo.png" alt="Cloud Admin Logo"
				class="img-responsive" height="30" width="120"> </a>
			<!-- /COMPANY LOGO -->
			<!-- TEAM STATUS FOR MOBILE -->
			<div class="visible-xs">
				<a href="javascript:;"
					class="team-status-toggle switcher btn dropdown-toggle"> <i
					class="fa fa-users"></i> </a>
			</div>
			<!-- /TEAM STATUS FOR MOBILE -->
			<!-- SIDEBAR COLLAPSE -->
			<div id="sidebar-collapse" class="sidebar-collapse btn">
				<i class="fa fa-bars" data-icon1="fa fa-bars"
					data-icon2="fa fa-bars"></i>
			</div>
			<!-- /SIDEBAR COLLAPSE -->
		</div>
		<!-- NAVBAR LEFT -->
		<ul class="nav navbar-nav pull-left hidden-xs" id="navbar-left">
			<!-- <li class="dropdown"><a href="javascript:;"
				class="team-status-toggle dropdown-toggle tip-bottom"
				data-toggle="tooltip" title="Toggle Team View"> <i
					class="fa fa-users"></i> <span class="name">Team Status</span> <i
					class="fa fa-angle-down"></i> </a></li> -->
			<li class="dropdown"><a href="javascript:;"
				class="dropdown-toggle" data-toggle="dropdown"> <i
					class="fa fa-cog"></i> <span class="name">主题</span> <i
					class="fa fa-angle-down"></i> </a>
				<ul class="dropdown-menu skins">
					<li class="dropdown-title"><span><i class="fa fa-leaf"></i>
							Theme Skins</span></li>
					<li><a href="javascript:;" data-skin="default">系统 </a>
					</li>
					<li><a href="javascript:;" data-skin="night">夜晚</a>
					</li>
					<li><a href="javascript:;" data-skin="earth">地球</a>
					</li>
					<li><a href="javascript:;" data-skin="utopia">乌托邦</a>
					</li>
					<li><a href="javascript:;" data-skin="nature">自然</a>
					</li>
					<li><a href="javascript:;" data-skin="graphite">石墨</a>
					</li>
				</ul></li>
		</ul>
		<!-- /NAVBAR LEFT -->
		 
		<!-- BEGIN TOP NAVIGATION MENU -->
		<ul class="nav navbar-nav pull-right">
			<!-- BEGIN NOTIFICATION DROPDOWN -->
			<!-- 
			<li class="dropdown" id="header-notification"><a
				href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
					<i class="fa fa-bell"></i> <span class="badge">7</span> </a>
				<ul class="dropdown-menu notification">
					<li class="dropdown-title"><span><i class="fa fa-bell"></i>
							7 Notifications</span></li>
					<li><a href="javascript:;"> <span
							class="label label-success"><i class="fa fa-user"></i>
						</span> <span class="body"> <span class="message">5 users
									online. </span> <span class="time"> <i class="fa fa-clock-o"></i>
									<span>Just now</span> </span> </span> </a></li>
					<li><a href="javascript:;"> <span
							class="label label-primary"><i class="fa fa-comment"></i>
						</span> <span class="body"> <span class="message">Martin
									commented.</span> <span class="time"> <i class="fa fa-clock-o"></i>
									<span>19 mins</span> </span> </span> </a></li>
					<li><a href="javascript:;"> <span
							class="label label-warning"><i class="fa fa-lock"></i>
						</span> <span class="body"> <span class="message">DW1
									server locked.</span> <span class="time"> <i
									class="fa fa-clock-o"></i> <span>32 mins</span> </span> </span> </a></li>
					<li><a href="javascript:;"> <span class="label label-info"><i
								class="fa fa-twitter"></i>
						</span> <span class="body"> <span class="message">Twitter
									connected.</span> <span class="time"> <i class="fa fa-clock-o"></i>
									<span>55 mins</span> </span> </span> </a></li>
					<li><a href="javascript:;"> <span
							class="label label-danger"><i class="fa fa-heart"></i>
						</span> <span class="body"> <span class="message">Jane
									liked. </span> <span class="time"> <i class="fa fa-clock-o"></i>
									<span>2 hrs</span> </span> </span> </a></li>
					<li><a href="javascript:;"> <span
							class="label label-warning"><i
								class="fa fa-exclamation-triangle"></i>
						</span> <span class="body"> <span class="message">Database
									overload.</span> <span class="time"> <i class="fa fa-clock-o"></i>
									<span>6 hrs</span> </span> </span> </a></li>
					<li class="footer"><a href="javascript:;">See all
							notifications <i class="fa fa-arrow-circle-right"></i>
					</a></li>
				</ul></li>
				 -->
			<!-- END NOTIFICATION DROPDOWN -->
			<!-- BEGIN INBOX DROPDOWN -->
			<!-- 
			<li class="dropdown" id="header-message"><a href="javascript:;"
				class="dropdown-toggle" data-toggle="dropdown"> <i
					class="fa fa-envelope"></i> <span class="badge">3</span> </a>
				<ul class="dropdown-menu inbox">
					<li class="dropdown-title"><span><i
							class="fa fa-envelope-o"></i> 3 Messages</span> <span
						class="compose pull-right tip-right" title="Compose message"><i
							class="fa fa-pencil-square-o"></i>
					</span></li>
					<li><a href="javascript:;"> <img
							src="${ctx }/resources/img/avatars/avatar2.jpg" alt="" /> <span
							class="body"> <span class="from">Jane Doe</span> <span
								class="message"> Duis autem vel eum iriure dolor in
									hendrerit in vulputate velit esse mole ... </span> <span class="time">
									<i class="fa fa-clock-o"></i> <span>Just Now</span> </span> </span> </a></li>
					<li><a href="javascript:;"> <img
							src="${ctx }/resources/img/avatars/avatar1.jpg" alt="" /> <span
							class="body"> <span class="from">Vince Pelt</span> <span
								class="message"> Duis autem vel eum iriure dolor in
									hendrerit in vulputate velit esse mole ... </span> <span class="time">
									<i class="fa fa-clock-o"></i> <span>15 min ago</span> </span> </span> </a></li>
					<li><a href="javascript:;"> <img
							src="${ctx }/resources/img/avatars/avatar8.jpg" alt="" /> <span
							class="body"> <span class="from">Debby Doe</span> <span
								class="message"> Duis autem vel eum iriure dolor in
									hendrerit in vulputate velit esse mole ... </span> <span class="time">
									<i class="fa fa-clock-o"></i> <span>2 hours ago</span> </span> </span> </a></li>
					<li class="footer"><a href="javascript:;">See all messages
							<i class="fa fa-arrow-circle-right"></i>
					</a></li>
				</ul></li>
				 -->
			<!-- END INBOX DROPDOWN -->
			<!-- BEGIN TODO DROPDOWN -->
			<!-- 
			<li class="dropdown" id="header-tasks"><a href="javascript:;"
				class="dropdown-toggle" data-toggle="dropdown"> <i
					class="fa fa-tasks"></i> <span class="badge">3</span> </a>
				<ul class="dropdown-menu tasks">
					<li class="dropdown-title"><span><i class="fa fa-check"></i>
							6 tasks in progress</span></li>
					<li><a href="javascript:;"> <span class="header clearfix">
								<span class="pull-left">Software Update</span> <span
								class="pull-right">60%</span> </span>
							<div class="progress">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="60" aria-valuemin="0"
									aria-valuemax="100" style="width: 60%;">
									<span class="sr-only">60% Complete</span>
								</div>
							</div> </a></li>
					<li><a href="javascript:;"> <span class="header clearfix">
								<span class="pull-left">Software Update</span> <span
								class="pull-right">25%</span> </span>
							<div class="progress">
								<div class="progress-bar progress-bar-info" role="progressbar"
									aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"
									style="width: 25%;">
									<span class="sr-only">25% Complete</span>
								</div>
							</div> </a></li>
					<li><a href="javascript:;"> <span class="header clearfix">
								<span class="pull-left">Software Update</span> <span
								class="pull-right">40%</span> </span>
							<div class="progress progress-striped">
								<div class="progress-bar progress-bar-warning"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 40%;">
									<span class="sr-only">40% Complete</span>
								</div>
							</div> </a></li>
					<li><a href="javascript:;"> <span class="header clearfix">
								<span class="pull-left">Software Update</span> <span
								class="pull-right">70%</span> </span>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-danger" role="progressbar"
									aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"
									style="width: 70%;">
									<span class="sr-only">70% Complete</span>
								</div>
							</div> </a></li>
					<li><a href="javascript:;"> <span class="header clearfix">
								<span class="pull-left">Software Update</span> <span
								class="pull-right">65%</span> </span>
							<div class="progress">
								<div class="progress-bar progress-bar-success"
									style="width: 35%">
									<span class="sr-only">35% Complete (success)</span>
								</div>
								<div class="progress-bar progress-bar-warning"
									style="width: 20%">
									<span class="sr-only">20% Complete (warning)</span>
								</div>
								<div class="progress-bar progress-bar-danger" style="width: 10%">
									<span class="sr-only">10% Complete (danger)</span>
								</div>
							</div> </a></li>
					<li class="footer"><a href="javascript:;">See all tasks <i
							class="fa fa-arrow-circle-right"></i>
					</a></li>
				</ul></li>
				 -->
			<!-- END TODO DROPDOWN -->
			
			<!-- BEGIN USER LOGIN DROPDOWN -->
			<li class="dropdown user" id="header-user"><a
				href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
					<img alt="" src="${ctx }/resources/img/avatars/avatar3.jpg" /> <span
					class="username">${Session["ppc_user_session"].userRealName }</span> <i class="fa fa-angle-down"></i> </a>
				<ul class="dropdown-menu">
					<li><a href="javascript:;" data-target="#piModel" data-toggle="modal" id="editInfo" ><i class="fa fa-user"></i> &nbsp;修改个人信息</a>
					</li>
					<li><a href="javascript:;" data-target="#pwModel" data-toggle="modal" id="editPwd" ><i class="fa fa-cog"></i>&nbsp;修改密码</a>
					</li>
					<!--  <li><a href="javascript:;"><i class="fa fa-eye"></i>&nbsp;预览</a>
					</li>-->
					<li><a id="log_out" href="javascript:;"><i class="fa fa-power-off"></i>
							安全退出</a>
					</li>
				</ul></li>
				
			<li class="dropdown user" id="header-user"><a
				href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
				<span class="username">当前选中人员：${Session["real_name"]}</span>  </a>
			</li>
			<!-- END USER LOGIN DROPDOWN -->
		</ul>
		<!-- END TOP NAVIGATION MENU -->
	</div>
	
	<!--Edit Personal Info  Modal -->
	<div class="modal fade" id="piModel" tabindex="-1" role="dialog" 
		   aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" 
		               aria-hidden="true" onclick="window.location.reload();">×
		            </button>
		            <h4 class="modal-title" id="myModalLabel">修改个人信息</h4>
		         </div>
			         <form id="wizInfoForm" class="form-horizontal" action="#">
			         <div class="modal-body"> 
			                    <div class="alert alert-danger display-none">
									<a class="close" aria-hidden="true" href="#" data-dismiss="alert">×</a>
						            <span id="tip_message">信息填写有误</span>
								</div>
								 <div class="alert alert-success display-none">
									<a class="close" aria-hidden="true" href="#" data-dismiss="alert">×</a>
									<span id="tip_success_message">填写正确,正在提交信息,请稍后...</span>
								 </div>				
								<div class="form-group">
								   <label class="col-md-4 control-label">姓名</label> 
								   <div class="col-md-8"><input type="text" name="userRealName" class="form-control" value="${sessionScope.ppc_user_session.userRealName }"></div>
								</div>		
								<div class="form-group">
								   <label class="col-md-4 control-label">手机</label> 
								   <div class="col-md-8"><input type="number" name="userPhone" class="form-control" value="${sessionScope.ppc_user_session.userMobile }"></div>
								</div>
								<div class="form-group">
								   <label class="col-md-4 control-label">登录名</label> 
								   <div class="col-md-8"><input type="text" name="userEmail" class="form-control" value="${sessionScope.ppc_user_session.userLoginName }"></div>
								</div>												 			
			              
			         </div>
			         <div class="modal-footer">
			            <button type="button" class="btn btn-default" 
			               data-dismiss="modal" onclick="window.location.reload();">关闭
			            </button>
			            <button type="submit" class="btn btn-primary">提交</button>
			         </div>
		         </form>
		      </div><!-- /.modal-content -->
		   </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
			<!--Edit Password Modal -->
	  <div class="modal fade" id="pwModel" tabindex="-1" role="dialog" 
		   aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" 
		               aria-hidden="true">×
		            </button>
		            <h4 class="modal-title" id="myModalLabel">修改密码</h4>
		         </div>
		         <form id="wizPwdForm" class="form-horizontal" action="#">
			         <div class="modal-body"> 
			                    <div class="alert alert-danger display-none">
									<a class="close" aria-hidden="true" href="#" data-dismiss="alert">×</a>
						            <span id="pwd_message">信息填写有误</span>
								</div>
								 <div class="alert alert-success display-none">
									<a class="close" aria-hidden="true" href="#" data-dismiss="alert">×</a>
									<span id="pwd_success_message">填写正确,正在提交信息,请稍后...</span>
								 </div>				
								<div class="form-group">
								   <label class="col-md-4 control-label">老密码</label> 
								   <div class="col-md-8"><input type="password" id="oldPassword" name="oldPassword" class="form-control" value=""></div>
								</div>		
								<div class="form-group">
								   <label class="col-md-4 control-label">新密码</label> 
								   <div class="col-md-8"><input type="password" id="password" name="password" class="form-control" value=""></div>
								</div>
								<div class="form-group">
								   <label class="col-md-4 control-label">确认密码</label> 
								   <div class="col-md-8"><input type="password" id="confPassword" name="confPassword" class="form-control" value=""></div>
								</div>												 			
			              
			         </div>
			         <div class="modal-footer">
			            <button type="button" class="btn btn-default" 
			               data-dismiss="modal">关闭
			            </button>
			            <button type="submit" class="btn btn-primary">提交</button>
			         </div>
		         </form>
		      </div><!-- /.modal-content -->
		   </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	<!-- TEAM STATUS -->
	<!--  
	<div class="container team-status" id="team-status">
		<div id="scrollbar">
			<div class="handle"></div>
		</div>
		<div id="teamslider">
			<ul class="team-list">
				<li class="current"><a href="javascript:void(0);"> <span
						class="image"> <img
							src="${ctx }/resources/img/avatars/avatar3.jpg" alt="" /> </span> <span
						class="title"> You </span>
						<div class="progress">
							<div class="progress-bar progress-bar-success" style="width: 35%">
								<span class="sr-only">35% Complete (success)</span>
							</div>
							<div class="progress-bar progress-bar-warning" style="width: 20%">
								<span class="sr-only">20% Complete (warning)</span>
							</div>
							<div class="progress-bar progress-bar-danger" style="width: 10%">
								<span class="sr-only">10% Complete (danger)</span>
							</div>
						</div> <span class="status">
							<div class="field">
								<span class="badge badge-green">6</span> completed <span
									class="pull-right fa fa-check"></span>
							</div>
							<div class="field">
								<span class="badge badge-orange">3</span> in-progress <span
									class="pull-right fa fa-adjust"></span>
							</div>
							<div class="field">
								<span class="badge badge-red">1</span> pending <span
									class="pull-right fa fa-list-ul"></span>
							</div> </span> </a></li>
				<li><a href="javascript:void(0);"> <span class="image">
							<img src="${ctx }/resources/img/avatars/avatar1.jpg" alt="" /> </span>
						<span class="title"> Max Doe </span>
						<div class="progress">
							<div class="progress-bar progress-bar-success" style="width: 15%">
								<span class="sr-only">35% Complete (success)</span>
							</div>
							<div class="progress-bar progress-bar-warning" style="width: 40%">
								<span class="sr-only">20% Complete (warning)</span>
							</div>
							<div class="progress-bar progress-bar-danger" style="width: 20%">
								<span class="sr-only">10% Complete (danger)</span>
							</div>
						</div> <span class="status">
							<div class="field">
								<span class="badge badge-green">2</span> completed <span
									class="pull-right fa fa-check"></span>
							</div>
							<div class="field">
								<span class="badge badge-orange">8</span> in-progress <span
									class="pull-right fa fa-adjust"></span>
							</div>
							<div class="field">
								<span class="badge badge-red">4</span> pending <span
									class="pull-right fa fa-list-ul"></span>
							</div> </span> </a></li>
				<li><a href="javascript:void(0);"> <span class="image">
							<img src="${ctx }/resources/img/avatars/avatar2.jpg" alt="" /> </span>
						<span class="title"> Jane Doe </span>
						<div class="progress">
							<div class="progress-bar progress-bar-success" style="width: 65%">
								<span class="sr-only">35% Complete (success)</span>
							</div>
							<div class="progress-bar progress-bar-warning" style="width: 10%">
								<span class="sr-only">20% Complete (warning)</span>
							</div>
							<div class="progress-bar progress-bar-danger" style="width: 15%">
								<span class="sr-only">10% Complete (danger)</span>
							</div>
						</div> <span class="status">
							<div class="field">
								<span class="badge badge-green">10</span> completed <span
									class="pull-right fa fa-check"></span>
							</div>
							<div class="field">
								<span class="badge badge-orange">3</span> in-progress <span
									class="pull-right fa fa-adjust"></span>
							</div>
							<div class="field">
								<span class="badge badge-red">4</span> pending <span
									class="pull-right fa fa-list-ul"></span>
							</div> </span> </a></li>
				<li><a href="javascript:void(0);"> <span class="image">
							<img src="${ctx }/resources/img/avatars/avatar4.jpg" alt="" /> </span>
						<span class="title"> Ellie Doe </span>
						<div class="progress">
							<div class="progress-bar progress-bar-success" style="width: 5%">
								<span class="sr-only">35% Complete (success)</span>
							</div>
							<div class="progress-bar progress-bar-warning" style="width: 48%">
								<span class="sr-only">20% Complete (warning)</span>
							</div>
							<div class="progress-bar progress-bar-danger" style="width: 27%">
								<span class="sr-only">10% Complete (danger)</span>
							</div>
						</div> <span class="status">
							<div class="field">
								<span class="badge badge-green">1</span> completed <span
									class="pull-right fa fa-check"></span>
							</div>
							<div class="field">
								<span class="badge badge-orange">6</span> in-progress <span
									class="pull-right fa fa-adjust"></span>
							</div>
							<div class="field">
								<span class="badge badge-red">2</span> pending <span
									class="pull-right fa fa-list-ul"></span>
							</div> </span> </a></li>
				<li><a href="javascript:void(0);"> <span class="image">
							<img src="${ctx }/resources/img/avatars/avatar5.jpg" alt="" /> </span>
						<span class="title"> Lisa Doe </span>
						<div class="progress">
							<div class="progress-bar progress-bar-success" style="width: 21%">
								<span class="sr-only">35% Complete (success)</span>
							</div>
							<div class="progress-bar progress-bar-warning" style="width: 20%">
								<span class="sr-only">20% Complete (warning)</span>
							</div>
							<div class="progress-bar progress-bar-danger" style="width: 40%">
								<span class="sr-only">10% Complete (danger)</span>
							</div>
						</div> <span class="status">
							<div class="field">
								<span class="badge badge-green">4</span> completed <span
									class="pull-right fa fa-check"></span>
							</div>
							<div class="field">
								<span class="badge badge-orange">5</span> in-progress <span
									class="pull-right fa fa-adjust"></span>
							</div>
							<div class="field">
								<span class="badge badge-red">9</span> pending <span
									class="pull-right fa fa-list-ul"></span>
							</div> </span> </a></li>
				<li><a href="javascript:void(0);"> <span class="image">
							<img src="${ctx }/resources/img/avatars/avatar6.jpg" alt="" /> </span>
						<span class="title"> Kelly Doe </span>
						<div class="progress">
							<div class="progress-bar progress-bar-success" style="width: 45%">
								<span class="sr-only">35% Complete (success)</span>
							</div>
							<div class="progress-bar progress-bar-warning" style="width: 21%">
								<span class="sr-only">20% Complete (warning)</span>
							</div>
							<div class="progress-bar progress-bar-danger" style="width: 10%">
								<span class="sr-only">10% Complete (danger)</span>
							</div>
						</div> <span class="status">
							<div class="field">
								<span class="badge badge-green">6</span> completed <span
									class="pull-right fa fa-check"></span>
							</div>
							<div class="field">
								<span class="badge badge-orange">3</span> in-progress <span
									class="pull-right fa fa-adjust"></span>
							</div>
							<div class="field">
								<span class="badge badge-red">1</span> pending <span
									class="pull-right fa fa-list-ul"></span>
							</div> </span> </a></li>
				<li><a href="javascript:void(0);"> <span class="image">
							<img src="${ctx }/resources/img/avatars/avatar7.jpg" alt="" /> </span>
						<span class="title"> Jessy Doe </span>
						<div class="progress">
							<div class="progress-bar progress-bar-success" style="width: 7%">
								<span class="sr-only">35% Complete (success)</span>
							</div>
							<div class="progress-bar progress-bar-warning" style="width: 30%">
								<span class="sr-only">20% Complete (warning)</span>
							</div>
							<div class="progress-bar progress-bar-danger" style="width: 10%">
								<span class="sr-only">10% Complete (danger)</span>
							</div>
						</div> <span class="status">
							<div class="field">
								<span class="badge badge-green">1</span> completed <span
									class="pull-right fa fa-check"></span>
							</div>
							<div class="field">
								<span class="badge badge-orange">6</span> in-progress <span
									class="pull-right fa fa-adjust"></span>
							</div>
							<div class="field">
								<span class="badge badge-red">2</span> pending <span
									class="pull-right fa fa-list-ul"></span>
							</div> </span> </a></li>
				<li><a href="javascript:void(0);"> <span class="image">
							<img src="${ctx }/resources/img/avatars/avatar8.jpg" alt="" /> </span>
						<span class="title"> Debby Doe </span>
						<div class="progress">
							<div class="progress-bar progress-bar-success" style="width: 70%">
								<span class="sr-only">35% Complete (success)</span>
							</div>
							<div class="progress-bar progress-bar-warning" style="width: 20%">
								<span class="sr-only">20% Complete (warning)</span>
							</div>
							<div class="progress-bar progress-bar-danger" style="width: 5%">
								<span class="sr-only">10% Complete (danger)</span>
							</div>
						</div> <span class="status">
							<div class="field">
								<span class="badge badge-green">13</span> completed <span
									class="pull-right fa fa-check"></span>
							</div>
							<div class="field">
								<span class="badge badge-orange">7</span> in-progress <span
									class="pull-right fa fa-adjust"></span>
							</div>
							<div class="field">
								<span class="badge badge-red">1</span> pending <span
									class="pull-right fa fa-list-ul"></span>
							</div> </span> </a></li>
			</ul>
		</div>
	</div>
	-->
	<!-- /TEAM STATUS -->

</header>
