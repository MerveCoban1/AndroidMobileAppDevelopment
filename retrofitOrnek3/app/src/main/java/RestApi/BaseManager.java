package RestApi;

public class BaseManager {
    //bu sınıf bize RestApiyi döndürüyor.
    protected RestApi getRestApiClient(){
        RestApiClient rsp=new RestApiClient(BaseUrl.url);
        return rsp.getRestApi();
    }
}
