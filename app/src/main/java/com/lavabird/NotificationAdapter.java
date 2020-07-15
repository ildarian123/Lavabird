package com.lavabird;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.lavabird.Base.entity.NotificationDb;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<NotificationDb> notificationItemList;

    public NotificationAdapter(List<NotificationDb> notificationItemList) {
        this.notificationItemList = notificationItemList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layotIdForListItem = R.layout.item_of_notification_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layotIdForListItem, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.name_of_app.setText(notificationItemList.get(position).name_of_app);
        holder.notification_text.setText(notificationItemList.get(position).notification_text);
        holder.date_of_notification.setText(notificationItemList.get(position).date_of_notification);
        holder.time_of_notification.setText(notificationItemList.get(position).time_of_notification);
        holder.notification_image.setText(notificationItemList.get(position).notification_image);
    }

    @Override
    public int getItemCount() {
        return notificationItemList.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {

        TextView name_of_app;
        TextView notification_text;
        TextView time_of_notification;
        TextView date_of_notification;
        TextView notification_image;

        NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            name_of_app = itemView.findViewById(R.id.name_of_app);
            notification_text = itemView.findViewById(R.id.notification_text);
            time_of_notification = itemView.findViewById(R.id.time_of_notification);
            date_of_notification = itemView.findViewById(R.id.date_of_notification);
            notification_image = itemView.findViewById(R.id.notification_image);
        }
    }

}
