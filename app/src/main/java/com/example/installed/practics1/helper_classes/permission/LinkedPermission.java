package com.example.installed.practics1.helper_classes.permission;

import com.example.installed.practics1.MainActivity;

/**
 * Created by Installed on 24.07.2017.
 */

public class LinkedPermission extends Permission {


    public LinkedPermission next = null;
    public boolean isOK = true;
    public ILinkedPermissionResult linkedPermissionResult;


    public LinkedPermission(MainActivity activity, String manifestName, int messageRequestCode) {
        super(activity, manifestName, messageRequestCode);
    }


    @Override
    public void OnRationale() {
        OnDenied();
    }


    @Override
    public void OnGranted() {
        if(next != null) {
            next.isOK = next.isOK && isOK;
            next.tryToGet();
        }
        else {
            if(linkedPermissionResult != null) {
                linkedPermissionResult.LinkedPermissionResult(isOK);
            }
        }
    }


    @Override
    public void OnDenied() {
        isOK = false;
        OnGranted();
    }


}
