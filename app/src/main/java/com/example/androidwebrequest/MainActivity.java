package com.example.androidwebrequest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView textView ;
    private TextInputLayout userInput;
    private TextInputLayout passwordInput ;
    private TextInputLayout deviceIDInput;
    private TextInputLayout ipAddressInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        userInput = (TextInputLayout) findViewById(R.id.userInput);
        passwordInput =  findViewById(R.id.userInput);
        deviceIDInput =  findViewById(R.id.deviceInput);
        ipAddressInput =  findViewById(R.id.serverInput);
    }

    public void put(View view) {
        // Instantiate the Request
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://User" + userInput.getEditText().getText() + ":" + passwordInput.getEditText().getText() +  "@" +
            ipAddressInput.getEditText().getText() + "/service/controls/update.json";

        // JSON body data
        JSONObject body = new JSONObject();
        try {
            body.put("id", deviceIDInput.getEditText().getText());
            body.put("numVal", 1);
        } catch (Exception e) {
            // TODO: send a log message
        }
        // Request a string response from the provided URL.
        final JsonObjectRequest request =
                new JsonObjectRequest(Request.Method.PUT, url, body,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                textView.setText(response.toString());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {

                    // custom headers to pass authorization
                    @Override
                    public Map getHeaders() throws AuthFailureError {
                        HashMap headers = new HashMap();
                        headers.put("Content-Type", "application/json");
                        headers.put("Accept", "application/json");
                        return headers;
                    }

                };



        // Add the request to the RequestQueue
        queue.add(request);
    }

    public void post(View view) {

       // // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://ptsv2.com/t/dan/post";

        // JSON body data
        JSONObject body = new JSONObject();
        int user = Integer.parseInt(userInput.getEditText().getText().toString());
        int password = Integer.parseInt(passwordInput.getEditText().getText().toString());
        int deviceid = Integer.parseInt(deviceIDInput.getEditText().getText().toString());
        int ip =  Integer.parseInt(ipAddressInput.getEditText().getText().toString());

        try {
            body.put("id", user);
            body.put("numVal", 1);
        } catch (Exception e) {
            Log.e("error", "JSON didn't save");
        }
        // Request a string response from the provided URL.
        final JsonObjectRequest request =
                new JsonObjectRequest(Request.Method.POST, url, body,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                textView.setText(userInput.getEditText().getText().toString());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {

            // custom headers to pass authorization
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                return headers;
            }

        };


        textView.setText(userInput.getEditText().getText().toString());
        // Add the request to the RequestQueue
        queue.add(request);
    }


}
