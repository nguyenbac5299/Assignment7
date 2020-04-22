package com.example.email1;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ItemModel> items;

    public ArrayList<ItemModel> getItems() {
        return items;
    }

    public EmailAdapter(ArrayList<ItemModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new EmailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmailViewHolder viewHolder= (EmailViewHolder) holder;
        ItemModel item=items.get(position);

        viewHolder.textLeter.setText(item.getName().substring(0,1));
        Drawable background=viewHolder.textLeter.getBackground();
        background.setColorFilter(new PorterDuffColorFilter(item.getColor(), PorterDuff.Mode.SRC_ATOP));
        viewHolder.textName.setText(item.getName());
        viewHolder.textSubject.setText(item.getSubject());
        viewHolder.textTime.setText(item.getTime());
        if(item.isFavorite())
            viewHolder.imageFavorite.setImageResource(R.drawable.ic_star_black_24dp);
        else
            viewHolder.imageFavorite.setImageResource(R.drawable.ic_star_border_black_24dp);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class EmailViewHolder extends RecyclerView.ViewHolder {
        TextView textLeter, textName, textSubject, textTime;
        ImageView imageFavorite;
        public EmailViewHolder(@NonNull View itemView) {
            super(itemView);
            textLeter=itemView.findViewById(R.id.text_letter);
            textName=itemView.findViewById(R.id.text_name);
            textSubject=itemView.findViewById(R.id.text_subject);
            textTime=itemView.findViewById(R.id.text_time);
            imageFavorite=itemView.findViewById(R.id.image_star);

            imageFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isFavorite=items.get(getAdapterPosition()).isFavorite;
                    items.get(getAdapterPosition()).setFavorite(!isFavorite);
                    notifyDataSetChanged();
                }
            });

        }
    }
}
