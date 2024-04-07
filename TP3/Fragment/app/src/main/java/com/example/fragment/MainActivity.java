package com.example.fragment;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements FragmentSaisie.OnDataPass {

    public static Bundle userDataBundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FragmentSaisie())
                    .commit();
        }
    }

    @Override
    public void onDataPass(Bundle data) {
        FragmentAffichage fragmentAffichage = new FragmentAffichage();
        fragmentAffichage.setArguments(data);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragmentAffichage)
                .commit();
    }
}