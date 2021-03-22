package com.example.devoir_maison;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class Annonce extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , AnnonceAdapter.onItemClickListener1, RecyclerItemTouchHelperListener{
    DataBaseManager dataBaseManager;
    public static final String EXTRA_URL = "images";
    public static final String EXTRA_TITRE = "titre";
    public static final String EXTRA_Ville = "ville";
    public static final String EXTRA_DATE = "date";
    public static final String EXTRA_EMAILV = "email";
    public static final String EXTRA_TELV = "telephone";
    public static final String EXTRA_NOMV = "nom";
    public static final String EXTRA_DES = "description";
    public static final String EXTRA_POSTAL = "codePostal";
    public static final String EXTRA_PRIX = "prix";
    public static final String EXTRA_NBP = "nbPieces";
    public static final String EXTRA_ID = "idPropriete";
    public static final String EXTRA_PRENOMV = "prenom";
    public static final String EXTRA_IDV = "idVendeur";
    View v;
    private RecyclerView myRecyclerView;
    private AnnonceAdapter mAnnonceAdapter;
    public static ArrayList<Propriete> mListeAnnonce ;
    private ActionBarDrawerToggle mtoggle;
    private NavigationView navigationView;
    private DrawerLayout mDrawLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeannonces);



        myRecyclerView = findViewById(R.id.recycle_view);
        dataBaseManager = new DataBaseManager(this);
        mDrawLayout = (DrawerLayout) findViewById(R.id.drawLayout);
        mtoggle = new ActionBarDrawerToggle(this, mDrawLayout, R.string.open, R.string.close);
        mDrawLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.configureNavigationView();
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListeAnnonce = dataBaseManager.AfficherAnnonce();






        mAnnonceAdapter = new AnnonceAdapter(Annonce.this, mListeAnnonce);
        myRecyclerView.setAdapter(mAnnonceAdapter);
        mAnnonceAdapter.setOnItemClickListenner1(Annonce.this);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new RecyclerItemTouch
                (0,ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(myRecyclerView);

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app, menu);
        return true;
    }

    private void configureNavigationView() {
        this.navigationView = (NavigationView) findViewById(R.id.menu_bar);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
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




    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accueil: {
                Intent accueilItem = new Intent(this, MainActivity.class);
                startActivity(accueilItem);
            }
            return true;
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




    @Override
    public void onItemClick1(int position) {
        Intent  intent = new Intent(this,AnnonceDesposee.class);
        Propriete  click =  mListeAnnonce.get(position);
        intent.putExtra(EXTRA_ID,click.getIdPropriete());
        intent.putExtra(EXTRA_URL,click.getImg());
        intent.putExtra(EXTRA_TITRE,click.getTitre());
        intent.putExtra(EXTRA_Ville,click.getVille());
        intent.putExtra(EXTRA_DATE,click.getDate());
        intent.putExtra(EXTRA_EMAILV,click.getV().getEmail());
        intent.putExtra(EXTRA_NOMV,click.getV().getNom());
        intent.putExtra(EXTRA_TELV,click.getV().getTelephone());
        intent.putExtra(EXTRA_IDV,click.getV().getIdVendeur());
        intent.putExtra(EXTRA_PRENOMV,click.getV().getPrenom());
        intent.putExtra(EXTRA_DES,click.getDescription());
        intent.putExtra(EXTRA_NBP,click.getNb_piece_disponible());
        intent.putExtra(EXTRA_POSTAL,click.getCode_postale());
        intent.putExtra(String.valueOf(EXTRA_PRIX),click.getPrix());
        startActivity(intent);
    }
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof AnnonceAdapter.MyViewHolder) {
            String titre = mListeAnnonce.get(viewHolder.getAdapterPosition()).getTitre();
            Propriete deletedItem = mListeAnnonce.get(viewHolder.getAdapterPosition());
            mAnnonceAdapter.removeItem(position, this);
        }
    }
    public float removeItem(String id){
        return dataBaseManager.delete1(id);
    }
}
