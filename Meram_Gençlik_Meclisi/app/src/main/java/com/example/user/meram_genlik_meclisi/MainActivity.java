package com.example.user.meram_genlik_meclisi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends Activity {

    final String kullanici_adi = "mabdemet";
    final String sifre = "123";
    EditText kullaniciadi, sifree;
    Button giris;
    TextView tarih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sistemin güncel saatini aldık
        String yy, mm, dd, hh, min, ss;
       // tarih = (TextView)findViewById(R.id.textView_tarih);

        final Calendar c = Calendar.getInstance();
        yy = String.valueOf(c.get(Calendar.YEAR));
        mm = String.valueOf(c.get(Calendar.MONTH));
        dd = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        hh = String.valueOf(c.get(Calendar.HOUR));
        min = String.valueOf(c.get(Calendar.MINUTE));
        ss = String.valueOf(c.get(Calendar.SECOND));
       // tarih.setText(new StringBuilder().append(dd).append(" ").append("- ").append(mm).append(" - ").append(yy).append("  ").append(hh).append(":").append(min).append(":").append(ss));


        kullaniciadi = (EditText)findViewById(R.id.editText_kullanici);
        sifree = (EditText)findViewById(R.id.editText_sifre);
        giris = (Button)findViewById(R.id.button_giris);


        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kullaniciadi_ = kullaniciadi.getText().toString();
                String sifre_ = sifree.getText().toString();

                if (kullaniciadi_.equals(kullanici_adi) && sifre_.equals(sifre))
                {
                    Utils.print(MainActivity.this, "Giriş Yapıldı!");
                    Intent intent = new Intent(MainActivity.this, Menu_.class);
                    startActivity(intent);
                } else {
                    Utils.print(MainActivity.this, "Kullanıcı adı veya şifre yanlış!");
                }
            }
        });
    }
}
