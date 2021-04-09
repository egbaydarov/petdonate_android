package hse.projectx.petdonate.main_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.info_screens.PetsInfoActivity;
import hse.projectx.petdonate.network.State;
import hse.projectx.petdonate.network.models.Animal;
import hse.projectx.petdonate.network.models.Shelter;

public class PetsActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        //кнопка назад
    }

    Intent intent;

    public static final String NAME = "animal_name";
    public static final String SHELTER_ID = "animal_shelter_id";
    public static final String HEALTH = "animal_health";
    public static final String GENDER = "animal_gender";
    public static final String APPEAR = "animal_appear";
    public static final String BEHAVIOR = "animal_behavior";
    public static final String ID = "animal_id";
    public static final String PET_INSTANCE = "pet_instance";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pets);

        RecyclerView rv = (RecyclerView) findViewById(R.id.scroller);
        GridLayoutManager llm = new GridLayoutManager(this, 2);
        rv.setLayoutManager(llm);

        RVAdapter adapter = new RVAdapter(State.animal_ids);
        rv.setAdapter(adapter);

        intent = new Intent(this, PetsInfoActivity.class);
    }




    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {
        ArrayList<Animal> pets = null;

        RVAdapter(ArrayList<Animal> pets) {
            this.pets = pets;
        }

        @NotNull
        @Override
        public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pets_elem, viewGroup, false);
            return new PersonViewHolder(v);
        }

        /**
         * Тут происходит добавление уникальных элементов в каждый cardview
         */
        @Override
        public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
            personViewHolder.name.setText(pets.get(i).getName());
            Picasso.get()
                    .load(pets.get(i).getPicture())
                    .error(R.drawable.baseline_broken_image_black_18dp)//TODO REPLACE IMAGE
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.load)//TODO REPLACE IMAGE
                    .into(personViewHolder.image);
            Long shelterID = pets.get(i).getShelter_id();
            Shelter res = State.shelter_ids.stream()
                    .filter((sh) -> sh.getId().equals(shelterID))
                    .findFirst()
                    .get();
            personViewHolder.adress.setText(res.getName());

            personViewHolder.cv.setRadius(26F);
            personViewHolder.cv.setCardElevation(8F);
            personViewHolder.cv.setMaxCardElevation(12F);

            View.OnClickListener ok = v -> {
                intent.putExtra(PET_INSTANCE, pets.get(i));
                intent.putExtra(NAME, pets.get(i).getName()); // пара ключ значение
                intent.putExtra(SHELTER_ID, pets.get(i).getShelter_id());
                intent.putExtra(APPEAR, pets.get(i).getAppear());
                intent.putExtra(HEALTH, "Дышит"); //TODO убрать говно
                intent.putExtra(BEHAVIOR, pets.get(i).getBehavior());
                intent.putExtra(GENDER, pets.get(i).getGender());
                intent.putExtra(ID, pets.get(i).getId());
                startActivity(intent);
            };

            personViewHolder.cv.setOnClickListener(ok);
        }

        @Override
        public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }


        @Override
        public int getItemCount() {
            return State.animal_ids.size();
        }

        class PersonViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView name;
            TextView adress;
            ImageView image;

            PersonViewHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.cv);
                name = (TextView) itemView.findViewById(R.id.animal_name);
                adress = (TextView) itemView.findViewById(R.id.animal_adress);
                image = itemView.findViewById(R.id.animal_image);
            }

        }


    }

    /*
    <activity android:name=".Shelter"></activity>
        <activity
            android:name=".SplashActivity"
            android:theme="@style/SplashTheme">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>
                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>
     */




}
