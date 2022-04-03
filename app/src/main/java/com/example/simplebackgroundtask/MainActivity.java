package com.example.simplebackgroundtask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplebackgroundtask.adapter.UserListAdapter;
import com.example.simplebackgroundtask.api.ApiClient;
import com.example.simplebackgroundtask.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    private UserListAdapter mAdapter;
    ArrayList<User> mWordList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView1);
        progressBar = findViewById(R.id.progressBar);
        mRecyclerView = findViewById(R.id.recyclerview);

        mAdapter = new UserListAdapter(mWordList, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void startTask(View view) {
        view.setEnabled(false);
        mWordList.clear();
        mTextView.setText(R.string.loading);
        progressBar.setVisibility(View.VISIBLE);

        User user = new User();
        user.name = "ndfasdx";
        user.email = "sdfszx@morissette.io";
        user.gender = "male";
        user.status = "active";
        ApiClient.getAPI().postUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("POST", response.raw().toString());
                mTextView.setText(String.valueOf(response.isSuccessful()));
                view.setEnabled(true);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mTextView.setText(t.getMessage());
                view.setEnabled(true);

            }
        });

//        ApiClient.getAPI().getAllUsers().enqueue(new Callback<ArrayList<User>>() {
//            @Override
//            public void onResponse(@NonNull Call<ArrayList<User>> call, @NonNull Response<ArrayList<User>> response) {
//                ArrayList<User> userList = response.body();
//                mTextView.setText("Number of Users: " + userList.size());
//                mWordList.addAll(userList);
//                mAdapter.notifyDataSetChanged();
//                progressBar.setVisibility(View.GONE);
//                view.setEnabled(true);
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ArrayList<User>> call, @NonNull Throwable t) {
//                mTextView.setText("Error:" + t.getMessage());
//                view.setEnabled(true);
//            }
//        });
    }
}