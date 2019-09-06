package com.example.restaurant2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private String email;
    private String pass;
    private Boolean vool=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url="http://192.168.43.146:3333/login";


        Button btn= (Button) findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealizarPost(view);
            }
        });



    }

    public void RealizarPost(View v) {

        String urlp="http://192.168.43.146:3333/login";
         email= ((EditText) findViewById(R.id.username)).getText().toString();
         pass= ((EditText) findViewById(R.id.password)).getText().toString();

        StringRequest stringrequest = new StringRequest(Request.Method.POST, urlp, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                vool = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vool = false;
                Toast.makeText(getApplicationContext(),"Hubo un error",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<String,String>();
                String maiil = email;
                String passwe= pass;
                params.put("email",maiil);
                params.put("password",passwe);
                return params;
            }
        };

        RequestQueue requestqueue = Volley.newRequestQueue(this);
        requestqueue.add(stringrequest);

        if (vool == true){
            Intent neevo = new Intent(v.getContext(),listas.class);
            startActivity(neevo);

        }
        /*Intent neevo = new Intent(v.getContext(),listas.class);
        startActivity(neevo);*/
    }


}
