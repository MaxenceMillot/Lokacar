package com.eni.lokacar.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.eni.lokacar.R;
import com.eni.lokacar.data.dal.AppDatabase;
import com.eni.lokacar.data.model.Client;
import com.eni.lokacar.data.model.Location;
import com.eni.lokacar.data.model.Vehicule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.ArrayList;
import java.util.Date;

public class AjoutLocationActivity extends AppCompatActivity {
    private AppDatabase db = null;
    Validator validator;

    @NotEmpty
    @Length(min = 2, max = 35)
    EditText editTextNom;
    @NotEmpty
    @Length(min = 2, max = 35)
    EditText editTextPrenom;
    EditText editTextTelephone;
    @NotEmpty
    @Length(min = 10, max = 35)
    EditText editTextNbJours;
    ArrayList<String> listePhotos = new ArrayList<>();

    Vehicule vehiculeExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_location);

        db = Room.databaseBuilder(this, AppDatabase.class, "LokacarBDD").build();

        // Définition de du menu (toolbar) sans Titre
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ajout Location");

        //TODO prise de photos
        editTextNom = findViewById(R.id.editTextNomClient);
        editTextPrenom = findViewById(R.id.editTextPrenomClient);
        editTextTelephone = findViewById(R.id.editTextTelephoneClient);
        editTextNbJours= findViewById(R.id.editTextNbJours);

        Intent intent = getIntent();
        // Get the extras (if there are any)
        Bundle extras = intent.getExtras();

        if (extras != null) {
            vehiculeExtra = (Vehicule)getIntent().getExtras().get("vehicule");
        }else{
            // TODO ERROR HANDLING
            Toast.makeText(this, "VehiculeExtra is NULL", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_ajout, menu);
        return true;
    }

    //TODO onOptionItemSelected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuItemValider){

           // TODO CHECK FIELDS INTEGRITY
                // TODO CREATE Client
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Client client = new Client(editTextNom.getText().toString(), editTextPrenom.getText().toString(), editTextTelephone.getText().toString());
                        long clientId = db.clientDAO().insert(client);
                        client.setId(((int) clientId));

                        // TODO CREATE Location
                        int nbJours = Integer.parseInt(editTextNbJours.getText().toString());
                        Location location = new Location(vehiculeExtra,
                                client,
                                listePhotos,
                                null,
                                new Date(),
                                null,
                                nbJours,
                                vehiculeExtra.getPrixJour()*nbJours);
                        db.locationDAO().insert(location);
                    }
                });

                // TODO start ListActivity + Toast Message
                Toast.makeText(this, "Location créée !", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
