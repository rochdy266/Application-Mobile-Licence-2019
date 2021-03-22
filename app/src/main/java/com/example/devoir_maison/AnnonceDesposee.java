package com.example.devoir_maison;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import static com.example.devoir_maison.Annonce.EXTRA_DATE;
import static com.example.devoir_maison.Annonce.EXTRA_DES;
import static com.example.devoir_maison.Annonce.EXTRA_EMAILV;
import static com.example.devoir_maison.Annonce.EXTRA_ID;
import static com.example.devoir_maison.Annonce.EXTRA_IDV;
import static com.example.devoir_maison.Annonce.EXTRA_NBP;
import static com.example.devoir_maison.Annonce.EXTRA_NOMV;
import static com.example.devoir_maison.Annonce.EXTRA_POSTAL;
import static com.example.devoir_maison.Annonce.EXTRA_PRENOMV;
import static com.example.devoir_maison.Annonce.EXTRA_PRIX;
import static com.example.devoir_maison.Annonce.EXTRA_TELV;
import static com.example.devoir_maison.Annonce.EXTRA_TITRE;
import static com.example.devoir_maison.Annonce.EXTRA_URL;
import static com.example.devoir_maison.Annonce.EXTRA_Ville;

public class AnnonceDesposee extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DataBaseManager dataBaseManager;
    private ActionBarDrawerToggle mtoggle;
    private NavigationView navigationView;
    private DrawerLayout mDrawLayout;
    private Propriete propriete;
    private ArrayList<String> mListeImage ;
    int j = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBaseManager = new DataBaseManager(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_deposee);
        mDrawLayout = (DrawerLayout) findViewById(R.id.drawLayout);
        mtoggle = new ActionBarDrawerToggle(this, mDrawLayout, R.string.open, R.string.close);
        mDrawLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.configureNavigationView();
        mListeImage = new ArrayList<>();

        Intent i = getIntent();


        final ArrayList<String> image_url = i.getStringArrayListExtra(EXTRA_URL);

        final String titre = i.getStringExtra(EXTRA_TITRE);
        final String ville = i.getStringExtra(EXTRA_Ville);
        final String date = i.getStringExtra(EXTRA_DATE);
        final String emailVendeur = i.getStringExtra(EXTRA_EMAILV);
        final String nomVendeur = i.getStringExtra(EXTRA_NOMV);
        final String telVendeur = i.getStringExtra(EXTRA_TELV);
        final String codePostal = i.getStringExtra(EXTRA_POSTAL);
        final String idProp = i.getStringExtra(EXTRA_ID);
        final int prix = i.getIntExtra(EXTRA_PRIX, 0);
        final String description = i.getStringExtra(EXTRA_DES);
        final int nbPiece = i.getIntExtra(EXTRA_NBP, 0);
        final String idVendeur= i.getStringExtra(EXTRA_IDV);
        final String prenomVendeur = i.getStringExtra(EXTRA_PRENOMV);

        final TextView valueTV = new TextView(this);
        final ImageView img = findViewById(R.id.image);
        final ImageView imgDroite = findViewById(R.id.droite);
        final ImageView imgGauche = findViewById(R.id.gauche);
        final TextView titre1 = findViewById(R.id.titre);
        TextView ville1 = findViewById(R.id.Ville);
        TextView date1 = findViewById(R.id.date);
        TextView emailVendeur1 = findViewById(R.id.emailVendeur);
        TextView nomVendeur1 = findViewById(R.id.nomVendeur);
        TextView telVendeur1 = findViewById(R.id.telVendeur);
        TextView codePostal1 = findViewById(R.id.codePostale);
        TextView prix1 = findViewById(R.id.prix);
        TextView description1 = findViewById(R.id.description);
        TextView nbPiece1 = findViewById(R.id.nbPience);
        Button modifier = (Button) findViewById(R.id.modifier);

        Picasso.with( getBaseContext()).load(new File(image_url.get(0))).fit().centerCrop().into(img);

        titre1.setText(titre);
        ville1.setText(ville);
        date1.setText(date);
        emailVendeur1.setText(emailVendeur);
        nomVendeur1.setText(nomVendeur);
        telVendeur1.setText(telVendeur);
        codePostal1.setText(codePostal);
        prix1.setText(String.valueOf(prix));
        description1.setText(description);
        nbPiece1.setText(String.valueOf(nbPiece));


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
            case R.id.accueil : {
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


}
