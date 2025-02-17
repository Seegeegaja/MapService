package com.my.mapService.config;

import com.my.mapService.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request
            , HttpServletResponse response
            , Object handler) throws Exception {
        //검문 민증까(무엇을 하기전에 물어보는것 request)
        HttpSession session = request.getSession();
        UserDto currentSession = (UserDto) session.getAttribute("sessionInfo");
        if (ObjectUtils.isEmpty(currentSession)) {
            response.sendRedirect("/users/login");
            return false;
        }
        return true;
    }
}
