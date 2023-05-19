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

public class presence_prof extends AppCompatActivity {

    ListView ls ;
    ProgressDialog dialog;
    JSONParser parser =new JSONParser() ;
    ArrayList<HashMap<String,String>> values= new ArrayList<HashMap<String,String>>() ;
    int success ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presence_prof);

        ls = findViewById(R.id.lst_presence_prof);
        new PresenceProf().execute();
    }

    class PresenceProf extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(presence_prof.this);
            dialog.setMessage("Wait please ..");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map=new HashMap<String,String>();

            JSONObject object= parser.makeHttpRequest("http://192.168.77.192/user/presence/presence_prof.php","GET",map);
            try {
                success=object.getInt("success");
                if (success==1)
                {
                    JSONArray prof=object.getJSONArray("prof");
                    for(int i=0 ;i<prof.length();i++)
                    {
                        JSONObject pf=prof.getJSONObject(i);
                        HashMap<String,String> m=new HashMap<String,String>();
                        m.put("id_prof",pf.getString("id_prof"));
                        m.put("id_seance",pf.getString("id_seance"));
                        m.put("status_prof",pf.getString("status_prof"));


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

            SimpleAdapter adapter=new SimpleAdapter(presence_prof.this,values,R.layout.item_presence_prof,
                    new String[] {"id_prof","id_seance","status_prof"},
                    new int[]   {R.id.lsidprof , R.id.lsidseance, R.id.lsstatusprof  } );
            ls.setAdapter(adapter);

        }
    }
}