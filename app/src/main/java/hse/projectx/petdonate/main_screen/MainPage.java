package hse.projectx.petdonate.main_screen;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemReselectedListener;

import hse.projectx.petdonate.R;

public class MainPage extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        bottomNavigationView = findViewById(R.id.main_bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationOnSelect);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentPet()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationOnSelect =
            item -> {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.fragment_animals:
                        fragment = new FragmentAnimals();
                        break;
                    case R.id.fragment_pet:
                        fragment = new FragmentPet();
                        break;
                    case R.id.fragment_profile:
                        fragment = new FragmentProfile();
                        break;
                    case R.id.fragment_shelters:
                        fragment = new FragmentShelter();
                        break;
                }
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                }
                return true;
            };
}
