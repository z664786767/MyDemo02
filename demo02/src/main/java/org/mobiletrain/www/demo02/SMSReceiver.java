package org.mobiletrain.www.demo02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class SMSReceiver extends BroadcastReceiver {
    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        Object[] pdus= (Object[]) intent.getExtras().get("pdus");
//        SmsMessage[]sms=new SmsMessage[pdus.length];
//        for (int i = 0; i <pdus.length ; i++) {
//            sms[i]=SmsMessage.createFromPdu((byte[]) pdus[i]);
//
//            String message=sms[i].getDisplayMessageBody();
//            String adress=sms[i].getDisplayOriginatingAddress();
//            Toast.makeText(context,message+" "+"\n"+adress+"", LENGTH_SHORT).show();
//
//        }
        //得到当前网络状态
        ConnectivityManager systemService =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //得到当前网络状态信息
        NetworkInfo info = systemService.getActiveNetworkInfo();
        if(info!=null&&info.isAvailable()) {
            int type = info.getType();
            switch (type) {
                case ConnectivityManager.TYPE_WIFI:
                    Toast.makeText(context,"wife",LENGTH_SHORT).show();
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    Toast.makeText(context,"moble",LENGTH_SHORT).show();
                    break;
            }
        }else{
            Toast.makeText(context,"no",LENGTH_SHORT).show();

        }
    }
}
