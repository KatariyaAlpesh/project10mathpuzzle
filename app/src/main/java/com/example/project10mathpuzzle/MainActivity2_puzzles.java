package com.example.project10mathpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.Collections;
import java.util.zip.Inflater;

public class MainActivity2_puzzles extends AppCompatActivity {

GridView grid1Page;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2_puzzles);

        grid1Page = findViewById(R.id.grid1Page);

        MyAdapter arrayAdapter = new MyAdapter(this);
        grid1Page.setAdapter(arrayAdapter);

    }
}