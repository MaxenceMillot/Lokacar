package com.eni.lokacar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.appyvet.materialrangebar.RangeBar;
import com.eni.lokacar.MainActivity;
import com.eni.lokacar.R;
import com.eni.lokacar.adapter.RecyclerViewVehiculeAdapter;
import com.eni.lokacar.data.model.Vehicule;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListeVehiculesActivity extends AppCompatActivity {
    String[] spinnerCarburantArray = new String[]{"Essence", "Diesel", "Électrique", "GPL", "Hydrogène"};
    Spinner spinnerCarburant;
    RangeBar rangebarCritAir,rangeBarNbPortes,rangeBarNbPlaces;
    CardView cardViewFiltresListeVehicules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_vehicules);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbarListeVehicules));
        cardViewFiltresListeVehicules = findViewById(R.id.cardViewFilterListeArticles);

        spinnerCarburant = findViewById(R.id.spinnerCarburant);
        rangebarCritAir = findViewById(R.id.rangebarCritAir);

        ArrayAdapter<String> adapterCarburant = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, spinnerCarburantArray);

        adapterCarburant.setDropDownViewResource(R.layout.spinner_textview_align);
        spinnerCarburant.setAdapter(adapterCarburant);
        spinnerCarburant.setSelection(0);

        ArrayList<Vehicule> listeVehicules = new ArrayList<>();

        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));


        RecyclerView recyclerView = findViewById(R.id.recyclerViewListeVehicules);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerViewVehiculeAdapter rvaa = new RecyclerViewVehiculeAdapter(listeVehicules, new RecyclerViewVehiculeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Vehicule vehicule) {
//                Intent intentAjoutLocationActivity = new Intent(ListeVehiculesActivity.this, AjoutLocationActivity.class);
//                intentAjoutLocationActivity.putExtra("Vehicule",vehicule);
//                startActivity(intentAjoutLocationActivity);
            }
        });
        recyclerView.setAdapter(rvaa);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButtonListeVehicules);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCreateVehicule = new Intent(ListeVehiculesActivity.this, AjoutVehiculeActivity.class);
                startActivity(intentCreateVehicule);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_liste_vehicules, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.Filtres){
            if(cardViewFiltresListeVehicules.getVisibility() == View.VISIBLE){
                collapse(cardViewFiltresListeVehicules);
            }
            else
            {
                expand(cardViewFiltresListeVehicules);
            }
            return true;
        }
        if(item.getItemId() == R.id.ChiffreAffaire){
            Intent intentToConfiguration = new Intent(this,AjoutVehiculeActivity.class);
            startActivity(intentToConfiguration);
            return true;
        }
        return false;
    }


    public void expand(final View v) {
        v.measure(CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();
        if (v.isShown()) {
            collapse(v);
        } else {
            v.getLayoutParams().height = 0;
            v.setVisibility(View.VISIBLE);
            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime,
                                                   Transformation t) {
                    v.getLayoutParams().height = interpolatedTime == 1 ? CardView.LayoutParams.WRAP_CONTENT
                            : (int) (targtetHeight * interpolatedTime);
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };
            a.setDuration((int) (300));
            v.startAnimation(a);
        }

    }

    public void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime,
                                               Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight
                            - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(300);
        v.startAnimation(a);
    }
}
