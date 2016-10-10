


var MainPage = {

	CurrentTalbeType: 'all',
	CurrentArea: '-1',//默认为全部

	init: function(){

		this.setTables();

		SetBotoomIfon.init();

		this.bindEvent();

		//加载虚拟键盘组件
		widget.keyboard();
	},

	bindEvent: function(){

		var that = this;
		var dom = {
			standardTables : $("#standard-tables"),
			openDialog : $("#open-dialog"),//开台权限验证弹窗,
			roomTypeNav: $(".rooms-type"),//餐台分类导航
		};
		/**
		 * 标准台事件
		 */
		dom.standardTables.on('click','li', function() {
			var me = $(this);
			var cla = me.attr("class");

			me.siblings().removeClass('selected').end().addClass('selected');
			//var data = {
			//	orderid: me.attr('orderid'),
			//	personnum: me.attr('personnum'),
			//	tableno: me.attr('tableno')
			//};

			if (cla !== "opened") {
				$("#open-dialog").modal("show");
				return false;
			}
			window.location.href = "../views/order.jsp?orderid=" + me.attr('orderid') + '&personnum=' + me.attr('personnum') + '&tableno=' + me.attr('tableno');
            //
			//dom.orderDialog.load("../views/order.jsp", data, function () {
			//	dom.orderDialog.modal('show');
			//
			//});
		});

		/**
		 * 开启服务员权限验证
		 */
		dom.openDialog.on('click', '.age-type>div', function(){
			var me = $(this);
			me.toggleClass('active');
		});

		dom.openDialog.on('change', '.J-male-num, .J-female-num', function(){
			var maleNum = dom.openDialog.find('.J-male-num').val() === '' ? '0' : dom.openDialog.find('.J-male-num').val();
			var femailNum = dom.openDialog.find('.J-female-num').val() === '' ? '0' : dom.openDialog.find('.J-female-num').val();
			$('.J-tableware-num').val(parseInt(maleNum,10) + parseInt(femailNum,10))
		});

		dom.openDialog.on('click','.J-btn-submit', function(){
			var serverName = dom.openDialog.find('.J-server-name').val();
			var maleNum = dom.openDialog.find('.J-male-num').val() === '' ? '0' : dom.openDialog.find('.J-male-num').val();
			var femailNum = dom.openDialog.find('.J-female-num').val() === '' ? '0' : dom.openDialog.find('.J-female-num').val();

			if(parseInt(maleNum,10) + parseInt(femailNum,10) < 1) {
				widget.modal.alert({
					cls: 'fade in',
					content:'<strong>请输入就餐人数</strong>',
					width:500,
					height:500,
					btnOkTxt: '确定',
					btnCancelTxt: ''
				});
				return false;
			}

			if(serverName === undefined || serverName === '') {
				widget.modal.alert({
					cls: 'fade in',
					content:'<strong>请输入服务员编号</strong>',
					width:500,
					height:500,
					btnOkTxt: '确定',
					btnCancelTxt: ''
				});
				return false;
			}

			//验证用户权限,然后开台
			that.verifyUser(serverName, function(){
				var $target = dom.standardTables.find('li.selected');
				$.ajax({
					url: _config.interfaceUrl.OpenTable,
					method: 'POST',
					contentType: "application/json",
					data: JSON.stringify({
						tableNo: $target.attr('tableno'),
						ageperiod: (function(){
							var str = '';
							dom.openDialog.find('.age-type>div').each(function(){
								var me = $(this);
								if(me.hasClass('active')){
									str += me.index()+1;
								}
							});
							return str;
						})(),
						username: serverName,
						manNum: maleNum,
						womanNum: femailNum
					}),
					dataType:'json',
					success: function(res){
						if(res.code === '0') {
							dom.openDialog.modal('hide');
							debugger;
							window.location.href = "../views/order.jsp?orderid=" + $target.attr('orderid') + '&personnum=' + $target.attr('personnum') + '&tableno=' + $target.attr('tableno');
						} else {
							widget.modal.alert({
								cls: 'fade in',
								content:'<strong>' + res.msg + '</strong>',
								width:500,
								height:500,
								btnOkTxt: '',
								btnCancelTxt: '确定'
							});
						}
					}
				})

			});
		});


		//退出系统
		$(".exit-sys").click(function(){
			window.location = "../views/login.jsp";
		});

		//标准台和咖啡台切换
		$(".menu-tab ul li").click(function(){
			var olddiv = $(".menu-tab ul li.active").attr("loaddiv");
			$(olddiv).addClass("hide");
			$(".menu-tab ul li").removeClass("active");
			$(this).addClass("active");
			var loaddiv = $(this).attr("loaddiv");
			$(loaddiv).removeClass("hide");
		});

		/*餐台分类事件*/
		var roomtype_prev = 0;
		var navRoomTypes = $("#nav-room-types");

		dom.roomTypeNav.delegate('li', 'click', function() {
			var me = $(this);
			me.siblings().removeClass("active").end().addClass('active');
			me.addClass("active");
			that.CurrentArea = me.attr('areaid');
			that.setTables(that.CurrentTalbeType,me.attr('areaid'));
		});

		dom.roomTypeNav.delegate('.nav-type-next', 'click', function() {
			var count = navRoomTypes.find( "li").length;
			if (roomtype_prev < count - 10) {
				navRoomTypes.find("li").eq(roomtype_prev).css("margin-left", "-10%");
				navRoomTypes.find("li").eq(roomtype_prev+1).click();
				roomtype_prev++;
			}
		});

		dom.roomTypeNav.delegate('.nav-type-prev', 'click', function() {
			if(roomtype_prev>=1){
				navRoomTypes.find("li").eq(roomtype_prev-1).css("margin-left","0");
				navRoomTypes.find("li").eq(roomtype_prev-1).click();
				roomtype_prev--;
			}
		});

		//底部菜单事件绑定
		$(".foot-menu li").click(function(e){
			var me = $(this);
			if(me.hasClass("J-btn-takeout")){
				$("#J-takeout-dialog").modal("show");
				$(".take-out-list li").unbind("click").on("click",  function(){
					$(".take-out-list li").removeClass("active");
					$(this).addClass("active");
				});
			}
			if(me.hasClass("member-btns")){
				//会员
				$(".m-member.popover").toggle();
				e.stopPropagation();
			}
			if(me.hasClass('J-btn-sys')) {
				$("#sys-dialog").load("../views/sys.jsp");
				$("#sys-dialog").modal("show");
			}
			if(me.hasClass('J-btn-rep')) {
				window.location.href="../views/reporting/reporting.jsp";
			}
			if(me.hasClass('J-btn-check')) {
				window.location.href="../views/check/check.jsp";

			};
			if(me.hasClass('J-btn-clear')) {
				var aUserid=utils.storage.getter('aUserid'),orderLength=0,str//获取登录用户
				$.ajax({
					url: _config.interfaceUrl.QueryOrderInfo+''+ aUserid + '/',
					type: "get",
					async: false,
					dataType: "text",
					success: function (data) {
						data = JSON.parse(data.substring(12, data.length - 3));//从第12个字符开始截取，到最后3位，并且转换为JSON
						var arry = [];
						for (var i = 0; i < data.OrderJson.length; i++) {
							if (data.OrderJson[i].orderstatus == 0) {
								arry.push(data.OrderJson[i])
							}
						}
						orderLength=arry.length
					}
				});

					var str ='<strong>请选择倒班或结业：</strong>'
					str+='<div id="clearchoose" class="form-group form-group-base" style="margin-top: 20px">'
					if(utils.userRight.get(aUserid,"030204")){//判断清机权限
						str+='<button id="clearAll" class="btn-default btn-lg btn-base btn-base-flex2 clearAll"  style="margin-right: 5px">倒班</button>'
					}
					else {
						str+='<button id="clearAll" class="btn-default btn-lg btn-base btn-base-flex2 clearAll" disabled="disabled" style="margin-right: 5px;color: #999; background: #E8E8E8">倒班</button>'
					}
					if(orderLength>0){//判断账单未结业数量
						str+='<button id="completion" class="btn-default btn-lg btn-base btn-base-flex2 clearCompletion" disabled="disabled" style="color: #999; background: #E8E8E8">结业</button>'
						str+='</div>'
						str+='<div class="glyphicon glyphicon-info-sign" style="color: #8c8c8c;">还有未结账的餐台不能结业</div>'
					}
					else {
						str+='<button id="completion" class="btn-default btn-lg btn-base btn-base-flex2 clearCompletion" >结业</button>'
						str+='</div>'
					}
				widget.modal.alert({
					cls: 'fade in',
					content:str,
					width:400,
					height:500,
					title: "",
					hasBtns:false,
				});
				$("#clearchoose button").click(function () {
					$(".modal-alert:last,.modal-backdrop:last").remove();
					var _this = $(this);
					if(_this.hasClass("clearAll")){
						$(".modal-alert:last,.modal-backdrop:last").remove();
						$("#J-btn-checkout-dialog").load("../views/check/impower.jsp",{'title':'清机授权','usernameDisble':'1','cbd':'MainPage.clearAll()','userRightNo':'030204'});
						$("#J-btn-checkout-dialog").modal("show");
					}
					if(_this.hasClass("clearCompletion")){//结业
						var str ='<strong>确定要结业吗？</strong>';
						 widget.modal.alert({
							cls: 'fade in',
							content:str,
							width:400,
							height:500,
							title: "结业提醒",
							btnOkTxt: '确定',
							btnOkCb: function(){
								$(".modal-alert:last,.modal-backdrop:last").remove();
								MainPage.checkout();
							},
							btnCancelCb: function(){
							}
						})

					}

				});

			}

			if(me.hasClass('J-btn-register')) {
				$("#register-dialog").load("../views/member/register.jsp");
				$("#register-dialog").modal("show");
			}

			if(me.hasClass('J-btn-storge')) {
				window.location.href = '../views/member/storge.jsp';
			}

			if(me.hasClass('J-btn-memberView')) {
				window.location.href = './member/view.jsp';
			}

		});

		$(document).click(function(e){
			$(".m-member.popover").hide();
			e.stopPropagation();
		});

		//设置餐桌统计
		$(".J-table-nums>div").click(function(){
			var me = $(this);
			me.siblings().removeClass("active").end().addClass('active');
			if(me.hasClass('all')) {
				that.setTables('all', that.CurrentArea);
				that.CurrentTalbeType = 'all';
			} else if(me.hasClass('opened')) {
				that.setTables('opened', that.CurrentArea);
				that.CurrentTalbeType = 'opened';
			} else {
				that.setTables('free', that.CurrentArea);
				that.CurrentTalbeType = 'free';
			}
		});
	},

	//验证用户
	verifyUser: function(serverName, cb){
		$.ajax({
			url: _config.interfaceUrl.VerifyUser,
			method: 'POST',
			contentType: "application/json",
			data: JSON.stringify({
				loginType: '030101',
				username: serverName
			}),
			dataType:'json',
			success: function(res){
				if(res.code === '0') {
					var alertModal = widget.modal.alert({
						cls: 'fade in',
						content:'<strong>' + '确认开台' + '</strong>',
						width:500,
						height:500,
						btnOkCb: function(){
							alertModal.close();
							cb && cb();
						}
					});
				} else {
					widget.modal.alert({
						cls: 'fade in',
						content:'<strong>' + res.msg + '</strong>',
						width:500,
						height:500,
						btnOkTxt: '',
						btnCancelTxt: '确定'
					});
				}
			}
		})
	},

	/**
	 * 设置餐桌
	 * @param type [opened, free, all]
	 * @param areaid
	 */
	setTables: function(type,areaid){

		var type = type || this.CurrentTalbeType;
		var areaid = areaid || this.CurrentArea;

		function _getTablesArr(res){

			var isOpend = false;
			var tablesAll = [];
			var tablesFree = [];
			var tablesOpened = [];

			$.each(res, function(key,val){
				var tmp = '';
				isOpend = val.status === '0' ? false : true;

				if(areaid === val.areaid || areaid === '-1') {
					if(isOpend) {
						tmp = '<li class="opened" orderid="'+ val.orderid  + '" personNum="'+ val.personNum  + '" tableno="' + val.tableNo + '" areaid="' + val.areaid + '">'+ val.tableNo +
							'<div class="tb-info tb-status">' + val.fixprice + '</div>' +
							'<div class="tb-info meal-time">' + val.begintime + '</div> ' +
							'<div class="tb-info tb-person">' + val.personNum + '</div>' +
							' </li>';

						tablesOpened.push(tmp);
					} else {
						tmp = '<li orderid="'+ val.orderid  + '" personNum="'+ val.personNum  + '" tableno="' + val.tableNo + '" areaid="' + val.areaid + '">'+ val.tableNo +
							'<div class="tb-info tb-person">' + val.personNum + '</div>' +
							' </li>'
						tablesFree.push(tmp);
					}
					tablesAll.push(tmp);
				}
			});

			return {
				all : tablesAll,
				free : tablesFree,
				opened : tablesOpened
			}
		}

		$.ajax({
			url: _config.interfaceUrl.GetTableInfoByTableType,
			method: 'POST',
			contentType: "application/json",
			data: JSON.stringify({
				tableType: [1,0,5]
			}),
			dataType:'json',
			success: function(res){

				var tables = _getTablesArr(res);
				var navRoomTypesArr = [];
				var navRoomTypes = $('#nav-room-types');

				//设置餐桌统计
				$('.J-table-nums').find('.all .num').text(tables.all.length)
					.end().find('.free .num').text(tables.free.length)
					.end().find('.opened .num').text(tables.opened.length);

				//设置区域
				if(navRoomTypes.attr('inited') !== 'true'){
					navRoomTypesArr.push('<li class="active" areaid="-1">全部</li>')
					$.each(res, function(key, val){
						navRoomTypesArr.push('<li areaid="' + val.areaid  + '">' + val.areaname  + '</li>');
					});
					navRoomTypes.attr('inited', 'true');
					navRoomTypes.html(utils.array.unique(navRoomTypesArr).join(''));
				}

				if(tables[type].length == 0) {
					$('#J-table-pager').html('');
					$("#standard-tables").html('');
				} else {
					//初始化分页
					$('#J-table-pager').pagination({
						dataSource: tables[type],
						pageSize: 40,
						showPageNumbers: false,
						showNavigator: true,
						callback: function(data) {
							$("#standard-tables").html(data.join(''));
							//$("#standard-tables").find('li').eq(0).trigger('click');
						}
					});
				}

			}
		})
	},
	/**
	 * 清机
	 */
	clearAll:function () {
		$("#J-btn-checkout-dialog").modal('hide')
		widget.modal.alert({
			cls: 'fade in',
			content:'<strong>清机中，请稍后</strong>',
			width:500,
			height:500,
			hasBtns:false,
		});
		$.ajax({
			url: _config.interfaceUrl.Clearner+''+utils.storage.getter('aUserid')+'/'+utils.storage.getter('fullname')+'/'+utils.storage.getter('ipaddress')+'/'+utils.storage.getter('posid')+'/'+utils.storage.getter('fullname')+'/',
			type: "get",
			dataType: "json",
			success: function (data) {
				$(".modal-alert:last,.modal-backdrop:last").remove();
				data=JSON.parse(data.result[0])
				if(data.Data === '0') {//清机失败
					widget.modal.alert({
						cls: 'fade in',
						content:'<strong>' + data.Info + '</strong>',
						width:500,
						height:500,
						btnOkTxt: '确定',
						btnCancelTxt: ''
					});
				}
				else {//清机成功
					utils.reprintClear.get()//打印清机单
					window.location = "../views/login.jsp";
				}
			}
		});
	},
	/*结业清机*/
	clearAllcheckOut:function () {
		var that=this
		$("#J-btn-checkout-dialog").modal('hide')
		var that=this;
		widget.modal.alert({
			cls: 'fade in',
			content:'<strong>清机中，请稍后</strong>',
			width:500,
			height:500,
			hasBtns:false,
		});
		$.ajax({
			url: _config.interfaceUrl.Clearner+''+$.trim($('#user').val())+'/'+utils.storage.getter('checkout_fullname')+'/'+utils.storage.getter('ipaddress')+'/'+utils.storage.getter('posid')+'/'+utils.storage.getter('checkout_fullname')+'/',
			type: "get",
			dataType: "json",
			success: function (data) {
				data=JSON.parse(data.result[0])
				if(data.Data === '0') {//清机失败
					$(".modal-alert:last,.modal-backdrop:last").remove();
					widget.modal.alert({
						cls: 'fade in',
						content:'<strong>' + data.Info + '</strong>',
						width:500,
						height:500,
						btnOkTxt: '确定',
						btnCancelTxt: ''
					});
				}
				else {//清机成功
					utils.reprintClear.get()//打印清机单
					$(".modal-alert:last,.modal-backdrop:last").remove();
					that.checkout()
				}
			}
		});
	},
	checkout:function () {
		var that=this;
		var Uncleandata=that.getFindUncleanPosList();
		var arrylength=Uncleandata.LocalArry.length-1;
		var LocalArry=Uncleandata.LocalArry;
		if(Uncleandata.LocalArry.length>0){
			$("#J-btn-checkout-dialog").load("../views/check/impower.jsp",{'title':'清机授权','userNmae':Uncleandata.LocalArry[arrylength].username,'usernameDisble':'2','cbd':'MainPage.clearAllcheckOut()','userRightNo':'030204'});
			$("#J-btn-checkout-dialog").modal('show')
		}
		if(Uncleandata.LocalArry.length==0&&Uncleandata.OtherArry.length>0){
			widget.modal.alert({
				cls: 'fade in',
				content:'<strong>还有其他POS机未清机,<br><br>请到其他POS机上先清机</strong>',
				width:500,
				height:500,
				btnOkTxt: '重试',
				btnCancelTxt: '',
			    btnOkCb:function () {
					$(".modal-alert:last,.modal-backdrop:last").remove();
					that.checkout()
			     }
			});
		}
		if(Uncleandata.findUncleanPosList.detail.length=='0'){
			$("#J-btn-checkout-dialog").load("../views/check/impower.jsp",{'title':'结业授权','cbd':'MainPage.checkoutCallback()','userRightNo':'030205'});
			$("#J-btn-checkout-dialog").modal('show')
		}


	},
	checkoutCallback:function () {//结业回调
		$.ajax({
			url: _config.interfaceUrl.EndWork,//不需要传递参数
			type: "get",
			dataType:'text',
			success: function (data) {
				$("#J-btn-checkout-dialog").modal('hide')
				var  data=JSON.parse(data.substring(12, data.length - 3));//从第12个字符开始截取，到最后3位，并且转换为JSON
				if(data.Data=='1'){
					/*$.ajax({
						url: _config.interfaceUrl.EndWorkSyncData,//结业数据上传
						type: "get",
						dataType:'text',
						data:{
							'synkey':'candaosynkey'
						},
						success: function (data) {
							console.log(data)
						},
						error:function (data) {
							console.log(data)
							alert("1111")
						}
					});*/
					widget.modal.alert({
						cls: 'fade in',
						content:'<strong>'+data.Info+',即将退出程序</strong>',
						width:500,
						height:500,
						btnOkTxt: '确定',
						btnCancelTxt: '',
						btnOkCb:function () {
							$(".modal-alert:last,.modal-backdrop:last").remove();
							window.location = '../views/openpage.jsp?ipaddress='+utils.storage.getter('ipaddress')+'&posid='+utils.storage.getter('posid');
							utils.clearLocalStorage.clearSelect();
						}
					});
				}
				else {
					widget.modal.alert({
						cls: 'fade in',
						content:'<strong>'+data.Info+'</strong>',
						width:500,
						height:500,
						btnOkTxt: '确定',
						btnCancelTxt: ''
					});
				}
			}
		});
	},
	getFindUncleanPosList:function () {//获取未清机数据列表
		var findUncleanPosList ,LocalArry=[],OtherArry=[]
		$.ajax({
			url: _config.interfaceUrl.GetAllUnclearnPosInfoes,
			type: "get",
			async:false,
			dataType: "text",
			success: function (data) {
				findUncleanPosList=JSON.parse(data)
				/*console.log(findUncleanPosList.detail)
				console.log(findUncleanPosList.result)*/
				if(findUncleanPosList.result==='0'){
					LocalArry=[];//本机数组集合
					OtherArry=[];//其他pos登录集合
					for(var i=0;i<findUncleanPosList.detail.length;i++){
						if(findUncleanPosList.detail[i].ipaddress==utils.storage.getter('ipaddress')){
							LocalArry.push(findUncleanPosList.detail[i])
						}
						else {
							OtherArry.push(findUncleanPosList.detail[i])
						}
					}
				}
			}
		});
		return{
			findUncleanPosList:findUncleanPosList,
			LocalArry:LocalArry,
			OtherArry:OtherArry,
		}
	}
};

