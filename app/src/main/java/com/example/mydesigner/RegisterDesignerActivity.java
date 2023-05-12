package com.example.mydesigner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sairamkrishna.mydesigner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class RegisterDesignerActivity extends AppCompatActivity {
    //Declare the views and firebase variables
    private EditText etFullName, etPhone, etEmail, etPassword, etAddress, etSpecialization, etCompany, etProjects;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_designer);

        //Initialize the views and firebase variables
        etFullName = findViewById(R.id.etFullName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etAddress = findViewById(R.id.etAddress);
        etSpecialization = findViewById(R.id.etSpecialization);
        etCompany = findViewById(R.id.etCompany);
        etProjects = findViewById(R.id.etProjects);
        btnRegister = findViewById(R.id.btnRegister);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sp-1-4b5ec-default-rtdb.firebaseio.com/");

        //Set a click listener for the register button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the user input from the edit texts
                String fullName = etFullName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String specialization = etSpecialization.getText().toString().trim();
                String company = etCompany.getText().toString().trim();
                String projects = etProjects.getText().toString().trim();

                //Validate the user input
                if (fullName.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty() || specialization.isEmpty() || company.isEmpty() || projects.isEmpty()) {
                    //Show a toast message if any field is empty
                    Toast.makeText(RegisterDesignerActivity.this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                } else {
                    //Create a new user account using firebase authentication
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterDesignerActivity.this, task -> {
                                if (task.isSuccessful()) {
                                    //Get the user id of the created user
                                    String userId = mAuth.getCurrentUser().getUid();

                                    //Create a user object with the user input
                                    Designer designer = new Designer(fullName, phone, email, address, specialization, company, projects);

                                    //Store the user object in the firebase database under the user id
                                    mDatabase.child("users").child(userId).setValue(designer)
                                            .addOnCompleteListener(RegisterDesignerActivity.this, task1 -> {
                                                if (task1.isSuccessful()) {
                                                    //Show a toast message if the user is registered successfully
                                                    Toast.makeText(RegisterDesignerActivity.this, "Registration completed successfully", Toast.LENGTH_SHORT).show();

                                                    //Move to the main activity
                                                    Intent intent = new Intent(RegisterDesignerActivity.this, HomeActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    //Show a toast message if there is an error in storing the user object
                                                    Toast.makeText(RegisterDesignerActivity.this, "Error: " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                } else {
                                    //Show a toast message if there is an error in creating the user account
                                    Toast.makeText(RegisterDesignerActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
}