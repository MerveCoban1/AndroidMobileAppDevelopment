package com.example.firebaseegitim2;

public class Messages {
    public String date;
    public String icerik;

    public Messages(String date, String icerik) {
        this.date = date;
        this.icerik = icerik;
    }
    public Messages(){

    }

    @Override
    public String toString() {
        return "Messages{" +
                "date='" + date + '\'' +
                ", icerik='" + icerik + '\'' +
                '}';
    }
}
