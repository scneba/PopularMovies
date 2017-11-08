package com.clasence.neba.popularmovies;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.clasence.neba.popularmovies.models.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Neba on 13-Jul-17.
 */

public class MovieAdapter extends ArrayAdapter<MovieModel> {
    private Activity context;
    private CustomClickListener customClickListener=null;
    //constructor
    //Ideas gotten from https://github.com/udacity/android-custom-arrayadapter/blob/master/app/src/main/java/demo/example/com/customarrayadapter/AndroidFlavorAdapter.java
    public MovieAdapter(Activity context, ArrayList<MovieModel> movielist, CustomClickListener customClickListener){
        super(context,0,movielist);
        this.context=context;
        this.customClickListener=customClickListener;
    }


    /**
     * Custom onclick listener to handle poster clicks*/
    public interface CustomClickListener{
        public abstract void onCustomClick(int position);
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final MovieModel movieModel = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_adapter_layout, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.moviePoster);
        String posterImageName = movieModel.getPosterPath();
        String  posterPath = context.getString(R.string.image_base_url)+posterImageName;
        Picasso.with(context).load(posterPath).fit().into(imageView);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              if(customClickListener!=null){
                  customClickListener.onCustomClick(position);
              }

            }
        });
        return convertView;

    }


}
