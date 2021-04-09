package hse.projectx.petdonate.main_screen;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.network.models.Animal;

public class FragmentAnimals extends Fragment {
    ViewPager viewPager;
    AnimalsAdapter adapter;
    List<Animal> models;

    public void receiveModels()
    {
        models = new ArrayList<>();
        //TODO
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_animals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Objects.requireNonNull(getActivity(), "Null Activity in Animals Fragment.")
                .getWindow().setBackgroundDrawableResource(R.drawable.background);


        receiveModels();
        adapter = new AnimalsAdapter(models, getActivity());

        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(100, 0, 100, 0);
        viewPager.setPageTransformer(false, new FragmentAnimals.PageTransformer());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    public static class PageTransformer implements ViewPager.PageTransformer {
        @Override
        public void transformPage(View page, float position) {

            // this part changes the scale, which is the zoom part
            page.setScaleX(1F - .1F * Math.abs(position));
            page.setScaleY(1F - .1F * Math.abs(position));

            // this part sets up so the page zooms from the center
            page.setPivotX(page.getWidth() / 2.0F);
            page.setPivotY(page.getHeight() / 2.0F);
        }
    }
}
