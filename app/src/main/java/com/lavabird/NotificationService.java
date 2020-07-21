package com.lavabird;

import android.app.Notification;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.lavabird.Base.entity.NotificationDb;
import com.lavabird.ui.MainPresenter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import javax.inject.Inject;

public class NotificationService extends NotificationListenerService {

    @Inject
    MainPresenter mainPresenter;

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onNotificationPosted(StatusBarNotification sbn){
        MyApp.mainComponent.injectsNotificationService(this);
        Log.v("aaaa","aaaa");
        String pack = sbn.getPackageName();

        Bundle extras = sbn.getNotification().extras;

        int iconId = extras.getInt(Notification.EXTRA_SMALL_ICON);
        Drawable bmp;
        try {
            NotificationDb notificationDb = new NotificationDb();
            final String packageName = sbn.getOpPkg();
            PackageManager packageManager= getApplicationContext().getPackageManager();
            String appName = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA));

            bmp = getApplicationContext().getPackageManager().getApplicationIcon(sbn.getOpPkg());
//            bmp = getPackageManager().getResourcesForApplication(pack).getDrawable(extras.getInt(Notification.EXTRA_SMALL_ICON));
            notificationDb.notification_image = saveToInternalStorage(bmp);
            notificationDb.notification_text = sbn.getNotification().tickerText.toString();
            notificationDb.time_of_notification = Calendar.getInstance().getTimeInMillis();
            notificationDb.name_of_app = appName;
            mainPresenter.saveNotificationToDataBase(notificationDb);
        } catch (PackageManager.NameNotFoundException e) {
            bmp = null;
            e.printStackTrace();
        }

        System.out.println();
        if (extras.containsKey(Notification.EXTRA_PICTURE)) {
            // this bitmap contain the picture attachment

        }
            Intent intent = new Intent("com.lavabird.NotificationListenerService");
            intent.putExtra("notif_image", ((BitmapDrawable)bmp).getBitmap());
            sendBroadcast(intent);
    }

    private String saveToInternalStorage(Drawable bitmapImage){

        Bitmap bitmap = ((BitmapDrawable)bitmapImage).getBitmap();
        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File file = wrapper.getDir("Images",MODE_PRIVATE);
        // Create imageDir
        file = new File(file, bitmapImage.toString()+".jpg");

        try{
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();
        }catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }

        Uri savedImageURI = Uri.parse(file.getAbsolutePath());
        return savedImageURI.toString();
    }

}
