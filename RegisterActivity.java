

package com.example.loginregisterapp; // Ensure this matches your package name

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText etNewUsername, etNewPassword;
    private Button btnCreateAccount;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize views
        etNewUsername = findViewById(R.id.etNewUsername);
        etNewPassword = findViewById(R.id.etNewPassword);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Set click listener for the "Create Account" button
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String username = etNewUsername.getText().toString();
                String password = etNewPassword.getText().toString();

                // Validate input
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Add user to the database
                    boolean isUserAdded = dbHelper.addUser(username, password);

                    if (isUserAdded) {
                        Toast.makeText(RegisterActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                        finish(); // Close the registration activity and return to the login screen
                    } else {
                        Toast.makeText(RegisterActivity.this, "Account creation failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
