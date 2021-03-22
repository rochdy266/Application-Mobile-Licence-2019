package com.example.devoir_maison;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

;import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "immobilier.db";
    private static final int DATABASE_VERSION = 4;
    public static final String table_favoris = "favoris";
    private static final String table_ajout = "AjoutAnnonce";
    public static final String table_image = "images";
    public static final String table_commentaire= "commentaire";
    public static final String table_image2 = "images2";
    private ArrayList<Propriete> mListePropriete  = new ArrayList<Propriete>();
    private ArrayList<Propriete> mListeAnnonce  = new ArrayList<Propriete>();

    private ArrayList<String> mListeImages  = new ArrayList<>();

    private static final String col1 = "idPropriete";
    private static final String col2 = "titre";
    private static final String col3 = "ville";
    private static final String col4 = "description";
    private static final String col5 = "nbPieces";
    private static final String col6 = "prix";
    private static final String col7 = "codePostal";
    private static final String col8 = "emailVendeur";
    private static final String col9 = "nomVendeur";
    private static final String col10 = "telVendeur";
    private static final String col12 = "prenomVendeur";
    private static final String col13 = "idVendeur";
    private static final String col11 = "date";
    private static final String ID1 = "id1";



    private static final String lig1 = "titre1";
    private static final String lig2 = "ville1";
    private static final String lig3 = "description1";
    private static final String lig4 = "nbPieces1";
    private static final String lig5 = "prix1";
    private static final String lig6 = "codePostal1";
    private static final String lig7 = "emailVendeur1";
    private static final String lig8 = "nomVendeur1";
    private static final String lig9 = "telVendeur1";
    private static final String lig10 = "date1";
    public final static String AT_IMAGE_URL="url";
    public final static String PROP_ID="idprop";


    private static final String col1img = "id";
    private static final String col2img = "photo";

    private static final String col1Coomentaire = "idComm";
    private static final String col2Commentaire = "Comm";

    public DataBaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String proprieteFavoris = "CREATE TABLE " + table_favoris + " ( "
                + col1 + " TEXT NOT NULL PRIMARY KEY  ,"
                + col2 + " TEXT NOT NULL ,"
                + col3 + " TEXT NOT NULL ,"
                + col4 + " TEXT NOT NULL,"
                + col5 + " INTEGER NOT NULL ,"
                + col6 + " INTEGER NOT NULL ,"
                + col7 + " TEXT NOT NULL ,"
                + col8 + " TEXT NOT NULL ,"
                + col9 + " TEXT NOT NULL ,"
                + col10 + " TEXT NOT NULL ,"
                + col11 + " TEXT NOT NULL ,"
                + col12 + " TEXT  ,"
                + col13 + " TEXT NOT NULL " +

                ")";
        String Ajout ="CREATE TABLE " + table_ajout + " ("+ID1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
               lig1 + " TEXT NOT NULL ,"
                +lig2 + " TEXT NOT NULL ,"
                + lig3 + " TEXT NOT NULL ,"
                + lig4 + " INTEGER NOT NULL ,"
                + lig5 + " INTEGER NOT NULL ,"
                + lig6 + " TEXT NOT NULL ,"
                + lig7 + " TEXT NOT NULL ,"
                + lig8 + " TEXT NOT NULL ,"
                + lig9 + " TEXT NOT NULL ,"
                + lig10 + " TEXT NOT NULL "
                +

                ")";
        String image = "CREATE TABLE " +table_image + "(" +
                 col1 + " TEXT NOT NULL, " +
                AT_IMAGE_URL  + " TEXT, " +
                " FOREIGN KEY (" + col1 + ")" +
                " REFERENCES " + table_favoris + "(" + col1 + "))";

        String image2="CREATE TABLE "+table_image2+" ("+col1img+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+col2img+" TEXT,FOREIGN KEY("+col1img+") REFERENCES "+table_ajout+ " ("+ID1+"))";

        String commentaire = "CREATE TABLE "+ table_commentaire + " ( "+ col1Coomentaire + " INTEGER PRIMARY KEY AUTOINCREMENT , " +col2Commentaire +" TEXT NOT NULL " +" ) ";
        db.execSQL(image2);
        db.execSQL(image);
       db.execSQL(proprieteFavoris);
        db.execSQL(Ajout);
        db.execSQL(commentaire);

        }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS "+ table_favoris);
        db.execSQL("DROP TABLE IF EXISTS "+ table_ajout);
        db.execSQL("DROP TABLE IF EXISTS "+ table_image);
        db.execSQL("DROP TABLE IF EXISTS "+ table_image2);
        db.execSQL("DROP TABLE IF EXISTS "+ table_commentaire);
        this.onCreate(db);
        Log.i("DATABASE","onUpgrade invoked");

    }

    public boolean insertion (Propriete propriete){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ContentValues valuesimage =  new ContentValues();

        contentValues.put(col1, propriete.getIdPropriete());
        contentValues.put(col2,propriete.getTitre());
       contentValues.put(col3,propriete.getVille());
        contentValues.put(col4,propriete.getDescription());
        contentValues.put(col5,propriete.getNb_piece_disponible());
        contentValues.put(col6,propriete.getPrix());
       contentValues.put(col7,propriete.getCode_postale());
        contentValues.put(col8,propriete.getV().getEmail());
        contentValues.put(col9,propriete.getV().getNom());
        contentValues.put(col10,propriete.getV().getTelephone());
        contentValues.put(col12,propriete.getV().getPrenom());
        contentValues.put(col13,propriete.getV().getIdVendeur());
        contentValues.put(col11,propriete.getDate());
        Long i , m = null;

        for (int j =0 ; j<propriete.getImg().size();j++){
            valuesimage.put(col1,propriete.getIdPropriete());
            valuesimage.put(AT_IMAGE_URL,propriete.getImg().get(j));
            m = db.insert(table_image ,null,valuesimage) ;



        }

        i = db.insert(table_favoris,null,contentValues);
        if(i!=-1){
            Log.i("INSERT_VENDEUR","OKAY");

        }else{
            Log.i("INSERT_VENDEUR","NON_OKAY");
        }


        if( m !=-1){
            Log.i("INSERT_IMAGE","OKAY");
        }else{
            Log.i("INSERT_IMAGE","NON_OKAY");
        }


        return  i>0 && m>0 ;

    }

    public boolean insertion2 (String titre, String ville, String description ,int nb ,int prix ,String code_postale,String emailVendeur , String nomVendeur , String telVendeur ,String date){

        SQLiteDatabase db1 = getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
         contentValues1.put(lig1,titre);
        contentValues1.put(lig2,ville);
        contentValues1.put(lig3,description);
        contentValues1.put(lig4,nb);
        contentValues1.put(lig5,prix);
        contentValues1.put(lig6,code_postale);
        contentValues1.put(lig7,emailVendeur);
        contentValues1.put(lig8,nomVendeur);
        contentValues1.put(lig9,telVendeur);
        contentValues1.put(lig10,date);
        long i;
       i = db1.insert(table_ajout,null,contentValues1);

        if(i == -1){
            return false;
        }else
            return true;
    }
    public boolean insertionCommentaire (String commentaire){
        SQLiteDatabase dbcomm = getWritableDatabase();
        ContentValues contentValuesCommen = new ContentValues();
        contentValuesCommen.put(col2Commentaire,commentaire);
        long i ;

        i = dbcomm.insert(table_commentaire,null,contentValuesCommen);

        if(i == -1){
            return false;
        }else
            return true;
    }
    public boolean insertion3(String img){
        SQLiteDatabase db1 = getWritableDatabase();
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(col2img,img);
        long i;
        i = db1.insert(table_image2,null,contentValues2);

        if(i == -1){
            return false;
        }else
            return true;
    }
