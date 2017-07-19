package com.example.installed.practics1.helper_classes;

import android.view.View;

import com.example.installed.practics1.MainActivity;
import com.example.installed.practics1.helper_classes.screen_controller.GreetingScreenController;
import com.example.installed.practics1.helper_classes.screen_controller.IOnGreetingScreenControllerViewCreated;
import com.example.installed.practics1.helper_classes.screen_controller.IOnInfoScreenControllerViewCreated;
import com.example.installed.practics1.helper_classes.screen_controller.IOnLoadingScreenControllerViewCreated;
import com.example.installed.practics1.helper_classes.screen_controller.IOnNumpadScreenControllerViewCreated;
import com.example.installed.practics1.helper_classes.screen_controller.InfoScreenController;
import com.example.installed.practics1.helper_classes.screen_controller.LoadingScreenController;
import com.example.installed.practics1.helper_classes.screen_controller.NumpadScreenController;

public class MainActivityController implements IOnLoadingScreenControllerViewCreated,
        IOnNumpadScreenControllerViewCreated,
        IOnGreetingScreenControllerViewCreated,
        IOnInfoScreenControllerViewCreated{

    public LoadingScreenController loadingScreenController;
    public NumpadScreenController numpadScreenController;
    public GreetingScreenController greetingScreenController;
    public InfoScreenController infoScreenController;


    public MainActivityController(MainActivity activity) {
        loadingScreenController = new LoadingScreenController(activity);
        numpadScreenController = new NumpadScreenController(activity);
        greetingScreenController = new GreetingScreenController(activity);
        infoScreenController = new InfoScreenController(activity);
        loadingScreenController.onLoadingScreenControllerViewCreated = this;
        numpadScreenController.onNumpadScreenControllerViewCreated = this;
        greetingScreenController.onGreetingScreenControllerViewCreated = this;
        infoScreenController.onInfoScreenControllerViewCreated = this;
    }

    public void Start() {
        loadingScreenController.Show();
    }


    public void OnLoadingScreenControllerViewCreated(LoadingScreenController controller) {
        loadingScreenController.logoFragment.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingScreenController.Remove();
                numpadScreenController.Show();
            }
        });
    }


    public void OnNumpadScreenControllerViewCreated(NumpadScreenController controller) {
        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numpadScreenController.Remove();
                greetingScreenController.Show();
            }
        };
        numpadScreenController.logoFragment.view.setOnClickListener(l);
        numpadScreenController.numpadFragment.view.setOnClickListener(l);
    }


    public void OnGreetingScreenControllerViewCreated(GreetingScreenController controller) {
        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greetingScreenController.Remove();
                infoScreenController.Show();
            }
        };
        greetingScreenController.logoFragment.view.setOnClickListener(l);
        greetingScreenController.greetingFragment.view.setOnClickListener(l);
    }


    public void OnInfoScreenControllerViewCreated(InfoScreenController controller) {

    }


}
