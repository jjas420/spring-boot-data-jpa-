/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bolsadeideas.springboot.app.controllers;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * 
 * 
 * @author ayosu
 */
@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String login (@RequestParam(value="error",required=false) String error,Model model, Principal principal, RedirectAttributes flash,
          @RequestParam(value="logout",required=false) String logout  ){
        if(principal != null){
            flash.addFlashAttribute("info","ya ha iniciado sesion anteriormente");
            return "redirect:/";
        }if(error!=null){
            model.addAttribute("error", "error en el login: Nombre de usuario o Contraseña incorrecta, por favor vuelva a intentarlo");
            
        }
        if(logout != null){
                        model.addAttribute("success", "se cerrro sesion correctamente");

    
            
        }
        return "login";
       
    }
    
    
}
