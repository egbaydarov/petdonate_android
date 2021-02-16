package hse.projectx.petdonate.payment;

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

public class ThanksPayment extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        //кнопка назад
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanks_payment);
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

    public void OnClickYes(View view) {
        startActivity(new Intent(this, MainScreen.class));
    }
}
