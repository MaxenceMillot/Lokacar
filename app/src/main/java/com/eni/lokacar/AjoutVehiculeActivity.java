package com.eni.lokacar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class AjoutVehiculeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_vehicule);

        EditText editTextMarque = findViewById(R.id.editTextMarque);
        EditText editTextModele = findViewById(R.id.editTextModele);
        EditText editTextPlaque = findViewById(R.id.editTextPlaque);
        EditText editTextPrixJour = findViewById(R.id.editTextPrixJour);
        Spinner spinnerCarburant = findViewById(R.id.spinnerCarburant);
        Spinner spinnerCritAir = findViewById(R.id.spinnerCritAir);
        Spinner spinnerNbPlaces = findViewById(R.id.spinnerNbPlaces);
        Spinner spinnerNbPortes = findViewById(R.id.spinnerNbPortes);
        Switch switchCitadine = findViewById(R.id.switchCitadine);
        Switch switchAttelage = findViewById(R.id.switchAttelage);
        Button buttonValider = findViewById(R.id.buttonValider);

        List<String> spinnerCarburantArray =  new ArrayList<String>();
        spinnerCarburantArray.add("Essence");
        spinnerCarburantArray.add("Diesel");
        spinnerCarburantArray.add("Électrique");
        spinnerCarburantArray.add("GPL");
        spinnerCarburantArray.add("Hydrogène");

        ArrayAdapter<String> adapterCarburant = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerCarburantArray);
        spinnerCarburant.setAdapter(adapterCarburant);

        List<String> spinnerCritAirArray =  new ArrayList<String>();
        spinnerCritAirArray.add("0");
        spinnerCritAirArray.add("1");
        spinnerCritAirArray.add("2");
        spinnerCritAirArray.add("3");
        spinnerCritAirArray.add("4");
        spinnerCritAirArray.add("5");

        ArrayAdapter<String> adapterCritAir = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerCritAirArray);
        spinnerCritAir.setAdapter(adapterCritAir);

        List<String> spinnerNbPlacesArray =  new ArrayList<String>();
        spinnerNbPlacesArray.add("2");
        spinnerNbPlacesArray.add("4");
        spinnerNbPlacesArray.add("5");
        spinnerNbPlacesArray.add("6");
        spinnerNbPlacesArray.add("7");
        spinnerNbPlacesArray.add("8");

        ArrayAdapter<String> adapterNbPlaces = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerNbPlacesArray);
        spinnerNbPlaces.setAdapter(adapterNbPlaces);

        List<String> spinnerNbPortesArray =  new ArrayList<String>();
        spinnerNbPortesArray.add("3");
        spinnerNbPortesArray.add("4");
        spinnerNbPortesArray.add("5");
        spinnerNbPortesArray.add("6");

        ArrayAdapter<String> adapterNbPortes = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerNbPortesArray);
        spinnerNbPortes.setAdapter(adapterNbPortes);
    }
}
