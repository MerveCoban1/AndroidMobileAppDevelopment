package Models;

public class urunler {
    private String urunadi;
    private String urunresmi;
    private String urunfiyati;
    private String id;

    public String getUrunadi() {
        return urunadi;
    }

    public void setUrunadi(String urunadi) {
        this.urunadi = urunadi;
    }

    public String getUrunresmi() {
        return urunresmi;
    }

    public void setUrunresmi(String urunresmi) {
        this.urunresmi = urunresmi;
    }

    public String getUrunfiyati() {
        return urunfiyati;
    }

    public void setUrunfiyati(String urunfiyati) {
        this.urunfiyati = urunfiyati;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "urunler{" +
                "urunadi='" + urunadi + '\'' +
                ", urunresmi='" + urunresmi + '\'' +
                ", urunfiyati='" + urunfiyati + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
