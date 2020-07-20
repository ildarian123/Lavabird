package com.lavabird.Base;

import com.lavabird.Base.entity.NotificationDb;
import com.lavabird.NotificationItem;

import java.util.List;

public interface DataBaseManager {
    List<NotificationDb> getNotifications();
    void saveNotificationToDb(NotificationDb NotificationDb);
}
