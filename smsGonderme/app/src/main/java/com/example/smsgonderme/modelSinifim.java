package com.example.smsgonderme;

public class modelSinifim {
    //sadece ismi ve numarayı listeleyeceğim
    //mesaj içerigini dışardan yazarak gireceğiz onun için bir değişken oluşturmaya gereke yok ol istelenecek bişey değil
    private  String isim;
    private String tel;

    public modelSinifim(String isim, String tel) {
        this.isim = isim;
        this.tel = tel;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
