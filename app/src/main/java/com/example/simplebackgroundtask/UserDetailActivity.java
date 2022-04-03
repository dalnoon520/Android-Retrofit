package com.example.simplebackgroundtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class UserDetailActivity extends AppCompatActivity {

    TextView tvUserId;
    EditText etUserName;
    EditText etUserEmail;
    EditText etUserGender;
    EditText etUserStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        tvUserId = findViewById(R.id.userId);
        etUserName = findViewById(R.id.userName);
        etUserEmail = findViewById(R.id.userEmail);
        etUserGender = findViewById(R.id.userGender);
        etUserStatus = findViewById(R.id.userStatus);

        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", 0);
        String userName = intent.getStringExtra("userName");
        String userEmail = intent.getStringExtra("userEmail");
        String userGender = intent.getStringExtra("userGender");
        String userStatus = intent.getStringExtra("userStatus");
        tvUserId.setText(String.valueOf(userId));
        etUserName.setText(userName);
        etUserEmail.setText(userEmail);
        etUserGender.setText(userGender);
        etUserStatus.setText(userStatus);
    }
}