package veritabaniTablolari;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class ResimlerVeritabaniTablosu extends RealmObject {
    private String uye_id;
    private String ilan_id;
    private String resim_yolu;

    public String getUye_id() {
        return uye_id;
    }

    public void setUye_id(String uye_id) {
        this.uye_id = uye_id;
    }

    public String getIlan_id() {
        return ilan_id;
    }

    public void setIlan_id(String ilan_id) {
        this.ilan_id = ilan_id;
    }

    public String getResim_yolu() {
        return resim_yolu;
    }

    public void setResim_yolu(String resim_yolu) {
        this.resim_yolu = resim_yolu;
    }
}
