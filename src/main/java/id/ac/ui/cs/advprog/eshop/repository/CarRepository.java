package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@NoArgsConstructor
@Repository
public class CarRepository implements CarRepositoryInterface {
    @SuppressWarnings({"PMD.UnusedLocalVariable", "PMD.ShortVariable"})
    private static int id = 0;
    private final List<Car> carData = new ArrayList<>();

    @Override
    public Car create(final Car newCar) {
        carData.add(newCar);
        return newCar;
    }

    @Override
    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    @Override
    public Car findById(final String carId) {
        Car carToFind = null;
        for (final Car car : carData) {
            if (car.getCarId().equals(carId)) {
                carToFind = car;
            }
        }
        return carToFind;
    }

    @Override
    public Car update(final String carId, final Car updatedCar) {
        Car carToUpdate = null;
        for (final Car car : carData) {
            if (car.getCarId().equals(carId)) {
                car.setCarName(updatedCar.getCarName());
                car.setCarColor(updatedCar.getCarColor());
                car.setCarQuantity(updatedCar.getCarQuantity());
                carToUpdate = car;
            }
        }
        return carToUpdate;
    }

    @Override
    public void delete(final String carId) {
        carData.removeIf(car -> car.getCarId().equals(carId));
    }
}