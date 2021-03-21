package pl.karnecki.carrest;

import org.springframework.stereotype.Component;
import pl.karnecki.carrest.dal.CarDao;


@Component
public class Start {

    CarDao carDao;

    public Start(CarDao carDao) {
        this.carDao = carDao;
//
//        carDao.saveCar(1L, "Toyota", "RAV4", "RED", 2015);
//        carDao.saveCar(2L, "BMW", "760Li", "BLACK", 2021);
//        carDao.saveCar(3L, "Audi", "A8", "BLUE", 2020);
//        carDao.saveCar(4L, "Tesla", "S", "WHITE",2021);
//        carDao.saveCar(5L, "Porsche", "Panamera", "GOLD", 2019);
//        carDao.saveCar(6L, "Kia", "Proceed", "BLACK", 2018);
//
//        carDao.findByProductionYearRange(2015, 2019).forEach(System.out::println);

//        carDao.findAll().forEach(System.out::println);
    }
}
