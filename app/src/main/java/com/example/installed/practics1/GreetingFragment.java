package com.example.installed.practics1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class GreetingFragment extends Fragment {

    FrameLayout frameLayout;
    ImageView imageView;
    TextView textView;

    public GreetingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_greeting, container, false);
        frameLayout = (FrameLayout) v.findViewById(R.id.GreetingFragmentFrameLayout);
        imageView = (ImageView) v.findViewById(R.id.GreetingFragmentImageView);
        textView = (TextView) v.findViewById(R.id.GreetingFragmentTextView);
        return v;
    }

}
