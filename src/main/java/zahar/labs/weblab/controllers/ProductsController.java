package zahar.labs.weblab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zahar.labs.weblab.models.Product;
import zahar.labs.weblab.services.ProductsService;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/index")
    public String index(){
        return "products/index";
    }

    @GetMapping("/about_us")
    public String aboutUs(){
        return "products/about_us";
    }

    @GetMapping("/contacts")
    public String contacts(){
        return "products/contacts";
    }

    @GetMapping("/employeers")
    public String employees(){
        return "products/employeers";
    }

    @GetMapping("/goods")
    public String Goods(Model model){
        model.addAttribute("goods", productsService.findAll());
        productsService.findAll().forEach(System.out::println);
        return "products/goods";
    }

    @GetMapping("/history")
    public String history(){
        return "products/history";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id, Model model){
        Product product = productsService.findOne(id);
        model.addAttribute("product", product);
        return "/products/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        productsService.delete(id);
        return "redirect:/products/goods";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id")int id, Model model){
        model.addAttribute("product", productsService.findOne(id));
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@PathVariable("id")int id, @ModelAttribute("product") Product product){
        productsService.update(id, product);
        return "redirect:/products/" + id;
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query){
        model.addAttribute("goods", productsService.searchByName(query));
        return "products/search";
    }

    @GetMapping("/search")
    public String searchPage(Model model){
        model.addAttribute("goods", productsService.findAll());
        return "products/search";
    }

    @GetMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "products/new";
    }

    @PostMapping
    public String create(@ModelAttribute("product") Product product){
        productsService.save(product);
        return "redirect:/products/goods";
    }

}
