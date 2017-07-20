package com.example.installed.practics1.helper_classes.screen_controller;


import android.app.FragmentTransaction;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.example.installed.practics1.LogoFragment;
import com.example.installed.practics1.MainActivity;
import com.example.installed.practics1.R;

/**
 * Created by Installed on 19.07.2017.
 */

public class LoadingScreenController implements IShow<LoadingScreenMetadata>, IRemove, IOnLogoFragmentCreateView {

    public LogoFragment logoFragment;
    public MainActivity activity;
    public IOnLoadingScreenControllerViewCreated onLoadingScreenControllerViewCreated;
    public LoadingScreenMetadata metadata = null;


    public LoadingScreenController(MainActivity activity) {
        logoFragment = new LogoFragment();
        logoFragment.onLogoFragmentCreateView = this;
        this.activity = activity;
    }


    public void Show(LoadingScreenMetadata metadata) {
        this.metadata = metadata;
        activity.getFragmentManager().beginTransaction()
              .replace(R.id.MainActivityRelativeLayout, logoFragment)
              .commit();

    }


    public void Remove() {
        activity.getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .remove(logoFragment)
                .commit();
    }


    public void OnLogoFragmentCreateView(LogoFragment logoFragment) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        logoFragment.frameLayout.setLayoutParams(params);
        if(metadata != null) {
            //TODO?
        }
        if(onLoadingScreenControllerViewCreated != null) {
            onLoadingScreenControllerViewCreated.OnLoadingScreenControllerViewCreated(this);
        }
    }

}
