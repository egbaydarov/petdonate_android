package hse.projectx.petdonate.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.main_screen.MainScreen;
import hse.projectx.petdonate.network.State;
import hse.projectx.petdonate.payment.CheckoutActivity;

public class ChooseFood extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        //кнопка назад
    }

    int index;
    int[] pic = {R.drawable.plate1_food1, R.drawable.plate1_food2, R.drawable.plate1_food4, R.drawable.plate1_food5};
    int[] text = {R.string.don1, R.string.don2, R.string.don3, R.string.don4};
    int[] price =  {R.string.don1r, R.string.don2r, R.string.don3r, R.string.don4r};
    long[] micros = {79,159, 359,759};


    TextView textv, pricev;
    ImageView imagev;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_food);
        index = 0;

        textv = (TextView) findViewById(R.id.text_food);
        pricev = (TextView) findViewById(R.id.text_price);;
        imagev = (ImageView) findViewById(R.id.plate);;
        imagev.setImageResource(pic[index % 4]);
    }



    public void OnClickFoodL(View veiw) {
        index += 3;
        imagev.setImageResource(pic[index % 4]);
        textv.setText(text[index % 4]);
        pricev.setText(price[index % 4]);
    }

    public void OnClickFoodR(View veiw) {
        imagev.setImageResource(pic[++index % 4]);
        textv.setText(text[index % 4]);
        pricev.setText(price[index % 4]);
    }

    public void OnClickYes(View view) {

        State.getShelterList(this, micros[index % 4]);

    }

    public void OnClickBack2(View view) {
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }

}
