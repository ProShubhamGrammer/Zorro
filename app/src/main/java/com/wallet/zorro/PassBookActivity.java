package com.wallet.zorro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PassBookActivity extends AppCompatActivity {
    Long mob;
    ArrayList<String> transArray=new ArrayList<String>();
    ListView lvTransaction;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_book);




        lvTransaction=(ListView)findViewById(R.id.lvTransaction);


        Intent passbookIntent=getIntent();
        mob=passbookIntent.getLongExtra("mob",-1);

        Response.Listener<String> responseListener=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    boolean success=jsonObject.getBoolean("success");
                    if(success)
                    {

                        int i=1;
                        int iter=jsonObject.getInt("iter");
                        while(i<=iter) {
                            String ex = jsonObject.getString("str"+i+"");
                            transArray.add(ex);

                            i++;
                        }


                    }
                    else
                    {
                        Toast.makeText(PassBookActivity.this,"Transaction Failed",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
               adapter=new ArrayAdapter<String>(PassBookActivity.this,android.R.layout.simple_list_item_1,transArray);
                lvTransaction.setAdapter(adapter);

            }
        };
        PassBookRequest passBookRequest=new PassBookRequest(mob,responseListener);
        RequestQueue queue = Volley.newRequestQueue(PassBookActivity.this);
        queue.add(passBookRequest);


    }


}
