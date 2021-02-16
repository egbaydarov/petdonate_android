package hse.projectx.petdonate.utils;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

//public class MusicServ extends Service {
public class MusicServ extends Service { //TODO НОРМАЛЬНО СДЕЛАЙ
    private static final String TAG = "MyService";
    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
//        Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show();

//        player = MediaPlayer.create(this, R.raw.simpsons);
//        player.setLooping(true); // зацикливаем
    }

    @Override
    public void onDestroy() {
//        Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
//        player.stop();
    }


    @Override
    public void onStart(Intent intent, int startid) {
//        Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
//        player.start();
    }
}