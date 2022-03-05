package RestApi;

import java.util.List;

import Models.Bilgi;
import Models.Results;
import retrofit2.Call;

public class ManagerAll extends BaseManager{
    private static ManagerAll ourInstance=new ManagerAll();
    public static synchronized ManagerAll getInstance(){
        return ourInstance;
    }
    public Call<List<Bilgi>>  getirCall(){
        Call<List<Bilgi>> x=getRestApi().getir();
        return x;
    }
    public Call<List<Results>>  managerGetirResult(String postid,String id){
        Call<List<Results>> y=getRestApi().getirResult(postid,id);
        return y;
    }
}
