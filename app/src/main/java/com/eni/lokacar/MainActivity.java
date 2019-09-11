package com.eni.lokacar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.eni.lokacar.data.dal.AppDatabase;
import com.eni.lokacar.data.model.Client;
import com.eni.lokacar.data.model.Location;
import com.eni.lokacar.data.model.Vehicule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Client stark;
    Client wayne;
    Client parker;

    Vehicule c4;
    Vehicule twingo;
    Vehicule punto;
    Vehicule classA;
    Vehicule p206;
    Vehicule scenic;
    Vehicule c8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "LokacarBDD").build();
/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                db.clientDAO().insertAll(
                        stark = new Client("Stark", "Tony", "0123456789"),
                        wayne = new Client("Wayne", "Bruce", "987654321"),
                        parker = new Client("Parker", "Peter", "3630")
                );
                for(Client client: db.clientDAO().getAll()){
                    Log.i(TAG, "LOG -- Client: "+client.getNom()+" "+client.getPrenom() + " "+client.getTelephone());
                }

                long vehiculeId = db.vehiculeDAO().insert(c4 = new Vehicule("C4", "Citroen", "26AB372", 47.99f, "path/to/photo", 5, 5, "Diesel", 3, true, false, true));

                db.vehiculeDAO().insertAll(
                       twingo = new Vehicule("Twingo", "Renault", "SW4G", 35f, "path/to/photo", 3, 4, "Essence", 2, false, true, true),
                       punto = new Vehicule("Punto", "Fiat", "Y0L0", 37f, "path/to/photo", 3, 5, "Diesel", 4, false, true, false),
                       classA = new Vehicule("Class A", "Mercedes", "PR1NC355", 52f, "path/to/photo", 5, 5, "Diesel", 4, false, true, false),
                       p206 = new Vehicule("206", "Peugeot", "PR1NC355", 42f, "path/to/photo", 3, 5, "Essence", 3, false, true, true),
                       scenic = new Vehicule("Scenic", "Renault", "F4M1LLY", 49.50f, "path/to/photo", 5, 5, "Essence", 2, true, false, true),
                       c8 = new Vehicule("C8", "Citroen", "F4TB0Y", 62.25f, "path/to/photo", 5, 8, "Diesel", 5, true, false, true)
                );
                for(Vehicule vehicule: db.vehiculeDAO().getAll()){
                    Log.i(TAG, "LOG -- Vehicule: "+vehicule.getMarque()+" "+vehicule.getModele()+" "+vehicule.getPrixJour()+"€");
                }

                ArrayList<String> photosAvant = new ArrayList<>();
                ArrayList<String> photosApres = new ArrayList<>();
                photosAvant.add("path/to/photo1");
                photosAvant.add("path/to/photo2");
                photosApres.add("path/to/photo3");

                Date demain = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(demain);
                c.add(Calendar.DATE, 1);
                demain = c.getTime();

                db.locationDAO().insertAll(
                        new Location(c4, stark, photosAvant, photosApres, new Date(), null, 36, 750f),
                        new Location(twingo, wayne, photosAvant, photosApres, new Date(), demain, 22, 2500.25f),
                        new Location(punto, parker, photosAvant, photosApres, demain, null, 7, 250.25f)
                );

                for(Location location: db.locationDAO().getAll()){
                    Log.i(TAG, "LOG -- : "+location.getDateDebut()+" "+location.getNbJours()+" "+location.getPrix()+" "+location.getPhotoApres());
                }

                // Get one location by ID Vehicule
                Location lastLocation = db.locationDAO().getLastByVehicule(4);
                Log.i(TAG, "LOG -- locationByVehiculeId (4): Client="+lastLocation.getClient().getPrenom()+ " DateDebut="+lastLocation.getDateDebut()+" nbJours="+lastLocation.getNbJours()+" prix="+lastLocation.getPrix());

                // Get one Client by ID
                Client myClient = db.clientDAO().getClientById(2);
                Log.i(TAG, "LOG -- clientById (2): "+myClient.getPrenom()+ " "+myClient.getNom()+" "+myClient.getTelephone());

                // TODO test Vehicules FILTERS
                List<Vehicule> vehiculesDispo = db.vehiculeDAO().getFilterDispo(50, 65, 5, 9, 5, 5, "%%", 4, false);

                List<Vehicule> vehiculesDispo2 = db.vehiculeDAO().getFilterDispo(0, 100, 2, 9, 2, 6, "%%", 5, true);

                List<Vehicule> vehiculesDispoAttelage = db.vehiculeDAO().getFilterAttelage(40, 55, 5, 5, 5, 5, "Essence", 4, false);

                List<Vehicule> vehiculesIndispo = db.vehiculeDAO().getFilterIndispo(0, 100, 2, 9, 2, 6, "%%", 5);

                Log.i(TAG, "LOG -- vehiculeDispo 1 SIZE: "+vehiculesDispo.size());
                for (Vehicule vehicule: vehiculesDispo){
                    Log.i(TAG, "LOG -- vehiculeDispo 1: "+vehicule.getModele());
                }

                Log.i(TAG, "LOG -- vehiculeDispo 2 SIZE: "+vehiculesDispo2.size());
                for (Vehicule vehicule: vehiculesDispo2){
                    Log.i(TAG, "LOG -- vehiculeDispo 2: "+vehicule.getModele());
                }

                Log.i(TAG, "LOG -- vehiculeDispoAttelage SIZE: "+vehiculesDispoAttelage.size());
                for (Vehicule vehicule: vehiculesDispoAttelage){
                    Log.i(TAG, "LOG -- vehiculeDispoAttelage: "+vehicule.getModele());
                }

                Log.i(TAG, "LOG -- vehiculeIndispo SIZE: "+vehiculesIndispo.size());
                for (Vehicule vehicule: vehiculesIndispo){
                    Log.i(TAG, "LOG -- vehiculeIndispo : "+vehicule.getModele());
                }

            }
        }).start();
 */
    }
}
