package hse.projectx.petdonate.startup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import hse.projectx.petdonate.R;

public class Welcome2 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome2);
    }
    public void OnClickWelc2(View view) {
        startActivity(new Intent(this, FirstScreen.class));
    }
}
