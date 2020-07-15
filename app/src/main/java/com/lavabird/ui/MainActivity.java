package com.lavabird.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lavabird.Base.DataBaseManager;
import com.lavabird.Base.entity.NotificationDb;
import com.lavabird.MyApp;
import com.lavabird.NotificationAdapter;
import com.lavabird.NotificationItem;
import com.lavabird.R;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView{

    private RecyclerView notificationList;
    private NotificationAdapter notificationAdapter;

    @Inject
    DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anableRecyclerList();

    }

    private void anableRecyclerList() {
        notificationList = findViewById(R.id.rv_notification_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        notificationList.setLayoutManager(layoutManager);
        notificationList.setHasFixedSize(true);

//        notificationAdapter = new NotificationAdapter(List<NotificationItem>);
//        notificationList.setAdapter(notificationAdapter);
    }
}