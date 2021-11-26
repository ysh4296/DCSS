package com.example.soup;

import android.os.Parcel;
import android.os.Parcelable;

public class item_data implements Parcelable {
    String item_name;
    int item_index;
    int item_Rarity;
    int health_point;
    int mana_point;
    int strength_point;
    int intelligence_point;
    int agility_point;
    int fire_resist_point;
    int ice_resist_point;
    int storm_resist_point;
    int negative_resist_point;
    int gear;
    int item_path;
    //String item_pic;
    public item_data(String item_name, int item_index, int item_Rarity, int health_point, int mana_point,
                     int strength_point, int intelligence_point, int agility_point, int fire_resist_point, int ice_resist_point,
                     int storm_resist_point, int negative_resist_point, int gear, int item_path){
        this.item_name = item_name;
        this.item_index = item_index;
        this.item_Rarity = item_Rarity;
        this.health_point = health_point;
        this.mana_point = mana_point;
        this.strength_point = strength_point;
        this.intelligence_point = intelligence_point;
        this.agility_point = agility_point;
        this.fire_resist_point = fire_resist_point;
        this.ice_resist_point = ice_resist_point;
        this.storm_resist_point = storm_resist_point;
        this.negative_resist_point = negative_resist_point;
        this.gear = gear;
        this.item_path = item_path;
    }

    public item_data(Parcel parcel) {
        this.item_name = parcel.readString();
        this.item_index = parcel.readInt();
        this.item_Rarity = parcel.readInt();
        this.health_point = parcel.readInt();
        this.mana_point = parcel.readInt();
        this.strength_point = parcel.readInt();
        this.intelligence_point = parcel.readInt();
        this.agility_point = parcel.readInt();
        this.fire_resist_point = parcel.readInt();
        this.ice_resist_point = parcel.readInt();
        this.storm_resist_point = parcel.readInt();
        this.negative_resist_point = parcel.readInt();
        this.gear = parcel.readInt();
        this.item_path = parcel.readInt();
    }

    public String get_item_name(){
        return item_name;
    }
    public int get_item_index(){
        return item_index;
    }
    public int getResourcepath() { return item_path;}
    public static final Parcelable.Creator<item_data> CREATOR = new Parcelable.Creator<item_data>() {
        @Override
        public item_data createFromParcel(Parcel parcel) {
            return new item_data(parcel);
        }
        @Override
        public item_data[] newArray(int size) {
            return new item_data[size];
        }
    };
    
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.item_name);
        parcel.writeInt(this.item_index);
        parcel.writeInt(this.item_Rarity);
        parcel.writeInt(this.health_point);
        parcel.writeInt(this.mana_point);
        parcel.writeInt(this.strength_point);
        parcel.writeInt(this.intelligence_point);
        parcel.writeInt(this.agility_point);
        parcel.writeInt(this.fire_resist_point);
        parcel.writeInt(this.ice_resist_point);
        parcel.writeInt(this.storm_resist_point);
        parcel.writeInt(this.negative_resist_point);
        parcel.writeInt(this.gear);
        parcel.writeInt(this.item_path);
    }
}
