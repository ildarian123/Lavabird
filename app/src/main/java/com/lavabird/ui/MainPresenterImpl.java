package com.lavabird.ui;

import com.lavabird.Base.DataBaseManager;
import com.lavabird.Base.dao.NotificationDao;
import com.lavabird.Base.entity.NotificationDb;

import javax.inject.Inject;

public class MainPresenterImpl implements MainPresenter{
    @Inject
    DataBaseManager dataBaseManager;
    @Inject
    NotificationDao notificationDao;

    @Override
    public void saveNotificationToDataBase(NotificationDb notificationDb) {
        notificationDao.setNotification(notificationDb);
    }
}
