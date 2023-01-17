package com.sanjit.sisu2.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.sanjit.sisu2.R;
import com.sanjit.sisu2.adapters.home_horizontal_adapter;
import com.sanjit.sisu2.models.Home_hor_model;
import com.sanjit.sisu2.models.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager2;
    private final Handler sliderHandler = new Handler();
    RecyclerView home_hor_recycler,home_hor_recycler2;
    List<Home_hor_model> home_horizontal_modelList;
    home_horizontal_adapter home_horizontal_adapter;
    LinearLayout vac_info,disease_info,appointments,upload_files,settings,about_us;


    public HomeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        home_hor_recycler = root.findViewById(R.id.home_horizontal_recycler);
        home_hor_recycler2 = root.findViewById(R.id.home_horizontal_recycler2);
        home_horizontal_modelList = new ArrayList<>();

        setHome_horizontal_modelList(home_horizontal_modelList);

        home_horizontal_adapter = new home_horizontal_adapter(getActivity(),home_horizontal_modelList);

        home_hor_recycler.setAdapter(home_horizontal_adapter);
        home_hor_recycler2.setAdapter(home_horizontal_adapter);

        home_hor_recycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        home_hor_recycler2.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        home_hor_recycler.setHasFixedSize(true);
        home_hor_recycler.setNestedScrollingEnabled(true);

        home_hor_recycler2.setHasFixedSize(true);
        home_hor_recycler2.setNestedScrollingEnabled(true);

        viewPager2=root.findViewById(R.id.viewPagerImageSliderHome);

        //Here,i'm preparing list of images from drawable
        // You can also prepare list of images from server or get it from API.
        List<SliderItem> sliderItems=new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.shr5));
        sliderItems.add(new SliderItem(R.drawable.shr2));
        sliderItems.add(new SliderItem(R.drawable.shr3));
        sliderItems.add(new SliderItem(R.drawable.shr4));
        sliderItems.add(new SliderItem(R.drawable.shr1));

        SliderAdapter adapter = new SliderAdapter(sliderItems, viewPager2);
        viewPager2.setAdapter(adapter);

        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);

        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer Transformer=new CompositePageTransformer();
        Transformer.addTransformer(new MarginPageTransformer(60));
        Transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f+r*0.14f);
            }
        });
        viewPager2.setPageTransformer(Transformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,3000); //Slide duration 3 seconds
            }
        });

        vac_info=root.findViewById(R.id.vac_info);
        disease_info = root.findViewById(R.id.disease_info);
        appointments = root.findViewById(R.id.appointments);
        upload_files = root.findViewById(R.id.upload_files);
        settings = root.findViewById(R.id.settings);
        about_us = root.findViewById(R.id.about_us);

        vac_info.setOnClickListener(v -> Toast.makeText(getActivity(), "Vaccine Info", Toast.LENGTH_SHORT).show());

        disease_info.setOnClickListener(v -> Toast.makeText(getActivity(), "Disease Info", Toast.LENGTH_SHORT).show());

        appointments.setOnClickListener(v -> Toast.makeText(getActivity(), "Appointments", Toast.LENGTH_SHORT).show());

        upload_files.setOnClickListener(v -> Toast.makeText(getActivity(), "Upload Files", Toast.LENGTH_SHORT).show());

        settings.setOnClickListener(v -> Toast.makeText(getActivity(), "Settings", Toast.LENGTH_SHORT).show());

        about_us.setOnClickListener(v -> Toast.makeText(getActivity(), "About Us", Toast.LENGTH_SHORT).show());

        return root;
    }

    public void setHome_horizontal_modelList(List<Home_hor_model> home_horizontal_modelList) {
        this.home_horizontal_modelList = home_horizontal_modelList;
        home_horizontal_modelList.add(new Home_hor_model(R.drawable.ic_logo,"Polio"));
        home_horizontal_modelList.add(new Home_hor_model(R.drawable.ic_logo,"DPT"));
        home_horizontal_modelList.add(new Home_hor_model(R.drawable.ic_logo,"Measles"));
        home_horizontal_modelList.add(new Home_hor_model(R.drawable.ic_logo,"Hepatitis B"));
        home_horizontal_modelList.add(new Home_hor_model(R.drawable.ic_logo,"Hepatitis A"));
        home_horizontal_modelList.add(new Home_hor_model(R.drawable.ic_logo,"Hib"));
        home_horizontal_modelList.add(new Home_hor_model(R.drawable.ic_logo,"Rotavirus"));
        home_horizontal_modelList.add(new Home_hor_model(R.drawable.ic_logo,"Pneumococcal"));
        home_horizontal_modelList.add(new Home_hor_model(R.drawable.ic_logo,"Influenza"));
        home_horizontal_modelList.add(new Home_hor_model(R.drawable.ic_logo,"HPV"));
        home_horizontal_modelList.add(new Home_hor_model(R.drawable.ic_logo,"Meningococcal"));
        home_horizontal_modelList.add(new Home_hor_model(R.drawable.ic_logo,"Tdap"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };
}