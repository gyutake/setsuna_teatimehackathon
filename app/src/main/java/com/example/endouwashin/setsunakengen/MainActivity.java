package com.example.endouwashin.setsunakengen;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @NeedsPermission(Manifest.permission.BLUETOOTH)
    void showBluetooth() {

    }

    @OnPermissionDenied(Manifest.permission.BLUETOOTH)
    void showDeniedForBluetooth() {

    }

    @OnNeverAskAgain(Manifest.permission.BLUETOOTH)
    void showNeverAskForBluetooth() {

    }
}
