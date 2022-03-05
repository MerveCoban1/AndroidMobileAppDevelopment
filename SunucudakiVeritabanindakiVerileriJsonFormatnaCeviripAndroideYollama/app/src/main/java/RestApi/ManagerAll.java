package RestApi;

import java.util.List;

import Model.Kullanici;
import Model.Result;
import retrofit2.Call;

public class ManagerAll extends BaseManager {
    private static ManagerAll ourInstance=new ManagerAll();
    public static synchronized ManagerAll getInstance(){
        return ourInstance;
    }
    public Call<List<Kullanici>> goster(){
        Call<List<Kullanici>> x=getRestApi().listele();
        return x;
    }
    public Call<Result> deleteFromDb(String id){
        Call<Result> y=getRestApi().sil(id);
        return y;
    }
}
