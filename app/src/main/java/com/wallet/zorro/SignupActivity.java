package com.wallet.zorro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {

    private RadioButton rbGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText etMob=(EditText)findViewById(R.id.etMob);
        final EditText etPass=(EditText)findViewById(R.id.etPass);
        final EditText etName=(EditText)findViewById(R.id.etName);
        final EditText etQue=(EditText)findViewById(R.id.etQue);
        final EditText etAns=(EditText)findViewById(R.id.etAns);

        final RadioGroup rgGender=(RadioGroup) findViewById(R.id.rgGender);

        final RadioButton rbFemale=(RadioButton)findViewById(R.id.rbFemale);

        final Button bSignup=(Button)findViewById(R.id.bSignup);



        bSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long mob;
                if(etMob.getText().toString().length()!=10) {

                    Toast.makeText(SignupActivity.this, "Please Enter a Valid Mobile number ", Toast.LENGTH_LONG).show();
                }
                else
                {

                    mob = Long.parseLong(etMob.getText().toString());
                }
                final String pass = etPass.getText().toString();
                final String name = etName.getText().toString();
                final String que = etQue.getText().toString();
                final String ans = etAns.getText().toString();
                Integer selectedid = rgGender.getCheckedRadioButtonId();





                if (name.isEmpty()||etMob.getText().toString().isEmpty()||pass.isEmpty()||que.isEmpty()||ans.isEmpty()||selectedid.toString()=="-1") {
                    if(!(etMob.getText().toString().isEmpty()))
                            Toast.makeText(SignupActivity.this,"Please fill the form ", Toast.LENGTH_LONG).show();


                } else {
                    mob=Long.parseLong(etMob.getText().toString());
                    rbGender = (RadioButton) findViewById(selectedid);
                    final String gender = rbGender.getText().toString();
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {

                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    Intent sIntent = new Intent(SignupActivity.this, LoginActivity.class);
                                    Toast.makeText(SignupActivity.this, "Successfully registered!", Toast.LENGTH_LONG).show();
                                    SignupActivity.this.startActivity(sIntent);
                                } else {
                                    Toast.makeText(SignupActivity.this, "Registration failed!", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    SignupRequest signupRequest = new SignupRequest(name, mob, pass, gender, que, ans, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                    queue.add(signupRequest);


                }
            }
        });


    }
}
