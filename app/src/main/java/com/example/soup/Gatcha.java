package com.example.soup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.soup.databinding.ActivityGatchaBinding;

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

        /**adapt data**/
        ArrayList<item_info> temp_item = new ArrayList<>();
        temp_item.add(new item_info(R.raw.double_sword,"A","sword"));
        temp_item.add(new item_info(R.raw.double_sword,"B","sword"));
        temp_item.add(new item_info(R.raw.double_sword,"C","sword"));
        temp_item.add(new item_info(R.raw.double_sword,"D","sword"));
        item_RecyclerAdapter = new Adapter();
        item_RecyclerAdapter.setitemlist(temp_item);
        item_RecyclerView = (RecyclerView) binding.itemList;
        item_RecyclerView.setAdapter(item_RecyclerAdapter);
        item_RecyclerView.setLayoutManager(new LinearLayoutManager(this));


        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }
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