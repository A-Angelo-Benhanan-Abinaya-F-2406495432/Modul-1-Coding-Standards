package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(final Model model) {
        final Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(final @ModelAttribute Product product) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(final Model model) {
        final List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(final @PathVariable String productId, final Model model) {
        final List<Product> allProducts = service.findAll();
        final Product productToEdit = allProducts.stream().filter(product -> product.getProductId().equals(productId)).findFirst().orElse(null);
        model.addAttribute("product", productToEdit);
        return "editProduct";
    }

    @PostMapping("/edit/{productId}")
    public String editProductPost(final @PathVariable String productId, final @ModelAttribute Product newProduct) {
        service.edit(productId, newProduct);
        return "redirect:/product/list";
    }

    @PostMapping("/delete/{productId}")
    public String deleteProductPost(final @PathVariable String productId) {
        service.delete(productId);
        return "redirect:/product/list";
    }
}
