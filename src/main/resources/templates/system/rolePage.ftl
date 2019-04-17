
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>角色管理</title>

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
                                <label class="pull-left control-label">角色名</label>
                                <div class="pull-left jui-form-group-content">
                                    <div class="pos-rel">
                                        <input type="text" id="searchId" name="roleName" class="min-input"/>
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
                        <a id="roleAdd" class="btn btn-info">新增角色</a>
                        <a  id="roleUpdate" class="btn btn-warning">编辑角色</a>
                        <a id="roleDel" class="btn btn-danger">删除角色</a>
                        <span id="error" style="display:none;color: red;float: right;"></span>
                    </div>
                    <table class="jui-theme-table table-hover margin-top" id="tableObj"></table>
                </div>

                <div class="modal fade" id="roleAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">新增角色</h4>
                            </div>
                            <div class="modal-body">
                                <form  id="roleForm">
                                    <div class="form-group">
                                        <label for="roleName">角色名</label>
                                        <input type="text" name="roleName" class="form-control"  placeholder="角色名">
                                    </div>
                                    <div class="form-group">
                                        <label for="role">角色标识</label>
                                        <input type="text" name="role" class="form-control"  placeholder="角色标识">
                                    </div>
                                    <div class="form-group">
                                        <label for="remark">备注</label>
                                        <input type="text" name="remark" class="form-control"  placeholder="备注">
                                    </div>
                                    <div class="form-group">
                                        <label for="rolePerms">权限设置</label>
                                        <ul id="tree" class="ztree" style="width:100%;height: 200px;border: 2px solid rgb(97, 118, 239); overflow:auto" ></ul>

                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                                <button type="button" id="roleAddBtn" class="btn btn-primary" >保存</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="roleUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">编辑角色</h4>
                            </div>
                            <div class="modal-body">
                                <form  id="roleForm1">
                                    <div class="form-group">
                                        <label for="roleName">角色名</label>
                                        <input name ="roleId" id="roleId" style="display: none"/>
                                        <input type="text" name="roleName" class="form-control"  id="roleName" placeholder="角色名">
                                    </div>
                                    <div class="form-group">
                                        <label for="role">角色标识</label>
                                        <input type="text" name="role" id="role" class="form-control"  placeholder="角色标识">
                                    </div>
                                    <div class="form-group">
                                        <label for="remark">备注</label>
                                        <input type="text" name="remark" id="remark" class="form-control"  placeholder="备注">
                                    </div>
                                    <div class="form-group">
                                        <label for="rolePerms">权限设置</label>
                                        <ul id="tree1" class="ztree" style="width:100%;height: 200px;border: 2px solid rgb(97, 118, 239); overflow:auto" ></ul>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                                <button type="button" id="roleUpdateBtn" class="btn btn-primary" >保存</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="roleDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" >角色详情</h4>
                            </div>
                            <div class="modal-body">
                                <form  id="roleForm1">
                                    <div class="form-group">
                                        <label for="roleName">角色名</label>
                                        <input name ="roleId" id="roleId1" style="display: none"/>
                                        <input type="text" name="roleName" class="form-control"  id="roleName1" placeholder="角色名">
                                    </div>
                                    <div class="form-group">
                                        <label for="role">角色标识</label>
                                        <input type="text"  id="role1" class="form-control"  placeholder="角色标识">
                                    </div>
                                    <div class="form-group">
                                        <label for="createTime1">创建时间</label>
                                        <input type="text"  id="createTime1" class="form-control"  placeholder="创建时间">
                                    </div>
                                    <div class="form-group">
                                        <label for="updateTime1">更新时间</label>
                                        <input type="text"  id="updateTime1" class="form-control"  placeholder="更新时间">
                                    </div>
                                    <div class="form-group">
                                        <label for="remark">备注</label>
                                        <input type="text" id="remark1" class="form-control"  placeholder="备注">
                                    </div>
                                    <div class="form-group">
                                        <label for="rolePerms">权限设置</label>
                                        <ul id="tree2" class="ztree" style="width:100%;height: 200px;border: 2px solid rgb(97, 118, 239); overflow:auto" ></ul>
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
        url: "${base}/system/role/roleList", // 获取表格数据的url
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
        idField : "roleId",
        columns: [
            {
                align: 'center',// 居中显示
                formatter: function (value, row, index) {
                    var  checkBox = '<label class=\"pos-rel\">'+
                            '<input data-index=\"'+index+'\" name=\"btSelectItem\" value=\"'+row.roleId+'\" type=\"checkbox\" class=\"ace\" lang=\"'+row.status+'\" />'+
                            '<span class=\"lbl\"></span></label>'
                    return checkBox;
                }
            },{
                title: '序号',//标题  可不加
                formatter: function (value, row, index) {
                    return index+1;
                }
            },  {
                field: 'roleName',
                title: '角色名',
                align: 'left',
                valign: 'middle',
                halign:'center',
                sortable:true
            }, {
                field: 'role',
                title: '角色标识',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                valign: 'middle',
                sortable:true,
                formatter: function (value, row, index) {
                    return dateFormat(value);
                }
            },{
                field: 'updateTime',
                title: '更新时间',
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
                    var  detail = '<button id=\"roleDetailBtn\" class=\"btn btn-primary\" onclick=\"roleDetail('+row.roleId+')\">'+'详情</button>';
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

    var roleValid={
        rules: {
            roleName: {
                required: true,
                minlength: 2,
                maxlength:20
            }, role: {
                required: true,
                minlength: 2,
                maxlength:40
            }
        }, messages: {
            roleName: {
                required: "请输入角色名称",
                minlength: "角色名称长度不能小于2",
                maxlength: "角色名称长度不能超过20"
            },role: {
                required: "请输入角色标识",
                minlength: "角色标识不能小于2",
                maxlength:"角色标识不能超过20"
            }
        },
        errorClass: "text-danger",
        errorElement: "span"
    }
    var $roleInfoForm = $('#roleForm').validate(roleValid);
    var $roleInfoForm1 = $('#roleForm1').validate(roleValid);
    var ztreeSetting={
        check: {
            enable: true,
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
            onClick: zTreeBeforeClick
        }
    };
    //勾选当前选中的节点
    function zTreeBeforeClick(){
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes = treeObj.getSelectedNodes();
        for (var i=0, l=nodes.length; i < l; i++) {
            treeObj.checkNode(nodes[i], true, true);
        }
    }
    function zTreeBeforeClick1(){
        var treeObj = $.fn.zTree.getZTreeObj("tree1");
        var nodes = treeObj.getSelectedNodes();
        for (var i=0, l=nodes.length; i < l; i++) {
            treeObj.checkNode(nodes[i], true, true);
        }
    }
    function zTreeBeforeClick2(){
        var treeObj = $.fn.zTree.getZTreeObj("tree2");
        var nodes = treeObj.getSelectedNodes();
        for (var i=0, l=nodes.length; i < l; i++) {
            treeObj.checkNode(nodes[i], true, true);
        }
    }
    /* 获取当前选中的checkbox id */
    function getSelectCheckId(obj){
        var menuIdArray=[];
        var treeObj = $.fn.zTree.getZTreeObj(obj);
        var nodes = treeObj.getCheckedNodes(true);
        if(nodes.length>0){
            $.each(nodes,function(index){
                menuIdArray.push(nodes[index].permId);
            });
        }
        return menuIdArray;
    }

    /* 初始化树菜单 */
    function initZtreeList(){
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
            onClick:    zTreeBeforeClick
        };

        $.fn.zTree.init($("#tree"), setting,zNodes);//初始化树对象
        $.fn.zTree.getZTreeObj("tree").expandAll(true);
    } ;
    //加载角色权限树
    function  loadPermsZtree(roleId){
        var zNodes=[];
        $.ajax({
            "type":"GET",
            "url":"${base}/system/role/getRolePerms",
            "data":{roleId:roleId},
            "dataType":"json",
            async:false,
            "success":function(data){
                if(data.code==1){
                    var data1=data.obj;
                    if(!data1){
                        console.log("权限树加载失败!");
                        return;
                    }
                    $("#roleName").val(data1.roleName);
                    $("#roleId").val(data1.roleId);
                    $("#role").val(data1.role);
                    $("#remark").val(data1.remark);
                    zNodes=data1.permsList;
                }
            }
        });
        var setting =ztreeSetting;
        setting.callback={
            onClick:    zTreeBeforeClick1
        };

        $.fn.zTree.init($("#tree1"), setting,zNodes);//初始化树对象
        $.fn.zTree.getZTreeObj("tree1").expandAll(true);
    }
    //角色详情
    function roleDetail(roleId){
        var zNodes=[];
        $.ajax({
            "type":"GET",
            "url":"${base}/system/role/getRolePerms",
            "data":{roleId:roleId},
            "dataType":"json",
            async:false,
            "success":function(data){
                if(data.code==1){
                    var data1=data.obj;
                    if(!data1){
                        console.log("权限树加载失败!");
                        return;
                    }
                    $("#roleName1").val(data1.roleName);
                    $("#roleId1").val(data1.roleId);
                    $("#createTime1").val(dateFormat(data1.createTime) );
                    $("#updateTime1").val(dateFormat(data1.updateTime) );
                    $("#role1").val(data1.role);
                    $("#remark1").val(data1.remark);
                    zNodes=data1.permsList;
                }
            }
        });
        var setting =ztreeSetting;
        setting.callback={
            onClick:    zTreeBeforeClick2
        };
        $.fn.zTree.init($("#tree2"), setting,zNodes);//初始化树对象
        $.fn.zTree.getZTreeObj("tree2").expandAll(true)
        $("#roleDetailModal").modal();
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
        //角色新增弹出框
        $("#roleAdd").click(function(){
            $("#roleForm")[0].reset();
            initZtreeList();
            $("#roleAddModal").modal();

        });
        //角色更新弹出框
        $("#roleUpdate").click(function(){
            $("#roleForm1")[0].reset();
            var itemCheckBox=$("input:checkbox[name='btSelectItem']:checked");
            if(itemCheckBox.length==1){
                loadPermsZtree(itemCheckBox[0].value);
                $("#roleUpdateModal").modal();

            }else{
                alert("请选中要编辑的角色!");

            }
        });
        //添加角色
        $("#roleAddBtn").click(function(){
            if($roleInfoForm.form()){
                   //角色选择的权限
                var perms=getSelectCheckId("tree");
                if(!perms || perms.length==0){
                    bootbox.alert("请设置角色权限!");
                    return;
                }
                console.log(perms);
                $.ajax({
                    "type":"POST",
                    "url":"${base}/system/role/roleAdd",
                    "data":$.param({permIds:perms})+'&'+$("#roleForm").serialize(),
                    "dataType":"json",
                    "success":function(data){
                        if(data.code==1){
                            cancelModel('roleAddModal');
                            $('#tableObj').bootstrapTable('refresh');
                        }else{
                            bootbox.alert(data.msg);
                        }
                    }
                })
            }
        });
        //更新角色
        $("#roleUpdateBtn").click(function(){
            if($roleInfoForm1.form()){
                //角色选择的权限
                var perms=getSelectCheckId("tree1");
                if(!perms || perms.length==0){
                    bootbox.alert("请设置角色权限!");
                    return;
                }
                $.ajax({
                    "type":"PUT",
                    "url":"${base}/system/role/roleUpdate",
                    "data":$.param({permIds:perms})+'&'+$("#roleForm1").serialize(),
                    "dataType":"json",
                    "success":function(data){
                        if(data.code==1){
                            cancelModel('roleUpdateModal');
                            $('#tableObj').bootstrapTable('refresh');
                        }else{
                            bootbox.alert(data.msg);
                        }
                    }
                })
            }
        });
        //删除角色
        $("#roleDel").click(function(){
            var itemCheckBox=$("input:checkbox[name='btSelectItem']:checked");
            if(itemCheckBox.length==1){
                bootbox.confirm("角色删除不可恢复，确定要删除该角色吗?",function(e){
                    if(e){
                        $.ajax({
                            "type":"DELETE",
                            "url":"${base}/system/role/deleteRole?roleId="+itemCheckBox[0].value,
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
                bootbox.alert("请选中要删除的角色!");
            }
        })
    })
</script>
</body>

</html>
