package com.example.demosynchronized;

import static java.lang.Thread.sleep;

import android.util.Log;

//賣口罩的商店
public class Store implements Runnable{
    //有20個口罩
    static int masks = 10;

    @Override
    public void run() {
        //不斷賣票
        while(true){
//            synchronized (this) {
                if (masks >= 0) {
                    Log.d("TAG", Thread.currentThread().getName() + " 賣出口罩。" + " 還有 " + masks + " 個口罩。");
                    masks--;
                } else {
                    break;
                }
//            }

            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
