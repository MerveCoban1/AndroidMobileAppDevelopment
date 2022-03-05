package veritabaniTablolari;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class IlanlarVeritabaniTablosu extends RealmObject {
    private String ilan_id;
    private String uye_id;
    private String ilanBaslik;
    private String ilanAciklama;
    private String sehirBilgi;
    private String ilceBilgi;
    private String mahalleBilgi;
    private String markaBilgi;
    private String seriBilgi;
    private String modelBilgi;
    private String motorTipiBilgi;
    private String motorHacmiBilgi;
    private String yakitTipiBilgi;
    private String ortalamaYakitBilgi;
    private String kimden;
    private String ilanTipi;
    private String ucret;

    public String getIlan_id() {
        return ilan_id;
    }

    public void setIlan_id(String ilan_id) {
        this.ilan_id = ilan_id;
    }

    public String getUye_id() {
        return uye_id;
    }

    public void setUye_id(String uye_id) {
        this.uye_id = uye_id;
    }

    public String getIlanBaslik() {
        return ilanBaslik;
    }

    public void setIlanBaslik(String ilanBaslik) {
        this.ilanBaslik = ilanBaslik;
    }

    public String getIlanAciklama() {
        return ilanAciklama;
    }

    public void setIlanAciklama(String ilanAciklama) {
        this.ilanAciklama = ilanAciklama;
    }

    public String getSehirBilgi() {
        return sehirBilgi;
    }

    public void setSehirBilgi(String sehirBilgi) {
        this.sehirBilgi = sehirBilgi;
    }

    public String getIlceBilgi() {
        return ilceBilgi;
    }

    public void setIlceBilgi(String ilceBilgi) {
        this.ilceBilgi = ilceBilgi;
    }

    public String getMahalleBilgi() {
        return mahalleBilgi;
    }

    public void setMahalleBilgi(String mahalleBilgi) {
        this.mahalleBilgi = mahalleBilgi;
    }

    public String getMarkaBilgi() {
        return markaBilgi;
    }

    public void setMarkaBilgi(String markaBilgi) {
        this.markaBilgi = markaBilgi;
    }

    public String getSeriBilgi() {
        return seriBilgi;
    }

    public void setSeriBilgi(String seriBilgi) {
        this.seriBilgi = seriBilgi;
    }

    public String getModelBilgi() {
        return modelBilgi;
    }

    public void setModelBilgi(String modelBilgi) {
        this.modelBilgi = modelBilgi;
    }

    public String getMotorTipiBilgi() {
        return motorTipiBilgi;
    }

    public void setMotorTipiBilgi(String motorTipiBilgi) {
        this.motorTipiBilgi = motorTipiBilgi;
    }

    public String getMotorHacmiBilgi() {
        return motorHacmiBilgi;
    }

    public void setMotorHacmiBilgi(String motorHacmiBilgi) {
        this.motorHacmiBilgi = motorHacmiBilgi;
    }

    public String getYakitTipiBilgi() {
        return yakitTipiBilgi;
    }

    public void setYakitTipiBilgi(String yakitTipiBilgi) {
        this.yakitTipiBilgi = yakitTipiBilgi;
    }

    public String getOrtalamaYakitBilgi() {
        return ortalamaYakitBilgi;
    }

    public void setOrtalamaYakitBilgi(String ortalamaYakitBilgi) {
        this.ortalamaYakitBilgi = ortalamaYakitBilgi;
    }

    public String getKimden() {
        return kimden;
    }

    public void setKimden(String kimden) {
        this.kimden = kimden;
    }

    public String getIlanTipi() {
        return ilanTipi;
    }

    public void setIlanTipi(String ilanTipi) {
        this.ilanTipi = ilanTipi;
    }

    public String getUcret() {
        return ucret;
    }

    public void setUcret(String ucret) {
        this.ucret = ucret;
    }
}
