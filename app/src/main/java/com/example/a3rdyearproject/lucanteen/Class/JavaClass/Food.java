package com.example.a3rdyearproject.lucanteen.Class.JavaClass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Adil on 5/3/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Food
{

    @JsonProperty("foodName")
    String foodName;
    @JsonProperty("foodPrice")
    String foodPrice;

    public Food()
    {

    }
    public Food(String foodName,String foodPrice)
    {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }
}
