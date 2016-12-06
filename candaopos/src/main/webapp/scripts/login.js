/**
 * Created by Alex on 16/9/20.
 */
'use strict';
$(function () {
    $('input:text:first').focus();

});
var activeinputele;

var dom = {
    remember: $('#J_remember_pwd'),
    pwd: $('#pwd'),
    user: $('#user'),
    thechangeSubmit: $('#J-thechange-submit')
};
/*$(document).keydown(function(event){
    if(event.keyCode==13){
        $('.btn-primary').each(function () {
            var me=$(this);
            if(me.hasClass('J-login-form-submit')){
                me.click();
                return false
            }
            if(me.attr('id')=='J-thechange-submit'){
                me.click();
                return false
            }

        })

    }
});*/
var Login = {
    init: function(){
        /*绑定虚拟键盘*/
        widget.keyboard({
            target: '.virtual-keyboard'
        });
        this.bindEvent();

        this.setDefaultLoginForm();

        //this.getBranchInfo();


    },

    bindEvent: function(){
        var that = this;

        //记住密码
        dom.remember.click(function(){
            var me = $(this);

            if(me.is(':checked')) {
                utils.storage.setter('remember_user', $.trim(dom.user.val()));
                utils.storage.setter('remember_pwd', $.trim(dom.pwd.val()));
            } else {
                utils.storage.remove('remember_user');
                utils.storage.remove('remember_pwd');
            }

        });

        //登录
        $('.J-login-form-submit').click(function(){
            Log.send(2, '登录参数:' + JSON.stringify({
                    username: $.trim(dom.user.val()),
                    password: hex_md5($.trim(dom.pwd.val())),
                    macAddress: utils.storage.getter('ipaddress'),//IP地址
                    loginType: '030201'
                }));
            $.ajax({
                url: _config.interfaceUrl.AuthorizeLogin,
                method: 'POST',
                contentType: "application/json",
                data: JSON.stringify({
                    username: $.trim(dom.user.val()),
                    password: hex_md5($.trim(dom.pwd.val())),
                    macAddress: utils.storage.getter('ipaddress'),//IP地址
                    loginType: '030201'
                }),
                dataType:'json',
                success: function(res){
                    Log.send(2, '登录返回:' + JSON.stringify(res));
                    if(res.code === '0') {
                        utils.storage.setter('fullname', res.data.fullname);
                        utils.storage.setter('loginTime', res.data.loginTime);
                        utils.storage.setter('aUserid',  $.trim(dom.user.val()));
                        utils.storage.setter('aUserid', dom.user.val(),1)//设置sessionStorage
                        that.setUserRight($.trim(dom.user.val()));
                        that.getBranchInfo();
                        var iSuserRight=utils.userRight.get($.trim(dom.user.val()),'030206');
                        if(iSuserRight){//验证是否有收银权限，iSuserRight为true时验证零找金，false直接跳转登录页面
                            $.ajax({//验证零找金
                                url: _config.interfaceUrl.PettyCashInput+''+utils.storage.getter('aUserid')+'/'+utils.storage.getter('ipaddress')+'/0/0/',
                                type: "get",
                                dataType: "text",
                                success: function (data) {
                                    var  data=JSON.parse(data.substring(12, data.length - 3));//从第12个字符开始截取，到最后3位，并且转换为JSON
                                    Log.send(2, '验证零找金:' + JSON.stringify(data));
                                    if(data.Data=='0'){
                                        $("#change_val").val("");
                                        focusIpt = $('#change_val');
                                        $("#thechange-dialog").modal("show");
                                    }
                                    else {
                                        window.location = "../views/main.jsp";
                                    }
                                }
                            });
                        }
                        else {
                            if(utils.storage.getter('isYesterdayEndWork')==='1'){
                                Log.send(2, '请登录收银员账号，以便餐台结账')
                                widget.modal.alert({
                                    cls: 'fade in',
                                    content:'<div style="text-align: center;font-size: 20px;font-weight:bold ">请登录收银员账号，以便餐台结账。</div>',
                                    title:'',
                                    width:500,
                                    height:500,
                                    btnOkTxt: '确定',
                                    btnCancelTxt: '',
                                });
                            }
                            else {
                                window.location = "../views/main.jsp";
                            }

                        }


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

            });

            return false;
        });
        //找零
        dom.thechangeSubmit.click(function(){
            $("#confirm-change-val").text($("#change_val").val());
            $('#confirm-dialog').modal('show');
        });
    },

    setUserRight: function(username){
        Log.send(2, 'GetUserRight: ' + username)
        $.ajax({
            url: _config.interfaceUrl.GetUserRight,
            method: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                username: username
            }),
            dataType:'json',
            async: false,
            success: function(res){
                Log.send(2, 'GetUserRight:' +  JSON.stringify(res));
                if(res.result === '0') {
                    utils.storage.setter('user_rights', JSON.stringify(res.rights));
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

    getBranchInfo: function(){
        $.ajax({
            url: _config.interfaceUrl.GetBranchInfo,
            method: 'GET',
            type: 'json',
            async: false,
            success: function(res){
                if(res.code === '0') {
                    $.each(res.data,function(k,v){
                        utils.storage.setter('branch_' + k, v);
                    })
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

    setDefaultLoginForm: function(){
        var sUser = utils.storage.getter('remember_user');
        var sPwd = utils.storage.getter('remember_pwd');

        if(sUser !== null &&  sUser !== '') {
            dom.remember.attr("checked", true);
            dom.user.val(sUser);
            dom.pwd.val(sPwd);

        } else {
            dom.remember.removeAttr('checked');
        }
    },

    toMain: function(){
        Log.send(2,' InputTellerCash:' + _config.interfaceUrl.PettyCashInput+''+utils.storage.getter('aUserid')+'/'+utils.storage.getter('ipaddress')+'/'+$.trim($('#change_val').val())+'/1/');
        $.ajax({
            url: _config.interfaceUrl.PettyCashInput+''+utils.storage.getter('aUserid')+'/'+utils.storage.getter('ipaddress')+'/'+$.trim($('#change_val').val())+'/1/',
            type: "get",
            dataType: "text",
            success: function (data) {
                var  data=JSON.parse(data.substring(12, data.length - 3));//从第12个字符开始截取，到最后3位，并且转换为JSON
                Log.send(2, 'InputTellerCash' + JSON.stringify(data))
                if(data.Data=='1'){
                    window.location = "../views/main.jsp";
                    $("#thechange-dialog").modal("hide");
                }
            }
        });

    },

    keyUp: function(o){
        var me = $(o);
        if(me.val() != null && me.val() != ""){
            $("#J-thechange-submit").attr("disabled", false);
        }else{
            $("#J-thechange-submit").attr("disabled", true);
        }
    }
};

$(document).ready(function(){
    Login.init();
});
