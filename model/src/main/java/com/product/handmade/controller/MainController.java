package com.product.handmade.controller;

import com.product.handmade.repo.ProductTypeRepository;
import com.product.handmade.service.ProductService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MainController {

    @Autowired
    ProductTypeRepository repo;
    private final ProductService productService;

    @RequestMapping("/jql/{name}")
    public String getMainPageJql(Model model, @PathVariable("name") String name) {
        model.addAttribute("productTypes", repo.getByName(name));
        return "productTypes";
    }




}
