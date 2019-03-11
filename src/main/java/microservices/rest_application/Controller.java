package microservices.rest_application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class Controller {
    private CarsModel model = new CarsModel();

    @GetMapping("/cars")
    public List<Car> getCars() {
        return model.getCars();
    }

    @PostMapping("/cars/add")
    public boolean add(@RequestBody String body) {
        Gson gson = new Gson();
        Car car = gson.fromJson(body, Car.class);
        if(model.isExist(car.getLicensenumber())){
            return false;
        } else {
            model.addCar(car);
            return true;
        }
    }

    @PostMapping("/cars/delete")
    public boolean delete(@RequestBody String body) {
        body = body.toUpperCase();
        System.out.println(body);

        if(model.isExist(body)){
            model.deleteCar(body);
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/find/{find}")
    public List<Car> find(@PathVariable String find) {
        find = find.replaceAll("_", " ");
        return model.find(find);
    }
}
