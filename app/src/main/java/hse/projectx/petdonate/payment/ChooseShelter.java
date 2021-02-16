package hse.projectx.petdonate.payment;

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
import hse.projectx.petdonate.main_screen.MainScreen;
import hse.projectx.petdonate.main_screen.ProfileActivity;
import hse.projectx.petdonate.network.State;
import hse.projectx.petdonate.network.models.Shelter;
import hse.projectx.petdonate.startup.SignInActivity;


public class ChooseShelter extends AppCompatActivity {

    Intent intent;

    @Override
    public void onBackPressed() {
        //кнопка назад
    }

    public static final String ID = "shelter_id";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_shelters);

        RecyclerView rv = (RecyclerView) findViewById(R.id.scroller);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);


        ChooseShelter.RVAdapter adapter = new ChooseShelter.RVAdapter(State.shelter_ids);
        rv.setAdapter(adapter);

        intent = new Intent(this, CheckoutActivity.class);
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
                    .error(R.drawable.baseline_broken_image_black_18dp)// TODO REPLACE IMAGE
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.load)// TODO REPLACE IMAGE
                    .into(personViewHolder.image);

            personViewHolder.cv.setRadius(30F);
            personViewHolder.cv.setCardElevation(8F);
            personViewHolder.cv.setMaxCardElevation(12F);

            View.OnClickListener ok = v -> {
                intent.putExtra(ID, shelters.get(i).getId());
                intent.putExtra("MICROS", getIntent().getLongExtra("MICROS",-1));
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
    public void OnClickBack2(View view) {
        Intent intent = new Intent(this, ChooseFood.class);
        startActivity(intent);
    }

}
