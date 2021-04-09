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
import hse.projectx.petdonate.info_screens.ShelterInfoActivity;
import hse.projectx.petdonate.network.models.Shelter;
import hse.projectx.petdonate.payment.ChooseFood;
import hse.projectx.petdonate.utils.MyDialogFragment;

public class SheltersAdapter extends PagerAdapter {

    private List<Shelter> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public static final String NAME = "shelter_name";
    public static final String ADRESS = "shelter_adress";
    public static final String ID = "shelter_id";
    public static final String EMAIL = "shelter_email";
    public static final String URL = "shelter_url";
    public static final String PHONE = "shelter_phone";
    public static final String CARD = "shelter_account";
    public static final String SHELTER = "shelter_instance";

    public SheltersAdapter(List<Shelter> models, Context context) {
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
        desc.setText(models.get(position).getLocation());
        buttonHelp.setOnClickListener(this::OnHelpClicked);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShelterInfoActivity.class);
                intent.putExtra(NAME, models.get(position).getName()); // пара ключ значение
                intent.putExtra(ADRESS, models.get(position).getLocation());
                intent.putExtra(ID, models.get(position).getId());
                intent.putExtra(EMAIL, models.get(position).getEmail());
                intent.putExtra(PHONE, models.get(position).getPhone_number());
                intent.putExtra(URL, models.get(position).getUrl());
                intent.putExtra(CARD, models.get(position).getAccount());
                intent.putExtra(SHELTER, models.get(position));
                context.startActivity(intent);
                // finish();
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
