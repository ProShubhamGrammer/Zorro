package com.wallet.zorro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class WelcomeActivity extends AppCompatActivity {

    EditText etBal,etName;
    long mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Intent wIntent =getIntent();
         mob =wIntent.getLongExtra("mob",-1);
        etBal=(EditText)findViewById(R.id.etBal);
        etName=(EditText)findViewById(R.id.etName);
        Response.Listener<String> responseListener = new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
            try{
                JSONObject  jsonResponse =new JSONObject(response);
                String bal = jsonResponse.getString("bal");
                Float fbal=Float.parseFloat(bal.toString());
                String name = jsonResponse.getString("name");
                etBal.setText(bal);
                etName.setText(name);




            } catch (JSONException e) {
                e.printStackTrace();
            }
            }
        };
        WelcomeRequest welcomeRequest=new WelcomeRequest(mob,responseListener);
        RequestQueue queue = Volley.newRequestQueue(WelcomeActivity.this);
        queue.add(welcomeRequest);


        final Button bRefresh=(Button)findViewById(R.id.bRefresh);
        bRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wIntent=new Intent(WelcomeActivity.this,WelcomeActivity.class);
                wIntent.putExtra("mob",mob);
                WelcomeActivity.this.startActivity(wIntent);

            }


        });
        final Button bLogout =(Button)findViewById(R.id.bLogout);
        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wIntent=new Intent(WelcomeActivity.this,LoginActivity.class);
                Toast.makeText(WelcomeActivity.this, "Successfully logged out!", Toast.LENGTH_LONG).show();

                WelcomeActivity.this.startActivity(wIntent);
                finish();
            }
        });
        final Button bAdd=(Button)findViewById(R.id.bAdd);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent=new Intent(WelcomeActivity.this,AddMoneyActivity.class);
                addIntent.putExtra("mob",mob);
                WelcomeActivity.this.startActivity(addIntent);
            }
        });

        final Button bSend=(Button) findViewById(R.id.bSend);
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent=new Intent(WelcomeActivity.this,SendMoneyActivity.class);
                sendIntent.putExtra("mob",mob);
                WelcomeActivity.this.startActivity(sendIntent);
            }
        });

        final Button bPassBook=(Button)findViewById(R.id.bPassBook);
        bPassBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passbookIntent=new Intent(WelcomeActivity.this,PassBookActivity.class);
                passbookIntent.putExtra("mob",mob);
                WelcomeActivity.this.startActivity(passbookIntent);
            }
        });
    }
}
