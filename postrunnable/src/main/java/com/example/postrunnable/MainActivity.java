package com.example.postrunnable;

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
    Button btnPost1, btnPostDelayed;

    //1.建立 Handler
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //按鈕監聽
        btnPost1 = findViewById(R.id.btn_post_1);
        btnPost1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testPost1();
            }
        });

        //按鈕監聽
        btnPostDelayed = findViewById(R.id.btn_post_delayed);
        btnPostDelayed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testPostDelayed();
            }
        });

    }

    //2A.使用主線程 handler 的 post 方法來傳遞 runnable 物件
    //此 runnable 物件會跑在 handler 依附的線程上，這邊是主線程
    private void testPost1() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "post");
                //更換按鈕文字
                btnPost1.setText("=)");
            }
        });
    }

    //2B.使用主線程 handler 的 postDelayed 方法來傳遞 runnable 物件
    //此 runnable 物件會跑在 handler 依附的線程上，這邊是主線程
    private void testPostDelayed() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "postDelayed");
                //更換按鈕文字
                btnPostDelayed.setText("=)");
            }
        }, 1 * 1000);
    }

}