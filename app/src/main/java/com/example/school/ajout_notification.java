package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ajout_notification extends AppCompatActivity {

    EditText ajoutAvis ;
    Button Ajout ;

    ProgressDialog dialog;
    JSONParser parser=new JSONParser();
    int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_notification);

        ajoutAvis = findViewById(R.id.txt_ajout) ;
        Ajout = findViewById(R.id.bt_ajout);

        Ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Add().execute();
            }
        });
    }

    class Add extends AsyncTask<String, String,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(ajout_notification.this);
            dialog.setMessage("Wait plz");
            dialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map=new HashMap<String,String>();
            map.put("avis",ajoutAvis.getText().toString() );



            JSONObject object=parser.makeHttpRequest("http://192.168.1.103/user/avis_notification/add_avis.php","GET",map);

            try {
                success=object.getInt("success");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.cancel();
            if(success==1)
            {
                Toast.makeText(ajout_notification.this,"Add successfully",Toast.LENGTH_LONG).show();
                /*Intent i = new Intent(ajout_notification.this,avis_list.class);
                startActivity(i);*/
            }
            else
            {
                Toast.makeText(ajout_notification.this,"Echec !!!!!",Toast.LENGTH_LONG).show();
            }
        }
    }

}