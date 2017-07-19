package com.example.installed.practics1;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.installed.practics1.helper_classes.screen_controller.IOnGreetingFragmentCreateView;


public class GreetingFragment extends Fragment {

    public FrameLayout frameLayout;
    public ImageView imageView;
    public TextView textView;
    public View view;
    public IOnGreetingFragmentCreateView onGreetingFragmentCreateView;

    public GreetingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_greeting, container, false);
        view = v;
        frameLayout = (FrameLayout) v.findViewById(R.id.GreetingFragmentFrameLayout);
        imageView = (ImageView) v.findViewById(R.id.GreetingFragmentImageView);
        textView = (TextView) v.findViewById(R.id.GreetingFragmentTextView);
        if(onGreetingFragmentCreateView != null) {
            onGreetingFragmentCreateView.OnGreetingFragmentCreateView(this);
        }
        return v;
    }

}
