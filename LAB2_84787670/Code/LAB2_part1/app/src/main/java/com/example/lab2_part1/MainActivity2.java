package com.example.lab2_part1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView tvNameValue   = findViewById(R.id.textView2);
        TextView tvEmailValue  = findViewById(R.id.textView3);
        TextView tvGenderValue = findViewById(R.id.textView4);
        TextView tvDobValue    = findViewById(R.id.textView5);

        String name     = getIntent().getStringExtra("name");
        String email    = getIntent().getStringExtra("email");
        String gender   = getIntent().getStringExtra("gender");
        String birthday = getIntent().getStringExtra("birthday");

        tvNameValue.setText(name != null ? name : "");
        tvEmailValue.setText(email != null ? email : "");
        tvGenderValue.setText(gender != null ? gender : "");
        tvDobValue.setText(birthday != null ? birthday : "");
    }
}
