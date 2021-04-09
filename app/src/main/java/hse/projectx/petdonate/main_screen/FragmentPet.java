package hse.projectx.petdonate.main_screen;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.network.State;

public class FragmentPet extends Fragment {
    ProgressBar Health;
    ProgressBar Food;
    ProgressBar Fun;

    TextView name;
    ImageView foodImage;

    ImageView pets;
    AnimationDrawable animations;
    ConstraintLayout main_layout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        name = view.findViewById(R.id.name);
        foodImage = view.findViewById(R.id.food);
        Health = view.findViewById(R.id.HpBar);
        Food = view.findViewById(R.id.ManaBar);
        Fun = view.findViewById(R.id.StaminaBar);

        Health.setProgress(State.cur_HP);
        Food.setProgress(State.cur_Mana);
        Fun.setProgress(State.cur_Stamina);
        name.setText(State.name);
        name.setTextSize((40 - (name.getText().length() - 3) * 5 / 3.0f) *
                getResources().getDisplayMetrics().scaledDensity);

        pets = view.findViewById(R.id.Pett);
        pets.setBackgroundResource(R.drawable.anim1); //TODO FIX THIS SHIT

        if (pets.getBackground() instanceof AnimationDrawable) {
            animations = (AnimationDrawable) pets.getBackground();
            animations.start();
        }

        //TODO
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(getActivity() != null)
                    getActivity().runOnUiThread(runn1);
            }
        }).start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet, container, false);

        Button button_game = view.findViewById(R.id.frag_game);
        button_game.setOnClickListener(this::OnClickGame);

        Button button_heal = view.findViewById(R.id.frag_heal);
        button_heal.setOnClickListener(this::OnClickHeal);

        Button button_feed = view.findViewById(R.id.frag_feed);
        button_feed.setOnClickListener(this::OnClickFeed);

        return view;
    }

    Runnable runn1 = new Runnable() {
        public void run() {
            int minus = 2;
            State.cur_HP = Health.getProgress() - minus;
            State.cur_Mana = Food.getProgress() - minus;
            State.cur_Stamina = Fun.getProgress() - minus;
            Health.setProgress(State.cur_HP - minus);
            Food.setProgress(State.cur_Mana - minus);
            Fun.setProgress(State.cur_Stamina - minus);
        }
    };

    public void OnClickHeal(View veiw) {
        State.cur_HP = Health.getProgress() + 5;
        Health.setProgress(State.cur_HP + 5);
    }


    public void OnClickGame(View veiw) {
        State.cur_Mana = Food.getProgress() + 5;
        Food.setProgress(State.cur_Mana + 5);
    }

    public void OnClickFeed(View veiw) {
        State.cur_Stamina = Fun.getProgress() + 5;
        Fun.setProgress(State.cur_Stamina + 5);
        if (Fun.getProgress() > 60) {
            foodImage.setImageResource(R.drawable.full_food);
        }
    }

}
