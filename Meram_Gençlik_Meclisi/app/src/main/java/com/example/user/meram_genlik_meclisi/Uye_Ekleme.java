package com.example.user.meram_genlik_meclisi;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Uye_Ekleme extends Activity {

    EditText ad, soyad, dogum_yer, dogum_tarih, bolum, okul, sinif, telefon, mail, kan_grup;
    Button kaydet;
    DatabaseHelper dbhelper;
    Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye__ekleme);

        ad = (EditText) findViewById(R.id.editText_ogrenci_adi);
        soyad = (EditText) findViewById(R.id.editText_ogrenci_soyad);
        dogum_yer = (EditText) findViewById(R.id.editText_ogrenci_dogum_yeri);
        dogum_tarih = (EditText) findViewById(R.id.editText_ogrenci_dogum_tarihi);
        bolum = (EditText) findViewById(R.id.editText_ogrenci_bolum);
        okul = (EditText) findViewById(R.id.editText_ogrenci_okulu);
        sinif = (EditText) findViewById(R.id.editText_ogrenci_sinif);
        telefon = (EditText) findViewById(R.id.editText_ogrenci_telefon);
        mail = (EditText) findViewById(R.id.editText_ogrenci_mail);
        kan_grup = (EditText) findViewById(R.id.editText_ogrenci_kan_grubu);
        kaydet = (Button) findViewById(R.id.button_kaydet);

        dbhelper= new DatabaseHelper(this);
        vt = new Veritabani();

        kaydet.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String adi, soyadi, dogum_yeri, dogum_tarihi, bolumu, okulu, sinifi, telefonu, maili, kan_grubu;
                adi = ad.getText().toString();
                soyadi = soyad.getText().toString();
                dogum_tarihi = dogum_tarih.getText().toString();
                dogum_yeri = dogum_yer.getText().toString();
                bolumu = bolum.getText().toString();
                okulu = okul.getText().toString();
                sinifi = sinif.getText().toString();
                telefonu = telefon.getText().toString();
                maili = mail.getText().toString();
                kan_grubu = kan_grup.getText().toString();




                if (adi.matches("") || soyadi.matches("") || bolumu.matches("") || okulu.matches("")) {
                    Toast.makeText(getApplicationContext(), "Tum Bilgileri Eksiksiz Doldurunuz", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ContentValues cv= new ContentValues();
                    cv.put("OGRENCI_AD", adi);
                    cv.put("OGRENCI_SOYAD", soyadi);
                    cv.put("OGRENCI_DOGUM_YERI", dogum_yeri);
                    cv.put("OGRENCI_DOGUM_TARIHI", dogum_tarihi);
                    cv.put("OGRENCI_BOLUM", bolumu);
                    cv.put("OGRENCI_OKUL", okulu);
                    cv.put("OGRENCI_SINIF", sinifi);
                    cv.put("OGRENCI_TELEFON", telefonu);
                    cv.put("OGRENCI_MAIL", maili);
                    cv.put("OGRENCI_KAN_GRUBU", kan_grubu);

                    vt.ogrenciEkle("bilgiler", Uye_Ekleme.this, cv);
                    Utils.print(Uye_Ekleme.this, "eklendi");


                    dbhelper.close();

                    ad.setText("");
                    soyad.setText("");
                    dogum_yer.setText("");
                    dogum_tarih.setText("");
                    bolum.setText("");
                    okul.setText("");
                    sinif.setText("");
                    telefon.setText("");
                    mail.setText("");
                    kan_grup.setText("");


                }
            }
        });


        Spinner sampleSpinner = (Spinner) findViewById(R.id.spinner_kan);

        String[] array=getResources().getStringArray(R.array.kanlar);

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array);

        adapter.setDropDownViewResource(R.layout.spinner_layout);

        sampleSpinner.setAdapter(adapter);

        sampleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                // TODO Auto-generated method stub
                String selectedItem = parent.getItemAtPosition(pos).toString();
                kan_grup.setText(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        hideKeyboard();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.ogrenci_ekle:
                Intent intent = new Intent(Uye_Ekleme.this, Uye_Ekleme.class);
                startActivity(intent);
                break;
            case R.id.ogrenci_listele:
                intent = new Intent(Uye_Ekleme.this, Uye_Listeleme.class);
                startActivity(intent);
                break;
            case R.id.ogrenci_arama:
                intent = new Intent(Uye_Ekleme.this, Uye_Arama.class);
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

    private void hideKeyboard()
    {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromInputMethod(kan_grup.getWindowToken(), 0);
    }
}