package com.example.soup;

import static com.example.soup.R.raw.double_sword;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
public class bagAdapter extends RecyclerView.Adapter<bagAdapter.ViewHolder> {
    private ArrayList<item_info> item_list = new ArrayList<>();
    public ArrayList<item_data> data_list = new ArrayList<>();

    item_data head;
    item_data body;
    item_data right;
    item_data left;
    item_data foot;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(item_list.get(position));
    }

    public void setitemlist(ArrayList<item_info> list){
        this.item_list = list;
        notifyDataSetChanged();
    }

    public void setdatalist(ArrayList<item_data> list){
        this.data_list = list;
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
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    item_data clicked_item = data_list.get(pos);
                    if(clicked_item.gear == 0){ // head
                        head = clicked_item;
                    } else if(clicked_item.gear == 1) {// body
                        body = clicked_item;
                    } else if(clicked_item.gear == 2) {// right
                        right = clicked_item;
                    } else if(clicked_item.gear == 3) {// left
                        left = clicked_item;
                    } else if(clicked_item.gear == 4) {// foot
                        foot = clicked_item;
                    }

                    Toast.makeText(itemView.getContext(),"item_changed to " + clicked_item.item_name,Toast.LENGTH_LONG).show();
                }
            });
        }
        @SuppressLint("ResourceType")
        void onBind(item_info item){
            image.setImageResource(item.getResourcepath());
            name.setText(item.get_item_name());
            info.setText(item.get_item_info());
        }
    }
}
