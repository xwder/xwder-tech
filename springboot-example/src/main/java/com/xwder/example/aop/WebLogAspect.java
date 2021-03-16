package com.xwder.example.aop;

import com.xwder.example.util.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * -@Aspect 注解 使之成为切面类
 *
 * @author xwder
 * @date 2021年3月11日
 */
@Aspect
@Component
@Order(-5)
public class WebLogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ThreadLocal<Long> startTime = new ThreadLocal<Long>();


    /**
     * 定义切入点
     */
    @Pointcut("execution(public * com.xwder..controller.*.*(..))")
    public void webLog() {
    }


    /**
     * 在连接点执行之前执行的通知
     *
     * @param joinPoint
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        logger.info("WebLogAspect.doBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        //获取所有参数方法一：
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            logger.info("请求参数信息: {}-{}", paraName, request.getParameter(paraName));
        }
    }

    /**
     * 在连接点执行之后执行的通知（返回通知和异常通知的异常）
     *
     * @param joinPoint
     */
    @After("webLog()")
    public void doAfter(JoinPoint joinPoint) {

    }

    /**
     * 在连接点执行之后执行的通知（返回通知）
     *
     * @param joinPoint
     */
    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestUrl = request.getRequestURL().toString();
        String remoteAddr = request.getRemoteAddr();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String requestIp = IpUtils.getIpAddress(request);

        // 处理完请求，返回内容
        logger.info("WebLogAspect.doAfterReturning()");
        logger.info("请求[requestURI:{}],[method:{}],[requestIp:{}]-处理完毕，[耗时(毫秒):{}]",requestURI,method,requestIp, (System.currentTimeMillis() - startTime.get()));
    }

    /**
     * @description 在连接点执行之后执行的通知（异常通知）
     */
    @AfterThrowing("webLog()")
    public void doAfterThrowing(JoinPoint joinPoint) {

        logger.info("WebLogAspect.doAfterThrowing()");
        logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
    }
}