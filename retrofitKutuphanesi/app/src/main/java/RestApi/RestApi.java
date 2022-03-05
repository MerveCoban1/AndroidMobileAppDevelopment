package RestApi;

import java.util.List;

import Models.Bilgiler;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {
    //retrofite ait interface classı.
    @GET("/posts")  //veri alacağız
    Call<List<Bilgiler>> bilgiGetir(); //bilgiler sınıfına ait liste donecek

}
