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

public class AddMoneyActivity extends AppCompatActivity {
    long mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);



        Intent aIntent=getIntent();
         mob=aIntent.getLongExtra("mob",-1);
        Button bAdd=(Button)findViewById(R.id.bAdd);

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText etAmount=(EditText)findViewById(R.id.etAmount);
                if(etAmount.getText().toString().isEmpty())
                {
                    Toast.makeText(AddMoneyActivity.this,"Please Enter Amount",Toast.LENGTH_LONG).show();
                }
                else
                {
                    float amount=Float.parseFloat(etAmount.getText().toString());


                    Response.Listener<String> responseListener=new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                boolean success=jsonObject.getBoolean("success");
                                if(success)
                                {
                                    Toast.makeText(AddMoneyActivity.this,"Amount Added Successfully",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(AddMoneyActivity.this,"Transaction Failed",Toast.LENGTH_LONG).show();
                                }
                                Intent wIntent=new Intent(AddMoneyActivity.this,WelcomeActivity.class);
                                wIntent.putExtra("mob",mob);
                                AddMoneyActivity.this.startActivity(wIntent);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };



                    AddMoneyRequest addMoneyRequest=new AddMoneyRequest(mob,amount,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(AddMoneyActivity.this);
                    queue.add(addMoneyRequest);

                }
            }
        });
    }



}
