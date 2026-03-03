package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@Controller
@RequestMapping("/car")
class CarController {
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
