package com.sparta.schedules.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.schedules.dto.ErrorResponseDto;
import com.sparta.schedules.web.config.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] whiteList = {"/home/user", "/home/login", "home/logout"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (isLoginCheckPath(requestURI)) {
            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute(SessionAttribute.LOGIN_MEMBER.getKey()) == null) {

                ErrorResponseDto errorResponse = new ErrorResponseDto(HttpServletResponse.SC_UNAUTHORIZED, "Login is required");

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonResponse = objectMapper.writeValueAsString(errorResponse);

                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
                httpResponse.getWriter().write(jsonResponse);
                return;
            }
        }

        filterChain.doFilter(httpRequest, httpResponse);
    }

    private boolean isLoginCheckPath(String requestUri) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestUri);
    }
}
