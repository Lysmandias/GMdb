package com.example.gmdb.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalSQLiteOnpenHelper extends SQLiteOpenHelper {

    static String DB_NAME = "GMdb.db";
    static int DB_VERSION = 1;
   /* LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(this);
    SQLiteDatabase dbReadable = helper.getReadableDatabase();
    SQLiteDatabase dbWritable = helper.getWritableDatabase();
    public void execSQL (String sql){}
*/
    public LocalSQLiteOnpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //on creer une base de donne avec les clé de chaque colonne
        String sqlFilTable = "CREATE TABLE DVD(id INTEGER PRIMARY KEY," + "titre TEXT, annee NUMERIC, acteurs TEXT, resume TEXT);";
        db.execSQL(sqlFilTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int indexversion = oldVersion; indexversion < newVersion; indexversion++){
            int nextversion = indexversion + 1;
            switch (nextversion) {
                case 2:
                    upgradeToVersion2(db);
                    break;
                case 3 :
                    //mise a jour v3
                    break;
            }
        }
    }
    private void upgradeToVersion2(SQLiteDatabase db){
        String sqlcommand = "ALTER TABLE GMdb ADD COLUMN affiche BLOB";
        db.execSQL(sqlcommand);
    }

}
