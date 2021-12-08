package com.example.soup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.example.soup.databinding.ActivityEquipmentPageBinding;
import com.example.soup.databinding.ActivityStatusBinding;

import java.util.ArrayList;

public class Status extends AppCompatActivity {
    private @NonNull
    ActivityStatusBinding binding;
    public Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatusBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Bundle extras = getIntent().getExtras();
        player = (Player) getIntent().getParcelableExtra("player_data");
        binding.level.setText("LEVEL : " + String.valueOf(player.level));
        binding.hp.setText("HP : " + String.valueOf(player.health_point));
        binding.mp.setText("MP : " + String.valueOf(player.mana_point));
        binding.strength.setText("STR : " + String.valueOf(player.strength_point));
        binding.intelligence.setText("INT : " + String.valueOf(player.intelligence_point));
        binding.agility.setText("AGI : " + String.valueOf(player.agility_point));
        binding.fr.setText("Fire Resist : " + String.valueOf(player.fire_resist_point));
        binding.ir.setText("Ice Resist : " + String.valueOf(player.ice_resist_point));
        binding.sr.setText("Storm Resist : " + String.valueOf(player.storm_resist_point));
        binding.nr.setText("Neg Resist : " + String.valueOf(player.negative_resist_point));
        binding.playerGold.setText("GOLD : " + String.valueOf(player.gold));
    }
    public void Return(View view){
        finish();
    }
    @Override
    public void finish(){
        Intent data = new Intent();
        data.putExtra("Page_type" , "Status");
        setResult(RESULT_OK,data);
        super.finish();
    }
}