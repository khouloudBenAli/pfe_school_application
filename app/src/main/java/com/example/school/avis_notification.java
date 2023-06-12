package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class avis_notification extends AppCompatActivity {

    ImageButton notification , ajout_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avis_notification);
        notification = findViewById(R.id.ib_avis_notification_admin);
        ajout_notification = findViewById(R.id.ib_ajout_avis);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(avis_notification.this,avis_list.class);
                startActivity(intent);
            }
        });

        ajout_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(avis_notification.this,ajout_notification.class);
                startActivity(intent);
            }
        });

    }
}