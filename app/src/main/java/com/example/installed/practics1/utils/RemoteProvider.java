package com.example.installed.practics1.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Patterns;

import java.io.IOException;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/*
* Article: http://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
* Library OkHttp (import - https://developer.android.com/studio/build/dependencies.html)
* */

public class RemoteProvider extends IntentService {

    public class ACTION_SEND_DATA {
        public static final String ACTION_NAME = "com.example.installed.practics1.utils.action.SEND_DATA";
        public static final String ACTION_RESPONSE_NAME = "com.example.installed.practics1.utils.action.response.SEND_DATA";
        public class INPUT_PARAMS {
            public static final String URL = "com.example.installed.practics1.utils.extra.URL";
            public static final String DATA = "com.example.installed.practics1.utils.extra.DATA";
        }
        public class OUTPUT_PARAMS {
            public static final String URL = "com.example.installed.practics1.utils.extra.URL";
            public static final String RESPONSE_DATA = "com.example.installed.practics1.utils.extra.RESPONSE_DATA";
        }
    }

    OkHttpClient httpClient = new OkHttpClient();

    public class ActionSendDataResult {
        public String URL;
        public String RESPONSE_DATA;
        public ActionSendDataResult(Intent intent) {
            URL = intent.getStringExtra(ACTION_SEND_DATA.OUTPUT_PARAMS.URL);
            RESPONSE_DATA = intent.getStringExtra(ACTION_SEND_DATA.OUTPUT_PARAMS.RESPONSE_DATA);
        }
    }

    public RemoteProvider() {
        super("RemoteProvider");
    }


    public static void startActionSendData(Context context) {
        Intent intent = new Intent(context, RemoteProvider.class);
        intent.setAction(ACTION_SEND_DATA.ACTION_NAME);
        context.startService(intent);
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();

            if (ACTION_SEND_DATA.ACTION_NAME.equals(action)) {
                String url = intent.getStringExtra(ACTION_SEND_DATA.INPUT_PARAMS.URL);
                String data = intent.getStringExtra(ACTION_SEND_DATA.INPUT_PARAMS.DATA);
                handleActionSendData(url, data);
            }

        }
    }


    private void handleActionSendData(String url, String data) {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_SEND_DATA.ACTION_RESPONSE_NAME);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);

        String response_data;
        try {
            response_data = doPostRequest(url, data);
        }
        catch (IOException ex) {
            response_data = ex.getMessage();
        }

        broadcastIntent.putExtra(ACTION_SEND_DATA.OUTPUT_PARAMS.URL, url);
        broadcastIntent.putExtra(ACTION_SEND_DATA.OUTPUT_PARAMS.RESPONSE_DATA, response_data);
        sendBroadcast(broadcastIntent);
    }


    public String doPostRequest(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = httpClient.newCall(request).execute();
        return response.body().toString();
    }


}
