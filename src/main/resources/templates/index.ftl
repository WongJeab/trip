
<!DOCTYPE html>
<html >
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title></title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
  <style>
	 .act{
	  background-color: #64d3d9;
	  }
	body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;overflow: auto;}

     .main-content{
          padding-bottom: 40px !important;
      }

  </style>

</head>
<#include "/head.ftl">

<body class="jui-black2-theme">
<!-- #section:basics/navbar.layout -->
<div id="navbar" class="navbar navbar-default">
    <script type="text/javascript">
        //try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>

    <div class="navbar-container clearfix no-padding" id="navbar-container">
        <div class="navbar-header pull-left">
            <!-- #section:basics/navbar.layout.brand -->
            <a href="#" class="navbar-brand">
                <img class="navbar-logo" src="${base}/static/assets/images/logo.png?t=es5i5">
                后台管理系统
            </a>
        </div>
        <div class="navbar-buttons navbar-header pull-left jui-navbar" role="navigation">
         <div class="navbarbox">
                <ul class="nav ace-nav" >

                </ul>
            </div>

        </div>

        <!-- theme-handover -->
        <!-- #section:basics/navbar.dropdown -->
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav user-nav">
                <!-- #section:basics/navbar.user_menu -->
                <li>
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="${base}/static/assets/avatars/avatar3.png?t=es5i5&t=erzyu" alt="Jason's Photo" />
                        <span class="user-info">
									<small>您好！</small>
                                      ${Session.user.nikeName!'用户'}
								</span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a id="userInfoPage" href="javascript:;"  lang="${base}/system/user/userInfoPage" >
                                <i class="ace-icon fa fa-cog"></i>
                                个人信息
                            </a>
                        </li>
                        <li>
                            <a  id="updatePwdPage" href="javascript:;" lang="${base}/system/user/updatePwdPage" >
                                <i class="ace-icon fa fa-user"></i>
                                修改密码
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="${base}/logOut">
                                <i class="ace-icon fa fa-power-off"></i>
                                退出
                            </a>
                        </li>
                    </ul>
                </li>

                <!-- /section:basics/navbar.user_menu -->
            </ul>
        </div>

        <!-- /section:basics/navbar.dropdown -->
    </div><!-- /.navbar-container -->
</div>

<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <!-- #section:basics/sidebar -->
    <div id="sidebar" class="sidebar  responsive">
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
        </script>
        <ul class="nav nav-list">
        <@shiro.hasPermission name="main:mainPage">
            <li class="">
                <a href="javascript:;" class="dropdown-toggle" lang="${base}/main">
                    <i class="menu-icon fa fa-tachometer"></i>
                    <span class="menu-text">首页</span>
                </a>
            </li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="system:root">
            <li class="">
                <a href="javascript:;"  id="zrt" class="dropdown-toggle">
                    <i class="menu-icon fa fa-desktop"></i>
                    <span class="menu-text">系统管理</span>
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <b class="arrow"></b>
                <ul class="submenu">
                    <@shiro.hasPermission name="system:user:userPage">
                        <li class="">
                            <a href="javascript:;"  lang="${base}/system/user/userPage" class="dropdown-toggle">
                                用户管理
                            </a>
                            <b class="arrow"></b>
                        </li>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="system:role:rolePage">
                        <li class="">
                            <a href="javascript:;"  lang="${base}/system/role/rolePage">角色管理</a>
                        </li>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="system:perm:permsPage">
                        <li class="">
                            <a href="javascript:;"  lang="${base}/system/perm/permsPage">权限管理</a>
                        </li>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="system:group:groupPage">
                        <li class="">
                            <a href="javascript:;"  lang="${base}/system/group/groupPage">分组管理</a>
                        </li>
                    </@shiro.hasPermission>
                </ul>
            </li>
        </@shiro.hasPermission>
        </ul><!-- /.nav-list -->
        <!--跳转-->


        <!-- #section:basics/sidebar.layout.minimize -->
        <div class="sidebar-toggle sidebar-collapse">
            <i class="ace-icon fa fa-arrow-circle-left" data-icon1="ace-icon fa fa-arrow-circle-left" data-icon2="ace-icon fa fa-arrow-circle-right"></i>
        </div>

        <!-- /section:basics/sidebar.layout.minimize -->
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
        </script>
    </div>

    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <div class="main-content-inner">

        <#--  <@system.hasRole name="admin">admin</@system.hasRole>
          <@system.hasRole name="test">test</@system.hasRole>-->
            <div class="iframes-nav-box">
                <ul class="iframes-nav" id="navTab" style="background-color: #cde7e8">
