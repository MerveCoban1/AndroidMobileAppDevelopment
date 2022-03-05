package RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {
    @GET("/urunler.php")
    Call<List<urunler>> listele();
}
