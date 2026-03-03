package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CarRepositoryTest {

    @InjectMocks
    CarRepository carRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Mobil Cap Bambang");
        car.setCarColor("Hitam");
        car.setCarQuantity(100);
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car1.setCarName("Mobil Cap Bambang");
        car1.setCarColor("Hitam");
        car1.setCarQuantity(100);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        car2.setCarName("Mobil Cap Usep");
        car2.setCarColor("Biru");
        car2.setCarQuantity(50);
        carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car1.getCarId(), savedCar.getCarId());
        savedCar = carIterator.next();
        assertEquals(car2.getCarId(), savedCar.getCarId());
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Mobil Cap Bambang");
        car.setCarColor("Hitam");
        car.setCarQuantity(100);
        carRepository.create(car);

        Car foundCar = carRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(foundCar);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", foundCar.getCarId());
        assertEquals("Mobil Cap Bambang", foundCar.getCarName());
    }

    @Test
    void testFindByIdNotFound() {
        Car foundCar = carRepository.findById("nonexistent-id");
        assertNull(foundCar);
    }

    @Test
    void testUpdateCar() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Mobil Cap Bambang");
        car.setCarColor("Hitam");
        car.setCarQuantity(100);
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarName("Mobil Cap Bambang Edited");
        updatedCar.setCarColor("Hijau");
        updatedCar.setCarQuantity(200);

        Car result = carRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedCar);

        assertNotNull(result);
        assertEquals("Mobil Cap Bambang Edited", result.getCarName());
        assertEquals("Hijau", result.getCarColor());
        assertEquals(200, result.getCarQuantity());

        List<Car> carList = new ArrayList<>();
        Iterator<Car> carIterator = carRepository.findAll();
        carIterator.forEachRemaining(carList::add);
        Car savedCar = carList.stream().filter(c -> c.getCarId().equals("eb558e9f-1c39-460e-8860-71af6af63bd6")).toList().getFirst();

        assertNotNull(savedCar);
        assertEquals("Mobil Cap Bambang Edited", savedCar.getCarName());
        assertEquals("Hijau", savedCar.getCarColor());
        assertEquals(200, savedCar.getCarQuantity());
    }

    @Test
    void testUpdateCarNotFound() {
        Car updatedCar = new Car();
        updatedCar.setCarName("Mobil Cap Bambang Edited");
        updatedCar.setCarColor("Hijau");
        updatedCar.setCarQuantity(200);

        Car result = carRepository.update("nonexistent-id", updatedCar);
        assertNull(result);
    }

    @Test
    void testDeleteCar() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Mobil Cap Bambang");
        car.setCarColor("Hitam");
        car.setCarQuantity(100);
        carRepository.create(car);

        carRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        List<Car> carList = new ArrayList<>();
        Iterator<Car> carIterator = carRepository.findAll();
        carIterator.forEachRemaining(carList::add);
        assertThrows(NoSuchElementException.class, () -> carList.stream().filter(c -> c.getCarId().equals("eb558e9f-1c39-460e-8860-71af6af63bd6")).toList().getFirst());
    }

    @Test
    void testDeleteCarNotFound() {
        Car car1 = new Car();
        car1.setCarId("temp. id");
        car1.setCarName("Mobil Cap Bambang");
        car1.setCarColor("Hitam");
        car1.setCarQuantity(100);
        carRepository.create(car1);

        carRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        List<Car> carList = new ArrayList<>();
        Iterator<Car> carIterator = carRepository.findAll();
        carIterator.forEachRemaining(carList::add);
        Car car2 = carList.stream().filter(car -> car.getCarId().equals("temp. id")).toList().getFirst();
        assertNotNull(car2);
    }
}