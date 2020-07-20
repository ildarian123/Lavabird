package com.lavabird.ui;

import com.lavabird.Base.DataBaseManager;
import com.lavabird.Base.dao.NotificationDao;
import com.lavabird.Base.entity.NotificationDb;
import com.lavabird.MyApp;

import javax.inject.Inject;

public class MainPresenterImpl implements MainPresenter{

    private DataBaseManager dataBaseManager;

    @Inject public MainPresenterImpl (DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    @Override
    public void saveNotificationToDataBase(NotificationDb notificationDb) {
        dataBaseManager.saveNotificationToDb(notificationDb);
    }
}
