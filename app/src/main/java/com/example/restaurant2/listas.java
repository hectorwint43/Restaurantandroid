package com.example.restaurant2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.android.volley.Request;
import com.android.volley.RequestQueue;
//import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.restaurant2.Pedido.AdaptadorPedido;
import com.example.restaurant2.Pedido.Pedido;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;





public class listas extends AppCompatActivity {

    TextView idpedido;
    TextView platillonombre;

    private TextView mtext;
    private RequestQueue mqueue;
    AdaptadorPedido ap2;
    TextView tex;
    String textoo;
    WebSocket websocket;
    private Button iniciar;
    private TextView output;
    private OkHttpClient client;
    //private List<String> lista = new ArrayList<>();
    ArrayList<HashMap<String , String>> pedidosllega;
    private String url;
    Usarvoley usarvoley;
    RecyclerView recyclerView;
    private ListView listvieww;
    String micloo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas);

        idpedido = (TextView) findViewById(R.id.idpedido);
        platillonombre = (TextView) findViewById(R.id.platillonombre);
       // recyclerView = (RecyclerView) findViewById(R.id.id_rv);
        //listvieww =findViewById(R.id.lst);

        //pedidosllega = new ArrayList<>();
        tex = (TextView) findViewById(R.id.textid);
        url="http://192.168.43.146:3333/pedidosver";
        client = new OkHttpClient();
        start();



        List<Pedido> lv = new ArrayList<Pedido>();
        AdaptadorPedido adp = new AdaptadorPedido(lv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //recyclerView.setAdapter(adp);
    }

    private void llenarlista()
    {


        Toast.makeText(listas.this,"entro al llenar",Toast.LENGTH_SHORT).show();
        client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Toast.makeText(listas.this,"1",Toast.LENGTH_SHORT).show();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(listas.this,"2",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myresponse = response.body().string();
                    micloo= myresponse;
                    lastHope(micloo);
                   // tex.setText(myresponse);
                        //lastHope(myresponse);

                }
            }
        });

        //tex.append("Pedido: "+" Platillio: ");
        //tex.append("\n\n");


    }

    private void lastHope(String myresponse)
    {
        tex.post(new Runnable() {
            @Override
            public void run() {
                tex.setText("");
                //tex.setText(tex.getText().toString() + "\n\n" + txt);
            }
        });

        JSONArray json_array = null;
        List<Pedido> lv = new ArrayList<Pedido>();
        try {
            json_array = new JSONArray(myresponse);
            for (int i = 0; i < json_array.length(); i++) {


                   JSONObject obj = json_array.getJSONObject(i);


                   final String firstName = obj.getString("platillonombre");

                   final  String numpedido = obj.getString("idpedido");

                final  String una = Integer.toString(i);

                   tex.post(new Runnable() {
                       @Override
                       public void run() {

                           tex.append("Pedido: " + numpedido + " Platillio: " + firstName);
                           tex.append("\n\n");

                           //tex.setText("");
                           //tex.setText(tex.getText().toString() + "\n\n" + txt);
                       }
                   });



                //String numpedido = obj.getString("idpedido");
                //formato(obj.getString("idpedido",obj.getString("platillonombre")));
                Pedido pedido = new Pedido();
                JSONObject obj2 = json_array.getJSONObject(i);
                pedido.setIdpedido(obj2.getString("idpedido"));
                pedido.setPlatillonombre(obj2.getString("platillonombre"));
                lv.add(pedido);
            }

            //AdaptadorPedido ap = new AdaptadorPedido(lv);

            /*recyclerView.setLayoutManager(new LinearLayoutManager(listas.this));
            recyclerView.setAdapter(ap);*/

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void start(){
        Request request = new Request.Builder().url("ws://192.168.43.146:3333/adonis-ws").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }
    //runnable se utiliza para poder entrar en los hilos
    private void output(final String txt) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(listas.this,"entro al run",Toast.LENGTH_SHORT).show();
                //Toast.makeText(listas.this,txt,Toast.LENGTH_SHORT).show();
                /*tex.post( new Runnable() {
                   @Override
                 public void run() {*/


                        Toast.makeText(listas.this,"entro al runneeer",Toast.LENGTH_SHORT).show();
                        llenarlista();

                         //tex.setText("");
                         //tex.setText(tex.getText().toString() + "\n\n" + txt);
                    /*}
                 });*/
            }
        });
    }




    public void emit(){
        //Toast.makeText(listas.this,"emit",Toast.LENGTH_SHORT).show();
        try {
            JSONObject text = new JSONObject();
            JSONObject topics = new JSONObject();
            topics.put("topic", "reserva:pedidos");
            topics.put("event", "message");
            topics.put("data", new JSONObject("{mensaje:hola}"));
            text.put("t", 7);
            text.put("d", topics);
            Log.v("socket 69", "Try to send data " + text.toString());

            websocket.send(text.toString());
        } catch (JSONException e) {
            Log.e("socket", "Try to send data with wrong JSON format, data: " + e);
        }
    }


    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;
        @Override
        public void onOpen(WebSocket webSocket, Response response) {

            websocket = webSocket;

            try {
                JSONObject text = new JSONObject();
                JSONObject topics = new JSONObject();
                topics.put("topic", "reserva:pedidos");
                topics.put("event", "message");
                text.put("t", 1);
                text.put("d", topics);
                Log.v("socket 89", "otra cosa " + text.toString());

                webSocket.send(text.toString());
                emit();
            } catch (JSONException e) {
                Log.e("socket 93", "Try to send data with wrong JSON format, data: " + e);
            }

            output(websocket.toString());
        }
        @Override
        public void onMessage(WebSocket webSocket, String text) {
            try {
                JSONObject object = new JSONObject(text);
                int type = object.optInt("t");
                Log.v("socket: 61", "JSON object " + object);
                Log.v("onMessage", ""+type);

                String tipe = String.valueOf(type);

                output(text);
                switch (type) {
                    case 0: {
                        Log.v("socket: 0","");
                        output(tipe);
                    }
                    case 1: {
                        Log.v("socket: 1","");
                        output(tipe);
                    }
                    case 2: {
                        Log.v("socket: 2","");
                        output(tipe);
                    }
                    case 3: {
                        Log.v("socket: 3","");
                        output(tipe);
                    }
                    case 4: {
                        Log.v("socket: 4","");
                        output(tipe);
                    }
                    case 5: {
                        Log.v("socket: 5","");
                        output(tipe);
                    }
                    case 6: {
                        Log.v("socket: 6","");
                        output(tipe);
                    }
                    case 7: {
                        JSONObject d = object.optJSONObject("d");
                        output(d.get("data").toString());
                        Log.v("socket: 7", "");
                        output(tipe);
                    }
                    case 8: {
                        Log.v("socket: 8","");
                        output(tipe);
                    }
                    case 9: {
                        Log.v("socket: 9","");
                        output(tipe);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);

        }    @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {

            output("Error : " + t.getMessage());

        }
    }



}

