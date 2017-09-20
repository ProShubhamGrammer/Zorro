package com.wallet.zorro;


        import com.android.volley.Response;
        import com.android.volley.toolbox.StringRequest;

        import java.util.HashMap;
        import java.util.Map;

/**
 * Created by lookf on 20-09-2017.
 */

public class ChangePassRequest extends StringRequest {
    private static final String CHANGEPASS_REQUEST_URL = "http://192.168.0.111/zorro/change.php";
    private Map<String, String> params;
    public ChangePassRequest(long mob, String pass, String ans, Response.Listener<String> listener) {

        super(Method.POST, CHANGEPASS_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("mob",mob + "");
        params.put("pass", pass);
        params.put("ans", ans);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
