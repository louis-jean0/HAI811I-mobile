package com.example.exo3_4_5_6_7;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.Manifest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class TroisiemeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_troisieme);

        String phoneNumber = getIntent().getStringExtra("telephone");
        System.out.println(phoneNumber);

        TextView phoneNumberText = findViewById(R.id.phoneNumberText);
        phoneNumberText.setText(phoneNumber);

        findViewById(R.id.callButton).setOnClickListener(view -> {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));

            if (ContextCompat.checkSelfPermission(TroisiemeActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(callIntent);
            } else {
                ActivityCompat.requestPermissions(TroisiemeActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        });
    }
}

