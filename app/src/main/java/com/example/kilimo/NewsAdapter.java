package com.example.kilimo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.imageview.ShapeableImageView;
import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    Context context;
    ArrayList<News> newsArrayList;
    String newsPageURL; // Specify the URL for the news page here

    public NewsAdapter(Context context, ArrayList<News> newsArrayList, String newsPageURL) {
        this.context = context;
        this.newsArrayList = newsArrayList;
        this.newsPageURL = newsPageURL;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        News news = newsArrayList.get(position);
        holder.shapeableImageView.setImageResource(news.titleImage);
        holder.newsDescription.setText(news.newsDescription);
        holder.newsHeading.setText(news.newsHeading);

        // Add a click listener to open the news page
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewsPage();
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    private void openNewsPage() {
        // Create an Intent to open the news page URL in a web browser
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsPageURL));

        // Start the browser activity
        context.startActivity(intent);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView shapeableImageView;
        TextView newsHeading, newsDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            shapeableImageView = itemView.findViewById(R.id.news_image);
            newsHeading = itemView.findViewById(R.id.heading);
            newsDescription = itemView.findViewById(R.id.news_desc);
        }
    }
}

