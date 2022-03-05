package com.example.egitim1;

public class KullaniciModeli {
    String isim;
    String soyisim;
    String yas;
    String takim;

    public KullaniciModeli(String isim, String soyisim, String yas, String takim) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.yas = yas; //her liste eleanı için bu değerler olacak
        this.takim = takim;
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

    public String getYas() {
        return yas;
    }

    public void setYas(String yas) {
        this.yas = yas;
    }

    public String getTakim() {
        return takim;
    }

    public void setTakim(String takim) {
        this.takim = takim;
    }
}
