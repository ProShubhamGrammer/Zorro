package com.wallet.zorro;


        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.toolbox.StringRequest;

        import java.util.HashMap;
        import java.util.Map;

/**
 * Created by lookf on 20-09-2017.
 */

public class ForgotRequest extends StringRequest{

    private static final String FORGOT_REQUEST_URL = "http://192.168.0.111/zorro/forgot.php";
    private Map<String, String> params;
    public ForgotRequest(long mob, Response.Listener<String> listener) {

        super(Request.Method.POST,FORGOT_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("mob",mob + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
