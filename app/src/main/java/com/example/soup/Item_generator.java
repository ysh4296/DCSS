package com.example.soup;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class Item_generator extends AppCompatActivity {
    public ArrayList<item_info> roll_gatcha(ArrayList<item_data> list, int times){
        ArrayList<item_info> get_list = new ArrayList<item_info>();
        Random random = new Random();
        int num = random.nextInt(100);

        if(num < 3){
            get_list.add(get_item(list,0));
        } else if (num < 18){
            get_list.add(get_item(list,1));
        } else if (num < 48){
            get_list.add(get_item(list,2));
        } else {
            get_list.add(get_item(list,3));
        }
        return get_list;
    }

    private item_info get_item(ArrayList<item_data> list, int item_rarity){
        int max_num_of_item = new Integer(list.size());
        int rar_cnt = 0;
        ArrayList<Integer> randomset = new ArrayList<>();
        for (int i = 0 ; i < max_num_of_item ; i++){
            if(list.get(i).item_Rarity != item_rarity) continue;
            else rar_cnt++;
            randomset.add(i);
        }
        Random random = new Random();
        int index = random.nextInt(rar_cnt);
        item_data Picked_item = list.get(randomset.get(index));
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
        Log.d("test line",item_path + "   " + item_name + "    " + item_info);
        return new item_info(item_path,item_name,item_info);
    }
}
