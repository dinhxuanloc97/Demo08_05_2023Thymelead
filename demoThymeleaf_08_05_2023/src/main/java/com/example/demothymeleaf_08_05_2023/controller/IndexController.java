package com.example.demothymeleaf_08_05_2023.controller;

import com.example.demothymeleaf_08_05_2023.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    private static List<Product> list = new ArrayList<>();
    private static Product product = new Product();

    @GetMapping("/home")
    public String getHomepage() {
        return "index";
    }

    @GetMapping("/variable")
    public ModelAndView index(@RequestParam(value = "name") String name) {
        ModelAndView mav = new ModelAndView("variable");
        mav.addObject("name", name);
        mav.addObject("variable", "hien thi bieu thuc");
        mav.addObject("localVariable", "localVariable");
        mav.addObject("product", product);
        return mav;
    }

    @GetMapping("/condition1")
    public String ifTestFalse(Model model) {
        Product product1 = new Product("Casino", 123);
        model.addAttribute("Product", product1);
        return "condition";
    }

    @GetMapping("/create")
    public static String createFrom(@ModelAttribute Model model) {
        model.addAttribute("product", new Product());
        return "form";
    }

    @GetMapping("/form")
    public static ModelAndView form() {
        ModelAndView max = new ModelAndView("form");
        max.addObject("list", list);
        return max;
    }

    @PostMapping("/createProduct")
    public static String createProduct(@ModelAttribute Product product, Model model) {
        if (!product.getName().equals("Iphone")) {
            model.addAttribute("er", "Ten sp phải co Iphone");
            return "create";
        }
        if (product.getPrice() < 500) {
            model.addAttribute("ms", "gia phải lớn hơn 500");
            return "create";
        }
        list.add(product);
        model.addAttribute("list", list);
        return "form";
    }
    @GetMapping("/loop")
    public String Loops(Model model) {
        String[] tokei = new String[]{"Casino", "seiko", "Carnation", "Hyacinth"};
        model.addAttribute("tokei", tokei);
        return "Loops";
    }

}
