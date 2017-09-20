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

public class ForgotActivity extends AppCompatActivity {

    Long mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        final EditText etMob = (EditText) findViewById(R.id.etMob);
        final Button etProceed = (Button) findViewById(R.id.bProceed);

        etProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etMob.getText().toString().length()!=10)
                    Toast.makeText(ForgotActivity.this, "Invalid Mobile Number!", Toast.LENGTH_SHORT).show();
                else {

                    mob=Long.parseLong(etMob.getText().toString());
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");


                                if (success) {
                                    Intent passIntent = new Intent(ForgotActivity.this, NewPassActivity.class);
                                    passIntent.putExtra("mob",mob);
                                    ForgotActivity.this.startActivity(passIntent);
                                } else {
                                    Toast.makeText(ForgotActivity.this, "Mobile no. isn't registred!", Toast.LENGTH_LONG).show();
                                    Intent sIntent = new Intent(ForgotActivity.this, SignupActivity.class);
                                    ForgotActivity.this.startActivity(sIntent);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    ForgotRequest forgotRequest = new ForgotRequest(mob, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(ForgotActivity.this);
                    queue.add(forgotRequest);
                }
            }

        });
    }
}
