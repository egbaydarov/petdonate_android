package hse.projectx.petdonate.main_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.startup.SignInActivity;
import hse.projectx.petdonate.network.State;

public class ProfileActivity extends AppCompatActivity {



    public static final String GOOGLE_ACCOUNT = "google_account";
    public static final String OAUTH_TOKEN = "oauth_token";
    private TextView profileName,profileEmail;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        profileName = findViewById(R.id.Name);
        profileEmail = findViewById(R.id.textView7);
        profileImage = findViewById(R.id.profile_image);

        Button signOut = findViewById(R.id.sign_out);
        setDataOnView();

        TextView transactions = findViewById(R.id.textView6);
        transactions.setText(State.transactions_count.toString() + " Переводов");

        signOut.setOnClickListener(v -> {
      /*
      Sign-out is initiated by simply calling the googleSignInClient.signOut API. We add a
      listener which will be invoked once the sign out is the successful
       */
            SignInActivity.googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    //On Succesfull signout we navigate the user back to LoginActivity
                    Intent intent= new Intent(ProfileActivity.this, SignInActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    SignInActivity.account = null;
                    SignInActivity.token = null;
                    finish();
                }
            });
        });
    }
    private void setDataOnView() {

        GoogleSignInAccount googleSignInAccount = SignInActivity.account;
        Picasso.get().load(googleSignInAccount.getPhotoUrl()).centerInside().fit().into(profileImage);
        profileName.setText(googleSignInAccount.getDisplayName());
        profileEmail.setText(googleSignInAccount.getEmail());
        //profileId.setText(googleSignInAccount.getId());
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
