package com.example.soup;

public class Current_State {
    int player_health_point;
    int monster_health_point;
    int turns;
    Current_State(int player_health_point,int monster_health_point, int turns){
        this.player_health_point = player_health_point;
        this.monster_health_point = monster_health_point;
        this.turns = turns;
    }
}
