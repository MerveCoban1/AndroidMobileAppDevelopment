package RestApi;

import java.util.List;

import Model.Kullanici;
import Model.Result;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {
    @GET("/listele.php")
    Call<List<Kullanici>> listele();  //bunun parametresi yok sadece o sayfaya istekte bulunuyoruz.Karşılığında verileri alıyoruz.

    //silme işlemi için yine farklı bir istekte bulunacağız.
    @FormUrlEncoded
    @POST("/sil.php") Call<Result> sil(@Field("id") String id);  //id yollacağız siteye istekte bulunurken.
    //sil.php'de $_POST["id"];//ile alınıyor.

}
