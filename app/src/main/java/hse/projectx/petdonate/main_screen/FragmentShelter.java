package hse.projectx.petdonate.main_screen;


import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hse.projectx.petdonate.R;
import hse.projectx.petdonate.network.State;
import hse.projectx.petdonate.network.models.Shelter;

public class FragmentShelter extends Fragment {
    ViewPager viewPager;
    SheltersAdapter adapter;
    List<Shelter> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public void receiveModels()
    {
        models = State.shelter_ids;
        models.add(new Shelter("Zorge", "Good Shelter", "1", "2", "3", "4", "https://priut-zorge.ru/wp-content/uploads/2020/10/image0-600x600.jpg"));
        models.add(new Shelter("BOOBOOBAB", "Good Shelter1", "", "", "", "", "https://lh3.googleusercontent.com/ogw/ADGmqu9idzmizFoxNWwfObRHOK1U7lLb6jJgssW3aKhC=s192-c-mo"));
        models.add(new Shelter("Zorge2", "Good Shelter2", "", "", "", "", "https://priut-zorge.ru/wp-content/uploads/2020/10/image0-600x600.jpg"));
        models.add(new Shelter("Zorge3", "Good Shelter3", "", "", "", "", "https://priut-zorge.ru/wp-content/uploads/2020/10/image0-600x600.jpg"));
        models.add(new Shelter("Zorge4", "Good Shelter3", "", "", "", "", "https://priut-zorge.ru/wp-content/uploads/2020/10/image0-600x600.jpg"));
        models.add(new Shelter("Zorge5", "Good Shelter3", "", "", "", "", "https://priut-zorge.ru/wp-content/uploads/2020/10/image0-600x600.jpg"));
        models.add(new Shelter("Zorge6", "Good Shelter3", "", "", "", "", "https://priut-zorge.ru/wp-content/uploads/2020/10/image0-600x600.jpg"));
        models.add(new Shelter("Zorge7", "Good Shelter3", "", "", "", "", "https://lh3.googleusercontent.com/ogw/ADGmqu9idzmizFoxNWwfObRHOK1U7lLb6jJgssW3aKhC=s192-c-mo"));
        models.add(new Shelter("Zorge8", "Good Shelter3", "", "", "", "", "https://priut-zorge.ru/wp-content/uploads/2020/10/image0-600x600.jpg"));
        models.add(new Shelter("Zorge9", "Good Shelter3", "", "", "", "", "https://lh3.googleusercontent.com/ogw/ADGmqu9idzmizFoxNWwfObRHOK1U7lLb6jJgssW3aKhC=s192-c-mo"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shelter, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Objects.requireNonNull(getActivity(), "Null Activity in Animals Fragment.")
                .getWindow().setBackgroundDrawableResource(R.drawable.background);

        receiveModels();
        adapter = new SheltersAdapter(models, getActivity());

        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(100, 0, 100, 0);
        viewPager.setPageTransformer(false, new PageTransformer());

//        colors = new Integer[]{
//                ContextCompat.getColor(getActivity(), R.color.color1),
//                ContextCompat.getColor(getActivity(), R.color.color2),
//                ContextCompat.getColor(getActivity(), R.color.color3),
//                ContextCompat.getColor(getActivity(), R.color.color4)
//        };

        if (savedInstanceState != null) {
            viewPager.setCurrentItem(savedInstanceState.getInt("Position", 0));
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

//                if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
//                    viewPager.setBackgroundColor(
//                            (Integer) argbEvaluator.evaluate(
//                                    positionOffset,
//                                    colors[position],
//                                    colors[position + 1]
//                            )
//                    );
//                }
//
//                else {
//                    viewPager.setBackgroundColor(colors[colors.length - 1]);
//                }
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
