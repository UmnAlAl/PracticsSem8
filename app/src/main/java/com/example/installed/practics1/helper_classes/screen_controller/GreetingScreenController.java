package com.example.installed.practics1.helper_classes.screen_controller;

import com.example.installed.practics1.GreetingFragment;
import com.example.installed.practics1.InfoFragment;
import com.example.installed.practics1.LogoFragment;
import com.example.installed.practics1.MainActivity;
import com.example.installed.practics1.R;

/**
 * Created by Installed on 19.07.2017.
 */

public class GreetingScreenController implements IShow, IRemove, IOnLogoFragmentCreateView, IOnGreetingFragmentCreateView {


    public LogoFragment logoFragment;
    public GreetingFragment greetingFragment;
    public MainActivity activity;
    public IOnGreetingScreenControllerViewCreated onGreetingScreenControllerViewCreated;
    private Boolean isLogoCreated = false;
    private Boolean isGreetingCreated = false;


    public GreetingScreenController(MainActivity activity) {
        logoFragment = new LogoFragment();
        greetingFragment = new GreetingFragment();
        this.activity = activity;
        logoFragment.onLogoFragmentCreateView = this;
        greetingFragment.onGreetingFragmentCreateView = this;
    }


    public void Show() {
        activity.getFragmentManager().beginTransaction()
                .add(R.id.MainActivityLinearLayout, logoFragment)
                .add(R.id.MainActivityLinearLayout, greetingFragment)
                .commit();
    }


    public void Remove() {
        activity.getFragmentManager().beginTransaction()
                .remove(logoFragment)
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
