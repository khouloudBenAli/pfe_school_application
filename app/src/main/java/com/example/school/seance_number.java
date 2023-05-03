package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class seance_number extends AppCompatActivity {

    String seanceListe[]={"Premier Seance", "Deuxieme Seance", "Troisieme Seance", "Quatrieme Seance", "Cinquieme Seance", "Sixieme Seance"};
    int seanceImages[] = { R.drawable.un , R.drawable.deux , R.drawable.trois, R.drawable.quatre , R.drawable.cinque , R.drawable.six };
    ListView lsSeance ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seance_number);

        lsSeance=findViewById(R.id.seance_list_ls);

        ClassesAdapter_class_list classesAdapter = new ClassesAdapter_class_list(getApplicationContext(), seanceListe , seanceImages );
        lsSeance.setAdapter(classesAdapter);

        int IndexClasse = getIntent().getIntExtra("index_classe", 0);

        lsSeance.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int IndexSeance, long l) {
                Intent intent = new Intent(seance_number.this, presence_list.class);
                intent.putExtra("index_seance", IndexSeance);
                intent.putExtra("index_classe", IndexClasse);
                startActivity(intent);
            }
        });

    }
}