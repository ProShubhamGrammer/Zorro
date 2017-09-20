package com.wallet.zorro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    Long mob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etMob=(EditText)findViewById(R.id.etMob);
        final EditText etPass=(EditText)findViewById(R.id.etPass);
        final Button bLogin=(Button)findViewById(R.id.bLogin);
        final TextView tvSignup=(TextView)findViewById(R.id.tvSignup);
        final TextView tvForgot=(TextView)findViewById(R.id.tvForgot);

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent=new Intent(LoginActivity.this,SignupActivity.class);
                LoginActivity.this.startActivity(signupIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etMob.getText().toString().length()!=10) {

                    Toast.makeText(LoginActivity.this, "Please Enter a Valid Mobile number ", Toast.LENGTH_LONG).show();
                }
                else
                {

                    mob = Long.parseLong(etMob.getText().toString());
                }
                final String pass = etPass.getText().toString();


                if (etMob.getText().toString().isEmpty()||pass.isEmpty()) {
                    if(!(etMob.getText().toString().isEmpty()))
                        Toast.makeText(LoginActivity.this,"Please enter the password! ", Toast.LENGTH_LONG).show();

                } else {
                    mob=Long.parseLong(etMob.getText().toString());

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {

                                JSONObject jsonResponse = new JSONObject(response);

                                boolean success = jsonResponse.getBoolean("success");


                                if (success) {
                                    Intent sIntent = new Intent(LoginActivity.this, WelcomeActivity.class);
                                    Toast.makeText(LoginActivity.this, "Welcome!", Toast.LENGTH_LONG).show();
                                    sIntent.putExtra("mob",mob);
                                    LoginActivity.this.startActivity(sIntent);
                                } else {
                                    Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    LoginRequest loginRequest = new LoginRequest(mob,pass,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(loginRequest);
                }

            }
        });
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fIntent=new Intent(LoginActivity.this,ForgotActivity.class);
                LoginActivity.this.startActivity(fIntent);
            }
        });


    }
}
