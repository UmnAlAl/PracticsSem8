package com.example.installed.practics1.helper_classes.screen_controller;


import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.installed.practics1.LogoFragment;
import com.example.installed.practics1.MainActivity;
import com.example.installed.practics1.R;

/**
 * Created by Installed on 19.07.2017.
 */

public class LoadingScreenController implements IShow<LoadingScreenMetadata>, IRemove, IOnLogoFragmentCreateView, IOnLogoFragmentStart {

    public LogoFragment logoFragment;
    public ProgressBar progressBar;
    public TextView textView;
    public MainActivity activity;
    public IOnLoadingScreenControllerViewCreated onLoadingScreenControllerViewCreated;
    public LoadingScreenMetadata metadata = null;
    private boolean isViewCreated = false;


    public LoadingScreenController(MainActivity activity) {
        //logoFragment = new LogoFragment();
        //logoFragment.onLogoFragmentCreateView = this;
        this.activity = activity;
    }


    public void Show(LoadingScreenMetadata metadata) {
        this.metadata = metadata;
        /*activity.getFragmentManager().beginTransaction()
              .add(R.id.MainActivityRelativeLayout, logoFragment)
              .commit();*/
        LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.MainActivityRelativeLayout);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        layoutInflater.inflate(R.layout.loading_screen, linearLayout);
        logoFragment = (LogoFragment) activity.getFragmentManager().findFragmentById(R.id.LoadingScreenLogoFragment);
        progressBar = (ProgressBar) activity.findViewById(R.id.LoadingScreenProgressBar);
        textView = (TextView) activity.findViewById(R.id.LoadingScreenText);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        logoFragment.onLogoFragmentCreateView = this;
        logoFragment.onLogoFragmentStart = this;
    }


    public void Remove() {
       /* activity.getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .remove(logoFragment)
                .commit();*/
        //((ViewGroup) progressBar.getParent()).removeView(progressBar);
        //((ViewGroup) textView.getParent()).removeView(textView);
        isViewCreated = false;
        LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.MainActivityRelativeLayout);
        View view = activity.findViewById(R.id.LoadingScreenRelativeLayout);
        if(activity.findViewById(R.id.LoadingScreenRelativeLayout) != null)
            linearLayout.removeView(view);
    }


    public void OnLogoFragmentCreateView(LogoFragment logoFragment) {
        isViewCreated = true;
        /*FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        logoFragment.frameLayout.setLayoutParams(params);*/

        if(metadata != null) {
            //TODO?
        }
        if(onLoadingScreenControllerViewCreated != null) {
            onLoadingScreenControllerViewCreated.OnLoadingScreenControllerViewCreated(this);
        }
    }


    public void OnLogoFragmentStart(LogoFragment logoFragment) {
        if(!isViewCreated && logoFragment.isFirstRun) {
            this.OnLogoFragmentCreateView(logoFragment);
            logoFragment.isFirstRun = false;
        }
    }

}
