package com.example.installed.practics1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.installed.practics1.helper_classes.permission.PermissionRegistry;

public class MainActivity extends AppCompatActivity {

    public class CONSTANTS {
        public class PERMISSIONS {
            public class READ_PHONE_STATE {
                public static final String MANIFEST_NAME = Manifest.permission.READ_PHONE_STATE;

            }
            public class GET_ACCOUNTS {
                public static final String MANIFEST_NAME = Manifest.permission.GET_ACCOUNTS;
            }
            public class INTERNET {
                public static final String MANIFEST_NAME = Manifest.permission.INTERNET;
            }
            public class ACCESS_NETWORK_STATE {
                public static final String MANIFEST_NAME = Manifest.permission.ACCESS_NETWORK_STATE;
            }
            public class WRITE_CALENDAR {
                public static final String MANIFEST_NAME = Manifest.permission.WRITE_CALENDAR;
            }
            public class READ_CALENDAR {
                public static final String MANIFEST_NAME = Manifest.permission.READ_CALENDAR;
            }
        }//permissions

    }


    PermissionRegistry permissionRegistry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        permissionRegistry = new PermissionRegistry(this);
    }



    /*
    PERMISSIONS ************************************************************
     */


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

    }

}
