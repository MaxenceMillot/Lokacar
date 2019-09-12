package com.eni.lokacar.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eni.lokacar.R;
import com.eni.lokacar.data.dal.AppDatabase;
import com.eni.lokacar.data.model.Client;
import com.eni.lokacar.data.model.Location;
import com.eni.lokacar.data.model.Vehicule;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class AjoutLocationActivity extends AppCompatActivity {
    private static final String TAG = "AjoutLocationActivity";
    public static final int REQUEST_CODE = 42;
    private AppDatabase db = null;

    EditText editTextNom;
    EditText editTextPrenom;
    EditText editTextTelephone;
    EditText editTextNbJours;
    TextView textViewCalculPrix;
    TextView textViewNbPhotos;

    private Vehicule vehiculeExtra;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_location);

        db = Room.databaseBuilder(this, AppDatabase.class, "LokacarBDD").build();

        // Définition de du menu (toolbar) sans Titre
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO prise de photos
        editTextNom = findViewById(R.id.editTextNomClient);
        editTextPrenom = findViewById(R.id.editTextPrenomClient);
        editTextTelephone = findViewById(R.id.editTextTelephoneClient);
        editTextNbJours = findViewById(R.id.editTextNbJours);
        textViewCalculPrix = findViewById(R.id.textViewCalculPrix);

        // Get the extras (if there are any)
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            vehiculeExtra = getIntent().getParcelableExtra("vehicule");
        }else{
            // TODO ERROR HANDLING
            Intent intent = new Intent(AjoutLocationActivity.this, ListeVehiculesActivity.class);
            startActivity(intent);
            Toast.makeText(AjoutLocationActivity.this, "Erreur: Aucun ID de véhicule", Toast.LENGTH_LONG).show();
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
                float prix = Float.parseFloat(df.format(vehiculeExtra.getPrixJour()*nbJours).replace(",", "."));
                textViewCalculPrix.setText("Prix total: "+prix+"€");
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
                    String message = "Bonjour "+  location.getClient().getPrenom()+ " "+ location.getClient().getNom()+", votre location chez Lokacar à été validée.\n\n" +
                            "Vous avez réservé une "+vehiculeExtra.getMarque()+" "+vehiculeExtra.getModele()+
                            " pour une durée de "+location.getNbJours()+" jours à compter du "+
                            dateFormat.format(location.getDateDebut())+".\n\n"+
                            "Bonne route.";
                    SmsManager manager = SmsManager.getDefault();
                    ArrayList<String> parts = manager.divideMessage(message);
                    manager.sendMultipartTextMessage(
                            location.getClient().getTelephone(),
                            null,
                            parts,
                            null, null);

                    Intent intentStartActivity = new Intent(AjoutLocationActivity.this, ListeVehiculesActivity.class);
                    startActivity(intentStartActivity);
                    Toast.makeText(AjoutLocationActivity.this, "Location créée", Toast.LENGTH_LONG).show();
                }else{
                    Intent intentStartActivity = new Intent(AjoutLocationActivity.this, ListeVehiculesActivity.class);
                    startActivity(intentStartActivity);
                    Toast.makeText(AjoutLocationActivity.this, "Location créée", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_ajout, menu);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuItemValider){
            // TODO CHECK FIELDS INTEGRITY
            // issou

            new Thread(new Runnable() {
                @Override
                public void run() {
                    // CREATE Client
                    Client client = new Client(editTextNom.getText().toString().trim(), editTextPrenom.getText().toString().trim(), editTextTelephone.getText().toString().trim());
                    long clientId = db.clientDAO().insert(client);
                    client.setId(((int) clientId));

                    // CREATE Location
                    int nbJours = Integer.parseInt(editTextNbJours.getText().toString());
                    DecimalFormat df = new DecimalFormat("##.##");
                    df.setRoundingMode(RoundingMode.DOWN);
                    float prix = Float.parseFloat(df.format(vehiculeExtra.getPrixJour()*nbJours).replace(",", "."));
                    location = new Location(
                            vehiculeExtra,
                            client,
                            new Date(),
                            null,
                            nbJours,
                            prix
                    );
                    long idLocation = db.locationDAO().insert(location);

                    // UPDATE Vehicule isDispo
                    vehiculeExtra.setDispo(false);

                    db.vehiculeDAO().updateVehicule(vehiculeExtra);

                    // Confirmation par SMS
                    ActivityCompat.requestPermissions(AjoutLocationActivity.this,new String[]{Manifest.permission.SEND_SMS}, REQUEST_CODE);
                }
            }).start();
        }
        return false;
    }
}
