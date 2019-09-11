package com.eni.lokacar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class AjoutVehiculeActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_PHOTO = 1;
    String[] spinnerCarburantArray = new String[]{"Essence", "Diesel", "Électrique", "GPL", "Hydrogène"};
    String[] spinnerCritAirArray = new String[]{"0", "1", "2", "3", "4", "5"};
    String[] spinnerNbPlacesArray = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
    String[] spinnerNbPortesArray = new String[]{"2", "3", "4", "5", "6"};
    EditText editTextMarque, editTextModele, editTextPlaque, editTextPrixJour;
    Spinner spinnerCarburant, spinnerCritAir, spinnerNbPlaces, spinnerNbPortes;
    Switch switchCitadine, switchAttelage;
    Button buttonPhoto;
    ImageView imageViewAjoutVoiture;
    private File tempProfileImageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_vehicule);

        //setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        editTextMarque = findViewById(R.id.editTextMarque);
        editTextModele = findViewById(R.id.editTextModele);
        editTextPlaque = findViewById(R.id.editTextPlaque);
        editTextPrixJour = findViewById(R.id.editTextPrixJour);
        spinnerCarburant = findViewById(R.id.spinnerCarburant);
        spinnerCritAir = findViewById(R.id.spinnerCritAir);
        spinnerNbPlaces = findViewById(R.id.spinnerNbPlaces);
        spinnerNbPortes = findViewById(R.id.spinnerNbPortes);
        switchCitadine = findViewById(R.id.switchCitadine);
        switchAttelage = findViewById(R.id.switchAttelage);
        buttonPhoto = findViewById(R.id.buttonPhoto);
        imageViewAjoutVoiture = findViewById(R.id.imageViewAjoutVoiture);

        ArrayAdapter<String> adapterCarburant = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, spinnerCarburantArray);
        adapterCarburant.setDropDownViewResource(R.layout.spinner_textview_align);
        spinnerCarburant.setAdapter(adapterCarburant);
        spinnerCarburant.setSelection(0);

        ArrayAdapter<String> adapterCritAir = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, spinnerCritAirArray);
        adapterCritAir.setDropDownViewResource(R.layout.spinner_textview_align);
        spinnerCritAir.setAdapter(adapterCritAir);
        spinnerCritAir.setSelection(1);

        ArrayAdapter<String> adapterNbPlaces = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, spinnerNbPlacesArray);
        adapterNbPlaces.setDropDownViewResource(R.layout.spinner_textview_align);
        spinnerNbPlaces.setAdapter(adapterNbPlaces);
        spinnerNbPlaces.setSelection(4);

        ArrayAdapter<String> adapterNbPortes = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, spinnerNbPortesArray);
        adapterNbPortes.setDropDownViewResource(R.layout.spinner_textview_align);
        spinnerNbPortes.setAdapter(adapterNbPortes);
        spinnerNbPortes.setSelection(3);


        buttonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCamera();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_liste_vehicule_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId() == R.id.valider){
//            String marque = editTextMarque.getText().toString();
//            String modele = editTextModele.getText().toString();
//            String plaque = editTextPlaque.getText().toString();
//            Float prixJour = Float.valueOf(editTextPrixJour.getText().toString());
//            String carburant = spinnerCarburantArray[spinnerCarburant.getSelectedItemPosition()];
//            int critAir = Integer.valueOf(spinnerCritAirArray[spinnerCritAir.getSelectedItemPosition()]);
//            int nbPlaces = Integer.valueOf(spinnerNbPlacesArray[spinnerNbPlaces.getSelectedItemPosition()]);
//            int nbPortes = Integer.valueOf(spinnerNbPortesArray[spinnerNbPortes.getSelectedItemPosition()]);
//            Boolean citadine = switchCitadine.isChecked();
//            Boolean attelage = switchAttelage.isChecked();
//            Vehicule vehicule = new Vehicule(modele,marque,plaque,prixJour,null,nbPortes,nbPlaces,carburant,critAir,attelage,citadine,true);
//            //TODO ajouter le véhicule créé en base
//            return true;
//        }
        return false;
    }


    private void startCamera() {
        File tempFile = createTempImageFile();
        if (tempFile != null) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if ((intent.resolveActivity(getPackageManager()) != null)) {
                tempProfileImageFile = tempFile;
                Uri photoURI = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".profileimage.fileprovider", tempFile);

                // Samsung Galaxy S3 Fix
                List<ResolveInfo> resInfoList = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }

                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, REQUEST_CODE_PHOTO);
            }
        }
    }

    private File createTempImageFile() {
        try {
            return File.createTempFile(
                    "TEMP_PROFILE_IMAGE",
                    ".jpg",
                    getExternalFilesDir("images")
            );
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_PHOTO && resultCode == RESULT_OK) {
            saveProfilePicture(tempProfileImageFile);
        }
    }

    private void saveProfilePicture(File tempFile) {
        final File imageFile = new File(getFilesDir(), new Random().nextInt() +".jpg");
        if (imageFile.exists()) {
            imageFile.delete();
        }

        if(tempFile != null)
        {
            String tempPath = tempFile.getAbsolutePath();
            try {
                Bitmap bitmapOrg = createOriginalBitmap(tempPath);
                bitmapOrg = rotateImage(tempPath, bitmapOrg); //Prendre une photo à l'horizontale fait crash l'appli
                final Bitmap finalBitmap = resizeBitmap(bitmapOrg);
                //imageViewAjoutVoiture.setImageBitmap(finalBitmap);
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(imageFile);
                    finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                } catch (final Exception e) {
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (final IOException e) {
                    }
                }
            } catch (final Exception e) {
                return;
            }

            Picasso.get().load(imageFile).rotate(90).into(imageViewAjoutVoiture);
            Picasso.get().invalidate(imageFile);

            try {
                tempFile.delete();
            } catch (final Exception e) {
            }
        }
    }

    private Bitmap createOriginalBitmap(final String imagePath) {
        final Bitmap bitmapOrg;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bitmapOrg = BitmapFactory.decodeFile(imagePath);
        } else {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inDither = true;
            bitmapOrg = BitmapFactory.decodeFile(imagePath, options);
        }
        return bitmapOrg;
    }

    private static Bitmap rotateImage(final String imagePath, Bitmap source) throws IOException {
        final ExifInterface ei = new ExifInterface(imagePath);
        final int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                source = rotateImageByAngle(source, 90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                source = rotateImageByAngle(source, 180);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                source = rotateImageByAngle(source, 270);
                break;
        }
        return source;
    }

    public static Bitmap rotateImageByAngle(final Bitmap source, final float angle) {
        final Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    private static Bitmap resizeBitmap(Bitmap source) {
        final int heightOrg = source.getHeight();
        final int heightNew = 800;
        if (heightNew < heightOrg) {
            final int widthOrg = source.getWidth();
            final int widthNew = (heightNew * widthOrg) / heightOrg;

            final Matrix matrix = new Matrix();
            matrix.postScale(((float) widthNew) / widthOrg, ((float) heightNew) / heightOrg);
            source = Bitmap.createBitmap(source, 0, 0, widthOrg, heightOrg, matrix, false);
        }
        return source;
    }
}

