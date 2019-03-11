package microservices.rest_application;

import java.util.ArrayList;
import java.util.List;

public class CarsModel {
    private List<Car> cars = new ArrayList<>();
    private DBHelper db = new DBHelper();

    public List<Car> getCars(){
        populateCars();
        return cars;
    }

    public void populateCars(){
        cars.clear();
        for(Car car : db.selectAll()){
            cars.add(car);
        }
    }

    public void addCar(Car car) {
        db.insert(car.getLicensenumber().toUpperCase(),
                car.getMake().substring(0, 1).toUpperCase() + car.getMake().substring(1),
                car.getColor().substring(0, 1).toUpperCase() + car.getColor().substring(1));
    }

    public boolean isExist(String licensenumber) {
        if(db.isExist(licensenumber.toUpperCase())){
            return true;
        }
        return false;
    }

    public void deleteCar(String licensenumber) {
        db.delete(licensenumber);
    }

    public List<Car> find(String find) {
        return db.find(find);
    }
}

