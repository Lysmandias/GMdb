package com.example.gmdb.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gmdb.SQL.LocalSQLiteOnpenHelper;

import java.util.ArrayList;

public class Film {
    private long id;
    private String titre, resume;
    private int annee;
    private String[] acteurs;

    private Film(Cursor cursor) {
        id = cursor.getLong(cursor.getColumnIndex("id"));
        titre = cursor.getString(cursor.getColumnIndex("titre"));
        annee = cursor.getInt(cursor.getColumnIndex("annee"));
        acteurs = cursor.getString(cursor.getColumnIndex("acteurs")).split(";");
        resume = cursor.getColumnName(cursor.getColumnIndex("resume"));
    }


    /*public Cursor query (boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit){}
    public Cursor rawQuery (String sql, String[] selectionArgs){}

*/
    public static ArrayList<Film> getFilmList(Context context){
        //creation de liste de film
        ArrayList<Film> listfilm = new ArrayList<Film>();
        //creation de notre helper
        LocalSQLiteOnpenHelper helper = new LocalSQLiteOnpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(true, "Film", new String[]{"id", "titre", "annee", "acteurs", "resume"},null, null,null,null,"titre", null );
        while (cursor.moveToNext()){
            listfilm.add(new Film(cursor));
        }
        //fermeture curseur
        cursor.close();
        //fermeture database
        db.close();
        return listfilm;
    }
    public static Film getFilm(Context context, long id){
        Film film = null;
        LocalSQLiteOnpenHelper helper = new LocalSQLiteOnpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String where = "id = "+String.valueOf(id);
        Cursor cursor = db.query(true,"film", new String[]{"id","titre", "annee", "acteurs", "resume"},null, null,null,null,"titre", null );
        if (cursor.moveToFirst()){
            film = new Film(cursor);
        }
        cursor.close();
        db.close();
        return film;
    }
    public void insert(Context context){
        LocalSQLiteOnpenHelper helper = new LocalSQLiteOnpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        this.id = db.insert("Film", null, getContentValues());
        db.close();
    }

    public void update(Context context) {
        String whereClause = "id="+this.id;
        LocalSQLiteOnpenHelper helper = new LocalSQLiteOnpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.update("Film", getContentValues(), whereClause, null);
        db.close();
    }
    private ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put("titre",this.titre);
        values.put("annee",this.annee);
        if (this.acteurs != null){
            String listacteur = new String();
            for (int i = 0; i < this.acteurs.length; i++){
                listacteur += this.acteurs;
                if (i < this.acteurs.length-1)
                    listacteur += ";";
            }
            values.put("acteurs",listacteur);
        }
        values.put("resume",this.resume);
        return values;
    }
    public void delete(Context context){
        String whereClause = "id="+this.id;
        String[] whereArgs = new String[1];
        whereArgs[0] = String.valueOf(this.id);
        LocalSQLiteOnpenHelper helper = new LocalSQLiteOnpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("Film",whereClause,whereArgs);
        db.close();
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getResume() {
        return resume;
    }
    public void setResume(String resume) {
        this.resume = resume;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String[] getActeurs() {
        return acteurs;
    }
    public void setActeurs(String[] acteurs) {
        this.acteurs = acteurs;
    }
}
