package Models_ılerledikce_verileri_tutuyoruz;


//bu verileri veritabanından dolduracaksın
public class IlanlarimPojoSinifi {
    private String resim;
    private String aciklama;
    private String uye_id;
    private String ilan_id;
    private String fiyat;
    private String baslik;

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

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

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }
}
