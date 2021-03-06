package com.product.handmade.controller;

import com.product.handmade.cart.CartRepository;
import com.product.handmade.cart.CartService;
import com.product.handmade.cart.Constants;
import com.product.handmade.cart.KeyValueRequestDto;
import com.product.handmade.exception.ResourceNotFoundException;
import com.product.handmade.model.Order;
import com.product.handmade.model.Product;
import com.product.handmade.model.User;
import com.product.handmade.model.UserRegisterForm;
import com.product.handmade.repo.OrderRepository;
import com.product.handmade.repo.ProductRepository;
import com.product.handmade.repo.UserRepo;
import com.product.handmade.resetPassword.PasswordResetToken;
import com.product.handmade.resetPassword.ResetRepository;
import com.product.handmade.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping
@AllArgsConstructor
public class FrontendController {
    private final UserService userService;
    private final PlaceService placeService;
    private final ProductService productService;
    private final PropertiesService propertiesService;
    private final UserRepo userRepo;
    private final ResetRepository reserRepo;
    private final OrderService orderService;

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


    @GetMapping("/")
    public String index(Model model, Pageable pageable, HttpServletRequest uriBuilder, HttpSession session) {
        var map = new HashMap<String, Object>();
        map.put("Идентификатор сессии", session.getId());

        session.getAttributeNames()
                .asIterator()
                .forEachRemaining(key -> map.put(key, session.getAttribute(key).toString()));

        model.addAttribute("sessionAttributes", map);

        var products = productService.findProducts(pageable);
        var uri = uriBuilder.getRequestURI();
        var mo = model.addAttribute("products", productService.findAllProducts());
        constructPageable(products, propertiesService.getDefaultPageSize(), mo, uri);
        if(uriBuilder.getUserPrincipal() != null) {
            var user = userService.getByEmail(uriBuilder.getUserPrincipal().getName());
            model.addAttribute("user", user);
        }
        return "main";
    }

    @RequestMapping("/")
    public String searchResult( Pageable pageable,
                               @RequestParam("name") String name, HttpServletRequest uriBuilder,Model model) {
        var uri = uriBuilder.getRequestURI();
        var result = productService.findByName(name,pageable);
        constructPageable(result, propertiesService.getDefaultPageSize(), model, "/");
        return "main";
    }

    @GetMapping("/registration")
    public String pageRegisterUser(Model model,  HttpServletRequest uriBuilder){
        if(!model.containsAttribute("form")){
            model.addAttribute("form", new UserRegisterForm());
        }
        if(uriBuilder.getUserPrincipal() != null) {
            var user = userService.getByEmail(uriBuilder.getUserPrincipal().getName());
            model.addAttribute("user", user);
        }
        return "registration";
    }

    @RequestMapping("/registration")
    public String register(@Valid UserRegisterForm form,
                           BindingResult validationResult,
                           RedirectAttributes attributes) {
        if (validationResult.hasFieldErrors()) {
            var list = validationResult.getFieldErrors();
            attributes.addFlashAttribute("errors", list);
            return "redirect:/registration";
        }
        if (userService.checkUser(form)) {
            attributes.addFlashAttribute("user", form);
            return "redirect:/";
        }
        attributes.addFlashAttribute("user", form);

        userService.register(form);
        return "redirect:/login";
    }

    @GetMapping("/successful")
    public String getUser(Model model, Principal principal) {
        var user = userService.getByEmail(principal.getName());
        model.addAttribute("user", user);
        return "successful";
    }
    @GetMapping("/order")
    public String OrderPage(Model model, Principal principal){
        var user = userService.getByEmail(principal.getName());
        model.addAttribute("user", user);
        return "order";
    }
    @PostMapping("/order")
    public String order(@RequestParam("email") String email,
//                        @RequestParam("number") int number, @RequestParam("address") String address,
//                        @RequestParam("city") String city, @RequestParam("state") String state, @RequestParam("zip") int zip
                        @Valid Order order ,BindingResult validationResult,
                        RedirectAttributes attributes){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/order";
        }

        if(userService.check(email)){
            attributes.addFlashAttribute("order", order);
            orderService.saveOrder(order);
            return "redirect:/";
        }
        return "order";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model){
        model.addAttribute("error",error);
        return "login";
    }

    @GetMapping("/test")
    @ResponseBody
    public  String getTestPage(@Valid UserRegisterForm form){
        return form.getEmail();

    }

    @GetMapping("/places/{id:\\d+?}")
    public String placePage(@PathVariable int id,Pageable pageable, HttpServletRequest uriBuilder, Model model ) {
        model.addAttribute("place", placeService.getPlace(id));
        var uri = uriBuilder.getRequestURI();
        var foods = productService.getProducts(id, pageable);
        constructPageable(foods, propertiesService.getDefaultPageSize(), model, uri);

        return "place";
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private String handleRNF(ResourceNotFoundException ex, Model model) {
        model.addAttribute("resource", ex.getResource());
        model.addAttribute("id", ex.getId());
        return "resource-not-found";
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

    @GetMapping("/invalidate")
    public String invalidate(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @PostMapping("/store")
    public String store(KeyValueRequestDto data, HttpSession session) {
        if (session != null) {
            var attr = session.getAttribute(data.getKey());
            if (attr == null) {
                session.setAttribute(data.getKey(), data.getValue());
            }
        }

        return "redirect:/";
    }

    @GetMapping("/forgot-password")
    public String pageForgotPassword(Model model, HttpServletRequest uriBuilder) {
        if(uriBuilder.getUserPrincipal() != null) {
            var user = userService.getByEmail(uriBuilder.getUserPrincipal().getName());
            model.addAttribute("user", user);
        }

        return "forgot";
    }

    @PostMapping("/forgot-password")
    public String submitForgotPasswordPage(@RequestParam("email") String email,
                                           RedirectAttributes attributes) {


        if (!userRepo.existsByEmail(email)) {
            String errorText = "";
            attributes.addFlashAttribute("errorText", errorText);
            return "redirect:/forgot-password";
        }

        PasswordResetToken pToken = PasswordResetToken.builder()
                .user(userRepo.findByEmail(email).get())
                .token(UUID.randomUUID().toString().replace("-",""))
                .build();
        reserRepo.deleteAll();
        reserRepo.save(pToken);
        attributes.addFlashAttribute("token", pToken);
        return "redirect:/forgot-success";
    }

    @GetMapping("/forgot-success")
    public String pageResetPassword(Model model,  HttpServletRequest uriBuilder)
    {
        if(uriBuilder.getUserPrincipal() != null) {
            var user = userService.getByEmail(uriBuilder.getUserPrincipal().getName());
            model.addAttribute("user", user);
        }
        return "forgot-success";
    }

    @PostMapping("/reset-password")
    public String submitResetPasswordPage(@RequestParam("token") String token,
                                          @RequestParam("newPassword") String newPassword,
                                          RedirectAttributes attributes) {
        if (!reserRepo.existsByToken(token)) {
            String errorText = "";
            attributes.addFlashAttribute("errorText", errorText);
            return "redirect:/forgot-password";
        }
        PasswordResetToken pToken = reserRepo.findByToken(token).get();
        User user = userRepo.findById(pToken.getUser().getId()).get();
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepo.save(user);
        return "redirect:/login";
    }


}
