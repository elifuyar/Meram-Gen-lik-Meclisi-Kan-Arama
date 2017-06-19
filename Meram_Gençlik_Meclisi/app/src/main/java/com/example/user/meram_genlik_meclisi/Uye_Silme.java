package com.example.user.meram_genlik_meclisi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Uye_Silme extends Activity
{

    DatabaseHelper dbhelper;
    EditText arama ;
    Button btnarama ;
    EditText clear_focus;
    String aranan_kan;
    arananAdapter adaptorumuz;
    FrameLayout search_edit_text_cancel;
    ListView listView_arama;
    final List<Bulunan_List> aranan = new ArrayList<Bulunan_List>();
    final List<String> nolar = new ArrayList<>();
    Bundle bnd;
    //Spinner spinnerKan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye__silme);

        initSearchBox();

        dbhelper = new DatabaseHelper(this);
        arama= (EditText)findViewById(R.id.search_edit_text);
        btnarama = (Button)findViewById(R.id.buton_Arama);
        listView_arama = (ListView)findViewById(R.id.listView_arama);
        //spinnerKan = (Spinner)findViewById(R.id.spinner_kan);

        final SQLiteDatabase db = dbhelper.getReadableDatabase();

        arama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        btnarama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aranan_kan = arama.getText().toString();
                Cursor c = db.rawQuery("SELECT OGRENCI_AD, OGRENCI_SOYAD, OGRENCI_ID FROM bilgiler WHERE OGRENCI_AD like '" + aranan_kan + "'", null);

                while (c.moveToNext()) {
                    String adim = c.getString(c.getColumnIndex("OGRENCI_AD"));
                    String soyadim = c.getString(c.getColumnIndex("OGRENCI_SOYAD"));
                    int no = c.getColumnIndex("OGRENCI_ID");
                    nolar.add(c.getString(no));
                    aranan.add(new Bulunan_List(adim, soyadim));
                }

                adaptorumuz = new arananAdapter(Uye_Silme.this, aranan);
                listView_arama.setAdapter(adaptorumuz);

                listView_arama.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long itemId) {

                        Intent intent = new Intent(Uye_Silme.this, Uye_Detay.class);
                        intent.putExtra("OGRENCI_ID", nolar.get(position));
                        startActivity(intent);
                    }
                });
            }
        });

    }

    private void initSearchBox() {
        arama = (EditText)findViewById(R.id.search_edit_text);
        clear_focus = (EditText)findViewById(R.id.clear_focus);

        search_edit_text_cancel = (FrameLayout)findViewById(R.id.search_edit_text_cancel);
        search_edit_text_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_focus.requestFocus();
                keyboardHide();
                arama.setText("");
                aranan.clear();

                if (adaptorumuz!=null)
                {
                    adaptorumuz.notifyDataSetChanged();
                }
            }
        });

        arama.requestFocus();
        arama.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    keyboardHide();
                    return true;
                }
                return false;
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
                Intent intent = new Intent(Uye_Silme.this, Uye_Ekleme.class);
                startActivity(intent);
                break;
            case R.id.ogrenci_listele:
                intent = new Intent(Uye_Silme.this, Uye_Listeleme.class);
                startActivity(intent);
                break;
            case R.id.ogrenci_arama:
                intent = new Intent(Uye_Silme.this, Uye_Arama.class);
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

    private void keyboardHide() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(arama.getWindowToken(), 0);
    }
}
