package com.example.demozjy.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* 执行登陆检查
* */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    //Controller执行前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            Object user = request.getSession().getAttribute("loginUser");
            if(user == null){
                //登陆没有成功
                //在request域中放入信息
                request.setAttribute("msg","你还未登录");
                //获取转发器转发请求
                request.getRequestDispatcher("/login").forward(request,response);
                return false;

            }else {
                return true;
            }
            //注册器写完记得进行注册
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
