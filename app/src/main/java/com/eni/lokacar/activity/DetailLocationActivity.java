package com.eni.lokacar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.concurrent.TimeUnit;

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

    private Vehicule vehiculeExtra;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);

        // Définition de du menu (toolbar) sans Titre
        Toolbar toolbar = findViewById(R.id.toolbarDetailLocation);
        setSupportActionBar(toolbar);

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
            Intent intent = new Intent(DetailLocationActivity.this, ListeVehiculesActivity.class);
            startActivity(intent);
            Toast.makeText(DetailLocationActivity.this, "Erreur: Aucun ID de véhicule", Toast.LENGTH_LONG).show();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Database query
               location = db.locationDAO().getLastByVehicule(vehiculeExtra.getId());
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
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuItemTerminerLocation)
        {
            // Difference entre dateDebut et dateFin
            Date dateFin = new Date();
            long diff = dateFin.getTime() - location.getDateDebut().getTime();
            long nbJours = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

            // Calculer prix final
            location.setPrix((nbJours+1)*location.getVehicule().getPrixJour());

            // Set dateFin
            location.setDateFin(dateFin);

            // Libérer vehicule
            location.getVehicule().setDispo(true);

            // Persistence Vehicule & Location
            new Thread(new Runnable() {
                @Override
                public void run() {
                    db.locationDAO().updateLocation(location);
                    db.vehiculeDAO().updateVehicule(location.getVehicule());
                    DetailLocationActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(DetailLocationActivity.this, ListeVehiculesActivity.class);
                            startActivity(intent);
                            Toast.makeText(DetailLocationActivity.this, "Location terminée avec succès", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }).start();
        }
        return false;
    }
}
