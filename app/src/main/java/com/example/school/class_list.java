package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class class_list extends AppCompatActivity {
    String classListe[]={"Premier", "Deuxieme", "Troisieme", "Quatrieme", "Cinquieme", "Sixieme"};
    int classImages[] = { R.drawable.un , R.drawable.deux , R.drawable.trois, R.drawable.quatre , R.drawable.cinque , R.drawable.six };
    ListView lsclass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);

        lsclass=findViewById(R.id.class_list_ls);

        ClassesAdapter_class_list classesAdapter = new ClassesAdapter_class_list(getApplicationContext(), classListe , classImages );
        lsclass.setAdapter(classesAdapter);

        lsclass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
                Intent intent = new Intent(class_list.this, class_planning.class);
                intent.putExtra("item_index", itemIndex);
                startActivity(intent);
            }
        });

    }
}