package zx31401425.zucc.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button send;
    private Button cancle;

    NotificationManager manager;
    int notification_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = (Button) findViewById(R.id.send);
        cancle = (Button) findViewById(R.id.cancle);

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification.Builder builder = new Notification.Builder(MainActivity.this);
                builder.setSmallIcon(R.drawable.ic_launcher);
                builder.setTicker("World");
                builder.setWhen(System.currentTimeMillis());
                builder.setContentTitle("标题栏");
                builder.setContentText("这个是显示出来的内容部分");

                Intent intent = new Intent(MainActivity.this, Activity.class);
                PendingIntent ma = PendingIntent.getActivity(MainActivity.this,0,intent,0);
                builder.setContentIntent(ma);//设置点击过后跳转的activity

                /*builder.setDefaults(Notification.DEFAULT_SOUND);//设置声音
                builder.setDefaults(Notification.DEFAULT_LIGHTS);//设置指示灯
                builder.setDefaults(Notification.DEFAULT_VIBRATE);//设置震动*/
                //    提示音，闪光灯，震动效果需要添加权限 : <uses-permission android:name="android.permission.VIBRATE"> </uses-permission>
                builder.setDefaults(Notification.DEFAULT_ALL);//设置全部

                Notification notification = builder.build();//4.1以上用.build();
                notification.flags |= Notification.FLAG_AUTO_CANCEL;// 点击通知的时候cancel掉
                manager.notify(notification_id,notification);
            }

        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.cancel(notification_id);
            }
        });
    }
}