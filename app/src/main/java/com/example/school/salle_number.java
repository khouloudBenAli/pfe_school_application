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

public class salle_number extends AppCompatActivity {

    ProgressDialog dialog;
    JSONParser parser =new JSONParser() ;
    ArrayList<HashMap<String,String>> values= new ArrayList<HashMap<String,String>>() ;
    int success ;
    ListView ls ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salle_number);

        ls = findViewById(R.id.salle_number_list);

        int IndexSalles = getIntent().getIntExtra("index_salles", 0);

        String phpFile;
        switch (IndexSalles) {
            case 0:
                phpFile = "http://192.168.1.103/user/accessControl_accessHistorique/Salles/sallesA.php";
                break;
            case 1:
                phpFile = "http://192.168.1.103/user/accessControl_accessHistorique/Salles/sallesB.php";
                break;
            case 2:
                phpFile = "http://192.168.1.103/user/accessControl_accessHistorique/Salles/sallesC.php";
                break;
            default:
                throw new IllegalArgumentException("Invalid itemIndex: " + IndexSalles);
        }

        new salle_number.salles().execute(phpFile);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int IndexNumSalle, long l) {
                Intent intent = new Intent(salle_number.this, historique_salle.class);
                intent.putExtra("index_num_salle", IndexNumSalle);
                intent.putExtra("index_salles", IndexSalles);
                startActivity(intent);
            }
        });


    }

    class salles extends AsyncTask<String, String, JSONObject> {

        // show a progress dialog before executing the task
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(salle_number.this);
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
                    JSONArray etage = object.getJSONArray("etage");
                    for (int i = 0; i < etage.length(); i++) {
                        JSONObject ls = etage.getJSONObject(i);
                        HashMap<String, String> m = new HashMap<String, String>();
                        m.put("id_salle", ls.getString("id_salle"));


                        values.add(m);
                    }
                }
                SimpleAdapter adapter = new SimpleAdapter(salle_number.this, values, R.layout.item_salle_number,
                        new String[]{"id_salle"},
                        new int[]{ R.id.salle_id  });
                ls.setAdapter(adapter);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}