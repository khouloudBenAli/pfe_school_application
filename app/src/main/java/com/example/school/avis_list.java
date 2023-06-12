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

public class avis_list extends AppCompatActivity {

    ListView ls_avis ;
    ProgressDialog dialog;
    JSONParser parser =new JSONParser() ;
    ArrayList<HashMap<String,String>> values= new ArrayList<HashMap<String,String>>() ;
    int success ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avis_list);

        ls_avis = findViewById(R.id.lst_avis);
        new AllAvis().execute();
    }

    class AllAvis extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(avis_list.this);
            dialog.setMessage("Wait please ..");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map=new HashMap<String,String>();

            JSONObject object= parser.makeHttpRequest("http://192.168.1.103/user/avis_notification/avis_list.php","GET",map);
            try {
                success=object.getInt("success");
                if (success==1)
                {
                    JSONArray avis=object.getJSONArray("avis");
                    for(int i=0 ;i<avis.length();i++)
                    {
                        JSONObject ev=avis.getJSONObject(i);
                        HashMap<String,String> m=new HashMap<String,String>();
                        m.put("avis",ev.getString("avis"));
                        m.put("date_avis",ev.getString("date_avis"));
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
            Log.e("resultat_notification", values.toString());

            SimpleAdapter adapter=new SimpleAdapter(avis_list.this,values,R.layout.item_avis,
                    new String[] {"avis","date_avis"},
                    new int[]   {R.id.lsavis , R.id.lsdate_avis } );
            ls_avis.setAdapter(adapter);

        }
    }
}