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

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //khouloud thb mazen

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
                                    // String typemsg = jsonObject.getString("typemsg");
                                    if (success == 1) {
                                        if (type == 1) {
                                            // Login successful for admin, navigate to the activity espace
                                            Intent intent = new Intent(MainActivity.this, admin.class);
                                            startActivity(intent);
                                            finish();
                                        } else if (type == 2) {
                                            // Login successful for professeur, navigate to the activity espaceProf
                                            Intent intent = new Intent(MainActivity.this, users.class);
                                            startActivity(intent);
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
