package com.example.myapplication;




import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



public class DetailActivity extends AppCompatActivity {

    Context context;
    TextView catName;
    ImageView catImage;
    ImageButton heartBtn;
    TextView description;
    TextView origin;
    TextView temper;
    TextView lifespan;
    TextView dogFriend;
    TextView weight;
    TextView wikiLink;

    Cat currentCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        context = this;
        catName = findViewById(R.id.catName);
        catImage = findViewById(R.id.catImage);
        heartBtn = findViewById(R.id.heartBtn);
        origin = findViewById(R.id.origin);
        temper = findViewById(R.id.temper);
        description = findViewById(R.id.catDescription);
        lifespan = findViewById(R.id.lifeSpan);
        dogFriend = findViewById(R.id.dogF);
        weight = findViewById(R.id.weight);
        wikiLink = findViewById(R.id.wikiLink);


        Intent intent = getIntent();
        final String intentDetails = intent.getStringExtra("catID");

        //Request Response from API - 71ebcdc4-00d9-4fee-aced-bfbf7e597065
        String apikey = "71ebcdc4-00d9-4fee-aced-bfbf7e597065";
        String url = "https://api.thecatapi.com/v1/images/search?api_key="+apikey+"&breed_id="+intentDetails;
        final RequestQueue requestQueue = Volley.newRequestQueue(this);


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);

                Gson gson = new Gson();
                Type collectionType = new TypeToken<Collection<CatAPI>>(){}.getType();
                ArrayList<CatAPI> catAPI =gson.fromJson(response, collectionType);
                requestQueue.stop();

                CatAPI currentApi = catAPI.get(0);
                currentCat = currentApi.getBreeds().get(0);

                //Button for favourites
                if (FavouriteCatDatabase.favouriteCatsMap.containsKey(intentDetails)){
                    heartBtn.setImageResource(R.drawable.heart_too_full);
                    heartBtn.setTag(true);
                } else {
                    heartBtn.setImageResource(R.drawable.heart_too_empty);
                    heartBtn.setTag(false);
                }

                catName.setText(currentCat.getName());
                Glide.with(context).load(currentApi.getUrl()).into(catImage);
                description.setText(currentCat.getDescription());
                origin.setText("             "+currentCat.getOrigin());
                temper.setText("                            "+currentCat.getTemperament());
                dogFriend.setText("                                  "+currentCat.getDog_friendly());
                wikiLink.setText("                     \n"+currentCat.getWikipedia_url());
                lifespan.setText("                   "+currentCat.getLife_span());
                weight.setText("                "+currentCat.getWeight().getMetric());
            }
        };


        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }

    public void onToggleFavourite(View view){
        if (heartBtn.getTag().equals(true)){
            FavouriteCatDatabase.removeFromFavourite(currentCat);
            heartBtn.setTag(false);
            heartBtn.setImageResource(R.drawable.heart_too_empty);
        } else {
            FavouriteCatDatabase.addToFavourites(currentCat);
            heartBtn.setTag(true);
            heartBtn.setImageResource(R.drawable.heart_too_full);
        }

    }



}
