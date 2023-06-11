package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class presence_student extends AppCompatActivity {
    String id_prof;
    ListView ls_presence ;
    ProgressDialog dialog;
    JSONParser parser =new JSONParser() ;
    ArrayList<HashMap<String,String>> values= new ArrayList<HashMap<String,String>>() ;
    int success ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presence_student);

        id_prof = getIntent().getStringExtra("id_prof");

        ls_presence=findViewById(R.id.lst_presence_student);
        new PresenceProf().execute();
    }

    class PresenceProf extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(presence_student.this);
            dialog.setMessage("Wait please ..");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map=new HashMap<String,String>();
            map.put("id_prof", id_prof); // Add id_prof to the map

            JSONObject object= parser.makeHttpRequest("http://192.168.1.103/user/presence/presence_student.php","GET",map);
            try {
                success=object.getInt("success");
                if (success==1)
                {
                    JSONArray student=object.getJSONArray("student");
                    for(int i=0 ;i<student.length();i++)
                    {
                        JSONObject ps=student.getJSONObject(i);
                        HashMap<String,String> m=new HashMap<String,String>();
                        m.put("name",ps.getString("name"));
                        m.put("lastname",ps.getString("lastname"));
                        m.put("status_student",ps.getString("status_student"));
                        m.put("num_seance",ps.getString("num_seance"));
                        m.put("jour",ps.getString("jour"));

                        values.add(m);
                    }
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.cancel();
            Log.e("useeeer", values.toString());

            SimpleAdapter adapter=new SimpleAdapter(presence_student.this,values,R.layout.item_presence_student,
                    new String[] {"name","lastname","status_student","num_seance","jour"},
                    new int[]   {R.id.lsname , R.id.lslastname, R.id.lsstatusstudent , R.id.lsnum_seance ,R.id.lsjour  } );
            ls_presence.setAdapter(adapter);

        }
    }
}