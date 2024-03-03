package com.example.train;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText depart = findViewById(R.id.depart);
        final EditText arrivee = findViewById(R.id.arrivee);
        Button btnRechercher = findViewById(R.id.btnRechercher);
        final TextView textViewHoraires = findViewById(R.id.textViewHoraires);

        btnRechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Horaires codées en dur
                String horaires = "06:00 - 08:00\n09:00 - 11:00\n12:00 - 14:00";
                textViewHoraires.setText(horaires);
            }
        });
    }
}
