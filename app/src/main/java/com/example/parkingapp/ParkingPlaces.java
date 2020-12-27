package com.example.parkingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class ParkingPlaces extends AppCompatActivity {

    RecyclerView myRecyclerView;
    ParkingPlacesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_places);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        List<String> pSkopje = Arrays.asList("Skopje", "Bitola");
        List<String> pOhrid = Arrays.asList("1");
        List<String> pBitola = Arrays.asList("1");
        List<String> pPrilep = Arrays.asList("1");
        List<String> lista = Arrays.asList("");

        Intent intent = getIntent();
        String grad = intent.getStringExtra("grad");

        if(grad.equals("Скопје")) {
            lista = pSkopje;
        }
        if(grad.equals("Битола")) {
            lista = pBitola;
        }
        if(grad.equals("Охрид")) {
            lista = pOhrid;
        }
        if(grad.equals("Прилеп")) {
            lista = pPrilep;
        }

        myRecyclerView = (RecyclerView) findViewById(R.id.pRview);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new ParkingPlacesAdapter(lista, R.layout.parking_layout, this, grad);
        myRecyclerView.setAdapter(adapter);
    }
}