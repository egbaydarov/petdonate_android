package hse.projectx.petdonate.startup;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import hse.projectx.petdonate.utils.MusicServ;
import hse.projectx.petdonate.R;
import hse.projectx.petdonate.network.State;
import hse.projectx.petdonate.main_screen.MainScreen;
import retrofit2.Retrofit;

public class SignInActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        //кнопка назад
    }

    AnimationDrawable[] animations = new AnimationDrawable[3];
    int[] skins = {R.drawable.anim4, R.drawable.anim5, R.drawable.anim1};
    ImageView[] pets = new ImageView[3];



    public static GoogleSignInClient googleSignInClient;
    public static GoogleSignInAccount account;
    public static String token;

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

        startService(new Intent(this, MusicServ.class));

        pets[0] = (ImageView) findViewById(R.id.doggg1);
        pets[0].setBackgroundResource(skins[0]);
        animations[0] = (AnimationDrawable) pets[0].getBackground();
        animations[0].start();

        pets[1] = (ImageView) findViewById(R.id.doggg2);
        pets[1].setBackgroundResource(skins[1]);
        animations[1] = (AnimationDrawable) pets[1].getBackground();
        animations[1].start();

        pets[2] = (ImageView) findViewById(R.id.doggg3);
        pets[2].setBackgroundResource(skins[2]);
        animations[2] = (AnimationDrawable) pets[2].getBackground();
        animations[2].start();

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
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 101:
                    try {
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        account = task.getResult(ApiException.class);
                        if (account != null) {
                            State.ID = account.getId();
                            token = account.getIdToken();
                        }
                        State.getStateQuery(SignInActivity.this, FirstScreen.class);
                    } catch (ApiException e) {
                        e.printStackTrace();
                    }
                    break;
            }

    }


    public void OnSkipClicked(View view) {
        SharedPreferences settings = getApplicationContext().getSharedPreferences(FirstScreen.PREF_NAME, 0);

        if (settings.getString(FirstScreen.PET_NAME, null) != null) {
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
            Log.d("SAVED STATE STATUS", "NOT SAVED");
            finish();
        } else {
            Intent intent = new Intent(this, Welcome1.class);//FirstScreen.class
            startActivity(intent);
            finish();
        }

        Intent intent = new Intent(this, Welcome1.class);//FirstScreen.class
        startActivity(intent);
        finish();
    }
}
