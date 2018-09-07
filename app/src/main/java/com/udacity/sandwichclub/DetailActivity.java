package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView also_known_Detial;
    TextView origin_Detial;
    TextView ingredients_Detial;
    TextView description_Detial;

    Sandwich sandwich;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        RUNDetailsIntent();
        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }
    private void RUNDetailsIntent() {
        also_known_Detial  = findViewById(R.id.also_known_tv);
        origin_Detial      = findViewById(R.id.origin_tv);
        ingredients_Detial = findViewById(R.id.ingredients_tv);
        description_Detial = findViewById(R.id.description_tv);

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }


    private void populateUI() {

        if (sandwich.getAlsoKnownAs().isEmpty() != true ){

            also_known_Detial.setText(TextUtils.join("",sandwich.getAlsoKnownAs()));

        }else {
            also_known_Detial.setText("Null");
        }

        if (sandwich.getPlaceOfOrigin().isEmpty()){

            origin_Detial .setText("Null");
        }else {

            origin_Detial.setText(sandwich.getPlaceOfOrigin());

        }


        if ( sandwich.getIngredients().isEmpty() != true){
            ingredients_Detial.setText(TextUtils.join("",sandwich.getIngredients()));

        }else {
            ingredients_Detial.setText("Null");
        }

        if (sandwich.getDescription().isEmpty() != true){

            description_Detial.setText(sandwich.getDescription());
        }else {
            description_Detial.setText("Null");

        }

    }
}
