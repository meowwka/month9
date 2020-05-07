package com.product.handmade.controller;

import com.product.handmade.model.UserRegisterForm;
import com.product.handmade.service.ProductService;
import com.product.handmade.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping
 @AllArgsConstructor
public class UserController {
    private final UserService us;
private final ProductService ps;

    @GetMapping("/registration")
    public String pageRegisterUser(Model model){
        if(!model.containsAttribute("form")){
            model.addAttribute("form", new UserRegisterForm());
        }

        return "registration";
    }

    @RequestMapping("/registration")
    public String register(@Valid UserRegisterForm form,
                           BindingResult validationResult,
                           RedirectAttributes attributes) {
        attributes.addFlashAttribute("form");
        attributes.addFlashAttribute("user", form);

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/registration";
        }

        if (us.checkUser(form)) {
            attributes.addFlashAttribute("user", form);
            return "redirect:/registration";
        }
        us.saveUser(form);


        return "redirect:/successful";
    }

    @GetMapping("/successful")
    public String getUser(){
        return "successful";
    }

    @GetMapping("/test")
    @ResponseBody
    public  String getTestPage(@Valid UserRegisterForm form){
        return form.getEmail();

    }


    @ExceptionHandler(BindException.class)
    private ResponseEntity<Object> handleBindExceptionResponseEntity(BindException ex) {
        var apiFieldErrors = ex.getFieldErrors()
                .stream()
                .map(fe -> String.format("%s -> %s", fe.getField(), fe.getDefaultMessage()))
                .collect(toList());
        return ResponseEntity.unprocessableEntity()
                .body(apiFieldErrors);
    }

}
