package pl.karnecki.carrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.karnecki.carrest.model.Car;
import pl.karnecki.carrest.service.CarServiceImpl;
import java.util.List;


@Controller
@RequestMapping("/cars")
public class CarController {


    private final CarServiceImpl carService;

    @Autowired
    public CarController(CarServiceImpl carService) {
        this.carService = carService;

    }

    @GetMapping("/all")
    public String getAllCars(Model model) {
        List<Car> listOfCars = carService.getAllCars();
        model.addAttribute("listOfCars", listOfCars);
        model.addAttribute("car1", new Car());
        return "allCarsPage";
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

    @PostMapping("/add-car")
    public String addCar(@ModelAttribute Car car) {
        carService.addCar(car);
        return "redirect:/cars/all";
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
