package hse.projectx.petdonate.network;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

import hse.projectx.petdonate.forms.ThanksBlank;
import hse.projectx.petdonate.main_screen.MainPage;
import hse.projectx.petdonate.main_screen.MainScreen;
import hse.projectx.petdonate.main_screen.PetsActivity;
import hse.projectx.petdonate.main_screen.ShelterActivity;
import hse.projectx.petdonate.network.models.AdoptForm;
import hse.projectx.petdonate.network.models.Animal;
import hse.projectx.petdonate.network.models.DataState;
import hse.projectx.petdonate.network.models.HelpForm;
import hse.projectx.petdonate.network.models.Shelter;
import hse.projectx.petdonate.network.models.Transaction;
import hse.projectx.petdonate.network.transfer.UserDataRequest;
import hse.projectx.petdonate.network.transfer.UserDataResponse;
import hse.projectx.petdonate.network.transfer.UserStateRequest;
import hse.projectx.petdonate.payment.ChooseShelter;
import hse.projectx.petdonate.startup.FirstScreen;
import hse.projectx.petdonate.startup.SignInActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class State {
    public static String name = "Egor", ID = "1";
    public static int cur_HP = 30, cur_Mana = 30, cur_Stamina = 30;
    public static String type;
    public static int skin;
    public static ArrayList<Shelter> shelter_ids = new ArrayList<>();
    public static ArrayList<Animal> animal_ids = new ArrayList<>();
    public static Long transactions_count = 0L; //TODO temporary
    public static int pts = 0;


    static DataState getState() {
        return new DataState(name, ID, cur_HP, cur_Mana, cur_Stamina, type, skin, pts, transactions_count);
    }

    static void setState(DataState data) {
        name = data.getName();
        ID = data.getID();
        cur_HP = data.getCur_HP();
        cur_Stamina = data.getCur_Stamina();
        cur_Mana = data.getCur_Mana();
        type = data.getType();
        skin = data.getSkin();
        pts = data.getPts();
        transactions_count = data.getTransactions_count();
    }

    public static void putStateQuery() {
        NetworkService.getInstance()
                .getJSONApi()
                .putUserData(SignInActivity.token, new UserStateRequest(State.getState()))
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                        Log.w("PUT USER DATA", "result code =" + response.code() + "\n" + response.message() + response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        Log.e("Failure", "Cant put data. MainScreen Method");
                        t.printStackTrace();
                    }
                });
    }

    public static void postStateQuery() {
        NetworkService.getInstance()
                .getJSONApi()
                .postUserData(SignInActivity.token, new UserDataRequest(State.getState()))
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        Log.w("POST USER DATA", "result code = " + response.code() + "\n" + response.message() + response.body());
                        if (response.code() == 207)
                            State.putStateQuery();
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        Log.e("Failure", "Cant post data. MainScreen Method");
                        t.printStackTrace();
                    }
                });
    }

    public static void getStateQuery(AppCompatActivity context, Class ActivityClass, Runnable errorCallback) {
        NetworkService.getInstance()
                .getJSONApi()
                .getUserData(SignInActivity.token)
                .enqueue(new Callback<UserDataResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<UserDataResponse> call, @NonNull Response<UserDataResponse> response) {
                        Log.w("GET USER DATA", "result code = " + response.code() + "\n" + response.body());
                        Log.w("TOKEN: ", SignInActivity.token);
                        if (response.code() == 200) {
                            State.setState(response.body().getState());
                            context.startActivity(new Intent(context, MainPage.class));
                        } else if (response.code() == 207) {
                            context.startActivity(new Intent(context, MainPage.class));
                        } else {
                            //Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, ActivityClass));
                        }
                        context.finish();
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserDataResponse> call, @NonNull Throwable t) {
                        Log.e("Failure", "Cant get data. SplashActivity");
                        if (errorCallback != null) {
                            errorCallback.run();
                        }
                        t.printStackTrace();
                    }
                });
    }

    public static void getShelters() {
        NetworkService.getInstance()
                .getJSONApi()
                .getShelters()
                .enqueue(new Callback<Shelter[]>() {
                    @Override
                    public void onResponse(@NotNull Call<Shelter[]> call, @NotNull Response<Shelter[]> response) {
                        Log.w("GET SHELTERS", "result code = " + response.code() + "\n" + Arrays.toString(response.body()));
                        if (response.code() == 200) {
                            shelter_ids = new ArrayList<>(Arrays.asList(response.body()));
                            animal_ids = new ArrayList<>();
                            for (Shelter sh : State.shelter_ids)
                                NetworkService.getInstance()
                                        .getJSONApi()
                                        .getAnimals(sh.getId())
                                        .enqueue(new Callback<Animal[]>() {
                                            @Override
                                            public void onResponse(@NotNull Call<Animal[]> call, @NotNull Response<Animal[]> response) {
                                                Log.w("GET ANIMALS", "result code = " + response.code() + "\n" + Arrays.toString(response.body()));
                                                if (response.code() == 200) {
                                                    animal_ids.addAll(Arrays.asList(response.body()));
                                                }
                                            }

                                            @Override
                                            public void onFailure(@NotNull Call<Animal[]> call, @NotNull Throwable t) {
                                                Log.e("Failure", "Cant get data. ShelterActivity");
                                                t.printStackTrace();
                                            }
                                        });
                        }

                    }

                    @Override
                    public void onFailure(@NotNull Call<Shelter[]> call, @NotNull Throwable t) {
                        Log.e("Failure", "Cant get data. ShelterActivity");
                        t.printStackTrace();
                    }
                });
    }

    public static void postAdoptForm(AppCompatActivity context, AdoptForm form) {
        NetworkService.getInstance()
                .getJSONApi()
                .postAdoptForm(form)
                .enqueue(new Callback<AdoptForm>() {
                    @Override
                    public void onResponse(@NonNull Call<AdoptForm> call, @NonNull Response<AdoptForm> response) {
                        Log.w("POST FORM DATA", "result code = " + response.code() + "\n" + response.message() + response.body());
                        if (response.code() == 200)
                            context.startActivity(new Intent(context, ThanksBlank.class));
                    }

                    @Override
                    public void onFailure(@NonNull Call<AdoptForm> call, @NonNull Throwable t) {
                        Log.e("Failure", "Cant post data. MainScreen Method");
                        t.printStackTrace();
                    }
                });
    }

    public static void postHelpForm(AppCompatActivity context, HelpForm form) {
        NetworkService.getInstance()
                .getJSONApi()
                .postHelpForm(form)
                .enqueue(new Callback<HelpForm>() {
                    @Override
                    public void onResponse(@NonNull Call<HelpForm> call, @NonNull Response<HelpForm> response) {
                        Log.w("POST FORM DATA", "result code = " + response.code() + "\n" + response.message() + response.body());
                        if (response.code() == 200)
                            context.startActivity(new Intent(context, ThanksBlank.class));
                    }

                    @Override
                    public void onFailure(@NonNull Call<HelpForm> call, @NonNull Throwable t) {
                        Log.e("Failure", "Cant post data. MainScreen Method");
                        t.printStackTrace();
                    }
                });
    }

    public static void postTransactionQuery(String token, Transaction transaction, Context context) {
        NetworkService.getInstance()
                .getJSONApi()
                .postTransaction(token, transaction)
                .enqueue(new Callback<Transaction>() {
                    @Override
                    public void onResponse(@NonNull Call<Transaction> call, @NonNull Response<Transaction> response) {
                        Log.d("POST TRANSACTION DATA", "result code = " + response.code() + "\n" + response.message() + response.body());
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(@NonNull Call<Transaction> call, @NonNull Throwable t) {
                        Log.e("Failure", "Cant post data. MainScreen Method");
                        Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
    }


    public static void getShelterList(Context context, long micros) {
        NetworkService.getInstance()
                .getJSONApi()
                .getShelters()
                .enqueue(new Callback<Shelter[]>() {
                    @Override
                    public void onResponse(@NotNull Call<Shelter[]> call, @NotNull Response<Shelter[]> response) {
                        Log.w("GET SHELTERS", "result code = " + response.code() + "\n" + Arrays.toString(response.body()));
                        if (response.code() == 200) {
                            shelter_ids = new ArrayList<>(Arrays.asList(response.body()));
                            Intent intent = new Intent(context, ChooseShelter.class);
                            intent.putExtra("MICROS", micros);
                            context.startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<Shelter[]> call, @NotNull Throwable t) {
                        Log.e("Failure", "Cant get data. ShelterActivity");
                        t.printStackTrace();
                    }
                });
    }
}
