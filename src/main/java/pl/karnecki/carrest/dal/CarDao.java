package pl.karnecki.carrest.dal;

import pl.karnecki.carrest.model.Car;

import java.util.List;


public interface CarDao {

    void saveCar(Long id, String mark, String model, String color, int productionYear);
    List<Car> findAll();
    void updateCar(Car car);
    void deleteCar(Long id);
    Car getCarById(Long id);
    List<Car> findByProductionYearRange(int fromYear, int untilYear);
    List<Car> findByColor(String color);
}
