package pl.karnecki.carrest.service;

import org.springframework.stereotype.Service;
import pl.karnecki.carrest.model.Car;
import pl.karnecki.carrest.repository.CarRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {


    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public List<Car> getAllCars() {
        return carRepository.createListOfCars();
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return carRepository.createListOfCars()
                .stream()
                .filter(car -> car.getCarId().equals(id))
                .findFirst();
    }

    @Override
    public List<Car> getCarsByColor(String color) {
        return carRepository.createListOfCars()
                .stream()
                .filter(car -> car.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Car> addCar(Car car) {
        boolean isCarOnList = carRepository.createListOfCars()
                .stream()
                .anyMatch(element -> element.getCarId().equals(car.getCarId()) && element.getMark().equals(car.getMark()) && element.getModel().equals(car.getModel()));
        return isCarOnList ? Optional.empty() : Optional.of(car);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.createListOfCars().remove(id);
    }
}
