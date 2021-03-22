package com.example.devoir_maison;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;


public class AnnonceAdapter extends RecyclerView.Adapter<AnnonceAdapter.MyViewHolder>{
    Context mContext;
    ArrayList<Propriete> mData;
    private static onItemClickListener1 mListenner1;


    public AnnonceAdapter(Context mContext, ArrayList<Propriete>  mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public interface onItemClickListener1
    {
        void onItemClick1(int position);

    }

    public void setOnItemClickListenner1(onItemClickListener1 listenner) {
        mListenner1 = listenner;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.card_item_favoris,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Propriete p = mData.get(position);
        String titre = p.getTitre() ;
        String ville = p.getVille() ;
        int prix = p.getPrix() ;
        String id =p.getIdPropriete();

        if (p.getImg().isEmpty()){

        }else{
            String image= p.getImg().get(0);
            Picasso.with(mContext).load(new File(image)).fit().centerCrop().into(holder.image);
        }



        //Bitmap bitmap= BitmapFactory.decodeFile(image);

        holder.tv_titre.setText(titre);
        holder.tv_ville.setText(ville);
        holder.tv_prix.setText(String.valueOf(prix));
        holder.itemView.setTag(id);




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public void removeItem(int position, Annonce annonce){
        Propriete p = mData.get(position);
        float supp =annonce.removeItem(p.getIdPropriete());

        mData.remove(position);
        notifyItemRemoved(position);
        Toast.makeText(annonce,"Suppression réussie",Toast.LENGTH_LONG).show();



    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout ViewForeground;
        public TextView tv_titre;
        public TextView tv_ville;
        public TextView tv_prix;
        public CircleImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_titre =  itemView.findViewById(R.id.titreFavoris);
            tv_ville =  itemView.findViewById(R.id.favorisVille);
            tv_prix =  itemView.findViewById(R.id.prixfavoris);
            image =  itemView.findViewById(R.id.imgFavoris);
            ViewForeground =  itemView.findViewById(R.id.recycle_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListenner1 != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListenner1.onItemClick1(position);
                        }
                    }
                }
            });

        }
    }

}
