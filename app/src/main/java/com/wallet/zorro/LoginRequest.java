package com.wallet.zorro;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 19-Sep-17.
 */

public class LoginRequest extends StringRequest
{
    private static final String LOGIN_REQUEST_URL = "http://192.168.0.111/zorro/login.php";
    private Map<String, String> params;
    public LoginRequest(long mob, String pass, Response.Listener<String> listener) {

        super(Request.Method.POST,LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("mob",mob + "");
        params.put("pass",pass);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


