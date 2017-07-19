package com.example.installed.practics1.helper_classes.screen_controller;

import com.example.installed.practics1.GreetingFragment;
import com.example.installed.practics1.InfoFragment;
import com.example.installed.practics1.LogoFragment;
import com.example.installed.practics1.MainActivity;
import com.example.installed.practics1.R;

/**
 * Created by Installed on 19.07.2017.
 */

public class InfoScreenController implements IShow, IRemove, IOnLogoFragmentCreateView, IOnInfoFragmentCreateView {


    public LogoFragment logoFragment;
    public InfoFragment infoFragment;
    public MainActivity activity;
    public IOnInfoScreenControllerViewCreated onInfoScreenControllerViewCreated;
    private Boolean isLogoCreated = false;
    private Boolean isInfoCreated = false;


    public InfoScreenController(MainActivity activity) {
        logoFragment = new LogoFragment();
        infoFragment = new InfoFragment();
        this.activity = activity;
        logoFragment.onLogoFragmentCreateView = this;
        infoFragment.onInfoFragmentCreateView = this;
    }


    public void Show() {
        activity.getFragmentManager().beginTransaction()
                .add(R.id.MainActivityLinearLayout, logoFragment)
                .add(R.id.MainActivityLinearLayout, infoFragment)
                .commit();
    }


    public void Remove() {
        activity.getFragmentManager().beginTransaction()
                .remove(logoFragment)
                .remove(infoFragment)
                .commit();
    }


    public void OnLogoFragmentCreateView(LogoFragment logoFragment) {
        isLogoCreated = true;
        if(isLogoCreated && isInfoCreated && (onInfoScreenControllerViewCreated != null))
            onInfoScreenControllerViewCreated.OnInfoScreenControllerViewCreated(this);
    }


    public void OnInfoFragmentCreateView(InfoFragment greetingFragment) {
        isInfoCreated = true;
        if(isLogoCreated && isInfoCreated && (onInfoScreenControllerViewCreated != null))
            onInfoScreenControllerViewCreated.OnInfoScreenControllerViewCreated(this);
    }


}
