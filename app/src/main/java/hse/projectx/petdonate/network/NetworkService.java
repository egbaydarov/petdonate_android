package hse.projectx.petdonate.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService mInstance;
    private static final String BASE_URL = "https://demopet.herokuapp.com/";
    //private static final String BASE_URL = "https://192.168.43.121:8443/";
    //private static final String BASE_URL = "https://192.168.1.39:8443/";
    private Retrofit mRetrofit;


    private NetworkService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        mRetrofit = new Retrofit.Builder()
    //            .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null)
            mInstance = new NetworkService();
        return mInstance;
    }

    public PetDonateApi getJSONApi() {
        return mRetrofit.create(PetDonateApi.class);
    }
}
