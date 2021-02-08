package pl.karnecki.carrest.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.karnecki.carrest.model.Car;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class CarRepository {

    public List<Car> createListOfCars() {

        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1L, "Toyota", "RAV4", "RED"));
        carList.add(new Car(2L, "BMW", "760Li", "BLACK"));
        carList.add(new Car(3L, "Audi", "A8", "BLUE"));
        carList.add(new Car(4L, "Tesla", "S", "WHITE"));
        carList.add(new Car(5L, "Porsche", "Panamera", "GOLD"));
        carList.add(new Car(6L, "Kia", "Proceed", "BLACK"));
        return carList;
    }

}

