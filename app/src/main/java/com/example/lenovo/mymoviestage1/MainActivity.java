package com.example.lenovo.mymoviestage1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MystateView recyclerViewmain;
    RequestQueue requestQueue;
    ArrayList<MovieList> movieArrayList;
    String myImageUrl="https://image.tmdb.org/t/p/w500";
    SharedPreferences mysharedPreference;
    SharedPreferences.Editor myeditor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewmain = findViewById(R.id.mainrec);
        requestQueue = Volley.newRequestQueue(this);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("NO INTERNET");
            builder.setTitle("WARNING");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();

                }
            });
            builder.show();
            //network connection

        }
        else{
            mysharedPreference = getPreferences(MODE_PRIVATE);
            if (mysharedPreference.getString("mykey", null) != null) {
                if (mysharedPreference.getString("mykey", null).equalsIgnoreCase("popular")) {
                    myPopularmovies();
                } else if (mysharedPreference.getString("mykey", null).equalsIgnoreCase("top_rated")) {
                    mytopratedmovies();
                } else {
                    myPopularmovies();
                }


            } else {
                myPopularmovies();
            }

            }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.mytoprated:
                myeditor=mysharedPreference.edit();
                myeditor.putString("mykey","top_rated");
                myeditor.apply();
                mytopratedmovies();
                break;

            case R.id.mypopular:
                myeditor=mysharedPreference.edit();
                myeditor.putString("mykey","popular");
                myeditor.apply();
                myPopularmovies();

                break;

        }

        return super.onOptionsItemSelected(item);

    }


    public void mytopratedmovies(){

        String topurl="https://api.themoviedb.org/3/movie/top_rated?api_key=212231b4217b91496d026aea43b242b0";

        final StringRequest stringRequest=new StringRequest(Request.Method.GET, topurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    movieArrayList=new ArrayList<>();
                    JSONObject jsonObjectroot=new JSONObject(response);
                    JSONArray myresults=jsonObjectroot.getJSONArray("results");

                    for (int i=0;i<myresults.length();i++) {
                        JSONObject object=myresults.getJSONObject(i);
                        String title=object.getString("title");
                        String vote_avg=object.getString("vote_average");
                        String poster=myImageUrl+object.getString("poster_path");
                        String overview=object.getString("overview");
                        String releasedate=object.getString("release_date");

                        MovieList movieList=new MovieList(title,vote_avg,poster,overview,releasedate);
                        movieArrayList.add(movieList);
                    }
                    MyMovieAdapter myMovieAdapter=new MyMovieAdapter(MainActivity.this,movieArrayList);
                    if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        recyclerViewmain.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    }else{

                        recyclerViewmain.setLayoutManager(new GridLayoutManager(MainActivity.this,5));
                    }
                    recyclerViewmain.setAdapter(myMovieAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    public void myPopularmovies(){

        String myPopularUrl="https://api.themoviedb.org/3/movie/popular?api_key=212231b4217b91496d026aea43b242b0";


        StringRequest stringRequest=new StringRequest(Request.Method.GET, myPopularUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    movieArrayList=new ArrayList<>();
                    JSONObject jsonObjectroot=new JSONObject(response);
                    JSONArray myresults=jsonObjectroot.getJSONArray("results");
                    for (int j=0;j<myresults.length();j++){
                        JSONObject object=myresults.getJSONObject(j);
                        String title=object.getString("title");
                        String vote_avg=object.getString("vote_average");
                        String poster=myImageUrl+object.getString("poster_path");
                        String overview=object.getString("overview");
                        String releaseDate=object.getString("release_date");

                        MovieList movieList=new MovieList(title,vote_avg,poster,overview,releaseDate);
                        movieArrayList.add(movieList);


                    }
                    MyMovieAdapter myMovieAdapter=new MyMovieAdapter(MainActivity.this,movieArrayList);
                    if (getApplicationContext().getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT) {
                        recyclerViewmain.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    }else{
                        recyclerViewmain.setLayoutManager(new GridLayoutManager(MainActivity.this,5));
                    }
                    recyclerViewmain.setAdapter(myMovieAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }


}
