package com.example.hackdemo.auth;

import com.example.hackdemo.jwt.JwtTokenProvider;
import com.example.hackdemo.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    public CustomOAuth2SuccessHandler(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 사용자 정보 가져오기
        GoogleUserDetails googleUserDetails = (GoogleUserDetails) authentication.getPrincipal();
        User user = googleUserDetails.toUserEntity();

        // JWT 토큰 생성
        String token = jwtTokenProvider.generateToken(user);

        // JWT를 쿠키에 저장
        Cookie jwtCookie = new Cookie("JWT-TOKEN", token);
        jwtCookie.setHttpOnly(true); // JavaScript로 쿠키를 접근할 수 없도록 설정
        jwtCookie.setSecure(true); // HTTPS에서만 쿠키 전송
        jwtCookie.setPath("/"); // 웹 애플리케이션 전체에 쿠키 적용
        jwtCookie.setMaxAge(7 * 24 * 60 * 60); // 쿠키 유효기간 (7일)

        response.addCookie(jwtCookie);

        // 인증 성공 후 리디렉션
        getRedirectStrategy().sendRedirect(request, response, "/api/auth/login-success");
    }
}
