package com.example.user.meram_genlik_meclisi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;


public class DatabaseHelper  extends SQLiteOpenHelper
{
    static String DB_PATH;
    //Veritabanı ismini veriyoruz
    static String DB_NAME = "Uyeler";
    static String TABLE_NAME = "Bilgiler";

  /*  public static final String OGRENCI_ID = "_id";
    public static final String OGRENCI_AD = "ad";
    public static final String OGRENCI_SOYAD ="soyad" ;
    public static final String OGRENCI_DOGUM_YERI = "dogum_yeri";
    public static final String OGRENCI_DOGUM_TARIHI = "dogum_tarihi";
    public static final String OGRENCI_BOLUM ="bolum" ;
    public static final String OGRENCI_OKUL = "okul";
    public static final String OGRENCI_SINIF = "sinif";
    public static final String OGRENCI_TELEFON = "telefon";
    public static final String OGRENCI_MAIL = "mail";
    public static final String OGRENCI_KAN_GRUBU = "kan_grubu";*/

    SQLiteDatabase myDatabase;

    final Context myContext;

    public DatabaseHelper(Context context)
    {
        super(context, DB_NAME, null, 1);

        DB_PATH = context.getFilesDir().getParent() + "/databases/";

        this.myContext = context;
    }
    //Assest dizinine koyduğumuz sql dosyasını, Android projesi içine oluşturma ve kopyalamna işlemi yapıldı..
    public void createDataBase()
    {
        boolean dbExists = checkDataBase();

        if (!dbExists)
        {
            this.getReadableDatabase();


            try
            {
                copyDataBase();
            }
            catch (Exception ex)
            {
                Log.w("hata","Veritabanı kopyalanamıyor");
                throw new Error("Veritabanı kopyalanamıyor.");
            }
        }
    }
    //Sqlite veritabanı dosyasını açıp, veritabanımı kontrol ediyoruz
    boolean checkDataBase()
    {
        SQLiteDatabase checkDB = null;

        try
        {
            String myPath = DB_PATH + DB_NAME;

            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }
        catch (Exception ex)
        {
            Log.w("hata","Veritabanı açılamadı");
        }

        if (checkDB != null)
            checkDB.close();

        return checkDB != null ? true : false;
    }
    ///Assest dizinine koyduğumuz sql dosyasındaki verileri kopyalıyoruz
    void copyDataBase()
    {
        try
        {
            InputStream myInput = myContext.getAssets().open(DB_NAME);

            String outFileName = DB_PATH + DB_NAME;

            OutputStream myOutput = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = myInput.read(buffer)) > 0)
            {
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();

            myInput.close();
            myOutput.close();
        }
        catch (Exception ex)
        {
            Log.w("hata", "Kopya oluşturma hatası.");
        }
    }
    //Veritabannı açma işlemi yapıldı

    void openDataBase()
    {
        String myPath = DB_PATH + DB_NAME;

        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close()
    {
        if (myDatabase != null && myDatabase.isOpen())
            myDatabase.close();

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public HashMap<String, String> ogrenciDetay(String no)
    {
        HashMap<String,String> ogrenci = new HashMap<String,String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME+ " WHERE OGRENCI_ID='"+no+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            ogrenci.put("OGRENCI_AD", cursor.getString(1));
            ogrenci.put("OGRENCI_SOYAD", cursor.getString(2));
            ogrenci.put("OGRENCI_DOGUM_YERI", cursor.getString(3));
            ogrenci.put("OGRENCI_DOGUM_TARIHI", cursor.getString(4));
            ogrenci.put("OGRENCI_BOLUM", cursor.getString(5));
            ogrenci.put("OGRENCI_OKUL", cursor.getString(6));
            ogrenci.put("OGRENCI_SINIF", cursor.getString(7));
            ogrenci.put("OGRENCI_TELEFON", cursor.getString(8));
            ogrenci.put("OGRENCI_MAIL", cursor.getString(9));
            ogrenci.put("OGRENCI_KAN_GRUBU", cursor.getString(10));
        }
        cursor.close();
        db.close();
        return ogrenci;
    }


    public void ogrenciSil(String no){ //id si belli olan row u silmek i�in

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "OGRENCI_ID" + " = ?",
                new String[]{no});
        db.close();
    }
}
