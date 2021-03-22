package com.example.devoir_maison;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import com.squareup.moshi.Types;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HasardAnnonces extends AppCompatActivity {
    private ImageView imageV;
    private TextView prix;
    private TextView ville;
    private TextView email;
    private TextView tel;
    private TextView ident;
    private TextView descrip;
    private  TextView date;
    private  TextView titre;
    private JSONObject data ;
    private boolean done ;


    Moshi moshi = new Moshi.Builder().build();
    OkHttpClient client = new OkHttpClient();
    private Propriete propriete ;
    public static Vendeur vendeur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasard_annonces);

        prix = (TextView)findViewById(R.id.prix);
        ville = (TextView)findViewById(R.id.ville);
        email=(TextView)findViewById(R.id.email);
        descrip=(TextView)findViewById(R.id.description);
        ident=(TextView)findViewById(R.id.nom);
        tel=(TextView)findViewById(R.id.telVendeur);
        date=(TextView)findViewById(R.id.date);
        imageV=(ImageView)findViewById(R.id.img);
        titre = (TextView)findViewById(R.id.titre);

       makeHttpRequest();
    }

    /**
     *  Methode qui va placer chaque valeur d'une proprieté
     *  a l"endroit dans la fenetre
     */
    public void fillTheActivity(Propriete propriete) {


    }


    /**
     * Methode pour charger les données du Json
     * dans l'objet propriété.
     */
    private void makeHttpRequest() {


        Request request = new Request.Builder()
                .url("https://ensweb.users.info.unicaen.fr/android-estate/mock-api/liste.json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    final String responseBody = response.body().string();
                    final int statusCode = response.code();

                    ProprieteResponse proprieteResponse;
                    JsonAdapter<ProprieteResponse> jsonAdapter = moshi.adapter(ProprieteResponse.class);
                    proprieteResponse = jsonAdapter.fromJson(responseBody);

                    List<Propriete> p = proprieteResponse.getResponse();
                    Random r = new Random();
                    int valeur = 0 + r.nextInt(p.size() - 0) ;
                    propriete= p.get(valeur);
                } catch
                (Exception e) {

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        titre.setText(propriete.getTitre());



                    }
                });

            }
        });

    }
}
