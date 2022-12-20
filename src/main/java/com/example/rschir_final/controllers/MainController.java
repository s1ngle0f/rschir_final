package com.example.rschir_final.controllers;

import com.example.rschir_final.models.FileNotUser;
import com.example.rschir_final.repo.FileNotUserRepo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController
{
    @Autowired
    FileNotUserRepo fileNotUserRepo;

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
                var.put("cur_theme", "black");
            }else{
                var.put("change_to_theme", "dark");
                var.put("theme", "светлый");
                var.put("cur_theme", "white");
            }
        }else if(lang.equals("en")){
            var.put("change_to_lang", "ru");
            var.put("lang", "en");
            if(theme.equals("dark")){
                var.put("change_to_theme", "light");
                var.put("theme", "dark");
                var.put("cur_theme", "black");
            }else{
                var.put("change_to_theme", "dark");
                var.put("theme", "light");
                var.put("cur_theme", "white");
            }
        }
        for (Map.Entry<String, String> entry : var.entrySet())
            model.addAttribute(entry.getKey(), entry.getValue());
        model.addAttribute("name", name);
        return "home";
    }

    @GetMapping("/pdf")
    public String pdf_get(Model model){
        model.addAttribute("files", fileNotUserRepo.findAll());
        return "pdf";
    }

    @GetMapping("/pdf/download/{id}")
    public ResponseEntity<ByteArrayResource> pdf_download_by_id(@PathVariable Long id){
        FileNotUser file = fileNotUserRepo.findFileNotUserById(id);
        System.out.println("File: " + file);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @GetMapping("/pdf/delete/{id}")
    public String pdf_delete_by_id(@PathVariable Long id){
        fileNotUserRepo.deleteById(id);
        return "redirect:/pdf";
    }

    @PostMapping("/pdf")
    public String pdf_post(@RequestParam("file") MultipartFile file) throws IOException
    {
        System.out.println(file.getOriginalFilename());
        FileNotUser newFile = new FileNotUser(file.getOriginalFilename(), file.getContentType(), file.getSize(), file.getBytes());
        fileNotUserRepo.saveAndFlush(newFile);
        return "redirect:/pdf";
    }
}
