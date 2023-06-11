package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class day_planning extends AppCompatActivity {
    String id_prof;

    ProgressDialog dialog;
    JSONParser parser =new JSONParser() ;
    ArrayList<HashMap<String,String>> values= new ArrayList<HashMap<String,String>>() ;
    int success ;
    ListView ls ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_planning);

        id_prof = getIntent().getStringExtra("id_prof");

        ls = findViewById(R.id.lst_seance);

        int itemIndex = getIntent().getIntExtra("item_index", 0);
        String phpFile;
        switch (itemIndex) {
            case 0:
                phpFile = "http://192.168.1.103/user/days/monday.php";
                break;
            case 1:
                phpFile = "http://192.168.1.103/user/days/tuesday.php";
                break;
            case 2:
                phpFile = "http://192.168.1.103/user/days/wednesday.php";
                break;
            case 3:
                phpFile = "http://192.168.1.103/user/days/thursday.php";
                break;
            case 4:
                phpFile = "http://192.168.1.103/user/days/friday.php";
                break;
            case 5:
                phpFile = "http://192.168.1.103/user/days/saturday.php";
                break;
            default:
                throw new IllegalArgumentException("Invalid itemIndex: " + itemIndex);
        }

        new DisplayTask(id_prof).execute(phpFile, id_prof);
        //new DisplayTask(id_prof).execute(phpFile);
    }

    class DisplayTask extends AsyncTask<String, String, JSONObject> {
        private String idProf;

        public DisplayTask(String idProf) {
            this.idProf = idProf;
        }

        // show a progress dialog before executing the task
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(day_planning.this);
            dialog.setMessage("Wait please ..");
            dialog.show();
        }

        // execute the PHP file in the background
        @Override
        protected JSONObject doInBackground(String... args) {
            String phpFile = args[0];
            String idProf = args[1]; // Retrieve the id_prof value from the arguments
            HashMap<String, String> map = new HashMap<String, String>();
            // Add id_prof to the request parameters
            map.put("id_prof", idProf);
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
                    JSONArray emploi = object.getJSONArray("emploi");
                    for (int i = 0; i < emploi.length(); i++) {
                        JSONObject schedule = emploi.getJSONObject(i);
                        HashMap<String, String> m = new HashMap<String, String>();
                        m.put("id_emploi", schedule.getString("id_emploi"));
                        m.put("id_prof", schedule.getString("id_prof"));
                        m.put("id_classe", schedule.getString("id_classe"));
                        m.put("salle", schedule.getString("salle"));
                        m.put("num_seance", schedule.getString("num_seance"));
                        values.add(m);
                    }
                }
                SimpleAdapter adapter = new SimpleAdapter(day_planning.this, values, R.layout.item,
                        new String[]{"id_emploi","id_prof", "id_classe", "salle", "num_seance"},
                        new int[]{ R.id.lsemploi , R.id.lsprof, R.id.lsclasse, R.id.lssalle, R.id.lsseance});
                ls.setAdapter(adapter);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }


}