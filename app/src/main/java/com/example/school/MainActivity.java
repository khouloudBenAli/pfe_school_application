package com.example.school;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.school.R;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
//Hello from the testMain
    static EditText usernameEditText;
    static EditText passwordEditText;
    static Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] id_prof = {""};

        usernameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Send the login request to the server using Volley
                String url = "http://192.168.77.192/user/appLogin.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    int success = jsonObject.getInt("success");
                                    int type = jsonObject.getInt("type");
                                    String message = jsonObject.getString("message");

                                    id_prof[0] = jsonObject.getString("id_prof");
                                    Intent intent;
                                    if (success == 1) {
                                        if (type == 1) {
                                            // Login successful for admin, navigate to the activity espace
                                             intent = new Intent(MainActivity.this, admin.class);
                                            intent.putExtra("id_prof", id_prof);
                                            startActivity(intent);
                                            finish();
                                        } else if (type == 2) {
                                            // Login successful for professeur, navigate to the activity espaceProf
                                             intent = new Intent(MainActivity.this, users.class);
                                            startActivity(intent);
                                            //intent.putExtra("id_prof", id_prof);
                                            finish();
                                        } else {
                                            // Login failed, show an error message
                                            Toast.makeText(MainActivity.this, "Invalid User Type", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        // Login failed, show an error message
                                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("username", username);
                        params.put("password", password);
                        return params;
                    }
                };

                Volley.newRequestQueue(MainActivity.this).add(stringRequest);
            }
        });
    }
}
