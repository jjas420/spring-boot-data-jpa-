/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bolsadeideas.springboot.app.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ayosu
 */
@Controller
public class LocaleController {
    
   @GetMapping("/locale")
   public String locale(HttpServletRequest request){
       String ultimaUrl=request.getHeader("referer");
       return "redirect:".concat(ultimaUrl);
       
   }
   
}
