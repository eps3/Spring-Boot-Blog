package cn.sheep3.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * exception log
 *
 * Created by sheep3 on 16-8-24.
 */
@Aspect
@Component
@Slf4j
public class LogAspect {


    //@AfterThrowing(throwing = "ex", pointcut = "execution(* cn.antiy.weiqing.*.*.*(..)))")
    public void ErrorLog(JoinPoint joinPoint, Throwable ex){
        //出错行
        int lineNumber = ex.getStackTrace()[0].getLineNumber();
        //j
        Signature signature = joinPoint.getSignature();
        //参数
        Object[] args = joinPoint.getArgs();
        //拼接参数
        StringBuilder argString = new StringBuilder();
        if (args.length > 0)
            for (Object arg : args)
                argString.append(arg);
        log.error("方法" + signature, "参数" + argString, "错误行：" + lineNumber, "时间" + new Date(), "异常内容" + ex.toString());
    }


    /**
     *
     * @param joinPoint
     */
    @Before("execution(* cn.sheep3.*.*.*(..))")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        StringBuilder msg = new StringBuilder();
        msg.append(":");
        msg.append(joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        if (args.length > 0)
            for (Object arg : args)
                msg.append(arg);
        log.info("before---" + msg);
    }

    /**
     * @param joinPoint
     */
    //@After("execution(* cn.antiy.weiqing.*.*.*(..))")
    public void doAfterInServiceLayer(JoinPoint joinPoint) {
        StringBuilder msg = new StringBuilder();
        msg.append(":");
        msg.append(joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        if (args.length > 0)
            for (Object arg : args)
                msg.append(arg);
        log.info("after---" + msg);
    }
}
