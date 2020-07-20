package com.lavabird.dagger;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.lavabird.Base.DataBaseManager;
import com.lavabird.Base.DataBaseManagerImpl;
import com.lavabird.Base.NotificationDataBase;
import com.lavabird.Base.dao.NotificationDao;
import com.lavabird.ui.MainPresenter;
import com.lavabird.ui.MainPresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class DepsManager {
    private Context context;

    public DepsManager(Context context) {
        this.context = context;
    }

    @Provides
    public MainPresenter providesMainPresenter(DataBaseManager dataBaseManager) {
        return new MainPresenterImpl(dataBaseManager);
    }

    @Provides
    public DataBaseManager providesDataBaseManager(NotificationDao notificationDao) {
        return new DataBaseManagerImpl(context, notificationDao);
    }

    @Provides
    public NotificationDao providesReferenceDao(NotificationDataBase dataBase) {
        return dataBase.notificationDao();
    }

    @Provides
    public NotificationDataBase providesNotificationDataBase (){
        return Room.databaseBuilder(context, NotificationDataBase.class, "notification_data_base").allowMainThreadQueries().build();
    }

}
