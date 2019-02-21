package com.spring.mvc.controller;

import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * -@description: 用户处理器-非注解方式
 * <p>
 * -@author: xiang
 * -@create: 2019-02-21 22:10
 */
public class UserController2 implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Object> list = new ArrayList<>();
        list.add("name");
        list.add("age");
        request.setAttribute("list",list);
        request.getRequestDispatcher("WEB-INF/jsp/user.jsp").forward(request,response);
    }
}