//
    public ArrayList<Propriete> Afficher() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from  " + table_favoris, null);
        while (cursor.moveToNext()) {
            String idProp = cursor.getString(cursor.getColumnIndex(DataBaseManager.col1));
            String titre = cursor.getString(cursor.getColumnIndex(DataBaseManager.col2));
            String ville = cursor.getString(cursor.getColumnIndex(DataBaseManager.col3));
            String description = cursor.getString(cursor.getColumnIndex(DataBaseManager.col4));
            int nbPiece= Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataBaseManager.col5)));
            int prix =  Integer.parseInt( cursor.getString(cursor.getColumnIndex(DataBaseManager.col6)));
            String codePostal = cursor.getString(cursor.getColumnIndex(DataBaseManager.col7));
            String nomVendeur = cursor.getString(cursor.getColumnIndex(DataBaseManager.col8));
            String emailVendeur = cursor.getString(cursor.getColumnIndex(DataBaseManager.col9));
            String telVendeur= cursor.getString(cursor.getColumnIndex(DataBaseManager.col10));
           String date= cursor.getString(cursor.getColumnIndex(DataBaseManager.col11));
            Vendeur v = new Vendeur (nomVendeur,nomVendeur,nomVendeur,emailVendeur,telVendeur);
            ArrayList<String> a = selectImgPropriete(idProp);
            mListePropriete.add(new Propriete(idProp,titre,description,ville,nbPiece,prix,codePostal,date,a,v));


        }

            return mListePropriete ;


    }

    public ArrayList<Propriete> AfficherAnnonce(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+table_ajout,null);
        while(cursor.moveToNext()){
            String idProp=cursor.getString(cursor.getColumnIndex(DataBaseManager.ID1));
            String titre= cursor.getString(cursor.getColumnIndex(DataBaseManager.lig1));
            String ville = cursor.getString(cursor.getColumnIndex(DataBaseManager.lig2));
            String description = cursor.getString(cursor.getColumnIndex(DataBaseManager.lig3));
            int nbPiece= Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataBaseManager.lig4)));
            int prix =  Integer.parseInt( cursor.getString(cursor.getColumnIndex(DataBaseManager.lig5)));
            String codePostal = cursor.getString(cursor.getColumnIndex(DataBaseManager.lig6));
            String nomVendeur = cursor.getString(cursor.getColumnIndex(DataBaseManager.lig8));
            String emailVendeur = cursor.getString(cursor.getColumnIndex(DataBaseManager.lig7));
            String telVendeur= cursor.getString(cursor.getColumnIndex(DataBaseManager.lig9));
            String date= cursor.getString(cursor.getColumnIndex(DataBaseManager.lig10));
            Vendeur v = new Vendeur (nomVendeur,nomVendeur,nomVendeur,emailVendeur,telVendeur);
            ArrayList<String> a = selectImgPropriete2(idProp);
            mListeAnnonce.add(new Propriete(idProp,titre,description,ville,nbPiece,prix,codePostal,date,a,v));
        }
        return mListeAnnonce;
    }

    public ArrayList<String> afficher2(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from "+table_image2,null);
        while (cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndex(DataBaseManager.col1img));
            String photo=cursor.getString(cursor.getColumnIndex(DataBaseManager.col2img));
            mListeImages.add(photo);

        }

        return mListeImages;
    }



    private ArrayList<String> selectImgPropriete(String idp){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> image=null;
        String request = "SELECT * FROM  " + DataBaseManager.table_image +" c " + "," + DataBaseManager.table_favoris +" f " +" WHERE c."+ col1+" = f."+ col1 + " and c."+col1 +" = '"+ idp  +"'";
        System.out.print(request);
        Cursor cursor = db.rawQuery(request,null);

        if(cursor!=null) {

            image = new ArrayList<>();

            while (cursor.moveToNext()) {// PARCOURT DU CURSOR  RETOURNNE PAR LA REQUÊTE


                image.add(cursor.getString(cursor.getColumnIndex(DataBaseManager.AT_IMAGE_URL)));
            }
        }
        return image;
    }

    private ArrayList<String> selectImgPropriete2(String idp){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> image=null;
        String request = "SELECT * FROM  " + DataBaseManager.table_image2 +" c " + "," + DataBaseManager.table_ajout +" f " +" WHERE c."+ col1img+" = f."+ ID1 + " and c."+col1img +" = '"+ idp  +"'";
        Log.i("JML", request);
        Cursor cursor = db.rawQuery(request,null);

        if(cursor!=null) {

            image = new ArrayList<>();

            while (cursor.moveToNext()) {// PARCOURT DU CURSOR  RETOURNNE PAR LA REQUÊTE


                image.add(cursor.getString(cursor.getColumnIndex(DataBaseManager.col2img)));
            }
        }



        return image;
    }






    public boolean verificationExistence (String idprop){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from  " + table_favoris, null);
        while(cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex(DataBaseManager.col1)).equals(idprop)){
                return true ;
            }

        }
        return false ;

    }
    public  float delete(String id){
        SQLiteDatabase db=this.getWritableDatabase();

        return db.delete(DataBaseManager.table_favoris,DataBaseManager.col1+" = ?",new String[]{id});
    }
    public  float delete1(String id){
        SQLiteDatabase db1=this.getWritableDatabase();

        return db1.delete(DataBaseManager.table_ajout,DataBaseManager.ID1+" = ?",new String[]{id});
    }
}
