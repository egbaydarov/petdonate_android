package hse.projectx.petdonate.forms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.startup.SignInActivity;
import hse.projectx.petdonate.network.State;
import hse.projectx.petdonate.main_screen.MainScreen;
import hse.projectx.petdonate.main_screen.ProfileActivity;
import hse.projectx.petdonate.main_screen.ShelterActivity;
import hse.projectx.petdonate.network.models.HelpForm;
import hse.projectx.petdonate.network.models.Shelter;

public class BlankShelterActivity extends AppCompatActivity {
    Shelter sh;
    TextView shelter_name,shelter_date,name,email;
    @Override
    public void onBackPressed() {
        //кнопка назад
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_shelter);
        sh = (Shelter) getIntent().getSerializableExtra(ShelterActivity.SHELTER);
        if (SignInActivity.account != null) {
            name = findViewById(R.id.blank_name1_sh);
            name.setText(SignInActivity.account.getDisplayName());
            email = findViewById(R.id.blank_email1_sh);
            email.setText(SignInActivity.account.getEmail());
        }
        shelter_name = findViewById(R.id.shelter_name_blank);
        shelter_date = findViewById(R.id.blank_date1);
        shelter_date.setText(LocalDateTime.now().toString());
        shelter_name.setText(sh.getName());
    }

    public void OnClickShelterInfo(View veiw) {
        startActivity(new Intent(this, MainScreen.class));
    }

    public void OnClickThanks(View veiw) {
        //TODO FILL FORM
        //HelpForm(String shelter_id, String name, String phone, String email, String date, String extra)
        HelpForm form = new HelpForm(sh.getId(), name.getText().toString(), "+79963241322", email.getText().toString(), "1.2.2000", "empty");
        State.postHelpForm(this, form);
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
