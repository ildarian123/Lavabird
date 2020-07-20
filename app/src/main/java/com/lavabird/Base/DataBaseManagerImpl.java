package com.lavabird.Base;

import android.content.Context;

import com.lavabird.Base.dao.NotificationDao;
import com.lavabird.Base.entity.NotificationDb;
import com.lavabird.NotificationItem;

import java.util.List;

public class DataBaseManagerImpl implements DataBaseManager {

    private NotificationDataBase dataBase;
    private Context context;
    private NotificationDao notificationDao;

    public DataBaseManagerImpl(Context context) {
        this.dataBase = NotificationDataBase.getDataBase(context);
        this.notificationDao = dataBase.notificationDao();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public List<NotificationDb> getNotifications() {
        return notificationDao.getAll();
    }

    @Override
    public void saveNotificationToDb(NotificationDb notificationDb) {
        notificationDao.setNotification(notificationDb);
    }


}
