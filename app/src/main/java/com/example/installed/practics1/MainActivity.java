package com.example.installed.practics1;

import android.Manifest;
import android.app.ActionBar;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.example.installed.practics1.helper_classes.MainActivityController;
import com.example.installed.practics1.helper_classes.permission.IPermissionGetResult;
import com.example.installed.practics1.helper_classes.permission.Permission;
import com.example.installed.practics1.helper_classes.permission.PermissionRegistry;
import com.example.installed.practics1.helper_classes.permission.PrimitivePermission;

public class MainActivity extends AppCompatActivity {

    public class CONSTANTS {
        public class PERMISSIONS {
            public class READ_PHONE_STATE {
                public static final String MANIFEST_NAME = Manifest.permission.READ_PHONE_STATE;
                public static final int REQUEST_CODE = 1;
            }
            public class GET_ACCOUNTS {
                public static final String MANIFEST_NAME = Manifest.permission.GET_ACCOUNTS;
                public static final int REQUEST_CODE = 2;
            }
            public class INTERNET {
                public static final String MANIFEST_NAME = Manifest.permission.INTERNET;
                public static final int REQUEST_CODE = 3;
            }
            public class ACCESS_NETWORK_STATE {
                public static final String MANIFEST_NAME = Manifest.permission.ACCESS_NETWORK_STATE;
                public static final int REQUEST_CODE = 4;
            }
            public class WRITE_CALENDAR {
                public static final String MANIFEST_NAME = Manifest.permission.WRITE_CALENDAR;
                public static final int REQUEST_CODE = 5;
            }
            public class READ_CALENDAR {
                public static final String MANIFEST_NAME = Manifest.permission.READ_CALENDAR;
                public static final int REQUEST_CODE = 6;
            }
        }//permissions

    }


    PermissionRegistry permissionRegistry;
    MainActivityController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPermissions();

        controller = new MainActivityController(this);
        controller.Start();

        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /*
    PERMISSIONS ************************************************************
     */

    void initPermissions() {
        permissionRegistry = new PermissionRegistry(this);
        PrimitivePermission p1 = new PrimitivePermission(
                this,
                CONSTANTS.PERMISSIONS.ACCESS_NETWORK_STATE.MANIFEST_NAME,
                CONSTANTS.PERMISSIONS.ACCESS_NETWORK_STATE.REQUEST_CODE
        );
        PrimitivePermission p2 = new PrimitivePermission(
                this,
                CONSTANTS.PERMISSIONS.GET_ACCOUNTS.MANIFEST_NAME,
                CONSTANTS.PERMISSIONS.GET_ACCOUNTS.REQUEST_CODE
        );
        PrimitivePermission p3 = new PrimitivePermission(
                this,
                CONSTANTS.PERMISSIONS.INTERNET.MANIFEST_NAME,
                CONSTANTS.PERMISSIONS.INTERNET.REQUEST_CODE
        );
        PrimitivePermission p4 = new PrimitivePermission(
                this,
                CONSTANTS.PERMISSIONS.READ_CALENDAR.MANIFEST_NAME,
                CONSTANTS.PERMISSIONS.READ_CALENDAR.REQUEST_CODE
        );
        PrimitivePermission p5 = new PrimitivePermission(
                this,
                CONSTANTS.PERMISSIONS.READ_PHONE_STATE.MANIFEST_NAME,
                CONSTANTS.PERMISSIONS.READ_PHONE_STATE.REQUEST_CODE
        );
        PrimitivePermission p6 = new PrimitivePermission(
                this,
                CONSTANTS.PERMISSIONS.WRITE_CALENDAR.MANIFEST_NAME,
                CONSTANTS.PERMISSIONS.WRITE_CALENDAR.REQUEST_CODE
        );
        IPermissionGetResult getPermissionHandler = new IPermissionGetResult() {
            @Override
            public void PermissionGetResult(Permission permission, Boolean isGot) {
                //TODO?
            }
        };
        p1.permissionGetResult = getPermissionHandler;
        p2.permissionGetResult = getPermissionHandler;
        p3.permissionGetResult = getPermissionHandler;
        p4.permissionGetResult = getPermissionHandler;
        p5.permissionGetResult = getPermissionHandler;
        p6.permissionGetResult = getPermissionHandler;
        permissionRegistry.insertPermission(p1);
        permissionRegistry.insertPermission(p2);
        permissionRegistry.insertPermission(p3);
        permissionRegistry.insertPermission(p4);
        permissionRegistry.insertPermission(p5);
        permissionRegistry.insertPermission(p6);
        p1.tryToGet();
        p2.tryToGet();
        p3.tryToGet();
        p4.tryToGet();
        p5.tryToGet();
        p6.tryToGet();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        Permission permission = permissionRegistry.getPermissionByMessageResultCode(requestCode);
        if (permission != null) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permission.OnGranted();
            }
            else {
                permission.OnDenied();
            }
        }
    }


}
