package com.lavabird.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApp.mainComponent.injectsMainActivity(this);
        anableRecyclerList(dataBaseManager.getNotifications());
        Button startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start thread getting notifications

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
}