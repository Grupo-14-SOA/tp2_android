package com.example.app.presenters;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.example.app.views.RefrescarTokenActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServiceCheckTokenExpiration extends IntentService {

    private static final String class_name = ServiceCheckTokenExpiration.class.getSimpleName();
    private ScheduledExecutorService scheduler;
    private Intent intentRefresh;
    private static final int INITIAL_DELAY = 15, PERIOD = 15;

    public ServiceCheckTokenExpiration() {
        super(class_name);
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        intentRefresh = new Intent(this, RefrescarTokenActivity.class);
        intentRefresh.putExtra("refresh_token", intent.getStringExtra("refresh_token"));
        intentRefresh.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                startActivity(intentRefresh);
            }
        },INITIAL_DELAY, PERIOD, TimeUnit.MINUTES);
    }
}
