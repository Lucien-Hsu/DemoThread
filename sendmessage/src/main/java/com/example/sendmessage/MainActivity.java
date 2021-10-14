package com.example.sendmessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnHandler;

    //1.建立 Handler
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        //2.以 handleMessage 處理訊息
        @Override
        public void handleMessage(@NonNull Message msg) {
            //3.依照傳過來的訊息編號做處理
            switch (msg.what){
                case 0:
                    btnHandler.setText("OK");
                    break;
                case 1:
                    //TODO
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHandler = findViewById(R.id.btn_handler);
        btnHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testHandler();
            }
        });

    }

    //開啟子執行緒，傳出訊息給主執行緒
    public void testHandler() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //等待一秒
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //4.傳送 Message 給主線程 Handler
                //此處傳遞空訊息
                //引數為訊息編號
                handler.sendEmptyMessage(0);

//                //等待1秒後傳遞空訊息
//                //引數一：訊息編號
//                //引數二：等待秒數
//                handler.sendEmptyMessageDelayed(0, 1000);
            }
        }).start();
    }

}