package com.example.agent.demo;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

/**
 * @Author: lichaoyang
 * @Date: 2020-06-15 15:13
 */
public class AgentBootstrap {

    public static void main(String args, Instrumentation inst) {
        System.out.println("基于javaagent链路追踪");

        AgentBuilder agentBuilder = new AgentBuilder.Default();


        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> {
            builder = builder.visit(
                    Advice.to(MyAdvice.class)
                            .on(ElementMatchers.isMethod()
                                    .and(ElementMatchers.any()).and(ElementMatchers.not(ElementMatchers.nameStartsWith("main")))));
            return builder;
        };

        agentBuilder = agentBuilder.type(ElementMatchers.nameStartsWith("com.example.hello.controller"))
        .or(ElementMatchers.nameStartsWith("com.example.hello.service"))
        .or(ElementMatchers.nameStartsWith("com.example.hello.dao"))
        .transform(transformer).asDecorator();

        agentBuilder.installOn(inst);

        System.out.println("install end");
    }

    public static void agentmain(String args, Instrumentation inst) {
        main(args, inst);
    }

    public static void premain(String args, Instrumentation inst) {
        main(args, inst);
    }

}
