package pl.karnecki.carrest.dal;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.karnecki.carrest.model.Car;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CarDaoImpl implements CarDao {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //TO LUNCH 1st Create TABLE


//    @EventListener(ApplicationReadyEvent.class)
//    public void init(){
//        String sql = "CREATE TABLE cars (car_Id int, mark varchar(255), model varchar(255), color varchar(255), production_Year int)";
//        jdbcTemplate.update(sql);
//    }

    //THEN ADD CARS TO DB -> goTo Start.class


    @Override
    public void saveCar(Long id, String mark, String model, String color, int productionYear) {

        Car car = new Car(id, mark, model, color, productionYear);
        String sql = "INSERT INTO cars VALUES ( ?, ?, ?, ? , ?)";
        jdbcTemplate.update(sql, car.getCarId(), car.getMark(), car.getModel(), car.getColor(), car.getProductionYear());


    }

    @Override
    public List<Car> findAll() {
        String sql = "SELECT * from cars";
        List<Car> carList = new ArrayList<>();

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return getCarList(carList, maps);
    }

    @Override
    public void updateCar(Car newCar) {
        String sql = "UPDATE cars SET cars.mark = ? , cars.model = ?, cars.color = ?, cars.production_Year = ? WHERE cars.car_Id = ? ";
        jdbcTemplate.update(sql, newCar.getMark(), newCar.getModel(), newCar.getColor(), newCar.getProductionYear(), newCar.getCarId());
    }

    @Override
    public void deleteCar(Long id) {
        String sql = "DELETE FROM cars  WHERE car_Id = ? ";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public Car getCarById(Long id) {
        String sql = "SELECT * FROM cars WHERE car_Id = ? ";
        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> new Car(
                resultSet.getLong("car_Id"),
                resultSet.getString("mark"),
                resultSet.getString("model"),
                resultSet.getString("color"),
                resultSet.getInt("production_Year")), id);

    }

    @Override
    public List<Car> findByProductionYearRange(int fromYear, int untilYear) {
        String sql = "SELECT * FROM cars WHERE production_Year >= ? AND production_Year <= ?";
        List<Car> carList = new ArrayList<>();
        final List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, fromYear, untilYear);
        return getCarList(carList, maps);
    }

    @Override
    public List<Car> findByColor(String color) {
        String sql = "SELECT * FROM cars WHERE color = ?";
        List<Car> carList = new ArrayList<>();
        final List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, color);
        return getCarList(carList, maps);

    }

    private List<Car> getCarList(List<Car> carList, List<Map<String, Object>> maps) {
        maps.stream()
                .forEach(element -> carList.add(new Car(Long.parseLong(String.valueOf(element.get("car_Id"))),
                        String.valueOf(element.get("mark")),
                        String.valueOf(element.get("model")),
                        String.valueOf(element.get("color")),
                        Integer.parseInt(String.valueOf(element.get("production_Year"))))));
        return carList;
    }
}
