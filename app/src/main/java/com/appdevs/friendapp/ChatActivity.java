package com.appdevs.friendapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";
    private Toolbar toolbar;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Friend Name");
        //toolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_round);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        String name = getIntent().getStringExtra("name");
        String uid = getIntent().getStringExtra("uid");
        String photo = getIntent().getStringExtra("photo");
        String email = getIntent().getStringExtra("email");
        boolean active = getIntent().getBooleanExtra("active", true);
        user = new User(name, email, uid, photo, active);

        toolbar.setTitle(user.getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}