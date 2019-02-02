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
    private static final int DEFAULT_POSITION =-1;

    TextView also_known_Detial;
    TextView origin_Detial;
    TextView ingredients_Detial;
    TextView description_Detial;
    ImageView image_Detial;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
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

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(image_Detial);

        setTitle(sandwich.getMainName());
    }



    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void populateUI(Sandwich sandwich) {

        also_known_Detial  = findViewById(R.id.also_known_tv);
        origin_Detial      = findViewById(R.id.origin_tv);
        ingredients_Detial = findViewById(R.id.ingredients_tv);
        description_Detial = findViewById(R.id.description_tv);
        image_Detial       = findViewById(R.id.image_iv);

        if (sandwich.getAlsoKnownAs()!=null){
            also_known_Detial.setText(sandwich.getAlsoKnownAs().toString());
        }
        if (sandwich.getPlaceOfOrigin()!=null){
            origin_Detial.setText(sandwich.getPlaceOfOrigin().toString());
        }
        if (sandwich.getIngredients()!=null){
            ingredients_Detial.setText(sandwich.getIngredients().toString());
        }
         if (sandwich.getDescription()!=null){
             description_Detial.setText(sandwich.getDescription().toString());
        }



    }
}
