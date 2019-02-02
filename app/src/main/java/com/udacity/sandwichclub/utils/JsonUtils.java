package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class JsonUtils {


    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();

        try {

            // For  Main Name Field (String)
            JSONObject sandwichObject = new JSONObject(json);
            String MAIN_NAME = sandwichObject.getJSONObject("name").getString("mainName");
            sandwich.setMainName(MAIN_NAME);

            // For  Also Known As Field (Array)
            JSONArray AlsoKnownAs = sandwichObject.getJSONObject("name").getJSONArray("alsoKnownAs");
            if (AlsoKnownAs == null) {
                return null;
            } else {
                List<String> ArrayDetails = new ArrayList<>();
                for (int i = 0; i < AlsoKnownAs.length(); i++) {
                    ArrayDetails.add(String.valueOf(AlsoKnownAs.get(i)));
                    sandwich.setAlsoKnownAs(ArrayDetails);
                }

//            // For Place of Origin
                String Place_of_Origin = sandwichObject.getString("placeOfOrigin");
                sandwich.setPlaceOfOrigin(Place_of_Origin);

//            // For Description
                String Description = sandwichObject.getString("description");
                sandwich.setDescription(Description);

              // For Image
                String Image = sandwichObject.getString("image");
                sandwich.setImage(Image);


//            //For Ingredients (Array)
                JSONArray Ingredients = sandwichObject.getJSONArray("ingredients");
                if (Ingredients == null) {
                    return null;
                } else {
                    List<String> IngredientsDetails = new ArrayList<>();
                    for (int i = 0; i < Ingredients.length(); i++) {
                        IngredientsDetails.add(String.valueOf(Ingredients.get(i)));
                        sandwich.setIngredients(IngredientsDetails);
                    }

                }

            }




        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}