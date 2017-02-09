<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">
<title>拍拍信后台管理系统 | ${param.title }</title>
<script>
var $context = {};
$context.ctx = '${ctx}';
$context.resources = '${ctx}/resources';
<#assign ctx=request.contextPath resources=value2 >
</script>
<!-- STYLESHEETS -->
<!--[if lt IE 9]><script src="js/flot/excanvas.min.js"></script><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script><![endif]-->
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/cloud-admin.css">
<c:if test="${param.title != '登录' }">
<link rel="stylesheet" type="text/css"  href="${ctx }/resources/css/themes/default.css" id="skin-switcher" >
</c:if>
<link rel="stylesheet" type="text/css"  href="${ctx }/resources/css/responsive.css" >

<link href="${ctx }/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- ANIMATE -->
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/animatecss/animate.min.css" />

<!-- UNIFORM -->
<link rel="stylesheet" type="text/css" href="${ctx }/resources/js/uniform/css/uniform.default.min.css" />


<!-- FONTS -->
<#--<link href='http://fonts.useso.com/css?family=Open+Sans:300,400,600,700' rel='stylesheet' type='text/css'> -->

