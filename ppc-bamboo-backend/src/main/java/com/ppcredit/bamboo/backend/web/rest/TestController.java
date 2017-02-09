package com.ppcredit.bamboo.backend.web.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    private static Logger log = LoggerFactory.getLogger(TestController.class); // 日志

    @RequestMapping("/")
    public String echo(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("进入index......");
        return "redirect:/admin/manage/user_manage";
    }

}
