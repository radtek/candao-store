var orderdata='',orderId='',rebackOrderReason=''
$(function () {

    checkOrder.int("");
});
var aUserid=utils.storage.getter('aUserid')//获取登录用户
var checkOrder={
    int:function (orderstatus) {
        SetBotoomIfon.init();//设置底部信息
        this.selectTr();
        this.search();
        this.getOrderdata;
        this.getOrderlist(orderstatus);
        this.switchOrderlist();
        this.bottomEvent();
        //加载虚拟键盘组件
        widget.keyboard({
            target: '.keyboard'
        });


    },
    getOrderdata: {
        getData: function () {//获取接口数据
            $.ajax({
                url: _config.interfaceUrl.QueryOrderInfo+''+ aUserid + '/',
                type: "get",
                async: false,
                dataType: "text",
                success: function (data) {
                    orderdata = JSON.parse(data.substring(12, data.length - 3));//从第12个字符开始截取，到最后3位，并且转换为JSON
                }
            });
            return orderdata

        }

    },
    getOrderlist:function (orderstatus ) {
        var that=this ,data="";
        $("#checklist tbody,#pagDome").html("");
        filtrateDatd();
        function filtrateDatd() {//数据筛选
            if (orderdata == "") {
                that.getOrderdata.getData();
            }
            data = orderdata.OrderJson;
            if (orderdata.Data == 1) {
                if (data.length == 0) {
                    $('#pagDome').hide()
                }
                else {
                    $('#pagDome').show()
                }
                if (orderstatus == "") {
                    data = data
                }
                if (orderstatus == "3") {
                    var arry = [];
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].orderstatus == 3) {
                            arry.push(data[i])
                        }
                    }
                    data = arry
                }
                if (orderstatus == "0") {
                    var arry = [];
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].orderstatus == 0) {
                            arry.push(data[i])
                        }
                    }
                    data = arry
                }

                var orderNo = $.trim($("#orderNo").val())
                var deskNo = $.trim($("#deskNo").val())
                if (orderNo != "" || deskNo != "") {
                    var arry = [];
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].orderid.indexOf(orderNo) > -1 && data[i].tableName.indexOf(deskNo) > -1) {
                            arry.push(data[i])
                        }
                    }
                    data = arry;
                    console.log(data)
                }

            }
        }
        $('#pagDome').pagination({
            dataSource: data,
            pageSize: 12,
            showPageNumbers: false,
            showNavigator: true,
            callback: function(data, pagination) {
                var str="";
                for( var i=0;i<data.length;i++) {

                    if(data[i].memberno){//判断是否是会员登录
                        str+='<tr orderid='+data[i].orderid+' orderstatus='+data[i].orderstatus+' memberno='+data[i].memberno+'>';
                    }
                    else {
                        str+='<tr orderid='+data[i].orderid+' orderstatus='+data[i].orderstatus+'>';
                    }
                    str+='   <td>'+data[i].orderid+'</td>'
                    var  ordertype='';
                    switch (data[i].orderstatus+""){
                        case "0": ordertype="未结"; break;
                        case "3": ordertype="已结"; break;
                        default:  ordertype="未知";break;
                    };
                    str+='   <td>'+ordertype+'</td>'
                    str+='   <td>'+data[i].areaname+'</td>'
                    str+='   <td>'+data[i].tableName+'</td>'
                    str+='   <td>'+data[i].userid+'</td>'
                    str+='   <td>'+(data[i].begintime).substring(8)+'</td>'
                    if(data[i].endtime===undefined){
                        str+='   <td></td>'
                    }
                    else{
                        str+='   <td>'+(data[i].endtime).substring(8)+'</td>'
                    }
                    str+='   <td>'+data[i].custnum+'</td>'
                    str+='   <td>'+data[i].dueamount+'</td>'
                    if(data[i].gzname===undefined){
                        str+='   <td></td>'
                    }
                    else{
                        str+='   <td>'+data[i].gzname+'</td>'
                    }
                    if(data[i].gztele===undefined){
                        str+='   <td></td>'
                    }
                    else{
                        str+='   <td>'+data[i].gztele+'</td>'
                    }
                    if(data[i].gzuser===undefined){
                        str+='   <td></td>'
                    }
                    else{
                        str+='   <td>'+data[i].gzuser+'</td>'
                    }

                    str+="</tr>";
                };
                $("#checklist tbody").html(str);
                $("#checklist tr").eq(1).trigger("click")//默认选中第一行
            }
        });

    },
    switchOrderlist:function () {
        var that = this
        $(".check-type div").click(function () {//账单状态
            var orderstatus = $(this).attr("orderstatus");
            $(this).addClass("active").siblings("div").removeClass("active");
            that.getOrderlist(orderstatus)
        })
    },
    refreshOrderlist:function () {//刷新
        var that=this;
        var orderstatus=$(".check-type .active").attr("orderstatus");
        that.getOrderdata.getData();
        that.getOrderlist(orderstatus)
    },

    reprintCheck:function () {//重印账单
        $.ajax({
            url:_config.interfaceUrl.PrintPay+'/' + aUserid + '/'+orderId+'/2/'+utils.storage.getter('posid')+'/',
            type: "get",
            success: function (data) {
                if(data.result=='0'){
                    rightBottomPop.alert({
                        content:"结账单打印完成",
                    })
                }
                else {
                    utils.printError.alert('结账单打印失败，请稍后重试')
                }

            }
        });
    },
    receipt:function () {//会员交易凭条
        $.ajax({
            url:_config.interfaceUrl.PrintMemberSale+'/' + aUserid + '/'+orderId+'/'+utils.storage.getter('posid')+'/',
            type: "get",
            success: function (data) {
                if(data.result=='0'){
                    rightBottomPop.alert({
                        content:"交易凭条打印完成",
                    })
                }
                else {
                    utils.printError.alert('交易凭条打印失败，请稍后重试')
                }
            }
        });
    },
    rebackOrder:{//反结算跳转
        jumpfjs:function(user) {
            $.ajax({
                url: _config.interfaceUrl.AntiSettlementOrder,//反结算
                method: 'POST',
                contentType: "application/json",
                data: JSON.stringify({
                    'reason':rebackOrderReason,
                    'orderNo':orderId,
                    'userName':user
                }),
                dataType: "json",
                success:function (data) {
                    console.log(data)
                    if(data.result==='0'){
                        $('#c-mod-fjs').modal("hide")
                        $("#order-dialog").modal('show');
                        $("#order-dialog").load("../orderdish.jsp",{"fromType":"1"});
                    }

                }
            })
        },
        rebackOrder:function () {//反结算
            var that=this
            _getOrder();
            function _getOrder() {//二次确认弹窗
                var str = '<div class="js_Ok" style="text-align: left;">订单号：<br>'+orderId+'确定反结算吗？</div>';
                var alertModal = widget.modal.alert({
                    cls: 'fade in',
                    content:str,
                    width:500,
                    height:500,
                    title: "",
                    btnOkTxt: '确定',
                    btnOkCb: function(){
                        _getBackinfo();
                        $(".modal-alert,.modal-backdrop").remove();
                    },
                    btnCancelCb: function(){
                    }
                });
            };
            function _getBackinfo() {//获取是否生成清机单
                $.ajax({
                    url:_config.interfaceUrl.CheckAntiSettleOrder+'' + aUserid + '/'+orderId+'/',
                    type: "get",
                    dataType: "text",
                    success: function (data) {
                        data = JSON.parse(data.substring(12, data.length - 3));//从第12个字符开始截取，到最后3位，并且转换为JSON
                        console.log(data)
                        if(data.Data=="0"){
                            _getClear(data.Info)
                        };
                        if(data.Data=="1"){
                            _whyClear()
                        }
                    }
                });
            };
            function _getClear(info) {//已经生成清机单不能结业
                var str = '<div class="js_Ok" ><br>'+info+'</div>';
                var alertModal = widget.modal.alert({
                    cls: 'fade in',
                    content:str,
                    width:500,
                    height:500,
                    title: "",
                    btnOkTxt: '确定',
                    btnCancelTxt:"",
                    btnOkCb: function(){
                    },
                    btnCancelCb: function(){
                    }
                });
            };
            function _noChoiceClearReason() {//没有选择反结算原因
                var str = '<div class="js_Ok" ><br>请选择一个反结算原因</div>';

                var alertModal = widget.modal.alert({
                    cls: 'fade in',
                    content:str,
                    width:500,
                    height:500,
                    title: "",
                    btnOkTxt: '确定',
                    btnCancelTxt:"",
                    btnOkCb: function(){
                        $(".modal-alert:last,.modal-backdrop:last").remove();
                    },
                    btnCancelCb: function(){
                    }
                });
            }
            function _whyClear() {
                $.ajax({
                    url: '../../scripts/config.json',
                    type:"get",
                    dataType:'text',
                    success: function(res){
                        var res= res.split("*/");//以注释结尾分割
                        res=JSON.parse(res[1])['ResettlementReason'];//获取反结算原因
                        res=res.split(";")//以；分割成数值
                        var     str = '<div class="selectReason" style="text-align: left">'
                        str+=   '<div class="form-group form-group-base form-input">'
                        str+='   <span class="form-label" style="line-height: 40px">反结原因:</span>'
                        str+='   <input id="selectReason" value="" name="selectReason" type="text" class="form-control" style="height: 40px;line-height: 40px;padding-left: 75px;width: 250px;" autocomplete="off">'
                        str+= '</div><br/>'
                        for(var i=0;i<res.length;i++){//反结算原因列表
                            str+=  '<label><input name="Fruit" type="radio" value='+res[i]+' />'+res[i]+'</label><br/>'
                        }
                        str+='</div>';
                        var alertModal = widget.modal.alert({
                            cls: 'fade in',
                            content: str,
                            width: 500,
                            height: 500,
                            title: "请选择反结原因",
                            btnOkTxt: '确定',
                            btnOkCb: function () {
                                if($("#selectReason").val()==""){
                                    _noChoiceClearReason();
                                    return
                                }
                                $(".modal-alert,.modal-backdrop").remove();
                                $("#c-mod-fjs").load("../check/impower.jsp",{"title" : "反结算授权","userRightNo":"030203","cbd":"checkOrder.rebackOrder.jumpfjs(user)"});
                                $("#c-mod-fjs").modal("show");
                            },
                            btnCancelCb: function () {

                            }
                        });
                        //选择给input赋值
                        $(".selectReason input").click(function () {
                            $("#selectReason").val($(this).val());
                            rebackOrderReason=$("#selectReason").val();
                        })
                    }
                })
            };
        },
    },
    clearing:function () {//结算
        debugger
        var userRight= utils.userRight.get(aUserid,"030206")//判断收银权限
        if(userRight){
            $("#order-dialog").modal('show');
            $("#order-dialog").load("../orderdish.jsp",{"fromType":"1"});
        }
        else {
            var str = '<div class="js_Ok" ><br>您没有收银权限</div>';
            widget.modal.alert({
                cls: 'fade in',
                content:str,
                width:500,
                height:500,
                title:'',
                btnOkTxt: '确定',
                btnCancelTxt: ''
            });
        }
    },
    selectTr:function () {
        var that=this;
        $("body").on("click","#checklist tr" ,function(){//点击tr变色
            $(this).addClass("tablistActive").siblings("tr").removeClass("tablistActive");
            var orderstatus=$.trim($(this).attr("orderstatus")) ,memberno=$(this).attr("memberno");
            orderId= $.trim($(this).attr("orderid"))
           // console.log(memberno+","+orderId+","+orderstatus)
            if(memberno !=undefined){//判断是否存在会员登录
                $(".receipt").removeAttr("disabled").removeClass("disabled");
            }
            else {
                $(".receipt").attr("disabled","disabled").addClass("disabled");
            };
            if(orderstatus=="0"){
                $(".c-mod-fjs,.reprintCheck,.receipt").attr("disabled","disabled").addClass("disabled");//反结算按钮，重印账单按钮disabled
                $(".c-mod-js").removeAttr("disabled","disabled").removeClass("disabled");//结算按钮移除disabled
            }
            if(orderstatus=="3"){
                $(".c-mod-js").attr("disabled","disabled").addClass("disabled");//结算按钮disabled
                $(".c-mod-fjs ,.reprintCheck,.receipt").removeAttr("disabled","disabled").removeClass("disabled");//反结算按钮，重印账单按钮移除disabled
            }
        });
    },
    search:function () {
        var that=this;
        $('#orderNo,#deskNo').on('input propertychange focus', function() {
            var orderstatus=$(".check-type .active").attr("orderstatus");
            that.getOrderlist(orderstatus)
        });
    },
    bottomEvent:function () {
        var that=this;
        $(".foot-menu button").click(function () {
            var me=$(this);
            if(me.hasClass("refresh")){//刷新
                that.refreshOrderlist();
            }
            if(me.hasClass("reprintClear")){//重印清机单
                utils.reprintClear.get()
            }
            if(me.hasClass("reprintCheck")){//重印账单
                that.reprintCheck()
            }
            if(me.hasClass("receipt")){//会员交易凭条
                that.receipt()
            }
            if(me.hasClass("c-mod-fjs")){//反结算
                that.rebackOrder.rebackOrder()
            }
            if(me.hasClass("c-mod-js")){//反结算
                that.clearing()
            }
        })

    },
}