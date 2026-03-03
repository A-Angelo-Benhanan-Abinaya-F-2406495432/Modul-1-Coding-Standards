package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    Car car;

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarServiceImpl carService;

    @BeforeEach
    void setUp() {
        this.car = new Car();
        this.car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.car.setCarName("Mobil Cap Bambang");
        this.car.setCarColor("Hitam");
        this.car.setCarQuantity(100);
    }

    @Test
    void testCreate() {
        when(carRepository.create(this.car)).thenReturn(this.car);
        Car result = carService.create(this.car);

        assertEquals(this.car, result);
        verify(carRepository, times(1)).create(this.car);
    }

    @Test
    void testFindAll() {
        when(carRepository.findAll()).thenReturn(List.of(this.car).iterator());
        List<Car> result = carService.findAll();

        assertEquals(1, result.size());
        assertEquals(this.car, result.getFirst());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindAllEmpty() {
        when(carRepository.findAll()).thenReturn(Collections.emptyIterator());
        List<Car> result = carService.findAll();

        assertTrue(result.isEmpty());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(carRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(this.car);
        Car result = carService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertEquals(this.car, result);
        verify(carRepository, times(1)).findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }

    @Test
    void testFindByIdNotFound() {
        when(carRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(null);
        Car result = carService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertNull(result);
        verify(carRepository, times(1)).findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }

    @Test
    void testUpdate() {
        Car updatedCar = new Car();
        updatedCar.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedCar.setCarName("Mobil Cap Bambang Edited");
        updatedCar.setCarColor("Hijau");
        updatedCar.setCarQuantity(200);

        carService.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedCar);

        verify(carRepository, times(1)).update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedCar);
    }

    @Test
    void testDeleteCarById() {
        doNothing().when(carRepository).delete(this.car.getCarId());
        carService.deleteCarById(this.car.getCarId());
        verify(carRepository, times(1)).delete(this.car.getCarId());
    }
}