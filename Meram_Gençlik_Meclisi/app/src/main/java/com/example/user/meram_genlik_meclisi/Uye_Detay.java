package com.example.user.meram_genlik_meclisi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class Uye_Detay extends Activity {

    TextView ad, soyad, dogum_yeri, dogum_tarihi,bolum, okul,sinif,telefon, mail, kan_grubu;
    Button sil;
    String no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        Bundle alinan = getIntent().getExtras();

        ad = (TextView)findViewById(R.id.textView_ogrenci_adi);
        soyad = (TextView)findViewById(R.id.textView_ogrenci_soyad);
        dogum_yeri = (TextView)findViewById(R.id.textView_ogrenci_dogum_yeri);
        dogum_tarihi = (TextView)findViewById(R.id.textView_ogrenci_dogum_tarihi);
        bolum = (TextView)findViewById(R.id.textView_ogrenci_bolum);
        okul = (TextView)findViewById(R.id.textView_ogrenci_okulu);
        sinif = (TextView)findViewById(R.id.textView_ogrenci_sinifi);
        telefon = (TextView)findViewById(R.id.textView_ogrenci_telefon);
        mail = (TextView)findViewById(R.id.textView_ogrenci_mail);
        kan_grubu = (TextView)findViewById(R.id.textView_ogrenci_kan_grubu);
        sil = (Button)findViewById(R.id.button_sil);

        no=alinan.getString("OGRENCI_ID");

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        HashMap<String, String> map = db.ogrenciDetay(no);

        ad.setText(map.get("OGRENCI_AD"));
        soyad.setText(map.get("OGRENCI_SOYAD"));
        dogum_yeri.setText(map.get("OGRENCI_DOGUM_YERI"));
        dogum_tarihi.setText(map.get("OGRENCI_DOGUM_TARIHI"));
        bolum.setText(map.get("OGRENCI_BOLUM"));
        okul.setText(map.get("OGRENCI_OKUL"));
        sinif.setText(map.get("OGRENCI_SINIF"));
        telefon.setText(map.get("OGRENCI_TELEFON"));
        mail.setText(map.get("OGRENCI_MAIL"));
        kan_grubu.setText(map.get("OGRENCI_KAN_GRUBU"));


        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final	AlertDialog.Builder builder = new AlertDialog.Builder(Uye_Detay.this);
                builder.setTitle("Lütfen Seçiminizi Yapınız.");
                builder.setMessage("Üyeyi silmek istediğinizden emin misiniz?");
                builder.setCancelable(false);

                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                        db.ogrenciSil(no);
                        ad.setText("");
                        soyad.setText("");
                        dogum_yeri.setText("");
                        dogum_tarihi.setText("");
                        bolum.setText("");
                        okul.setText("");
                        sinif.setText("");
                        telefon.setText("");
                        mail.setText("");
                        kan_grubu.setText("");
                        Utils.print(Uye_Detay.this, "Üye Silindi!");
                        db.close();
                    }
                });
                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Utils.print(Uye_Detay.this, "Üye Silinmedi!");
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();



            }
        });

        telefon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri number = Uri.parse(telefon.getText().toString());

                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                if(callIntent.resolveActivity(getPackageManager()) != null)
                {
                    startActivityForResult(callIntent, 0);
                }


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
                Intent intent = new Intent(Uye_Detay.this, Uye_Ekleme.class);
                startActivity(intent);
                break;
            case R.id.ogrenci_listele:
                intent = new Intent(Uye_Detay.this, Uye_Listeleme.class);
                startActivity(intent);
                break;
            case R.id.ogrenci_arama:
                intent = new Intent(Uye_Detay.this, Uye_Arama.class);
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
