package com.example.user.meram_genlik_meclisi;

/**
 * Created by User on 16.10.2015.
 */
public class Menu_list {

    private String menu_adi;
    private String image;

    public Menu_list(String menu_adi, String image) {
        super();
        this.menu_adi = menu_adi;
        this.image = image;
    }

    public String getMenu_adi() {
        return menu_adi;
    }

    public void setMenu_adi(String menu_adi) {
        this.menu_adi = menu_adi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
