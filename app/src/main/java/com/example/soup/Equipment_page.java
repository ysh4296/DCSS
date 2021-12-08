package com.example.soup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.soup.databinding.ActivityEquipmentPageBinding;
import com.example.soup.databinding.ActivitySkillPageBinding;

import java.util.ArrayList;

public class Equipment_page extends AppCompatActivity {
    private @NonNull ActivityEquipmentPageBinding binding;
    private RecyclerView item_RecyclerView;
    private Adapter item_RecyclerAdapter;
    ArrayList<item_data> bag = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEquipmentPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Bundle extras = getIntent().getExtras();
        bag = (ArrayList<item_data>) getIntent().getSerializableExtra("item_bag");

        item_RecyclerAdapter = new Adapter();
        item_RecyclerAdapter.setitemlist(temp_item);
        item_RecyclerView = (RecyclerView) binding.itemList;
        item_RecyclerView.setAdapter(item_RecyclerAdapter);
        item_RecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}