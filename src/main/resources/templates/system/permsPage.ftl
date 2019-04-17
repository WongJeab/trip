
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>权限管理</title>

    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>


    <style>
        table thead{
            background-color: rgb(110, 177, 164);
        }
        table thead th{
            background-color: rgb(110, 177, 164);
        }
    </style>

</head>
<#include "/head.ftl">

<body class="jui-linghtgreen-theme">


<div class="clearfix">
    <div class="jui-view-content" style="margin: 0 ">
        <div class="main-content-inner">
            <div class="jui-page">
                <!-- /.page-content -->
                <div class="page-content" transition>
                    <div class="jui-search-icon"></div>
                    <!-- /.page-form -->
                    <form class="form-horizontal jui-form-justify" role="form" id="query-form">
                        <div class="clearfix jui-form-group">
                            <div class="col-sm-6">
                                <label class="pull-left control-label">权限名或权限标识</label>
                                <div class="pull-left jui-form-group-content">
                                    <div class="pos-rel">
                                        <input type="text" id="searchId" name="permName" class="min-input"/>
                                        <a id = "search" class = "btn btn-lightblue">查询</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!-- /.page-form -->
                </div>
                <div class="page-content margin-top">
                    <div class="btn-group">
                        <a id="permAdd" class="btn btn-info">新增权限</a>
                        <a  id="permUpdate" class="btn btn-warning">编辑权限</a>
                        <a id="permDel" class="btn btn-danger">删除权限</a>
                        <span id="error" style="display:none;color: red;float: right;"></span>
                    </div>
                    <table class="jui-theme-table table-hover margin-top" id="tableObj"></table>
                </div>
                <div class="modal fade" id="permAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">新增权限</h4>
                            </div>
                            <div class="modal-body">
                                <form  id="permForm">
                                    <div class="form-group">
                                        <label for="remark">上级</label>
                                        <input name="upId" id="upId" class="form-control" style="display: none" >
                                        <input name="upName" id="upName" class="form-control" placeholder="上级">
                                        <ul id="tree" class="ztree" style="width:100%;height: 200px; overflow:auto; border: 2px solid rgb(97, 118, 239);display: none" ></ul>
                                    </div>
                                    <div class="form-group">
                                        <label for="permName">权限名称</label>
                                        <input type="text" name="permName" class="form-control"  placeholder="权限名称">
                                    </div>
                                    <div class="form-group">
                                        <label for="perm">权限标识</label>
                                        <input type="text" name="perm" class="form-control"  placeholder="权限标识">
                                    </div>
                                    <div class="form-group">
                                        <label for="uri">权限URL</label>
                                        <input type="text" name="uri" class="form-control"  placeholder="权限URL">
                                    </div>
                                    <div class="form-group">
                                        <label for="remark">权限类型</label>

                                        <label class="radio-inline" style="padding-left: 57px"><input type="radio" name="type" value="1" checked />菜单</label>

                                        <label class="radio-inline"><input type="radio" name="type" value="2"   />资源链接</label>

                                    </div>
                                    <div class="form-group">
                                        <label for="remark">备注</label>
                                        <input type="text" name="remark" class="form-control"  placeholder="备注">
                                    </div>


                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                                <button type="button" id="permAddBtn" class="btn btn-primary" >保存</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="permUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">编辑权限</h4>
                            </div>
                            <div class="modal-body">
                                <form  id="permForm1">
                                    <div class="form-group">
                                        <label for="remark">上级</label>
                                        <input name="upId" id="upId1" class="form-control" style="display: none" >
                                        <input name="permId" id="permId" class="form-control" style="display: none" >

                                        <input name="upName" id="upName1" class="form-control" placeholder="上级">
                                        <ul id="tree1" class="ztree" style="width:100%;height: 200px; overflow:auto; border: 2px solid rgb(97, 118, 239);display: none" ></ul>
                                    </div>
                                    <div class="form-group">
                                        <label for="permName">权限名称</label>
                                        <input type="text" name="permName" id="permName" class="form-control"  placeholder="权限名称">
                                    </div>
                                    <div class="form-group">
                                        <label for="perm">权限标识</label>
                                        <input type="text" name="perm" id="perm" class="form-control"  placeholder="权限标识">
                                    </div>
                                    <div class="form-group">
                                        <label for="uri">权限URL</label>
                                        <input type="text" name="uri"  id="uri" class="form-control"  placeholder="权限URL">
                                    </div>
                                    <div class="form-group">
                                        <label for="remark">权限类型</label>

                                        <label class="radio-inline" style="padding-left: 57px"><input type="radio" id="menuType" name="type" value="1"  />菜单</label>

                                        <label class="radio-inline"><input id="resType" type="radio" name="type" value="2"   />资源链接</label>

                                    </div>
                                    <div class="form-group">
                                        <label for="remark">备注</label>
                                        <input type="text" name="remark" id="remark" class="form-control"  placeholder="备注">
                                    </div>


                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                                <button type="button" id="permUpdateBtn" class="btn btn-primary" >保存</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="permDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" >权限详情</h4>
                            </div>
                            <div class="modal-body">
                                <form  id="roleForm2">
                                      <div class="form-group">
                                          <label for="remark">上级</label>
                                          <input name="upName" id="upName2" class="form-control" readonly placeholder="上级">
                                          <ul id="tree2" class="ztree" style="width:100%;height: 200px; overflow:auto; border: 2px solid rgb(97, 118, 239);display: none" ></ul>
                                      </div>
                                      <div class="form-group">
                                          <label for="permName">权限名称</label>
                                          <input type="text" name="permName" id="permName1" readonly class="form-control"  placeholder="权限名称">
                                      </div>
                                      <div class="form-group">
                                          <label for="perm">权限标识</label>
                                          <input type="text" name="perm" id="perm1" readonly class="form-control"  placeholder="权限标识">
                                      </div>
                                      <div class="form-group">
                                          <label for="uri">权限URL</label>
                                          <input type="text" name="uri"  id="uri1" readonly class="form-control"  placeholder="权限URL">
                                      </div>
                                      <div class="form-group">
                                          <label for="remark">权限类型</label>
                                          <input type="text" name="type"  id="type1" readonly class="form-control"  placeholder="权限类型">
                                      </div>

                                    <div class="form-group">
                                        <label for="createTime1">创建时间</label>
                                        <input type="text"  id="createTime1"  readonly class="form-control"  placeholder="创建时间">
                                    </div>
                                    <div class="form-group">
                                        <label for="updateTime1">更新时间</label>
                                        <input type="text"  id="updateTime1"  readonly class="form-control"  placeholder="更新时间">
                                    </div>
                                    <div class="form-group">
                                        <label for="remark">备注</label>
                                        <input type="text" id="remark1"  readonly class="form-control"  placeholder="备注">
                                    </div>

                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='${base}/static/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<#include "/foot.ftl">

