package com.example.springbootdemo.aop;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemo.common_core.Request;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * ParamValidationAspect
 *  参数校验切面
 * @author zhengzhongpeng
 * @date 2019/4/26 10:16
 */
@Aspect
@Component
public class ParamValidationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParamValidationAspect.class);


    //切入点表达式的格式：execution([可见性]返回类型[声明类型].方法名(参数)[异常])   *：匹配所有字符  ..：一般用于匹配多个包，多个参数
    @Pointcut("execution(public * com.example.springbootdemo.aop..*.*(..))")
    public void paramValidationPointcut(){}

    @Around("paramValidationPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        List<String> exceptionMsg = validation(joinPoint);
        if (! exceptionMsg.isEmpty()){
            throw new RuntimeException(exceptionMsg.toString());
        }
        return joinPoint.proceed();
    }

    public List<String> validation(ProceedingJoinPoint joinPoint){
        //参数校验异常信息
        List<String> exceptionMsg = new ArrayList<>();
        //获取参数
        Object[] args = joinPoint.getArgs();
        //如果有参数校验参数
        if (args != null && args.length != 0){
            LOGGER.info("开始调用接口:{}.{},param:{}",joinPoint.getTarget().getClass().getName(),joinPoint.getSignature().getName(), JSON.toJSONString(args));
            //获取校验目标对象
            Object object = joinPoint.getTarget();
            //获取校验方法
            MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
            Method method = methodSignature.getMethod();
            //获取校验器
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            //校验参数返回校验结果
            List<String> validMsg = validatedParam(object,method,validator,args);
            if (! validMsg.isEmpty()){
                exceptionMsg.addAll(validMsg);
            }
        }
        return exceptionMsg;
    }

    public static  <T> List<String> validatedParam(final  T instance,final Method method,final Validator validator,final Object[] params){
        List<String> list = new ArrayList<>();
        if (params.length == 1 && params[0] instanceof Request){
            //如果是自定义Request封装的参数 走自定义参数校验
            return customParamValid(instance,method,validator,params);
        }else {
            //不是自定义封装的参数 走通用参数校验
            return normalParamValid(instance,method,validator,params);
        }
    }

    /**
     * 自定义验证
     * @param instance
     * @param method
     * @param validator
     * @param params
     * @param <T>
     * @return
     */
    public static  <T> List<String> customParamValid(final  T instance,final Method method,final Validator validator,final Object[] params){
        List<String> validResult = new ArrayList<>();
        Request<?> request = (Request<?>) params[0];
        Object param = request.getData();
        if (param == null){
            validResult.add("Request中包装的参数不能为null");
        }else if (param instanceof List){
            //...
        }
        //...
        return validResult;
    }

    /**
     * 通用参数验证流程，使用hibernate的JSR303验证
     * @param instance
     * @param method
     * @param validator
     * @param params
     * @param <T>
     * @return
     */
    public static  <T> List<String> normalParamValid(final T instance, final Method method,final Validator validator,final Object[] params){
        //获取方法参数校验器
        ExecutableValidator executableValidator = validator.forExecutables();
        //校验并返回校验结果
        Set<ConstraintViolation<T>> violationSet = executableValidator.validateParameters(instance,method,params);
        //记录校验信息
        List<String> listResult = new ArrayList<>();
        if (! violationSet.isEmpty()){
            Iterator<ConstraintViolation<T>> iterator = violationSet.iterator();
            while (iterator.hasNext()){
                ConstraintViolation<T> constraintViolation = iterator.next();
                String path = constraintViolation.getPropertyPath().toString();
                String message = constraintViolation.getMessage();
                listResult.add(message);
            }
        }
        return listResult;
    }


    public String getAnnotationMessage(Annotation annotation){
        try {
            return annotation.getClass().getDeclaredMethod("message",new Class<?>[0]).invoke(annotation,new Object()).toString();
        }catch (Exception e){
            return "参数错误";
        }
    }


}
