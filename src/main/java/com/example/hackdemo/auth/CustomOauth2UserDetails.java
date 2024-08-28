//package com.example.hackdemo.auth;
//
//import com.example.hackdemo.model.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Map;
//
//public class CustomOauth2UserDetails implements UserDetails, OAuth2User {
//
//    private final User user;
//    private Map<String, Object> attributes;
//
//    public CustomOauth2UserDetails(User user, Map<String, Object> attributes) {
//        this.user = user;
//        this.attributes = attributes;
//    }
//
//    @Override
//    public Map<String, Object> getAttributes() {
//        return attributes;
//    }
//
//    @Override
//    public String getName() {
//        return user.getName();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getEmail();
//    }
//    @Override
//    public String getPassword() {
//        return "";
//    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//    }
//}