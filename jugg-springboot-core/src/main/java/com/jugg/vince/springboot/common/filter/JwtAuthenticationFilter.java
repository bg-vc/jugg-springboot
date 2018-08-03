package com.jugg.vince.springboot.common.filter;


import com.jugg.vince.springboot.common.config.WebMvcConfigurer;
import com.jugg.vince.springboot.common.enums.ResultCode;
import com.jugg.vince.springboot.common.model.Result;
import com.jugg.vince.springboot.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static com.jugg.vince.springboot.common.util.JwtUtil.ROLE;

/**
 * Author:   Vince
 * Date:     2018/7/30 上午12:04
 * Description:
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final PathMatcher pathMatcher = new AntPathMatcher();
    private String protectUrlPattern = "/api/**";

    public JwtAuthenticationFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            if (isProtectedUrl(request)) {
                Map<String, Object> claims = JwtUtil.validateTokenAndGetClaims(request);
                String role = String.valueOf(claims.get(ROLE));

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                null, null, Arrays.asList(() -> role)));
            }
        } catch (Exception e) {
            log.error("Authentication error:{}", e.getMessage());
            Result<String> result = new Result<String>(ResultCode.UNAUTHORIZED).setMessage("authentication fail:token error");
            WebMvcConfigurer.responseResult(response, result);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean isProtectedUrl(HttpServletRequest request) {
        return pathMatcher.match(protectUrlPattern, request.getServletPath());
    }


}
