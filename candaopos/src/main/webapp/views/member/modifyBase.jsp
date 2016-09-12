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
	<title>修改基本信息</title>
</head>
<body>
<div class="modal-dialog" style="width: 760px;">
	<div class="modal-content">
		<div class="modal-header">
			<div class="fl">修改基本信息</div>
			<div class="fr close-win" data-dismiss="modal">x</div>
		</div>
		<div class="modal-body">
			<div class="block-shadow block-radius ">
				<ul class="member-info-list">
					<li>卡号：<span>827375738</span></li>
					<li>手机号码：<span>827375738</span></li>
					<li>姓名：<span>张晓东</span></li>
				</ul>
			</div>
			<div class="row">
				<div class="col-md-7">
					<form action="">
						<div class="form-group form-group-base">
							<span class="form-label">姓名:</span>
							<input value="" name="phone"  type="text" class="form-control" autocomplete="off">
						</div>
						<div class="form-group form-group-base"  style="height: 38px; margin-top: -10px;">
							<span class="form-label">性别:</span>
							<div class="form-info">
								<div class="radio-box">
									<label>
										<input type="radio" name="sex">&nbsp;男
									</label>
									&nbsp;
									&nbsp;
									&nbsp;
									<label>
										<input type="radio"  name="sex">&nbsp;女
									</label>
								</div>
							</div>

						</div>
						<div class="form-group form-group-base" >
							<span class="form-label">生日:</span>
							<input value="" name="birthday"  type="date" class="form-control" autocomplete="off">
						</div>
						<div class="form-group form-group-base">
							<button class="btn-default btn-lg btn-base btn-base-sm btn-yellow" style="width: 100%;">保存修改</button>
						</div>
					</form>
				</div>
				<div class="col-md-5">
					<div class="virtual-keyboard-base" style="position: absolute;top:0;right:10px;">
						<ul>
							<li>1</li><li>2</li><li>3</li>
						</ul>
						<ul>
							<li>4</li><li>5</li><li>6</li>
						</ul>
						<ul>
							<li>7</li><li>8</li><li>9</li>
						</ul>
						<ul>
							<li>.</li><li>0</li><li>←</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>