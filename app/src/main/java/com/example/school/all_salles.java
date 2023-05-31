package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class all_salles extends AppCompatActivity {

    String sallesListe[]={"Salles A", "Salles B", "Salles C"};
    int sallesImages[] = { R.drawable.lettre_a , R.drawable.lettre_b , R.drawable.lettre_c };
    ListView lsSalles ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_salles);

        lsSalles=findViewById(R.id.salles_list_ls);

        ClassesAdapter_class_list classesAdapter = new ClassesAdapter_class_list(getApplicationContext(), sallesListe , sallesImages );
        lsSalles.setAdapter(classesAdapter);

        lsSalles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int IndexSalles, long l) {
                Intent intent = new Intent(all_salles.this, salle_number.class);
                intent.putExtra("index_salles", IndexSalles);
                startActivity(intent);
            }
        });
    }
}