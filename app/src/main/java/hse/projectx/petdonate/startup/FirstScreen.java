package hse.projectx.petdonate.startup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.network.State;


public class FirstScreen extends AppCompatActivity{

    public static final String PET_NAME = "pet_name";
    public static final String PET_TYPE = "pet_type";
    public static final String PET_COLOR = "pet_color";
    public static final String PET_HP = "pet_hp";
    public static final String PET_MANA = "pet_mana";
    public static final String PET_STAMINA = "pet_stamina";
    public static final String PREF_NAME = "pet_pref";

    private GestureDetectorCompat lSwipeDetector;
    private static final int SWIPE_MIN_DISTANCE = 130;
    private static final int SWIPE_MAX_DISTANCE = 300;
    private static final int SWIPE_MIN_VELOCITY = 200;

    EditText petName;
    ImageView petPic;
    TextView petType;
    Toast toast;
    int n = 0;
    int[] picture= {R.drawable.dog,R.drawable.cat};
    String[] strings = {"Собака", "Кошка"};

    ConstraintLayout main_layout;


    @Override
    public void onBackPressed() {
        // do nothing
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        petName = (EditText)findViewById(R.id.enter_text);
        petPic = (ImageView)findViewById(R.id.pet);
        petType = (TextView) findViewById(R.id.what_name);
        toast = Toast.makeText(getApplicationContext(), "Введите от 2 до 8 символов", Toast.LENGTH_SHORT);

        lSwipeDetector = new GestureDetectorCompat(this, new MyGestureListener());
        main_layout =  (ConstraintLayout) findViewById(R.id.first_screen);


        main_layout.setOnTouchListener((v, event) -> lSwipeDetector.onTouchEvent(event));

    }

    public void OnMyClickLeft(View view)
    {
        petType.setText(n % 2 != 0 ? "Cобака" : "Кошка");
        petPic.setImageResource(picture[Math.abs(++n  % 2)]);
    }

    public void OnMyClickRight(View view)
    {
        petType.setText(n % 2 != 0 ? "Cобака" : "Кошка");
        petPic.setImageResource(picture[Math.abs(--n  % 2)]);
    }



    public void OnClick(View veiw)
    {
        if (petName.getText().length() >= 2 && petName.getText().length() <= 8)
        {
            State.name = petName.getText().toString();
            State.type = petType.getText().toString();

            Intent intent = new Intent(this, ChooseDog.class);
            intent.putExtra("n", State.name); // пара ключ значение
            startActivity(intent);

        } else
            toast.show();

    }


    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_DISTANCE)
                return false;
            if (Math.abs(e2.getX() - e1.getX()) > SWIPE_MIN_DISTANCE) {
                petType.setText(n % 2 != 0 ? "Cобака" : "Кошка");
                petPic.setImageResource(picture[Math.abs(--n  % 2)]);
            }
            return false;
        }
    }
}
