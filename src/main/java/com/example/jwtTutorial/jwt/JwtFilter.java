package com.example.jwtTutorial.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Slf4j
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "authorization";

    private final TokenProvider tokenProvider;

    public JwtFilter(TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(
            ServletRequest request, ServletResponse response, FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)){
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication((authentication));
            log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri : {}", authentication.getName(), requestURI);
        }else{
            log.debug("유효한 JWT 가 없습니다, uri: {}", requestURI);
        }

        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

}