<script>


    $("#tableObj").bootstrapTable({ // 对应table标签的id
        url: "${base}/system/perm/permList", // 获取表格数据的url
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
            console.log("33333333333");
            return getQueryParams(params);  //该方法在static/goods/js/pageTable.js中
        },
        sortName: 'createTime', // 要排序的字段
        sortOrder: 'desc', // 排序规则
        //Indicate which field is an identity field.
        sortable:false,
        idField : "permId",
        columns: [
            {
                align: 'center',// 居中显示
                formatter: function (value, row, index) {
                    var  checkBox = '<label class=\"pos-rel\">'+
                            '<input data-index=\"'+index+'\" name=\"btSelectItem\" value=\"'+row.permId+'\" type=\"checkbox\" class=\"ace\" lang=\"'+row.status+'\" />'+
                            '<span class=\"lbl\"></span></label>'
                    return checkBox;
                }
            },{
                title: '序号',//标题  可不加
                formatter: function (value, row, index) {
                    return index+1;
                }
            },  {
                field: 'permName',
                title: '权限名称',
                align: 'left',
                valign: 'middle',
                halign:'center',
                sortable:true
            }, {
                field: 'perm',
                title: '权限标识',
                align: 'center',
                valign: 'middle'
            },
            {
                field: 'uri',
                title: '权限URL',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'type',
                title: '权限类型',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    if('1'==value){
                        return '菜单';
                    }else  if('2'==value){
                        return '资源链接';
                    }else{
                        return value;
                    }
                }
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
            },{
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
                title: '操作',
                align: 'center',
                formatter: function (value, row, index) {
                    var  detail = '<button id=\"permDetailBtn\" class=\"btn btn-primary\" onclick=\"permDetail('+row.permId+')\">'+'详情</button>';
                    return  detail;

                }
            }
        ],
        onLoadSuccess: function(){  //加载成功时执行
            console.info("加载成功");
            initCheckBoxStyle();
            initCheckAllStyle();
        },
        onLoadError: function(){  //加载失败时执行
            console.info("加载数据失败");
        }
    });
    var permValid={
        rules: {
            permName: {
                required: true,
                minlength: 2,
                maxlength:40
            }, perm: {
                required: true,
                minlength: 2,
                maxlength:40
            }
        }, messages: {
            permName: {
                required: "请输入权限名称",
                minlength: "权限名称长度不能小于2",
                maxlength: "权限名称长度不能超过40"
            },perm: {
                required: "请输入权限标识",
                minlength: "权限标识不能小于2",
                maxlength:"权限标识不能超过40"
            }
        },
        errorClass: "text-danger",
        errorElement: "span"
    };
    var $permInfoForm = $('#permForm').validate(permValid);
    var $permInfoForm1 = $('#permForm1').validate(permValid);
    var ztreeSetting = {
        check: {
            enable: false,
            chkStyle: "checkbox",
            chkboxType: { "Y": "ps", "N": "ps" }
        },
        data: {
            key : {
                //树结构显示的名称定义
                name : "permName"
            },
            simpleData: {
                enable: true,
                idKey: "permId",
                pIdKey: "upId",
                rootPId: ""

            }
        },
        callback: {
            onClick: zTreeOnClick
        }
    };
    //权限详情
    function permDetail(permId){
        $.ajax({
            "type":"GET",
            "url":"${base}/system/perm/getPerm",
            "data":{permId:permId},
            "dataType":"json",
            async:false,
            "success":function(data){
                if(data.code==1){
                    var data1=data.obj;
                    if(!data1.permObj){
                        bootbox.alert("权限信息加载失败!");
                        return;
                    }
                    $("#permName1").val(data1.permObj.permName);
                    $("#upName2").val(data1.permObj.upName);
                    $("#perm1").val(data1.permObj.perm);
                    $("#uri1").val(data1.permObj.uri);
                    $("#createTime1").val( dateFormat(data1.permObj.createTime));
                    $("#updateTime1").val( dateFormat(data1.permObj.updateTime));
                    $("#remark1").val(data1.permObj.remark);
                    if(data1.permObj.type==1){
                        $("#type1").val("菜单");
                    }else{
                        $("#type1").val("资源链接");
                    }
                   $("#permDetailModal").modal();
                }else{
                    bootbox.alert(data.msg);
                }
            }
        });
    }
    //加载单个权限信息
    function loadPermData(permId){
        var zNodes=[];
        $.ajax({
            "type":"GET",
            "url":"${base}/system/perm/getPerm",
            "data":{permId:permId},
            "dataType":"json",
            async:false,
            "success":function(data){
                if(data.code==1){
                    var data1=data.obj;
                    if(!data1.permObj){
                        bootbox.alert("权限信息加载失败!");
                        return;
                    }
                    $("#permName").val(data1.permObj.permName);
                    $("#permId").val(data1.permObj.permId);

                    $("#upName1").val(data1.permObj.upName);
                    $("#perm").val(data1.permObj.perm);
                    $("#uri").val(data1.permObj.uri);
                    $("#remark").val(data1.permObj.remark);
                    if(data1.permObj.type==1){
                        console.log("==================1")
                        $("#menuType").attr("checked",true);
                    }else{
                        console.log("==================2")
                        $("#resType").attr("checked",true);
                    }
                    if(!data1.treeObj){
                        console.log("权限树加载失败!");
                        return;
                    }
                    zNodes=data1.treeObj;
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
            $('#tableObj').bootstrapTable('refresh');
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
        $("#permAdd").click(function(){
            $("#permForm")[0].reset();
            initZtreeMenu();
            $("#permAddModal").modal();

        })
        //添加权限
        $("#permAddBtn").click(function(){
            if($permInfoForm.form()){
                $.ajax({
                    "type":"POST",
                    "url":"${base}/system/perm/permAdd",
                    "data":$("#permForm").serialize(),
                    "dataType":"json",
                    "success":function(data){
                        if(data.code==1){
                            cancelModel('permAddModal');
                            $('#tableObj').bootstrapTable('refresh');
                        }else{
                            bootbox.alert(data.msg);
                        }
                    }
                })
            }
        });

        $("#permUpdate").click(function(){
            $("#permForm1")[0].reset();
            var itemCheckBox=$("input:checkbox[name='btSelectItem']:checked");
            if(itemCheckBox.length==1){
                loadPermData(itemCheckBox[0].value);
                $("#permUpdateModal").modal();

            }else{
                bootbox.alert("请选中要编辑的资源权限!");

            }
        })
        $("#permDel").click(function(){
            var itemCheckBox=$("input:checkbox[name='btSelectItem']:checked");
            if(itemCheckBox.length==1){
                bootbox.confirm("资源权限删除不可恢复，确定要删除该权限吗?",function(e){
                    if(e){
                        $.ajax({
                            "type":"DELETE",
                            "url":"${base}/system/perm/deletePerm?permId="+itemCheckBox[0].value,
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
                })
            }else{
                bootbox.alert("请选中要删除的权限!");
            }
        })


        //编辑权限
        $("#permUpdateBtn").click(function(){
            if($permInfoForm1.form()){
                $.ajax({
                    "type":"PUT",
                    "url":"${base}/system/perm/permUpdate",
                    "data":$("#permForm1").serialize(),
                    "dataType":"json",
                    "success":function(data){
                        if(data.code==1){
                            cancelModel('permUpdateModal');
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

    /*点击事件*/
    function zTreeOnClick(event, treeId, treeNode) {
        $("#upId").val(treeNode.permId);
        $("#upName").val(treeNode.permName);
        $("#tree").hide();
    };
    /*点击事件*/
    function zTreeOnClick1(event, treeId, treeNode) {
        $("#upId1").val(treeNode.permId);
        $("#upName1").val(treeNode.permName);
        $("#tree1").hide();
    };


    /* 初始化树菜单 */
    function initZtreeMenu(){
        var zNodes=[];
        $.ajax({
            "type":"GET",
            "url":"${base}/system/perm/getZtreePermList",
            "data":{},
            "dataType":"json",
            async:false,
            "success":function(data){
                if(data.code==1){
                    var data1=data.obj;
                    if(!data.obj){
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
    } ;

</script>
</body>

</html>
