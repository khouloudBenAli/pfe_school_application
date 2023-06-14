package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class schedule extends AppCompatActivity {
    String id_prof;
    String daysListe[]={"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};
    int daysImages[] = { R.drawable.monday , R.drawable.tuesday , R.drawable.wednesday, R.drawable.thursday , R.drawable.friday };
    ListView lsdays ;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        id_prof = getIntent().getStringExtra("id_prof");

        lsdays=findViewById(R.id.schedule_ls);
        lsdays.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
                Intent intent = new Intent(schedule.this, day_planning.class);
                intent.putExtra("item_index", itemIndex);
                intent.putExtra("id_prof", id_prof);
                startActivity(intent);
            }
        });

        ClassesAdapter_schedule classesAdapter = new ClassesAdapter_schedule(getApplicationContext(), daysListe , daysImages );
        lsdays.setAdapter(classesAdapter);
        }
    }