package com.wallet.zorro;

;

        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.toolbox.StringRequest;

        import java.util.HashMap;
        import java.util.Map;

/**
 * Created by lookf on 20-09-2017.
 */

public class NewPassRequest extends StringRequest {

    private static final String NEWPASS_REQUEST_URL = "http://192.168.0.111/zorro/newpass.php";
    private Map<String, String> params;
    public NewPassRequest(long mob, Response.Listener<String> listener) {

        super(Method.POST, NEWPASS_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("mob",mob + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
