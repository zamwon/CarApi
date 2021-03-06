package pl.karnecki.carrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karnecki.carrest.model.Car;
import pl.karnecki.carrest.repository.CarRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {


    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public List<Car> getAllCars() {
        return carRepository.getCarList();
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return carRepository.getCarList()
                .stream()
                .filter(car -> car.getCarId().equals(id))
                .findFirst();
    }

    @Override
    public List<Car> getCarsByColor(String color) {
        return carRepository.getCarList()
                .stream()
                .filter(car -> car.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addCar(Car car) {
        boolean isCarOnList = carRepository.getCarList()
                .stream()
                .anyMatch(element ->
                        element.getCarId().equals(car.getCarId()));
        if (isCarOnList) return false;
        carRepository.addCarToList(car);
        return true;
    }

    @Override
    public boolean deleteCar(Long id) {
        return carRepository.removeCarFromList(id);
    }

    @Override
    public boolean modifyCar(Car car) {
        boolean isCarOnList = carRepository.getCarList()
                .stream()
                .anyMatch(element ->
                        element.getCarId().equals(car.getCarId()));
        if (isCarOnList) {
            carRepository.modify(car);
            return true;
        } else
            return false;
    }
}
