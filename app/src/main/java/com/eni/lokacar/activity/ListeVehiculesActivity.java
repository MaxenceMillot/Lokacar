package com.eni.lokacar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.eni.lokacar.data.model.Client;
import com.eni.lokacar.data.model.Location;
import com.eni.lokacar.data.model.Vehicule;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    Menu menu;

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

        // Checks if the DB is empty: if so, insert placeholder datas
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(db.vehiculeDAO().getAll().size()<1){
                    Client wayne = new Client("Wayne", "Bruce", "0760985982");
                    Client parker = new Client("Parker", "peter", "0760985982");
                    Client musk = new Client("Musk", "Ellon", "0760985982");
                    wayne.setId((int)db.clientDAO().insert(wayne));
                    parker.setId((int)db.clientDAO().insert(parker));
                    musk.setId((int)db.clientDAO().insert(musk));

                    Vehicule batmobile = new Vehicule("Mobile", "Bat", "B4TM4N", 999.99f, 2, 2, "Diesel", 5, false, false, true);
                    Vehicule busScolaire = new Vehicule("Scolaire", "Bus", "T0B3Y", 12.25f, 2, 9, "Diesel", 5, false, false, true);
                    Vehicule tesla = new Vehicule("Model S", "Tesla", "SP4C3X", 98f, 5, 5, "Électrique", 1, false, false, false);
                    Vehicule kykymobile = new Vehicule("mobile", "kyky", "F4LC0N", 72f, 5, 5, "Essence", 2, true, false, true);
                    Vehicule twingo = new Vehicule("Twingo", "Renault", "SW4G", 28.75f, 3, 4, "Essence", 3, false, true, true);
                    Vehicule deLorean = new Vehicule("DeLorean DMC-12", "DMC", "Z3U5", 125.75f, 2, 2, "Essence", 5, false, false, true);
                    Vehicule classeC = new Vehicule("Class C", "Mercedes", "BL1NG", 42.75f, 5, 5, "Essence", 1, false, false, true);
                    Vehicule punto = new Vehicule("Punto", "Fiat", "Y0L0", 28.74f, 3, 5, "Diesel", 3, false, true, true);
                    Vehicule kiaRio = new Vehicule("Rio", "Kia", "W33B", 33.5f, 3, 5, "Essence", 1, false, true, true);
                    Vehicule c4 = new Vehicule("C4", "Citroën", "44BZH", 35.45f, 5, 5, "Diesel", 3, false, false, true);
                    batmobile.setId((int)db.vehiculeDAO().insert(batmobile));
                    busScolaire.setId((int)db.vehiculeDAO().insert(busScolaire));
                    tesla.setId((int)db.vehiculeDAO().insert(tesla));
                    kykymobile.setId((int)db.vehiculeDAO().insert(kykymobile));
                    twingo.setId((int)db.vehiculeDAO().insert(twingo));
                    deLorean.setId((int)db.vehiculeDAO().insert(deLorean));
                    classeC.setId((int)db.vehiculeDAO().insert(classeC));
                    punto.setId((int)db.vehiculeDAO().insert(punto));
                    kiaRio.setId((int)db.vehiculeDAO().insert(kiaRio));
                    c4.setId((int)db.vehiculeDAO().insert(c4));

                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.MONTH, -1);
                    c.set(Calendar.DAY_OF_MONTH, 4);
                    long timeStampDebut = c.getTimeInMillis();
                    c.set(Calendar.DAY_OF_MONTH, 16);
                    long timeStampFin = c.getTimeInMillis();
                    Date dateDebut = new Date();
                    dateDebut.setTime(timeStampDebut);
                    Date dateFin = new Date();
                    dateFin.setTime(timeStampFin);


                    Location loc1 = new Location(batmobile, wayne, dateDebut, dateFin, 12, 11999.88f);
                    Location loc2 = new Location(busScolaire, parker, dateDebut, dateFin, 12, 147f);
                    Location loc3 = new Location(tesla, musk, dateDebut, null, 12, 1176f);
                    db.locationDAO().insertAll(loc1, loc2, loc3);

                    ListeVehiculesActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateListe();
                        }
                    });
                }
            }
        }).start();

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
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_liste_vehicules, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Filtres:
                if (item.isChecked()) {
                    collapse(cardViewFiltresListeVehicules);
                } else {
                    expand(cardViewFiltresListeVehicules);
                }
                return true;
            case R.id.ChiffreAffaire:
                Intent intentToConfiguration = new Intent(this, ChiffreAffaireActivity.class);
                startActivity(intentToConfiguration);
                return true;
            case R.id.buttonFilterDispo:
                filterDispo = item.isChecked();
                item.setChecked(!filterDispo);
                int icon = item.isChecked()?R.drawable.ic_directions_car_green_24dp:R.drawable.ic_directions_car_red_24dp;
                item.setIcon(icon);
                MenuItem menuFiltre = menu.findItem(R.id.Filtres);
                if(filterDispo){
                    menuFiltre.setEnabled(true);
                    menuFiltre.getIcon().setAlpha(255);
                }
                else{
                    menuFiltre.setEnabled(false);
                    menuFiltre.getIcon().setAlpha(130);
                    if(findViewById(R.id.cardViewFilterListeArticles).getVisibility() == View.VISIBLE){
                        collapse(cardViewFiltresListeVehicules);
                    }
                }
                updateListe();
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
                                        intentDetailVehiculeActivity.putExtra("vehicule",vehicule);
                                        startActivity(intentDetailVehiculeActivity);
                                    }
                                },ListeVehiculesActivity.this);
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
                                        intentDetailVehiculeActivity.putExtra("vehicule",vehicule);
                                        startActivity(intentDetailVehiculeActivity);
                                    }
                                },ListeVehiculesActivity.this);
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
                    listeVehicules = db.vehiculeDAO().getFilterIndispo();
                    ListeVehiculesActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<Vehicule> arrayListeVehicule = new ArrayList<>(listeVehicules);
                            RecyclerViewVehiculeAdapter rvaa = new RecyclerViewVehiculeAdapter(arrayListeVehicule, new RecyclerViewVehiculeAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Vehicule vehicule) {
                                    Intent intentDetailVehiculeActivity = new Intent(ListeVehiculesActivity.this, DetailVehiculeActivity.class);
                                    intentDetailVehiculeActivity.putExtra("vehicule",vehicule);
                                    startActivity(intentDetailVehiculeActivity);
                                }
                            },ListeVehiculesActivity.this);
                            recyclerView.setAdapter(rvaa);
                        }
                    });
                }
            }).start();
        }
    }
}
