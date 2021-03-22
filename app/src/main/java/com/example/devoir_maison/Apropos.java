package com.example.devoir_maison;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Apropos extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ActionBarDrawerToggle mtoggle;
    private NavigationView navigationView;
    private DrawerLayout mDrawLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apropos);
        mDrawLayout = (DrawerLayout) findViewById(R.id.drawLayout);
        mtoggle = new ActionBarDrawerToggle(this, mDrawLayout, R.string.open, R.string.close);
        mDrawLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.configureNavigationView();

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
