package com.example.installed.practics1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.installed.practics1.helper_classes.screen_controller.IOnLogoFragmentCreateView;


public class LogoFragment extends Fragment {

    public ImageView imageView;
    public FrameLayout frameLayout;
    public View view;
    public IOnLogoFragmentCreateView onLogoFragmentCreateView;

    public LogoFragment() {
        // Required empty public constructor
    }

    public static LogoFragment newInstance() {
        LogoFragment fragment = new LogoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_logo, container, false);
        view = v;
        frameLayout = (FrameLayout) v.findViewById(R.id.FragmentLogoFrameLayout);
        imageView = (ImageView) v.findViewById(R.id.FragmentLogoImageView);
        if(onLogoFragmentCreateView != null) {
            onLogoFragmentCreateView.OnLogoFragmentCreateView(this);
        }
        return v;
    }

}
