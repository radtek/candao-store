<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport"
		  content="width=device-width,initial-scale=1, user-scalable=no, minimum-scale=1.0,maximum-scale=1.0" />
	<!-- 让 IE 浏览器运行最新的渲染模式下 -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- 让部分国产浏览器默认采用高速模式渲染页面 -->
	<meta name="renderer" content="webkit">
	<title>会员储值</title>
	<link rel="stylesheet" href="../../tools/bootstrap-3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" href="../../css/common.css">
	<link rel="stylesheet" href="../../css/main.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="../../scripts/jquery-3.1.0.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="../../tools/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	<script src="../../scripts/common.js"></script>
	<link rel="stylesheet" href="../../css/member.css">
</head>
<body>


<header>
	<div class="fl">餐道</div>
	<div class="fr" onclick="goBack();" >返回</div>
</header>
<article>
	<div class="member-view"  style="width: 740px;margin:0 auto;">
		<div class="row" style="display: none">
			<div class="col-md-12">
				<div class="form-group form-group-base search-box mt20" >
					<input value="" name="name"  type="text" placeholder="输入会员卡号/手机号" class="form-control form-control-sm form-control-search" autocomplete="off">
					<button class="btn-base btn-yellow btn-base-sm btn-search">搜索</button>
				</div>
			</div>
		</div>

		<div class="block-shadow block-radius " style="display: none">
			<ul class="member-info-list">
				<li>卡号：<span>827375738</span></li>
				<li>手机号码：<span>827375738</span></li>
				<li>姓名：<span>张晓东</span></li>
				<li>会员卡等级：<span>储值卡</span></li>
				<li>生日：<span>87/02/09</span></li>
				<li>性别：<span>女</span></li>
				<li>余额：<span>88.00</span></li>
				<li>积分：<span>88</span></li>
				<li>卡状态：<span>正常</span></li>
			</ul>
		</div>

		<div class="coupon-box">
			<div class="prev">&lt;</div>
			<div class="coupon-cnt">
				<div class="coupon-item">美团88抵100</div>
				<div class="coupon-item">美团88抵100</div>
				<div class="coupon-item">美团88抵100</div>
				<div class="coupon-item">美团88抵100</div>
				<div class="coupon-item">美团88抵100</div>
				<div class="coupon-item">美团88抵100</div>
				<div class="coupon-item">美团88抵100</div>
				<div class="coupon-item">美团88抵100</div>
			</div>
			<div class="next">&gt;</div>
		</div>

		<div class="pay-type-select">
			<ul class="cnt">
				<li class="active">现金</li>
				<li>银行卡</li>
			</ul>
		</div>

		<div style="margin-top:90px"></div>

		<div class="pay-type-cnt">
			<div class="pay-type-cash">
				<div class="form-group form-group-nor" >
					<span class="form-label">手机号/会员卡号:</span>
					<div class="form-group-info">
						<input value="" name="repwd"  type="password" class="form-control" autocomplete="off">
					</div>
				</div>
				<div class="form-group form-group-nor" >
					<span class="form-label">赠送金额:</span>
					<div class="form-group-info">
						<input value="" name="repwd"  type="password" class="form-control" autocomplete="off">
					</div>
				</div>
				<div class="form-group form-group-nor" >
					<span class="form-label">赠送:</span>
					<div class="form-group-info">
						0
					</div>
				</div>
				<button class="btn-base btn-yellow btn-base-sm">确定重置</button>
				<button class="btn-base btn-base-sm ">取消</button>
			</div>
		</div>


		<div class="virtual-keyboard-box f-pr">
			<div class="virtual-keyboard-base virtual-keyboard-base-4" style="position: absolute;top:0;right:0px;">
				<ul>
					<li>1</li><li>2</li><li>3</li><li>←</li>
				</ul>
				<ul>
					<li>4</li><li>5</li><li>6</li>
				</ul>
				<ul>
					<li>7</li><li>8</li><li>9</li>
				</ul>
				<ul>
					<li>.</li><li>0</li><li>00</li>
				</ul>
				<div class="virtual-keyboard-ok">确定</div>
			</div>
		</div>
	</div>
</article>
<footer class="footer-min">
	<div class="info"><span>店铺编号：</span><span>0012</span><span>&nbsp;登录员工：</span><span>&nbsp;收银员(008)</span><span>&nbsp;当前时间：</span><span>2016-08-19 12:00:00</span><span>&nbsp;版本号：</span><span>1.01</span></div>
</footer>
<script type="text/javascript" src="../../scripts/member.js"></script>
</body>
</html>