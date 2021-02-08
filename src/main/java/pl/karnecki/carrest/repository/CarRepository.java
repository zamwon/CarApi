package pl.karnecki.carrest.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.karnecki.carrest.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class CarRepository {

    List<Car> carList;

    public CarRepository() {
        this.carList = new ArrayList<>();
        carList.add(new Car(1L, "Toyota", "RAV4", "RED"));
        carList.add(new Car(2L, "BMW", "760Li", "BLACK"));
        carList.add(new Car(3L, "Audi", "A8", "BLUE"));
        carList.add(new Car(4L, "Tesla", "S", "WHITE"));
        carList.add(new Car(5L, "Porsche", "Panamera", "GOLD"));
        carList.add(new Car(6L, "Kia", "Proceed", "BLACK"));
    }

    public List<Car> getCarList() {
        return carList;
    }

    public boolean addCarToList(Car car) {
        Optional<Car> carOptional = carList.stream()
                .filter(oldCar -> oldCar.getCarId().equals(car.getCarId()))
                .findFirst();

        if (carOptional.isPresent())
            return false;

        return carList.add(car);
    }

    public boolean removeCarFromList(Long id) {
        Optional<Car> carOptional = carList.stream()
                .filter(oldCar -> oldCar.getCarId().equals(id))
                .findFirst();

        if (carOptional.isPresent()) {
            carList.remove(id);
            return true;
        }
        return false;
    }
}
