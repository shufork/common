package me.shufork.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import me.shufork.common.constants.HttpHerderKeyConstants;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ServletRequestInterceptor implements HandlerInterceptor {

    private static final String X_USER_ID = "x_user_id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.trace("[pre handle]:{}",request.getRequestURI());
        String userId = request.getHeader(HttpHerderKeyConstants.X_USER_ID);

        if (userId != null) {
            MDC.put(X_USER_ID, userId);
        }

        log.info("{}?{} {}", request.getRequestURI(), request.getQueryString(), request.getMethod());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.trace("[post handle]:{}",request.getRequestURI());
        MDC.remove(X_USER_ID);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.trace("[after completion]:{}",request.getRequestURI());
    }

}
