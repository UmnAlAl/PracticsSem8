package com.example.installed.practics1.helper_classes;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Patterns;

import java.util.regex.Pattern;


/*
* Main article
* https://code.tutsplus.com/tutorials/android-fundamentals-intentservice-basics--mobile-6183
* */

public class MobileDataProvider extends IntentService {

    public class ACTION_GET_MOBILE_DATA {
        public static final String ACTION_NAME = "com.example.installed.practics1.helper_classes.action.GET_MOBILE_DATA";
        public static final String ACTION_RESPONSE_NAME = "com.example.installed.practics1.helper_classes.action.response.GET_MOBILE_DATA";
        public class INPUT_PARAMS {

        }
        public class OUTPUT_PARAMS {
            public static final String TELEPHONE_ID = "com.example.installed.practics1.helper_classes.extra.TELEPHONE_ID";
            public static final String TELEPHONE_NUMBER = "com.example.installed.practics1.helper_classes.extra.TELEPHONE_NUMBER";
            public static final String TELEPHONE_MODEL = "com.example.installed.practics1.helper_classes.extra.TELEPHONE_MODEL";
            public static final String EMAIL = "com.example.installed.practics1.helper_classes.extra.EMAIL";
            public static final String HOUR = "com.example.installed.practics1.helper_classes.extra.HOUR";
        }
    }

    public class ActionGetMobileDataResult {
        public String TELEPHONE_ID;
        public String TELEPHONE_NUMBER;
        public String TELEPHONE_MODEL;
        public String EMAIL;
        public Integer HOUR;
        public ActionGetMobileDataResult(Intent intent) {
            TELEPHONE_ID = intent.getStringExtra(ACTION_GET_MOBILE_DATA.OUTPUT_PARAMS.TELEPHONE_ID);
            TELEPHONE_NUMBER = intent.getStringExtra(ACTION_GET_MOBILE_DATA.OUTPUT_PARAMS.TELEPHONE_NUMBER);
            TELEPHONE_MODEL = intent.getStringExtra(ACTION_GET_MOBILE_DATA.OUTPUT_PARAMS.TELEPHONE_MODEL);
            EMAIL = intent.getStringExtra(ACTION_GET_MOBILE_DATA.OUTPUT_PARAMS.EMAIL);
            HOUR = intent.getIntExtra(ACTION_GET_MOBILE_DATA.OUTPUT_PARAMS.HOUR, 0);
        }
    }

    public MobileDataProvider() {
        super("MobileDataProvider");
    }


    public static void startActionGetMobileData(Context context) {
        Intent intent = new Intent(context, MobileDataProvider.class);
        intent.setAction(ACTION_GET_MOBILE_DATA.ACTION_NAME);
        context.startService(intent);
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();

            if (ACTION_GET_MOBILE_DATA.ACTION_NAME.equals(action)) {
                handleActionGetMobileData();
            }

        }
    }


    private void handleActionGetMobileData() {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_GET_MOBILE_DATA.ACTION_RESPONSE_NAME);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(ACTION_GET_MOBILE_DATA.OUTPUT_PARAMS.TELEPHONE_ID, getTelephoneId());
        broadcastIntent.putExtra(ACTION_GET_MOBILE_DATA.OUTPUT_PARAMS.TELEPHONE_NUMBER, getTelephoneNumber());
        broadcastIntent.putExtra(ACTION_GET_MOBILE_DATA.OUTPUT_PARAMS.TELEPHONE_MODEL, getTelephoneModel());
        broadcastIntent.putExtra(ACTION_GET_MOBILE_DATA.OUTPUT_PARAMS.EMAIL, getEmail());
        broadcastIntent.putExtra(ACTION_GET_MOBILE_DATA.OUTPUT_PARAMS.HOUR, getHour());
        sendBroadcast(broadcastIntent);
    }


    private String getTelephoneId() {
        TelephonyManager tmg = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        return tmg.getDeviceId();
    }


    private String getTelephoneNumber() {
        TelephonyManager tmg = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        return tmg.getLine1Number();
    }


    private String getTelephoneModel() {
        return Build.MANUFACTURER
                + " " + Build.MODEL + " " + Build.VERSION.RELEASE
                + " " + Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();
    }


    private String getEmail() {
        String res = new String();
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        Account[] accs = AccountManager.get(this).getAccounts();
        for (Account acc : accs) {
            if(emailPattern.matcher(acc.name).matches()) {
                res += acc.name + " ";
            }
        }
        return res;
    }


    private int getHour() {
        int hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY);
        return hour;
    }


}
