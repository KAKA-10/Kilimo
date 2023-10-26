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

public class TendersAdapter extends RecyclerView.Adapter<TendersAdapter.MyViewHolder> {

    Context context;
    ArrayList<Tenders> tendersArrayList;
    String tendersPageURL; // Specify the URL for the tenders page here

    public TendersAdapter(Context context, ArrayList<Tenders> tendersArrayList, String tendersPageURL) {
        this.context = context;
        this.tendersArrayList = tendersArrayList;
        this.tendersPageURL = tendersPageURL;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tenders tenders = tendersArrayList.get(position);
        holder.shapeableImageView.setImageResource(tenders.titleImage);
        holder.tenderDescription.setText(tenders.tenderDescription);
        holder.tenderName.setText(tenders.tenderName);

        // Add a click listener to open the tenders page
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTendersPage();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tendersArrayList.size();
    }

    private void openTendersPage() {
        // Create an Intent to open the tenders page URL in a web browser
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tendersPageURL));

        // Start the browser activity
        context.startActivity(intent);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView shapeableImageView;
        TextView tenderName, tenderDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            shapeableImageView = itemView.findViewById(R.id.tender_image);
            tenderName = itemView.findViewById(R.id.tender_name);
            tenderDescription = itemView.findViewById(R.id.tender_desc);
        }
    }
}
