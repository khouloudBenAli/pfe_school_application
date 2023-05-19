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

public class presence_list extends AppCompatActivity {

    ProgressDialog dialog;
    JSONParser parser =new JSONParser() ;
    ArrayList<HashMap<String,String>> values= new ArrayList<HashMap<String,String>>() ;
    int success ;
    ListView ls ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presence_list);

        ls = findViewById(R.id.pres_list);

        int IndexSeance = getIntent().getIntExtra("index_seance", 0);
        int IndexClasse = getIntent().getIntExtra("index_classe", 0);

        String phpFile;

        switch (IndexClasse) {
            case 0:
                switch (IndexSeance) {
                    case 0:
                        phpFile = "http://192.168.77.192/user/admin/1AS1.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.77.192/user/admin/1AS2.php";
                        break;
                    case 2:
                        phpFile = "http://192.168.77.192/user/admin/1AS3.php";
                        break;
                    case 3:
                        phpFile = "http://192.168.77.192/user/admin/1AS4.php";
                        break;
                    case 4:
                        phpFile = "http://192.168.77.192/user/admin/1AS5.php";
                        break;
                    case 5:
                        phpFile = "http://192.168.77.192/user/admin/1AS6.php";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid IndexSeance: " + IndexSeance);
                }
                break;
            case 1:
                switch (IndexSeance) {
                    case 0:
                        phpFile = "http://192.168.77.192/user/admin/1BS1.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.77.192/user/admin/1BS2.php";
                        break;
                    case 2:
                        phpFile = "http://192.168.77.192/user/admin/1BS3.php";
                        break;
                    case 3:
                        phpFile = "http://192.168.77.192/user/admin/1BS4.php";
                        break;
                    case 4:
                        phpFile = "http://192.168.77.192/user/admin/1BS5.php";
                        break;
                    case 5:
                        phpFile = "http://192.168.77.192/user/admin/1BS6.php";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid IndexSeance: " + IndexSeance);
                }
                break;
            case 2:
                switch (IndexSeance) {
                    case 0:
                        phpFile = "http://192.168.77.192/user/admin/2AS1.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.77.192/user/admin/2AS2.php";
                        break;
                    case 2:
                        phpFile = "http://192.168.77.192/user/admin/2AS3.php";
                        break;
                    case 3:
                        phpFile = "http://192.168.77.192/user/admin/2AS4.php";
                        break;
                    case 4:
                        phpFile = "http://192.168.77.192/user/admin/2AS5.php";
                        break;
                    case 5:
                        phpFile = "http://192.168.77.192/user/admin/2AS6.php";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid IndexSeance: " + IndexSeance);
                }
                break;
            case 3:
                switch (IndexSeance) {
                    case 0:
                        phpFile = "http://192.168.77.192/user/admin/2BS1.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.77.192/user/admin/2BS2.php";
                        break;
                    case 2:
                        phpFile = "http://192.168.1.103/user/admin/2BS3.php";
                        break;
                    case 3:
                        phpFile = "http://192.168.1.103/user/admin/2BS4.php";
                        break;
                    case 4:
                        phpFile = "http://192.168.1.103/user/admin/2BS5.php";
                        break;
                    case 5:
                        phpFile = "http://192.168.1.103/user/admin/2BS6.php";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid IndexSeance: " + IndexSeance);
                }
                break;
            case 4:
                switch (IndexSeance) {
                    case 0:
                        phpFile = "http://192.168.1.103/user/admin/3AS1.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.1.103/user/admin/3AS2.php";
                        break;
                    case 2:
                        phpFile = "http://192.168.1.103/user/admin/3AS3.php";
                        break;
                    case 3:
                        phpFile = "http://192.168.1.103/user/admin/3AS4.php";
                        break;
                    case 4:
                        phpFile = "http://192.168.1.103/user/admin/3AS5.php";
                        break;
                    case 5:
                        phpFile = "http://192.168.1.103/user/admin/3AS6.php";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid IndexSeance: " + IndexSeance);
                }
                break;
            case 5:
                switch (IndexSeance) {
                    case 0:
                        phpFile = "http://192.168.1.103/user/admin/3BS1.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.1.103/user/admin/3BS2.php";
                        break;
                    case 2:
                        phpFile = "http://192.168.1.103/user/admin/3BS3.php";
                        break;
                    case 3:
                        phpFile = "http://192.168.1.103/user/admin/3BS4.php";
                        break;
                    case 4:
                        phpFile = "http://192.168.1.103/user/admin/3BS5.php";
                        break;
                    case 5:
                        phpFile = "http://192.168.1.103/user/admin/3BS6.php";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid IndexSeance: " + IndexSeance);
                }
                break;
            case 6:
                switch (IndexSeance) {
                    case 0:
                        phpFile = "http://192.168.1.103/user/admin/4AS1.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.1.103/user/admin/4AS2.php";
                        break;
                    case 2:
                        phpFile = "http://192.168.1.103/user/admin/4AS3.php";
                        break;
                    case 3:
                        phpFile = "http://192.168.1.103/user/admin/4AS4.php";
                        break;
                    case 4:
                        phpFile = "http://192.168.1.103/user/admin/4AS5.php";
                        break;
                    case 5:
                        phpFile = "http://192.168.1.103/user/admin/4AS6.php";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid IndexSeance: " + IndexSeance);
                }
                break;
            case 7:
                switch (IndexSeance) {
                    case 0:
                        phpFile = "http://192.168.1.103/user/admin/4BS1.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.1.103/user/admin/4BS2.php";
                        break;
                    case 2:
                        phpFile = "http://192.168.1.103/user/admin/4BS3.php";
                        break;
                    case 3:
                        phpFile = "http://192.168.1.103/user/admin/4BS4.php";
                        break;
                    case 4:
                        phpFile = "http://192.168.1.103/user/admin/4BS5.php";
                        break;
                    case 5:
                        phpFile = "http://192.168.1.103/user/admin/4BS6.php";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid IndexSeance: " + IndexSeance);
                }
                break;
            case 8:
                switch (IndexSeance) {
                    case 0:
                        phpFile = "http://192.168.1.103/user/admin/5AS1.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.1.103/user/admin/5AS2.php";
                        break;
                    case 2:
                        phpFile = "http://192.168.1.103/user/admin/5AS3.php";
                        break;
                    case 3:
                        phpFile = "http://192.168.1.103/user/admin/5AS4.php";
                        break;
                    case 4:
                        phpFile = "http://192.168.1.103/user/admin/5AS5.php";
                        break;
                    case 5:
                        phpFile = "http://192.168.1.103/user/admin/5AS6.php";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid IndexSeance: " + IndexSeance);
                }
                break;
            case 9:
                switch (IndexSeance) {
                    case 0:
                        phpFile = "http://192.168.1.103/user/admin/5BS1.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.1.103/user/admin/5BS2.php";
                        break;
                    case 2:
                        phpFile = "http://192.168.1.103/user/admin/5BS3.php";
                        break;
                    case 3:
                        phpFile = "http://192.168.1.103/user/admin/5BS4.php";
                        break;
                    case 4:
                        phpFile = "http://192.168.1.103/user/admin/5BS5.php";
                        break;
                    case 5:
                        phpFile = "http://192.168.1.103/user/admin/5BS6.php";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid IndexSeance: " + IndexSeance);
                }
                break;
            case 10:
                switch (IndexSeance) {
                    case 0:
                        phpFile = "http://192.168.1.103/user/admin/6AS1.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.1.103/user/admin/6AS2.php";
                        break;
                    case 2:
                        phpFile = "http://192.168.1.103/user/admin/6AS3.php";
                        break;
                    case 3:
                        phpFile = "http://192.168.1.103/user/admin/6AS4.php";
                        break;
                    case 4:
                        phpFile = "http://192.168.1.103/user/admin/6AS5.php";
                        break;
                    case 5:
                        phpFile = "http://192.168.1.103/user/admin/6AS6.php";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid IndexSeance: " + IndexSeance);
                }
                break;
            case 11:
                switch (IndexSeance) {
                    case 0:
                        phpFile = "http://192.168.1.103/user/admin/6BS1.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.1.103/user/admin/6BS2.php";
                        break;
                    case 2:
                        phpFile = "http://192.168.1.103/user/admin/6BS3.php";
                        break;
                    case 3:
                        phpFile = "http://192.168.1.103/user/admin/6BS4.php";
                        break;
                    case 4:
                        phpFile = "http://192.168.1.103/user/admin/6BS5.php";
                        break;
                    case 5:
                        phpFile = "http://192.168.1.103/user/admin/6BS6.php";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid IndexSeance: " + IndexSeance);
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid IndexClasse: " + IndexClasse);
        }

        new showlst().execute(phpFile);

    }

    class showlst extends AsyncTask<String, String, JSONObject> {

        // show a progress dialog before executing the task
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(presence_list.this);
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
                    JSONArray lst = object.getJSONArray("lst");
                    for (int i = 0; i < lst.length(); i++) {
                        JSONObject ls = lst.getJSONObject(i);
                        HashMap<String, String> m = new HashMap<String, String>();
                        m.put("name", ls.getString("name"));
                        m.put("jour", ls.getString("jour"));

                        values.add(m);
                    }
                }
                SimpleAdapter adapter = new SimpleAdapter(presence_list.this, values, R.layout.item_precence_list,
                        new String[]{"name","jour"},
                        new int[]{ R.id.pres_list_name ,R.id.pres_list_jour });
                ls.setAdapter(adapter);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

}