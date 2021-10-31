package com.example.app.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.app.R;
import com.example.app.presenters.ServiceCheckTokenExpiration;

public class ActivityPrincipal extends AppCompatActivity {

    private Intent intentPrevio, intentServiceCheckTokenExpiration;
    private String token, refreshToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        intentPrevio = getIntent();
        token = intentPrevio.getStringExtra("token");
        refreshToken = intentPrevio.getStringExtra("refresh_token");

        intentServiceCheckTokenExpiration = new Intent(this, ServiceCheckTokenExpiration.class);
        intentServiceCheckTokenExpiration.putExtra("refresh_token", refreshToken);

        if (!isMyServiceRunning(ServiceCheckTokenExpiration.class))
            startService(intentServiceCheckTokenExpiration);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}