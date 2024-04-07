package com.example.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSaisie#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSaisie extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnDataPass dataPasser;
    private String dateNaissance = "";

    public FragmentSaisie() {
        // Required empty public constructor
    }

    public interface OnDataPass {
        void onDataPass(Bundle data);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dataPasser = (OnDataPass) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " doit implémenter OnDataPass");
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSaisie.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSaisie newInstance(String param1, String param2) {
        FragmentSaisie fragment = new FragmentSaisie();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void passerLesDonnees() {
        EditText editTextNom = getView().findViewById(R.id.editTextNom);
        EditText editTextPrenom = getView().findViewById(R.id.editTextPrenom);
        EditText editTextTelephone = getView().findViewById(R.id.editTextTelephone);
        EditText editTextMail = getView().findViewById(R.id.editTextMail);
        EditText editTextNaissance = getView().findViewById(R.id.editTextNaissance);

        CheckBox checkBoxSport = getView().findViewById(R.id.checkboxSport);
        CheckBox checkBoxMusique = getView().findViewById(R.id.checkboxMusique);
        CheckBox checkboxJV = getView().findViewById(R.id.checkboxJV);
        ArrayList<String> centresInterets = new ArrayList<>();
        if(checkBoxSport.isChecked()) centresInterets.add("Sport");
        if(checkBoxMusique.isChecked()) centresInterets.add("Musique");
        if(checkboxJV.isChecked()) centresInterets.add("Jeux vidéo");

        Bundle data = new Bundle();
        data.putString("nom", editTextNom.getText().toString());
        data.putString("prenom",editTextPrenom.getText().toString());
        data.putString("naissance",dateNaissance);
        data.putString("telephone",editTextTelephone.getText().toString());
        data.putString("mail",editTextMail.getText().toString());
        data.putStringArrayList("centresInterets",centresInterets);

        MainActivity.userDataBundle.putString("nom", editTextNom.getText().toString());
        MainActivity.userDataBundle.putString("prenom", editTextPrenom.getText().toString());
        MainActivity.userDataBundle.putString("naissance", editTextNaissance.getText().toString());
        MainActivity.userDataBundle.putString("telephone", editTextTelephone.getText().toString());
        MainActivity.userDataBundle.putString("mail", editTextMail.getText().toString());
        MainActivity.userDataBundle.putStringArrayList("centresInterets", centresInterets);

        dataPasser.onDataPass(data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saisie, container, false);
        Button submitButton = view.findViewById(R.id.buttonSubmit);
        submitButton.setOnClickListener(v -> passerLesDonnees());
        EditText editTextNaissance = view.findViewById(R.id.editTextNaissance);
        editTextNaissance.setOnClickListener(v -> montrerDatePickerDialog());
        // Persistance des données
        if(MainActivity.userDataBundle.containsKey("nom")) {
            String nom = MainActivity.userDataBundle.getString("nom", "Inconnu");
            EditText editTextNom = view.findViewById(R.id.editTextNom);
            editTextNom.setText(nom);
        }
        if(MainActivity.userDataBundle.containsKey("prenom")) {
            String prenom = MainActivity.userDataBundle.getString("prenom", "Inconnu");
            EditText editTextPrenom = view.findViewById(R.id.editTextPrenom);
            editTextPrenom.setText(prenom);
        }
        if(MainActivity.userDataBundle.containsKey("telephone")) {
            String telephone = MainActivity.userDataBundle.getString("telephone", "Numéro inconnu");
            EditText editTextTelephone = view.findViewById(R.id.editTextTelephone);
            editTextTelephone.setText(telephone);
        }
        if(MainActivity.userDataBundle.containsKey("naissance")) {
            String naissance = MainActivity.userDataBundle.getString("naissance", "Date non spécifiée");
            EditText editTextDateNaissance = view.findViewById(R.id.editTextNaissance);
            editTextDateNaissance.setText(naissance);
        }
        if(MainActivity.userDataBundle.containsKey("mail")) {
            String mail = MainActivity.userDataBundle.getString("mail", "Adresse mail inconnue");
            EditText editTextMail = view.findViewById(R.id.editTextMail);
            editTextMail.setText(mail);
        }
        if(MainActivity.userDataBundle.containsKey("centresInterets")) {
            ArrayList<String> centresInterets = MainActivity.userDataBundle.getStringArrayList("centresInterets");
            if (centresInterets != null) {
                CheckBox checkBoxSport = view.findViewById(R.id.checkboxSport);
                CheckBox checkBoxMusique = view.findViewById(R.id.checkboxMusique);
                CheckBox checkBoxJV = view.findViewById(R.id.checkboxJV);
                checkBoxSport.setChecked(centresInterets.contains("Sport"));
                checkBoxMusique.setChecked(centresInterets.contains("Musique"));
                checkBoxJV.setChecked(centresInterets.contains("Jeux vidéo"));
            }
        }
        return view;
    }

    private void montrerDatePickerDialog() {
        Calendar calendrier = Calendar.getInstance();
        int annee = calendrier.get(Calendar.YEAR);
        int mois = calendrier.get(Calendar.MONTH);
        int jour = calendrier.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, anneeSelectionnee, moisSelectionne, jourSelectionne) -> {
                    // Formate la date et la stocke dans la variable
                    dateNaissance = jourSelectionne + "/" + (moisSelectionne + 1) + "/" + anneeSelectionnee;
                    EditText editTextNaissance = getView().findViewById(R.id.editTextNaissance);
                    editTextNaissance.setText(dateNaissance); // Affiche la date dans l'EditText
                },
                annee, mois, jour);
        datePickerDialog.show();
    }
}