<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>个人信息</title>

    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
</head>
<#include "/head.ftl">

<body class="jui-linghtgreen-theme">
                <div class="main-content">
                    <div class="main-content-inner">
                        <div class="jui-page">
                            <div class="page-content">
                                <div class="jui-back clearfix">

                                    <a class="jui-back-icon" onclick="history.go(-1)"><i class="ace-icon fa fa-arrow-circle-left"></i></a>

                                    <div class="jui-back-text">
                                        <div class="jui-back-title">个人信息</div>
                                    </div>
                                </div>
                            </div>
                            <div class="page-content margin-top">
                                <form class="form-horizontal jui-padding-top">
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label no-padding-left emphasis"> 账号 </label>

                                        <div class="col-sm-8">
                                            <input type="text" placeholder="账号" id="userName" readonly style="border: 0px;outline:none;cursor: pointer;"  class="col-xs-10 col-sm-5" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label no-padding-left emphasis"> 昵称 </label>

                                        <div class="col-sm-8">
                                            <input type="text" placeholder="昵称" readonly id="nikeName" style="border: 0px;outline:none;cursor: pointer;"   class="col-xs-10 col-sm-5" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label no-padding-left emphasis"> 手机号 </label>

                                        <div class="col-sm-8">
                                            <input type="text" placeholder="手机号" readonly id="phone" style="border: 0px;outline:none;cursor: pointer;"  class="col-xs-10 col-sm-5" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label no-padding-left emphasis"> 邮箱 </label>

                                        <div class="col-sm-8">
                                            <input type="text" placeholder="邮箱" readonly id="email" style="border: 0px;outline:none;cursor: pointer;"   class="col-xs-10 col-sm-5" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label no-padding-left emphasis"> 创建时间 </label>

                                        <div class="col-sm-8">
                                            <input type="text" placeholder="创建时间" id="createTime" style="border: 0px;outline:none;cursor: pointer;"  readonly class="col-xs-10 col-sm-5" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label no-padding-left emphasis"> 更新时间 </label>

                                        <div class="col-sm-8">
                                            <input type="text" placeholder="更新时间" id="updateTime" style="border: 0px;outline:none;cursor: pointer;"  readonly class="col-xs-10 col-sm-5" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label no-padding-left emphasis"> 最近登录时间 </label>

                                        <div class="col-sm-8">
                                            <input type="text" placeholder="最近登录时间" id="lastLoginTime" style="border: 0px;outline:none;cursor: pointer;"  readonly class="col-xs-10 col-sm-5" />
                                        </div>
                                    </div>
                                   <#-- <div class="form-Group jui-btn-Group center">
                                        <button type="button"  class="btn btn-lightblue">保存</button>
                                        <button type="button" class="btn btn-gray">取消</button>
                                    </div>-->
                                </form>
                            </div>
                        </div>
                    </div>
             </div>
    </body>
<#include "/foot.ftl">
<script>
    $(function(){
        var  userId="${userId}";
        $.ajax({
            "type":"GET",
            "url":"${base}/system/user/getUserRoles",
            "data":{userId:userId},
            "dataType":"json",
            async:false,
            "success":function(data){
                if(data.code==1){
                  var retObj=data.obj;
                    if(!retObj){
                        bootbox.alert("获取用户信息失败!");
                        return;
                    }
                    $("#userName").val(retObj.userName);
                    $("#nikeName").val(retObj.nikeName);
                    $("#email").val(retObj.email);
                    $("#phone").val(retObj.phone);
                    $("#createTime").val(dateFormat(retObj.createTime) );
                    $("#updateTime").val(dateFormat(retObj.updateTime) );
                    $("#lastLoginTime").val(dateFormat(retObj.lastLoginTime) );
                }
            }
        });

    })
</script>


</html>