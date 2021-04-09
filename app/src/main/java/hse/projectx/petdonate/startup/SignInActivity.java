package hse.projectx.petdonate.startup;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.shobhitpuri.custombuttons.GoogleSignInButton;

import hse.projectx.petdonate.utils.MusicServ;
import hse.projectx.petdonate.R;
import hse.projectx.petdonate.network.State;
import hse.projectx.petdonate.main_screen.MainScreen;
import retrofit2.Retrofit;

public class SignInActivity extends AppCompatActivity {
    public void setInvisible() {
        progressOverlay.setVisibility(View.INVISIBLE);
    }
    public void setVisible() {
        progressOverlay.setVisibility(View.VISIBLE);
    }
    FrameLayout progressOverlay;
    LinearLayout bottomLayout;

    @Override
    public void onBackPressed() {
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
        account = GoogleSignIn.getLastSignedInAccount(this);

        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 101);
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

        GoogleSignInButton googleSignInButton = findViewById(R.id.sign_in_button);

        ConfigureGoogleSignIn();

        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });

        progressOverlay = findViewById(R.id.progress_overlay);
        bottomLayout = findViewById(R.id.bottomRetryLayout);
        bottomLayout.setVisibility(View.GONE);

        ((Button)findViewById(R.id.retry_clickable_text)).setText(getString(R.string.ok));
        ((TextView)findViewById(R.id.retry_text)).setText(getString(R.string.error_text));

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setInvisible();
        Log.i("Google SignIn", Integer.toString(resultCode) + " " + requestCode);

        if (resultCode == Activity.RESULT_OK) //Success
            switch (requestCode) {
                case 101:
                    try {
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        account = task.getResult(ApiException.class);
                        if (account != null) {
                            State.ID = account.getId();
                            token = account.getIdToken();
                        }
                        setVisible();
                        State.getShelters();
                        State.getStateQuery(SignInActivity.this, FirstScreen.class, ()->
                        {
                            slideUp(bottomLayout);
                            setInvisible();
                        });
                    } catch (ApiException e) {
                        slideUp(bottomLayout);
                        e.printStackTrace();
                    }
                    break;
                default:
                    slideUp(bottomLayout);
                    setInvisible();
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


    public void slideUp(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(View view){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }


    public void OnRetryClicked(View view) {
        //bottomLayout.setVisibility(View.GONE);
        slideDown(bottomLayout);
    }
}
