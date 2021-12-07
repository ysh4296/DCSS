package com.example.soup;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
    int level;
    double experience_point;
    int gold;
    int health_point;
    int mana_point;
    int strength_point;
    int intelligence_point;
    int agility_point;
    int fire_resist_point;
    int ice_resist_point;
    int storm_resist_point;
    int negative_resist_point;
    int head_gear_item_index;
    int body_gear_item_index;
    int left_hand_item_gear_index;
    int right_hand_item_gear_index;
    int feet_item_gear_index;
    public Player(int level,double experience_point,int gold,
    int health_point, int mana_point, int strength_point, int intelligence_point, int agility_point,
    int fire_resist_point, int ice_resist_point, int storm_resist_point,
    int negative_resist_point,int head_gear_item_index,int body_gear_item_index,
    int left_hand_item_gear_index,int right_hand_item_gear_index,int feet_item_gear_index){
        this.level = level;
        this.experience_point = experience_point;
        this.gold = gold;
        this.health_point = health_point;
        this.mana_point = mana_point;
        this.strength_point = strength_point;
        this.intelligence_point = intelligence_point;
        this.agility_point = agility_point;
        this.fire_resist_point = fire_resist_point;
        this.ice_resist_point = ice_resist_point;
        this.storm_resist_point = storm_resist_point;
        this.negative_resist_point = negative_resist_point;
        this.head_gear_item_index = head_gear_item_index;
        this.body_gear_item_index = body_gear_item_index;
        this.left_hand_item_gear_index = left_hand_item_gear_index;
        this.right_hand_item_gear_index = right_hand_item_gear_index;
        this.feet_item_gear_index = feet_item_gear_index;
    }
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel parcel) {
            return new Player(parcel);
        }
        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public Player(Parcel parcel) {
        this.level = parcel.readInt();
        this.experience_point = parcel.readDouble();
        this.gold = parcel.readInt();
        this.health_point = parcel.readInt();
        this.mana_point = parcel.readInt();
        this.strength_point = parcel.readInt();
        this.intelligence_point = parcel.readInt();
        this.agility_point = parcel.readInt();
        this.fire_resist_point = parcel.readInt();
        this.ice_resist_point = parcel.readInt();
        this.storm_resist_point = parcel.readInt();
        this.negative_resist_point = parcel.readInt();
        this.head_gear_item_index = parcel.readInt();
        this.body_gear_item_index = parcel.readInt();
        this.left_hand_item_gear_index = parcel.readInt();
        this.right_hand_item_gear_index = parcel.readInt();
        this.feet_item_gear_index = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.level);
        parcel.writeDouble(this.experience_point);
        parcel.writeInt(this.gold);
        parcel.writeInt(this.health_point);
        parcel.writeInt(this.mana_point);
        parcel.writeInt(this.strength_point);
        parcel.writeInt(this.intelligence_point);
        parcel.writeInt(this.agility_point);
        parcel.writeInt(this.fire_resist_point);
        parcel.writeInt(this.ice_resist_point);
        parcel.writeInt(this.storm_resist_point);
        parcel.writeInt(this.negative_resist_point);
        parcel.writeInt(this.head_gear_item_index);
        parcel.writeInt(this.body_gear_item_index);
        parcel.writeInt(this.left_hand_item_gear_index);
        parcel.writeInt(this.right_hand_item_gear_index);
        parcel.writeInt(this.feet_item_gear_index);

    }
}