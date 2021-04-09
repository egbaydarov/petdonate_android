package hse.projectx.petdonate.main_screen;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.network.State;
import hse.projectx.petdonate.startup.SignInActivity;

public class FragmentProfile extends Fragment {
    private TextView profileName = null;
    private TextView profileEmail= null;
    private ImageView profileImage= null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            profileName = view.findViewById(R.id.Name);
            profileEmail = view.findViewById(R.id.textView7);
            profileImage = view.findViewById(R.id.profile_image);

        Button signOut = view.findViewById(R.id.sign_out);
        setDataOnView();

        TextView transactions = view.findViewById(R.id.textView6);
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
                    Intent intent= new Intent(getActivity(), SignInActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    SignInActivity.account = null;
                    SignInActivity.token = null;
                    Objects.requireNonNull(getActivity()).finish();
                }
            });
        });

        Objects.requireNonNull(getActivity(), "Null Activity in Profile Fragment.")
                .getWindow().setBackgroundDrawableResource(R.drawable.background);
    }

    private void setDataOnView() {

        GoogleSignInAccount googleSignInAccount = SignInActivity.account;
        Picasso.get().load(googleSignInAccount.getPhotoUrl()).centerInside().fit().into(profileImage);
        profileName.setText(googleSignInAccount.getDisplayName());
        profileEmail.setText(googleSignInAccount.getEmail());
        //profileId.setText(googleSignInAccount.getId());
    }
}
