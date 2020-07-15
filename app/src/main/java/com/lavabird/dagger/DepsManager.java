package com.lavabird.dagger;

import android.content.Context;

import com.lavabird.Base.DataBaseManager;
import com.lavabird.Base.DataBaseManagerImpl;
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
    public MainPresenter providesMainPresenter() {
        return new MainPresenterImpl();
    }

    @Provides
    public DataBaseManager providesDataBaseManager() {
        return new DataBaseManagerImpl(context);
    }
}
