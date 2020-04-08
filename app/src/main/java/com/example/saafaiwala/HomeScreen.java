package com.example.saafaiwala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    Button btn_saafaiwala;
    Button btn_customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        btn_customer = (Button)findViewById(R.id.customer);
        btn_saafaiwala = (Button)findViewById(R.id.safaiwala);

        btn_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, CustomerDashboard.class);
                startActivity(i);
            }
        });

        btn_saafaiwala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, SaafaiWalaDashboard.class);
                startActivity(i);
            }
        });
    }

}
