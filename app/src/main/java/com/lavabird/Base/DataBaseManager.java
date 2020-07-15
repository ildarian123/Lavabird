package com.lavabird.Base;

import com.lavabird.Base.entity.NotificationDb;

import java.util.List;

public interface DataBaseManager {
    List<NotificationDb> getNotifications();
}
