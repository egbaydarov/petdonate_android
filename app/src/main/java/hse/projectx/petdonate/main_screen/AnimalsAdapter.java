package hse.projectx.petdonate.main_screen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.info_screens.PetsInfoActivity;
import hse.projectx.petdonate.info_screens.ShelterInfoActivity;
import hse.projectx.petdonate.network.models.Animal;
import hse.projectx.petdonate.network.models.Shelter;
import hse.projectx.petdonate.payment.ChooseFood;
import hse.projectx.petdonate.utils.MyDialogFragment;

public class AnimalsAdapter extends PagerAdapter {

    private List<Animal> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public static final String NAME = "animal_name";
    public static final String SHELTER_ID = "animal_shelter_id";
    public static final String HEALTH = "animal_health";
    public static final String GENDER = "animal_gender";
    public static final String APPEAR = "animal_appear";
    public static final String BEHAVIOR = "animal_behavior";
    public static final String ID = "animal_id";
    public static final String PET_INSTANCE = "pet_instance";

    public AnimalsAdapter(List<Animal> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_shelter, container, false);


        ImageView imageView;
        TextView title, desc;
        Button buttonHelp;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);
        buttonHelp = view.findViewById(R.id.button_card_help);

        Picasso.get()
                .load(models.get(position).getPicture())
                .error(R.drawable.baseline_broken_image_black_18dp) //TODO REPLACE IMAGE
                .fit()
                .centerCrop()
                .placeholder(R.drawable.load)//TODO REPLACE IMAGE
                .into(imageView);
        title.setText(models.get(position).getName());
        desc.setText(models.get(position).getAppear());
        buttonHelp.setOnClickListener(this::OnHelpClicked);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PetsInfoActivity.class);
                intent.putExtra(PET_INSTANCE, models.get(position));
                intent.putExtra(NAME, models.get(position).getName()); // пара ключ значение
                intent.putExtra(SHELTER_ID, models.get(position).getShelter_id());
                intent.putExtra(APPEAR, models.get(position).getAppear());
                intent.putExtra(HEALTH, "Дышит"); //TODO убрать говно
                intent.putExtra(BEHAVIOR, models.get(position).getBehavior());
                intent.putExtra(GENDER, models.get(position).getGender());
                intent.putExtra(ID, models.get(position).getId());
                context.startActivity(intent);
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    public void OnHelpClicked(View view)
    {
        context.startActivity(new Intent(context, ChooseFood.class));
    }
}
