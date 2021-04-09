package hse.projectx.petdonate.info_screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.payment.ChooseFood;
import hse.projectx.petdonate.startup.SignInActivity;
import hse.projectx.petdonate.network.State;
import hse.projectx.petdonate.forms.BlankShelterActivity;
import hse.projectx.petdonate.main_screen.MainScreen;
import hse.projectx.petdonate.main_screen.ProfileActivity;
import hse.projectx.petdonate.main_screen.ShelterActivity;
import hse.projectx.petdonate.network.NetworkService;
import hse.projectx.petdonate.network.models.Animal;
import hse.projectx.petdonate.network.models.Shelter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShelterInfoActivity extends AppCompatActivity {

    Shelter sh;
    TextView dogs_count , cats_count, others_count;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shelter_page);


        dogs_count = findViewById(R.id.t1);
        cats_count = findViewById(R.id.t2);
        others_count = findViewById(R.id.t3);

        TextView tw_name = findViewById(R.id.sh_name);
        tw_name.setText(getIntent().getStringExtra(ShelterActivity.NAME));

        TextView tw_loc = findViewById(R.id.sh_loc);
        tw_loc.setText(getIntent().getStringExtra(ShelterActivity.ADRESS));

        TextView tw_info = findViewById(R.id.sh_info);
        String info = getIntent().getStringExtra(ShelterActivity.CARD) + '\n' + getIntent().getStringExtra(ShelterActivity.EMAIL);
        tw_info.setText(info);

        TextView tw_phone = findViewById(R.id.sh_phone);
        tw_phone.setText(getIntent().getStringExtra(ShelterActivity.PHONE));

        sh = (Shelter) getIntent().getSerializableExtra(ShelterActivity.SHELTER);

        NetworkService.getInstance()
                .getJSONApi()
                .getAnimals(sh.getId())
                .enqueue(new Callback<Animal[]>() {
                    @Override
                    public void onResponse(@NotNull Call<Animal[]> call, @NotNull Response<Animal[]> response) {
                        Log.w("GET ANIMALS", "result code = " + response.code() + "\n" + Arrays.toString(response.body()));
                        if (response.code() == 200) {
                            ArrayList<Animal> animals =  new ArrayList<>(Arrays.asList(response.body()));
                            dogs_count.setText(Long.toString(animals.stream()
                                    .filter((sh) -> sh.getType().equals("Собака"))
                                    .count()));
                            cats_count.setText(Long.toString(animals.stream()
                                    .filter((sh) -> sh.getType().equals("Кошка"))
                                    .count()));
                            others_count.setText(Long.toString(animals.stream()
                                    .filter((sh) -> sh.getType().equals("Другое"))
                                    .count()));
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<Animal[]> call, @NotNull Throwable t) {
                        Log.e("Failure", "Cant get data. ShelterActivity");
                        t.printStackTrace();
                    }
                });

        TextView tw_url = findViewById(R.id.sh_url);
        tw_url.setText(getIntent().getStringExtra(ShelterActivity.URL));

        ImageView imageView = (ImageView) findViewById(R.id.sh_pic);

        Picasso.get().load(sh.getPicture())
                .error(R.drawable.baseline_broken_image_black_18dp)//TODO REPLACE IMAGE
                .fit()
                .centerCrop()
                .placeholder(R.drawable.load)//TODO REPLACE IMAGE
                .into(imageView);
    }

    public void OnClickShelters(View veiw)
    {
        onBackPressed();
    }

    public void OnClickAccept(View veiw)
    {
        startActivity(new Intent(this, ChooseFood.class));
    }


    public void OnClickShelter(View view) {
    }

    public void onClickProfile(View veiw) {
        if (SignInActivity.account != null) {
            Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("IsFirst", false);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SignInActivity.class);
            intent.putExtra("IsFirst", false);
            startActivity(intent);
        }
    }

    public void OnClickPets(View view) {
    }

    public void OnClickMain0(View view) {
        startActivity(new Intent(this, MainScreen.class));
    }
}
