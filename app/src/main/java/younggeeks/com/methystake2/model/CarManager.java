package younggeeks.com.methystake2.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Samjunior on 22/05/16.
 * Defined singleton which will be acting as datasource
 *
 */
public class CarManager {


    //our CarManager static instance
    private static CarManager ourInstance = new CarManager();

    //list of  Car Object
    private static   List<Car> cars;


    //Hashmap will be responsible for mapping id into the car object when showing the details
    public  Map<Integer,Car>  carMap=new HashMap<>();


    //getter method for Ourinstance
    public static CarManager getInstance() {
        return ourInstance;
    }

    //private constructor
    private CarManager() {
    }


    /**
     * Samwel Charles
     *
     * This method is returning data which will be used in both Recyclerview and details page
     *
     * I also added for loop for mapping Car object with id - for retrieval purpose when viewing car details
     *
     * @return List<Car>
     */
    public List<Car> getCars(){
       cars=new ArrayList<>();

        cars.add(new Car(2,"Nissan","Serena","1990","Black","300 Km/H","Car","SUV","Electric","4 Wheels","Automatic"));
        cars.add(new Car(3,"Isuzu","Pickup","2000","Red","140 Km/H","Car","Sedan","Gas","4 Wheels","Manual"));
        cars.add(new Car(4,"Canter","Pickup","1980","Blue","80 Km/H","Truck","SUV","Hybrid","2 Wheels","Automatic"));
        cars.add(new Car(5,"Mazda","TopUp","1980","Black","120 Km/H","Car","Hatchback","Natural Gas","4 Wheels","Automatic"));
        cars.add(new Car(9,"Range","Rover","2010","Black","140 Km/H","Minivan"," Cargo Minivan","Electric","4 Wheels","Automatic"));
        cars.add(new Car(6,"Corola","Avalon","2000","Blue","340 Km/H","Minivan"," Cargo Minivan","Electric","4 Wheels","Automatic"));
        cars.add(new Car(7,"Toyota","Axio","1990","Green","200 Km/H","car"," SUV","Hybrid","4 Wheels","Automatic"));


        for (Car car:cars){
            carMap.put(car.getId(),car);
        }

        return cars;
    }

    /**
     *Samwel Charles
     *
     *Getter method for instance variable Hashmap which will be used in mapping Car object to Id
     * @return
     */
    public Map<Integer, Car> getCarMap() {
        return carMap;
    }
}
