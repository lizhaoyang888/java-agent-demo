package com.example.agent.demo;

import com.alibaba.fastjson.JSON;
import com.example.agent.demo.track.Span;
import com.example.agent.demo.track.TraceContext;
import net.bytebuddy.asm.Advice;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Stack;
import static com.example.agent.demo.MyAgentLogger.log;

public class MyAdvice {

    // @Advice.AllArguments Object[] args

    @Advice.OnMethodEnter()
    public static void enter(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName, @Advice.AllArguments Object[] args) {

        log.info("args=>{}", JsonUtil.object2Json(args));
        String traceId = TraceContext.getTraceId();

        Span span = new Span(traceId,className+"->"+methodName);

        Stack<Span> spanStack = TraceContext.getSpanStack();

        spanStack.push(span);

 //       HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

//        System.out.println(JsonUtil.object2Json(request.getRequestURL()));
//        System.out.println(JsonUtil.object2Json(request.getAuthType()));
//        System.out.println(JsonUtil.object2Json(request.getContextPath()));
//        System.out.println(JsonUtil.object2Json(request.getCookies()));
//        System.out.println(JsonUtil.object2Json(request.getMethod()));
//        System.out.println(JsonUtil.object2Json(request.getPathInfo()));
//        System.out.println(JsonUtil.object2Json(request.getQueryString()));

      //  logRequestInfo(request);
    }

//    private static void logRequestInfo(HttpServletRequest request){
//        System.out.println(JsonUtil.object2Json(request.getRequestURL()));
//        System.out.println(JsonUtil.object2Json(request.getAuthType()));
//        System.out.println(JsonUtil.object2Json(request.getContextPath()));
//        System.out.println(JsonUtil.object2Json(request.getCookies()));
//        System.out.println(JsonUtil.object2Json(request.getMethod()));
//        System.out.println(JsonUtil.object2Json(request.getPathInfo()));
//        System.out.println(JsonUtil.object2Json(request.getQueryString()));
//    }


    //@Advice.Return Object result, @Advice.Thrown Throwable throwable
    @Advice.OnMethodExit()
    public static void exit(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName, @Advice.Return Object result) {
        Stack<Span> spanStack = TraceContext.getSpanStack();
        Span span = spanStack.pop();

        log.info("链路追踪：traceId:" + span.getTraceId() +" offset:"+span.getOffset()+" method:"+ span.getMethodName()+" 耗时:" + (System.currentTimeMillis() - span.getEnterTime().getTime()) + "ms");

        if (spanStack.isEmpty()){
            TraceContext.clear();
        }

        log.info("result=>{}", JsonUtil.object2Json(result));
//
//        log.info("throwable=>{}", JsonUtil.object2Json(throwable));


//        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
//        response.setHeader("X-TraceId",TraceContext.getTraceId());
//        System.out.println(JsonUtil.object2Json(response.getHeader("X-TraceId")));
    }
}
