package com.example.egitim1;

public class mesajModel {
    private String mesajİcerik;
    private String kisi;
    private int resimId;

    public mesajModel(String mesajİcerik, String kisi, int resimId) {
        this.mesajİcerik = mesajİcerik;
        this.kisi = kisi;
        this.resimId = resimId;
    }

    public String getMesajİcerik() {
        return mesajİcerik;
    }

    public void setMesajİcerik(String mesajİcerik) {
        this.mesajİcerik = mesajİcerik;
    }

    public String getKisi() {
        return kisi;
    }

    public void setKisi(String kisi) {
        this.kisi = kisi;
    }

    public int getResimId() {
        return resimId;
    }

    public void setResimId(int resimId) {
        this.resimId = resimId;
    }
}
