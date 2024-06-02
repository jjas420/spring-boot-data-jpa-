package com.bolsadeideas.springboot.app.errorController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ayosu
 */
@ControllerAdvice
public class ErrorHandlerController {
    @ExceptionHandler(NoResourceFoundException.class)
    public String Error404(NoResourceFoundException ex, Model model){
        model.addAttribute("error", ex.getResourcePath());
        
        return "/error_404";
    }
    
    
}
