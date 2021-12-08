package com.example.soup;

public class Monster {
    int health_point;
    int AC;
    int EV;
    double XP;
    int Damage;
    int Monster_image_id;
    public Monster(int health_point , int AC , int EV , double XP , int Damage , int Monster_image_id){
        this.health_point = health_point;
        this.AC = AC;
        this.EV = EV;
        this.XP = XP;
        this.Damage = Damage;
        this.Monster_image_id = Monster_image_id;
    }
}
