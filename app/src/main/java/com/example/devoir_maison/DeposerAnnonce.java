package com.example.devoir_maison;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeposerAnnonce extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

   private DataBaseManager databaseManager;
    EditText titre,ville,description,prix,nb_piece,code_postal,nom,email,telephone,date_dep;
    Button save;
    String titre2,ville2,description2,nomVendeur2,emailVendeur2,telVendeur2,date2,codePostal2;
    int nbp2 , prix2 ;
    private ActionBarDrawerToggle mtoggle;
    private NavigationView navigationView;
    private DrawerLayout mDrawLayout;
    public static String path;
    String currentPhotoPath;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposer_annonce);

        databaseManager = new DataBaseManager(this);
        mDrawLayout = (DrawerLayout) findViewById(R.id.drawLayout);
        mtoggle = new ActionBarDrawerToggle(this, mDrawLayout, R.string.open, R.string.close);
        mDrawLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.configureNavigationView();
        titre=findViewById(R.id.titre);
        ville=findViewById(R.id.ville);
        description=findViewById(R.id.des);
        prix=findViewById(R.id.prix);
        nb_piece=findViewById(R.id.nbp);
        code_postal=findViewById(R.id.code_p);
        nom=findViewById(R.id.nom);
        email=findViewById(R.id.email);
        telephone=findViewById(R.id.numtel);
        date_dep=findViewById(R.id.dateDep);
        save=findViewById(R.id.save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titre2 = titre.getText().toString();
                ville2 = ville.getText().toString();
                description2  = description.getText().toString();
                try {

                    prix2 = Integer.parseInt(prix.getText().toString());
                    nbp2 = Integer.parseInt(nb_piece.getText().toString());
                } catch(NumberFormatException nfe) {
                    nfe. printStackTrace ();

                }
                date2  = date_dep.getText().toString();
                emailVendeur2  = email.getText().toString();
                nomVendeur2  = nom.getText().toString();
                telVendeur2  = telephone.getText().toString();
                codePostal2  =code_postal.getText().toString();


                if(titre2.equals("")|| ville2.equals("") || nbp2 == 0 ||  date2.equals("")|| emailVendeur2.equals("") || nomVendeur2.equals("") || telVendeur2.equals("")  || codePostal2.equals("") || prix2 == 0){
                    Toast.makeText(DeposerAnnonce.this, "Veuillez Remplir Tout Les Champs", Toast.LENGTH_LONG).show();

                }



                    else {
                    boolean i = databaseManager.insertion2(titre2, ville2, description2,nbp2 ,prix2, codePostal2,emailVendeur2, nomVendeur2, telVendeur2,date2);
                    boolean j= databaseManager.insertion3(path);

                    if (j){
                        Toast.makeText(DeposerAnnonce.this, "Photos ajoutée avec succés2", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(DeposerAnnonce.this, "echec d'ajout de l'annonce2", Toast.LENGTH_LONG).show();
                    }
                    if (i == true) {
                        Toast.makeText(DeposerAnnonce.this, "Annonce Ajoutèe avec succés", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DeposerAnnonce.this, "echec d'ajout de l'annonce", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater() ;
        inflater.inflate(R.menu.menu_app,menu);
        return true;
    }
    public  boolean onOptionsItemSelected (MenuItem item) {
        if (mtoggle.onOptionsItemSelected(item)) {
            // rendre le bouton enABLE
            return true;
        }
        if (item.getItemId() == R.id.propos) {
            Intent apropos = new Intent(this, Apropos.class);
            startActivity(apropos);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void configureNavigationView() {
        this.navigationView = (NavigationView) findViewById(R.id.menu_bar);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.accueil: {
                Intent accueilItem = new Intent(this,MainActivity.class);
                startActivity(accueilItem);
            }return true ;
            case R.id.hasard: {
                Intent hasardItem = new Intent(this, HasardAnnonces.class);
                startActivity(hasardItem);
            }
            return true;
            case R.id.listeannonces: {
                Intent ListeAnnoncesItem = new Intent(this, ListeAnnonces.class);
                startActivity(ListeAnnoncesItem);

            }
            return true;
            case R.id.favoris: {

                    Intent favorisItem = new Intent(this, Favoriss.class);
                    startActivity(favorisItem);

            }
            return true;
            case R.id.depot: {
                Intent favorisItem = new Intent(this, DeposerAnnonce.class);
                startActivity(favorisItem);
            }
            return true;
            case R.id.maliste: {

                Intent annonceItem = new Intent(this, Annonce.class);
                startActivity(annonceItem);
            }
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fct(View v) {
        // invoke the image gallery using an implict intent.
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        // where do we want to find the data?
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        // finally, get a URI representation
        Uri data = Uri.parse(pictureDirectoryPath);
        // set the data and type.  Get all image types.
        photoPickerIntent.setDataAndType(data, "image/*");
        // we will invoke this activity, and get something back from it.
        startActivityForResult(photoPickerIntent, 2);
    }

    public void fct2(View v){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
                path= photoFile.getAbsolutePath().toString();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, 1);
            }
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }








}
