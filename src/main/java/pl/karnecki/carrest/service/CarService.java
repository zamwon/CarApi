package pl.karnecki.carrest.service;

import pl.karnecki.carrest.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {


    List<Car> getAllCars();

    Optional<Car> getCarById(Long id);

    List<Car> getCarsByColor(String color);

    Optional<Car> addCar(Car car);

    void deleteCar(Long id);
}
