package com.example.agent.demo.track;


import java.util.Stack;
import java.util.UUID;

public class TraceContext {

    private static final ThreadLocal<String> traceIdLocal = new ThreadLocal<>();

    private static final ThreadLocal<Stack<Span>> spanLink = new ThreadLocal<>();

    private static final ThreadLocal<Integer> offsetLocal = new ThreadLocal<>();


    public static void clear(){
        traceIdLocal.remove();
        spanLink.remove();
        offsetLocal.remove();
    }


    public static String getTraceId() {
        String traceId = traceIdLocal.get();
        if (null == traceId){
            traceId = UUID.randomUUID().toString().replaceAll(" ","-");
            traceIdLocal.set(traceId);
        }
        return traceId;
    }


    public static Stack<Span> getSpanStack() {
        Stack<Span> spanStack = spanLink.get();
        if (null == spanStack){
            spanStack = new Stack<>();
            spanLink.set(spanStack);
        }
        return spanStack;
    }


    public static Integer incAndReturnOffset() {
        Integer offset = offsetLocal.get();
        if (null == offset){
            offset = 0;
        }else {
            offset++;
        }
        offsetLocal.set(offset);
        return offset;
    }

}
