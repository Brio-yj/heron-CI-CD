//package com.example.hackdemo.auth;
//
//import com.example.hackdemo.model.User;
//import lombok.AllArgsConstructor;
//
//import java.util.Map;
//
//import lombok.AllArgsConstructor;
//
//import java.util.Map;
//
//@AllArgsConstructor
//public class GoogleUserDetails implements OAuth2UserInfo {
//
//    private Map<String, Object> attributes;
//
//    @Override
//    public String getProvider() {
//        return "google";
//    }
//
//    @Override
//    public String getProviderId() {
//        return (String) attributes.get("sub");
//    }
//
//    @Override
//    public String getEmail() {
//        return (String) attributes.get("email");
//    }
//
//    @Override
//    public String getName() {
//        return (String) attributes.get("name");
//    }
//
//    public User toUserEntity() {
//        return User.builder()
//                .email(getEmail())
//                .name(getName())
//                .provider(getProvider())
//                .providerId(getProviderId())
//                // 기타 사용자 정보 초기화
//                .build();
//    }
//}