package com.wallet.zorro;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 19-Sep-17.
 */

public class WelcomeRequest extends StringRequest{
    private static final String WELCOME_REQUEST_URL = "http://192.168.0.111/zorro/welcome.php";
    private Map<String, String> params;
    public WelcomeRequest( long mob, Response.Listener<String> listener) {

        super(Request.Method.POST,WELCOME_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("mob",mob + "");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
