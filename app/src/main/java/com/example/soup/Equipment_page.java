package com.example.soup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.example.soup.databinding.ActivityEquipmentPageBinding;
import com.example.soup.databinding.ActivitySkillPageBinding;

import java.util.ArrayList;
import java.util.Random;

public class Equipment_page extends AppCompatActivity {
    private @NonNull ActivityEquipmentPageBinding binding;
    private RecyclerView item_RecyclerView;
    private bagAdapter item_RecyclerAdapter;
    private Player player;
    ArrayList<item_data> bag = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEquipmentPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Bundle extras = getIntent().getExtras();
        bag = (ArrayList<item_data>) getIntent().getSerializableExtra("item_bag");
        player = (Player) getIntent().getParcelableExtra("player_data");
        Log.d("bag.size()  " , String.valueOf((int)bag.size()));
        item_RecyclerAdapter = new bagAdapter();
        /**
        item_RecyclerAdapter.setitemlist(temp_item);
        item_RecyclerView = (RecyclerView) binding.itemList;
        item_RecyclerView.setAdapter(item_RecyclerAdapter);
        item_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
         **/
    }
    public void Return(View view){
        finish();
    }
    @Override
    public void finish(){
        Log.d("finish stamp", "finifinfinfi" );
        Intent data = new Intent();
        data.putExtra("Page_type" , "Equipment");
        set_item();
        data.putExtra("player_data", (Parcelable) player);
        setResult(RESULT_OK,data);
        super.finish();
    }
    public void set_item(){
        item_erase();
        if(item_RecyclerAdapter.head != null)
        player.head_gear_item_index = item_RecyclerAdapter.head.item_index;
        if(item_RecyclerAdapter.body != null)
        player.body_gear_item_index = item_RecyclerAdapter.body.item_index;
        if(item_RecyclerAdapter.right != null)
        player.right_hand_item_gear_index = item_RecyclerAdapter.right.item_index;
        if(item_RecyclerAdapter.left != null)
        player.left_hand_item_gear_index = item_RecyclerAdapter.left.item_index;
        if(item_RecyclerAdapter.foot != null)
        player.feet_item_gear_index = item_RecyclerAdapter.foot.item_index;
        item_add();
    }

    private void item_add() {
        for(int i = 0 ; i < (int)bag.size() ; i++) {
            if(bag.get(i).item_index == player.head_gear_item_index){
                stat_dif(bag.get(i),1);
                break;
            }
        }
        for(int i = 0 ; i < (int)bag.size() ; i++) {
            if(bag.get(i).item_index == player.body_gear_item_index){
                stat_dif(bag.get(i),1);
                break;
            }
        }
        for(int i = 0 ; i < (int)bag.size() ; i++) {
            if(bag.get(i).item_index == player.right_hand_item_gear_index){
                stat_dif(bag.get(i),1);
                break;
            }
        }
        for(int i = 0 ; i < (int)bag.size() ; i++) {
            if(bag.get(i).item_index == player.left_hand_item_gear_index){
                stat_dif(bag.get(i),1);
                break;
            }
        }
        for(int i = 0 ; i < (int)bag.size() ; i++) {
            if(bag.get(i).item_index == player.feet_item_gear_index){
                stat_dif(bag.get(i),1);
                break;
            }
        }
    }

    private void item_erase() {
        for(int i = 0 ; i < (int)bag.size() ; i++) {
            if(bag.get(i).item_index == player.head_gear_item_index){
                stat_dif(bag.get(i),-1);
                break;
            }
        }
        for(int i = 0 ; i < (int)bag.size() ; i++) {
            if(bag.get(i).item_index == player.body_gear_item_index){
                stat_dif(bag.get(i),-1);
                break;
            }
        }
        for(int i = 0 ; i < (int)bag.size() ; i++) {
            if(bag.get(i).item_index == player.right_hand_item_gear_index){
                stat_dif(bag.get(i),-1);
                break;
            }
        }
        for(int i = 0 ; i < (int)bag.size() ; i++) {
            if(bag.get(i).item_index == player.left_hand_item_gear_index){
                stat_dif(bag.get(i),-1);
                break;
            }
        }
        for(int i = 0 ; i < (int)bag.size() ; i++) {
            if(bag.get(i).item_index == player.feet_item_gear_index){
                stat_dif(bag.get(i),-1);
                break;
            }
        }
    }

    private void stat_dif(item_data item_data, int pi) {
        player.health_point += item_data.health_point*pi;
        player.mana_point += item_data.mana_point*pi;
        player.strength_point += item_data.strength_point*pi;
        player.intelligence_point += item_data.intelligence_point*pi;
        player.agility_point += item_data.agility_point*pi;
        player.fire_resist_point += item_data.fire_resist_point*pi;
        player.ice_resist_point += item_data.ice_resist_point*pi;
        player.storm_resist_point += item_data.storm_resist_point*pi;
        player.negative_resist_point += item_data.negative_resist_point*pi;
    }

    public void get_item_list_head(View view){
        int max_num_of_item = new Integer(bag.size());
        item_RecyclerAdapter = new bagAdapter();
        ArrayList<item_info> temp_item = new ArrayList<>();
        for (int i = 0 ; i < max_num_of_item ; i++){
            if(bag.get(i).gear != 0) continue;
            item_info picked = get_item_info(bag.get(i));
            temp_item.add(new item_info(picked.item_image_id,picked.item_name,picked.item_info));
        }
        item_RecyclerAdapter = new bagAdapter();
        item_RecyclerAdapter.setitemlist(temp_item);
        item_RecyclerAdapter.setdatalist(bag);
        item_RecyclerView = (RecyclerView) binding.itemList;
        item_RecyclerView.setAdapter(item_RecyclerAdapter);
        item_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void get_item_list_body(View view){
        int max_num_of_item = new Integer(bag.size());
        item_RecyclerAdapter = new bagAdapter();
        ArrayList<item_info> temp_item = new ArrayList<>();
        for (int i = 0 ; i < max_num_of_item ; i++){
            if(bag.get(i).gear != 1) continue;
            item_info picked = get_item_info(bag.get(i));
            temp_item.add(new item_info(picked.item_image_id,picked.item_name,picked.item_info));
        }
        item_RecyclerAdapter = new bagAdapter();
        item_RecyclerAdapter.setitemlist(temp_item);
        item_RecyclerAdapter.setdatalist(bag);
        item_RecyclerView = (RecyclerView) binding.itemList;
        item_RecyclerView.setAdapter(item_RecyclerAdapter);
        item_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void get_item_list_right(View view){
        int max_num_of_item = new Integer(bag.size());
        item_RecyclerAdapter = new bagAdapter();
        ArrayList<item_info> temp_item = new ArrayList<>();
        for (int i = 0 ; i < max_num_of_item ; i++){
            if(bag.get(i).gear != 2) continue;
            item_info picked = get_item_info(bag.get(i));
            temp_item.add(new item_info(picked.item_image_id,picked.item_name,picked.item_info));
        }
        item_RecyclerAdapter = new bagAdapter();
        item_RecyclerAdapter.setitemlist(temp_item);
        item_RecyclerAdapter.setdatalist(bag);
        item_RecyclerView = (RecyclerView) binding.itemList;
        item_RecyclerView.setAdapter(item_RecyclerAdapter);
        item_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void get_item_list_left(View view){
        int max_num_of_item = new Integer(bag.size());
        item_RecyclerAdapter = new bagAdapter();
        ArrayList<item_info> temp_item = new ArrayList<>();
        for (int i = 0 ; i < max_num_of_item ; i++){
            if(bag.get(i).gear != 3) continue;
            item_info picked = get_item_info(bag.get(i));
            temp_item.add(new item_info(picked.item_image_id,picked.item_name,picked.item_info));
        }
        item_RecyclerAdapter = new bagAdapter();
        item_RecyclerAdapter.setitemlist(temp_item);
        item_RecyclerAdapter.setdatalist(bag);
        item_RecyclerView = (RecyclerView) binding.itemList;
        item_RecyclerView.setAdapter(item_RecyclerAdapter);
        item_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void get_item_list_foot(View view){
        int max_num_of_item = new Integer(bag.size());
        item_RecyclerAdapter = new bagAdapter();
        ArrayList<item_info> temp_item = new ArrayList<>();
        for (int i = 0 ; i < max_num_of_item ; i++){
            if(bag.get(i).gear != 4) continue;
            item_info picked = get_item_info(bag.get(i));
            temp_item.add(new item_info(picked.item_image_id,picked.item_name,picked.item_info));
        }
        item_RecyclerAdapter = new bagAdapter();
        item_RecyclerAdapter.setitemlist(temp_item);
        item_RecyclerAdapter.setdatalist(bag);
        item_RecyclerView = (RecyclerView) binding.itemList;
        item_RecyclerView.setAdapter(item_RecyclerAdapter);
        item_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void get_item_list_use(View view){
        int max_num_of_item = new Integer(bag.size());
        item_RecyclerAdapter = new bagAdapter();
        ArrayList<item_info> temp_item = new ArrayList<>();
        for (int i = 0 ; i < max_num_of_item ; i++){
            if(bag.get(i).gear != 5) continue;
            item_info picked = get_item_info(bag.get(i));
            temp_item.add(new item_info(picked.item_image_id,picked.item_name,picked.item_info));
        }
        item_RecyclerAdapter = new bagAdapter();
        item_RecyclerAdapter.setitemlist(temp_item);
        item_RecyclerAdapter.setdatalist(bag);
        item_RecyclerView = (RecyclerView) binding.itemList;
        item_RecyclerView.setAdapter(item_RecyclerAdapter);
        item_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public item_info get_item_info(item_data Picked_item){
        String item_name = Picked_item.item_name;
        int item_index = Picked_item.item_index;
        int item_Rarity = Picked_item.item_Rarity;
        int health_point = Picked_item.health_point;
        int mana_point = Picked_item.mana_point;
        int strength_point = Picked_item.strength_point;
        int intelligence_point = Picked_item.intelligence_point;
        int agility_point = Picked_item.agility_point;
        int fire_resist_point = Picked_item.fire_resist_point;
        int ice_resist_point = Picked_item.ice_resist_point;
        int storm_resist_point = Picked_item.storm_resist_point;
        int negative_resist_point = Picked_item.negative_resist_point;
        int gear = Picked_item.gear;
        int item_path = Picked_item.item_path;
        String item_info = "";
        if(health_point != 0){
            item_info += "HP : " + health_point + "\n";
        }
        if(mana_point != 0){
            item_info += "MP : " + mana_point + "\n";
        }
        if(strength_point != 0){
            item_info += "STR : " + strength_point + "\n";
        }
        if(intelligence_point != 0){
            item_info += "INT : " + intelligence_point + "\n";
        }
        if(agility_point != 0){
            item_info += "AGI : " + agility_point + "\n";
        }
        if(fire_resist_point != 0){
            item_info += "Fr : " + fire_resist_point + "\n";
        }
        if(ice_resist_point != 0){
            item_info += "Ir : " + ice_resist_point + "\n";
        }
        if(storm_resist_point != 0){
            item_info += "Sr : " + storm_resist_point + "\n";
        }
        if(negative_resist_point != 0){
            item_info += "Nr : " + negative_resist_point + "\n";
        }
        Log.d("test line",item_path + "  item name   " + item_name + "   item info " + item_info);
        return new item_info(item_path,item_name,item_info);
    }
}