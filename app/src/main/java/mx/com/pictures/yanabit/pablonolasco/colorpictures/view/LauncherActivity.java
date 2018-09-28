package mx.com.pictures.yanabit.pablonolasco.colorpictures.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import mx.com.pictures.yanabit.pablonolasco.colorpictures.R;
import mx.com.pictures.yanabit.pablonolasco.colorpictures.view.activities.HomeActivity;

public class LauncherActivity extends AppCompatActivity {

    private static final int DALAY=2000;
    private Timer timer= new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        TimerTask timerTask= new TimerTask() {
            @Override
            public void run() {
                Intent intent= new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        };
        timer.schedule(timerTask,DALAY);
    }
}
