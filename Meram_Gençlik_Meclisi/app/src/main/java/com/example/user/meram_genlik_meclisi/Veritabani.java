package com.example.user.meram_genlik_meclisi;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Veritabani {

    DatabaseHelper veritabanim;

    //veri ekler
    public  void ogrenciEkle(String tableName,Context context,ContentValues cv)
    {
        veritabanim=new DatabaseHelper(context);
        veritabanim.createDataBase();
        SQLiteDatabase myDb=veritabanim.getWritableDatabase();

        myDb.insert(tableName,null,cv);
        myDb.close();

    }

  /*  //veri güncelle
    public  void veriGuncelle(String tableName,Context context,ContentValues values,int guncellenecekId)
    {
        veritabanim=new DatabaseHelper(context);
        veritabanim.createDataBase();
        SQLiteDatabase myDb=veritabanim.getWritableDatabase();
        myDb.update(tableName, values, "id=" + guncellenecekId, null);
        myDb.close();

    }

    //Tekbir veri getir
    public String tekveriGetir(String getirilecekId,Context context,String kolon,String tablo) {
        veritabanim=new DatabaseHelper(context);
        veritabanim.createDataBase();
        SQLiteDatabase SQLiteDatabaseInstance_=veritabanim.getWritableDatabase();
        Cursor cursor = null;
        String empName = "";
        try{

            cursor = SQLiteDatabaseInstance_.rawQuery("SELECT "+kolon+" FROM "+tablo+" WHERE id="+getirilecekId,null);

            if(cursor.getCount() > 0) {

                cursor.moveToFirst();
                empName = cursor.getString(cursor.getColumnIndex(kolon));
            }

            return empName;
        }finally {

            cursor.close();
        }
    }*/
}
