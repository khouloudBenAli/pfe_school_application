package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class historique_salle extends AppCompatActivity {

    ProgressDialog dialog;
    JSONParser parser =new JSONParser() ;
    ArrayList<HashMap<String,String>> values= new ArrayList<HashMap<String,String>>() ;
    int success ;
    ListView ls ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_salle);

        ls = findViewById(R.id.hist_list);

        int IndexNumSalle = getIntent().getIntExtra("index_num_salle", 0);
        int IndexSalles = getIntent().getIntExtra("index_salles", 0);

        String phpFile;
        switch (IndexSalles) {
            case 0:
                switch (IndexNumSalle) {
                    case 0:
                        phpFile = "http://192.168.1.103/user/accessControl_accessHistorique/num_salles/salleA1.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.1.103/user/accessControl_accessHistorique/num_salles/salleA2.php";
                        break;
                    case 2:
                        phpFile = "http://192.168.1.103/user/accessControl_accessHistorique/num_salles/salleA3.php";
                        break;
                    case 3:
                        phpFile = "http://192.168.1.103/user/accessControl_accessHistorique/num_salles/TPA1.php";
                        break;
                    case 4:
                        phpFile = "http://192.168.1.103/user/accessControl_accessHistorique/num_salles/TPA2.php";
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid IndexNumSalle: " + IndexNumSalle);
                }
                break;
            case 1:
                switch (IndexNumSalle) {
                    case 0:
                        phpFile = "http://192.168.1.103/user/accessControl_accessHistorique/num_salles/salleB1.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.1.103/user/accessControl_accessHistorique/num_salles/salleB2.php";
                        break;
                    case 2:
                        phpFile = "http://192.168.1.103/user/accessControl_accessHistorique/num_salles/salleB3.php";
                        break;
                    case 3:
                        phpFile = "http://192.168.1.103/user/accessControl_accessHistorique/num_salles/TPB1.php";
                        break;
                    case 4:
                        phpFile = "http://192.168.1.103/user/accessControl_accessHistorique/num_salles/TPB2.php";
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid IndexNumSalle: " + IndexNumSalle);
                }
                break;
            case 2:
                switch (IndexNumSalle) {
                    case 0:
                        phpFile = "http://192.168.1.103/user/accessControl_accessHistorique/num_salles/salleC1.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.1.103/user/accessControl_accessHistorique/num_salles/salleC2.php";
                        break;
                    case 2:
                        phpFile = "http://192.168.163.154/user/accessControl_accessHistorique/num_salles/salleC3.php";
                        break;
                    case 3:
                        phpFile = "http://192.168.163.154/user/accessControl_accessHistorique/num_salles/TPB1.php";
                        break;
                    case 4:
                        phpFile = "http://192.168.163.154/user/accessControl_accessHistorique/num_salles/TPB2.php";
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid IndexNumSalle: " + IndexNumSalle);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid IndexSalles: " + IndexSalles);
        }

        new historique().execute(phpFile);

    }

    class historique extends AsyncTask<String, String, JSONObject> {

        // show a progress dialog before executing the task
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(historique_salle.this);
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
                    JSONArray historique = object.getJSONArray("historique");
                    for (int i = 0; i < historique.length(); i++) {
                        JSONObject ls = historique.getJSONObject(i);
                        HashMap<String, String> m = new HashMap<String, String>();
                        m.put("id_prof", ls.getString("id_prof"));
                        m.put("full_name", ls.getString("full_name"));
                        m.put("id_salle", ls.getString("id_salle"));
                        m.put("time", ls.getString("time"));

                        values.add(m);
                    }
                }
                SimpleAdapter adapter = new SimpleAdapter(historique_salle.this, values, R.layout.item_historique,
                        new String[]{"id_prof","full_name","id_salle","time"},
                        new int[]{ R.id.id_pro ,R.id.id_fullname, R.id.id_salle , R.id.id_time });
                ls.setAdapter(adapter);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}