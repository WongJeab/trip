
<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
<!-- ace settings handler -->
<script src="${base}/static/assets/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
<script src="${base}/static/assets/js/html5shiv.min.js?t=es5i5"></script>


<![endif]-->
<script type="text/javascript" src="${base}/static/assets/js/jquery-1.11.3.min.js"></script>
<#--<script type="text/javascript" src="${base}/static/assets/js/jquery.min.js?t=es5i5"></script>-->
<!--md5-->
<script src="${base}/static/assets/js/jquery.md5.js?t=es5i5"></script>

<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='${base}/static/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="${base}/static/assets/js/bootstrap.min.js"></script>

<!-- ace 原有JS压缩文件 -->
<script src="${base}/static/assets/js/ace-elements.min.js"></script>
<script src="${base}/static/assets/js/ace.js"></script>
<!-- cookie -->
<script src="${base}/static/assets/js/jquery.cookie.js"></script>
<!-- ace 添加JS压缩文件 -->
<script src="${base}/static/assets/js/addjs.js"></script>
<!-- 树形 -->
<script src="${base}/static/assets/ztree/js/jquery.ztree.all.js"></script>

<!-- 插件文件视情况选择 -->
<!-- 百度图标仅有柱状图 折线图 -->
<#--<script src="${base}/static/assets/js/echarts.min.js"></script>
<!-- 表格数据 &ndash;&gt;
<script src="${base}/static/assets/js/perfect-scrollbar-0.4.10.with-mousewheel.min.js"></script>
<script src="${base}/static/assets/js/flexigrid_ext.js"></script>
<!-- 城市数据 &ndash;&gt;
<script src="${base}/static/assets/js/citydata.js"></script>-->

<!-- 百度富文本编辑器 -->
<#--<script type="text/javascript" charset="utf-8" src="../ueditor/ueditor.config.js"></script>-->
<#--<script type="text/javascript" charset="utf-8" src="../ueditor/ueditor.all.min.js"></script>-->
<#--<script type="text/javascript" charset="utf-8" src="../ueditor/lang/zh-cn/zh-cn.js"></script>-->
<!-- 滚动条插件 -->
<script src="${base}/static/assets/js/ZUI.js"></script>
<!-- 下拉选择美化插件 -->
<link rel="stylesheet" href="${base}/static/assets/css/chosen.css"/>
<script type="text/javascript" src="${base}/static/assets/js/chosen.jquery.min.js"></script>
<!-- 左右表格操作插件 -->
<link rel="stylesheet" href="${base}/static/assets/css/bootstrap-duallistbox.css"/>
<script type="text/javascript" src="${base}/static/assets/js/jquery.bootstrap-duallistbox.min.js"></script>
<!-- jQuery UI插件 包含美化下拉 日期选择-->
<script type="text/javascript" src="${base}/static/assets/js/jquery-ui.min.js"></script>
<!-- 弹层插件 -->
<script type="text/javascript" src="${base}/static/assets/js/layer.js"></script>
<script type="text/javascript" src="${base}/static/assets/js/bootbox.min.js"></script>

<#--multiple-select-->
<script type="text/javascript" src="${base}/static/assets/js/multiple-select.js"></script>
<#--validate-->
<script type="text/javascript" src="${base}/static/assets/js/jquery.validate.min.js"></script>
<!-- 表格插件 -->
<script src="${base}/static/assets/js/dataTables/jquery.dataTables.min.js"></script>
<script src="${base}/static/assets/js/dataTables/jquery.dataTables.bootstrap.min.js"></script>
<script src="${base}/static/assets/js/dataTables/extensions/TableTools/js/dataTables.tableTools.min.js"></script>
<script src="${base}/static/assets/js/dataTables/extensions/ColVis/js/dataTables.colVis.min.js"></script>

<link href="${base}/static/assets/css/bootstrap-table.css" rel="stylesheet" type="text/css">
<script src="${base}/static/assets/js/bootstrap-table.js"></script>
<script src="${base}/static/assets/js/bootstrap-table-zh-CN.js"></script>
<!--时间戳 格式化:yyyy-MM-dd HH:mm:ss -->
<script src="${base}/static/assets/js/local/pageTable.js"></script>
<#--checkbok-->
<script src="${base}/static/assets/js/local/checkCommon.js"></script>
<!--加入下拉框模糊搜索功能插件-->
<script type="text/javascript" src="${base}/static/assets/js/bootstrap-select.js"></script>
<script type="text/javascript" src="${base}/static/assets/js/bootstrap-select.min.js"></script>
<#--<script type="text/javascript" src="${base}/static/assets/js/i18n/defaults-*.min.js"></script>-->

<!--加时间选择-->
<script type="text/javascript" src="${base}/static/assets/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${base}/static/assets/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${base}/static/assets/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<!-- 自定义插件 包括*select双向选择 *图片上传预览 *2级别选择-->
<script src="${base}/static/assets/js/tmd.js"></script>

<script>

    bootbox.setDefaults("locale","zh_CN");  //弹窗设置中文
    $(document).ajaxComplete(function(event, xhr, settings) {
    	//关闭弹窗
        //从http头信息取出 在filter定义的sessionstatus，判断是否是 timeout
        if(xhr.getResponseHeader("sessionstatus")=="timeout"){
            //从http头信息取出登录的url ＝ loginPath
            if(xhr.getResponseHeader("loginPath")){
                bootbox.alert("会话过期，请重新登陆!");
            }else{
                bootbox.alert("请求超时请重新登陆 !");
            }
            //打会到登录页面
            window.location.replace(xhr.getResponseHeader("loginPath"));
        }
    });

    
    
    
    


</script>
