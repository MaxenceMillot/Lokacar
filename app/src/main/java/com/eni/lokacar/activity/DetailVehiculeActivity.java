package com.eni.lokacar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.eni.lokacar.R;
import com.eni.lokacar.data.dal.AppDatabase;
import com.eni.lokacar.data.model.Location;
import com.eni.lokacar.data.model.Vehicule;

import java.io.File;
import java.util.Date;

public class DetailVehiculeActivity extends AppCompatActivity {

    Vehicule vehicule;
    TextView textViewMarque,textViewModele,textViewPlaque,textViewPrix,textViewCarburant,textViewCritAir,textViewNbPortes,textViewNbPlaces;
    Button buttonDetaiLocation,buttonLouer;
    ImageView imageViewDetailVehicule,imageViewCitadine,imageViewAttelage;
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
        textViewCarburant = findViewById(R.id.textViewCarburant);
        textViewCritAir = findViewById(R.id.textViewCritAir);
        textViewNbPortes = findViewById(R.id.textViewNbPortes);
        textViewNbPlaces = findViewById(R.id.textViewNbPlaces);
        imageViewCitadine = findViewById(R.id.imageViewCitadine);
        imageViewAttelage = findViewById(R.id.imageViewAttelage);
        imageViewDetailVehicule = findViewById(R.id.imageViewDetailVehicule);
        buttonLouer = findViewById(R.id.buttonLouer);
        buttonDetaiLocation = findViewById(R.id.buttonDetailLocation);


        File image = new File(this.getApplicationContext().getFilesDir(),  vehicule.getId()+".jpg");
        if(image.exists()){
            imageViewDetailVehicule.setImageURI(Uri.fromFile(image));
        }
        else
        {
            imageViewDetailVehicule.setImageDrawable(getApplicationContext().getDrawable(R.mipmap.ic_launcher));
        }
        textViewMarque.setText(vehicule.getMarque());
        textViewModele.setText(vehicule.getModele());
        textViewPlaque.setText(vehicule.getPlaque());
        textViewPrix.setText(vehicule.getPrixJour()+"/Jour");
        textViewCarburant.setText(vehicule.getCarburant());
        textViewCritAir.setText(String.valueOf(vehicule.getCritair()));
        textViewNbPortes.setText(String.valueOf(vehicule.getNbPorte()));
        textViewNbPlaces.setText(String.valueOf(vehicule.getNbPlace()));
        Drawable check = getApplicationContext().getResources().getDrawable(R.drawable.ic_check_green_24dp);
        Drawable close = getApplicationContext().getResources().getDrawable(R.drawable.ic_close_red_24dp);
        imageViewCitadine.setImageDrawable(vehicule.isCitadine()?check:close);
        imageViewAttelage.setImageDrawable(vehicule.isAttelage()?check:close);

        if(vehicule.isDispo()){
            buttonLouer.setVisibility(View.VISIBLE);
            buttonDetaiLocation.setVisibility(View.GONE);
        }
        else{
            buttonLouer.setVisibility(View.GONE);
            buttonDetaiLocation.setVisibility(View.VISIBLE);
        }

        buttonLouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToAjoutLocationActivity = new Intent(DetailVehiculeActivity.this, AjoutLocationActivity.class);
                intentToAjoutLocationActivity.putExtra("vehicule",vehicule);
                startActivity(intentToAjoutLocationActivity);
            }
        });

        buttonDetaiLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToDetailLocationActivity = new Intent(DetailVehiculeActivity.this, DetailLocationActivity.class);
                intentToDetailLocationActivity .putExtra("vehicule",vehicule);
                startActivity(intentToDetailLocationActivity );
            }
        });
    }
}
