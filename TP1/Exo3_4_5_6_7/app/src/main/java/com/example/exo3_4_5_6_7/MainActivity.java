package com.example.exo3_4_5_6_7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private EditText nomInput;
    private EditText prenomInput;
    private EditText ageInput;
    private EditText domaineInput;
    private EditText telephoneInput;

    private void changerCouleurFondEditTexts(int couleur) {
        EditText nom = findViewById(R.id.nom);
        EditText prenom = findViewById(R.id.prenom);
        EditText age = findViewById(R.id.age);
        EditText domaine = findViewById(R.id.domaine);
        EditText telephone = findViewById(R.id.telephone);

        nom.setBackgroundColor(couleur);
        prenom.setBackgroundColor(couleur);
        age.setBackgroundColor(couleur);
        domaine.setBackgroundColor(couleur);
        telephone.setBackgroundColor(couleur);
    }
    private void afficherDialogueConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Confirmation");
        builder.setMessage("Êtes-vous sûr de vouloir valider ?");

        // Bouton Confirmer
        builder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, AffichageActivity.class);
                intent.putExtra("nom", nomInput.getText().toString());
                intent.putExtra("prenom", prenomInput.getText().toString());
                intent.putExtra("age", ageInput.getText().toString());
                intent.putExtra("domaine", domaineInput.getText().toString());
                intent.putExtra("telephone", telephoneInput.getText().toString());
                startActivity(intent);
            }
        });

        // Bouton Annuler
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    // Pour la version XML
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomInput = findViewById(R.id.nom);
        prenomInput = findViewById(R.id.prenom);
        ageInput = findViewById(R.id.age);
        domaineInput = findViewById(R.id.domaine);
        telephoneInput = findViewById(R.id.telephone);

        Button validerButton = findViewById(R.id.valider);
        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherDialogueConfirmation();
            }
        });

    }

    // Pour la version Java
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layout.setPadding(16, 16, 16, 16);

        // Nom
        TextView nomLabel = new TextView(this);
        nomLabel.setText("Nom");
        layout.addView(nomLabel);

        EditText nomInput = new EditText(this);
        nomInput.setId(R.id.nom);
        layout.addView(nomInput);

        // Prénom
        TextView prenomLabel = new TextView(this);
        prenomLabel.setText("Prénom");
        layout.addView(prenomLabel);

        EditText prenomInput = new EditText(this);
        prenomInput.setId(R.id.prenom);
        layout.addView(prenomInput);

        // Âge
        TextView ageLabel = new TextView(this);
        ageLabel.setText("Âge");
        layout.addView(ageLabel);

        EditText ageInput = new EditText(this);
        ageInput.setId(R.id.age);
        ageInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(ageInput);

        // Domaine de compétences
        TextView domaineLabel = new TextView(this);
        domaineLabel.setText("Domaine de compétences");
        layout.addView(domaineLabel);

        EditText domaineInput = new EditText(this);
        domaineInput.setId(R.id.domaine);
        layout.addView(domaineInput);

        // Numéro de téléphone
        TextView telephoneLabel = new TextView(this);
        telephoneLabel.setText("Numéro de téléphone");
        layout.addView(telephoneLabel);

        EditText telephoneInput = new EditText(this);
        telephoneInput.setId(R.id.telephone);
        telephoneInput.setInputType(InputType.TYPE_CLASS_PHONE);
        layout.addView(telephoneInput);

        // Bouton Valider
        Button validerButton = new Button(this);
        validerButton.setId(R.id.valider);
        validerButton.setText("Valider");
        layout.addView(validerButton);

        setContentView(layout);
    }
    */
}