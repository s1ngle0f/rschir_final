package com.example.rschir_final.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController
{
    @PostMapping("/")
    public String index_post(@RequestParam Map<String, Object> map, HttpServletResponse response){
        System.out.println(map);
        if(map.get("lang") != null)
        {
            Cookie cookie = new Cookie("lang", (String) map.get("lang"));
            response.addCookie(cookie);
        }
        if(map.get("theme") != null)
        {
            Cookie cookie = new Cookie("theme", (String) map.get("theme"));
            response.addCookie(cookie);
        }
        return "redirect:/";
    }

    @GetMapping("/")
    public String index_get(@CookieValue(value = "theme", defaultValue = "light") String theme, @CookieValue(value = "lang", defaultValue = "ru") String lang, Model model){
        Map<String, String> var = new HashMap<>();
        if(lang.equals("ru")){
            var.put("change_to_lang", "en");
            if(theme.equals("dark")){
                var.put("change_to_theme", "light");
            }else{
                var.put("change_to_theme", "dark");
            }
        }else if(lang.equals("en")){
            var.put("change_to_lang", "ru");
            if(theme.equals("dark")){
                var.put("change_to_theme", "light");
            }else{
                var.put("change_to_theme", "dark");
            }
        }
        model.addAttribute("theme", theme);
        model.addAttribute("lang", lang);
        return "home";
    }
}
