package com.example.app.views;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.app.R;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver BateriaReceiver = new BroadcastReceiver(){

        private static final int ESCALA = 100;

        @Override
        public void onReceive(Context context, Intent intent) {
            int nivel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int escala = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float porcentaje = nivel * ESCALA / (float)escala;
            Toast.makeText(getApplicationContext(), "Porcentaje batería: " + round(porcentaje) + "%", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.registerReceiver(this.BateriaReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
}