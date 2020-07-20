package com.lavabird.dagger;

import com.lavabird.NotificationService;
import com.lavabird.ui.MainActivity;
import com.lavabird.ui.MainPresenterImpl;

import dagger.Component;

@Component(modules = {DepsManager.class})
public interface MainComponent {
    void injectsMainActivity(MainActivity mainActivity);
    void injectsNotificationService(NotificationService notificationService);
    void injectsMainPresenter(MainPresenterImpl mainPresenter);
}