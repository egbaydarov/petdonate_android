package hse.projectx.petdonate.forms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.startup.SignInActivity;
import hse.projectx.petdonate.network.State;
import hse.projectx.petdonate.main_screen.MainScreen;
import hse.projectx.petdonate.main_screen.ProfileActivity;

public class ThanksBlank extends AppCompatActivity {




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanks_blank);
    }

    public void OnClickMAIN(View veiw)
    {
        startActivity(new Intent(this, MainScreen.class));
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
