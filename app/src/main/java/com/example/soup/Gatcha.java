package com.example.soup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.soup.databinding.ActivityGatchaBinding;
import com.example.soup.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class Gatcha extends AppCompatActivity {
    private @NonNull ActivityGatchaBinding binding;
    private RecyclerView item_RecyclerView;
    private Adapter item_RecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGatchaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);




        Bundle extras = getIntent().getExtras();
        ArrayList<item_data> list = (ArrayList<item_data>) getIntent().getSerializableExtra("item_data");
        if(extras == null){
            return;
        }



        /**adapt data**/
        ArrayList<item_info> temp_item = new ArrayList<>();
        Item_generator generator = new Item_generator();


        generator.roll_gatcha(list,10);

        temp_item.add(new item_info(R.raw.double_sword,"A","sword"));
        temp_item.add(new item_info(R.raw.double_sword,"B","sword"));
        temp_item.add(new item_info(R.raw.double_sword,"C","sword"));
        temp_item.add(new item_info(R.raw.double_sword,"D","sword"));
        item_RecyclerAdapter = new Adapter();
        item_RecyclerAdapter.setitemlist(temp_item);
        item_RecyclerView = (RecyclerView) binding.itemList;
        item_RecyclerView.setAdapter(item_RecyclerAdapter);
        item_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void Return(View view){
        finish();
    }
    @Override
    public void finish(){
        Intent data = new Intent();
        setResult(RESULT_OK,data);
        super.finish();
    }
}