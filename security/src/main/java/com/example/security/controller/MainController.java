package com.example.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class MainController {

    @GetMapping("/")
    public String MainPage(Model model){

        // 세션 현재 사용자 아이디
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        // 세션 현재 사용자 권한
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        // model 객체에 담아서 메인 페이지로 로그인 세션 정보 넘겨주기
        model.addAttribute("id", id);
        model.addAttribute("role", role);

        return "main";  //  루트 디렉토리로 가면 main페이지가 화면에 나타난다.
    }
}
