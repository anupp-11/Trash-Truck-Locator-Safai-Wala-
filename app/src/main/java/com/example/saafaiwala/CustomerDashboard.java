package com.example.saafaiwala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

public class CustomerDashboard extends AppCompatActivity {

    private CardView customerLoaction, payBill, viewReceipt, viewRoutine, trackSafai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);

        customerLoaction = (CardView)findViewById(R.id.customerlocation);
        payBill = (CardView)findViewById(R.id.paybill);
        viewReceipt = (CardView)findViewById(R.id.viewreceipt);
        viewRoutine = (CardView)findViewById(R.id.viewroutine);
        trackSafai = (CardView)findViewById(R.id.tracksaafai);

        customerLoaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Customer Location Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CustomerDashboard.this, CustomerTrackLocation.class);
                startActivity(i);

            }
        });

        payBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Paybill Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        viewReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "View Receipt Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        viewRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "View Routine Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CustomerDashboard.this, Routine.class);
                startActivity(i);
            }
        });

        trackSafai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Track Safai Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CustomerDashboard.this, RetriveSaafaiLocation.class);
                startActivity(i);
            }
        });
    }


}
