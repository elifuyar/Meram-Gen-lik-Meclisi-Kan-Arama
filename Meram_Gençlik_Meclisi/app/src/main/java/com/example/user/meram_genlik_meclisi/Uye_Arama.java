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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Uye_Arama extends Activity {

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
    Spinner spinnerKan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_arama);

        initSearchBox();

        dbhelper = new DatabaseHelper(this);
        arama= (EditText)findViewById(R.id.search_edit_text);
        btnarama = (Button)findViewById(R.id.buton_Arama);
        listView_arama = (ListView)findViewById(R.id.listView_arama);
        spinnerKan = (Spinner)findViewById(R.id.spinner_kan);

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
                Cursor c = db.rawQuery("SELECT OGRENCI_ID, OGRENCI_AD, OGRENCI_SOYAD FROM bilgiler WHERE OGRENCI_KAN_GRUBU like '" + aranan_kan + "'", null);

                while(c.moveToNext()){
                    String adim = c.getString(c.getColumnIndex("OGRENCI_AD"));
                    String soyadim = c.getString(c.getColumnIndex("OGRENCI_SOYAD"));
                    int no = c.getColumnIndex("OGRENCI_ID");
                    nolar.add(c.getString(no));
                    aranan.add(new Bulunan_List(adim, soyadim));
                }

                adaptorumuz = new arananAdapter(Uye_Arama.this, aranan);
                listView_arama.setAdapter(adaptorumuz);

                listView_arama.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long itemId) {

                        Intent intent = new Intent(Uye_Arama.this, Uye_Detay.class);
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
                nolar.clear();

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
                arama.setText(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

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
                Intent intent = new Intent(Uye_Arama.this, Uye_Ekleme.class);
                startActivity(intent);
                break;
            case R.id.ogrenci_listele:
                intent = new Intent(Uye_Arama.this, Uye_Listeleme.class);
                startActivity(intent);
                break;
            case R.id.ogrenci_arama:
                intent = new Intent(Uye_Arama.this, Uye_Arama.class);
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
