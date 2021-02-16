package hse.projectx.petdonate.startup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import hse.projectx.petdonate.R;

public class Welcome1 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome1);
    }

    public void OnClickWelc1(View view) {
        startActivity(new Intent(this, Welcome2.class));
    }

}
