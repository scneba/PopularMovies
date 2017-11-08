package com.clasence.neba.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.clasence.neba.popularmovies.models.MovieModel;
import com.squareup.picasso.Picasso;

/**
 * Created by Neba on 13-Jul-17.
 */

public class DetailActivity extends AppCompatActivity {

    //define imageview and textviews that will be used  to display movie details
    private ImageView imageView;
    private TextView tvMovieTitle, tvReleaseDate,tvRating,tvSynopsis;

    private Button addToFavouriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get views
        imageView= (ImageView) findViewById(R.id.imagePoster);
        tvMovieTitle = (TextView) findViewById(R.id.movietitle);
        tvReleaseDate = (TextView) findViewById(R.id.release_date);
        tvRating = (TextView) findViewById(R.id.rating);
        tvSynopsis = (TextView) findViewById(R.id.synopsis);
        addToFavouriteButton = (Button) findViewById(R.id.btnAddToFavourite);

        //functionality will be implemented in next project
        addToFavouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailActivity.this,getString(R.string.todo),Toast.LENGTH_SHORT).show();
            }
        });

        //get movie details from  intent
        MovieModel movieModel = getIntent().getExtras().getParcelable(MainActivity.MOVIE_DETAILS);

        //extract parameters and set to appropriate view element
        String posterImageName = movieModel.getPosterPath();
        String  posterPath = getString(R.string.image_base_url)+posterImageName;
        Picasso.with(DetailActivity.this).load(posterPath).fit().into(imageView);
        tvMovieTitle.setText(movieModel.getMovieTitle());
        tvReleaseDate.setText(movieModel.getReleaseDate());
        tvRating.setText(movieModel.getVoteAverage()+"/10");
        tvSynopsis.setText(movieModel.getOverview());

    }

    @Override
    public void onBackPressed() {
        //let android handle back press
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            //make sure clicking on back button takes user back
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
            default:{
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
