package hse.projectx.petdonate.forms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hse.projectx.petdonate.main_screen.PetsActivity;
import hse.projectx.petdonate.R;
import hse.projectx.petdonate.startup.SignInActivity;
import hse.projectx.petdonate.network.State;
import hse.projectx.petdonate.main_screen.MainScreen;
import hse.projectx.petdonate.main_screen.ProfileActivity;
import hse.projectx.petdonate.network.models.AdoptForm;
import hse.projectx.petdonate.network.models.Animal;

public class BlankPetActivity extends AppCompatActivity {
    Animal an;
    TextView name, email, shelter_name;

    @Override
    public void onBackPressed() {
        //кнопка назад
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_pet);
        an = (Animal) getIntent().getSerializableExtra(PetsActivity.pet_instance);
        if (SignInActivity.account != null) {
            name = findViewById(R.id.blank_name11_sh);
            name.setText(SignInActivity.account.getDisplayName());
            email = findViewById(R.id.blank_email11_sh);
            email.setText(SignInActivity.account.getEmail());
        }
        shelter_name = findViewById(R.id.animal_name_blank);
        shelter_name.setText(getIntent().getStringExtra(PetsActivity.NAME));
    }

    public void OnClickPetInfo(View veiw) {
        startActivity(new Intent(this, MainScreen.class));
    }

    public void OnClickThanks(View veiw) {
        //TODO FILL FORM
        //AdoptForm(String shelter_id, String animal_id, String name, String phone, String email)
        AdoptForm form = new AdoptForm(getIntent().getLongExtra(PetsActivity.SHELTER_ID,0), an.getId(), shelter_name.getText().toString(), "+79963241322", email.getText().toString());
        State.postAdoptForm(this, form);
    }

    public void OnClickShelter(View view) {
        State.getShelters(this, true);
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
        State.getShelters(this, false);
    }

    public void OnClickMain0(View view) {
        startActivity(new Intent(this, MainScreen.class));
    }


}
