package com.wallet.zorro;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 18-Sep-17.
 */

public class SignupRequest extends StringRequest{
    private static final String SIGNUP_REQUEST_URL = "http://192.168.0.111/zorro/signup.php";
    private Map<String, String> params;
    public SignupRequest(String name, long mob, String pass, String gender, String que, String ans, Response.Listener<String> listener) {

        super(Method.POST,SIGNUP_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("mob",mob + "");
        params.put("pass",pass);
        params.put("gender",gender);
        params.put("que",que);
        params.put("ans",ans);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

