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

public class SendMoneyActivity extends AppCompatActivity {

    long mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        final EditText etMob = (EditText) findViewById(R.id.etMob);
        final EditText etAmount = (EditText) findViewById(R.id.etAmount);
        final Button bSend = (Button) findViewById(R.id.bSend);

        Intent aIntent=getIntent();
        mob=aIntent.getLongExtra("mob",-1);

        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etAmount.getText().toString().isEmpty() || etMob.getText().toString().isEmpty())
                {
                    if(!(etMob.getText().toString().isEmpty()))
                    Toast.makeText(SendMoneyActivity.this,"Please Enter Amount",Toast.LENGTH_LONG).show();

                    else
                    {
                        Toast.makeText(SendMoneyActivity.this,"Please Enter Mobile Number",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    float amount=Float.parseFloat(etAmount.getText().toString());
                    long senderMob = Long.parseLong(etMob.getText().toString());

                    Response.Listener<String> responseListener=new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                boolean success=jsonObject.getBoolean("success");
                                if(success)
                                {
                                    Toast.makeText(SendMoneyActivity.this,"Amount Sent Successfully",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(SendMoneyActivity.this,"Transaction Failed",Toast.LENGTH_LONG).show();
                                }
                                Intent wIntent=new Intent(SendMoneyActivity.this,WelcomeActivity.class);
                                wIntent.putExtra("mob",mob);
                                SendMoneyActivity.this.startActivity(wIntent);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };



                    SendMoneyRequest sendMoneyRequest=new SendMoneyRequest(mob,senderMob,amount,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(SendMoneyActivity.this);
                    queue.add(sendMoneyRequest);

                }
            }
        });
    }
}

