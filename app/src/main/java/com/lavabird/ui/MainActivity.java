package com.lavabird.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lavabird.Base.DataBaseManager;
import com.lavabird.Base.entity.NotificationDb;
import com.lavabird.MyApp;
import com.lavabird.NotificationAdapter;
import com.lavabird.R;
import java.util.List;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView{

    @Inject
    DataBaseManager dataBaseManager;

    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
    private AlertDialog enableNotificationListenerAlertDialog;
    private NotificationAdapter adapter;
    private RecyclerView rv_notification_list;

    private NotificationAdapter notificationAdapter;
    private TextView txtView;
    private BroadcastReceiver nReceiver;
    private Button startButton;
    private Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApp.mainComponent.injectsMainActivity(this);
        anableRecyclerList(dataBaseManager.getNotifications());
        startButton = findViewById(R.id.startButton);

        if(!isNotificationServiceEnabled()){
            enableNotificationListenerAlertDialog = buildNotificationServiceAlertDialog();
            enableNotificationListenerAlertDialog.show();
        }
        menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rv_notification_list = findViewById(R.id.rv_notification_list);
        adapter = new NotificationAdapter(dataBaseManager.getNotifications());
        rv_notification_list.setAdapter(adapter);

        txtView = (TextView) findViewById(R.id.temp_text_view);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (startButton.getText().equals("START")) {
                    nReceiver = new NotificationReceiver();
                    IntentFilter filter = new IntentFilter();
                    filter.addAction("com.lavabird.NotificationListenerService");
                    registerReceiver(nReceiver, filter);
                    startButton.setText("STOP");
                }else if (startButton.getText().equals("STOP")){
                    unregisterReceiver(nReceiver);
                    startButton.setText("START");
                }
            }
        });
    }

    private void anableRecyclerList(List<NotificationDb> items) {
        RecyclerView notificationList = findViewById(R.id.rv_notification_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        notificationList.setLayoutManager(layoutManager);
        notificationList.setHasFixedSize(true);

        notificationAdapter = new NotificationAdapter(items);
        notificationList.setAdapter(notificationAdapter);
    }

    class NotificationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("dddd","dddd");
            String temp = intent.getStringExtra("notification_event") + "n" + txtView.getText();
            txtView.setText(temp);
            adapter.notificationItemList = dataBaseManager.getNotifications();
            adapter.notifyDataSetChanged();
        }
    }

    private boolean isNotificationServiceEnabled(){
        String pkgName = getPackageName();
        final String flat = Settings.Secure.getString(getContentResolver(),
                ENABLED_NOTIFICATION_LISTENERS);
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private AlertDialog buildNotificationServiceAlertDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.notification_listener_service);
        alertDialogBuilder.setMessage(R.string.notification_listener_service_explanation);
        alertDialogBuilder.setPositiveButton(R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS));
                    }
                });
        alertDialogBuilder.setNegativeButton(R.string.no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // If you choose to not enable the notification listener
                        // the app. will not work as expected
                    }
                });
        return(alertDialogBuilder.create());
    }
}