package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class JsonUtils {
    public static final String MAIN_NAME ="mainName";
    public static final String ALSO_KNOWN_AS ="alsoKnownAs";
    public static final String PLACE_OF_ORIGIN ="placeOfOrigin";
    public static final String DESCRIPTION ="description";
    public static final String IMAGE ="image";
    public static final String INGREDIENTS ="ingredients";


    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();

        try {

            // For  Main Name Field (String)
            JSONObject sandwichObject = new JSONObject(json);
            JSONObject mainName = sandwichObject.getJSONObject(MAIN_NAME);
            if (mainName == null) {
                return null;
            }
            sandwich.setMainName(mainName.optString(MAIN_NAME));

            // For  Also Known As Field (Array)
            JSONArray AlsoKnownAs = mainName.optJSONArray(ALSO_KNOWN_AS);
            if (AlsoKnownAs == null) {
                return null;
            }
            sandwich.setAlsoKnownAs(getStringsFromArrayJSON(AlsoKnownAs));

            // For Place of Origin
            JSONObject placeOfOrigin = sandwichObject.getJSONObject(PLACE_OF_ORIGIN);
            if (placeOfOrigin == null) {
                return null;
            }
            sandwich.setPlaceOfOrigin(placeOfOrigin.optString(PLACE_OF_ORIGIN));

            // For Description
            JSONObject Description = sandwichObject.getJSONObject(DESCRIPTION);
            if (Description == null) {
                return null;
            }
            sandwich.setDescription(Description.optString(DESCRIPTION));

            //For Image
            JSONObject Image = sandwichObject.getJSONObject(IMAGE);
            if (Image == null) {
                return null;
            }
            sandwich.setImage(Image.optString(IMAGE));

            //For Ingredients (Array)
            JSONArray Ingredients = mainName.optJSONArray(INGREDIENTS);
            if (Ingredients == null) {
                return null;
            }
            sandwich.setIngredients(getStringsFromArrayJSON(Ingredients));



        } catch (JSONException e) {
            e.printStackTrace();
        }


        return sandwich;
    }

    private static List<String> getStringsFromArrayJSON(JSONArray ArrayFromJSON) {
        List<String> ArrayDetails =new ArrayList<>();

        if (ArrayFromJSON != null) {
            for (int i = 0; i < ArrayFromJSON.length()-1 ; i++) {

                String arrayString = null;
                try {
                    arrayString = ArrayFromJSON.getString(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ArrayDetails.add(arrayString);

            }

        }

        return ArrayDetails;
    }
}
