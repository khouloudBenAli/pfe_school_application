package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class admin extends AppCompatActivity {
    ImageButton all_classes ,  all_niveaux , attendance , AvisNotification , historique ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        all_niveaux=findViewById(R.id.ib_niveaux);
        all_classes=findViewById(R.id.ib_classes);
        attendance=findViewById(R.id.ib_attendance);
        AvisNotification=findViewById(R.id.ib_Avis_Notification);
        historique=findViewById(R.id.ib_historique);

        AvisNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin.this,avis_notification.class);
                startActivity(intent);
            }
        });

        historique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin.this,all_salles.class);
                startActivity(intent);
            }
        });

        all_niveaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin.this,class_list.class);
                startActivity(intent);
            }
        });

        all_classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin.this,all_classes.class);
                startActivity(intent);
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin.this,attendance_prof.class);
                startActivity(intent);
            }
        });

    }
}