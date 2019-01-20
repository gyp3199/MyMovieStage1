package com.example.lenovo.mymoviestage1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyMovieAdapter extends RecyclerView.Adapter<MyMovieAdapter.MovieViewHolder> {
    Context context;
    ArrayList<MovieList>movieArrayList;
    public MyMovieAdapter(MainActivity mainActivity, ArrayList<MovieList> movieArrayList) {

        this.context=mainActivity;
        this.movieArrayList=movieArrayList;

    }

    @NonNull
    @Override
    public MyMovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.mymain,viewGroup,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMovieAdapter.MovieViewHolder movieViewHolder, int i) {
        Picasso.with(context).load(movieArrayList.get(i).getPoster()).into(movieViewHolder.nimageView);


    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView nimageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            nimageView=itemView.findViewById(R.id.imgmain);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int adapPos=getAdapterPosition();

                    Intent intent=new Intent(context,Main2Activity.class);
                    intent.putExtra("mtitle",movieArrayList.get(adapPos).getTitle());
                    intent.putExtra("mvote_avg",movieArrayList.get(adapPos).getVote_avg());
                    intent.putExtra("mposter",movieArrayList.get(adapPos).getPoster());
                    intent.putExtra("moverview",movieArrayList.get(adapPos).getOverview());
                    intent.putExtra("mrelease",movieArrayList.get(adapPos).getReleasedate());
                    context.startActivity(intent);

                }
            });
        }
    }
}
