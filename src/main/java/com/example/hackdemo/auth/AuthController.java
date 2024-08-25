package com.example.hackdemo.auth;

import com.example.hackdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/google")
    public ResponseEntity<String> initiateGoogleAuth() {
        logger.info("Initiating Google OAuth process");
        String googleAuthUrl = "/oauth2/authorization/google";
        return ResponseEntity.ok("{\"googleAuthUrl\": \"" + googleAuthUrl + "\"}");
    }

    @GetMapping("/login-success")
    public ResponseEntity<String> loginSuccess(Authentication authentication) {
        logger.info("Google login success");

        if (authentication != null && authentication.isAuthenticated()) {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            String email = oauth2User.getAttribute("email");
            String name = oauth2User.getAttribute("name");

            logger.info("Authenticated user: {}", email);
            return ResponseEntity.ok("{\"message\": \"로그인 및 회원가입이 완료되었습니다.\", \"email\": \"" + email + "\", \"name\": \"" + name + "\", \"success\": true}");
        } else {
            logger.warn("Unauthorized access attempt to login-success endpoint");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"인증이 필요합니다.\", \"success\": false}");
        }
    }

    @GetMapping("/logout-success")
    public ResponseEntity<String> logoutSuccess() {
        logger.info("User logged out successfully");
        return ResponseEntity.ok("{\"message\": \"로그아웃이 완료되었습니다.\", \"success\": true}");
    }
}
