package com.lushnikova.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class RequestProcessingTimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long startTime = System.currentTimeMillis();
        log.info("Request URL::{}:: Start Time={}", request.getRequestURL().toString(), System.currentTimeMillis());

        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)  {
        log.info("Request URL::{} Sent to Handler :: Current Time={}", request.getRequestURL().toString(), System.currentTimeMillis());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long startTime = (Long) request.getAttribute("startTime");
        log.info("Request URL::{}:: End Time={}", request.getRequestURL().toString(), System.currentTimeMillis());
        log.info("Request URL::{}:: Time Taken={}", request.getRequestURL().toString(), System.currentTimeMillis() - startTime);
    }

}