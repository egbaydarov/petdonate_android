package hse.projectx.petdonate.main_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.info_screens.ShelterInfoActivity;
import hse.projectx.petdonate.network.State;
import hse.projectx.petdonate.network.models.Shelter;
import hse.projectx.petdonate.startup.SignInActivity;

public class ShelterActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        //кнопка назад
    }

    Intent intent;
    public static final String NAME = "shelter_name";
    public static final String ADRESS = "shelter_adress";
    public static final String ID = "shelter_id";
    public static final String EMAIL = "shelter_email";
    public static final String URL = "shelter_url";
    public static final String PHONE = "shelter_phone";
    public static final String CARD = "shelter_account";
    public static final String SHELTER = "shelter_instance";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shelters);
        /*
        CardView cardView = (CardView) findViewById(R.id.cv);;

        cardView.setContentPadding(25,25,25,25);
        cardView.setCardBackgroundColor(Color.LTGRAY);


        cardView.setCardElevation(8F);
        cardView.setMaxCardElevation(12F);
        */
        RecyclerView rv = (RecyclerView) findViewById(R.id.scroller);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);


        RVAdapter adapter = new RVAdapter(State.shelter_ids);
        rv.setAdapter(adapter);

        intent = new Intent(this, ShelterInfoActivity.class);
    }


    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {
        ArrayList<Shelter> shelters = null;

        RVAdapter(ArrayList<Shelter> shelters) {
            this.shelters = shelters;
        }

        @NotNull
        @Override
        public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shelter_elem, viewGroup, false);
            return new PersonViewHolder(v);
        }

        /**
         * Тут происходит ИСЛАМ
         */
        @Override
        public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
            personViewHolder.name.setText(shelters.get(i).getName());
            personViewHolder.adress.setText(shelters.get(i).getLocation());
            Picasso.get()
                    .load(shelters.get(i).getPicture())
                    .error(R.drawable.baseline_broken_image_black_18dp) //TODO REPLACE IMAGE
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.load)//TODO REPLACE IMAGE
                    .into(personViewHolder.image);

            personViewHolder.cv.setRadius(30F);
            personViewHolder.cv.setCardElevation(8F);
            personViewHolder.cv.setMaxCardElevation(12F);

            View.OnClickListener ok = v -> {
                intent.putExtra(NAME, shelters.get(i).getName()); // пара ключ значение
                intent.putExtra(ADRESS, shelters.get(i).getLocation());
                intent.putExtra(ID, shelters.get(i).getId());
                intent.putExtra(EMAIL, shelters.get(i).getEmail());
                intent.putExtra(PHONE, shelters.get(i).getPhone_number());
                intent.putExtra(URL, shelters.get(i).getUrl());
                intent.putExtra(CARD, shelters.get(i).getAccount());
                intent.putExtra(SHELTER, shelters.get(i));
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
            return State.shelter_ids.size();
        }

        class PersonViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView name;
            TextView adress;
            ImageView image;

            PersonViewHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.cv);
                name = (TextView) itemView.findViewById(R.id.shelter_name);
                adress = (TextView) itemView.findViewById(R.id.shelter_adress);
                image = (ImageView) itemView.findViewById(R.id.shelter_image);
            }
        }
    }


    public void OnClickShelter(View view) {
    }

    public void onClickProfile(View veiw) {
        if (SignInActivity.account != null) {
            Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("IsFirst", false);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SignInActivity.class);
            intent.putExtra("IsFirst", false);
            startActivity(intent);
        }
    }

    public void OnClickPets(View view) {
    }

    public void OnClickMain0(View view) {
        startActivity(new Intent(this, MainScreen.class));
    }

}
