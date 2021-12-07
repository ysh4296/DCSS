package com.example.soup;

public class Skill {
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
}
