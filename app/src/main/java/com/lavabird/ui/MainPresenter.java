package com.lavabird.ui;

import com.lavabird.Base.entity.NotificationDb;
import com.lavabird.NotificationItem;

public interface MainPresenter {
    void saveNotificationToDataBase(NotificationDb NotificationDb);
}
