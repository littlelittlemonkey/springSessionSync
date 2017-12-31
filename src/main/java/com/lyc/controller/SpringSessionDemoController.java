package com.lyc.controller;

import com.lyc.commons.ConstString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 解决session分布式同步
 * 对于分布式环境Session跨域共享的问题，不管是使用开源的框架还是使用自己开发的框架，
 * 都需要明白的一个问题是：在Tomcat容器中创建Session是一个很耗费内存的事情。
 * 因此，我们在自己写类似框架的时候，我们一定要注意的是，并不是Tomcat为我们
 * 创建好了Session之后，我们首先获取Session然后再上传到Redis等进行存储，
 * 而是直接有我们自己创建Session，这一点是至关重要的！
 */
@Controller
@RequestMapping(value = "/spring/session", produces = {ConstString.APP_JSON_UTF_8})
public class SpringSessionDemoController {

    @RequestMapping(value = "/setSession.do", method = RequestMethod.GET)
    public void setSession(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String value = request.getParameter("value");
        System.out.println("--------添加session到redis------------");
        request.getSession().setAttribute(name, value);
    }

    @RequestMapping(value = "/getSession.do", method = RequestMethod.GET)
    public void getInterestPro(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        System.out.println("------" + request.getSession().getAttribute(name));
    }

    @RequestMapping(value = "/removeSession.do", method = RequestMethod.GET)
    public void removeSession(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        request.getSession().removeAttribute(name);
    }
}
