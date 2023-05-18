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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class all_classes extends AppCompatActivity {

    ListView ls_all ;
    ProgressDialog dialog;
    JSONParser parser =new JSONParser() ;
    ArrayList<HashMap<String,String>> values= new ArrayList<HashMap<String,String>>() ;
    int success ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_classes);

        ls_all = findViewById(R.id.lst_all_class);
        new AllClasses().execute();


        ls_all.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int IndexClasse, long l) {
                Intent intent = new Intent(all_classes.this, seance_number.class);
                intent.putExtra("index_classe", IndexClasse);
                startActivity(intent);
            }
        });

    }

    class AllClasses extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(all_classes.this);
            dialog.setMessage("Wait please ..");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map=new HashMap<String,String>();

            JSONObject object= parser.makeHttpRequest("http://192.168.208.154/user/classes/all_classes.php","GET",map);
            try {
                success=object.getInt("success");
                if (success==1)
                {
                    JSONArray every=object.getJSONArray("every");
                    for(int i=0 ;i<every.length();i++)
                    {
                        JSONObject ev=every.getJSONObject(i);
                        HashMap<String,String> m=new HashMap<String,String>();
                        m.put("id_classe",ev.getString("id_classe"));
                       // m.put("description",ev.getString("description"));


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

            SimpleAdapter adapter=new SimpleAdapter(all_classes.this,values,R.layout.item_all_classes,
                    new String[] {"id_classe","description"},
                    new int[]   {R.id.ls_id_all_classes , R.id.lsiddes } );
            ls_all.setAdapter(adapter);

        }
    }

}