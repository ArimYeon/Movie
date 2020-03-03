package com.example.mymovie;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MovieFragment extends Fragment {

    private ImageView image;
    private TextView name, detail;
    private Button detailBtn;
    private int resId;
    private String movieName, rate;

    public final static String MOVIE = "movie";


    public interface detailCallback{
        public abstract void onDetail(int resId, String name, String rate);
    }

    public detailCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getParentFragment() instanceof detailCallback){
            callback = (detailCallback)getParentFragment();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    public static final MovieFragment newInstance(int image, String name, String rate){
        MovieFragment fragment = new MovieFragment();
        Bundle bundle = new Bundle(1);
        bundle.putParcelable(MOVIE, new MovieData(image, name, rate));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container,false);

        image = rootView.findViewById(R.id.movie_image);
        name = rootView.findViewById(R.id.movie_name);
        detail = rootView.findViewById(R.id.movie_detail);
        detailBtn = rootView.findViewById(R.id.movie_detail_btn);

        setData();

        detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback!=null) {
                    callback.onDetail(resId, movieName, rate);
                }
            }
        });

        return rootView;
    }

    private void setData(){
        MovieData data = getArguments().getParcelable(MOVIE);
        resId = data.getImage();
        movieName = data.getName();
        rate = data.getRate();

        image.setImageResource(resId);
        name.setText(movieName);
        detail.setText("예매율 "+rate+"% | 15세 관람가 | D-1");
    }
}
