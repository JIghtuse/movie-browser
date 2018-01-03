package com.example.android.moviebrowser;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {


    public MovieAdapter(@NonNull Context context, List<Movie> movies) {
        super(context, 0, movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_item, parent, false);
        }

        Movie currentMovie = getItem(position);

        ImageView poster = convertView.findViewById(R.id.poster);
        poster.setImageResource(R.drawable.ic_launcher_background);

        TextView name = convertView.findViewById(R.id.name);
        name.setText(currentMovie.getName());

        TextView rating = convertView.findViewById(R.id.rating);
        rating.setText(String.valueOf(currentMovie.getRating()));

        return convertView;
    }

}
