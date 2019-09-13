package com.eni.lokacar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.eni.lokacar.R;
import com.eni.lokacar.data.dal.AppDatabase;
import com.eni.lokacar.data.model.Location;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChiffreAffaireActivity extends AppCompatActivity {
    private static final String TAG = "ChiffreAffaireActivity";
    private AppDatabase db = null;

    TextView textViewChiffreAffaire;
    String chiffreAffaire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chiffre_affaire);

        db = Room.databaseBuilder(this, AppDatabase.class, "LokacarBDD").build();

        textViewChiffreAffaire = findViewById(R.id.textViewChiffreAffaire);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Calendar c = Calendar.getInstance();   // this takes current date
                c.set(Calendar.DAY_OF_MONTH, 1);
                long mois = c.getTimeInMillis();
                c.add(Calendar.MONTH, -1);
                long moisMoinsUn = c.getTimeInMillis();

                chiffreAffaire = String.valueOf(db.locationDAO().getChiffreAffaire(moisMoinsUn, mois)+"€");
                ChiffreAffaireActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewChiffreAffaire.setText(chiffreAffaire);
                    }
                });
            }
        }).start();
    }
}
