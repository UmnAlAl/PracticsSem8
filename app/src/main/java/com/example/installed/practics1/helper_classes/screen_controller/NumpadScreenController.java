package com.example.installed.practics1.helper_classes.screen_controller;

import com.example.installed.practics1.LogoFragment;
import com.example.installed.practics1.MainActivity;
import com.example.installed.practics1.NumpadFragment;
import com.example.installed.practics1.R;

/**
 * Created by Installed on 19.07.2017.
 */

public class NumpadScreenController implements IShow, IRemove, IOnLogoFragmentCreateView, IOnNumpadFragmentCreateView {

    public NumpadFragment numpadFragment;
    public LogoFragment logoFragment;
    public MainActivity activity;
    public IOnNumpadScreenControllerViewCreated onNumpadScreenControllerViewCreated;
    private Boolean isNumpadCreated = false;
    private Boolean isLogoCreated = false;


    public NumpadScreenController(MainActivity activity) {
        this.numpadFragment = new NumpadFragment();
        this.logoFragment = new LogoFragment();
        this.activity = activity;
        this.logoFragment.onLogoFragmentCreateView = this;
        this.numpadFragment.onNumpadFragmentCreateView = this;
    }


    public void Show() {
        activity.getFragmentManager().beginTransaction()
                .add(R.id.MainActivityLinearLayout, logoFragment)
                .add(R.id.MainActivityLinearLayout, numpadFragment)
                .commit();

    }


    public void Remove() {
        activity.getFragmentManager().beginTransaction()
                .remove(logoFragment)
                .remove(numpadFragment)
                .commit();
    }


    public void OnLogoFragmentCreateView(LogoFragment logoFragment) {
        isLogoCreated = true;
        if(viewCreated() && (onNumpadScreenControllerViewCreated != null))
            onNumpadScreenControllerViewCreated.OnNumpadScreenControllerViewCreated(this);
    }


    public void OnNumpadFragmentCreateView(NumpadFragment numpadFragment){
        isNumpadCreated = true;
        if(viewCreated() && (onNumpadScreenControllerViewCreated != null))
            onNumpadScreenControllerViewCreated.OnNumpadScreenControllerViewCreated(this);
    }


    private Boolean viewCreated() {
        return isLogoCreated && isNumpadCreated;
    }

}
