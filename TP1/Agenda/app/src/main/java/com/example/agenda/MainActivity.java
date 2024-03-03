package com.example.agenda;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    StringBuilder eventsList = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText eventTime = findViewById(R.id.eventTime);
        final EditText eventDescription = findViewById(R.id.eventDescription);
        Button addEventButton = findViewById(R.id.addEventButton);
        final TextView eventsTextView = findViewById(R.id.eventsTextView);

        // Formatteur pour la date (ex : 10 Mars 2024)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        // Obtenir la date actuelle
        String currentDate = LocalDate.now().format(formatter);

        // Définir le titre avec la date du jour
        eventsTextView.setText("Évènements du " + currentDate);

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = eventTime.getText().toString();
                String description = eventDescription.getText().toString();

                if (!time.isEmpty() && !description.isEmpty()) {
                    // Ajout de l'événement à la liste
                    eventsList.append(time).append(" - ").append(description).append("\n");

                    // Affichage de la liste mise à jour avec le titre
                    eventsTextView.setText("Événements du " + currentDate + "\n" + eventsList.toString());

                    // Réinitialisation des champs de saisie
                    eventTime.setText("");
                    eventDescription.setText("");
                }
            }
        });
    }
}
