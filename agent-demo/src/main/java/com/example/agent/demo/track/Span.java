package com.example.agent.demo.track;

import java.util.Date;


public class Span {

    private String traceId;  //链路ID
    private Date enterTime; //方法进入时间
    private String  methodName; //方法
    private Integer offset;//位移量

    private Span(){}

    public Span(String traceId,String methodName){
        this.traceId = traceId;
        this.methodName = methodName;
        this.enterTime = new Date();
        this.offset = TraceContext.incAndReturnOffset();
    }

    public String getMethodName() {
        return methodName;
    }


    public String getTraceId() {
        return traceId;
    }


    public Date getEnterTime() {
        return enterTime;
    }

    public Integer getOffset() {
        return offset;
    }
}
