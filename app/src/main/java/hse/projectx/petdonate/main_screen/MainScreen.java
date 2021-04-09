package hse.projectx.petdonate.main_screen;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
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
    FrameLayout progressOverlay = null;

    public void setOverlayInvisible() {
        progressOverlay.setVisibility(View.INVISIBLE);
    }
    public void setOverlayVisible() {
        progressOverlay.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        //кнопка назад
    }

    ProgressBar Health;
    ProgressBar Food;
    ProgressBar Fun;
    ProgressBar Energy;
    ProgressBar Points;

    TextView Text;
    TextView name;
    ImageView foodImage;


    ImageView pets;
    AnimationDrawable animations;
    ConstraintLayout main_layout;

    void initialize()
    {
        name = findViewById(R.id.name);
        foodImage = findViewById(R.id.food);
        Health = findViewById(R.id.HpBar);
        Food = findViewById(R.id.ManaBar);
        Fun = findViewById(R.id.StaminaBar);
        Points = findViewById(R.id.PointBar);
        Text = findViewById(R.id.PointsText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        State.postStateQuery();
        main_layout =  (ConstraintLayout) findViewById(R.id.first_screen);

        initialize();

        setContentView(R.layout.main_screen);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        Health.setProgress(State.cur_HP);
        Food.setProgress(State.cur_Mana);
        Fun.setProgress(State.cur_Stamina);
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
        progressOverlay = findViewById(R.id.progress_overlay);

    }

    Runnable runn1 = new Runnable() {
        public void run() {
            int minus = 2;
            State.cur_HP = Health.getProgress() - minus;
            State.cur_Mana = Food.getProgress() - minus;
            State.cur_Stamina = Fun.getProgress() - minus;
            State.pts = Points.getProgress() + 5;
            Points.setProgress(Points.getProgress() + 5);
            Text.setText(Points.getProgress() + "/" + 100);
            Health.setProgress(State.cur_HP - minus);
            Food.setProgress(State.cur_Mana - minus);
            Fun.setProgress(State.cur_Stamina - minus);
            State.putStateQuery();
        }
    };

  









}
