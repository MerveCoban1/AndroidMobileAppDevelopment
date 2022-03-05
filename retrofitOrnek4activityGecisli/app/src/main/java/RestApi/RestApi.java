package RestApi;

import java.util.List;

import Models.Bilgi;
import Models.Results;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {
    @GET("/comments")
    Call<List<Bilgi>> getir();

    @GET("/comments")
    Call<List<Results>> getirResult(@Query("postId") String postid, @Query("id") String id);

}
