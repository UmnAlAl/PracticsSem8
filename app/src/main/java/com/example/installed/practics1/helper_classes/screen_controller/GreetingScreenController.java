package com.example.installed.practics1.helper_classes.screen_controller;

import com.example.installed.practics1.GreetingFragment;
import com.example.installed.practics1.InfoFragment;
import com.example.installed.practics1.LogoFragment;
import com.example.installed.practics1.MainActivity;
import com.example.installed.practics1.ProgressBarFragment;
import com.example.installed.practics1.R;

/**
 * Created by Installed on 19.07.2017.
 */

public class GreetingScreenController implements IShow<GreetingScreenMetadata>, IRemove, IOnLogoFragmentCreateView, IOnGreetingFragmentCreateView {


    public LogoFragment logoFragment;
    public GreetingFragment greetingFragment;
    public ProgressBarFragment progressBarFragment;
    public MainActivity activity;
    public GreetingScreenMetadata metadata;
    public IOnGreetingScreenControllerViewCreated onGreetingScreenControllerViewCreated;
    private Boolean isLogoCreated = false;
    private Boolean isGreetingCreated = false;


    public GreetingScreenController(MainActivity activity) {
        logoFragment = new LogoFragment();
        greetingFragment = new GreetingFragment();
        progressBarFragment = new ProgressBarFragment();
        this.activity = activity;
        logoFragment.onLogoFragmentCreateView = this;
        greetingFragment.onGreetingFragmentCreateView = this;
    }


    public void Show(GreetingScreenMetadata metadata) {
        this.metadata = metadata;
        activity.getFragmentManager().beginTransaction()
                .add(R.id.MainActivityRelativeLayout, logoFragment)
                .commit();
        activity.getFragmentManager().beginTransaction()
                .add(R.id.MainActivityRelativeLayout, progressBarFragment)
                .commit();
        activity.getFragmentManager().beginTransaction()
                .add(R.id.MainActivityRelativeLayout, greetingFragment)
                .commit();
    }


    public void Remove() {
        activity.getFragmentManager().beginTransaction()
                .remove(logoFragment)
                .remove(progressBarFragment)
                .remove(greetingFragment)
                .commit();
    }


    public void OnLogoFragmentCreateView(LogoFragment logoFragment) {
        isLogoCreated = true;
        if(isLogoCreated && isGreetingCreated && (onGreetingScreenControllerViewCreated != null))
            onGreetingScreenControllerViewCreated.OnGreetingScreenControllerViewCreated(this);
    }


    public void OnGreetingFragmentCreateView(GreetingFragment greetingFragment) {
        isGreetingCreated = true;
        if(isLogoCreated && isGreetingCreated && (onGreetingScreenControllerViewCreated != null))
            onGreetingScreenControllerViewCreated.OnGreetingScreenControllerViewCreated(this);
    }


}
