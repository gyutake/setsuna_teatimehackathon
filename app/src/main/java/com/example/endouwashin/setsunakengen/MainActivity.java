package com.example.endouwashin.setsunakengen;
import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

import static com.example.endouwashin.setsunakengen.R.drawable.hanabi1;
import static com.example.endouwashin.setsunakengen.R.id.list_item;
import static com.example.endouwashin.setsunakengen.R.id.myImageView;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1c2833")));
//        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
      //  progressBar.setVisibility(View.VISIBLE);

        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,1);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        List<Drawable> data=new ArrayList<>();


            Drawable drawable1 = getResources().getDrawable(R.drawable.hanabi1);
        Drawable drawable2 = getResources().getDrawable(R.drawable.hanabi2);
        Drawable drawable5 = getResources().getDrawable(R.drawable.hanabi2);
        Drawable drawable3 = getResources().getDrawable(R.drawable.hanabi2);
        Drawable drawable4 = getResources().getDrawable(R.drawable.hanabi2);
            data.add(drawable1);
        data.add(drawable2);
        data.add(drawable3);
        data.add(drawable4);
        data.add(drawable5);




        final Adapter adapter =new Adapter(data);
        recyclerView.setAdapter(adapter);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        }
        if (!mBluetoothAdapter.isEnabled()) {
            int REQUEST_ENABLE_BT = 2;
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

        }

        // Register for broadcasts when a device is discovered.
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        int DISCOVER_DURATION=3600;
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, DISCOVER_DURATION);
        startActivity(discoverableIntent);
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
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
            }
        }
    };




}
