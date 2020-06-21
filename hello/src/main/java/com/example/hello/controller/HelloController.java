package com.example.hello.controller;


import com.example.hello.tool.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.slf4j.Log4jLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class HelloController {
	
    @RequestMapping("/")
    public String index() {
        fuck();
        return "Hello Spring Boot 2.0!";
    }

    @RequestMapping("/test")
    public String test() {
        fuck();
        return "test";
    }


    public void fuck(){
        log.info("fuck u");
        goodMorning();
    }

    public void goodMorning(){
        log.info("goodMorning");
    }

}