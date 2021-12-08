package com.example.soup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.soup.R;
import com.example.soup.databinding.ActivityMainBinding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> resultLauncher;

    public Player player = new Player(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
    public ArrayList<item_data> list;
    public Monster monster;
    public Current_State current = new Current_State(0,0,0);
    public Skill skill = new Skill(0,0,0,0,0,1,1,1,1,1);
    ArrayList<Monster> Monster_list = new ArrayList<Monster>();
    //String filepath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/myApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initializeDB(view);
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>(){
                    @Override
                    public void onActivityResult(ActivityResult result){
                        if(result.getResultCode() == RESULT_OK){
                            Bundle extras = result.getData().getExtras();
                            String page = (String) result.getData().getStringExtra("Page_type");
                            if(page.equals("Gatcha")){
                                player = (Player) result.getData().getParcelableExtra("player_data");
                                list = (ArrayList<item_data>) result.getData().getSerializableExtra("item_list");
                                set_page();
                                add_data_for_bag();
                                Log.d("item","Updated");
                            }
                        }
                    }
                }
        );
        set_player();
        Log.d("hp0" , String.valueOf(current.player_health_point));
        SearchDB_Monster();
        Log.d("hp1" , String.valueOf(current.player_health_point));
        set_Monster();
        Log.d("hp2" , String.valueOf(current.player_health_point));
        update_Current();
        Log.d("hp3" , String.valueOf(current.player_health_point));
        set_Current();
        Log.d("hp4" , String.valueOf(current.player_health_point));
        set_page();
    }

    private void set_player() {
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("select * from Charactor_table;" , null);
        while(cursor.moveToNext()) {
            player.level = cursor.getInt(0);
            player.experience_point = cursor.getDouble(1);
            player.gold = cursor.getInt(2);
            player.health_point = cursor.getInt(3);
            player.mana_point = cursor.getInt(4);
            player.strength_point = cursor.getInt(5);
            player.intelligence_point = cursor.getInt(6);
            player.agility_point = cursor.getInt(7);
            player.fire_resist_point = cursor.getInt(8);
            player.ice_resist_point = cursor.getInt(9);
            player.storm_resist_point = cursor.getInt(10);
            player.negative_resist_point = cursor.getInt(11);
            player.head_gear_item_index = cursor.getInt(12);
            player.body_gear_item_index = cursor.getInt(13);
            player.left_hand_item_gear_index = cursor.getInt(14);
            player.right_hand_item_gear_index = cursor.getInt(15);
            player.feet_item_gear_index = cursor.getInt(16);
            current.player_health_point = player.health_point;
            Log.d("hpppp" , String.valueOf(current.player_health_point));
        }
        sqlDB.close();
    }

    private void SearchDB_Monster(){
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("select * from Monster;" , null);
        while(cursor.moveToNext()) {
            int health_point = cursor.getInt(0);
            int AC = cursor.getInt(1);
            int EV = cursor.getInt(2);
            double XP = cursor.getDouble(3);
            int Damage = cursor.getInt(4);
            int Monster_image_id = cursor.getInt(5);
            Monster_list.add(new Monster(health_point,AC,EV,XP,Damage,Monster_image_id));
        }
        sqlDB.close();
    }
    private void set_Monster() {
        Random random = new Random();
        monster = Monster_list.get(random.nextInt((int)Monster_list.size()));
        binding.Monster.setImageResource(monster.Monster_image_id);
        current.monster_health_point = monster.health_point;
    }
    private void update_Current(){
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        Log.d("current hp" , String.valueOf(current.player_health_point));
        sqlDB.execSQL("update Current_State " +
                "set player_health_point = " + "" + current.player_health_point +" " +
                "where player_health_point is not null;");
        sqlDB.execSQL("update Current_State " +
                "set monster_health_point = " + "" + current.monster_health_point +" " +
                "where monster_health_point is not null;");
        sqlDB.execSQL("update Current_State " +
                "set turns = " + "" + current.turns +"" +
                ";where turns is not null;");
        sqlDB.close();
    }
    private void set_Current() {
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("select * from Current_State;" , null);
        while(cursor.moveToNext()) {
            Log.d("new" , String.valueOf(cursor.getInt(0)));
            current.player_health_point = cursor.getInt(0);
            current.monster_health_point = cursor.getInt(1);
            current.turns = cursor.getInt(2);
        }
        sqlDB.close();
    }
    public void Attack(View view){
        damage_each();
        if(current.player_health_point < 0) {
            Toast.makeText(getApplicationContext(),"GameOver",Toast.LENGTH_LONG).show();
            /**exit**/
            moveTaskToBack(true);
            finishAndRemoveTask();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        else if(current.monster_health_point < 0) {/**exit**/
            player.experience_point += monster.XP;
            Add_Skill(monster.XP);
            set_Monster();
            update_Current();
            set_Current();
        }
    }

    private void Add_Skill(double XP) {
        int count_all = 0;
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        searchDB_Skill();
        count_all += skill.Fighting_weight;
        count_all += skill.Armor_weight;
        count_all += skill.sword_weight;
        count_all += skill.axe_weight;
        count_all += skill.arrow_weight;
        skill.Fighting += XP / count_all;
        skill.Armor += XP / count_all;
        skill.sword += XP / count_all;
        skill.axe += XP / count_all;
        skill.arrow += XP / count_all;
        insertDB_Skill();
        sqlDB.close();
        return;
    }
    private void insertDB_Skill(){
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        sqlDB.execSQL("update Skill " +
                "set Fighting = " + "" + skill.Fighting +" " +
                "where Fighting is not null;");
        sqlDB.execSQL("update Skill " +
                "set Armor = " + "" + skill.Armor +" " +
                "where Armor is not null;");
        sqlDB.execSQL("update Skill " +
                "set sword = " + "" + skill.sword +"" +
                ";where sword is not null;");
        sqlDB.execSQL("update Skill " +
                "set axe = " + "" + skill.axe +"" +
                ";where axe is not null;");
        sqlDB.execSQL("update Skill " +
                "set arrow = " + "" + skill.arrow +"" +
                ";where arrow is not null;");
        sqlDB.execSQL("update Skill " +
                "set Fighting_weight = " + "" + skill.Fighting_weight +"" +
                ";where Fighting_weight is not null;");
        sqlDB.execSQL("update Skill " +
                "set Armor_weight = " + "" + skill.Armor_weight +"" +
                ";where Armor_weight is not null;");
        sqlDB.execSQL("update Skill " +
                "set sword_weight = " + "" + skill.sword_weight +"" +
                ";where sword_weight is not null;");
        sqlDB.execSQL("update Skill " +
                "set axe_weight = " + "" + skill.axe_weight +"" +
                ";where axe_weight is not null;");
        sqlDB.execSQL("update Skill " +
                "set arrow_weight = " + "" + skill.arrow_weight +"" +
                ";where arrow_weight is not null;");
        sqlDB.close();
    }

    private void searchDB_Skill() {
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("select * from Skill;" , null);
        while(cursor.moveToNext()) {
            skill.Fighting = cursor.getDouble(0);
            skill.Armor = cursor.getDouble(1);
            skill.sword = cursor.getDouble(2);
            skill.axe = cursor.getDouble(3);
            skill.arrow = cursor.getDouble(4);
            skill.Fighting_weight = cursor.getInt(5);
            skill.Armor_weight = cursor.getInt(6);
            skill.sword_weight = cursor.getInt(7);
            skill.axe_weight = cursor.getInt(8);
            skill.arrow_weight = cursor.getInt(9);
        }
        sqlDB.close();
    }

    public void damage_each(){
        Random random = new Random();
        for(int i = 0 ; i < 3 ; i++)
        current.player_health_point -= random.nextInt(monster.Damage);
        int res = 0;
        for(int i = 0 ; i < 3 ; i++) {
            res += random.nextInt(player.strength_point * (Exp_to_Level(skill.arrow + skill.sword + skill.axe)));
        }
        current.monster_health_point -= res * (100 - monster.AC/ 100);
        set_page();
    }
    private int Exp_to_Level(double v) {
        if(v < 100){
            return 1;
        } else if(v < 300){
            return 2;
        } else if(v < 500){
            return 3;
        } else if(v < 800){
            return 4;
        } else {
            return 5;
        }
    }
    public void set_page(){
        /**set data for main page**/
        binding.PlayerHP.setText(String.valueOf(current.player_health_point));
        binding.MonsterHP.setText(String.valueOf(current.monster_health_point));
        return;
    }
    public void add_data_for_bag(){
        /**set data for main page**/
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        for (int i = 0 ; i < (int)list.size() ; i++) {
            sqlDB.execSQL("insert into Bag_Item_table values('" +
                    ""+ list.get(i).item_name +"' , '" +
                    ""+ list.get(i).item_index +"' , '" +
                    ""+ list.get(i).item_Rarity +"' , '" +
                    ""+ list.get(i).health_point +"' , '" +
                    ""+ list.get(i).mana_point +"' , '" +
                    ""+ list.get(i).strength_point +"' , '" +
                    ""+ list.get(i).intelligence_point +"' , '" +
                    ""+ list.get(i).agility_point +"' , '" +
                    ""+ list.get(i).fire_resist_point +"' , '" +
                    ""+ list.get(i).ice_resist_point +"' , '" +
                    ""+ list.get(i).storm_resist_point +"' , '" +
                    ""+ list.get(i).negative_resist_point +"' , '" +
                    ""+ list.get(i).gear +"' , '" +
                    ""+ list.get(i).item_path +"" +
                    "');");
        }
        sqlDB.close();
        return;
    }
    public void initializeDB(View view){
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        sqlDB.execSQL("drop table if exists Charactor_table");
        sqlDB.execSQL("drop table if exists Item_table");
        sqlDB.execSQL("drop table if exists Bag_Item_table");
        sqlDB.execSQL("drop table if exists Current_State");
        sqlDB.execSQL("drop table if exists Monster");
        sqlDB.execSQL("drop table if exists Skill");
        sqlDB.execSQL("create table Charactor_table(/*Charactor_name char(40),*/" +
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
        sqlDB.execSQL("create table Item_table(Item_Name char(40)," +
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
        sqlDB.execSQL("create table Bag_Item_table(Item_Name char(40)," +
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

        sqlDB.execSQL("create table Skill(Fighting double," +
                " Armor double," +
                " sword double," +
                " axe double," +
                " arrow double," +
                " Fighting_weight int," +
                " Armor_weight int," +
                " sword_weight int," +
                " axe_weight int," +
                " arrow_weight int)");
        sqlDB.execSQL("create table Monster(health_point int," +
                " AC int," +
                " EV int," +
                " XP double," +
                " Damage int," +
                " Monster_image_id int)");
        sqlDB.execSQL("create table Current_State(player_health_point int," +
                " monster_health_point int," +
                " turns int)");
        Log.d("test","insert into Item_table values('" +
                        "3' , '" +
                        "1' , '" +
                        "3' , '" +
                        "0' , '" +
                        "0' , '" +
                        "3' , '" +
                        "0' , '" +
                        "0' , '" +
                        "0' , '" +
                        "0' , '" +
                        "0' , '" +
                        "0' , '" +
                        "0' , '" +
                        R.raw.elven_short_sword +"" +
                        "');");
        sqlDB.execSQL("insert into Item_table values('" +
                "3' , '" +
                "2' , '" +
                "3' , '" +
                "0' , '" +
                "0' , '" +
                "3' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                R.raw.elven_short_sword +"" +
                "');");
        sqlDB.execSQL("insert into Item_table values('" +
                "3' , '" +
                "3' , '" +
                "3' , '" +
                "0' , '" +
                "0' , '" +
                "3' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                R.raw.orcish_dagger +"" +
                "');");
        sqlDB.execSQL("insert into Item_table values('" +
                "3' , '" +
                "4' , '" +
                "3' , '" +
                "0' , '" +
                "4' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                R.raw.orcish_dagger +"" +
                "');");
        sqlDB.execSQL("insert into Item_table values('" +
                "3' , '" +
                "5' , '" +
                "2' , '" +
                "5' , '" +
                "3' , '" +
                "4' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                R.raw.scimitar1 +"" +
                "');");
        sqlDB.execSQL("insert into Item_table values('" +
                "3' , '" +
                "6' , '" +
                "2' , '" +
                "8' , '" +
                "0' , '" +
                "4' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                R.raw.scimitar1 +"" +
                "');");
        sqlDB.execSQL("insert into Item_table values('" +
                "3' , '" +
                "7' , '" +
                "2' , '" +
                "8' , '" +
                "6' , '" +
                "7' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                R.raw.sabre2 +"" +
                "');");
        sqlDB.execSQL("insert into Item_table values('" +
                "3' , '" +
                "8' , '" +
                "1' , '" +
                "10' , '" +
                "5' , '" +
                "4' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                R.raw.bardiche1 +"" +
                "');");
        sqlDB.execSQL("insert into Item_table values('" +
                "3' , '" +
                "9' , '" +
                "1' , '" +
                "10' , '" +
                "5' , '" +
                "4' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                R.raw.bardiche1 +"" +
                "');");
        sqlDB.execSQL("insert into Item_table values('" +
                "3' , '" +
                "10' , '" +
                "0' , '" +
                "20' , '" +
                "10' , '" +
                "10' , '" +
                "5' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                R.raw.double_sword +"" +
                "');");
        sqlDB.execSQL("insert into Charactor_table values('" +
                "1' , '" +
                "0' , '" +
                "100000' , '" +
                "500' , '" +
                "5' , '" +
                "5' , '" +
                "5' , '" +
                "5' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "-1' , '" +
                "-1' , '" +
                "-1' , '" +
                "-1' , '" +
                "-1 " +
                "');");
        sqlDB.execSQL("insert into Current_State values('" +
                "0' , '" +
                "0' , '" +
                "0 " +
                "');");
        sqlDB.execSQL("insert into Monster values('" +
                "5' , '" +
                "3' , '" +
                "0' , '" +
                "5' , '" +
                "5' , '" +
                "" + R.raw.alligator + " " +
                "');");
        sqlDB.execSQL("insert into Monster values('" +
                "20' , '" +
                "10' , '" +
                "10' , '" +
                "50' , '" +
                "23' , '" +
                "" + R.raw.soul_eater +"" +
                "');");
        sqlDB.execSQL("insert into Monster values('" +
                "10' , '" +
                "5' , '" +
                "0' , '" +
                "30' , '" +
                "10' , '" +
                ""+R.raw.elephant_slug +"" +
                "');");
        sqlDB.execSQL("insert into Skill values('" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "0' , '" +
                "1' , '" +
                "1' , '" +
                "1' , '" +
                "1' , '" +
                "1" +
                "');");
        sqlDB.close();
        //Toast.makeText(getApplicationContext(),"Initialized",Toast.LENGTH_LONG).show();
    }
    public void insertDB(View view){
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        //sqlDB.execSQL("insert into groupTBL values('" + binding.editText1.getText().toString() + "' , '" + binding.editText2.getText().toString() + "','" + binding.editText2.getText().toString() +"');");
        sqlDB.close();
        Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_LONG).show();
    }
    public ArrayList<item_data> searchDB(MainActivity view){
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getReadableDatabase();
        ArrayList<item_data> list = new ArrayList<>();
        Cursor cursor;

        cursor = sqlDB.rawQuery("select * from Item_table;" , null);
        while(cursor.moveToNext()) {
            String item_name = cursor.getString(0);
            int item_index = cursor.getInt(1);
            int item_Rarity = cursor.getInt(2);
            int health_point = cursor.getInt(3);
            int mana_point = cursor.getInt(4);
            int strength_point = cursor.getInt(5);
            int intelligence_point = cursor.getInt(6);
            int agility_point = cursor.getInt(7);
            int fire_resist_point = cursor.getInt(8);
            int ice_resist_point = cursor.getInt(9);
            int storm_resist_point = cursor.getInt(10);
            int negative_resist_point = cursor.getInt(11);
            int gear = cursor.getInt(12);
            int item_path = cursor.getInt(13);
            list.add(new item_data(item_name,item_index,item_Rarity,health_point,mana_point,strength_point,
                    intelligence_point,agility_point,fire_resist_point,ice_resist_point,storm_resist_point,negative_resist_point,
                    gear,item_path));
        }
        cursor.close();
        sqlDB.close();
        Toast.makeText(getApplicationContext(),"Searched",Toast.LENGTH_LONG).show();
        return list;
    }
    public void Gatcha_Page(View view){
        Intent intent = new Intent(this,Gatcha.class);
        ArrayList<item_data> list = searchDB(this);
        Log.d("print d" , list.toString());
        intent.putExtra("item_data",list);
        Player player = searchDB_player(this);
        intent.putExtra("player_data", (Parcelable) player);
        Log.d("Player", String.valueOf(player.level));
        resultLauncher.launch(intent);
    }

    private Player searchDB_player(MainActivity view) {
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("select * from Charactor_table;" , null);
        Player player = null;
        while(cursor.moveToNext()) {
            int level = cursor.getInt(0);
            double experience_point = cursor.getDouble(1);
            int gold = cursor.getInt(2);
            int health_point = cursor.getInt(3);
            int mana_point = cursor.getInt(4);
            int strength_point = cursor.getInt(5);
            int intelligence_point = cursor.getInt(6);
            int agility_point = cursor.getInt(7);
            int fire_resist_point = cursor.getInt(8);
            int ice_resist_point = cursor.getInt(9);
            int storm_resist_point = cursor.getInt(10);
            int negative_resist_point = cursor.getInt(11);
            int head_gear_item_index = cursor.getInt(12);
            int body_gear_item_index = cursor.getInt(13);
            int left_hand_item_gear_index = cursor.getInt(14);
            int right_hand_item_gear_index = cursor.getInt(15);
            int feet_item_gear_index = cursor.getInt(16);
            player = new Player(level,experience_point,gold,
                    health_point,mana_point,strength_point,intelligence_point,
                    agility_point,fire_resist_point,ice_resist_point,storm_resist_point,
                    negative_resist_point,head_gear_item_index,body_gear_item_index,left_hand_item_gear_index,
                    right_hand_item_gear_index,feet_item_gear_index);
        }
        return player;
    }
}