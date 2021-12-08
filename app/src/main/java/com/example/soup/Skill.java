package com.example.soup;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;

public class Skill implements Parcelable {
    double Fighting;
    double Armor;
    double sword;
    double axe;
    double arrow;
    int Fighting_weight;
    int Armor_weight;
    int sword_weight;
    int axe_weight;
    int arrow_weight;
    public Skill(double Fighting, double Armor , double sword , double axe , double arrow,
                 int Fighting_weight, int Armor_weight , int sword_weight , int axe_weight , int arrow_weight){
        this.Fighting = Fighting;
        this.Armor = Armor;
        this.sword = sword;
        this.axe = axe;
        this.arrow = arrow;
        this.Fighting_weight = Fighting_weight;
        this.Armor_weight = Armor_weight;
        this.sword_weight = sword_weight;
        this.axe_weight = axe_weight;
        this.arrow_weight = arrow_weight;
    }

    public static final Parcelable.Creator<Skill> CREATOR = new Parcelable.Creator<Skill>() {
        @Override
        public Skill createFromParcel(Parcel parcel) {
            return new Skill(parcel);
        }
        @Override
        public Skill[] newArray(int size) {
            return new Skill[size];
        }
    };

    public Skill(Parcel parcel) {
        this.Fighting = parcel.readDouble();
        this.Armor = parcel.readDouble();
        this.sword = parcel.readDouble();
        this.axe = parcel.readDouble();
        this.arrow = parcel.readDouble();
        this.Fighting_weight = parcel.readInt();
        this.Armor_weight = parcel.readInt();
        this.sword_weight = parcel.readInt();
        this.axe_weight = parcel.readInt();
        this.arrow_weight = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.Fighting);
        parcel.writeDouble(this.Armor);
        parcel.writeDouble(this.sword);
        parcel.writeDouble(this.axe);
        parcel.writeDouble(this.arrow);
        parcel.writeInt(this.Fighting_weight);
        parcel.writeInt(this.Armor_weight);
        parcel.writeInt(this.sword_weight);
        parcel.writeInt(this.axe_weight);
        parcel.writeInt(this.arrow_weight);

    }
}
