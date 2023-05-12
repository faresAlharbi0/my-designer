package com.example.mydesigner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button registerCustomerButton;
    private Button registerDesignerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.login_button);
        registerCustomerButton = findViewById(R.id.register_customer_button);
        registerDesignerButton = findViewById(R.id.register_designer_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // move to the login activity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registerCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // move to the registration activity for clients
                Intent intent = new Intent(MainActivity.this, RegisterCustomerActivity.class);
                startActivity(intent);
            }
        });

        registerDesignerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // move to the registration activity for designers
                Intent intent = new Intent(MainActivity.this, RegisterDesignerActivity.class);
                startActivity(intent);
            }
        });
    }
}