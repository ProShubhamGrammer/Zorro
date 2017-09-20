package com.wallet.zorro;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 20-Sep-17.
 */

public class SendMoneyRequest extends StringRequest
{

    private static final String REQUEST_URL = "http://192.168.0.111/zorro/send.php";
    private Map<String, String> params;
    public SendMoneyRequest(long mob,long senderMob,float amount, Response.Listener<String> listener)
    {
        super(Method.POST,REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("amount",amount+"");
        params.put("mob",mob+"");
        params.put("smob",senderMob+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
