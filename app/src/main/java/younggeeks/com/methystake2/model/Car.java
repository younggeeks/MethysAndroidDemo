package younggeeks.com.methystake2.model;

import android.content.Context;
import android.util.Log;
import junit.framework.Assert;

/**
 * Created by Samjunior on 22/05/16.
 * Defining data model (Car )
 * For Demonstration purpose the data will be hardcoded
 */
public class Car {

    /**
     *Defining Car Attributes
     */

    private int id;
    private String manufacturer;
    private String model;
    private String year;
    private String color;
    private String topSpeed;
    private String type;
    private String category;
    private String fuel;
    private String wheels;
    private String transmission;

    /**
     * Samwel Charles
     * Defining constructor
     *
     * @param id
     * @param manufacturer
     * @param model
     * @param year
     * @param color
     * @param topSpeed
     * @param type
     * @param category
     * @param fuel
     * @param wheels
     * @param transmission
     */

    public Car(int id, String manufacturer, String model, String year, String color, String topSpeed, String type, String category, String fuel, String wheels, String transmission) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.color = color;
        this.topSpeed = topSpeed;
        this.type = type;
        this.category = category;
        this.fuel = fuel;
        this.wheels = wheels;
        this.transmission = transmission;
    }

    /**
     *
     * The following are getters and setters of the properties above
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(String topSpeed) {
        this.topSpeed = topSpeed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getWheels() {
        return wheels;
    }

    public void setWheels(String wheels) {
        this.wheels = wheels;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public  int getDrawable(Context context, String name)
    {
        Assert.assertNotNull(context);
        Assert.assertNotNull(name);

        return context.getResources().getIdentifier(name.toLowerCase(),
                "drawable", context.getPackageName());
    }
}