$(function(){
	MainPage.init();
});

var pay_nowPage = 0;
function paging(currPage) {
	var o = $(".tab-div ul li.active").attr("loaddiv") + " li";
	nowPage = loadPage({
		obj : o,
		listNum : 40,
		currPage : currPage,
		totleNums : $(o).length,
		curPageObj : "#curr-page",
		pagesLenObj : "#pages-len",
		prevBtnObj : ".page .prev-btn",
		nextBtnObj : ".page .next-btn"
	});
}

function page(options) {
	return loadPage(options);
}
function selPayCompany(){
	pay_nowPage = 0;
	payPage(0);
	$("#select-paycompany-dialog").modal("show");
	$(".paycompany-content ul li").unbind("click").on("click", function(){
		$(".paycompany-content ul li").removeClass("active");
		$(this).addClass("active");
	});
	
	//�ѵ����һҳ
	$("#select-paycompany-dialog .prev-btn").unbind("click").on("click", function(){
		if ($(this).hasClass("disabled")) {
			return false;
		}
		payPage(pay_nowPage - 1);
	});
	//�ѵ����һҳ
	$("#select-paycompany-dialog .next-btn").unbind("click").on("click", function(){
		if ($(this).hasClass("disabled")) {
			return false;
		}
		payPage(pay_nowPage + 1);
	});
}
function payPage(currPage) {
	pay_nowPage = loadPage({
		obj : ".paycompany-content ul li",
		listNum : 8,
		currPage : currPage,
		totleNums : $(".paycompany-content ul li").length,
		curPageObj : "#select-paycompany-dialog #pay-curr-page",
		pagesLenObj : "#select-paycompany-dialog #pay-pages-len",
		prevBtnObj : "#select-paycompany-dialog .prev-btn",
		nextBtnObj : "#select-paycompany-dialog .next-btn",
		callback : function() {
		}
	});
}