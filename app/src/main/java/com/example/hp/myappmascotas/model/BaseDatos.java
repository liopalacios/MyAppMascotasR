package com.example.hp.myappmascotas.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hp.myappmascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by HP on 26/03/2018.
 */

public class BaseDatos extends SQLiteOpenHelper {
    private Context context;
    public BaseDatos(Context context) {
        super(context, ConstanteBD.DATABASE_NAME, null, ConstanteBD.DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTableMascota = "CREATE TABLE "+ConstanteBD.TABLE_MASCOTA+" ("+
                ConstanteBD.TABLE_MASCOTA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ConstanteBD.TABLE_MASCOTA_NOMBRE+" TEXT,"+
                ConstanteBD.TABLE_MASCOTA_FOTO+" INTEGER,"+
                ConstanteBD.TABLE_MASCOTA_LIKES+" INTEGER)";
        String queryCreateTableLikes = "CREATE TABLE "+ConstanteBD.TABLE_LIKE+" ("+
                ConstanteBD.TABLE_LIKE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ConstanteBD.TABLE_LIKE_MASCOTA_ID+" INTEGER,"+
                ConstanteBD.TABLE_LIKE_CANT+" INTEGER, FOREIGN KEY("+ConstanteBD.TABLE_LIKE_MASCOTA_ID+")"+
                " REFERENCES "+ConstanteBD.TABLE_MASCOTA+" ( "+ConstanteBD.TABLE_MASCOTA_ID+"))";
        db.execSQL(queryCreateTableMascota);
        db.execSQL(queryCreateTableLikes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String drop = "DROP TABLE IF EXIST";
        db.execSQL(""+drop+" "+ConstanteBD.TABLE_MASCOTA);
        db.execSQL(""+drop+" "+ConstanteBD.TABLE_LIKE);
        onCreate(db);
    }
    public ArrayList<Mascota> obtenerMascotas(){
        ArrayList<Mascota>mascotas=new ArrayList<>();
        String query = "SELECT * FROM "+ConstanteBD.TABLE_MASCOTA;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor registros=db.rawQuery(query,null);
        while (registros.moveToNext()){
            Mascota mascota=new Mascota();
           // mascota.setId(registros.getInt(0));
            mascota.setNombre(registros.getString(1));
           // mascota.setFoto(registros.getInt(2));
            mascotas.add(mascota);
        }
        db.close();
        return mascotas;
    }
    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstanteBD.TABLE_MASCOTA,null,contentValues);
        db.close();
    }
    public void insertarLike(ContentValues contentValues){

        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(ConstanteBD.TABLE_LIKE,null,contentValues);
        db.close();
    }
    public int obtenerLike(Mascota mascota){
        int like=0;
        String query="SELECT COUNT("+ConstanteBD.TABLE_LIKE_CANT+") FROM "+
                ConstanteBD.TABLE_LIKE+" WHERE "+ConstanteBD.TABLE_LIKE_MASCOTA_ID+"="+mascota.getId();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor registros=db.rawQuery(query,null);
        if(registros.moveToNext()){
            like=registros.getInt(0);
        }
        db.close();
        return like;
    }
}
