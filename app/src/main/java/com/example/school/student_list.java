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

public class student_list extends AppCompatActivity {
    ProgressDialog dialog;
    JSONParser parser =new JSONParser() ;
    ArrayList<HashMap<String,String>> values= new ArrayList<HashMap<String,String>>() ;
    int success ;
    ListView ls ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        ls = findViewById(R.id.lst_student_list);

        /*int itemClasse = getIntent().getIntExtra("item_classe", 0);
        int itemIndex = getIntent().getIntExtra("item_index", 0);*/

        int itemIndex = getIntent().getIntExtra("item_index", 0); //quel niveau cliquer
        int itemClasse = getIntent().getIntExtra("item_classe", 0); // quel classe clique
        String phpFile;

        switch (itemIndex) {
            case 0:
                switch (itemClasse) {
                    case 0:
                        phpFile = "http://192.168.208.154/user/studentlist/student_list_1A.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.208.154/user/studentlist/student_list_1B.php";
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid IndexClasse: " + itemClasse);
                }
                break;
            case 1:
                switch (itemClasse) {
                    case 0:
                        phpFile = "http://192.168.208.154/user/studentlist/student_list_2A.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.208.154/user/studentlist/student_list_2B.php";
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid IndexClasse: " + itemClasse);
                }
                break;
            case 2:
                switch (itemClasse) {
                    case 0:
                        phpFile = "http://192.168.208.154/user/studentlist/student_list_3A.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.208.154/user/studentlist/student_list_3B.php";
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid IndexClasse: " + itemClasse);
                }
                break;
            case 3:
                switch (itemClasse) {
                    case 0:
                        phpFile = "http://192.168.208.154/user/studentlist/student_list_4A.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.208.154/user/studentlist/student_list_4B.php";
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid IndexClasse: " + itemClasse);
                }
                break;
            case 4:
                switch (itemClasse) {
                    case 0:
                        phpFile = "http://192.168.208.154/user/studentlist/student_list_5A.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.208.154/user/studentlist/student_list_5B.php";
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid IndexClasse: " + itemClasse);
                }
                break;
            case 5:
                switch (itemClasse) {
                    case 0:
                        phpFile = "http://192.168.208.154/user/studentlist/student_list_6A.php";
                        break;
                    case 1:
                        phpFile = "http://192.168.208.154/user/studentlist/student_list_6B.php";
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid IndexClasse: " + itemClasse);
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid IndexNiveaux: " + itemIndex);
        }

        new showlst_student().execute(phpFile);

    }

    class showlst_student extends AsyncTask<String, String, JSONObject> {

        // show a progress dialog before executing the task
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(student_list.this);
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
                    JSONArray student = object.getJSONArray("student");
                    for (int i = 0; i < student.length(); i++) {
                        JSONObject eleve = student.getJSONObject(i);
                        HashMap<String, String> m = new HashMap<String, String>();
                        m.put("age", eleve.getString("age"));
                        m.put("name", eleve.getString("name"));
                        m.put("lastname", eleve.getString("lastname"));

                        values.add(m);
                    }
                }
                SimpleAdapter adapter = new SimpleAdapter(student_list.this, values, R.layout.item_student_list,
                        new String[]{"age","name","lastname"},
                        new int[]{ R.id.lsidstudent , R.id.lsidname , R.id.lsidlastname});
                ls.setAdapter(adapter);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}