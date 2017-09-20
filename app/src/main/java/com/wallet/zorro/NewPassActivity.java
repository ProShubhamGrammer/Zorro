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

    public class NewPassActivity extends AppCompatActivity {

        EditText etQue;
        Long mob;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_new_pass);
            Intent passIntent = getIntent();
            mob = passIntent.getLongExtra("mob",-1);

            etQue=(EditText)findViewById(R.id.etQue);

            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONObject jsonResponse = new JSONObject(response);

                        String que = jsonResponse.getString("que");
                        etQue.setText(que);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            NewPassRequest newpassRequest = new NewPassRequest(mob, responseListener);
            RequestQueue queue = Volley.newRequestQueue(NewPassActivity.this);
            queue.add(newpassRequest);

            final Button bChange = (Button) findViewById(R.id.bChange);
            bChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final EditText etAns = (EditText) findViewById(R.id.etAns);
                    final EditText etPass = (EditText) findViewById(R.id.etPass);
                    final EditText etConfPass = (EditText) findViewById(R.id.etConfPass);
                    String ans = etAns.getText().toString();
                    String pass = etPass.getText().toString();
                    String confpass = etConfPass.getText().toString();
                    if(ans.isEmpty() || pass.isEmpty() || confpass.isEmpty())
                        Toast.makeText(NewPassActivity.this, "Please fill the form", Toast.LENGTH_SHORT).show();
                    else if(!pass.equals(confpass))
                        Toast.makeText(NewPassActivity.this, "Password doesn't match!", Toast.LENGTH_SHORT).show();

                    else
                    {
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    if(success)
                                    {
                                        Toast.makeText(NewPassActivity.this, "Password changed successfully!", Toast.LENGTH_SHORT).show();
                                        Intent sIntent = new Intent(NewPassActivity.this, LoginActivity.class);
                                        NewPassActivity.this.startActivity(sIntent);
                                    }

                                    else
                                        Toast.makeText(NewPassActivity.this, "Answer invalid!", Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        ChangePassRequest changepassRequest = new ChangePassRequest(mob, pass, ans, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(NewPassActivity.this);
                        queue.add(changepassRequest);

                    }

                }
            });
        }
    }
