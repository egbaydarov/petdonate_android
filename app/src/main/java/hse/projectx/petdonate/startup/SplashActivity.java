package hse.projectx.petdonate.startup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.network.State;


public class SplashActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        //кнопка назад
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.client_ID))
                .requestEmail()
                .requestId()
                .requestProfile()
                .build();
        SignInActivity.googleSignInClient = GoogleSignIn.getClient(this, gso);
        SignInActivity.account = GoogleSignIn.getLastSignedInAccount(this);
        startActivity(new Intent(this, SignInActivity.class));
        if (SignInActivity.account != null) {
            SignInActivity.token = SignInActivity.account.getIdToken();
            State.getStateQuery(SplashActivity.this, SignInActivity.class);
        } else {
            startActivity(new Intent(this, SignInActivity.class));
            Log.d("SPLASH LOGIN", "Not logged in, login activity started");
        }
    }
}