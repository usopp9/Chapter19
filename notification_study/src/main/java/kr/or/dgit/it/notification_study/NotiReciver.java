package kr.or.dgit.it.notification_study;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotiReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast toast = Toast.makeText(context,"I am NotiReciver...",Toast.LENGTH_SHORT);
        toast.show();
    }
}
