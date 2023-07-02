package com.example.applogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String basededatos="usuariosena.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "usuariosena.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table usuarios(username TEXT primary key, nombrecompleto TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists usuarios");

    }

    public Boolean insertarDatos(String username, String nombrecompleto, String password){
        SQLiteDatabase DbUsuario= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("nombrecompleto",nombrecompleto);
        contentValues.put("password",password);
        long resultado=DbUsuario.insert("usuarios",null,contentValues);

        return resultado != -1;
    }

    public boolean verificarUsername(String username){
        SQLiteDatabase DbUsuario=this.getWritableDatabase();
        Cursor cursor = DbUsuario.rawQuery("Select * From usuarios where username=?",new String[]{username});
        return cursor.getCount() > 0;
    }

    public boolean verificarContrasena(String username, String password){
        SQLiteDatabase DbUsuario=this.getWritableDatabase();
        Cursor cursor= DbUsuario.rawQuery("select * from usuarios where username=? and password=?", new String[]{username, password});
        return  cursor.getCount() > 0;
    }

    public Cursor ConsultarUsuarios(){
        SQLiteDatabase DbUsuario= this.getWritableDatabase();
        String sql="select * from usuarios";
        Cursor cursor = DbUsuario.rawQuery(sql,null);
        return cursor;
    }
}
