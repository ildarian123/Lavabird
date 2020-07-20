package com.lavabird;

import android.app.Application;

import com.lavabird.dagger.DepsManager;
import com.lavabird.dagger.MainComponent;

public class MyApp extends Application {

    public static MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }
    private void initDagger() {
        mainComponent = DaggerMainComponent.builder()
                .depsManager(new DepsManager(this))
                .build();
    }
}
