package RestApi;

import java.util.List;

import Models.urunler;
import retrofit2.Call;

public class ManagerAll extends BaseManager{
    private static ManagerAll ourInstance=new ManagerAll();
    public static synchronized ManagerAll getInstance(){
        return ourInstance;
    }
    public Call<List<urunler>> goster(){
        Call<List<urunler>> x=getRestApi().listele();
        return x;
    }
}
