package com.example.installed.practics1.helper_classes.permission;

import com.example.installed.practics1.MainActivity;

/**
 * Created by Installed on 15.07.2017.
 */

public class PrimitivePermission extends Permission {

    public PrimitivePermission(MainActivity activity, String manifestName, int messageRequestCode) {
        super(activity, manifestName, messageRequestCode);
    }


    public void OnRationale() {
        super.tryToGet();
    }
    public void OnGranted() {}
    public void OnDenied() {}

}
