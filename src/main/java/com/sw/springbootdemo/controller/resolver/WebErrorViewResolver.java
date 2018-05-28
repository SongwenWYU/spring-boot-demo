package com.sw.springbootdemo.controller.resolver;

import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class WebErrorViewResolver implements ErrorViewResolver {
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", status.value());
        mv.addObject("errorCode", status.value());
        if(status.is5xxServerError()){
            mv.addObject("msg", "内部错误！");
        }else if(status.is4xxClientError()){
            mv.addObject("msg", "未找到页面！");
        }
        mv.setViewName("/error/error");

        return mv;
    }
}
