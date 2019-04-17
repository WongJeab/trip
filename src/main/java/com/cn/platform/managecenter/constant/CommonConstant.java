package com.cn.platform.managecenter.constant;

/**
 * 字段类型枚举
 * @author tamg
 *
 */

public class CommonConstant {
	public static final Integer ZERO=0;

	public static final String ZERO_STRING="0";

	public static final String ONE_STRING="1";
	//控制中心数据类型
	public static final String CONTROL_DATA_TYPE="2";
	//监控中心数据类型
	public static final String MONITOR_DATA_TYPE="2";

	//验证码
	public static final String YZM_CODE = "yzmCode";
	//用户session
	public static final String USER_KEY = "user";

	public static final String PASSWORD_ERROR= "登录密码错误!";

	public static final String ACCOUNT_LOCKED= "帐号已被锁定";

	public static final String ACCOUNT_NOT_EXISITS= "帐号不存在";

	public static final String ACCOUNT_HAS_DISABLED= "帐号已停用";


	public static final String AUTH_NOT_ENOUGH= "访问权限不足!";

	public static final String LOGIN_SUCCESS= "登录成功!";

	public static final String OLD_PASSWORD_ERROR= "旧密码输入有误!";



	public static final String YZM_CODE_NULL = "验证码为空";

	public static final String YZM_CODE_ERROR = "验证码错误";

	public static final String PARMS_NOT_ENOUGH="参数不完整!";

	public static final String USERNAME_OR_PHONE_EXISITS="用户或手机号已存在!";

	public static final String SYS_ERROR = "系统繁忙,请稍后再试!";

	public static final String  DEFAULT_PWD="123456";

	public static final String   STATUS_E="E";

	public static final String   STATUS_D="D";

	public static final String   STATUS_L="L";

	public static final String   NETWORK_ERROR="NE";

	public static final String   HOST_USER_ERROR="HE";

	public static final String  DELETE_FAIL="数据删除失败!";







	public static final String   ROLE_IS_EXISITS="角色标识已经存在!";

	public static final String   PERM_IS_EXISITS="权限标识已经存在!";

	public static final String   TASKNAME_IS_EXISITS="任务名称已存在!";

	public static final String   APP_TYPE_WAR="war";//war类型

	public static final String   APP_TYPE_JAR="jar";//jar 类型

	public static final String   TYPE_BACK="4";//回退


	public static final String   TYPE_UPGRADE="1";//升级

	public static final String   TYPE_REBOOT="2";//重启

	public static final String   TYPE_COLOSE="3";//关闭

	public static final String   TYPE_LOOK="5";//查看日志

	public static final String   SERV_TYPE_FRONT="C";//前端服务

	public static final String   SERV_TYPE_BACK="S";//后端服务



	public static final String   HOST_AND_PORT_IS_EXISITS="资源已存在,请勿重复添加!";

	public static final String   UPSTREAM_SERVER_UP="up";//上线

	public static final String   UPSTREAM_SERVER_DOWN="down";//下线

	public static final String START_STATUS_SUCCESS="1";//启动成功

	public static final String START_STATUS_STOP="2";//已关闭

	public static final String START_STATUS_ING="-1";//启动中

	public static final String START_STATUS_FAIL="-2";//启动失败

	public static final String SUFFIX_BAK="_bak";





	public static final String   QRY_ALL="_all";




	public static final String AT_TIMESTAMP="@timestamp";

	public static final String TIMESTAMP="timestamp";
	public static final String USED_TIME="usedTime";

	public static final String AT="@";

	public static final String DEFAULT_INDEX = "log-intf-app-";
	public static final String BUSINESS_INDEX = "log-business-link-log-";
    public static final String SPLIT_DOU_HAO = "[,]";
	public static final String SPLIT_FEN_HAO = "[;]";
    public static final String SPLIT_DIAN = "[.]";
    public static final String PHONE_CODE = "phoneCode_";
	public static final String SMS_CODE_TEMPLATE="100010580001";
	public static final String ACCOUNT_NOT_PHONE = "当前账号未配置手机号";
	public static final String TOTAL_CNT = "totalCnt";
	public static final String INTF_LIST = "intfMoniorEntityList";
	public static final String BUSSINESS_ERROR_MSG = "serviceErrorMsg";
	public static final String CALL_ERROR_MSG = "callErrorMsg";
    public static final String _ID ="_id";
    public static final String PDF =".pdf" ;
    public static final String DOC_TYPES_KEY ="DOC_TYPES_KEY" ;

    public static String DEFAULT_TYPE="doc";


	public static String DEFAULT_CHARSET="UTF-8";
	public static String HTTP_METHOD_GET="GET";
	public static String HTTP_METHOD_POST="POST";


	public static String ACCURATE="accurate";

	public static String INDISTINCT="indistinct";

	/*success fail*/
	public static String DEFAULT_EN_SUCCESS="SUCCESS";
	public static String DEFAULT_CN_SUCCESS="成功";
	public static String DEFAULT_EN_FAIL="FAIL";
	public static String DEFAULT_CN_FAIL="失败";


	/*主机地址拼接*/
	public static String HTTPS_HEAD="https://";
	public static String HTTP_HEAD="http://";
	public static String HTTP_COLON=":";
	public static String HTTP_GETMONITORINFO="getMonitorInfo";
	public static String HTTPS_SLASH="/";


	/*监控名称*/
	public static final String MONITOR_CN_HOST="主机监控";
	public static final String MONITOR_CN_APP="应用监控";
	public static final String MONITOR_CN_TELNET="网络监控";
	public static final String MONITOR_CN_INTERFACE="接口监控";

	public static final String AUTH_VALIDATE="权限验证";

	public static final String MONITOR_CN_BUSINESS="业务监控";
	public static final String MONITOR_CN_ERROR = "异常";
	public static final String MONITOR_CN_HOST_CPU="%idle";



	/*监控名称*/
	public static double MONITOR_PER_EIGHTY=80;
	public static double MONITOR_PER_NINETY=90;
	public static double MONITOR_PER_NINETYFIVE=95;

	public static String MONITOR_APP_YES="Y";
	public static String MONITOR_APP_NO="N";

	public static String RES_DYNAMIC_YES="Y";
	public static String RES_DYNAMIC_NO="N";

	/*服务器放包路径和日志路径*/

	public static String DEFAULT_JAR_PATH="/data/webapps";

	public static String DEFAULT_LOG_PATH="/data/logs";
	/*动态资源单个或者批量类型*/
	public static String  DYNAMIC_BATCH="batch";

	public static String  DYNAMIC_SINGLE="single";

	//服务升级发布锁定30分钟 防止异常情况下 更新服务状态失败
	public static Integer LOCK_THIRTY_MINUTE=30;


	public static final String  SMS_TEMPLATE_CODE="100010620002";


	public static final String   BUSINESS_SUCESS ="businessSuccess";

	public static final String   CALL_SUCESS ="callSucces";

	public static final String   CALL_ERROR ="callError";

}
