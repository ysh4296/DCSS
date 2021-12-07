package com.example.soup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.soup.databinding.ActivityGatchaBinding;
import com.example.soup.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class Gatcha extends AppCompatActivity {
    private @NonNull ActivityGatchaBinding binding;
    private RecyclerView item_RecyclerView;
    private Adapter item_RecyclerAdapter;
    public Player player;
    public ArrayList<item_data> list;
    public ArrayList<item_data> added_item_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGatchaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Bundle extras = getIntent().getExtras();
        list = (ArrayList<item_data>) getIntent().getSerializableExtra("item_data");
        player = (Player) getIntent().getParcelableExtra("player_data");
        binding.PlayerGold.setText(String.valueOf(player.gold));
        if(extras == null){
            return;
        }
        /**adapt data
        ArrayList<item_info> temp_item = new ArrayList<>();


       item_RecyclerAdapter = new Adapter();
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
        data.putExtra("Page_type" , "Gatcha");
        data.putExtra("item_list" , added_item_list);
        data.putExtra("player_data", (Parcelable) player);
        setResult(RESULT_OK,data);
        super.finish();
    }
    public void pull1(View view){
        if(player.gold > 100)
            player.gold -= 100;
        else {
            Toast.makeText(getApplicationContext(),"Non Enough Gold",Toast.LENGTH_LONG).show();
            return;
        }
        Item_generator generator = new Item_generator();
        ArrayList<item_data> li = generator.roll_gatcha(list,1);
        ArrayList<item_info> temp_item = new ArrayList<>();
        for(int i = 0 ; i < (int)li.size() ; i++){
            item_info picked = get_item_info(li.get(i));
            added_item_list.add(li.get(i));
            temp_item.add(new item_info(picked.item_image_id,picked.item_name,picked.item_info));
        }
        item_RecyclerAdapter = new Adapter();
        item_RecyclerAdapter.setitemlist(temp_item);
        item_RecyclerView = (RecyclerView) binding.itemList;
        item_RecyclerView.setAdapter(item_RecyclerAdapter);
        item_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.PlayerGold.setText(String.valueOf(player.gold));
    }
    public void pull10(View view){
        if(player.gold > 900)
            player.gold -= 900;
        else {
            Toast.makeText(getApplicationContext(),"Non Enough Gold",Toast.LENGTH_LONG).show();
            return;
        }
        Item_generator generator = new Item_generator();
        ArrayList<item_data> li = generator.roll_gatcha(list,10);
        ArrayList<item_info> temp_item = new ArrayList<>();
        for(int i = 0 ; i < (int)li.size() ; i++){
            item_info picked = get_item_info(li.get(i));
            added_item_list.add(li.get(i));
            temp_item.add(new item_info(picked.item_image_id,picked.item_name,picked.item_info));
        }
        item_RecyclerAdapter = new Adapter();
        item_RecyclerAdapter.setitemlist(temp_item);
        item_RecyclerView = (RecyclerView) binding.itemList;
        item_RecyclerView.setAdapter(item_RecyclerAdapter);
        item_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.PlayerGold.setText(String.valueOf(player.gold));
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
        if(gear != 0){
            item_info += "gear : " + gear + "\n";
        }
        Log.d("test line",item_path + "  item name   " + item_name + "   item info " + item_info);
        return new item_info(item_path,item_name,item_info);
    }
}