package com.example.installed.practics1;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.installed.practics1.helper_classes.screen_controller.IOnInfoFragmentCreateView;


public class InfoFragment extends Fragment {

    public FrameLayout frameLayout;
    public TextView textView;
    public View view;
    public IOnInfoFragmentCreateView onInfoFragmentCreateView;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info, container, false);
        view = v;
        frameLayout = (FrameLayout) v.findViewById(R.id.InfoFragmentFrameLayout);
        textView = (TextView) v.findViewById(R.id.InfoFragmentTextView);
        if(onInfoFragmentCreateView != null) {
            onInfoFragmentCreateView.OnInfoFragmentCreateView(this);
        }
        return v;
    }

}
