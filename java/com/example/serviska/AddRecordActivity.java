package com.example.serviska;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddRecordActivity extends AppCompatActivity {

    ImageButton btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Toast.makeText(getApplicationContext(), "Working :)", Toast.LENGTH_SHORT).show();


        btnClose=findViewById(R.id.btnClose);
        btnClose.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return true;
            }
        });
    }
}
