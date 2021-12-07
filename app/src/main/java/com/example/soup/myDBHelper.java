package com.example.soup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDBHelper extends SQLiteOpenHelper {
    public myDBHelper(Context context){
        super(context,"DB",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        onUpgrade(db,0,1);
        db.execSQL("create table Charactor_table(/*Charactor_name char(40),*/" +
                " level int," +
                " experience_point double," +
                " gold int," +
                " Health_Point int," +
                " Mana_Point int," +
                " Strength_Point int," +
                " Intelligence_Point int," +
                " Agility_point int," +
                " Fire_Resist_point int, " +
                " Ice_Resist_point int," +
                " Storm_Resist_point int," +
                " Negative_Resist_point int," +
                " Head_Gear_item_index int," +
                " Body_Gear_item_index int," +
                " Left_Hand_item_Gear_index int," +
                " Right_Hand_item_Gear_index int," +
                " Feet_Gear_item_index int)");
        // + 경험치 관련 추가 요소들 있음
        db.execSQL("create table Item_table(Item_Name char(40)," +
                " Item_Index int," +
                " Item_Rarity int," +
                " Health_Point int," +
                " Mana_Point int," +
                " Strength_Point int," +
                " Intelligence_Point int," +
                " Agility_point int," +
                " Fire_Resist_point int, " +
                " Ice_Resist_point int," +
                " Storm_Resist_point int," +
                " Negative_Resist_point int," +
                " Gear int," +
                " Item_Image_Path int)");
        db.execSQL("create table Bag_Item_table(Item_Name char(40)," +
                " Item_Index int," +
                " Item_Rarity int," +
                " Health_Point int," +
                " Mana_Point int," +
                " Strength_Point int," +
                " Intelligence_Point int," +
                " Agility_point int," +
                " Fire_Resist_point int, " +
                " Ice_Resist_point int," +
                " Storm_Resist_point int," +
                " Negative_Resist_point int," +
                " Gear int," +
                " Item_Image_Path int)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        db.execSQL("drop table if exists Charactor_table");
        db.execSQL("drop table if exists Item_table");
        db.execSQL("drop table if exists Bag_Item_table");
    }
}