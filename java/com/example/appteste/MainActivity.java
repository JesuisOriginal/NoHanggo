package com.example.appteste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appteste.ui.login.LoginActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;

import lecho.lib.hellocharts.model.PieChartData;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {



    private FirebaseAuth mAuth;
//    private PieChart pieChart;w

    Button signUpButton;
    EditText displayNameEditText;
    EditText usernameEditText;
    EditText passwordEditText;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // sets the .xml current view
        setContentView(R.layout.activity_sign_in);

        signUpButton = findViewById(R.id.createUserButton);
        displayNameEditText =  findViewById(R.id.name_field);
        usernameEditText = findViewById(R.id.mail_field);
        passwordEditText = findViewById(R.id.password_field);
        login = findViewById(R.id.login);


        // pie start
//        pieChart = findViewById(R.id0
//         pie end
//        getEntries();

        // declarations
        mAuth = FirebaseAuth.getInstance();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // calls createAccount
                createAccount(displayNameEditText.getText().toString(), usernameEditText.getText().toString(), passwordEditText.getText().toString());


                // logs in the system that
                Log.v("signUp", "UserSignedUp:"+ usernameEditText.getText().toString() + "password: " + passwordEditText.getText().toString());
                // Logs in the created user
                mAuth.signInWithEmailAndPassword(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
//                                    startActivity(new Intent(MainActivity.this, MenuActivity.class));
                                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                } else {
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                // supposed to show a welcome message
//                updateUiWithUser(mAuth.getCurrentUser());
                // shows message to user? maybe?
                Toast.makeText(MainActivity.this, "Register Succeed.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // change to Login screen
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }



    public void createAccount(final String displayName, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // sets the display Name for the user
                            user.updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(displayName).build());
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            updateUiWithUser();
                        } else {
                            // If sign in fails, display a mcessage to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUiWithUser(null);
                        }

                        // TODO discover what to add here
                    }
                });
    }


    private void updateUiWithUser(FirebaseUser model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }
    private void updateUiWithUser() {
        String welcome = getString(R.string.welcome) + mAuth.getCurrentUser().getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }
}
