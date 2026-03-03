package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.List;

public interface CarService {
    Car create(final Car newCar);
    List<Car> findAll();
    Car findById(String carId);
    void update(String carId, Car updatedCar);
    void deleteCarById(String carId);
}