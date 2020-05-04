package com.product.handmade.controller;

import com.product.handmade.model.UserRegisterForm;
import com.product.handmade.service.ProductService;
import com.product.handmade.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping
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

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/registration";
        }


        return "redirect:/index";
    }



}
