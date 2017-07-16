package com.example.installed.practics1.helper_classes.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.installed.practics1.MainActivity;

/**
 * Created by Installed on 14.07.2017.
 */

public abstract class Permission implements IOnPermissionGranted, IOnPermissionDenied, IOnPermissionRationale {

    private String _manifestName;
    private MainActivity _activity;
    private int _messageRequestCode;


    public Permission(MainActivity activity, String manifestName, int messageRequestCode) {
        _manifestName = manifestName;
        _activity = activity;
        _messageRequestCode = messageRequestCode;
    }


    public boolean isGranted() {
        int permissionCheckResult = ContextCompat.checkSelfPermission(_activity, _manifestName);
        return (permissionCheckResult == PackageManager.PERMISSION_GRANTED);
    }


    public void tryToGet() {
        if(!isGranted()) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(_activity, _manifestName)) {
                OnRationale();
            }
            else {
                ActivityCompat.requestPermissions(_activity, new String[]{_manifestName}, _messageRequestCode);
            }
        }
    }


    public String getManifestName() {return _manifestName;}
    public int getMessageResultCode() {return _messageRequestCode;}
    public Activity getActivity() {return _activity;}


    public abstract void OnRationale();
    public abstract void OnGranted();
    public abstract void OnDenied();

}
