package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class class_planning extends AppCompatActivity {

    ProgressDialog dialog;
    JSONParser parser =new JSONParser() ;
    ArrayList<HashMap<String,String>> values= new ArrayList<HashMap<String,String>>() ;
    int success ;
    ListView ls ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_planning);

        ls = findViewById(R.id.lst_planning_class);
        int itemIndex = getIntent().getIntExtra("item_index", 0);


        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemClasse, long l) {
                Intent intent = new Intent(class_planning.this, student_list.class);
                intent.putExtra("item_classe", itemClasse);
                intent.putExtra("item_index", itemIndex);
                startActivity(intent);
            }
        });

        String phpFile;
        switch (itemIndex) {
            case 0:
                phpFile = "http://192.168.208.154/user/classes/premier.php";
                break;
            case 1:
                phpFile = "http://192.168.208.154/user/classes/deuxieme.php";
                break;
            case 2:
                phpFile = "http://192.168.208.154/user/classes/troisieme.php";
                break;
            case 3:
                phpFile = "http://192.168.208.154/user/classes/quatrieme.php";
                break;
            case 4:
                phpFile = "http://192.168.208.154/user/classes/cinquieme.php";
                break;
            case 5:
                phpFile = "http://192.168.208.154/user/classes/sixieme.php";
                break;
            default:
                throw new IllegalArgumentException("Invalid itemIndex: " + itemIndex);
        }

        new class_planning.DisplayClasses().execute(phpFile);


    }

    class DisplayClasses extends AsyncTask<String, String, JSONObject> {

        // show a progress dialog before executing the task
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(class_planning.this);
            dialog.setMessage("Wait please ..");
            dialog.show();
        }

        // execute the PHP file in the background
        @Override
        protected JSONObject doInBackground(String... args) {
            String phpFile = args[0];
            HashMap<String, String> map = new HashMap<String, String>();
            return parser.makeHttpRequest(phpFile, "GET", map);
        }

        // update the UI with the results
        @Override
        protected void onPostExecute(JSONObject object) {
            super.onPostExecute(object);
            dialog.dismiss();
            try {
                success = object.getInt("success");
                if (success == 1) {
                    JSONArray niveaux = object.getJSONArray("niveaux");
                    for (int i = 0; i < niveaux.length(); i++) {
                        JSONObject nv = niveaux.getJSONObject(i);
                        HashMap<String, String> m = new HashMap<String, String>();
                        m.put("id_classe", nv.getString("id_classe"));
                        //m.put("description", nv.getString("description"));
                        values.add(m);
                    }
                }
                SimpleAdapter adapter = new SimpleAdapter(class_planning.this, values, R.layout.item_class_planning,
                        new String[]{"id_classe", "description"},
                        new int[]{ R.id.lsidclasse , R.id.lsiddescription});
                ls.setAdapter(adapter);

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }


}