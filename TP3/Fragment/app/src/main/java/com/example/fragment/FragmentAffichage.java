package com.example.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAffichage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAffichage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentAffichage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAffichage.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAffichage newInstance(String param1, String param2) {
        FragmentAffichage fragment = new FragmentAffichage();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_affichage, container, false);

        TextView textViewNom = view.findViewById(R.id.textViewNom);
        String nom = getArguments().getString("nom", "Inconnu");
        textViewNom.setText("Nom : " + nom);

        TextView textViewPrenom = view.findViewById(R.id.textViewPrenom);
        String prenom = getArguments().getString("prenom", "Inconnu");
        textViewPrenom.setText("Prénom : " + prenom);

        TextView textViewNaissance = view.findViewById(R.id.textViewNaissance);
        String naissance = getArguments().getString("naissance", "Date non spécifiée");
        textViewNaissance.setText("Date de naissance : " + naissance);

        TextView textViewTelephone = view.findViewById(R.id.textViewTelephone);
        String telephone = getArguments().getString("telephone", "Numéro inconnu");
        textViewTelephone.setText("Numéro de téléphone : " + telephone);

        TextView textViewMail = view.findViewById(R.id.textViewMail);
        String mail = getArguments().getString("mail", "Adresse mail inconnue");
        textViewMail.setText("Adresse mail : " + mail);

        TextView textViewCentresInterets = view.findViewById(R.id.textViewCentresInterets);
        Bundle data = getArguments();
        if(data != null) {
            ArrayList<String> centresInterets = data.getStringArrayList("centresInterets");
            String centresInteretsStr = "Centres d'intérêts : " + TextUtils.join(", ", centresInterets);
            textViewCentresInterets.setText(centresInteretsStr);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonConfirmer = view.findViewById(R.id.buttonConfirmer);
        buttonConfirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("nom", getArguments().getString("nom", "Inconnu"));
                    jsonObject.put("prenom",getArguments().getString("prenom", "Inconnu"));
                    jsonObject.put("naissance",getArguments().getString("naissance", "Date non spécifiée"));
                    jsonObject.put("telephone",getArguments().getString("telephone", "Numéro inconnu"));
                    jsonObject.put("mail",getArguments().getString("mail", "Adresse mail inconnue"));
                    ArrayList<String> centresInteretsList = getArguments().getStringArrayList("centresInterets");
                    JSONArray centresInteretsArray = new JSONArray();
                    if(centresInteretsList != null) {
                        for(String centreInteret : centresInteretsList) {
                            centresInteretsArray.put(centreInteret);
                        }
                    }
                    jsonObject.put("centresInterets",centresInteretsArray);

                    FileOutputStream fos = getActivity().openFileOutput("userData.json", Context.MODE_PRIVATE);
                    fos.write(jsonObject.toString().getBytes());
                    fos.close();

                    Toast.makeText(getActivity(), "Données JSON enregistrées", Toast.LENGTH_SHORT).show();
                    MainActivity.userDataBundle.putString("nom", getArguments().getString("nom", "Inconnu"));
                    MainActivity.userDataBundle.putString("prenom", getArguments().getString("prenom", "Inconnu"));
                    MainActivity.userDataBundle.putString("naissance", getArguments().getString("naissance", "Date non spécifiée"));
                    MainActivity.userDataBundle.putString("telephone", getArguments().getString("telephone", "Numéro inconnu"));
                    MainActivity.userDataBundle.putString("mail", getArguments().getString("mail", "Adresse mail inconnue"));
                    ArrayList<String> centresInterets = getArguments().getStringArrayList("centresInterets");
                    if(centresInterets != null) {
                        MainActivity.userDataBundle.putStringArrayList("centresInterets", new ArrayList<>(centresInterets));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Erreur lors de la création de JSON", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Fichier non trouvé", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Erreur d'entrée/sortie", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button buttonRetour = view.findViewById(R.id.buttonRetour);
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentSaisie fragmentSaisie = new FragmentSaisie();
                if(getFragmentManager() != null) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragmentSaisie)
                            .commit();
                }
            }
        });
    }
}