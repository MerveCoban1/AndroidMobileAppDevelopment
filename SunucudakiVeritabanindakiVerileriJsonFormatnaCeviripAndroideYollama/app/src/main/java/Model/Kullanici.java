package Model;

public class Kullanici {
    private String id;
    private String isim;
    private String soyad;
    //çekeceğim veriler için oluşturdum bu sınıfı.

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    @Override
    public String toString() {
        return "Kullanici{" +
                "id='" + id + '\'' +
                ", isim='" + isim + '\'' +
                ", soyad='" + soyad + '\'' +
                '}';
    }
}
