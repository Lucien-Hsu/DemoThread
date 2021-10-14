package com.example.threadpool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testCache();
//        testFixed();
//        testSingle();
//        testScheduled1();
//        testScheduled2();
    }

    public void testCache(){
        //1.建立 newCachedThreadPool
        ExecutorService catchThreadPool = Executors.newCachedThreadPool();

        //進行十個任務
        for (int i = 0 ; i < 10 ; i ++){
            final int index = i;

            //每個任務暫停0.2秒
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //2.執行 catchThreadPool
            //引數為 Runnable
            catchThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Log.d("TAG", Thread.currentThread().getName() + " " + index);
                }
            });
        }
    }

    public void testFixed(){
        //1.建立 newFixedThreadPool 指定為3個執行緒
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        //進行十個任務
        for (int i = 0 ; i < 10 ; i ++){
            final int index = i;

            //每個任務暫停0.2秒
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //2.執行 fixedThreadPool
            //引數為 Runnable
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Log.d("TAG", Thread.currentThread().getName() + " " + index);
                }
            });
        }
    }

    //單 Thread 線程池
    public void testSingle(){
        //1.建立 newSingleThreadExecutor
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

        //進行十個任務
        for (int i = 0 ; i < 10 ; i ++){
            final int index = i;

            //2.執行 singleThreadExecutor
            //引數為 Runnable
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Log.d("TAG", Thread.currentThread().getName() + " " + index);
                }
            });
        }
    }

    //延遲一定時間後執行
    public void testScheduled1(){
        Log.d("TAG", "testScheduled1()");

        //1.建立 singleThreadScheduledExecutor
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        //2.執行 singleThreadScheduledExecutor
        //引數一：Runnable
        //引數二：延遲時間數字
        //引數三：時間單位
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "delay 2s");
            }
        }, 2 , TimeUnit.SECONDS);
    }

    //延遲一定時間後執行，並且固定時間間隔會重複執行
    public void testScheduled2(){
        Log.d("TAG", "testScheduled2()");

        //1.建立 singleThreadScheduledExecutor
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        //2.執行 singleThreadScheduledExecutor
        //引數一：Runnable
        //引數二：延遲時間數字
        //引數三：間格時間數字
        //引數四：時間單位
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "delay 2s, every 1s");
            }
        }, 2 , 1, TimeUnit.SECONDS);

        //3.停止
        //scheduledExecutorService.shutdown();
    }
}