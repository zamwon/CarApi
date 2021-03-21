package pl.karnecki.carrest.service;

import pl.karnecki.carrest.model.Car;

import java.util.List;


public interface CarService {


    List<Car> getAllCars();

    Car getCarById(Long id);

    List<Car> getCarsByColor(String color);

    boolean addCar(Car car);

    void deleteCar(Long id);

    void modifyCar(Car car);

    List<Car> getCarsByProductionYearRange(int year1, int year2);
}
