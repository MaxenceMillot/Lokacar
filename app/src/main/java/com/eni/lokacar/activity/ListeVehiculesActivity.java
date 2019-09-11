package com.eni.lokacar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.appyvet.materialrangebar.RangeBar;
import com.eni.lokacar.R;
import com.eni.lokacar.adapter.RecyclerViewVehiculeAdapter;
import com.eni.lokacar.data.dal.AppDatabase;
import com.eni.lokacar.data.model.Vehicule;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListeVehiculesActivity extends AppCompatActivity {
    private static final String TAG = "ListeVehiculesActivity";
    String[] spinnerCarburantArray = new String[]{"Essence", "Diesel", "Électrique", "GPL", "Hydrogène"};
    Spinner spinnerCarburant;
    Boolean filterDispo = true;
    RangeBar rangebarCritAir,rangebarNbPortes,rangebarNbPlaces,rangebarPrixJour;
    CardView cardViewFiltresListeVehicules;
    Switch switchCitadine,switchAttelage;
    RecyclerView recyclerView;
    List<Vehicule> listeVehicules = new ArrayList<>();
    AppDatabase db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_vehicules);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbarListeVehicules));
        cardViewFiltresListeVehicules = findViewById(R.id.cardViewFilterListeArticles);

        db = Room.databaseBuilder(this, AppDatabase.class, "LokacarBDD").build();
        spinnerCarburant = findViewById(R.id.spinnerCarburant);
        rangebarCritAir = findViewById(R.id.rangebarCritAir);
        rangebarNbPortes = findViewById(R.id.rangebarNbPortes);
        rangebarNbPlaces = findViewById(R.id.rangebarNbPlaces);
        rangebarPrixJour = findViewById(R.id.rangebarPrixJour);
        switchAttelage = findViewById(R.id.switchAttelage);
        switchCitadine = findViewById(R.id.switchCitadine);

        ArrayAdapter<String> adapterCarburant = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, spinnerCarburantArray);

        adapterCarburant.setDropDownViewResource(R.layout.spinner_textview_align);
        spinnerCarburant.setAdapter(adapterCarburant);
        spinnerCarburant.setSelection(0);

        final ArrayList<Vehicule> listeVehicules = new ArrayList<>();

        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Diesel",3,false,false,false));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,5,5,"Essence",1,true,true,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",30.99f,null,3,6,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",20.99f,null,3,5,"Diesel",3,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",50.99f,null,3,5,"Essence",1,true,true,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",60.99f,null,3,5,"Essence",1,false,false,false));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",70.99f,null,5,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,4,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,true,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,5,5,"Diesel",3,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,3,"Essence",1,true,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,5,5,"Essence",1,false,false,false));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,5,2,"Essence",1,false,true,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Diesel",3,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,true,false,false));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,8,"Essence",1,false,false,true));
        listeVehicules.add(new Vehicule("Rio","Kia","CN-968-HJ",40.99f,null,3,5,"Essence",1,false,true,true));

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                for(Vehicule vehicule : listeVehicules){
//                    db.vehiculeDAO().insert(vehicule);
//                }
//            }
//        }).start();

        spinnerCarburant.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateListe();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rangebarCritAir.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                updateListe();
            }

            @Override
            public void onTouchStarted(RangeBar rangeBar) {

            }

            @Override
            public void onTouchEnded(RangeBar rangeBar) {

            }
        });

        rangebarNbPortes.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                updateListe();
            }

            @Override
            public void onTouchStarted(RangeBar rangeBar) {

            }

            @Override
            public void onTouchEnded(RangeBar rangeBar) {

            }
        });

        rangebarNbPlaces.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                updateListe();
            }

            @Override
            public void onTouchStarted(RangeBar rangeBar) {

            }

            @Override
            public void onTouchEnded(RangeBar rangeBar) {

            }
        });

        rangebarPrixJour.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                updateListe();
            }

            @Override
            public void onTouchStarted(RangeBar rangeBar) {

            }

            @Override
            public void onTouchEnded(RangeBar rangeBar) {

            }
        });

        switchCitadine.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListe();
            }
        });
        switchAttelage.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateListe();
            }
        });
        recyclerView = findViewById(R.id.recyclerViewListeVehicules);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateListe();

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
        else if(item.getItemId() == R.id.ChiffreAffaire){
            Intent intentToConfiguration = new Intent(this,ChiffreAffaireActivity.class);
            startActivity(intentToConfiguration);
            return true;
        }
        else if(item.getItemId() == R.id.menuSwitchDispo){
            filterDispo = !item.isChecked();
            item.setChecked(filterDispo);
            updateListe();
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

    public void updateListe(){

        if(filterDispo == true){
            if(switchAttelage.isChecked()){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        listeVehicules = db.vehiculeDAO().getFilterAttelage(Integer.valueOf(rangebarPrixJour.getLeftPinValue()),Integer.valueOf(rangebarPrixJour.getRightPinValue()),Integer.valueOf(rangebarNbPlaces.getLeftPinValue()),Integer.valueOf(rangebarNbPlaces.getRightPinValue()),Integer.valueOf(rangebarNbPortes.getLeftPinValue()),Integer.valueOf(rangebarNbPortes.getRightPinValue()),spinnerCarburantArray[spinnerCarburant.getSelectedItemPosition()],Integer.valueOf(rangebarCritAir.getRightPinValue()),switchCitadine.isChecked());
                        ListeVehiculesActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArrayList<Vehicule> arrayListeVehicule = new ArrayList<>(listeVehicules);
                                RecyclerViewVehiculeAdapter rvaa = new RecyclerViewVehiculeAdapter(arrayListeVehicule, new RecyclerViewVehiculeAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(Vehicule vehicule) {
                                        Intent intentDetailVehiculeActivity = new Intent(ListeVehiculesActivity.this, DetailVehiculeActivity.class);
                                        intentDetailVehiculeActivity.putExtra("Vehicule",vehicule);
                                        startActivity(intentDetailVehiculeActivity);
                                    }
                                });
                                recyclerView.setAdapter(rvaa);
                            }
                        });
                    }
                }).start();
            }
            else{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        listeVehicules = db.vehiculeDAO().getFilterDispo(Integer.valueOf(rangebarPrixJour.getLeftPinValue()),Integer.valueOf(rangebarPrixJour.getRightPinValue()),Integer.valueOf(rangebarNbPlaces.getLeftPinValue()),Integer.valueOf(rangebarNbPlaces.getRightPinValue()),Integer.valueOf(rangebarNbPortes.getLeftPinValue()),Integer.valueOf(rangebarNbPortes.getRightPinValue()),spinnerCarburantArray[spinnerCarburant.getSelectedItemPosition()],Integer.valueOf(rangebarCritAir.getRightPinValue()),switchCitadine.isChecked());
                        ListeVehiculesActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArrayList<Vehicule> arrayListeVehicule = new ArrayList<>(listeVehicules);
                                RecyclerViewVehiculeAdapter rvaa = new RecyclerViewVehiculeAdapter(arrayListeVehicule, new RecyclerViewVehiculeAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(Vehicule vehicule) {
                                        Intent intentDetailVehiculeActivity = new Intent(ListeVehiculesActivity.this, DetailVehiculeActivity.class);
                                        intentDetailVehiculeActivity.putExtra("Vehicule",vehicule);
                                        startActivity(intentDetailVehiculeActivity);
                                    }
                                });
                                recyclerView.setAdapter(rvaa);
                            }
                        });
                    }
                }).start();
            }
        }
        else{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    listeVehicules = db.vehiculeDAO().getFilterIndispo(Integer.valueOf(rangebarPrixJour.getLeftPinValue()),Integer.valueOf(rangebarPrixJour.getRightPinValue()),Integer.valueOf(rangebarNbPlaces.getLeftPinValue()),Integer.valueOf(rangebarNbPlaces.getRightPinValue()),Integer.valueOf(rangebarNbPortes.getLeftPinValue()),Integer.valueOf(rangebarNbPortes.getRightPinValue()),spinnerCarburantArray[spinnerCarburant.getSelectedItemPosition()],Integer.valueOf(rangebarCritAir.getRightPinValue()));
                    ListeVehiculesActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<Vehicule> arrayListeVehicule = new ArrayList<>(listeVehicules);
                            RecyclerViewVehiculeAdapter rvaa = new RecyclerViewVehiculeAdapter(arrayListeVehicule, new RecyclerViewVehiculeAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Vehicule vehicule) {
                                    Intent intentDetailVehiculeActivity = new Intent(ListeVehiculesActivity.this, DetailVehiculeActivity.class);
                                    intentDetailVehiculeActivity.putExtra("Vehicule",vehicule);
                                    startActivity(intentDetailVehiculeActivity);
                                }
                            });
                            recyclerView.setAdapter(rvaa);
                        }
                    });
                }
            }).start();
        }
    }
}
