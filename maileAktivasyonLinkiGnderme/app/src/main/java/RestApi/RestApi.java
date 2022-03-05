package RestApi;

import Models.Result;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {
    @FormUrlEncoded
    @POST("/ekle.php")
    Call<Result> addUser(@Field("kullaniciadi") String kullaniciadi, @Field("sifre") String sifre, @Field("mailadres") String mailadres);

}

