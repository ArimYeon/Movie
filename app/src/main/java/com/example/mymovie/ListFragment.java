package com.example.mymovie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class ListFragment extends Fragment implements MovieFragment.detailCallback {

    private MoviePagerAdapter adapter;
    private ViewGroup rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_list, container, false);

        adapter = new MoviePagerAdapter(getChildFragmentManager());
        ViewPager pager = rootView.findViewById(R.id.pager);

        adapter.addItem(MovieFragment.newInstance(R.drawable.image1, "1.군 도", "61.6"));
        adapter.addItem(MovieFragment.newInstance(R.drawable.image2, "2.공 조", "61.6"));
        adapter.addItem(MovieFragment.newInstance(R.drawable.image3, "3.더 킹", "61.6"));
        adapter.addItem(MovieFragment.newInstance(R.drawable.image4, "4.레지던트 이블", "61.6"));
        adapter.addItem(MovieFragment.newInstance(R.drawable.image5, "5.럭 키", "61.6"));
        adapter.addItem(MovieFragment.newInstance(R.drawable.image6, "6.아수라", "61.6"));

        pager.setAdapter(adapter);

        return rootView;
    }

    public void onDetail(int resId, String name, String rate){
        ((MainActivity)getActivity()).changeFragment(resId, name, rate);
        //Navigation.findNavController(rootView).navigate(R.id.action_nav_movie_list_to_nav_movie_detail);
    }
}
