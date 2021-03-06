package pl.karnecki.carrest.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.karnecki.carrest.model.Car;
import pl.karnecki.carrest.service.CarServiceImpl;
import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/cars")
public class CarController {


    private final CarServiceImpl carService;

    @Autowired
    public CarController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Car>> getAllCars() {
        log.info("Returned list of all cars");
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Car>> getCarById(@PathVariable Long id) {
        Optional<Car> first = carService.getCarById(id);
        if (first.isPresent()) {
            log.info("Found car with id: " + id);
            return new ResponseEntity<>(first, HttpStatus.OK);
        }
        log.info("Car with id: " + id + ", not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getCarByColor(@PathVariable String color) {

        List<Car> first = carService.getCarsByColor(color);
        if (!first.isEmpty()) {
            if (first.size() == 1) {
                log.info("Found: " + first.size() + " car with color: " + color);
            } else {
                log.info("Found: " + first.size() + " cars with color: " + color);
            }
            return new ResponseEntity<>(first, HttpStatus.OK);
        }
        log.info("Cars with color: \"" + color + "\" - not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addCar(@Validated @RequestBody Car car) {
        boolean isAdded = carService.addCar(car);
        if (isAdded) {
            log.info("New car: " + car + " created");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        log.info("Adding car unsuccessful");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> modifyCar(@RequestBody Car car) {
        if (car.getCarId() != null) {
            carService.modifyCar(car);
            log.info("Car modification successful");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        log.info("Car not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> modifyCarField(@PathVariable Long id, @RequestBody Car car) {
        Optional<Car> oldCar = carService.getCarById(id);
        if (oldCar.isEmpty()) {
            log.info("Car not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Car carToUpdate = oldCar.get();

        if (car.getMark() != null) {
            carToUpdate.setMark(car.getMark());
            log.info("Car mark modification successful");
        }
        if (car.getModel() != null) {
            carToUpdate.setModel(car.getModel());
            log.info("Car model modification successful");
        }
        if (car.getColor() != null) {
            carToUpdate.setColor(car.getColor());
            log.info("Car color modification successful");
        }
        carService.modifyCar(carToUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
