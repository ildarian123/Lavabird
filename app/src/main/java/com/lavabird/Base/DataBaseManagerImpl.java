package com.lavabird.Base;

import android.content.Context;

import com.lavabird.Base.dao.NotificationDao;
import com.lavabird.Base.entity.NotificationDb;

import java.util.List;

public class DataBaseManagerImpl implements DataBaseManager {

    private NotificationDataBase dataBase;
    private Context context;
    private NotificationDao notificationDao;

    public DataBaseManagerImpl(Context context, NotificationDao notificationDao) {
        this.dataBase = NotificationDataBase.getDataBase(context);
        this.notificationDao = notificationDao;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public List<NotificationDb> getNotifications() {
        return notificationDao.getNotifications();
    }

    @Override
    public void saveNotificationToDb(NotificationDb notificationDb) {
        notificationDao.setNotification(notificationDb);
    }

}
