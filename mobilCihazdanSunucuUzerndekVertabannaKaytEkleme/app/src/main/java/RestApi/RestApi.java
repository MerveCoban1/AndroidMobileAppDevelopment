package RestApi;

import Models.Deneme;
import retrofit2.Call;

import retrofit2.http.GET;


public interface RestApi {

    @GET("/deneme.php")   //bu dosyaya göndericez //bu post getleri retrofit kütüphanesi sayesinde yapıyoruz.
    Call<Deneme> addUser();
    //php dosyasında post("ad"),post("soyad") ile alacağız verileri.Ve bu veriler java da String ad ve soyad a atılarak yollanmış olacak.
}
