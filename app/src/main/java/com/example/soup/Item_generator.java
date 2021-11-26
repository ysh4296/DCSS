package com.example.soup;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class Item_generator extends AppCompatActivity {
    private ArrayList<item_info> roll_gatcha(int times){
        ArrayList<item_info> get_list = new ArrayList<item_info>();
        Random random = new Random();
        int num = random.nextInt(100);

        if(num < 3){
            get_list.add(get_item(0));
        } else if (num < 18){
            get_list.add(get_item(1));
        } else if (num < 48){
            get_list.add(get_item(2));
        } else {
            get_list.add(get_item(3));
        }
        return get_list;
    }
    private item_info get_item(int item_rarity){
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("select count(*) from Item_table where Item_Rarity = " + item_rarity + ";" , null);
        int max_num_of_item = cursor.getInt(0);
        Log.d("test", String.valueOf(max_num_of_item));
        cursor = sqlDB.rawQuery("select * from Item_table where Item_Rarity = " + item_rarity + ";" , null);
        ArrayList<Integer> list = new ArrayList<>();
        while(cursor.moveToNext()){
            int item_index = cursor.getInt(1);
            list.add(item_index);
        }
        Random random = new Random();
        int index = list.get(random.nextInt(max_num_of_item));
        cursor = sqlDB.rawQuery("select * from Item_table where Item_Index = " + index + ";" , null);
        String item_name = cursor.getString(0);
        int item_index = cursor.getInt(1);
        int item_Rarity = cursor.getInt(2);
        int health_point = cursor.getInt(3);
        int mana_point = cursor.getInt(4);
        int strength_point = cursor.getInt(5);
        int intelligence_point = cursor.getInt(6);
        int agility_point = cursor.getInt(7);
        int fire_resist_point = cursor.getInt(8);
        int ice_resist_point = cursor.getInt(9);
        int storm_resist_point = cursor.getInt(10);
        int negative_resist_point = cursor.getInt(11);
        int item_path = cursor.getInt(12);
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
        return new item_info(item_path,item_name,item_info);
    }
}
