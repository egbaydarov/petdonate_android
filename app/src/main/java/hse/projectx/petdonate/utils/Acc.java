package hse.projectx.petdonate.utils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.network.State;
import hse.projectx.petdonate.startup.FirstScreen;
import retrofit2.Retrofit;

public class Acc extends AppCompatActivity {

    static GoogleSignInClient googleSignInClient;
    static GoogleSignInAccount account;
    static String token;

    private Retrofit client;

    void ConfigureGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.client_ID))
                .requestEmail()
                .requestId()
                .requestProfile()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_log);

        SignInButton googleSignInButton = findViewById(R.id.sign_in_button);
        Button skipLogButton = findViewById(R.id.buttonSkipLog);

        ConfigureGoogleSignIn();

        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        try {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            account = task.getResult(ApiException.class);
            if (account != null) {
                State.ID = account.getId();
                token = account.getIdToken();
            }

            State.getStateQuery(Acc.this, FirstScreen.class);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    public void OnSkipClicked(View view) {
        SharedPreferences settings = getApplicationContext().getSharedPreferences(FirstScreen.PREF_NAME, 0);
        /*
        if (settings.getString(FirstScreen.PET_NAME, null) != null) {
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
            Log.d("SAVED STATE STATUS", "NOT SAVED");
            finish();
        } else {
            Intent intent = new Intent(this, FirstScreen.class);
            startActivity(intent);
            finish();
        }*/

        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
        finish();
    }
}
