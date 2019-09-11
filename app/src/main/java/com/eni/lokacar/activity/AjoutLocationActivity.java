package com.eni.lokacar.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eni.lokacar.MainActivity;
import com.eni.lokacar.R;
import com.eni.lokacar.data.dal.AppDatabase;
import com.eni.lokacar.data.model.Client;
import com.eni.lokacar.data.model.Location;
import com.eni.lokacar.data.model.Vehicule;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class AjoutLocationActivity extends AppCompatActivity {
    private static final String TAG = "AjoutLocationActivity";
    private AppDatabase db = null;

    EditText editTextNom;
    EditText editTextPrenom;
    EditText editTextTelephone;
    EditText editTextNbJours;
    TextView textViewCalculPrix;
    TextView textViewNbPhotos;
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
        editTextNbJours = findViewById(R.id.editTextNbJours);
        textViewCalculPrix = findViewById(R.id.textViewCalculPrix);

        Intent intent = getIntent();
        // Get the extras (if there are any)
        Bundle extras = intent.getExtras();

        if (extras != null) {
            vehiculeExtra = (Vehicule)getIntent().getExtras().get("vehicule");
        }else{
            // TODO ERROR HANDLING
            Toast.makeText(this, "VehiculeExtra is NULL", Toast.LENGTH_SHORT).show();
            //TODO remove debug default value
            new Thread(new Runnable(){
                @Override
                public void run() {
                    vehiculeExtra = new Vehicule("Twingo", "Renault", "Y0L0W4G", 39.99f, "path/to/photo", 3, 4, "Essence", 3, false, true, true);
                    long idVehicule = db.vehiculeDAO().insert(vehiculeExtra);
                    vehiculeExtra.setId((int)idVehicule);
                    Log.i(TAG, "LOG -- VehiculeExtra created with ID: "+vehiculeExtra.getId()+" At "+new Date());
                }
            }).start();
        }

        editTextNbJours.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String prixStr = editTextNbJours.getText().toString();
                int nbJours=0;
                if(!"".equals(prixStr)){
                    nbJours = Integer.parseInt(editTextNbJours.getText().toString());
                }
                DecimalFormat df = new DecimalFormat("##.##");
                df.setRoundingMode(RoundingMode.DOWN);
                float prix = Float.parseFloat(df.format(vehiculeExtra.getPrixJour()*nbJours));

                textViewCalculPrix.setText("Prix total: "+prix+"€");
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_ajout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuItemValider){
            // TODO CHECK FIELDS INTEGRITY

            // CREATE Client
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Client client = new Client(editTextNom.getText().toString(), editTextPrenom.getText().toString(), editTextTelephone.getText().toString());
                    long clientId = db.clientDAO().insert(client);
                    client.setId(((int) clientId));

                    // CREATE Location
                    int nbJours = Integer.parseInt(editTextNbJours.getText().toString());
                    DecimalFormat df = new DecimalFormat("##.##");
                    df.setRoundingMode(RoundingMode.DOWN);
                    float prix = Float.parseFloat(df.format(vehiculeExtra.getPrixJour()*nbJours));
                    Location location = new Location(
                            vehiculeExtra,
                            client,
                            listePhotos,
                            null,
                            new Date(),
                            null,
                            nbJours,
                            prix
                    );
                    long idLocation = db.locationDAO().insert(location);

                    Log.i(TAG, "LOG -- location created with ID: "+idLocation+" At "+new Date());
                }
            }).start();

            // TODO start ListActivity + Toast Message
            Intent intentStartActivity = new Intent(this, MainActivity.class);
            startActivity(intentStartActivity);
            Toast.makeText(this, "Location créée !", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
