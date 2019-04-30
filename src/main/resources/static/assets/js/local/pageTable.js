/**
 * 日期格式化
 */
function dateFormat(dateStr)
{
    var fmt = "yyyy-MM-dd hh:mm:ss";
    if(!dateStr){
        return "";
    }
    var date = new Date(dateStr);
    var o = {
        "M+" : date.getMonth()+1,                 //月份
        "d+" : date.getDate(),                    //日
        "h+" : date.getHours(),                   //小时
        "m+" : date.getMinutes(),                 //分
        "s+" : date.getSeconds(),                 //秒
        "q+" : Math.floor((date.getMonth()+3)/3), //季度
        "S"  : date.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
};


/**
 * 获取bootstrap查询参数
 */
function getQueryParams(params) {
    console.log("111111111");
    console.log(params.offset);
    console.log(params.limit);

    var param = {
        pageNum: (params.offset / params.limit) + 1,
        pageSize: params.limit, // 每页要显示的数据条数
        sort: params.sort, // 要排序的字段
        sortOrder: params.order // 排序规则（desc/asc）
    };
    //额外添加的参数
    //获取id=query-form节点下的所有值
    var otherParams={};
    $('#query-form').find('[name]').each(function () {
        var value = $(this).val();
        if (value != '') {
            otherParams[$(this).attr('name')] = value;
        }
    });
    param['otherParams']=otherParams;

    return param;

};

