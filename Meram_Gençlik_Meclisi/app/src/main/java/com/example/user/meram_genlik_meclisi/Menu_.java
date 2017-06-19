package com.example.user.meram_genlik_meclisi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Menu_ extends Activity {

    final List<Menu_list> menuler = new ArrayList<Menu_list>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_);

        menuler.add(new Menu_list("ÜYE EKLE", "ekleme.png"));
        menuler.add(new Menu_list("ÜYE LİSTELE", "liste.png"));
        menuler.add(new Menu_list("KAN ARAMA", "ara.png"));
        menuler.add(new Menu_list("ÜYE SİLME", "sil.png"));
        menuler.add(new Menu_list("ÇIKIŞ", "ekleme"));

        final ListView listemiz = (ListView) findViewById(R.id.listView);
        OzelAdapter adaptorumuz=new OzelAdapter(this, menuler);
        listemiz.setAdapter(adaptorumuz);

        listemiz.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                if(position == 0){
                    Intent intent = new Intent(Menu_.this, Uye_Ekleme.class);
                    startActivity(intent);
                }
                if(position == 1){
                    Intent intent = new Intent(Menu_.this, Uye_Listeleme.class);
                    startActivity(intent);
                }
                if(position == 2){
                    Intent intent = new Intent(Menu_.this, Uye_Arama.class);
                    startActivity(intent);
                }
                if(position == 3){
                    Intent intent = new Intent(Menu_.this, Uye_Silme.class);
                    startActivity(intent);
                }
                if(position == 4){
                    Intent intent = new Intent(Menu_.this, Cikis.class);
                    startActivity(intent);
                }
            }
        });
    }
}
