package com.hashtechtauch.kuman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hashtechtauch.kuman.Item.Notification;

import java.util.ArrayList;

public class AdapterNotifcation extends RecyclerView.Adapter<AdapterNotifcation.ViewHolder>
{
    Context context;
    ArrayList<Notification> notificationArrayList;


    public AdapterNotifcation(Context context, ArrayList<Notification> notifications)
    {
        this.context = context;
        this.notificationArrayList = notifications;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView titleMessage, subMessage, time;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            titleMessage = itemView.findViewById(R.id.messageTitle);
            subMessage = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.messageDate);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(context).inflate(R.layout.notif_object, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        final Notification notificationList = notificationArrayList.get(position);
        //TODO: Fill Recylerview
//        holder.titleMessage.setText(notificationList.getTitle());
//        holder.subMessage.setText(notificationList.getMessage());
//        holder.time.setText(notificationList.getDateTime());
    }

    @Override
    public int getItemCount()
    {
        return notificationArrayList.size();
    }

}
