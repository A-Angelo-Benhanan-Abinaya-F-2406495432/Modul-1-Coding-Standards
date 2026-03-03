package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {
    Car car;

    @Mock
    Model model;

    @Mock
    CarService carService;

    @InjectMocks
    CarController carController;

    @BeforeEach
    void setUp() {
        this.car = new Car();
        this.car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.car.setCarName("Mobil Cap Bambang");
        this.car.setCarColor("Hitam");
        this.car.setCarQuantity(100);
    }

    @Test
    void testCreateCarPage_GET() {
        String view = carController.createCarPage(model);

        assertEquals("createCar", view);
        verify(model, times(1)).addAttribute(eq("car"), any(Car.class));
    }

    @Test
    void testCreateCarPage_POST() {
        when(carService.create(car)).thenReturn(car);
        String view = carController.createCarPost(car);

        assertEquals("redirect:listCar", view);
        verify(carService, times(1)).create(car);
    }

    @Test
    void testCarListPage_GET() {
        List<Car> carList = List.of(car);
        when(carService.findAll()).thenReturn(carList);
        String view = carController.carListPage(model);

        assertEquals("carList", view);
        verify(model, times(1)).addAttribute("cars", carList);
    }

    @Test
    void testCarListPage_NoCar() {
        List<Car> carList = List.of();
        when(carService.findAll()).thenReturn(carList);
        String view = carController.carListPage(model);

        assertEquals("carList", view);
        verify(model, times(1)).addAttribute("cars", carList);
    }

    @Test
    void testEditCarPage_GET() {
        when(carService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(car);
        String view = carController.editCarPage("eb558e9f-1c39-460e-8860-71af6af63bd6", model);

        assertEquals("editCar", view);
        verify(model, times(1)).addAttribute("car", car);
    }

    @Test
    void testEditCarPage_CarNotFound() {
        when(carService.findById("nonexistent-id")).thenReturn(null);
        String view = carController.editCarPage("nonexistent-id", model);

        assertEquals("editCar", view);
        verify(model, times(1)).addAttribute("car", null);
    }

    @Test
    void testEditCarPage_POST() {
        Car updatedCar = new Car();
        updatedCar.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedCar.setCarName("Mobil Cap Bambang Edited");
        updatedCar.setCarColor("Hijau");
        updatedCar.setCarQuantity(200);

        String view = carController.editCarPost(updatedCar);

        assertEquals("redirect:listCar", view);
        verify(carService, times(1)).update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedCar);
    }

    @Test
    void testDeleteCar_POST() {
        doNothing().when(carService).deleteCarById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        String view = carController.deleteCar("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertEquals("redirect:listCar", view);
        verify(carService, times(1)).deleteCarById("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }
}