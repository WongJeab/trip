
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>用户管理</title>
    <meta name="description" content="" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <style>
        body {
            font-family: '微软雅黑';
        }

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
                                <label class="pull-left control-label">用户名或手机号</label>
                                <div class="pull-left jui-form-group-content">
                                    <div class="pos-rel">
                                        <input type="text" id="searchId" name="userName"
                                               class="min-input" /> <a id="search"
                                                                       class="btn btn-lightblue">查询</a>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </form>
                    <!-- /.page-form -->
                </div>
                <div class="page-content margin-top">
                    <div class="btn-group">
                        <@shiro.hasPermission name="omp:user:btnAdd">
                        <a id="userAdd" class="btn btn-info">新增</a>
                        </@shiro.hasPermission>
                       <@shiro.hasPermission name="omp:user:btnEdit">
                        <a id="userUpdate" class="btn btn-warning">编辑</a>
                       </@shiro.hasPermission>
                        <@shiro.hasPermission name="omp:user:btnStartOrStop">
                        <a id="updateStatus"  class="btn btn-danger">启停</a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="omp:user:btnGroupSetting">
                        <a id="channelSetting"  class="btn btn-danger">分组设置</a>
                        </@shiro.hasPermission>
                        <span id="error" style="display: none; color: red; float: right;"></span>
                    </div>
                    <table class="jui-theme-table table-hover margin-top"
                           id="tableObj"></table>
                </div>

                <div class="modal fade" id="userAddModal" tabindex="-1"
                     role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">新增用户</h4>
                            </div>
                            <div class="modal-body">
                                <form id="userForm">
                                    <table>
                                        <tr>
                                            <td><label for="userName" class="emphasis">用户名：</label></td>
                                            <td><input type="text" name="userName"
                                                       class="form-control" placeholder="用户名"></td>
                                            <td><label for="nikeName" class="emphasis">昵称：</label></td>
                                            <td><input type="text" name="nikeName"
                                                       class="form-control" placeholder="昵称"></td>
                                        </tr>
                                        <tr>
                                            <td><label for="phone" class="emphasis">手机号：</label></td>
                                            <td><input type="text" name="phone"
                                                       class="form-control" placeholder="手机号"></td>
                                            <td><label for="email" class="emphasis">邮箱：</label></td>
                                            <td><input type="text" name="email"
                                                       class="form-control" placeholder="邮箱"></td>
                                        </tr>
                                        <tr>
                                            <td><label for="remark">备注：</label></td>
                                            <td><input type="text" name="remark"
                                                       class="form-control" placeholder="备注"></td>
                                            <td><label for="remark" class="emphasis">角色设置：</label></td>
                                            <td><select id="ms1" multiple="multiple"></select></td>
                                        </tr>

                                    </table>
                                    <input id="roleIds1" name="roleIds" style="display: none" />
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭</button>
                                <button type="button" id="userAddBtn" class="btn btn-primary">保存</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="userUpdateModal" tabindex="-1"
                     role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">编辑用户</h4>
                            </div>
                            <div class="modal-body">
                                <form id="userForm1">


                                    <table>
                                        <tr>
                                            <td><label for="userName" class="emphasis">用户名：</label></td>
                                            <td><input type="text" name="userName"
                                                       class="form-control" id="userName" placeholder="用户名">
                                            </td>
                                            <td><label for="nikeName" class="emphasis">昵称：</label></td>
                                            <td><input type="text" name="nikeName"
                                                       class="form-control" id="nikeName" placeholder="昵称"></td>
                                        </tr>
                                        <tr>
                                            <td><label for="phone" class="emphasis">手机号：</label></td>
                                            <td><input type="text" name="phone"
                                                       class="form-control" id="phone" placeholder="手机号">
                                            </td>
                                            <td><label for="txt_statu" class="emphasis">邮箱：</label>
                                            </td>
                                            <td><input type="text" name="email"
                                                       class="form-control" id="email" placeholder="邮箱"></td>

                                        </tr>
                                        <tr>
                                            <td><label for="txt_statu">备注：</label></td>
                                            <td><input type="text" name="remark"
                                                       class="form-control" id="remark" placeholder="备注">
                                            </td>
                                            <td><label for="remark" class="emphasis">角色设置：</label></td>
                                            <td><select id="ms2" multiple="multiple"></select></td>
                                        </tr>
                                    </table>
                                    <input type="text" name="userId" id="userId"
                                           style="display: none">

                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭</button>
                                <button type="button" id="userUpdateBtn"
                                        class="btn btn-primary">保存</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="userDetailModal" tabindex="-1"
                     role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title">用户详情</h4>
                            </div>
                            <div class="modal-body">
                                <form >
                                    <table>
                                        <tr>
                                            <td><label for="userName">用户名：</label></td>
                                            <td><input type="text" name="userName"
                                                       class="form-control" readonly id="userName1"
                                                       placeholder="用户名"></td>
                                            <td><label for="nikeName">昵称：</label></td>
                                            <td><input type="text" name="nikeName"
                                                       class="form-control" readonly id="nikeName1"
                                                       placeholder="昵称"></td>
                                        </tr>

                                        <tr>
                                            <td><label for="phone">手机号：</label></td>
                                            <td><input type="text" name="phone" class="form-control"
                                                       readonly id="phone1" placeholder="手机号"></td>
                                            <td><label for="txt_statu">邮箱：</label></td>
                                            <td><input type="text" name="email" class="form-control"
                                                       id="email1" readonly placeholder="邮箱"></td>
                                        </tr>
                                        <tr>
                                            <td><label for="txt_statu">创建时间：</label></td>
                                            <td><input type="text" name="createTime"
                                                       class="form-control" id="createTime1" readonly
                                                       placeholder="创建时间"></td>
                                            <td><label for="txt_statu">更新时间：</label></td>
                                            <td><input type="text" name="updateTime1"
                                                       class="form-control" id="updateTime1" readonly
                                                       placeholder="更新时间"></td>
                                        </tr>
                                        <tr>
                                            <td><label for="txt_statu">最近登录时间：</label></td>
                                            <td><input type="text" name="lastLoginTime"
                                                       class="form-control" id="lastLoginTime" readonly
                                                       placeholder="最近登录时间"></td>
                                            <td><label for="txt_statu">备注：</label></td>
                                            <td><input type="text" name="remark"
                                                       class="form-control" id="remark1" readonly placeholder="备注"></td>
                                        </tr>
                                        <tr>
                                            <td><label for="txt_statu">用户角色：</label></td>
                                            <td><input type="text" name="userRole"
                                                       class="form-control" id="userRole" readonly
                                                       placeholder="用户角色">
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
                <!--渠道设置-->
                <div class="modal fade" id="channelSettingModal" tabindex="-1"
                     role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title">渠道设置</h4>
                            </div>
                            <div class="modal-body">
                                <form id="setForm">
                                    <table>
                                        <tr>
                                            <td><label for="channel">渠道：</label></td>
                                            <td>
                                                <input name="pId" id="pId"  style="display: none">
                                                <input name="upChannel" id="upChannel" type="text" class="form-control" placeholder="渠道">
                                                <ul id="tree" class="ztree" style="width: 100%; height: 200px; overflow: auto; border: 2px solid rgb(97, 118, 239); display: none"></ul></td>
                                        </tr>
                                        <tr>
                                            <td><label for="userNames">用户名：</label></td>
                                            <td colspan="">
                                                <input type="text" name="userIds" class="form-control" id="userIds" style="display: none">
                                                <input type="text" name="userNames" class="form-control" readonly id="userNames" placeholder="用户名">
                                            </td>

                                        </tr>
                                    </table>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭</button>
                                <button type="button" id="setSaveBtn"
                                        class="btn btn-primary">设置</button>
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
        url: "${base}/system/user/userList", // 获取表格数据的url
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
        idField : "userId",
        columns: [
            {
                align: 'center',// 居中显示
                formatter: function (value, row, index) {
                    var  checkBox = '<label class=\"pos-rel\">'+
                            '<input data-index=\"'+index+'\" name=\"btSelectItem\" value=\"'+row.userId+'\" type=\"checkbox\" class=\"ace\" lang=\"'+row.status+'\" />'+
                            '<span class=\"lbl\"></span></label>'
                    return checkBox;
                }
            },{
                title: '序号',//标题  可不加
                formatter: function (value, row, index) {
                    return index+1;
                }
            },  {
                field: 'userName',
                title: '用户名',
                align: 'left',
                valign: 'middle',
                halign:'center',
                sortable:true
            }, {
                field: 'nikeName',
                title: '昵称',
                align: 'center',
                valign: 'middle'
            },{
                field: 'groupName',
                title: '分组',
                align: 'left',
                valign: 'middle',
                halign:'center'
            },  {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                valign: 'middle',
                sortable:true,
                formatter: function (value, row, index) {
                    return dateFormat(value);
                }
            },{
                field: 'phone',
                title: '手机号码',
                align: 'center',
                valign: 'middle',
                sortable:true
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
            }/*,
            {
                title: '操作',
                align: 'center',
                formatter: function (value, row, index) {
                    var  detail = '<button id=\"userDetailBtn\" class=\"btn btn-primary\" onclick=\"userDetail('+row.userId+')\">'+'详情</button>';
                    return  detail;

                }
            }*/
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


    /* 手机号码校检 */
    $.validator.addMethod("mobile", function (value, element) {
        var tel = new RegExp("^[1][3-9]+\\d{9}$");
        return tel.test(value) || this.optional(element);
    });
    /* 表单验证规则 */
    var  userValidate={
        rules: {
            userName: {
                required: true,
                minlength: 4,
                maxlength:20
            }, nikeName: {
                required: true,
                minlength: 2,
                maxlength:20
            },phone:{
                required: true,
                mobile: true,
            },
            email:{
                required: true,
                email: true,
            },
        }, messages: {
            userName: {
                required: "请输入用户名",
                minlength: "用户名长度不能小于4",
                maxlength: "用户名长度不能超过20"
            },nikeName: {
                required: "请输入昵称",
                minlength: "昵称长度不能小于2",
                maxlength:"昵称长度不能超过20"
            },
            phone:{
                required: "请输入联系电话",
                mobile: "联系电话格式非法",
            },
            email:{
                required: "请输入电子邮箱",
                email: "电子邮箱非法",
            }
        },
        errorClass: "text-danger",
        errorElement: "span"
    };
    //添加用户表单验证
    var $userInfoForm = $('#userForm').validate(userValidate);
    //编辑用户表单验证
    var $userInfoForm1 = $('#userForm1').validate(userValidate);
    //关闭模态框
    function cancelModel(obj){
        $('#'+obj).modal('hide');
    }
    //打开模态框
    function openModel(obj){
        $('#'+obj).modal('show');
    }
    /*  初始化select数据 */
    var  initMultipleSelect=function(obj){
        $.ajax({
            "type":"GET",
            "url":"${base}/system/role/getAllRoles",
            "data":{},
            "dataType":"json",
            async:false,
            "success":function(data){
                if(data.code==1){
                    var html='';
                    var l=data.obj.length;
                    if(l>0){
                        $.each(data.obj,function(index,obj){
                            html+=  "<option value='"+obj.roleId+"'>"+obj.roleName+"</option>";
                        })
                        $("#"+obj).empty();
                        $("#"+obj).append(html);
                        $("#"+obj).multipleSelect({
                            placeholder:'请选择角色',
                            filter: true,
                            selectAll:true,
                            width: '100%',
                            selectAllText:'全选',
                            position:'bottom',
                            allSelected: '已全选',
                            countSelected: '# of % selected',
                            noMatchesFound: '未找到匹配的数据',
                            minimumCountSelected: l,
                        });
                    }
                }
            }
        })
    }

    /* 已有角色初始化*/
    function loadRole(userId){
        initMultipleSelect("ms2");
        $.ajax({
            "type":"GET",
            "url":"${base}/system/user/getUserRoles",
            "data":{userId:userId},
            "dataType":"json",
            async:false,
            "success":function(data){
                if(data.code==1){
                    if(!data.obj){
                        bootbox.alert("加载用户信息失败!");
                        return;
                    }
                    $("#userName").val(data.obj.userName)  ;
                    $("#userId").val(data.obj.userId)  ;
                    $("#nikeName").val(data.obj.nikeName)  ;
                    $("#phone").val(data.obj.phone)  ;
                    $("#email").val(data.obj.email)  ;
                    $("#remark").val(data.obj.remark)  ;
                    var valArr = [];
                    var l=data.obj.roleList.length;
                    if(l>0){
                        $.each(data.obj.roleList,function(index,obj){
                            valArr.push(obj.roleId);
                        });
                    }
                    $("#ms2").val(valArr).multipleSelect('refresh');
                    $("#userUpdateModal").modal();
                }else{
                    bootbox.alert(data.msg);
                }

            }
        })
    }
    //用户详情页
    function userDetail(userId){
        $.ajax({
            "type":"GET",
            "url":"${base}/system/user/getUserRoles",
            "data":{userId:userId},
            "dataType":"json",
            async:false,
            "success":function(data){
                if(data.code==1){
                    if(!data.obj){
                        bootbox.alert("加载用户信息失败!");
                        return;
                    }
                    $("#userName1").val(data.obj.userName)  ;
                    $("#nikeName1").val(data.obj.nikeName)  ;
                    $("#phone1").val(data.obj.phone)  ;
                    $("#email1").val(data.obj.email)  ;
                    $("#remark1").val(data.obj.remark)  ;
                    $("#createTime1").val(dateFormat(data.obj.createTime))  ;
                    $("#updateTime1").val(dateFormat(data.obj.updateTime))  ;
                    $("#lastLoginTime").val(dateFormat(data.obj.lastLoginTime))  ;
                    var l=data.obj.roleList.length;
                    var roleNames="";
                    if(l>0){
                        $.each(data.obj.roleList,function(index,obj){
                            roleNames+=obj.roleName+",";
                        });
                    }
                    $("#userRole").val((roleNames.substring(roleNames.length-1)==',')?roleNames.substring(0,roleNames.length-1):roleNames);
                    $("#userDetailModal").modal();
                }else{
                    bootbox.alert(data.msg);
                }
            }
        })
    }
    //搜索框隐藏显示
    $(function () {
        $(".jui-search-icon").click(function () {
            $(this).parent().hasClass("jui-hide") ? $(this).parent().removeClass("jui-hide") : $(this).parent().addClass("jui-hide");
        })
        /*查询*/
        $('#search').click(function () {
            //刷新table，bootstrap调用getQueryParams方法，所有查询条件通过该方法获取，getQueryParams方法在static/goods/js/pageTable.js中
            $('#tableObj').bootstrapTable('refreshOptions', {pageNumber: 1});
        });
        //用户新增弹出框
        $("#userAdd").click(function () {
            $("#userForm")[0].reset();
            initMultipleSelect("ms1");
            $("#userAddModal").modal();
        });
        //用户编辑弹出框
        $("#userUpdate").click(function () {
            $("#userForm1")[0].reset();
            var itemCheckBox = $("input:checkbox[name='btSelectItem']:checked");
            if (itemCheckBox.length == 1) {
                loadRole(itemCheckBox[0].value);
            } else if (itemCheckBox.length > 1) {
                bootbox.alert("不能同时编辑多个用户!");
            } else {
                bootbox.alert("请选中要编辑的用户!");
            }
        });
        //保存用户
        $("#userAddBtn").click(function () {
            if ($userInfoForm.form()) {
                var roles = $("#ms1").val();
                if (!roles || roles.length == 0) {
                    bootbox.alert("请设置角色!");
                    return;
                }
                $.ajax({
                    "type": "POST",
                    "url": "${base}/system/user/userAdd",
                    "data": $.param({roleIds: roles}) + '&' + $("#userForm").serialize(),
                    "dataType": "json",
                    "success": function (data) {
                        if (data.code == 1) {
                            cancelModel('userAddModal');
                            $('#tableObj').bootstrapTable('refreshOptions', {pageNumber: 1});
                        }
                        else {
                            bootbox.alert(data.msg);
                        }
                    }
                })
            }
        });
        //保存用户
        $("#userUpdateBtn").click(function () {
            if ($userInfoForm1.form()) {
                var roles = $("#ms2").val();
                if (!roles || roles.length == 0) {
                    bootbox.alert("请设置角色!");
                    return;
                }
                $.ajax({
                    "type": "PUT",
                    "url": "${base}/system/user/userInfoUpdate",
                    "data": $.param({roleIds: roles}) + '&' + $("#userForm1").serialize(),
                    "dataType": "json",
                    "success": function (data) {
                        if (data.code == 1) {
                            cancelModel('userUpdateModal');
                            $('#tableObj').bootstrapTable('refresh');
                        } else {
                            bootbox.alert(data.msg);
                        }
                    }
                })
            }
        });
        //编辑用户
        $("#updateStatus").click(function () {
            var itemCheckBox = $("input:checkbox[name='btSelectItem']:checked");
            if (itemCheckBox.length == 1) {
                bootbox.confirm("确定要修改用户状态吗?", function (e) {
                    if (e) {
                        $.ajax({
                            "type": "PUT",
                            "url": "${base}/system/user/userStatusUpdate",
                            "data": {userId: itemCheckBox[0].value, status: (itemCheckBox[0].lang == "E" ? "D" : "E")},
                            "dataType": "json",
                            "success": function (data) {
                                if (data.code == 1) {
                                    $('#tableObj').bootstrapTable('refresh');
                                } else {
                                    bootbox.alert(data.msg);
                                }
                            }
                        })
                    }
                })
            } else if (itemCheckBox.length > 1) {
                bootbox.alert("不能同时修改多个用户状态!");
            } else {
                bootbox.alert("请选中要修改的用户状态!");
            }
        })


        //用户编辑弹出框
        $("#channelSetting").click(function () {
            $("#setForm")[0].reset();
            var itemCheckBox = $("input:checkbox[name='btSelectItem']:checked");
            if (itemCheckBox.length >= 1) {
                $("#channelSettingModal").modal();
                queryUserByIds();
                initZtreeMenu();
            } else {
                bootbox.alert("请选中要设置的用户!");
            }
        });

        //保存设置
        $("#setSaveBtn").click(function () {
            var itemCheckBox = $("input:checkbox[name='btSelectItem']:checked");
            if (itemCheckBox.length < 1) {
                return;
            }
            var ids = getIds().toString();
            var channelId = $("#pId").val();
            $.ajax({
                "type": "POST",
                "url": "${base}/system/user/updateUserGroup",
                "data": {"ids": ids, "channelId":channelId},
                "dataType": "json",
                "success": function (data) {
                    if (data.code == 1) {
                        cancelModel('channelSettingModal');
                        $('#tableObj').bootstrapTable('refreshOptions', {pageNumber: 1});
                    } else {
                        bootbox.alert(data.msg);
                    }
                }
            })
        });

        //控制上级选择
        $("#upChannel").on('click',function(){
            var tree=$("#tree");
            if(tree.is(":hidden")){
                tree.show();
            }else{
                tree.hide();
            }
        })

    });

    function queryUserByIds() {
        var itemCheckBox=$("input:checkbox[name='btSelectItem']:checked");
        if(itemCheckBox.length<1){
            return;
        }
        var ids =  getIds().toString();
        $.ajax({
            "type":"GET",
            "url":"${base}/system/user/queryUserByIds",
            "data":{"ids":ids},
            "dataType":"json",
            "success":function(data){
                if(data.code==1){
                    //var ids = data.obj.userIds;
                    var ids =  getIds().toString();
                    var names = data.obj;
                    $("#userIds").val(ids);
                    $("#userNames").val(names);
                }else{
                    bootbox.alert(data.msg);
                }
            }
        })
    }

    function getIds(){
        var itemCheckBox=$("input:checkbox[name='btSelectItem']:checked");
        if(itemCheckBox.length<1){
            return;
        }
        var idsArray = new Array();
        for(var i=0;i<itemCheckBox.length;i++){
            var id = itemCheckBox[i].value;
            idsArray.push(id);
        }
        return idsArray;
    }

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

    /*点击事件*/
    function zTreeOnClick(event, treeId, treeNode) {
        $("#pId").val(treeNode.id);
        $("#upChannel").val(treeNode.name);
        $("#tree").hide();
    };

    /* 初始化树菜单 */
    function initZtreeMenu(){
        var zNodes=[];
        $.ajax({
            "type":"GET",
            "url":"${base}/system/platformChannel/queryChannelListAll",
            "data":{},
            "dataType":"json",
            async:false,
            "success":function(data){
                if(data.code==1){
                    var data1=data.obj.channelList;
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
