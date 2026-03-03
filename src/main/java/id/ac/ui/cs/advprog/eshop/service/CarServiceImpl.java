package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepositoryInterface;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@NoArgsConstructor
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepositoryInterface carRepository;

    @Override
    public Car create(final Car newCar) {
        carRepository.create(newCar);
        return newCar;
    }

    @Override
    public List<Car> findAll() {
        final Iterator<Car> carIterator = carRepository.findAll();
        final List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }

    @Override
    public Car findById(final String carId) {
        return carRepository.findById(carId);
    }

    @Override
    public void update(final String carId, final Car updatedCar) {
        carRepository.update(carId, updatedCar);
    }

    @Override
    public void deleteCarById(final String carId) {
        carRepository.delete(carId);
    }
}