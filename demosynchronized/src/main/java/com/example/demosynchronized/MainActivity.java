package com.example.demosynchronized;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Store store = new Store();

        Thread staff1 = new Thread(store, "店員A");
        Thread staff2 = new Thread(store, "店員B");
        Thread staff3 = new Thread(store, "店員C");
        staff1.start();
        staff2.start();
        staff3.start();

    }
}