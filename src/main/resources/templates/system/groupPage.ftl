
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>分组管理</title>

    <meta name="description" content="" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <style>
        table thead {
            background-color: rgb(110, 177, 164);
        }

        table thead th {
            background-color: rgb(110, 177, 164);
        }
    </style>

</head>
<#include "/head.ftl">

<body class="jui-linghtgreen-theme">
<div class="clearfix">
    <div class="jui-view-content" style="margin: 0">
        <div class="main-content-inner">
            <div class="jui-page">
                <!-- /.page-content -->
                <div class="page-content" transition>
                    <div class="jui-search-icon"></div>
                    <!-- /.page-form -->
                    <form class="form-horizontal jui-form-justify" role="form"
                          id="query-form">
                        <div class="clearfix jui-form-group">
                            <div class="col-sm-6">
                                <label class="pull-left control-label">组名称</label>
                                <div class="pull-left jui-form-group-content">
                                    <div class="pos-rel">
                                        <input type="text" id="searchId" name="name" class="min-input" />
                                        <a id="search" class="btn btn-lightblue">查询</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!-- /.page-form -->
                </div>
                <div class="page-content margin-top">
                    <div class="btn-group">
                   <@shiro.hasPermission name="omp:group:btnAdd">
                          <a id="channelAdd" class="btn btn-info">新增</a>
                   </@shiro.hasPermission>
                    <@shiro.hasPermission name="omp:group:btnEdit">
                        <a id="channelUpdate"   class="btn btn-warning">编辑</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="omp:group:btnDelete">
                    <a id="channelDel" class="btn btn-danger">删除</a>
                    </@shiro.hasPermission>
                        <span id="error" style="display: none; color: red; float: right;"></span>
                    </div>
                    <table class="jui-theme-table table-hover margin-top"
                           id="tableObj"></table>
                </div>
                <div class="modal fade" id="channelAddModal" tabindex="-1"
                     role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">新增分组</h4>
                            </div>
                            <div class="modal-body">
                                <form id="channelForm">
                                    <table>
                                        <tr>
                                            <td><label for="remark">上级:</label></td>
                                            <td colspan="3">
                                                <input name="pId" id="pId"  style="display: none">
                                                <input name="upName" id="upName" type="text" class="form-control" placeholder="上级">
                                                <ul id="tree" class="ztree"  style="width: 100%; height: 200px; overflow: auto; border: 2px solid rgb(97, 118, 239); display: none"></ul></td>
                                        </tr>
                                        <tr>
                                            <td><label for="name" class="emphasis">分组名称：</label></td>
                                            <td><input type="text" name="name" id="name" class="form-control" placeholder="分组名称"></td>
                                            <td>
                                        </tr>
                                        <tr>
                                            <td><label for="remark">备注：</label></td>
                                            <td colspan="3"><input type="text" name="remark" class="form-control" placeholder="备注"></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭</button>
                                <button type="button" id="channelAddBtn" class="btn btn-primary">保存</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="channelUpdateModal" tabindex="-1"
                     role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">编辑分组</h4>
                            </div>
                            <div class="modal-body">
                                <form id="channelForm1">
                                    <table>
                                        <tr>
                                            <td><label for="remark">上级:</label></td>
                                            <td colspan="3">
                                                <input name="id" id="id1"  style="display: none">
                                                <input name="pId" id="pId1"  style="display: none">
                                                <input name="upName" id="upName1" type="text" class="form-control" placeholder="上级">
                                                <ul id="tree1" class="ztree" style="width: 100%; height: 200px; overflow: auto; border: 2px solid rgb(97, 118, 239); display: none"></ul></td>
                                        </tr>
                                        <tr>
                                            <td><label for="name" class="emphasis">分组名称：</label></td>
                                            <td><input type="text" name="name" id="name1" class="form-control" placeholder="分组名称"></td>
                                            <td>
                                        </tr>
                                        <tr>
                                            <td><label for="remark">备注：</label></td>
                                            <td colspan="3"><input type="text" name="remark" id="remark1" class="form-control" placeholder="备注"></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="button" id="channelUpdateBtn" class="btn btn-primary">保存</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="channelDetailModal" tabindex="-1"
                     role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title">分组详情</h4>
                            </div>
                            <div class="modal-body">
                                <form id="channelForm2">
                                    <table>
                                        <tr>
                                            <td><label for="remark">上级:</label></td>
                                            <td colspan="3">
                                                <input name="pId" id="pId2"  style="display: none">
                                                <input name="upName" id="upName2" type="text" class="form-control" placeholder="上级">
                                                <ul id="tree2" class="ztree" style="width: 100%; height: 200px; overflow: auto; border: 2px solid rgb(97, 118, 239); display: none"></ul></td>
                                        </tr>
                                        <tr>
                                            <td><label for="name" class="emphasis">分组名称：</label></td>
                                            <td><input type="text" name="name" id="name2" class="form-control" placeholder="分组名称"></td>
                                            <td>
                                        </tr>
                                        <tr>
                                            <td><label for="remark">备注：</label></td>
                                            <td colspan="3"><input type="text" name="remark" id="remark2" class="form-control" placeholder="备注"></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<a href="#" id="btn-scroll-up"
   class="btn-scroll-up btn btn-sm btn-inverse"> <i
        class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='${base}/static/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
	<#include "/foot.ftl">
