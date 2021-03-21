package pl.karnecki.carrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karnecki.carrest.dal.CarDao;
import pl.karnecki.carrest.model.Car;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {



    private final CarDao carDao;

    @Autowired
    public CarServiceImpl( CarDao carDao) {
        this.carDao = carDao;
    }


    @Override
    public List<Car> getAllCars() {
        return carDao.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        return carDao.getCarById(id);
    }

    @Override
    public List<Car> getCarsByColor(String color) {
        return carDao.findByColor(color)
                .stream()
                .filter(car -> car.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addCar(Car car) {
        boolean isCarOnList = carDao.findAll()
                .stream()
                .anyMatch(element ->
                        element.getCarId().equals(car.getCarId()));
        if (isCarOnList) return false;
        carDao.saveCar(car.getCarId(), car.getMark(), car.getModel(), car.getColor(), car.getProductionYear());
        return true;
    }

    @Override
    public void deleteCar(Long id) {
        carDao.deleteCar(id);
    }

    @Override
    public void modifyCar(Car car) {
        boolean isCarOnList = carDao.findAll()
                .stream()
                .anyMatch(element ->
                        element.getCarId().equals(car.getCarId()));
        if (isCarOnList) {
            carDao.updateCar(car);
        }
    }

    @Override
    public List<Car> getCarsByProductionYearRange(int year1, int year2) {
        return carDao.findByProductionYearRange(year1, year2).
                stream()
                .filter(car -> car.getProductionYear() >= year1 && car.getProductionYear() <= year2).collect(Collectors.toList());
    }
}
