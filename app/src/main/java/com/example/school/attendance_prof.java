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

public class attendance_prof extends AppCompatActivity {

    ListView ls ;
    ProgressDialog dialog;
    JSONParser parser =new JSONParser() ;
    ArrayList<HashMap<String,String>> values= new ArrayList<HashMap<String,String>>() ;
    int success ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_prof);


        ls = findViewById(R.id.lst_attendance_prof);
        new AttendanceProf().execute();
    }

    class AttendanceProf extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(attendance_prof.this);
            dialog.setMessage("Wait please ..");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map=new HashMap<String,String>();

            JSONObject object= parser.makeHttpRequest("http://192.168.77.192/user/presence/attendance_prof.php","GET",map);
            try {
                success=object.getInt("success");
                if (success==1)
                {
                    JSONArray prof=object.getJSONArray("prof");
                    for(int i=0 ;i<prof.length();i++)
                    {
                        JSONObject pf=prof.getJSONObject(i);
                        HashMap<String,String> m=new HashMap<String,String>();
                        m.put("full_name",pf.getString("full_name"));
                        m.put("num_seance",pf.getString("num_seance"));
                        m.put("status_prof",pf.getString("status_prof"));
                        m.put("jour",pf.getString("jour"));
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

            SimpleAdapter adapter=new SimpleAdapter(attendance_prof.this,values,R.layout.item_attendance_prof,
                    new String[] {"full_name","num_seance","status_prof","jour" },
                    new int[]   {R.id.lsidfullname , R.id.lsidnumseance, R.id.lsstatusprof , R.id.lsdate } );
            ls.setAdapter(adapter);

        }
    }
    }


