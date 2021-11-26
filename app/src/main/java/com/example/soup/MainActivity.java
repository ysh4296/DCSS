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
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.soup.R;
import com.example.soup.databinding.ActivityMainBinding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> resultLauncher;

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
                            Log.d( "test","return");
                        }
                    }
                }
        );
    }

    public void initializeDB(View view){
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        sqlDB.execSQL("drop table if exists Charactor_table");
        sqlDB.execSQL("drop table if exists Item_table");
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
        Log.d("test","insert into Item_table values('" +
                        "3' , '" +
                        "1' , '" +
                        "3' , '" +
                        "0' , '" +
                        "0' , '" +
                        "0' , '" +
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
                "0' , '" +
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
                "4' , '" +
                "3' , '" +
                "0' , '" +
                "0' , '" +
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
                "0' , '" +
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
    public void btnClick(View view) {
        /*File dir = new File(filepath);
        if(!dir.exists()){
            dir.mkdir();
        }
        */
        File file = new File("testFile.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("hello");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Gatcha_Page(View view){
        Intent intent = new Intent(this,Gatcha.class);
        ArrayList<item_data> list = searchDB(this);
        Log.d("print d" , list.toString());
        intent.putExtra("item_data",list);
        resultLauncher.launch(intent);
    }
}