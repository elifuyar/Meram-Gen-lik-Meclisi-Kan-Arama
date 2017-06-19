package com.example.user.meram_genlik_meclisi;

/**
 * Created by User on 7.1.2016.
 */
public class Bulunan_List {

    private String isim;
    private String soyisim;
   // private String telefon;
    //private String kan_grubu;


    public Bulunan_List(String isim, String soyisim){//, String kan_grubu, String telefon) {
        this.isim = isim;
        this.soyisim = soyisim;
       // this.kan_grubu = kan_grubu;
       // this.telefon = telefon;
    }


    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }

  /*  public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getKan_grubu() {
        return kan_grubu;
    }

    public void setKan_grubu(String kan_grubu) {
        this.kan_grubu = kan_grubu;
    }*/


}
