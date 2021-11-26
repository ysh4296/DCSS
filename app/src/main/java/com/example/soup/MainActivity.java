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

        //Toast.makeText(getApplicationContext(),"Initialized",Toast.LENGTH_LONG).show();
    }
    public void insertDB(View view){
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        //sqlDB.execSQL("insert into groupTBL values('" + binding.editText1.getText().toString() + "' , '" + binding.editText2.getText().toString() + "','" + binding.editText2.getText().toString() +"');");
        sqlDB.close();
        Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_LONG).show();
    }
    public void searchDB(View view){
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("select * from groupTBL;" , null);
        String string1 = "Movie Title" + System.lineSeparator();
        String string2 = "Director" + System.lineSeparator();
        String string3 = "Director" + System.lineSeparator();
        string1 += "=================" + System.lineSeparator();
        string2 += "=================" + System.lineSeparator();
        string3 += "=================" + System.lineSeparator();
        while(cursor.moveToNext()){
            string1 += cursor.getString(0) + System.lineSeparator();
            string2 += cursor.getString(1) + System.lineSeparator();
            string3 += cursor.getString(2) + System.lineSeparator();
        }
        //binding.textView3.setText(string1);
        //binding.textView4.setText(string2);
        //binding.textView5.setText(string3);
        cursor.close();
        sqlDB.close();
        Toast.makeText(getApplicationContext(),"Searched",Toast.LENGTH_LONG).show();
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
        resultLauncher.launch(intent);
    }
}