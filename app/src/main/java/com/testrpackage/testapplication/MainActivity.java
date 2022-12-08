package com.testrpackage.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    String nameImage[];
    String urlOfImage[];
    String authorName[];

    String width;
    String height;
    Double[] resol;

    String url;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        httpCall(url);
    }

    public void init() {
        listView = findViewById(R.id.listView);
        intent = new Intent(this, FullScreenActivity.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent.putExtra("url", urlOfImage[i]);
                intent.putExtra("resol", resol[i]);
                startActivity(intent);
            }
        });

        nameImage = new String[10];
        urlOfImage = new String[10];
        authorName = new String[10];

        resol = new Double[10];


        url = "https://api.unsplash.com/photos/?client_id=0S9-KU3rjgOP_giD2WihrAudKIoGugenTQVv_XyDK1o";
    }

    public void httpCall(String url) {

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray mainArrayJson = new JSONArray(response);
                            for (int i = 0; i < 10; i++) {
                                JSONObject list = mainArrayJson.getJSONObject(i);
                                JSONObject urls = list.getJSONObject("urls");
                                JSONObject users = list.getJSONObject("user");

                                width = list.getString("width");
                                height = list.getString("height");

                                resol[i] = Double.parseDouble(width)/Double.parseDouble(height);

                                if(list.getString("alt_description") != "null")
                                    nameImage[i] = list.getString("alt_description");
                                else
                                    nameImage[i] = "Without Name";

                                urlOfImage[i] = urls.getString("raw");
                                authorName[i] = users.getString("name");
                            }

                            CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),urlOfImage, nameImage,authorName, resol);
                            listView.setAdapter(customBaseAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error
            }
        });

        queue.add(stringRequest);
    }


}