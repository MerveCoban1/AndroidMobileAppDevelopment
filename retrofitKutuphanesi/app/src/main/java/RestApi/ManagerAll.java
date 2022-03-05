package RestApi;

import java.util.List;

import Models.Bilgiler;
import retrofit2.Call; //call yaparken retrofitinki olacak dikkat et buna

public class ManagerAll extends BaseManager{
    private static ManagerAll ourInstance=new ManagerAll();
    public static synchronized ManagerAll getInstance(){
        return ourInstance; //nesnesini oluşturmadan erişmek için ınstance oluşturmuşuz.
    }
    public Call<List<Bilgiler>> getirBilgileri(){
        Call<List<Bilgiler>> call=getRestApiClient().bilgiGetir();
        return call;
        //main activity içinde bu fonk çağırdığımızda istek atılacak ve biz cevabını alacağız.

    }
}
