package com.eni.lokacar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.eni.lokacar.R;

public class ChiffreAffaireActivity extends AppCompatActivity {

    TextView textViewChiffreAffaire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chiffre_affaire);
        textViewChiffreAffaire = findViewById(R.id.textViewChiffreAffaire);
        textViewChiffreAffaire.setText("999999999999999€");
    }

}
