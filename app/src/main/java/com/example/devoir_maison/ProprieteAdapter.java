package com.example.devoir_maison;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProprieteAdapter extends RecyclerView.Adapter<ProprieteAdapter.ProprieteViewHolder> {
    private Context mContext ;
    private ArrayList<Propriete> liste;
    private onItemClickListener mListenner;


    public interface onItemClickListener
    {
        void onItemClick(int position);

    }

    public void setOnItemClickListenner(onItemClickListener listenner){
        mListenner = listenner;

    }

    public ProprieteAdapter(Context mContext, ArrayList<Propriete> liste) {
        this.mContext = mContext;
        this.liste = liste;
    }


    @Override
    public ProprieteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item,parent,false);
        return  new ProprieteViewHolder(v);

    }

    @Override
    public void onBindViewHolder( ProprieteViewHolder holder, int position) {
    Propriete p = liste.get(position);
    String titre = p.getTitre() ;
    String ville = p.getVille() ;

        int prix = p.getPrix() ;
        Random r = new Random();
        int valeur = 0 + r.nextInt(p.getImg().size() - 0);
        String img= p.getImg().get(valeur);

    holder.titre.setText(titre);
    holder.ville.setText(ville);
        holder.prix.setText(String.valueOf(prix));

        Picasso.with(mContext).load(img).fit().centerCrop().into(holder.img);

    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public class ProprieteViewHolder extends RecyclerView.ViewHolder {
        public TextView titre ;
        public TextView ville ;
        public TextView prix ;

        public CircleImageView img ;
        public ProprieteViewHolder(View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.titre);
            ville = itemView.findViewById(R.id.ville);
           img = itemView.findViewById(R.id.img);
            prix =  itemView.findViewById(R.id.prix);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListenner != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListenner.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}


