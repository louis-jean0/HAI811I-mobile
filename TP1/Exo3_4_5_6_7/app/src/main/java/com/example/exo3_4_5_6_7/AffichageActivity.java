package com.example.exo3_4_5_6_7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class AffichageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);

        TextView textViewInfos = findViewById(R.id.textViewInfos);
        Button btnOK = findViewById(R.id.btnOK);
        Button btnRetour = findViewById(R.id.btnRetour);

        // Récupérer les données de l'intent
        Intent intent = getIntent();
        String informations = "Nom: " + intent.getStringExtra("nom") + "\n" +
                "Prénom: " + intent.getStringExtra("prenom") + "\n" +
                "Âge: " + intent.getStringExtra("age") + "\n" +
                "Domaine: " + intent.getStringExtra("domaine") + "\n" +
                "Téléphone: " + intent.getStringExtra("telephone");
        textViewInfos.setText(informations);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AffichageActivity.this, TroisiemeActivity.class);
                // Assurez-vous de récupérer le numéro de téléphone depuis l'intent actuel ou une autre source fiable
                String telephone = getIntent().getStringExtra("telephone");
                intent.putExtra("telephone", telephone);
                startActivity(intent);
            }
        });


        // Gérer le clic sur le bouton Retour pour finir l'activité
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
