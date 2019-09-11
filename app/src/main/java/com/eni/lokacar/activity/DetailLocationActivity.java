package com.eni.lokacar.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.eni.lokacar.R;
import com.eni.lokacar.data.dal.AppDatabase;
import com.eni.lokacar.data.model.Location;
import com.eni.lokacar.data.model.Vehicule;

import java.text.DateFormat;
import java.util.Date;

public class DetailLocationActivity extends AppCompatActivity {
    private static final String TAG = "DetailLocationActivity";
    private AppDatabase db = null;

    TextView textViewDateDebut;
    TextView textViewNbJours;
    TextView textViewPrix;
    TextView textViewNomClient;
    TextView textViewPrenomClient;
    TextView textViewTelephoneClient;
    TextView textViewMarque;
    TextView textViewModele;
    TextView textViewPrixJour;

    Vehicule vehiculeExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);

        // Définition de du menu (toolbar) sans Titre
        Toolbar toolbar = findViewById(R.id.toolbarDetailLocation);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Détail Location");

        textViewDateDebut = findViewById(R.id.textViewDateDebut);
        textViewNbJours = findViewById(R.id.textViewNbJours);
        textViewPrix = findViewById(R.id.textViewPrix);
        textViewNomClient = findViewById(R.id.textViewNomClient);
        textViewPrenomClient = findViewById(R.id.textViewPrenomCLient);
        textViewTelephoneClient = findViewById(R.id.textViewTelephoneClient);
        textViewModele = findViewById(R.id.textViewModele);
        textViewMarque= findViewById(R.id.textViewMarque);
        textViewPrixJour= findViewById(R.id.textViewPrixJour2);

        db = Room.databaseBuilder(this, AppDatabase.class, "LokacarBDD").build();

        // Get the extras (if there are any)
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            vehiculeExtra = getIntent().getParcelableExtra("vehicule");
        }else{
            // TODO ERROR HANDLING
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Database query
                // TODO replace id with vehiculeExtra.getId();
               final Location location = db.locationDAO().getLastByVehicule(1);
                DetailLocationActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Affichage textviews
                        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
                        textViewDateDebut.setText(dateFormat.format(location.getDateDebut()));
                        textViewNbJours.setText(String.valueOf(location.getNbJours())+" jours");
                        textViewPrix.setText((String.valueOf(location.getPrix()))+"€");
                        textViewNomClient.setText(location.getClient().getNom());
                        textViewPrenomClient.setText(location.getClient().getPrenom());
                        textViewTelephoneClient.setText(location.getClient().getTelephone());
                        textViewMarque.setText(location.getVehicule().getMarque());
                        textViewModele.setText(location.getVehicule().getModele());
                        textViewPrixJour.setText(String.valueOf(location.getVehicule().getPrixJour())+"€ /jour");
                    }
                });
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuItemTerminerLocation){
            // TODO Calculer prix final

            // TODO set dateFin

            // TODO Libérer voiture

        }
        return false;
    }
}
