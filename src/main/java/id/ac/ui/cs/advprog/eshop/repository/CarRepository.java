package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Repository
public class CarRepository {
    static int id = 0;
    private final List<Car> carData = new ArrayList<>();

    public Car create(final Car newCar) {
        carData.add(newCar);
        return newCar;
    }

    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    public Car findById(final String carId) {
        for (Car car : carData) {
            if (car.getCarId().equals(carId)) {
                return car;
            }
        }
        return null;
    }

    public Car update(final String carId, final Car updatedCar) {
        for (Car car : carData) {
            if (car.getCarId().equals(carId)) {
                car.setCarName(updatedCar.getCarName());
                car.setCarColor(updatedCar.getCarColor());
                car.setCarQuantity(updatedCar.getCarQuantity());
                return car;
            }
        }
        return null;
    }

    public void delete(final String carId) {
        carData.removeIf(car -> car.getCarId().equals(carId));
    }
}