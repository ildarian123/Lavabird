package com.lavabird.Base.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notification")
public class NotificationDb {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "notification_image")
    public String notification_image;

    @ColumnInfo(name = "name_of_app")
    public String name_of_app;

    @ColumnInfo(name = "notification_text")
    public String notification_text;

    @ColumnInfo(name = "time_of_notification")
    public String time_of_notification;

    @ColumnInfo(name = "date_of_notification")
    public String date_of_notification;

}
