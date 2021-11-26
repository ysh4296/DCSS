package com.example.soup;

import static com.example.soup.R.raw.double_sword;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private ArrayList<item_info> item_list = new ArrayList<>();
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.onBind(item_list.get(position));
    }

    public void setitemlist(ArrayList<item_info> list){
        this.item_list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView info;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.item_image);
            name = (TextView) itemView.findViewById(R.id.item_name);
            info = (TextView) itemView.findViewById(R.id.item_stat);
        }
        @SuppressLint("ResourceType")
        void onBind(item_info item){
            image.setImageResource(item.getResourcepath());
            name.setText(item.get_item_name());
            info.setText(item.get_item_info());
        }
    }
}
