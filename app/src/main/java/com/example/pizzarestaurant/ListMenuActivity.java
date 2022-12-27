package com.example.pizzarestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListMenuActivity extends AppCompatActivity {
    private TextView textResult;

    ListView simpleList;
    ArrayList<String> foodNames = new ArrayList<>();
    ArrayList<String> foodDetails = new ArrayList<>();
    int foodImages[] = {R.drawable.pizza, R.drawable.burger, R.drawable.steak, R.drawable.spageti};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);

        String url = "https://retoolapi.dev/";
        textResult =  (TextView)findViewById(R.id.test);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        textResult.setText("Wait ...");
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textResult.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                int j =0;
                for (Post post : posts){
                    if(j >= 4){
                        break;
                    }
                    foodNames.add(post.getFoodName());
                    foodDetails.add(post.getDetails());
                    j++;
                }
                String a = "";
                for (int i = 0; i < foodDetails.size(); i++) {
                    a = a + foodDetails.get(i) + ", \n";
                }
                textResult.setText("Finish \n" + posts.size() + ", " + a);
                textResult.setText(".  ." );
                simpleList = (ListView)findViewById(R.id.listview);
                CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), foodNames, foodDetails, foodImages);
                simpleList.setAdapter(customAdapter);

                FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);
                myFab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(ListMenuActivity.this, DetailActivity.class));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textResult.setText("Error :"+t.getMessage());
            }
        });


    }
}