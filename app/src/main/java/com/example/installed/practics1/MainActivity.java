package com.example.installed.practics1;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public class CONSTANTS {
        public class PERMISSIONS {
            public class PERMISSION_REQUESTS{
                public  static final int READ_PHONE_STATE = 0;
            }
            public class PERMISSION_MESSAGE_RESULT_CODES {
                public class READ_PHONE_STATE {

                }
            }
        }//permissions

    }


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
    }



    /*
    PERMISSIONS ************************************************************
     */

    public boolean checkPermission(String permission) {
        int permissionCheckResult = ContextCompat.checkSelfPermission(this, permission);
        return (permissionCheckResult == PackageManager.PERMISSION_GRANTED);
    }


    public void tryToGetPermission(String permission) {
        if(!checkPermission(permission)) {

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

    }

}
