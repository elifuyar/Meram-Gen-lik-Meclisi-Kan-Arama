package com.example.user.meram_genlik_meclisi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 16.10.2015.
 */
public class arananAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Bulunan_List> bulunan_listesi;

    public arananAdapter(Activity activity, List<Bulunan_List> bulunan) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        bulunan_listesi = bulunan;
    }


    @Override
    public int getCount() {
        return bulunan_listesi.size();
    }

    @Override
    public Bulunan_List getItem(int position) {
        //şöyle de olabilir: public Object getItem(int position)
        return bulunan_listesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.item_news, null);

        TextView textView = (TextView) satirView.findViewById(R.id.menuisim_textView);
       // ImageView imageView = (ImageView) satirView.findViewById(R.id.imageView_menuresim);

        Bulunan_List bulunanlar = bulunan_listesi.get(position);

        textView.setText(bulunanlar.getIsim()+ "    "+ bulunanlar.getSoyisim());//+"    "+bulunanlar.getTelefon()+ "    "+bulunanlar.getKan_grubu());


        return satirView;
    }
}
