package hse.projectx.petdonate.info_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import hse.projectx.petdonate.main_screen.PetsActivity;
import hse.projectx.petdonate.R;
import hse.projectx.petdonate.network.State;
import hse.projectx.petdonate.forms.BlankPetActivity;
import hse.projectx.petdonate.network.models.Animal;

public class PetsInfoActivity extends AppCompatActivity {

    Animal an;

    @Override
    public void onBackPressed() {
        //кнопка назад
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pets_page);

        ImageView image = (ImageView) findViewById(R.id.an_pic);
        an = (Animal)getIntent().getSerializableExtra(PetsActivity.PET_INSTANCE);
        Picasso.get().load(an.getPicture())
                .error(R.drawable.baseline_broken_image_black_18dp)//TODO REPLACE IMAGE
                .fit()
                .centerCrop()
                .placeholder(R.drawable.load)//TODO REPLACE IMAGE
                .into(image);
        TextView tw_name = findViewById(R.id.an_name);
        tw_name.setText(getIntent().getStringExtra(PetsActivity.NAME));

        TextView tw_appear = findViewById(R.id.an_appear);
        tw_appear.setText(getIntent().getStringExtra(PetsActivity.APPEAR));

        TextView tw_location = findViewById(R.id.an_location);
        long id = getIntent().getLongExtra(PetsActivity.SHELTER_ID, 0);
        if (id != 0) {
            String location = State.shelter_ids.stream()
                    .filter((sh) -> sh.getId().equals(id))
                    .findFirst()
                    .get().getName();
            tw_location.setText(location); //TODO getNAME
        }

        TextView tw_behavior = findViewById(R.id.an_behavior);
        tw_behavior.setText(getIntent().getStringExtra(PetsActivity.BEHAVIOR));

        TextView tw_health = findViewById(R.id.an_health);
        tw_health.setText(getIntent().getStringExtra(PetsActivity.HEALTH));

    }

    public void OnClickShelters(View veiw) {
        startActivity(new Intent(this, PetsActivity.class));
    }

    public void OnClickAccept(View veiw) {
        TextView tw_location = findViewById(R.id.an_location);
        Intent intent =  new Intent(this, BlankPetActivity.class);
        intent.putExtra(PetsActivity.PET_INSTANCE, an);
        intent.putExtra(PetsActivity.NAME, tw_location.getText());
        intent.putExtra(PetsActivity.SHELTER_ID, getIntent().getLongExtra(PetsActivity.SHELTER_ID, 0));
        startActivity(intent);
    }

    public void OnClickBack1(View view) {
        startActivity(new Intent(this, PetsActivity.class));
    }
}
