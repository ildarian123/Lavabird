package com.lavabird;

import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * MIT License
 *
 *  Copyright (c) 2016 Fábio Alves Martins Pereira (Chagall)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class NotificationService extends NotificationListenerService {

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn){

        Log.v("aaaa","aaaa");
        String pack = sbn.getPackageName();

        Bundle extras = sbn.getNotification().extras;

        int iconId = extras.getInt(Notification.EXTRA_SMALL_ICON);
        Drawable bmp;
        try {
            bmp = getPackageManager().getResourcesForApplication(pack).getDrawable(extras.getInt(Notification.EXTRA_SMALL_ICON));
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

}
