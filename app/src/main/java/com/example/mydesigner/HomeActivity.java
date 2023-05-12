package com.example.mydesigner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sairamkrishna.mydesigner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class HomeActivity extends AppCompatActivity {

    //Declare the views and firebase variables
    private TextView tvWelcome;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Initialize the views and firebase variables
        tvWelcome = findViewById(R.id.tvWelcome);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Get the user id of the current user
        String userId = mAuth.getCurrentUser().getUid();

        //Get the user object from the firebase database using the user id
        mDatabase.child("users").child(userId).get()
                .addOnCompleteListener(HomeActivity.this, task -> {
                    if (task.isSuccessful()) {
                        //Set the welcome text view
                        tvWelcome.setText("Welcome to My Designer app");
                    } else {
                        //Show a toast message if there is an error in getting the user object
                        Toast.makeText(HomeActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}