<script>
    $("#tableObj").bootstrapTable({ // 对应table标签的id
        url: "${base}/system/group/groupList", // 获取表格数据的url
        cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
        striped: true,  //表格显示条纹，默认为false
        pagination: true, // 在表格底部显示分页组件，默认false
        paginationLoop: true,
        pageList: [10, 15, 20, 25, 50, 100], // 设置页面可以显示的数据条数
        pageSize: 10, // 页面数据条数
        pageNumber: 1, // 首页页码
        search: false,  //是否显示搜索
        strictSearch: true,  //Enable the strict search.
        sidePagination: 'server', // 设置为服务器端分页
        queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
            return getQueryParams(params);  //该方法在static/goods/js/pageTable.js中
        },
        sortName: 'createTime', // 要排序的字段
        sortOrder: 'desc', // 排序规则
        //Indicate which field is an identity field.
        sortable:false,
        idField : "id",
        columns: [
            {
                align: 'center',// 居中显示
                formatter: function (value, row, index) {
                    var  checkBox = '<label class=\"pos-rel\">'+
                            '<input data-index=\"'+index+'\" name=\"btSelectItem\" value=\"'+row.id+'\" type=\"checkbox\" class=\"ace\" lang=\"'+row.status+'\" />'+
                            '<span class=\"lbl\"></span></label>'
                    return checkBox;
                }
            },{
                title: '序号',//标题  可不加
                formatter: function (value, row, index) {
                    return index+1;
                }
            },  {
                field: 'name',
                title: '分组名称',
                align: 'left',
                valign: 'middle',
                halign:'center',
                sortable:true
            },
            {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                valign: 'middle',
                sortable:true,
                formatter: function (value, row, index) {
                    return dateFormat(value);
                }
            },
            {
                field: 'remark',
                title: '备注',
                align: 'center',
                valign: 'middle'
            },
            {
                field: 'status',
                title: '状态',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    if('E'==value){
                        return '在用';
                    }else  if('D'==value){
                        return '停用';
                    }else{
                        return value;
                    }
                }
            },
            {
                field: 'status',
                title: '启用停用',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    if('E'==value){
                        var  detail = '<button  class=\"btn btn-primary\" onclick=\"changeStatus(\'D\','+row.id+')\">'+'停用</button>';
                        return  detail;
                    }else  if('D'==value){
                        var  detail = '<button  class=\"btn btn-primary\" onclick=\"changeStatus(\'E\','+row.id+')\">'+'启用</button>';
                        return  detail;
                    }
                }
            }
        ],
        onLoadSuccess: function(){  //加载成功时执行
            /*console.info("加载成功");*/
            initCheckBoxStyle();
            initCheckAllStyle();
        },
        onLoadError: function(){  //加载失败时执行
            console.info("加载数据失败");
        }
    });
    var permValid={
        rules: {
            name: {
                required: true,
                minlength: 2,
                maxlength:100
            }
        }, messages: {
            permName: {
                required: "请输入分组名称",
                minlength: "权限名称长度不能小于2",
                maxlength: "权限名称长度不能超过100"
            }
        },
        errorClass: "text-danger",
        errorElement: "span"
    };
    var $permInfoForm = $('#channelForm').validate(permValid);
    var $permInfoForm1 = $('#channelForm1').validate(permValid);
    var ztreeSetting = {
        check: {
            enable: false,
            chkStyle: "checkbox",
            chkboxType: { "Y": "ps", "N": "ps" }
        },
        data: {
            key : {
                //树结构显示的名称定义
                name : "name"
            },
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: ""
            }
        },
        callback: {
            onClick: zTreeOnClick
        }
    };
    //权限详情
    function channelDetail(id){
        var zNodes=[];
        $.ajax({
            "type":"GET",
            "url":"${base}/system/group/getGroup",
            "data":{id:id},
            "dataType":"json",
            async:false,
            "success":function(data){
                if(data.code==1){
                    var data1=data.obj;
                    if(!data1.channelObj){
                        bootbox.alert("权限信息加载失败!");
                        return;
                    }
                    $("#pId2").val(data1.channelObj.pId);
                    $("#upName2").val(data1.channelObj.upName);
                    $("#name2").val(data1.channelObj.name);
                    $("#remark2").val(data1.channelObj.remark);
                    if(!data1.channelList){
                        console.log("权限树加载失败!");
                        return;
                    }
                    zNodes=data1.channelList;

                    $("#channelDetailModal").modal();
                }else{
                    bootbox.alert(data.msg);
                }
            }
        });
        var setting =ztreeSetting;
        setting.callback={
            onClick:    zTreeOnClick1
        };
        $.fn.zTree.init($("#tree1"), setting,zNodes);//初始化树对象
        $.fn.zTree.getZTreeObj("tree1").expandAll(true);
    }
    //加载单个权限信息
    function loadPermData(id){
        var zNodes=[];
        $.ajax({
            "type":"GET",
            "url":"${base}/system/group/getGroup",
            "data":{id:id},
            "dataType":"json",
            async:false,
            "success":function(data){
                if(data.code==1){
                    var data1=data.obj;
                    if(!data1.channelObj){
                        bootbox.alert("权限信息加载失败!");
                        return;
                    }
                    $("#pId1").val(data1.channelObj.pid);
                    $("#upName1").val(data1.channelObj.upName);
                    $("#id1").val(data1.channelObj.id);
                    $("#name1").val(data1.channelObj.name);
                    $("#remark1").val(data1.channelObj.remark);

                    if(!data1.channelList){
                        console.log("权限树加载失败!");
                        return;
                    }
                    zNodes=data1.channelList;
                }
            }
        });

        var setting =ztreeSetting;
        setting.callback={
            onClick:    zTreeOnClick1
        };
        $.fn.zTree.init($("#tree1"), setting,zNodes);//初始化树对象
        $.fn.zTree.getZTreeObj("tree1").expandAll(true);

    }

    $(function(){
        $(".jui-search-icon").click(function(){
            $(this).parent().hasClass("jui-hide")?$(this).parent().removeClass("jui-hide"):$(this).parent().addClass("jui-hide");
        })
        /*查询*/
        $('#search').click(function () {
            //刷新table，bootstrap调用getQueryParams方法，所有查询条件通过该方法获取，getQueryParams方法在static/goods/js/pageTable.js中
            $('#tableObj').bootstrapTable('refreshOptions',{pageNumber:1});
        });

        //关闭模态框
        function cancelModel(obj){
            $('#'+obj).modal('hide');
        }
        //打开模态框
        function openModel(obj){
            $('#'+obj).modal('show');
        }
        //------新增权限-------//
        //添加弹出框
        $("#channelAdd").click(function(){
            $("#channelForm")[0].reset();
            initZtreeMenu();
            $("#channelAddModal").modal();

        })
        //添加权限
        $("#channelAddBtn").click(function(){
            if($permInfoForm.form()){
                $.ajax({
                    "type":"POST",
                    "url":"${base}/system/group/groupAdd",
                    "data":$("#channelForm").serialize(),
                    "dataType":"json",
                    "success":function(data){
                        if(data.code==1){
                            cancelModel('channelAddModal');
                            $('#tableObj').bootstrapTable('refreshOptions',{pageNumber:1});
                        }else{
                            bootbox.alert(data.msg);
                        }
                    }
                })
            }
        });

        $("#channelUpdate").click(function(){
            $("#channelForm1")[0].reset();
            var itemCheckBox=$("input:checkbox[name='btSelectItem']:checked");
            if(itemCheckBox.length==1){
                loadPermData(itemCheckBox[0].value);
               $("#channelUpdateModal").modal();
            }else if(itemCheckBox.length>1){
                bootbox.alert("不能同时编辑多调记录!");
            }else{
                bootbox.alert("请选中要编辑的记录!");
            }
        })

        $("#channelDel").click(function(){
            var itemCheckBox=$("input:checkbox[name='btSelectItem']:checked");
            if(itemCheckBox.length==1){
                bootbox.confirm("记录删除不可恢复，确定要删除该记录吗?",function(e){
                    if(e){
                        $.ajax({
                            "type":"DELETE",
                            "url":"${base}/system/group/deleteGroup?id="+itemCheckBox[0].value,
                            "dataType":"json",
                            "success":function(data){
                                if(data.code==1){
                                    $('#tableObj').bootstrapTable('refreshOptions',{pageNumber:1});
                                }else{
                                    bootbox.alert(data.msg);
                                }
                            }
                        })
                    }
                })
            }else if(itemCheckBox.length>1){
                bootbox.alert("不能同时删除多个记录!");
            }else{
                bootbox.alert("请选中要删除的记录!");
            }
        })


        //编辑权限
        $("#channelUpdateBtn").click(function(){
            if($permInfoForm1.form()){
                $.ajax({
                    "type":"PUT",
                    "url":"${base}/system/group/groupUpdate",
                    "data":$("#channelForm1").serialize(),
                    "dataType":"json",
                    "success":function(data){
                        if(data.code==1){
                            cancelModel('channelUpdateModal');
                            $('#tableObj').bootstrapTable('refresh');
                        }else{
                            bootbox.alert(data.msg);
                        }
                    }
                })
            }
        });
        //控制上级选择
        $("#upName").on('click',function(){
            var tree=$("#tree");
            if(tree.is(":hidden")){
                tree.show();
            }else{
                tree.hide();
            }
        })
        //控制上级选择
        $("#upName1").on('click',function(){
            var tree=$("#tree1");
            if(tree.is(":hidden")){
                tree.show();
            }else{
                tree.hide();
            }
        })
    })


   function changeStatus(status,id) {
       $.ajax({
           "type":"PUT",
           "url":"${base}/system/group/groupUpdate",
           "data":{'status':status,'id':id},
           "dataType":"json",
           "success":function(data){
               if(data.code==1){
                   $('#tableObj').bootstrapTable('refresh');
               }else{
                   bootbox.alert(data.msg);
               }
           }
       })
   }

    /*点击事件*/
    function zTreeOnClick(event, treeId, treeNode) {
        $("#pId").val(treeNode.id);
        $("#upName").val(treeNode.name);
        $("#tree").hide();
    };
    /*点击事件*/
    function zTreeOnClick1(event, treeId, treeNode) {
        $("#pId1").val(treeNode.id);
        $("#upName1").val(treeNode.name);
        $("#tree1").hide();
    };


    /* 初始化树菜单 */
    function initZtreeMenu(){
        var zNodes=[];
        $.ajax({
            "type":"GET",
            "url":"${base}/system/group/queryGroupListAll",
            "data":{},
            "dataType":"json",
            async:false,
            "success":function(data){
                if(data.code==1){
                    var data1=data.obj.groupList;
                    if(!data1){
                        console.log("权限树加载失败!");
                        return;
                    }
                    zNodes=data1;
                }
            }
        });
        var setting =ztreeSetting;
        setting.callback={
            onClick:    zTreeOnClick
        };
        $.fn.zTree.init($("#tree"), setting,zNodes);//初始化树对象
        $.fn.zTree.getZTreeObj("tree").expandAll(true);
    };


</script>
</body>

</html>
