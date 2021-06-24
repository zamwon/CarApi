package pl.karnecki.pokemonapi;

import org.springframework.stereotype.Component;
import pl.karnecki.pokemonapi.dal.PokemonDao;


@Component
public class Start {

    PokemonDao pokemonDao;

    public Start(PokemonDao pokemonDao) {
        this.pokemonDao = pokemonDao;
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
