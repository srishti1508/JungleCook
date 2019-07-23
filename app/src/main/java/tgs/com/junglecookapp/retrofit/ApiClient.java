package tgs.com.junglecookapp.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public  static String Apikey = "5199";
    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS).build();

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    //private static final String BASE_URL = "http://jabalpurbloodbank.com/app_service/index.php/admin/ADMIN/";
    //private static final String BASE_URL = "http://myglobalapp.in";
    //private static final String BASE_URL = "http://192.168.1.237/rest_pos/web_service/";
  private static final String BASE_URL = "http://103.103.213.104/junglecook/web_service/";
  // private static final String BASE_URL = "http://182.70.253.144:8081/rest_posweb/web_service/";
    public static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}
