package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.ImageButton;

public class users extends AppCompatActivity {

    ImageButton student_presence , schedule_prof,prof_presence ;
    String id_prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        id_prof = getIntent().getStringExtra("id_prof");
        Log.d("MainActivity", "id_prof: " + id_prof);

        schedule_prof=findViewById(R.id.scheduleProf) ;
        prof_presence=findViewById(R.id.PresenceProf) ;
        student_presence=findViewById(R.id.PresenceStudent);

        schedule_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(users.this , schedule.class);
                intent.putExtra("id_prof", id_prof);
                startActivity(intent);

            }
        });

        prof_presence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(users.this,presence_prof.class);
                i.putExtra("id_prof", id_prof);
                startActivity(i);
            }
        });

        student_presence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(users.this,presence_student.class);
                intent.putExtra("id_prof", id_prof);
                startActivity(intent);
            }
        });
    }


}