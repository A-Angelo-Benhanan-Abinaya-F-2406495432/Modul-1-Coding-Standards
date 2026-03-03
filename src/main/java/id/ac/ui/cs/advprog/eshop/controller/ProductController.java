package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarService;
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
        return "CreateProduct";
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
        return "ProductList";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(final @PathVariable String productId, final Model model) {
        final List<Product> allProducts = service.findAll();
        final Product productToEdit = allProducts.stream().filter(product -> product.getProductId().equals(productId)).findFirst().orElse(null);
        model.addAttribute("product", productToEdit);
        return "EditProduct";
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

@NoArgsConstructor
@Controller
@RequestMapping("/car")
class CarController extends ProductController {
    @Autowired
    private CarService carservice;

    @GetMapping("/createCar")
    public String createCarPage(final Model model) {
        final Car newCar = new Car();
        model.addAttribute("car", newCar);
        return "createCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(final @ModelAttribute Car newCar) {
        carservice.create(newCar);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String carListPage(final Model model) {
        final List<Car> allCars = carservice.findAll();
        model.addAttribute("cars", allCars);
        return "carList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(final @PathVariable String carId, final Model model) {
        final Car editedCar = carservice.findById(carId);
        model.addAttribute("car", editedCar);
        return "editCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(final @ModelAttribute Car car) {
        carservice.update(car.getCarId(), car);
        return "redirect:listCar";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(final @RequestParam("carId") String carId) {
        carservice.deleteCarById(carId);
        return "redirect:listCar";
    }
}