<#--

-->
                    <li class="active"> <a href="javascript:void(0);" lang="${base}/main"    >首页  <i  lang="iframe_999" class="fa fa-times-circle" ></i></a> </li>

                </ul>
                <div class="iframes-nav-list"> <i  id="closeAllIframe" class="fa fa-times"></i></div>
            </div>
            <div class="iframes-content" id="iframes-content">

                <div class="iframes-main" id="iframe_999">
                    <iframe src="${base}/main" width="100%" height="100%"  style="border: 0"></iframe>
                </div>

            </div>

        </div>
    </div>
    <!-- /.main-content -->
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->
<#include "/foot.ftl">
<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${base}/static/assets/js/jquery.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${base}/static/assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='${base}/static/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>




</body>

</html>

<script>

    function changeIframe(menu){
       var  lang=menu.lang;
       $("#sidebar  a").removeClass("act");
       $(menu).addClass("act");
       if(lang){
           var  obj= $("#navTab li a");
           var single= getSingleNum();
           if(obj && obj.length>0){
               var c=0;
               $.each(obj,function (index,a) {//是否有选项卡
                   if(a.lang==lang){//有tab  定位到当前选项卡
                       comArry.push(single);
                       $(a).parent().addClass("active").siblings().removeClass("active");
                       $("#iframes-content div").hide();
                       $("#"+$(a).children("i")[0].lang).show();
                       c++;
                       return ;
                   }
               })
               if(c==0){
                   if(single<0){
                       bootbox.alert("选项卡太多,请关闭多余选项卡重新操作!");
                       return;
                   }
                   $("#navTab li").removeClass("active")
                   $("#navTab").append('<li class="active"> <a href="javascript:void(0);"     lang="'+lang+'">'+$(menu).text()+'&nbsp;<i class="fa fa-times-circle" lang="iframe_'+single+'" ></i></a> </li>');
                   $("#iframes-content div").hide();
                   $("#iframes-content").append('<div class="iframes-main" style="height: 500px;"  id="iframe_'+single+'" >'+ ' <iframe   src="'+lang+'" width="100%" height="100%"  style="border: 0">'+'</iframe>'+' </div> ' );
               }
           }else{
               $("#iframes-content div").hide();
               $("#navTab").append('<li class="active"> <a href="javascript:void(0);"    lang="'+lang+'">'+$(menu).text()+'&nbsp;<i class="fa fa-times-circle"  lang="iframe_'+single+'" ></i></a> </li>');
               $("#iframes-content").append('<div class="iframes-main" style="height: 500px;"   id="iframe_'+single+'">'+ ' <iframe  src="'+lang+'" width="100%" height="100%" style="border: 0">'+'</iframe>'+' </div> ' );
           }
       }
   }

    $(function(){

       $("#closeAllIframe").click(function(){
           $("#navTab").empty();
           $("#iframes-content").empty();
           comArry=getArry(arryLen);
       });
        JuiTheme('jui-blue-theme');//固定样式
        $("#sidebar  a").click(function(){
            changeIframe(this);
        })

        //个人信息
        $("#userInfoPage").click(function(){
            changeIframe(this);

        })
        //修改密码
        $("#updatePwdPage").click(function(){
            changeIframe(this);

        })
        //显示儿子菜单
        //顶部导航滚动
        navscroll();

        //滚动调用
        var scolltable1 = setInterval(function(){
            var h=$("#TMDtable2 .table-scroll-tbody").height();
            if(h>300){
                $("#TMDtable2 .table-scroll-tbody").css("height","300px").panel({iWheelStep:32});
                clearInterval(scolltable1)
            }
        },300)
        var scolltable2 = setInterval(function(){
            var h=$("#TMDtable1 .table-scroll-tbody").height();
            if(h>300){
                $("#TMDtable1 .table-scroll-tbody").css("height","300px").panel({iWheelStep:32});
                clearInterval(scolltable2)
            }
        },300)
        var scolltable3 = setInterval(function(){
            var h=$("#TMDtable3 .table-scroll-tbody").height();
            if(h>300){
                $("#TMDtable3 .table-scroll-tbody").css("height","300px").panel({iWheelStep:32});
                clearInterval(scolltable3)
            }
        },300)

        //下拉框插件启用
        $( ".jui-btn-select" ).css('width','150px').selectmenu();
        //查询条件配置
        var queryHTML = '<tr>'+
                '<td><div type="contenteditable">默认</div></td>'+
                '<td><div type="contenteditable">WQB8888888888</div></td>'+
                '<td><div type="contenteditable">默认</div></td>'+
                '<td><div type="contenteditable">否</div></td>'+
                '<td><div type="contenteditable">暂无</div></td>'+
                '<td><div type="contenteditable">暂无</div></td>'+
                '<td><div class="sort-text" type="contenteditable">0</div></td>'+
                '<td>'+
                '<div class="btn-Group">'+
                '<a class="icon-btn query-edit"><i class="ace-icon fa fa-edit"></i></a>'+
                '<a class="icon-btn query-del"><i class="ace-icon fa fa-trash-o"></i></a>'+
                '</div>'+
                '</td>'+
                '</tr>';
        $("#TMDtable1").TMDtableBase({
            table:'.table-main',
            plus:".query-add",
            plusHTML:queryHTML,
            plusinit:function(that,self){
                $(self).find(".query-hint").hide();
            },
            edit:'.query-edit',
            editAdd:function(that,self){
                console.log('1')
                $(that).parents('tr').find("[type='contenteditable']").attr("contenteditable",false);
            },
            editRem:function(that,self){
                $(that).parents('tr').find("[type='contenteditable']").attr("contenteditable",true);
            },
            del:'.query-del',
            delFun:function(that,self){
                $(that).parents('tr').remove();
            },
            Other:[{
                class:'.query-sort',
                fun:function(that,self){
                    var arr = [];
                    var sortRes = '';
                    $(self).find("tbody tr").each(function(index){
                        if(!arr[$(this).find('.sort-text').text()]){
                            arr[$(this).find('.sort-text').text()] = [];
                        }
                        arr[$(this).find('.sort-text').text()].push($(this));
                    })
                    $(self).find("tbody").html('')
                    for(var i = 0; i<arr.length; i++){
                        if(arr[i]){
                            for(var l = 0; l< arr[i].length; l++){
                                $(self).find("tbody").append(arr[i][l]);
                            }
                        }
                    }
                }
            }]
        })
        //指标配置
        var normHTML = '<tr>'+
                '<td><div type="contenteditable">默认名称</div></td>'+
                '<td><div type="contenteditable">WQB8888888888</div></td>'+
                '<td><div type="contenteditable">SUM(DECODE(T.PAYMETHODID,1002,1.0))</div></td>'+
                '<td><div type="contenteditable"> </div></td>'+
                '<td><div class="sort-text" type="contenteditable">有</div></td>'+
                '<td>'+
                '<div class="btn-Group">'+
                '<a class="icon-btn query-edit"><i class="ace-icon fa fa-edit"></i></a>'+
                '<a class="icon-btn query-del"><i class="ace-icon fa fa-trash-o"></i></a>'+
                '</div>'+
                '</td>'+
                '</tr>';
        $("#TMDtable2").TMDtableBase({
            table:'.table-main',
            plus:".query-add",
            plusHTML:normHTML,
            plusinit:function(that,self){
                $(self).find(".query-hint").hide();
            },
            edit:'.query-edit',
            editAdd:function(that,self){
                $(that).parents('tr').find("[type='contenteditable']").attr("contenteditable",false);
            },
            editRem:function(that,self){
                $(that).parents('tr').find("[type='contenteditable']").attr("contenteditable",true);
            },
            del:'.query-del',
            delFun:function(that,self){
                $(that).parents('tr').remove();
            },
        })
        //维度配置
        var dimensionHTML = '<tr>'+
                '<td><div type="contenteditable">默认名称</div></td>'+
                '<td><div type="contenteditable">SUM(DECODE(T.PAYMETHODID,1002,1.0))</div></td>'+
                '<td><div type="contenteditable"> </div></td>'+
                '<td>'+
                '<div class="btn-Group">'+
                '<a class="icon-btn query-edit"><i class="ace-icon fa fa-edit"></i></a>'+
                '<a class="icon-btn query-del"><i class="ace-icon fa fa-trash-o"></i></a>'+
                '</div>'+
                '</td>'+
                '</tr>';
        $("#TMDtable3").TMDtableBase({
            table:'.table-main',
            plus:".query-add",
            plusHTML:dimensionHTML,
            plusinit:function(that,self){
                $(self).find(".query-hint").hide();
            },
            edit:'.query-edit',
            editAdd:function(that,self){
                console.log('1')
                $(that).parents('tr').find("[type='contenteditable']").attr("contenteditable",false);
            },
            editRem:function(that,self){
                $(that).parents('tr').find("[type='contenteditable']").attr("contenteditable",true);
            },
            del:'.query-del',
            delFun:function(that,self){
                $(that).parents('tr').remove();
            },
        })
        //行维度设置
        var handlebind = function(){$(".jui-choose-handle").TMDbind([{
            class:'.choose-original .jui-choose-item',
            event:'click',
            fun:function(that,self){
                $(that).prependTo($(self).find('.choose-true .jui-choose-row-main'));
            }},{
            class:'.jui-choose-item .jui-choose-close ',
            event:'click',
            fun:function(that,self){
                $(that).parents('.jui-choose-item').prependTo($(self).find('.choose-original .jui-choose-row-main'));
            }},{
            class:'.jui-choose-all',
            event:'click',
            fun:function(that,self){
                $(self).find('.choose-original .jui-choose-item').prependTo($(self).find('.choose-true .jui-choose-row-main'));
            }},{
            class:'.jui-choose-item .jui-choose-up',
            event:'click',
            fun:function(that,self){
                $(that).parents('.jui-choose-item').insertBefore($(that).parents('.jui-choose-item').prev())
            }},{
            class:'.jui-choose-item .jui-choose-down',
            event:'click',
            fun:function(that,self){
                $(that).parents('.jui-choose-item').insertAfter($(that).parents('.jui-choose-item').next())
            }}
        ])
        }
        handlebind();
        //其他设置
        var tabConHTML = $("#tabConts").children().eq(0).clone().removeClass("active").TMDhtml();
        $("#tabbable").TMDtabbable({
            tab:'<li class="tabs-item"><a>分组{i}</a><i class="ace-icon fa fa-close"></i></li>',//列表标题 请将内容转换为String {i}会自动转化为递增的数字起到区别的作用
            tabAppend:'tabs-item',//列表标题插入位置 插入添加元素的前面
            tabCon:tabConHTML,//列表内容 请将内容转换为String {i}会自动转化为递增的数字起到区别的作用
            tabConAppend:'.jui-tab-content',//列表内容插入位置 插入在同类名元素的末尾
            plus:'.jui-nav-add',
            createFun:function(i){//这是添加生成后执行的函数 i为递增的数字起到区别的作用
                handlebind();
            }
        })
        //选项卡操作
        $(document).delegate(".jui-nav-tabs .tabs-item","click",function(){
            var index = $(".jui-nav-tabs .tabs-item").index(this);
            var that = this;
            $(".jui-nav-tabs .tabs-item").removeClass("active");
            $(this).addClass("active");
            $(this).parent().siblings(".jui-tab-content").find(".tab-pane").removeClass("in active");
            $(this).parent().siblings(".jui-tab-content").find(".tab-pane").eq(index).addClass("active");
            setTimeout(function(){
                $(that).parent().siblings(".jui-tab-content").find(".tab-pane").eq(index).addClass("in");
            },30)

        })
        //标签删除标题页
        $(document).delegate(".tabbable .jui-nav-tabs .fa-close","click",function(){
            var index = $(this).parents(".tabbable").find("li").index($(this).parent());
            $(this).parents(".tabbable").find(".tab-pane").eq(index).remove();
            $(this).parent().remove();
        })

    })








</script>