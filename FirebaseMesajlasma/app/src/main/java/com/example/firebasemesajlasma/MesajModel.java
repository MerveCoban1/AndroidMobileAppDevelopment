package com.example.firebasemesajlasma;

public class MesajModel {
    private String from;
    private String mesaj;

    public MesajModel(String from, String mesaj) {
        this.from = from;
        this.mesaj = mesaj;
    }
    public MesajModel(){

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    @Override
    public String toString() {
        return "MesajModel{" +
                "from='" + from + '\'' +
                ", mesaj='" + mesaj + '\'' +
                '}';
    }
}
