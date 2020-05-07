package com.product.handmade.controller;

import com.product.handmade.exception.ResourceNotFoundException;
import com.product.handmade.service.PlaceService;
import com.product.handmade.service.ProductService;
import com.product.handmade.service.PropertiesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
@AllArgsConstructor
public class FrontendController {

    private final PlaceService placeService;
    private final ProductService productService;

    private final PropertiesService propertiesService;

    private static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }

        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }

        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("products", list.getContent());
        model.addAttribute("defaultPageSize", pageSize);
    }

    private static String constructPageUri(String uri, int page, int size) {
        return String.format("%s?page=%s&size=%s", uri, page, size);
    }

    @GetMapping
    public String index(Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        var products = productService.findProducts(pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);

        return "index";
    }

    @GetMapping("/places/{id:\\d+?}")
    public String placePage(@PathVariable int id, Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        model.addAttribute("place", placeService.getPlace(id));
        var uri = uriBuilder.getRequestURI();
        var foods = productService.getProducts(id, pageable);
        constructPageable(foods, propertiesService.getDefaultPageSize(), model, uri);

        return "place";
    }

    @RequestMapping("/")
    public String searchResult(Model model, Pageable pageable, HttpServletRequest uriBuilder,
                               @RequestParam("name") String name) {
        var uri = uriBuilder.getRequestURI();
        var result = productService.findByName(name,pageable);
        constructPageable(result, propertiesService.getDefaultPageSize(), model, "/");
        return "index";
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private String handleRNF(ResourceNotFoundException ex, Model model) {
        model.addAttribute("resource", ex.getResource());
        model.addAttribute("id", ex.getId());
        return "resource-not-found";
    }

}
