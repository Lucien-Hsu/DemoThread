package com.example.demothread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv01;
    int countTv01 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv01 = findViewById(R.id.tv_01);
        tv01.setText("nothing happens...");

        //0.主執行緒
        Log.d("TAG", "⭓⭓0.ThreadGroup: " + Thread.currentThread().getThreadGroup().getName() +
                " ⭓⭓ThreadName: " + Thread.currentThread().getName());

        //1.匿名 Thread 覆寫 run() 方法
        new Thread(){
            @Override
            public void run() {
                super.run();
                Log.d("TAG", "⭓⭓1.ThreadGroup: " + Thread.currentThread().getThreadGroup().getName() +
                        " ⭓⭓ThreadName: " + Thread.currentThread().getName());
            }
        }.start();

        //2.匿名 Thread 傳入匿名 Runnable，並覆寫 Runnable 的 run() 方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "⭓⭓2.ThreadGroup: " + Thread.currentThread().getThreadGroup().getName() +
                        " ⭓⭓ThreadName: " + Thread.currentThread().getName());

            }
        }).start();

        new Thread(){
            @Override
            public void run() {
                super.run();
                Log.d("TAG", "⭓⭓3.ThreadGroup: " + Thread.currentThread().getThreadGroup().getName() +
                        " ⭓⭓ThreadName: " + Thread.currentThread().getName());
                //3.在非主執行緒更新 View 會 crash?
//                tv01.setText("Magic!");

//                //4-1.在主執行緒更新畫面
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d("TAG", "⭓⭓4.ThreadGroup: " + Thread.currentThread().getThreadGroup().getName() +
//                                " ⭓⭓ThreadName: " + Thread.currentThread().getName());
//                        tv01.setText("runOnUiThread");
//                    }
//                });
//                //4-2.在主執行緒更新畫面
//                tv01.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        tv01.setText("View.post");
//                    }
//                });
//                //4-3.使用 View.postDelayed 延遲後更新UI
//                tv01.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        tv01.setText("view.postDelayed");
//                    }
//                }, 2000);

            }
        }.start();


    }
}