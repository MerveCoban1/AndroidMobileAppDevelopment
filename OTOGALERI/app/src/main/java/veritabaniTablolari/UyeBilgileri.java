package veritabaniTablolari;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class UyeBilgileri extends RealmObject {
    private String kisiEmail;
    private String kisiSifre;

    public String getKisiEmail() {
        return kisiEmail;
    }

    public void setKisiEmail(String kisiEmail) {
        this.kisiEmail = kisiEmail;
    }

    public String getKisiSifre() {
        return kisiSifre;
    }

    public void setKisiSifre(String kisiSifre) {
        this.kisiSifre = kisiSifre;
    }

    @Override
    public String toString() {
        return "UyeBilgileri{" +
                "kisiEmail='" + kisiEmail + '\'' +
                ", kisiSifre='" + kisiSifre + '\'' +
                '}';
    }
}
