package com.lavabird.Base.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lavabird.Base.entity.NotificationDb;
import com.lavabird.NotificationItem;

import java.util.List;

@Dao
public interface NotificationDao {

    @Query("SELECT * FROM notification")
    List<NotificationDb> getAll();

    @Insert
    void setNotification(NotificationDb NotificationDb);
}
