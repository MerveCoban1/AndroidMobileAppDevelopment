package RestApi;

import Models.Result;
import retrofit2.Call;

public class ManagerAll extends BaseManager{
    private static ManagerAll ourInstance=new ManagerAll();
    public static synchronized ManagerAll getInstance(){
        return ourInstance;
    }
    public Call<Result> ekle(String kullaniciadi, String sifre,String mailadres){
        Call<Result> x=getRestApi().addUser(kullaniciadi,sifre,mailadres);
        return x;
    }
}
