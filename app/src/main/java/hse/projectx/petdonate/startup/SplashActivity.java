package hse.projectx.petdonate.startup;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.Objects;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.network.State;


public class SplashActivity extends AppCompatActivity {

    LinearLayout bottomLayout;
    FrameLayout overlay;

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);

        bottomLayout = findViewById(R.id.bottomRetryLayout);
        overlay = findViewById(R.id.progress_overlay);

        overlay.setVisibility(View.VISIBLE);
        slideDown(bottomLayout);

        TryConnectAndGoSignIn();
    }

    private void TryConnectAndGoSignIn()
    {
        if (isNetworkAvailable()) {
            startActivity(new Intent(this, SignInActivity.class));
        } else {
            Log.i("On Create SplashActivity", "No internet connection");
            slideUp(bottomLayout);
        }
    }

    public void slideUp(View view){
        view.setVisibility(View.VISIBLE);
        overlay.setVisibility(View.INVISIBLE);
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
        overlay.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    @Override
    public void onBackPressed() {
    }

    public void OnRetryClicked(View view) {
        slideDown(bottomLayout);
        TryConnectAndGoSignIn();
    }
}