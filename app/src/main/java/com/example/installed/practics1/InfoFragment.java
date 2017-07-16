package com.example.installed.practics1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class InfoFragment extends Fragment {

    FrameLayout frameLayout;
    TextView textView;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_greeting, container, false);
        frameLayout = (FrameLayout) v.findViewById(R.id.InfoFragmentFrameLayout);
        textView = (TextView) v.findViewById(R.id.InfoFragmentTextView);
        return v;
    }

}
