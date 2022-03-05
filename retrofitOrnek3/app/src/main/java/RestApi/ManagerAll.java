package RestApi;

import java.util.List;

import Models.YeniBilgi;
import retrofit2.Call;

public class ManagerAll extends BaseManager{
    //önce bu classın kendisini döndürüyoruz.neden: main activity debu class içindeki metotlara erişmek için
    //nesnesini oluşturmayacağım için  static olarak oluşturuyoruz.
    private static ManagerAll ourgetInstance=new ManagerAll();
    //bu classın dönmesini sağlayan fonk tanımlayalım
    public static synchronized ManagerAll getInstance(){
        return ourgetInstance;
    }
    //main activityde çağıracağımız metodu tanımlayalım artık.
    public Call<List<YeniBilgi>> getirBilgi(){
        Call<List<YeniBilgi>> x=getRestApiClient().getir();
        return x;
    }
}
