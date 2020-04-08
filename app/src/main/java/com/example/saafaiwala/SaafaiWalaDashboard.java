package com.example.saafaiwala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SaafaiWalaDashboard extends AppCompatActivity {

    private RequestQueue mRequestQue;
    private CardView myArea, updateRoutine, shareLocation;
    private String URL = "https://fcm.googleapis.com/fcm/send";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saafai_wala_dashboard);


        myArea = (CardView)findViewById(R.id.myareas);
        updateRoutine = (CardView)findViewById(R.id.updateroutine);
        shareLocation = (CardView)findViewById(R.id.sharelocation);
        mRequestQue = Volley.newRequestQueue(this);
        FirebaseMessaging.getInstance().subscribeToTopic("news");

        myArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(), "My Location Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SaafaiWalaDashboard.this, SaafaiTrackLocation.class);
                startActivity(i);
            }
        });


        updateRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Update Routine Clicked", Toast.LENGTH_SHORT).show();


            }
        });

        shareLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendNotification();
                Intent i = new Intent(SaafaiWalaDashboard.this, SaafaiTrackLocation.class);
                startActivity(i);

            }
        });
    }

        private void sendNotification(){

            JSONObject mainObj = new JSONObject();
            try {
                mainObj.put("to", "/topics/"+"news" );
                JSONObject notificationObj = new JSONObject();
                notificationObj.put("title", "Saafai Wala");
                notificationObj.put("body","Saafai Wala is coming today");
                mainObj.put("notification",notificationObj);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                        mainObj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Notification Sent", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Notification Not Sent", Toast.LENGTH_SHORT).show();
                    }
                }
                ){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> header = new HashMap<>();
                        header.put("content-type","application/json");
                        header.put("authorization","key=AIzaSyDn2dj7kiOeom54Me8cl6QYVURvxvxb1aY");
                        return header;
                    }
                };
                mRequestQue.add(request);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
}
