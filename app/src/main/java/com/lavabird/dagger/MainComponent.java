package com.lavabird.dagger;

import com.lavabird.ui.MainActivity;

import dagger.Component;

@Component(modules = {DepsManager.class})
public interface MainComponent {
    void injectsMainActivity(MainActivity mainActivity);
}