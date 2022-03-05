package RestApi;

public class BaseManager {
    protected RestApi getRestApiClient(){
        RestApiClient restApiClient=new RestApiClient(BaseUrl.bilgi_URL); //restApiClient'ın nesnesini oluşturduk.
        //url'i vereceğiz bu bize restApiyi döndürecek.
        return restApiClient.getRestApi();
    }
    //bir json url'ine istek atıyoruz.
}
