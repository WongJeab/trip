
//全选反选
function checkAll() {
    var all=document.getElementById('brandquanxuanId');//获取到点击全选的那个复选框的id
    var one=document.getElementsByName('brandId');//获取到复选框的名称
    if(all.checked==true){//因为获得的是数组，所以要循环 为每一个checked赋值
        for(var i=0;i<one.length;i++){
            one[i].checked=true;
        }
    }else{
        for(var j=0;j<one.length;j++){
            one[j].checked=false;
        }
    }
}
function initCheckBoxStyle(){
    var itemCheckBox=$("input:checkbox[name='btSelectItem']");
    for(var i=0;i<itemCheckBox.length;i++){
        $(itemCheckBox[i]).addClass("ace");  // 每一个被选中项的值
    }
}

function initCheckAllStyle(){
    var th_inner = $('#tableObj').find("tr").eq(0).find(".th-inner").eq(0);
    var thead_1 = $('#tableObj').find("tr").eq(0).find("th").eq(0);
    $(thead_1).addClass("bs-checkbox ");  //选中所有
    var innerHtml = "<label class=\"pos-rel\">"+
        "<input name=\"btSelectAll\" type=\"checkbox\" class=\"ace\" onclick=\"checkAllItem();\"/>"+
        "<span class=\"lbl\"></span>"+
        "</label>";
    th_inner.html(innerHtml);
}

function checkAllItem(){
    var active_class = 'active';
    //表格全选
    var btSelectAll = $("input:checkbox[name='btSelectAll']").eq(0);
    var btSelectItem = $("input:checkbox[name='btSelectItem']");
    if(btSelectAll[0].checked){
        for(var i=0;i<btSelectItem.length;i++){
            var $row = $(this).closest('tr');
            $row.addClass(active_class);
            $(btSelectItem[i]).prop("checked",true);
        }
    }else{
        for(var i=0;i<btSelectItem.length;i++){
            $(btSelectItem[i]).prop("checked",false);
            var $row = $(this).closest('tr');
            $row.removeClass(active_class);
        }
    }
};

