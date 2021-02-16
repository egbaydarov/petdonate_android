package hse.projectx.petdonate.main_screen;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import java.util.concurrent.TimeUnit;

import hse.projectx.petdonate.payment.ChooseFood;
import hse.projectx.petdonate.utils.MyDialogFragment;
import hse.projectx.petdonate.R;
import hse.projectx.petdonate.startup.SignInActivity;
import hse.projectx.petdonate.network.State;


public class MainScreen extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        //кнопка назад
    }

    ProgressBar HP;
    ProgressBar Mana;
    ProgressBar Stamina;
    ProgressBar Points;
    TextView Text;
    TextView name;
    ImageView foodImage;


    ImageView pets;
    AnimationDrawable animations;
    ConstraintLayout main_layout;

    LinearLayout lin3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        State.downloadPreferences(MainScreen.this);
        State.postStateQuery();
        main_layout =  (ConstraintLayout) findViewById(R.id.first_screen);




        setContentView(R.layout.main_screen);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name = (TextView) findViewById(R.id.name);
        foodImage = (ImageView) findViewById(R.id.food);
        HP = (ProgressBar) findViewById(R.id.HpBar);
        Mana = (ProgressBar) findViewById(R.id.ManaBar);
        Stamina = (ProgressBar) findViewById(R.id.StaminaBar);
        Points = (ProgressBar) findViewById(R.id.PointBar);
        Text = (TextView) findViewById(R.id.PointsText);

        HP.setProgress(State.cur_HP);
        Mana.setProgress(State.cur_Mana);
        Stamina.setProgress(State.cur_Stamina);
        Points.setProgress(50);
        Text.setText(Points.getProgress() + "/" + 100);
        name.setText(State.name);

        pets = (ImageView) findViewById(R.id.Pett);
        pets.setBackgroundResource(R.drawable.anim1); //TODO FIX THIS SHIT

        if (pets.getBackground() instanceof AnimationDrawable) {
            animations = (AnimationDrawable) pets.getBackground();
            animations.start();
        }


        Thread t = new Thread(new Runnable() {
            public void run() {

                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(4);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(runn1);
                }
            }
        });


        t.start();


    }

    Runnable runn1 = new Runnable() {
        public void run() {
            int minus = 2;
            State.cur_HP = HP.getProgress() - minus;
            State.cur_Mana = Mana.getProgress() - minus;
            State.cur_Stamina = Stamina.getProgress() - minus;
            State.pts = Points.getProgress() + 5;
            State.uploadPreferences(MainScreen.this);
            Points.setProgress(Points.getProgress() + 5);
            Text.setText(Points.getProgress() + "/" + 100);
            HP.setProgress(State.cur_HP - minus);
            Mana.setProgress(State.cur_Mana - minus);
            Stamina.setProgress(State.cur_Stamina - minus);
            State.putStateQuery();
        }
    };

    public void OnClickHeal(View veiw) {
        if (Points.getProgress() > 0)
        {
            State.cur_HP = HP.getProgress() + 5;
            State.pts = Points.getProgress() - 5;
            HP.setProgress(State.cur_HP + 5);
            Points.setProgress(Points.getProgress() - 5);
            Text.setText(Points.getProgress() + "/" + 100);
            State.uploadPreferences(MainScreen.this);
            State.putStateQuery();
        }
    }





    public void OnClickGame(View veiw) {
        if (Points.getProgress() > 0) {
            State.cur_Mana = Mana.getProgress() + 5;
            Mana.setProgress(State.cur_Mana + 5);
            Points.setProgress(Points.getProgress() - 5);
            Text.setText(Points.getProgress() + "/" + 100);
            State.uploadPreferences(MainScreen.this);
            State.putStateQuery();
        }
    }

    public void OnClickFeed(View veiw) {
        if (Points.getProgress() > 0) {
            State.cur_Stamina = Stamina.getProgress() + 5;
            Stamina.setProgress(State.cur_Stamina + 5);
            Points.setProgress(Points.getProgress() - 5);
            Text.setText(Points.getProgress() + "/" + 100);
            if (Stamina.getProgress() > 60) {
                foodImage.setImageResource(R.drawable.full_food);
            }
            State.uploadPreferences(MainScreen.this);
            State.putStateQuery();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        lin3 = (LinearLayout) findViewById(R.id.mask);
        lin3.setVisibility(View.GONE);

    }


    public void OnClickPets(View view) {
        lin3 = (LinearLayout) findViewById(R.id.mask);
        lin3.setVisibility(View.VISIBLE);

        FragmentManager manager = getSupportFragmentManager();
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        myDialogFragment.show(manager, "myDialog");

        State.getShelters(this, false);
    }

    public void onClickProfile(View veiw) {
        lin3 = (LinearLayout) findViewById(R.id.mask);
        lin3.setVisibility(View.VISIBLE);

        FragmentManager manager = getSupportFragmentManager();
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        myDialogFragment.show(manager, "myDialog");

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

    public void OnClickShelter(View view) {
        lin3 = (LinearLayout) findViewById(R.id.mask);
        lin3.setVisibility(View.VISIBLE);

        FragmentManager manager = getSupportFragmentManager();
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        myDialogFragment.show(manager, "myDialog");

        State.getShelters(this, true);
    }

    public void OnClickMore(View view) {
        lin3 = (LinearLayout) findViewById(R.id.mask);
        lin3.setVisibility(View.VISIBLE);

        FragmentManager manager = getSupportFragmentManager();
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        myDialogFragment.show(manager, "myDialog");

        startActivity(new Intent(this, ChooseFood.class));
    }


    /*

  <LinearLayout
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/Pett"
        app:layout_constraintVertical_bias="1.0">

        <ImageButton
            android:id="@+id/db_but_1"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:background="@drawable/downbar_choose"
            android:src="@drawable/menu_pet" />

        <ImageButton
            android:id="@+id/db_but_3"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:background="@xml/button_down_bar"
            android:onClick="OnClickShelter"
            android:scaleType="centerInside"
            android:src="@drawable/menu_shelter" />

        <ImageButton
            android:id="@+id/db_but_4"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:onClick="OnClickPets"
            android:background="@xml/button_down_bar"
            android:src="@drawable/menu_pets" />

        <ImageButton
            android:id="@+id/db_but_2"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:background="@xml/button_down_bar"
            android:onClick="onClickProfile"
            android:src="@drawable/menu_prof" />

    </LinearLayout>
  */
}
