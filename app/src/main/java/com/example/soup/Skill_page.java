package com.example.soup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.example.soup.databinding.ActivityGatchaBinding;
import com.example.soup.databinding.ActivitySkillPageBinding;

public class Skill_page extends AppCompatActivity {
    private @NonNull
    ActivitySkillPageBinding binding;
    Skill skill = new Skill(0,0,0,0,0,0,0,0,0,0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySkillPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Bundle extras = getIntent().getExtras();
        skill = (Skill) getIntent().getParcelableExtra("player_skill");
        binding.fightinglv.setText(get_level(skill.Fighting));
        binding.armorlv.setText(get_level(skill.Armor));
        binding.swordlv.setText(get_level(skill.sword));
        binding.axelv.setText(get_level(skill.axe));
        binding.arrowlv.setText(get_level(skill.arrow));
        binding.fightingWeight.setText(String.valueOf(skill.Fighting_weight));
        binding.armorWeight.setText(String.valueOf(skill.Armor_weight));
        binding.swordWeight.setText(String.valueOf(skill.sword_weight));
        binding.axeWeight.setText(String.valueOf(skill.axe_weight));
        binding.arrowWeight.setText(String.valueOf(skill.arrow_weight));
    }
    private String get_level(double v){
        if(v < 100){
            return "1";
        } else if(v < 300){
            return "2";
        } else if(v < 500){
            return "3";
        } else if(v < 800){
            return "4";
        } else {
            return "5";
        }
    }
    public void Return(View view){
        skill.Fighting_weight = Integer.parseInt(binding.fightingWeight.getText().toString());
        skill.Armor_weight = Integer.parseInt(binding.armorWeight.getText().toString());
        skill.sword_weight = Integer.parseInt(binding.swordWeight.getText().toString());
        skill.axe_weight = Integer.parseInt(binding.axeWeight.getText().toString());
        skill.arrow_weight = Integer.parseInt(binding.arrowWeight.getText().toString());



        finish();
    }
    @Override
    public void finish(){
        Log.d("finish stamp", "finifinfinfi" );
        Intent data = new Intent();
        data.putExtra("Page_type" , "Skill");
        data.putExtra("player_skill", (Parcelable) skill);
        setResult(RESULT_OK,data);
        super.finish();
    }
}