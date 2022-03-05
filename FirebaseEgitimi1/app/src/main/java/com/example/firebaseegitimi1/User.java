package com.example.firebaseegitimi1;

public class User {
    private boolean durum;
    private String id;
    private String isim;
    private String soyisim;
    private int yas;

    public User(boolean durum, String soyisim, int yas, String id, String isim) {
        this.durum = durum;
        this.id = id;
        this.isim = isim;
        this.soyisim = soyisim;
        this.yas = yas;
    }
    //constructor içindeki parametreler log çıktımızla yani ana keyimizin cocuk keylerinin sırasıyla aynı olmalı.
    //önce log kısmında da bir bastır ona göre const parametresini düzenle yoksa hata veriyor.

    public User(){

    }//boş bir constructor da mutlaka olmalıymış yoksa hata veriyor.

    public boolean isDurum() {
        return durum;
    }

    public void setDurum(boolean durum) {
        this.durum = durum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }
}
