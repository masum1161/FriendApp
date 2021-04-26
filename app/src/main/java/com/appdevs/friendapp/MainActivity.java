package com.appdevs.friendapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.appdevs.friendapp.adpters.UserAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private ProgressBar progressBar;
    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.userRecyclerView);
        progressBar = findViewById(R.id.progress_bar);

        adapter = new UserAdapter(MainActivity.this, userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        getAllUsers(currentUser);
    }

    private void getAllUsers(FirebaseUser currentUser) {
        isLoading(true);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(getString(R.string.user_db))
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                         userList.clear();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : querySnapshot) {
                            User user = (User) queryDocumentSnapshot.toObject(User.class);
                            if (!user.getUid().equalsIgnoreCase(currentUser.getUid()))
                                userList.add(user);
                        }

                        if (userList != null){
                             adapter.notifyDataSetChanged();
                        }
                        isLoading(false);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                isLoading(false);
                Toast.makeText(MainActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void isLoading(boolean isLoading){
        if (isLoading){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
}