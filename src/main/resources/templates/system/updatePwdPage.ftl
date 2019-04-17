<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>修改密码</title>

    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <style type="text/css">
        .default {background: #eeeeee;}
        .weak {background: #FF0000;}
        .medium {background: #FF9900;}
        .strong {background: #33CC00;}

        span  {display: inline-block;width: 70px;height: 30px;line-height: 30px;background: #ddd;text-align: center;margin: 4px 2px;}
        #newPassword-error{
            width: 180px;
        }
        #oldPassword-error{
            width: 180px;
        }
        #rPassword-error{
            width: 180px;
        }
    </style>
</head>
<#include "/head.ftl">

<body class="jui-linghtgreen-theme">
<div class="main-content">

    <div class="main-content-inner">
        <!-- /.page-header -->
    <#-- <div class="breadcrumbs">
         <ol class="breadcrumb no-margin-left">
             <li><i class="ace-icon fa fa-home home-icon"></i><a href="#">商城管理</a></li>
             <li><a href="#">模板管理</a></li>
             <li class="active">子菜单A</li>
         </ol>
     </div>-->
        <div class="jui-page">
            <div class="page-content">
                <div class="jui-back clearfix">

                    <a class="jui-back-icon" onclick="history.go(-1)"><i class="ace-icon fa fa-arrow-circle-left"></i></a>

                    <div class="jui-back-text">
                        <div class="jui-back-title">密码修改</div>
                    </div>
                </div>
            </div>
            <div class="page-content margin-top">
                <form class="form-horizontal jui-padding-top" id="pwdForm">
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right emphasis"> 旧密码 </label>

                        <div class="col-sm-8">
                            <input type="password" placeholder="旧密码" name="oldPassword"  id="oldPassword"  class="col-xs-10 col-sm-5" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right emphasis"> 新密码 </label>

                        <div class="col-sm-8">
                            <input type="password" placeholder="新密码"  name="newPassword" id="newPassword"  class="col-xs-10 col-sm-5" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right emphasis">密  码  强  度</label>
                        <div class="col-sm-3" >
                            <span class="default">弱</span><span class="default">中</span><span class="default">强</span>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label no-padding-right emphasis"> 确认密码 </label>

                        <div class="col-sm-8">
                            <input type="password" placeholder="确认密码"  name="rPassword" class="col-xs-10 col-sm-5" />
                        </div>
                    </div>

                   <div class="form-group jui-btn-group center">
                     <button type="button" id="savePwd"  class="btn btn-lightblue">保存</button>
                     <button type="button" id="cancel" class="btn btn-gray">取消</button>
                 </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
<#include "/foot.ftl">
<script>
    function getStrong(pwd) {
        var result = 0;
        for ( var i = 0, len = pwd.length; i < len; ++i) {
            result |= charType(pwd.charCodeAt(i));
        }
        var level = 0;
        //对result进行四次循环，计算其level
        for ( var i = 0; i <= 4; i++) {
            if (result & 1) {
                level++;
            }
            //右移一位
            result = result >>> 1;
        }
        return level;
    }

    function charType(num) {
        if (num >= 48 && num <= 57) {
            return 1;
        }
        if (num >= 97 && num <= 122) {
            return 2;
        }
        if (num >= 65 && num <= 90) {
            return 4;
        }
        return 8;
    }

    var $pwdInfoForm = $('#pwdForm').validate({
        rules: {
            oldPassword: {
                required: true,
                minlength: 6,
            }, newPassword: {
                required: true,
                minlength: 6

            }, rPassword: {
                required: true,
                equalTo: "#newPassword"
            }
        }, messages: {
            oldPassword: {
                required: "请输入旧密码",
                minlength: "旧密码长度不能低于6位"
            }, newPassword: {
                required: "请输入新密码",
                minlength: "新密码长度不能低于6位"

            }, rPassword: {
                equalTo: "两次密码不一致"
            }
        },
        errorClass: "text-danger",
        errorElement: "em"
    });
    $(function(){
        var level=0;
        $("#cancel").click(function(){
            $("#pwdForm")[0].reset();
        })
        var spans = $('.default');
        $("#newPassword").on('keyup',function(){
            //强度状态设为默认
            spans[0].className ="default";
            spans[1].className="default";
            spans[2].className ="default" ;
            var pwd = this.value;
            level= getStrong(pwd);
            if(pwd.length >= 6){
                switch (level) {
                    case 1:
                        spans[0].className = "weak";
                        break;
                    case 2:
                        spans[0].className = "medium";
                        spans[1].className = "medium";
                        break;
                    case 3:
                    case 4:
                        spans[0].className = "strong";
                        spans[1].className = "strong";
                        spans[2].className = "strong";
                        break;
                }
            }

        })
        $("#savePwd").click(function(){
            if($pwdInfoForm.form()){
                if( level<2){
                    bootbox.alert("密码强度太低,请修改密码再试！");
                    return;
                }
                var oldPwd=$.md5($("#oldPassword").val());
                var newPwd=$.md5($("#newPassword").val());
                $.ajax({
                    "type":"GET",
                    "url":"${base}/system/user/updateUserPwd",
                    "data":{oldPwd:oldPwd,newPwd:newPwd},
                    "dataType":"json",
                    "success":function(data){
                        if(data.code==1){
                            window.parent.location.href="${base}/login";
                        }else{
                            bootbox.alert(data.msg);
                        }

                    }
                });
            }
        })


    })
</script>



</html>