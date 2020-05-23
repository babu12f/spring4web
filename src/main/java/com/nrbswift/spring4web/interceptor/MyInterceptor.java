package com.nrbswift.spring4web.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String controllerName = "";
        String actionName = "";

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            controllerName = handlerMethod.getBean().getClass().getSimpleName();
            //controllerName = handlerMethod.getBeanType().getSimpleName();
            actionName = handlerMethod.getMethod().getName();

            System.out.println("controller name : " + controllerName);
            System.out.println("method name : " + actionName);
        }

        return true;
    }
}
