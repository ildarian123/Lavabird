package com.lavabird.Base;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.lavabird.Base.dao.NotificationDao;
import com.lavabird.Base.entity.NotificationDb;

@Database(entities = {NotificationDb.class}, version = 1)
public abstract class NotificationDataBase extends RoomDatabase {
    private static NotificationDataBase db = null;

    public static NotificationDataBase getDataBase(Context applicationContext) {
        if (db == null) {
            db = Room.databaseBuilder(applicationContext,
                    NotificationDataBase.class, "notification_data_base")
                    .allowMainThreadQueries()
                    .build();
        }
        return db;
    }

    public abstract NotificationDao notificationDao();
}
