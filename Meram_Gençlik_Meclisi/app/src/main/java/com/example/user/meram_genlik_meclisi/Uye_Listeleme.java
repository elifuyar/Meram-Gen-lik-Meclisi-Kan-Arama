package com.example.user.meram_genlik_meclisi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Uye_Listeleme extends Activity {

    ListView lv;
    Bundle bnd;
    List<String > nolar = new ArrayList<>();

    ArrayAdapter adaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye__listeleme);

        DatabaseHelper dbHelper=new DatabaseHelper(this);
        try
        {
            dbHelper.createDataBase();

        }
        catch (Exception ex)
        {
            Log.w("hata", "Veritabanı oluşturulamadı ve kopyalanamadı!");
        }
    }

    public ArrayAdapter<String> Kayitlar_Liste(Context context)
    {
        DatabaseHelper dbHelper=new DatabaseHelper(this);
        try
        {
            dbHelper.createDataBase();

        }
        catch (Exception ex)
        {
            Log.w("hata", "Veritabanı oluşturulamadı ve kopyalanamadı!");
        }


        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String[] getColumnName={"OGRENCI_AD, OGRENCI_SOYAD, OGRENCI_ID"};
        Cursor imlec=db.query("Bilgiler", getColumnName, null, null, null, null, null);
        final ArrayList<String> student=new ArrayList<String>();

        while(imlec.moveToNext()){
            String student_name=imlec.getString(imlec.getColumnIndex("OGRENCI_AD"));
            String student_surname=imlec.getString(imlec.getColumnIndex("OGRENCI_SOYAD"));

            int student_no=imlec.getColumnIndex("OGRENCI_ID");
            String name_surname= student_name+ " "+student_surname;
            student.add(name_surname);
            nolar.add(imlec.getString(student_no));

        }

        ArrayAdapter AA= new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,student);
        return AA;
    }



    public void onResume() {
        super.onResume();

        bnd = new Bundle();
        lv = (ListView)findViewById(R.id.list_View);

        adaptor= Kayitlar_Liste(getApplicationContext());
        lv.setAdapter(adaptor);
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long itemId) {

                Intent intent = new Intent(Uye_Listeleme.this, Uye_Detay.class);
                bnd.putString("OGRENCI_ID", nolar.get(position));
                intent.putExtras(bnd);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.ogrenci_ekle:
                Intent intent = new Intent(Uye_Listeleme.this, Uye_Ekleme.class);
                startActivity(intent);
                break;
            case R.id.ogrenci_listele:
                intent = new Intent(Uye_Listeleme.this, Uye_Listeleme.class);
                startActivity(intent);
                break;
            case R.id.ogrenci_arama:
                intent = new Intent(Uye_Listeleme.this, Uye_Arama.class);
                startActivity(intent);
                break;
            case R.id.cikis:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                System.exit(0);
                return true;
        }
        return false;
    }
}