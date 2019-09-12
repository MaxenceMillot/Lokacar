package com.eni.lokacar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eni.lokacar.R;
import com.eni.lokacar.data.dal.AppDatabase;
import com.eni.lokacar.data.model.Location;
import com.eni.lokacar.data.model.Vehicule;

import java.util.Date;

public class DetailVehiculeActivity extends AppCompatActivity {

    Vehicule vehicule;
    TextView textViewMarque,textViewModele,textViewPlaque,textViewPrix;
    Button buttonRendre,buttonLouer;
    AppDatabase db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vehicule);

        db = Room.databaseBuilder(this, AppDatabase.class, "LokacarBDD").build();
        final Vehicule vehicule = getIntent().getParcelableExtra("Vehicule");
        textViewMarque = findViewById(R.id.textViewMarque);
        textViewModele = findViewById(R.id.textViewModele);
        textViewPlaque = findViewById(R.id.textViewPlaque);
        textViewPrix = findViewById(R.id.textViewPrix);
        buttonLouer = findViewById(R.id.buttonLouer);
        buttonRendre = findViewById(R.id.buttonRendre);


        StringBuilder text = new StringBuilder();
        text.append("La "+vehicule.getModele()+" de "+vehicule.getMarque()+" est un véhicule formidable. \n" +
                "Possédant "+vehicule.getNbPlace()+" places et "+vehicule.getNbPorte()+" portes, il vous emmenera partout.\n " +
                "Son moteur très économique de Crit'Air "+vehicule.getCritair()+"fonctionne ");

        if("Essence".equals(vehicule.getCarburant())||"Électricité".equals(vehicule.getCarburant())||"Hydrogène".equals(vehicule.getCarburant())){
            text.append("à l'");
        }
        else{
            text.append("au ");
        }
        text.append(vehicule.getCarburant() +" Cette ");

        if(vehicule.isCitadine()){
            text.append("citadine ");
        }else{
            text.append("voiture ");
        }

        if(vehicule.isAttelage()){
            text.append("possède une fixation pour un attelage et ");
        }

        if(vehicule.isDispo()){
            text.append("est disponnible à la location");
        }else{
            text.append("est actuellement louée");
        }

        textViewMarque.setText(vehicule.getMarque());
        textViewModele.setText(vehicule.getModele());
        textViewPlaque.setText(vehicule.getPlaque());
        textViewPrix.setText(text);

        if(vehicule.isDispo()){
            buttonLouer.setVisibility(View.VISIBLE);
            buttonRendre.setVisibility(View.GONE);
        }
        else{
            buttonLouer.setVisibility(View.GONE);
            buttonRendre.setVisibility(View.VISIBLE);
        }

        buttonLouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToAjoutLocationActivity = new Intent(DetailVehiculeActivity.this,AjoutLocationActivity.class);
                intentToAjoutLocationActivity.putExtra("Vehicule",vehicule);
                startActivity(intentToAjoutLocationActivity);
            }
        });

        buttonRendre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Location location = db.locationDAO().getLastByVehicule(vehicule.getId());
                location.setDateFin(new Date());
                db.locationDAO().updateLocation(location);
                vehicule.setDispo(true);
                db.vehiculeDAO().updateVehicule(vehicule);
            }
        });
    }
}
