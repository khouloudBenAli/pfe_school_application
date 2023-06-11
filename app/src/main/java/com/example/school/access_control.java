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

public class access_control extends AppCompatActivity {

    ProgressDialog dialog;
    JSONParser parser =new JSONParser() ;
    ArrayList<HashMap<String,String>> values= new ArrayList<HashMap<String,String>>() ;
    int success ;
    ListView ls ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_control);

        ls = findViewById(R.id.lst_control_access);
        new accesControl().execute();
    }

    class accesControl extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(access_control.this);
            dialog.setMessage("Wait please ..");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map=new HashMap<String,String>();
            JSONObject object= parser.makeHttpRequest("http://192.168.1.103/user/accessControl_accessHistorique/access_control.php","GET",map);
            try {
                success=object.getInt("success");
                if (success==1)
                {
                    JSONArray acces=object.getJSONArray("acces");
                    for(int i=0 ;i<acces.length();i++)
                    {
                        JSONObject ps=acces.getJSONObject(i);
                        HashMap<String,String> m=new HashMap<String,String>();
                        m.put("id_prof",ps.getString("id_prof"));
                        m.put("full_name",ps.getString("full_name"));
                        m.put("id_salle",ps.getString("id_salle"));

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

            SimpleAdapter adapter=new SimpleAdapter(access_control.this,values,R.layout.item_access_control,
                    new String[] {"id_prof","full_name","id_salle"},
                    new int[]   {R.id.lsidprof , R.id.lsidfullname, R.id.lsidsalle  } );
            ls.setAdapter(adapter);

        }
    }
}