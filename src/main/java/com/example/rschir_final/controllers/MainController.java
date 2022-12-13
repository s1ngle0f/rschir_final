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
    @PostMapping("/change_theme_and_lang")
    public String change_theme_and_lang_post(@RequestParam Map<String, Object> map, HttpServletResponse response){
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

    @PostMapping("/change_name")
    public String change_name_post(@RequestParam Map<String, Object> map, HttpServletResponse response){
        System.out.println(map);
        if(map.get("name") != null)
        {
            Cookie cookie = new Cookie("name", (String) map.get("name"));
            response.addCookie(cookie);
        }
        return "redirect:/";
    }

    @GetMapping("/")
    public String index_get(@CookieValue(value = "name", defaultValue = "Гачи") String name, @CookieValue(value = "theme", defaultValue = "light") String theme, @CookieValue(value = "lang", defaultValue = "ru") String lang, Model model){
        Map<String, String> var = new HashMap<>();
        if(lang.equals("ru")){
            var.put("change_to_lang", "en");
            var.put("lang", "ру");
            if(theme.equals("dark")){
                var.put("change_to_theme", "light");
                var.put("theme", "темный");
            }else{
                var.put("change_to_theme", "dark");
                var.put("theme", "светлый");
            }
        }else if(lang.equals("en")){
            var.put("change_to_lang", "ru");
            var.put("lang", "en");
            if(theme.equals("dark")){
                var.put("change_to_theme", "light");
                var.put("theme", "dark");
            }else{
                var.put("change_to_theme", "dark");
                var.put("theme", "light");
            }
        }
        for (Map.Entry<String, String> entry : var.entrySet())
            model.addAttribute(entry.getKey(), entry.getValue());
        model.addAttribute("name", name);
        return "home";
    }

    @GetMapping("/pdf")
    public String pdf_get(Model model){
        return "pdf";
    }
    @GetMapping("/pdf")
    public String pdf_post(Model model){

        return "pdf";
    }
}
