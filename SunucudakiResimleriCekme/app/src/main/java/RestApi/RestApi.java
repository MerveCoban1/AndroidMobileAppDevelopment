package RestApi;

import java.util.List;

import Models.urunler;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {
    @GET("/urunler.php")
    Call<List<urunler>> listele();  //bunun parametresi yok sadece o sayfaya istekte bulunuyoruz.Karşılığında verileri alıyoruz.

}
