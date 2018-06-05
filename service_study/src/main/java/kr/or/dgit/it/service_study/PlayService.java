package kr.or.dgit.it.service_study;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlayService extends Service implements MediaPlayer.OnCompletionListener{
    MediaPlayer player;
    String filePath;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String mode = intent.getStringExtra("mode");
            if(mode !=null){
                if(mode.equals("start")){
                    try {
                        if(player != null && player.isPlaying()){
                            player.stop();
                            player.release();
                            player=null;
                        }
                        player = new MediaPlayer();
                        player.setDataSource(filePath);
                        player.prepare();
                        player.start();

                        Intent aIntent = new Intent("kr.or.dgit.it.service_study.PLAY_TO_ACTIVITY");
                        aIntent.putExtra("mode","start");
                        aIntent.putExtra("duration",player.getDuration());
                        sendBroadcast(aIntent);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else if(mode.equals("stop")){
                    if(player !=null && player.isPlaying()){
                        player.stop();
                        player.release();
                        player = null;
                    }
                }
            }
        }
    };

    public PlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Intent intent = new Intent("kr.or.dgit.it.service_study.PLAY_TO_ACTIVITY");
        intent.putExtra("mode","stop");
        sendBroadcast(intent);
        stopSelf();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(receiver, new IntentFilter("kr.or.dgit.it.service_study.PLAY_TO_SERVICE"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        filePath = intent.getStringExtra("filePath");
        if(player != null){
            Intent aIntet = new Intent("kr.or.dgit.it.service_study.PLAY_TO_ACTIVITY");
            aIntet.putExtra("mode","restart");
            aIntet.putExtra("duration",player.getDuration());
            aIntet.putExtra("current",player.getCurrentPosition());
            sendBroadcast(aIntet);
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
