package com.example.soup;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class Item_generator extends AppCompatActivity {
    public ArrayList<item_data> roll_gatcha(ArrayList<item_data> list, int times){
        ArrayList<item_data> get_list = new ArrayList<item_data>();
        Random random = new Random();
        for(int i = 0 ; i < times ; i++) {
            int num = random.nextInt(100);
            if (num < 3) {
                get_list.add(get_item(list, 0));
            } else if (num < 18) {
                get_list.add(get_item(list, 1));
            } else if (num < 48) {
                get_list.add(get_item(list, 2));
            } else {
                get_list.add(get_item(list, 3));
            }
        }
        return get_list;
    }

    private item_data get_item(ArrayList<item_data> list, int item_rarity){
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
        return Picked_item;

    }
}
