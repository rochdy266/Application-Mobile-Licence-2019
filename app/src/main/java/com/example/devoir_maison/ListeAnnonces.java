package com.example.devoir_maison;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListeAnnonces extends AppCompatActivity implements ProprieteAdapter.onItemClickListener, NavigationView.OnNavigationItemSelectedListener {
    public static final String EXTRA_URL ="img";
    public static final String EXTRA_TITRE ="titre";
    public static final String EXTRA_Ville ="ville";
    public static final String EXTRA_DATE ="date";
    public static final String EXTRA_EMAILV ="email";
    public static final String EXTRA_TELV ="telephone";
    public static final String EXTRA_NOMV ="nom";
    public static final String EXTRA_DES ="description";
    public static final String EXTRA_POSTAL ="codePostal";
    public static final String EXTRA_PRIX = "prix";
    public static final String EXTRA_NBP ="nbPieces";
    public static final String EXTRA_ID ="idPropriete";
    public static final String EXTRA_PRENOMV ="prenom";
    public static final String EXTRA_IDV ="idVendeur";

    private RecyclerView mRecyleView;
    private ProprieteAdapter mProprieteAdapter ;
    private ArrayList<Propriete> mListePropriete ;
    private ArrayList<String> mListeImage ;
    private RequestQueue mResquestQueue;
    private ActionBarDrawerToggle mtoggle;
    private NavigationView navigationView;
    private DrawerLayout mDrawLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeannonces);
        mRecyleView = findViewById(R.id.recycle_view);
        mDrawLayout = (DrawerLayout) findViewById(R.id.drawLayout);
        mtoggle = new ActionBarDrawerToggle(this, mDrawLayout, R.string.open, R.string.close);
        mDrawLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.configureNavigationView();


        mRecyleView.setHasFixedSize(true);
        mRecyleView.setLayoutManager(new LinearLayoutManager(this) );

        mListePropriete = new ArrayList<>();
        mListeImage = new ArrayList<>();
        mResquestQueue = Volley.newRequestQueue(this);
        parseJson();


    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater() ;
        inflater.inflate(R.menu.menu_app,menu);
        return true;
    }
    private void configureNavigationView() {
        this.navigationView = (NavigationView) findViewById(R.id.menu_bar);
        navigationView.setNavigationItemSelectedListener(this);
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
    private void parseJson()
    {
        String url ="https://ensweb.users.info.unicaen.fr/android-estate/mock-api/liste.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("response");
                    for (int i =0 ; i< jsonArray.length() ; i++){
                        JSONObject respons = jsonArray.getJSONObject(i);
                        String titre = respons.getString("titre");
                        String idProp = respons.getString("id");
                        String ville= respons.getString("ville");
                        String description= respons.getString("description");
                        int nbPiece= respons.getInt("nbPieces");
                        int prix = respons.getInt("prix");
                        String codePostale = respons.getString("codePostal");
                        String date = respons.getString("date");

                        JSONArray  img = respons.getJSONArray("images") ;
                        for ( int y = 0 ; y<img.length();y++){
                           mListeImage.add(img.getString(y)) ;
                        }

                        JSONObject respons2 = respons.getJSONObject("vendeur");
                            String nom  = respons2.getString("nom") ;
                            String idV  = respons2.getString("id") ;
                            String prenom  = respons2.getString("prenom") ;
                            String email  = respons2.getString("email") ;
                            String tel  = respons2.getString("telephone") ;
                            Vendeur   v  = new Vendeur(idV,nom,prenom,email,tel) ;

                        mListePropriete.add(new Propriete(idProp,titre,description, ville,nbPiece,prix,codePostale,date,mListeImage,v) );
                    }
                    mProprieteAdapter =  new ProprieteAdapter(ListeAnnonces.this,mListePropriete);
                    mRecyleView.setAdapter(mProprieteAdapter);
                    mProprieteAdapter.setOnItemClickListenner(ListeAnnonces.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mResquestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent  intent = new Intent(this,OneAnnonces.class);
        Propriete  click =  mListePropriete.get(position);
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

