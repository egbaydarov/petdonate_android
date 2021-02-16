package hse.projectx.petdonate.network;

import hse.projectx.petdonate.network.models.AdoptForm;
import hse.projectx.petdonate.network.models.Animal;
import hse.projectx.petdonate.network.models.HelpForm;
import hse.projectx.petdonate.network.models.Shelter;
import hse.projectx.petdonate.network.models.Transaction;
import hse.projectx.petdonate.network.transfer.UserDataRequest;
import hse.projectx.petdonate.network.transfer.UserDataResponse;
import hse.projectx.petdonate.network.transfer.UserStateRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PetDonateApi {
    @POST("apiv1/data/{token}")
    Call<Void> postUserData(@Path("token") String token,
                            @Body UserDataRequest data);

    @PUT("apiv1/data/{token}")
    Call<String> putUserData(@Path("token") String token,
                              @Body UserStateRequest data);

    @GET("apiv1/data/{token}")
    Call<UserDataResponse> getUserData(@Path("token") String token);

    @GET("apiv1/shelters/fff") // fff-instead of token
    Call<Shelter[]> getShelters();

    @GET("apiv1/animals/{shelterId}")
    Call<Animal[]> getAnimals(@Path("shelterId") Long shelterId);

    @POST("apiv1/adopt/")
    Call<AdoptForm> postAdoptForm(@Body AdoptForm form);

    @POST("apiv1/help/")
    Call<HelpForm> postHelpForm(@Body HelpForm form);

    @POST("apiv1/transaction/{token}")
    Call<Transaction> postTransaction(@Path("token") String token, @Body Transaction transaction);
}
