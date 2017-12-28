package com.stasbizdiga.pamlab5;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;


public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://81.180.72.17/api/Register/UserReg";
    private Map<String, String> params;


    public RegisterRequest(String Name, String Bday, String Email, String Tel, String Addr, String User, String Pass, String photo, Response.Listener<String> listener) {
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params = new HashMap<>();

        params.put("Content-Type","application/x-www-form-urlencoded");

        params.put("FullName",Name);
        params.put("Birthday",Bday.toString()); // yyyy/MM/dd
        params.put("Email",Email);
        params.put("Phone",Tel);
        params.put("Address",Addr);

        params.put("Username",User);
        params.put("Password",Pass);
        params.put("Base64Photo",photo.toString()); // string:base64

        }

    @Override
    public Map<String, String> getHeaders()   {
        Map<String,String> params = new HashMap<String, String>();
        params.put("Content-Type","application/x-www-form-urlencoded");
        return params;
    }



    @Override
    public Map<String,String> getParams() {
        return params;
    }
}
