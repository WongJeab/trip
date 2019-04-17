package com.cn.platform.managecenter.aop;

import com.alibaba.fastjson.JSONObject;
import com.cn.platform.managecenter.constant.CommonConstant;
import com.cn.platform.managecenter.entity.system.SysLog;
import com.cn.platform.managecenter.entity.system.User;
import com.cn.platform.managecenter.service.system.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by dhj on 2018/8/22.
 */
@Aspect
@Component
public class LogAspceJAdvice {
    @Autowired
    private SysLogService sysLogService;
    /**
     * Pointcut 定义Pointcut，Pointcut的名称为aspectjMethod()，此方法没有返回值和参数
     * 该方法就是一个标识，不进行调用
     */
    @Pointcut("execution(* com.cn.platform.managecenter.controller..*.*(..))")
    private void aspectjMethod() {

    }

    /**
     * Around 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
     *
     * 注意：当核心业务抛异常后，立即退出，转向AfterAdvice 执行完AfterAdvice，再转到ThrowingAdvice
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "aspectjMethod()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {

        long start = System.currentTimeMillis();
        Object resultObj = pjp.proceed();
        long end = System.currentTimeMillis();

        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        Integer userId = 0;

        HttpSession session = request.getSession();
        try {
            Object userObject = session.getAttribute(CommonConstant.USER_KEY);
            if(null != userObject){
                User user = (User)userObject;
                userId = user.getUserId();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        String remoteAddr = request.getRemoteAddr();
        String remoteHost = request.getRemoteHost();
        String httpMethod = request.getMethod();
        String requestURI = request.getRequestURI();
        String isAjax = "";
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            isAjax = "Y";
        }else{
            isAjax = "N";
        }
        String requestMethod = pjp.toString();
        String requestParameter = JSONObject.toJSONString(request.getParameterMap());

        SysLog log = new SysLog();

        log.setRemoteAddr(remoteAddr);
        log.setRemoteHost(remoteHost);
        log.setHttpMethod(httpMethod);
        log.setRequestUri(requestURI);
        log.setIsAjax(isAjax);
        log.setRequestMethod(requestMethod);
        log.setRequestParameter(requestParameter);
        log.setLogType(CommonConstant.MONITOR_DATA_TYPE);
        log.setUserId(userId);
         log.setUseTime((int)(end-start));
        String outMsg = resultObj==null?"":resultObj.toString();
        if(outMsg.length()>4000){
            log.setResult(outMsg.substring(0,4000));
        }else{
            log.setResult(outMsg);
        }
        sysLogService.save(log);
        return resultObj;
    }




}
