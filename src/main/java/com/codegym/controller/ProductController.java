package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductType;
import com.codegym.service.ProductService;
import com.codegym.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Optional;

@Controller
public class ProductController {
    public static final String ERROR_404 = "/error-404";

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @ModelAttribute("productTypes")
    public Iterable<ProductType> productTypes() {
        return productTypeService.findAll();
    }

    @GetMapping("/products")
    public ModelAndView showAll(@RequestParam("search") Optional<String> search){
        ModelAndView modelAndView = new ModelAndView("/product/list");
        Iterable<Product> products;
        if (search.isPresent()) {
            products = productService.findByNameContains(search.get());
        } else {
            products = productService.findAll();
        }
        modelAndView.addObject("products",products);
        return modelAndView;
    }

    @GetMapping("/create-product")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView saveProduct(@ModelAttribute Product product) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        product.setDate(date);
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "Created!");
        return modelAndView;
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product);
            return modelAndView;
        }
        return new ModelAndView(ERROR_404);
    }

    @PostMapping("/edit-product")
    public ModelAndView editProduct(@ModelAttribute Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Updated!");
        return modelAndView;
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product);
            return modelAndView;
        }
        return new ModelAndView(ERROR_404);
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@ModelAttribute Product product) {
        productService.remove(product.getId());
        return "redirect:/products";
    }
}
