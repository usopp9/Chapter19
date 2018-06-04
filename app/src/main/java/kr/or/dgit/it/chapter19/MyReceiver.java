package kr.or.dgit.it.chapter19;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals("android.intent.action.NEW_OUTGOING_CALL")){
            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Intent intent1 = new Intent(context,DialogActivity.class);
            intent1.putExtra("number",phoneNumber);
            context.startActivity(intent1);
        }else if(action.equals("android.intent.action.PHONE_STATE")){
            Bundle bundle = intent.getExtras();
            String state = bundle.getString(TelephonyManager.EXTRA_STATE);
            String phoneNumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                Intent intent1 = new Intent(context,DialogActivity.class);
                intent1.putExtra("number",phoneNumber);
                context.startActivity(intent1);
            }
        }
    }
}
