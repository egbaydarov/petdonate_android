package hse.projectx.petdonate.startup;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.network.State;
import hse.projectx.petdonate.main_screen.MainScreen;


public class ChooseDog extends AppCompatActivity {

    ImageView imageShadow;
    TextView textBreed;
    int n1 = 0;
    int state = 0;// текущая собака
    int temp_anim = 0;
    int pets_size = 2;
    int second_state = 0;
    int[][] skins = {{R.drawable.anim1, R.drawable.anim1, R.drawable.anim1}, {R.drawable.anim2, R.drawable.anim3, R.drawable.anim4}};
    String[] strings = {"Бульдог", "Пудель", "Такса"};

    ImageView[] pets = new ImageView[pets_size];
    AnimationDrawable[] animations = new AnimationDrawable[pets_size];

    ImageView[] breed1 = new ImageView[3];
    AnimationDrawable[] poodles = new AnimationDrawable[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_dog);//kek
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageShadow = (ImageView) findViewById(R.id.shadow2);
        textBreed = (TextView) findViewById(R.id.choose_ur_fighter2);

        pets[0] = (ImageView) findViewById(R.id.pet);
        pets[0].setBackgroundResource(skins[0][0]);
        animations[0] = (AnimationDrawable) pets[0].getBackground();

        pets[1] = (ImageView) findViewById(R.id.pet2);
        pets[1].setBackgroundResource(skins[1][0]);
        animations[1] = (AnimationDrawable) pets[1].getBackground();


        State.skin = R.drawable.anim1;//TODO egor daun

        for (int i = 0; i < pets_size; ++i) {
            pets[i].setVisibility(View.INVISIBLE);
            animations[i].start();
        }
        pets[0].setVisibility(View.VISIBLE);
    }

    public void OnMyClickLeft2(View view) {

        state = ++state % 2;
        SetAnim(temp_anim, -1);
        temp_anim = Math.abs((temp_anim - 1) % pets_size);
        State.skin = skins[state][second_state];
        textBreed.setText(strings[Math.abs(++n1 % 2)]);
    }

    public void OnMyClickRight2(View view) {
        state = ++state % 2;
        SetAnim(temp_anim, 1);
        temp_anim = Math.abs((temp_anim + 1) % pets_size);
        State.skin = skins[state][second_state];
        textBreed.setText(strings[Math.abs(++n1 % 2)]);
    }


    public void OnClickColor1(View view) {
        if (state == 1) {
            SetBreed(0);
            State.skin = skins[state][0];
            second_state = 0;
        }
    }

    public void OnClickColor2(View view) {
        if (state == 1) {
            SetBreed(1);
            State.skin = skins[state][1];
            second_state = 1;
        }
    }

    public void OnClickColor3(View view) {
        if (state == 1) {
            SetBreed(2);
            State.skin = skins[state][2];
            second_state = 2;
        }
    }


    public void OnClick2(View veiw) {
        Intent intent = new Intent(this, MainScreen.class);
        intent.putExtra("n", getIntent().getStringExtra("n"));
        intent.putExtra("p", Math.abs(n1 % 3));
        startActivity(intent);
        finish();
    }


    public void OnClickBack(View veiw) {
        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
    }


    public void SetAnim(int i, int dir) {
        pets[i].setVisibility(View.INVISIBLE);
        pets[Math.abs((i + dir) % pets_size)].setVisibility(View.VISIBLE);
    }

    public void SetBreed(int i) {
        breed1[i] = (ImageView) findViewById(R.id.pet2);
        breed1[i].setBackgroundResource(skins[1][i]);
        poodles[i] = (AnimationDrawable) breed1[i].getBackground();
        breed1[i].setVisibility(View.VISIBLE);
        poodles[i].start();
    }
}
