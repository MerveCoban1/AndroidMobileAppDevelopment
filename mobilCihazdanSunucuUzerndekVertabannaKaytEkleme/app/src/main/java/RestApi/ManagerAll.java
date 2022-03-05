package RestApi;

import Models.Deneme;
import retrofit2.Call;

public class ManagerAll extends BaseManager {
    private static ManagerAll ourInstance=new ManagerAll();
    public static synchronized ManagerAll getInstance(){
        return ourInstance;
    }
    public Call<Deneme> ekle(){
        Call<Deneme> x=getRestApi().addUser();
        return x;
    }
}
