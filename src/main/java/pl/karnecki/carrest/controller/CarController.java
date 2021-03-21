package pl.karnecki.carrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.karnecki.carrest.model.Car;
import pl.karnecki.carrest.service.CarServiceImpl;
import java.util.List;


@RestController
@RequestMapping("/cars")
public class CarController {


    private final CarServiceImpl carService;

    @Autowired
    public CarController(CarServiceImpl carService) {
        this.carService = carService;

    }

    @GetMapping()
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car first = carService.getCarById(id);
        if (first != null) {
            return new ResponseEntity<>(first, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getCarByColor(@PathVariable String color) {

        List<Car> first = carService.getCarsByColor(color);
        if (!first.isEmpty()) {

            return new ResponseEntity<>(first, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/since/{year}/to/{year2}")
    public ResponseEntity<List<Car>> getCarInRange(@PathVariable int year, @PathVariable int year2) {

        List<Car> first = carService.getCarsByProductionYearRange(year, year2);
        if (!first.isEmpty()) {
            return new ResponseEntity<>(first, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addCar(@Validated @RequestBody Car car) {
        boolean isAdded = carService.addCar(car);
        if (isAdded) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> modifyCar(@RequestBody Car car) {
        if (car.getCarId() != null) {
            carService.modifyCar(car);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> modifyCarField(@PathVariable Long id, @RequestBody Car car) {
        Car oldCar = carService.getCarById(id);
        if (oldCar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (car.getMark() != null) {
            oldCar.setMark(car.getMark());
        }
        if (car.getModel() != null) {
            oldCar.setModel(car.getModel());
        }
        if (car.getColor() != null) {
            oldCar.setColor(car.getColor());
        }
        if (car.getProductionYear() != 0){
            oldCar.setProductionYear(car.getProductionYear());
        }
        carService.modifyCar(oldCar);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
