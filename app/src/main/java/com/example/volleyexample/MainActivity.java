package com.example.volleyexample;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // attributes
    @BindView(R.id.textView_string)
    public TextView textView_string;

    @BindView(R.id.textView_json)
    public TextView textView_json;

    @BindView(R.id.textView_pojo)
    public TextView textView_pojo;

    public RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Don't forget to call this to initialize the attributes!
        ButterKnife.bind(this);

        // create the queue (with multiple activities, use a singleton)
        queue = Volley.newRequestQueue(this);


    }

    @OnClick(R.id.button_get_string)
    public void performStringRequest(View v) {
        // Instantiate the RequestQueue.
        String url ="http://www.google.com";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView_string.setText("Response: "+ response.substring(0,100));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView_string.setText("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @OnClick(R.id.button_get_json)
    public void performJSONRequest(View v) {

        // # this methods uses a simple localhost json server for mocks. Take 10s to setup
        // > npm install -g json-server
        // # Create a db.json file with some data (anywhere)
        //
        //{
        //  "posts": [
        //    { "id": 1, "title": "Android is great", "author": "marc" }
        //  ],
        //  "comments": [
        //    { "id": 1, "body": "...when it compiles", "postId": 1 }
        //  ]
        //}
        //
        // # start a JSON server
        // > json-server --watch db.json
        // # test it using this  http://localhost:3000/posts/1


        // to test the app on your local server use this address
        // If using genymotion devices, use http://10.0.3.2:3000
        String url = "http://10.0.2.2:3000/posts/1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        textView_json.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView_json.setText("That didn't work!"+error);


                    }
                });

        // Access the RequestQueue
        queue.add(jsonObjectRequest);

    }

    @OnClick(R.id.button_get_pojo)
    public void performPOJORequest(View v) {

        // This uses GSON library. For more details here is a tutorial and the github
        // https://borntocode.fr/android-mettre-en-place-et-utiliser-gson-pour-faciliter-lutilisation-du-json/
        // https://github.com/google/gson


        // same as previous, using json-server
        String url = "http://10.0.2.2:3000/posts/1";

        GsonRequest<Post> jsonObjectRequest = new GsonRequest<Post>
                (url, Post.class,  null, new Response.Listener<Post>() {

                    @Override
                    public void onResponse(Post response) {
                        textView_pojo.setText("Response: (" + response.getId() +","
                                                            + response.getTitle() + ","
                                                            + response.getAuthor() + ")");
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView_pojo.setText("That didn't work!");

                    }
                });

        // Access the RequestQueue
        queue.add(jsonObjectRequest);

    }


}
