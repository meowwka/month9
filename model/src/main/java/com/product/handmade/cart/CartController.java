package com.product.handmade.cart;

import com.product.handmade.model.Product;
import com.product.handmade.repo.ProductRepository;
import com.product.handmade.repo.UserRepo;
import com.product.handmade.service.ProductService;
import com.product.handmade.service.PropertiesService;
import com.product.handmade.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class CartController {
    private final ProductRepository productRepository;
    private final UserRepo userRepo;
    private final CartRepository cartRepository;
    private final UserService userService;
    private final PropertiesService propertiesService;

    @GetMapping("/cart")
    public String cart(Model model, @SessionAttribute(name = Constants.CART_ID, required = false) List<Product> cart, HttpServletRequest uriBuilder) {
        if (cart != null) {
            model.addAttribute("cartItems", cart);
        }else {
            return "redirect:/cart";
        }
        if(uriBuilder.getUserPrincipal() != null) {
            var user = userService.getByEmail(uriBuilder.getUserPrincipal().getName());
            model.addAttribute("user", user);
        }
        return "cart";
    }

    // это метод для асинхронных запросов
    // демонстрация добавления в "корзину" через параметр @SessionAttribute cart_model
    @PostMapping("/cart")
    @ResponseBody
    public boolean addToListRest(@RequestParam("value") String value, @SessionAttribute(name = Constants.CART_ID, required = false) List<Product> cart) {
        if (cart != null) {
            cart.add(productRepository.findById(Integer.parseInt(value)).get());
        }

        return true;
    }

    // метод для добавления в "корзину" через форму
    // демонстрация добавления через объект HttpSession session
    @PostMapping("/cart/add")
    public String addToList(@RequestParam("value") String value, HttpSession session, HttpServletRequest uriBuilder) {
        Cart c = new Cart();
        c.setSession(session.getId());
        if(userRepo.findByEmail(uriBuilder.getUserPrincipal().getName()).isPresent()){
            c.setUser(userRepo.findByEmail(uriBuilder.getUserPrincipal().getName()).get());
//            c.setProducts((List<Product>) productRepository.findById(uriBuilder.getUserPrincipal().hashCode()).get());
            cartRepository.save(c);


        }


        var attr = session.getAttribute(Constants.CART_ID);
        if (attr == null) {
            session.setAttribute(Constants.CART_ID, new ArrayList<Product>());
        }
        try {
            var list = (List<Product>) session.getAttribute(Constants.CART_ID);
            list.add(productRepository.findById(Integer.parseInt(value)).get());
        } catch (ClassCastException ignored) {

        }

        return "redirect:/";
    }

    // в идеале это должно быть @DeleteMapping, но web-формы не поддерживают
    // запросы с методами отличными от get и post
    // по этому у нас адрес метода немного "странный" :)
    @PostMapping("/cart/empty")
    public String emptyCart(HttpSession session) {
        session.removeAttribute(Constants.CART_ID);

        return "redirect:/cart";
    }
}
