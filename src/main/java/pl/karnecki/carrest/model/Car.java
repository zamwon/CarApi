package pl.karnecki.carrest.model;

import javax.validation.constraints.NotNull;
import java.util.Objects;


public class Car{

    @NotNull(message = "id cannot be null")
    Long carId;
    String mark;
    String model;
    String color;

    public Car(Long carId, String mark, String model, String color) {
        this.carId = carId;
        this.mark = mark;
        this.model = model;
        this.color = color;
    }

    public Car() {
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + carId +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) && Objects.equals(mark, car.mark) && Objects.equals(model, car.model) && Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, mark, model, color);
    }
}
