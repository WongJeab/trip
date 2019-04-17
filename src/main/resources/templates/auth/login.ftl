<#assign base=request.contextPath />

<!DOCTYPE html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>登录</title>

    <meta name="description" content="User login page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${base}/static/assets/css/bootstrap.css?t=es5i5" />
    <link rel="stylesheet" href="${base}/static/assets/css/font-awesome.css?t=es5i5" />
    <!-- text fonts -->
    <link rel="stylesheet" href="${base}/static/assets/css/ace-fonts.css?t=es5i5" />
    <!-- ace styles -->
    <link rel="stylesheet" href="${base}/static/assets/css/ace.css?t=es5i5" />
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${base}/static/assets/css/ace-part2.css?t=es5i5" />
    <![endif]-->
    <link rel="stylesheet" href="${base}/static/assets/css/ace-rtl.css?t=es5i5" />
    <!--添加修改文件-->
    <link rel="stylesheet" href="${base}/static/assets/css/jui.css" />
</head>
<body class="jui-login">
<div class="jui-fixed-with">
    <div class="jui-table">
        <div class="jui-table-middle">
            <div class="jui-login-bottom" style="height: 12.3%">
            </div>
            <div class="jui-login-container">
                <div style="width: 350px;margin-left: 55%">
                    <div class="center">
                        <img src="${base}/static/assets/images/logo2.png?t=es5i5">
                    </div>
                    <form id="login"  class="layui-form">
                        <fieldset>
                            <label class="block clearfix form-group">
								<span class="block input-icon input-icon-right">
									<input type="text" class="form-control" required   id="userId" placeholder="请输入您的账号" />
									<i class="ace-icon fa fa-user"></i>
								</span>
                            </label>

                            <label class="block clearfix form-group">
								<span class="block input-icon input-icon-right">
									<input type="password" class="form-control" required   id="userPwd" placeholder="请输入您的密码" />
									<i class="ace-icon fa fa-lock"></i>
								</span>
                            </label>

                            <label class="block clearfix form-group">
                                <div class="row">
                                    <div class="col-md-8 col-sm-8 col-xs-8">
								  	<span class="block input-icon input-icon-right">
										<input type="text" class="form-control"    id="userCode" placeholder="请输入您的验证码" />
									</span>
                                    </div>
                                    <div class="col-md-4 col-sm-4 col-xs-4 login-code-box">
                                        <img src="${base}/randomImg?t=es5i5" id="randomImg" onclick="changeYzmCode()">
                                    </div>
                                </div>
                            </label>
                            <button type="button" lay-filter="demo2"  id="loginBtn"class="jui-btn">登陆</button>
                            <p style="text-align: right"><a href="##">忘记密码？</a></p>
                        </fieldset>
                    </form>
                </div>
            </div>
            <div class="jui-login-bottom" style="height: 20%">
                <div class="display" style="display: block;width: 100%;height: 100%">
                    <div class="" style="display: block;width: 100%;height: 100%;text-align: center;padding-top: 5%">
                        <a href="##" target="_blank">关于我们</a>
                        <span class="">|</span>
                        <a href="#" class="">联系我们</a>
                        <span class="">|</span>
                        <a href="#" class="">使用帮助</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
<script src="${base}/static/assets/js/html5shiv.min.js?t=es5i5"></script>


<![endif]-->
<script type="text/javascript" src="${base}/static/assets/js/jquery.min.js?t=es5i5"></script>
<!--md5-->
<script src="${base}/static/assets/js/jquery.md5.js?t=es5i5"></script>

<!-- 弹层插件 -->
<script type="text/javascript" src="${base}/static/assets/js/layer.js?t=es5i5"></script>

<!-- 表格插件 -->
<script src="${base}/static/assets/js/dataTables/jquery.dataTables.min.js?t=es5i5"></script>
<script src="${base}/static/assets/js/dataTables/jquery.dataTables.bootstrap.min.js?t=es5i5"></script>
<script src="${base}/static/assets/js/dataTables/extensions/TableTools/js/dataTables.tableTools.min.js?t=es5i5"></script>
<script src="${base}/static/assets/js/dataTables/extensions/ColVis/js/dataTables.colVis.min.js?t=es5i5"></script>

<script>
    //验证码
    var changeYzmCode = function() {
        $("#randomImg").attr("src",   "${base}/randomImg?v=" + Math.random());
    }
    $(document).keyup(function(event) {
        //监听enter
        if (event.keyCode == 13) {
            login();
        }
    })

    if(top.location != self.location){
        window.top.location = "${base}/login";
    }
  var login= function(){
      var userId = $("#userId").val().trim();
      var pwd=$("#userPwd").val().trim();
      var userPwd = $.md5(pwd);
      var userCode = $("#userCode").val().trim();
      if(!userId){
          layer.alert("用户名不能为空!");
          return;
      }
      if(!pwd){
          layer.alert("密码不能为空!");
          return;
      }
      if(!userCode){
          layer.alert("验证码不能为空!");
          return;
      }
      $.ajax({
          url:"${base}/loginUp",
          data:{
              username:userId,
              password:userPwd,
              randImg:userCode
          },
          type:"POST",
          async:false,
          success:function(data){
              if(data.code=='SUCCESS'){
                  window.location.href="${base}/index"
              }else{
                  layer.alert(data.msg);
                  changeYzmCode();
              }
          }
      });
  }
    $(function (){

        $("#loginBtn").on('click', function (){
            login();

        });


    })
</script>
